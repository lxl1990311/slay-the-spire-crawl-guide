param(
    [Parameter(Mandatory = $true)][string]$StsDir,
    [string]$JdkDir = $env:JAVA_HOME,
    [string]$MtsJar,
    [string]$GuidePath = "guide/index.html"
)

$ErrorActionPreference = "Stop"
$root = Split-Path -Parent $PSScriptRoot
Set-Location $root

if (-not $JdkDir) {
    throw "JdkDir is required. Pass -JdkDir or set JAVA_HOME to a JDK 8 installation."
}
$javac = Join-Path $JdkDir "bin\javac.exe"
$jar = Join-Path $JdkDir "bin\jar.exe"
if (-not (Test-Path $javac)) {
    throw "javac not found under $JdkDir"
}

$desktopJar = Join-Path $StsDir "desktop-1.0.jar"
if (-not (Test-Path $desktopJar)) {
    throw "desktop-1.0.jar not found under $StsDir"
}
if (-not $MtsJar) {
    $MtsJar = Join-Path $StsDir "ModTheSpire.jar"
}
if (-not (Test-Path $MtsJar)) {
    throw "ModTheSpire.jar not found. Install ModTheSpire first or pass -MtsJar."
}
if (-not (Test-Path $GuidePath)) {
    throw "Guide HTML not found at $GuidePath"
}

Write-Host "==> Extracting localization from desktop-1.0.jar"
$locDir = Join-Path $root ".build\localization"
New-Item -ItemType Directory -Force $locDir | Out-Null
Add-Type -AssemblyName System.IO.Compression.FileSystem
$zip = [IO.Compression.ZipFile]::OpenRead($desktopJar)
try {
    $files = @(
        @{ Path = "localization/eng/cards.json"; Out = "cards.json.eng.json" },
        @{ Path = "localization/eng/relics.json"; Out = "relics.json.eng.json" },
        @{ Path = "localization/zhs/cards.json"; Out = "cards.json.zhs.json" }
    )
    foreach ($file in $files) {
        $entry = $zip.Entries | Where-Object { $_.FullName -eq $file.Path }
        if (-not $entry) {
            throw "$($file.Path) not found in desktop-1.0.jar"
        }
        [IO.Compression.ZipFileExtensions]::ExtractToFile($entry, (Join-Path $locDir $file.Out), $true)
    }
} finally {
    $zip.Dispose()
}

Write-Host "==> Building data.json from $GuidePath"
& node "tools/extract-data.js" $GuidePath "CrawlGuide/data.json" $locDir
if ($LASTEXITCODE -ne 0) {
    throw "extract-data.js failed"
}

Write-Host "==> Expanding to the full Slay the Spire 1 card pool"
& node "tools/build-full-data.js" "CrawlGuide/data.json" "guide/spirespy-tiers.json" $locDir
if ($LASTEXITCODE -ne 0) {
    throw "build-full-data.js failed"
}

Write-Host "==> Generating GuideData.java"
& node "tools/gen-java.js" "CrawlGuide/data.json" "CrawlGuide/src/crawlguide/GuideData.java"
if ($LASTEXITCODE -ne 0) {
    throw "gen-java.js failed"
}

Write-Host "==> Compiling"
$classes = Join-Path $root "build\classes"
New-Item -ItemType Directory -Force $classes | Out-Null
$sources = Get-ChildItem "CrawlGuide\src\crawlguide\*.java" | ForEach-Object { $_.FullName }
& $javac -encoding UTF-8 -source 8 -target 8 -cp "$desktopJar;$MtsJar" -d $classes $sources
if ($LASTEXITCODE -ne 0) {
    throw "Compilation failed"
}

Write-Host "==> Packaging"
New-Item -ItemType Directory -Force "dist" | Out-Null
& $jar cf "dist\crawlguide.jar" -C $classes . -C "CrawlGuide\resources" ModTheSpire.json
if ($LASTEXITCODE -ne 0) {
    throw "Packaging failed"
}

Write-Host ""
Write-Host "Done: dist\crawlguide.jar"
Write-Host "Copy it to <SlayTheSpire>\mods\ and launch with ModTheSpire."
