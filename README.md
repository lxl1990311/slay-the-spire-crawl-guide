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

- 320 张可获取卡牌：铁甲战士 72、静默猎手 71、故障机器人 71、观者 71、无色 35
- 179 件遗物
- 16 套流派（四职业各 4 套）

不包含不会出现在卡牌奖励/商店的基础打击防御、状态、诅咒和 token 卡。

### T 度与流派来源

- 全部 320 张卡的 T 度统一来自 [Unduel 的《Slay the Spire》全卡社区共识榜](https://unduel.com/t/slay-the-spire/the-best-slay-the-spire-cards-2W3WSTcjk91rLIipyd0x3x/tier-list)：85 位投票者、约 9.3K 次评分，抓取于 2026-10-27。
- 全部 179 件遗物的 T 度统一来自 [Unduel 的《Slay the Spire》遗物共识榜](https://unduel.com/t/slay-the-spire/the-best-slay-the-spire-relics-2CfPb2TVHz5wtNJ9NdZ8Nd/tier-list)：16 位投票者、约 1.2K 次评分。
- 映射规则固定：`S→T0`、`A→T1`、`B→T2`、`C/D/F→T3`，不再混用其他榜单。
- 所有卡的流派标签都由《杀戮尖塔 1》卡牌文本规则推导；遗物到流派的标签仍沿用速查表。
- 没有使用《杀戮尖塔 2》的任何卡牌或评分数据。

## 从源码构建

需要：

- JDK 8
- Node.js 18+
- 游戏目录下的 `desktop-1.0.jar`
- 游戏目录下的 `ModTheSpire.jar`

```powershell
.\tools\build.ps1 -StsDir "C:\Program Files (x86)\Steam\steamapps\common\SlayTheSpire" -JdkDir "C:\Java\jdk8"
```

脚本会从游戏 jar 提取中英文卡牌/遗物映射，合并 `guide/unduel-tiers.json` 的全卡池 T 度，重新生成 `CrawlGuide/data.json` 与 `GuideData.java`，编译并输出 `dist/crawlguide.jar`。

## 说明

- T 度是社区投票共识，不是官方数据；样本量为 85 位投票者、约 9.3K 次评分。
- 流派标签是规则推导，不是逐张手写，可能有偏差。
- 「卡组倾向」是启发式打分（核心件 4 分、关键件 2 分），仅作参考。
- 使用 mod 启动会禁用游戏成就。
- 本项目与 Mega Crit Games 无关；卡牌与遗物名称、图片版权归其所有。
