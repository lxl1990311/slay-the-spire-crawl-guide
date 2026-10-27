# 爬塔速查 · CrawlGuide

《杀戮尖塔》一代的 DotaPlus 式速查 mod：在卡牌三选一、Boss 遗物三选一和商店里，直接把 **T 度、流派标签、当前卡组契合度** 显示在游戏画面上。

## 功能

- 三选一卡牌：卡牌上方显示 `T0`–`T3` 徽章和所属流派；底部显示「牌组倾向：消耗 / 核心 2/3」。
- 卡组契合：与你当前卡组最接近的流派，对应卡牌加金边并标 `* 契合`；核心件额外标 `核心`。
- Boss 遗物：左上角 T 度，底部 `> 毒` 表示这件遗物指向的流派；契合当前卡组时变金色。
- 商店：卡牌和遗物同样显示 T 度与流派标签。

## 安装

1. Steam 版《杀戮尖塔》一代（游戏版本 `12-18-2022`）。
2. 安装 [ModTheSpire 3.30.3](https://steamcommunity.com/sharedfiles/filedetails/?id=1605060445)。
3. 从 [Releases](../../releases) 下载 `crawlguide.jar`，放进游戏目录的 `mods/` 文件夹。
4. 用 ModTheSpire 启动游戏，勾选 `爬塔速查 CrawlGuide`。

## 当前数据

- 327 张卡牌：铁甲战士 / 静默猎手 / 故障机器人 / 观者各 73 张，无色 35 张
- 179 件遗物
- 16 套流派（四职业各 4 套）

不含基础打击/防御、状态、诅咒和正常流程中无法获得的 token 卡。

### T 度与流派来源

- 原速查表收录的 114 张卡：保留原表的 T 度与流派标签。
- 其余 213 张卡：T 度来自 [SpireSpy 的《杀戮尖塔 1》卡牌梯队](https://maybelatergames.co.uk/tools/slaythespire/tierlist/cards/ironclad/)，`S/A/B/C/D` 映射为 `T0–T3`（S→T0，A→T1，B→T2，C/D→T3）。
- 新增卡牌的流派标签由《杀戮尖塔 1》卡牌文本规则推导；无色牌通常只显示 T 度。
- 全程没有使用《杀戮尖塔 2》的卡牌或评分数据。

## 从源码构建

需要：

- JDK 8
- Node.js 18+
- 游戏目录下的 `desktop-1.0.jar`
- 游戏目录下的 `ModTheSpire.jar`

```powershell
.\tools\build.ps1 -StsDir "C:\Program Files (x86)\Steam\steamapps\common\SlayTheSpire" -JdkDir "C:\Java\jdk8"
```

脚本会从游戏 jar 提取中英文卡牌/遗物映射，合并 `guide/spirespy-tiers.json` 的全卡池 T 度，重新生成 `CrawlGuide/data.json` 与 `GuideData.java`，编译并输出 `dist/crawlguide.jar`。

## 说明

- 原表 114 张的 T 度是作者主观排序；其余卡来自 SpireSpy 社区梯队，两套评分可能不一致。
- 新增卡牌的流派标签是规则推导，不是原表逐张手写，可能有偏差。
- 「卡组倾向」是启发式打分（核心件 4 分、关键件 2 分），仅作参考。
- 使用 mod 启动会禁用游戏成就。
- 本项目与 Mega Crit Games 无关；卡牌与遗物名称、图片版权归其所有。
