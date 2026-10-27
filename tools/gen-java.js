const fs = require('fs');

const dataPath = process.argv[2] || 'CrawlGuide/data.json';
const outPath = process.argv[3] || 'CrawlGuide/src/crawlguide/GuideData.java';
const data = JSON.parse(fs.readFileSync(dataPath, 'utf8'));

const esc = s => String(s).replace(/\\/g, '\\\\').replace(/"/g, '\\"');
const arr = list => list.length === 0 ? 'new String[0]' : 'new String[]{' + list.map(x => '"' + esc(x) + '"').join(', ') + '}';

const lines = [];
lines.push('package crawlguide;');
lines.push('');
lines.push('import java.util.ArrayList;');
lines.push('import java.util.HashMap;');
lines.push('import java.util.List;');
lines.push('import java.util.Map;');
lines.push('');
lines.push('/* Generated from the Crawl Guide HTML. Do not edit by hand. */');
lines.push('public final class GuideData {');
lines.push('    public static final class Build {');
lines.push('        public final String key;');
lines.push('        public final String charId;');
lines.push('        public final String tier;');
lines.push('        public final String nameCn;');
lines.push('        public final String nameEn;');
lines.push('        public final String shortCn;');
lines.push('        public final String shortEn;');
lines.push('        public final String[] core;');
lines.push('        public final String[] keys;');
lines.push('');
lines.push('        Build(String key, String charId, String tier, String nameCn, String nameEn,');
lines.push('              String shortCn, String shortEn, String[] core, String[] keys) {');
lines.push('            this.key = key;');
lines.push('            this.charId = charId;');
lines.push('            this.tier = tier;');
lines.push('            this.nameCn = nameCn;');
lines.push('            this.nameEn = nameEn;');
lines.push('            this.shortCn = shortCn;');
lines.push('            this.shortEn = shortEn;');
lines.push('            this.core = core;');
lines.push('            this.keys = keys;');
lines.push('        }');
lines.push('    }');
lines.push('');
lines.push('    public static final class Card {');
lines.push('        public final String id;');
lines.push('        public final String name;');
lines.push('        public final String cn;');
lines.push('        public final String tier;');
lines.push('        public final String[] builds;');
lines.push('');
lines.push('        Card(String id, String name, String cn, String tier, String[] builds) {');
lines.push('            this.id = id;');
lines.push('            this.name = name;');
lines.push('            this.cn = cn;');
lines.push('            this.tier = tier;');
lines.push('            this.builds = builds;');
lines.push('        }');
lines.push('    }');
lines.push('');
lines.push('    public static final class Relic {');
lines.push('        public final String id;');
lines.push('        public final String name;');
lines.push('        public final String cn;');
lines.push('        public final String rarity;');
lines.push('        public final String tier;');
lines.push('        public final String[] builds;');
lines.push('');
lines.push('        Relic(String id, String name, String cn, String rarity, String tier, String[] builds) {');
lines.push('            this.id = id;');
lines.push('            this.name = name;');
lines.push('            this.cn = cn;');
lines.push('            this.rarity = rarity;');
lines.push('            this.tier = tier;');
lines.push('            this.builds = builds;');
lines.push('        }');
lines.push('    }');
lines.push('');
lines.push('    public static final List<Build> BUILDS = new ArrayList<Build>();');
lines.push('    public static final Map<String, Build> BUILD_BY_KEY = new HashMap<String, Build>();');
lines.push('    public static final Map<String, Card> CARDS = new HashMap<String, Card>();');
lines.push('    public static final Map<String, Relic> RELICS = new HashMap<String, Relic>();');
lines.push('');
lines.push('    static {');
for (const b of data.builds) {
  lines.push('        addBuild("' + esc(b.key) + '", "' + esc(b.char) + '", "' + esc(b.tier) + '", "' +
    esc(b.nameCn) + '", "' + esc(b.nameEn) + '", "' + esc(b.shortCn) + '", "' + esc(b.shortEn) + '", ' +
    arr(b.core) + ', ' + arr(b.keys) + ');');
}
lines.push('');
for (const c of Object.values(data.cards)) {
  lines.push('        addCard("' + esc(c.id) + '", "' + esc(c.name) + '", "' + esc(c.cn) + '", "' +
    esc(c.tier || '') + '", ' + arr(c.builds) + ');');
}
lines.push('');
for (const r of Object.values(data.relics)) {
  lines.push('        addRelic("' + esc(r.id) + '", "' + esc(r.name) + '", "' + esc(r.cn) + '", "' +
    esc(r.rarity) + '", "' + esc(r.tier || '') + '", ' + arr(r.builds) + ');');
}
lines.push('    }');
lines.push('');
lines.push('    private GuideData() {');
lines.push('    }');
lines.push('');
lines.push('    private static void addBuild(String key, String charId, String tier, String nameCn, String nameEn,');
lines.push('                                  String shortCn, String shortEn, String[] core, String[] keys) {');
lines.push('        Build b = new Build(key, charId, tier, nameCn, nameEn, shortCn, shortEn, core, keys);');
lines.push('        BUILDS.add(b);');
lines.push('        BUILD_BY_KEY.put(key, b);');
lines.push('    }');
lines.push('');
lines.push('    private static void addCard(String id, String name, String cn, String tier, String[] builds) {');
lines.push('        CARDS.put(id, new Card(id, name, cn, tier, builds));');
lines.push('    }');
lines.push('');
lines.push('    private static void addRelic(String id, String name, String cn, String rarity, String tier, String[] builds) {');
lines.push('        RELICS.put(id, new Relic(id, name, cn, rarity, tier, builds));');
lines.push('    }');
lines.push('}');
lines.push('');

fs.mkdirSync('c:/Users/Admin/OneDrive/Desktop/sljt/CrawlGuide/src/crawlguide', { recursive: true });
fs.writeFileSync(outPath, lines.join('\n'), 'utf8');
console.log('wrote', outPath, 'cards', Object.keys(data.cards).length, 'relics', Object.keys(data.relics).length);
