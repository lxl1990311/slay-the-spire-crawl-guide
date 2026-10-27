package crawlguide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* Generated from the Crawl Guide HTML. Do not edit by hand. */
public final class GuideData {
    public static final class Build {
        public final String key;
        public final String charId;
        public final String tier;
        public final String nameCn;
        public final String nameEn;
        public final String shortCn;
        public final String shortEn;
        public final String[] core;
        public final String[] keys;

        Build(String key, String charId, String tier, String nameCn, String nameEn,
              String shortCn, String shortEn, String[] core, String[] keys) {
            this.key = key;
            this.charId = charId;
            this.tier = tier;
            this.nameCn = nameCn;
            this.nameEn = nameEn;
            this.shortCn = shortCn;
            this.shortEn = shortEn;
            this.core = core;
            this.keys = keys;
        }
    }

    public static final class Card {
        public final String id;
        public final String name;
        public final String cn;
        public final String tier;
        public final String[] builds;

        Card(String id, String name, String cn, String tier, String[] builds) {
            this.id = id;
            this.name = name;
            this.cn = cn;
            this.tier = tier;
            this.builds = builds;
        }
    }

    public static final class Relic {
        public final String id;
        public final String name;
        public final String cn;
        public final String rarity;
        public final String tier;
        public final String[] builds;

        Relic(String id, String name, String cn, String rarity, String tier, String[] builds) {
            this.id = id;
            this.name = name;
            this.cn = cn;
            this.rarity = rarity;
            this.tier = tier;
            this.builds = builds;
        }
    }

    public static final List<Build> BUILDS = new ArrayList<Build>();
    public static final Map<String, Build> BUILD_BY_KEY = new HashMap<String, Build>();
    public static final Map<String, Card> CARDS = new HashMap<String, Card>();
    public static final Map<String, Relic> RELICS = new HashMap<String, Relic>();

    static {
        addBuild("i|腐化消耗流", "i", "T0", "腐化消耗流", "Corruption Exhaust", "消耗", "Exhaust", new String[]{"Corruption", "Dark Embrace", "Feel No Pain"}, new String[]{"Battle Trance", "Burning Pact", "Shrug It Off", "True Grit", "Sentinel"});
        addBuild("i|力量流", "i", "T1", "力量流", "Strength Stack", "力量", "Strength", new String[]{"Demon Form", "Inflame", "Spot Weakness", "Limit Break"}, new String[]{"Heavy Blade", "Whirlwind", "Sword Boomerang", "Twin Strike", "Flex"});
        addBuild("i|格挡堡垒流", "i", "T2", "格挡堡垒流", "Barricade · Body Slam", "格挡", "Block", new String[]{"Barricade", "Entrench", "Body Slam", "Juggernaut"}, new String[]{"Metallicize", "True Grit", "Shrug It Off", "Flame Barrier"});
        addBuild("i|撕裂自伤流", "i", "T3", "撕裂自伤流", "Rupture · Self-Damage", "自伤", "SelfDmg", new String[]{"Rupture", "Bloodletting", "Offering", "Berserk"}, new String[]{"Reaper", "Twin Strike"});
        addBuild("s|毒流", "s", "T0", "毒流", "Poison", "毒", "Poison", new String[]{"Noxious Fumes", "Catalyst", "Deadly Poison", "Bouncing Flask"}, new String[]{"Corpse Explosion", "Envenom", "Wraith Form v2", "Backflip", "Acrobatics", "Burst"});
        addBuild("s|小刀流", "s", "T1", "小刀流", "Shiv", "小刀", "Shiv", new String[]{"Blade Dance", "Infinite Blades", "Accuracy", "Finisher"}, new String[]{"Cloak And Dagger", "Storm of Steel", "After Image", "Envenom", "A Thousand Cuts"});
        addBuild("s|弃牌流", "s", "T1", "弃牌流", "Discard", "弃牌", "Discard", new String[]{"Acrobatics", "Calculated Gamble", "Tactician", "Reflex"}, new String[]{"Eviscerate", "Grand Finale", "Well Laid Plans", "Prepared", "Adrenaline"});
        addBuild("s|无限流", "s", "T2", "无限流", "Infinite Draw", "无限", "Infinite", new String[]{"Acrobatics", "Reflex", "Tactician", "Backflip"}, new String[]{"Adrenaline", "Well Laid Plans"});
        addBuild("d|冰霜坦克流", "d", "T0", "冰霜坦克流", "Frost Orb", "冰霜", "Frost", new String[]{"Glacier", "Coolheaded", "Blizzard", "Capacitor", "Defragment"}, new String[]{"Chill", "Echo Form", "Buffer", "Loop", "Redo", "Multi-Cast"});
        addBuild("d|闪电球流", "d", "T1", "闪电球流", "Lightning Orb", "闪电", "Lightning", new String[]{"Electrodynamics", "Zap", "Ball Lightning", "Thunder Strike"}, new String[]{"Storm", "Defragment", "Capacitor", "Steam Power", "Conserve Battery"});
        addBuild("d|能力流", "d", "T2", "能力流", "Power / Creative AI", "能力", "Power", new String[]{"Creative AI", "Heatsinks", "Echo Form", "Buffer"}, new String[]{"Biased Cognition", "Capacitor", "Machine Learning", "Fusion"});
        addBuild("d|暗球 / 爪击流", "d", "T3", "暗球 / 爪击流", "Dark Orb · Claw", "暗球", "Dark/Claw", new String[]{"Darkness", "Multi-Cast", "Gash", "All For One"}, new String[]{"Redo", "Loop", "Steam", "Reboot", "Turbo"});
        addBuild("w|姿态循环无限流", "w", "T0", "姿态循环无限流", "Stance Infinite", "姿态无限", "StanceLoop", new String[]{"Eruption", "InnerPeace", "FearNoEvil", "Adaptation"}, new String[]{"MentalFortress", "TalkToTheHand", "EmptyMind", "Vault", "Scrawl"});
        addBuild("w|姿态舞（常规）", "w", "T1", "姿态舞（常规）", "Stance Dance", "姿态舞", "StanceDance", new String[]{"Eruption", "Vigilance", "Tantrum", "EmptyFist"}, new String[]{"FlurryOfBlows", "TalkToTheHand", "MentalFortress", "Meditate", "LikeWater"});
        addBuild("w|神格真言流", "w", "T2", "神格真言流", "Divinity · Mantra", "真言", "Divinity", new String[]{"Devotion", "Worship", "Pray", "Prostrate", "Brilliance"}, new String[]{"Ragnarok", "Blasphemy", "DevaForm", "Omniscience", "Wallop"});
        addBuild("w|点穴 / 保留流", "w", "T3", "点穴 / 保留流", "Pressure Points · Retain", "点穴 / 保留流", "Pressure Points · Retain", new String[]{"PathToVictory", "SandsOfTime", "Meditate", "Establishment"}, new String[]{"Perseverance", "SignatureMove", "WaveOfTheHand", "Omniscience"});

        addCard("Barricade", "Barricade", "壁垒", "T1", new String[]{"i|格挡堡垒流"});
        addCard("Battle Trance", "Battle Trance", "战斗专注", "T1", new String[]{"i|腐化消耗流"});
        addCard("Berserk", "Berserk", "狂暴", "T3", new String[]{"i|撕裂自伤流"});
        addCard("Bloodletting", "Bloodletting", "放血", "T3", new String[]{"i|撕裂自伤流"});
        addCard("Body Slam", "Body Slam", "全身撞击", "T1", new String[]{"i|格挡堡垒流"});
        addCard("Burning Pact", "Burning Pact", "燃烧契约", "T2", new String[]{"i|腐化消耗流"});
        addCard("Corruption", "Corruption", "腐化", "T0", new String[]{"i|腐化消耗流"});
        addCard("Dark Embrace", "Dark Embrace", "黑暗之拥", "T0", new String[]{"i|腐化消耗流"});
        addCard("Demon Form", "Demon Form", "恶魔形态", "T0", new String[]{"i|力量流"});
        addCard("Entrench", "Entrench", "巩固", "T1", new String[]{"i|格挡堡垒流"});
        addCard("Feel No Pain", "Feel No Pain", "无惧疼痛", "T0", new String[]{"i|腐化消耗流"});
        addCard("Flame Barrier", "Flame Barrier", "火焰屏障", "T2", new String[]{"i|格挡堡垒流"});
        addCard("Flex", "Flex", "活动肌肉", "T2", new String[]{"i|力量流"});
        addCard("Heavy Blade", "Heavy Blade", "重刃", "T1", new String[]{"i|力量流"});
        addCard("Inflame", "Inflame", "燃烧", "T1", new String[]{"i|力量流"});
        addCard("Juggernaut", "Juggernaut", "势不可当", "T2", new String[]{"i|格挡堡垒流"});
        addCard("Limit Break", "Limit Break", "突破极限", "T0", new String[]{"i|力量流"});
        addCard("Metallicize", "Metallicize", "金属化", "T2", new String[]{"i|格挡堡垒流"});
        addCard("Offering", "Offering", "祭品", "T1", new String[]{"i|撕裂自伤流"});
        addCard("Reaper", "Reaper", "死亡收割", "T1", new String[]{"i|撕裂自伤流"});
        addCard("Rupture", "Rupture", "撕裂", "T3", new String[]{"i|撕裂自伤流"});
        addCard("Sentinel", "Sentinel", "哨卫", "T2", new String[]{"i|腐化消耗流"});
        addCard("Shrug It Off", "Shrug It Off", "耸肩无视", "T1", new String[]{"i|腐化消耗流", "i|格挡堡垒流"});
        addCard("Spot Weakness", "Spot Weakness", "观察弱点", "T1", new String[]{"i|力量流"});
        addCard("Sword Boomerang", "Sword Boomerang", "飞剑回旋镖", "T3", new String[]{"i|力量流"});
        addCard("True Grit", "True Grit", "坚毅", "T2", new String[]{"i|腐化消耗流", "i|格挡堡垒流"});
        addCard("Twin Strike", "Twin Strike", "双重打击", "T2", new String[]{"i|力量流", "i|撕裂自伤流"});
        addCard("Whirlwind", "Whirlwind", "旋风斩", "T1", new String[]{"i|力量流"});
        addCard("Accuracy", "Accuracy", "精准", "T1", new String[]{"s|小刀流"});
        addCard("Acrobatics", "Acrobatics", "杂技", "T2", new String[]{"s|毒流", "s|弃牌流", "s|无限流"});
        addCard("Adrenaline", "Adrenaline", "肾上腺素", "T1", new String[]{"s|弃牌流", "s|无限流"});
        addCard("After Image", "After Image", "余像", "T0", new String[]{"s|小刀流"});
        addCard("Backflip", "Backflip", "后空翻", "T1", new String[]{"s|毒流", "s|无限流"});
        addCard("Blade Dance", "Blade Dance", "刀刃之舞", "T1", new String[]{"s|小刀流"});
        addCard("Bouncing Flask", "Bouncing Flask", "弹跳药瓶", "T1", new String[]{"s|毒流"});
        addCard("Burst", "Burst", "爆发", "T2", new String[]{"s|毒流"});
        addCard("Calculated Gamble", "Calculated Gamble", "计算下注", "T2", new String[]{"s|弃牌流"});
        addCard("Catalyst", "Catalyst", "催化剂", "T0", new String[]{"s|毒流"});
        addCard("Cloak And Dagger", "Cloak and Dagger", "斗篷与匕首", "T2", new String[]{"s|小刀流"});
        addCard("Corpse Explosion", "Corpse Explosion", "尸爆", "T1", new String[]{"s|毒流"});
        addCard("Deadly Poison", "Deadly Poison", "致命毒药", "T1", new String[]{"s|毒流"});
        addCard("Envenom", "Envenom", "涂毒", "T2", new String[]{"s|毒流", "s|小刀流"});
        addCard("Eviscerate", "Eviscerate", "内脏切除", "T3", new String[]{"s|弃牌流"});
        addCard("Finisher", "Finisher", "终结技", "T2", new String[]{"s|小刀流"});
        addCard("Grand Finale", "Grand Finale", "华丽收场", "T3", new String[]{"s|弃牌流"});
        addCard("Infinite Blades", "Infinite Blades", "无限刀刃", "T1", new String[]{"s|小刀流"});
        addCard("Noxious Fumes", "Noxious Fumes", "毒雾", "T0", new String[]{"s|毒流"});
        addCard("Prepared", "Prepared", "早有准备", "T3", new String[]{"s|弃牌流"});
        addCard("Reflex", "Reflex", "本能反应", "T2", new String[]{"s|弃牌流", "s|无限流"});
        addCard("Storm of Steel", "Storm of Steel", "钢铁风暴", "T2", new String[]{"s|小刀流"});
        addCard("Tactician", "Tactician", "战术大师", "T1", new String[]{"s|弃牌流", "s|无限流"});
        addCard("A Thousand Cuts", "Thousand Cuts", "凌迟", "T1", new String[]{"s|小刀流"});
        addCard("Well Laid Plans", "Well-Laid Plans", "计划妥当", "T1", new String[]{"s|弃牌流", "s|无限流"});
        addCard("Wraith Form v2", "Wraith Form", "幽魂形态", "T0", new String[]{"s|毒流"});
        addCard("All For One", "All For One", "万物一心", "T2", new String[]{"d|暗球 / 爪击流"});
        addCard("Ball Lightning", "Ball Lightning", "球状闪电", "T2", new String[]{"d|闪电球流"});
        addCard("Biased Cognition", "Biased Cognition", "偏差认知", "T0", new String[]{"d|能力流"});
        addCard("Blizzard", "Blizzard", "暴雪", "T1", new String[]{"d|冰霜坦克流"});
        addCard("Buffer", "Buffer", "缓冲", "T1", new String[]{"d|冰霜坦克流", "d|能力流"});
        addCard("Capacitor", "Capacitor", "扩容", "T1", new String[]{"d|冰霜坦克流", "d|闪电球流", "d|能力流"});
        addCard("Conserve Battery", "Charge Battery", "充电", "T2", new String[]{"d|闪电球流"});
        addCard("Chill", "Chill", "冰寒", "T2", new String[]{"d|冰霜坦克流"});
        addCard("Gash", "Claw", "爪击", "T2", new String[]{"d|暗球 / 爪击流"});
        addCard("Coolheaded", "Coolheaded", "冷静头脑", "T1", new String[]{"d|冰霜坦克流"});
        addCard("Darkness", "Darkness", "漆黑", "T2", new String[]{"d|暗球 / 爪击流"});
        addCard("Defragment", "Defragment", "碎片整理", "T0", new String[]{"d|冰霜坦克流", "d|闪电球流"});
        addCard("Echo Form", "Echo Form", "回响形态", "T0", new String[]{"d|冰霜坦克流", "d|能力流"});
        addCard("Electrodynamics", "Electrodynamics", "电动力学", "T0", new String[]{"d|闪电球流"});
        addCard("Fusion", "Fusion", "聚变", "T2", new String[]{"d|能力流"});
        addCard("Glacier", "Glacier", "冰川", "T1", new String[]{"d|冰霜坦克流"});
        addCard("Heatsinks", "Heatsinks", "散热片", "T1", new String[]{"d|能力流"});
        addCard("Loop", "Loop", "循环", "T1", new String[]{"d|冰霜坦克流", "d|暗球 / 爪击流"});
        addCard("Machine Learning", "Machine Learning", "机器学习", "T3", new String[]{"d|能力流"});
        addCard("Multi-Cast", "Multi-Cast", "多重释放", "T1", new String[]{"d|冰霜坦克流", "d|暗球 / 爪击流"});
        addCard("Steam Power", "Overclock", "超频", "T3", new String[]{"d|闪电球流"});
        addCard("Reboot", "Reboot", "重启", "T3", new String[]{"d|暗球 / 爪击流"});
        addCard("Redo", "Recursion", "递归", "T2", new String[]{"d|冰霜坦克流", "d|暗球 / 爪击流"});
        addCard("Steam", "Steam Barrier", "蒸汽护壁", "T3", new String[]{"d|暗球 / 爪击流"});
        addCard("Storm", "Storm", "雷暴", "T2", new String[]{"d|闪电球流"});
        addCard("Thunder Strike", "Thunder Strike", "雷霆打击", "T2", new String[]{"d|闪电球流"});
        addCard("Turbo", "Turbo", "内核加速", "T2", new String[]{"d|暗球 / 爪击流"});
        addCard("Zap", "Zap", "电击", "T2", new String[]{"d|闪电球流"});
        addCard("Creative AI", "Creative AI", "创造性AI", "T1", new String[]{"d|能力流"});
        addCard("Blasphemy", "Blasphemy", "渎神", "T3", new String[]{"w|神格真言流"});
        addCard("Brilliance", "Brilliance", "光辉", "T1", new String[]{"w|神格真言流"});
        addCard("DevaForm", "Deva Form", "天人形态", "T3", new String[]{"w|神格真言流"});
        addCard("Devotion", "Devotion", "虔信", "T1", new String[]{"w|神格真言流"});
        addCard("EmptyFist", "Empty Fist", "化拳为空", "T1", new String[]{"w|姿态舞（常规）"});
        addCard("EmptyMind", "Empty Mind", "化智为空", "T1", new String[]{"w|姿态循环无限流"});
        addCard("Eruption", "Eruption", "暴怒", "T0", new String[]{"w|姿态循环无限流", "w|姿态舞（常规）"});
        addCard("Establishment", "Establishment", "确立基础", "T2", new String[]{"w|点穴 / 保留流"});
        addCard("FearNoEvil", "Fear No Evil", "不惧妖邪", "T1", new String[]{"w|姿态循环无限流"});
        addCard("FlurryOfBlows", "Flurry of Blows", "疾风连击", "T2", new String[]{"w|姿态舞（常规）"});
        addCard("InnerPeace", "Inner Peace", "内心宁静", "T1", new String[]{"w|姿态循环无限流"});
        addCard("LikeWater", "Like Water", "如水", "T2", new String[]{"w|姿态舞（常规）"});
        addCard("Meditate", "Meditate", "冥想", "T2", new String[]{"w|姿态舞（常规）", "w|点穴 / 保留流"});
        addCard("MentalFortress", "Mental Fortress", "心灵堡垒", "T0", new String[]{"w|姿态循环无限流", "w|姿态舞（常规）"});
        addCard("Omniscience", "Omniscience", "通晓万物", "T1", new String[]{"w|神格真言流", "w|点穴 / 保留流"});
        addCard("Perseverance", "Perseverance", "坚韧", "T2", new String[]{"w|点穴 / 保留流"});
        addCard("Pray", "Pray", "祈祷", "T2", new String[]{"w|神格真言流"});
        addCard("PathToVictory", "Pressure Points", "点穴", "T2", new String[]{"w|点穴 / 保留流"});
        addCard("Prostrate", "Prostrate", "五体投地", "T3", new String[]{"w|神格真言流"});
        addCard("Ragnarok", "Ragnarok", "诸神之黄昏", "T3", new String[]{"w|神格真言流"});
        addCard("Adaptation", "Rushdown", "猛虎下山", "T0", new String[]{"w|姿态循环无限流"});
        addCard("SandsOfTime", "Sands of Time", "时之沙", "T2", new String[]{"w|点穴 / 保留流"});
        addCard("Scrawl", "Scrawl", "潦草急就", "T0", new String[]{"w|姿态循环无限流"});
        addCard("SignatureMove", "Signature Move", "招牌技", "T3", new String[]{"w|点穴 / 保留流"});
        addCard("Tantrum", "Tantrum", "发泄", "T1", new String[]{"w|姿态舞（常规）"});
        addCard("TalkToTheHand", "Talk to the Hand", "以手拒之", "T0", new String[]{"w|姿态循环无限流", "w|姿态舞（常规）"});
        addCard("Vault", "Vault", "腾跃", "T1", new String[]{"w|姿态循环无限流"});
        addCard("Vigilance", "Vigilance", "警惕", "T0", new String[]{"w|姿态舞（常规）"});
        addCard("Wallop", "Wallop", "当头棒喝", "T2", new String[]{"w|神格真言流"});
        addCard("WaveOfTheHand", "Wave of the Hand", "摆手", "T2", new String[]{"w|点穴 / 保留流"});
        addCard("Worship", "Worship", "敬拜", "T1", new String[]{"w|神格真言流"});

        addRelic("Akabeko", "Akabeko", "赤牛", "普通", "T2", new String[]{"w|姿态舞（常规）", "i|力量流"});
        addRelic("Anchor", "Anchor", "锚", "普通", "T2", new String[0]);
        addRelic("Bird Faced Urn", "Bird-Faced Urn", "鸟面瓮", "稀有", "T2", new String[0]);
        addRelic("Blood Vial", "Blood Vial", "小血瓶", "普通", "T2", new String[0]);
        addRelic("Bronze Scales", "Bronze Scales", "铜制鳞片", "普通", "T2", new String[0]);
        addRelic("Burning Blood", "Burning Blood", "燃烧之血", "初始", "T2", new String[0]);
        addRelic("Calipers", "Calipers", "外卡钳", "稀有", "T2", new String[0]);
        addRelic("Chemical X", "Chemical X", "化学物X", "商店", "T1", new String[]{"i|力量流", "d|暗球 / 爪击流"});
        addRelic("Charon's Ashes", "Charon's Ashes", "卡戎之灰", "稀有", "T1", new String[]{"i|腐化消耗流"});
        addRelic("Damaru", "Damaru", "手摇鼓", "普通", "T2", new String[]{"w|神格真言流"});
        addRelic("DataDisk", "Data Disk", "数据磁盘", "普通", "T1", new String[]{"d|冰霜坦克流", "d|闪电球流", "d|暗球 / 爪击流"});
        addRelic("Dead Branch", "Dead Branch", "枯木树枝", "稀有", "T0", new String[]{"i|腐化消耗流"});
        addRelic("Empty Cage", "Empty Cage", "空鸟笼", "Boss", "T2", new String[0]);
        addRelic("FossilizedHelix", "Fossilized Helix", "螺类化石", "稀有", "T2", new String[0]);
        addRelic("FrozenCore", "Frozen Core", "冻结核心", "Boss", "T2", new String[]{"d|冰霜坦克流"});
        addRelic("Cables", "Gold-Plated Cables", "镀金缆线", "罕见", "T2", new String[]{"d|冰霜坦克流", "d|暗球 / 爪击流"});
        addRelic("HornCleat", "Horn Cleat", "船夹板", "罕见", "T2", new String[0]);
        addRelic("HoveringKite", "Hovering Kite", "悬浮风筝", "Boss", "T2", new String[]{"s|弃牌流"});
        addRelic("Ice Cream", "Ice Cream", "冰淇淋", "稀有", "T1", new String[]{"w|神格真言流", "i|力量流", "d|冰霜坦克流"});
        addRelic("Incense Burner", "Incense Burner", "香炉", "稀有", "T1", new String[]{"s|毒流", "d|冰霜坦克流"});
        addRelic("Inserter", "Inserter", "机械臂", "Boss", "T2", new String[]{"d|冰霜坦克流"});
        addRelic("Kunai", "Kunai", "苦无", "罕见", "T1", new String[]{"s|小刀流", "s|弃牌流"});
        addRelic("Meat on the Bone", "Meat on the Bone", "带骨肉", "罕见", "T2", new String[0]);
        addRelic("Medical Kit", "Medical Kit", "医药箱", "商店", "T2", new String[0]);
        addRelic("Mummified Hand", "Mummified Hand", "干瘪之手", "罕见", "T1", new String[]{"d|能力流", "i|腐化消耗流"});
        addRelic("MutagenicStrength", "Mutagenic Strength", "突变之力", "事件", "T2", new String[0]);
        addRelic("Nuclear Battery", "Nuclear Battery", "核能电池", "Boss", "T2", new String[]{"d|能力流", "d|闪电球流"});
        addRelic("Ornamental Fan", "Ornamental Fan", "精致折扇", "罕见", "T2", new String[0]);
        addRelic("Paper Crane", "Paper Krane", "纸鹤", "罕见", "T2", new String[]{"s|毒流", "s|弃牌流"});
        addRelic("Philosopher's Stone", "Philosopher's Stone", "贤者之石", "Boss", "T3", new String[0]);
        addRelic("Pocketwatch", "Pocketwatch", "怀表", "稀有", "T0", new String[0]);
        addRelic("Question Card", "Question Card", "问号牌", "罕见", "T1", new String[0]);
        addRelic("Red Skull", "Red Skull", "红头骨", "普通", "T2", new String[]{"i|力量流", "i|撕裂自伤流"});
        addRelic("Runic Capacitor", "Runic Capacitor", "符文电容器", "商店", "T1", new String[]{"d|冰霜坦克流", "d|闪电球流"});
        addRelic("Runic Cube", "Runic Cube", "符文立方体", "Boss", "T3", new String[]{"i|撕裂自伤流"});
        addRelic("Runic Dome", "Runic Dome", "符文圆顶", "Boss", "T1", new String[0]);
        addRelic("Runic Pyramid", "Runic Pyramid", "符文金字塔", "Boss", "T0", new String[0]);
        addRelic("SacredBark", "Sacred Bark", "神圣树皮", "Boss", "T3", new String[0]);
        addRelic("Self Forming Clay", "Self-Forming Clay", "自成型黏土", "罕见", "T2", new String[]{"i|撕裂自伤流"});
        addRelic("Shuriken", "Shuriken", "手里剑", "罕见", "T1", new String[]{"s|小刀流", "i|力量流"});
        addRelic("Snake Skull", "Snecko Skull", "异蛇头骨", "普通", "T2", new String[]{"s|毒流"});
        addRelic("Symbiotic Virus", "Symbiotic Virus", "共生病毒", "罕见", "T2", new String[]{"d|暗球 / 爪击流"});
        addRelic("TeardropLocket", "Teardrop Locket", "泪滴吊坠盒", "罕见", "T2", new String[]{"w|姿态舞（常规）", "w|姿态循环无限流"});
        addRelic("The Specimen", "The Specimen", "生物样本", "稀有", "T2", new String[]{"s|毒流"});
        addRelic("Tingsha", "Tingsha", "铜钹", "稀有", "T2", new String[]{"s|弃牌流"});
        addRelic("Tough Bandages", "Tough Bandages", "结实绷带", "稀有", "T1", new String[]{"s|弃牌流"});
        addRelic("TungstenRod", "Tungsten Rod", "钨合金棍", "稀有", "T1", new String[0]);
        addRelic("TwistedFunnel", "Twisted Funnel", "扭曲漏斗", "商店", "T2", new String[]{"s|毒流"});
        addRelic("Unceasing Top", "Unceasing Top", "不休陀螺", "稀有", "T2", new String[0]);
        addRelic("Vajra", "Vajra", "金刚杵", "普通", "T2", new String[0]);
        addRelic("VioletLotus", "Violet Lotus", "紫色莲花", "Boss", "T1", new String[]{"w|姿态循环无限流", "w|姿态舞（常规）"});
        addRelic("WristBlade", "Wrist Blade", "袖剑", "Boss", "T2", new String[]{"s|小刀流"});
        addRelic("Snecko Eye", "Snecko Eye", "异蛇之眼", "Boss", "T0", new String[0]);
        addRelic("OrangePellets", "Orange Pellets", "橙色药丸", "商店", "T0", new String[0]);
        addRelic("Gambling Chip", "Gambling Chip", "赌博筹码", "稀有", "T1", new String[0]);
        addRelic("Bag of Preparation", "Bag of Preparation", "准备背包", "普通", "T1", new String[0]);
        addRelic("Torii", "Torii", "鸟居", "稀有", "T1", new String[0]);
        addRelic("Pandora's Box", "Pandora's Box", "潘多拉魔盒", "Boss", "T1", new String[0]);
        addRelic("Astrolabe", "Astrolabe", "星盘", "Boss", "T1", new String[0]);
        addRelic("Orrery", "Orrery", "星系仪", "商店", "T1", new String[0]);
        addRelic("Coffee Dripper", "Coffee Dripper", "咖啡滤杯", "Boss", "T1", new String[0]);
        addRelic("Fusion Hammer", "Fusion Hammer", "融合之锤", "Boss", "T1", new String[0]);
        addRelic("Ring of the Serpent", "Ring of the Serpent", "长蛇戒指", "Boss", "T1", new String[0]);
        addRelic("Black Blood", "Black Blood", "黑暗之血", "Boss", "T1", new String[0]);
        addRelic("Bag of Marbles", "Bag of Marbles", "弹珠袋", "普通", "T2", new String[0]);
        addRelic("Lantern", "Lantern", "灯笼", "普通", "T2", new String[0]);
        addRelic("Black Star", "Black Star", "黑星", "Boss", "T2", new String[0]);
        addRelic("Calling Bell", "Calling Bell", "召唤铃铛", "Boss", "T2", new String[0]);
        addRelic("PreservedInsect", "Preserved Insect", "昆虫标本", "普通", "T2", new String[0]);
        addRelic("White Beast Statue", "White Beast Statue", "白兽雕像", "罕见", "T2", new String[0]);
        addRelic("DollysMirror", "Dolly's Mirror", "多利之镜", "商店", "T2", new String[0]);
        addRelic("Paper Frog", "Paper Phrog", "纸蛙", "罕见", "T2", new String[]{"i|力量流"});
        addRelic("Bloody Idol", "Bloody Idol", "鲜血神像", "事件", "T2", new String[0]);
        addRelic("Singing Bowl", "Singing Bowl", "颂钵", "罕见", "T2", new String[0]);
        addRelic("Frozen Eye", "Frozen Eye", "冻结之眼", "商店", "T2", new String[0]);
        addRelic("Nunchaku", "Nunchaku", "双节棍", "普通", "T2", new String[0]);
        addRelic("Pen Nib", "Pen Nib", "钢笔尖", "普通", "T2", new String[0]);
        addRelic("Happy Flower", "Happy Flower", "开心小花", "普通", "T2", new String[0]);
        addRelic("Pantograph", "Pantograph", "缩放仪", "罕见", "T2", new String[0]);
        addRelic("Prayer Wheel", "Prayer Wheel", "转经轮", "稀有", "T2", new String[0]);
        addRelic("Lizard Tail", "Lizard Tail", "蜥蜴尾巴", "稀有", "T2", new String[0]);
        addRelic("CeramicFish", "Ceramic Fish", "陶瓷小鱼", "普通", "T3", new String[0]);
        addRelic("Boot", "The Boot", "发条靴", "普通", "T3", new String[0]);
        addRelic("Darkstone Periapt", "Darkstone Periapt", "黑石护符", "罕见", "T3", new String[0]);
        addRelic("Blue Candle", "Blue Candle", "蓝蜡烛", "罕见", "T3", new String[0]);
        addRelic("HandDrill", "Hand Drill", "手钻", "商店", "T3", new String[0]);
        addRelic("SsserpentHead", "Ssserpent Head", "蛇的头", "事件", "T3", new String[0]);
        addRelic("Red Mask", "Red Mask", "红面具", "事件", "T3", new String[0]);
        addRelic("Tiny House", "Tiny House", "小屋子", "Boss", "T3", new String[0]);
        addRelic("Mark of Pain", "Mark of Pain", "痛楚印记", "Boss", "T3", new String[0]);
        addRelic("Busted Crown", "Busted Crown", "破碎金冠", "Boss", "T3", new String[0]);
        addRelic("Sozu", "Sozu", "添水", "Boss", "T3", new String[0]);
        addRelic("Velvet Choker", "Velvet Choker", "天鹅绒颈圈", "Boss", "T3", new String[0]);
        addRelic("Cursed Key", "Cursed Key", "诅咒钥匙", "Boss", "T3", new String[0]);
        addRelic("Ectoplasm", "Ectoplasm", "灵体外质", "Boss", "T3", new String[0]);
        addRelic("PrismaticShard", "Prismatic Shard", "棱镜碎片", "商店", "T3", new String[0]);
        addRelic("Brimstone", "Brimstone", "硫磺", "商店", "T3", new String[]{"i|力量流"});
        addRelic("Necronomicon", "Necronomicon", "死灵之书", "事件", "T3", new String[0]);
        addRelic("Ring of the Snake", "Ring of the Snake", "蛇之戒指", "初始", "T2", new String[0]);
        addRelic("PureWater", "Pure Water", "至纯之水", "初始", "T2", new String[0]);
        addRelic("Cracked Core", "Cracked Core", "破损核心", "初始", "T2", new String[0]);
        addRelic("Centennial Puzzle", "Centennial Puzzle", "百年积木", "普通", "T2", new String[0]);
        addRelic("Dream Catcher", "Dream Catcher", "捕梦网", "普通", "T2", new String[0]);
        addRelic("Oddly Smooth Stone", "Oddly Smooth Stone", "意外光滑的石头", "普通", "T2", new String[0]);
        addRelic("Omamori", "Omamori", "御守", "普通", "T3", new String[0]);
        addRelic("Orichalcum", "Orichalcum", "奥利哈钢", "普通", "T2", new String[0]);
        addRelic("Regal Pillow", "Regal Pillow", "皇家枕头", "普通", "T3", new String[0]);
        addRelic("Smiling Mask", "Smiling Mask", "微笑面具", "普通", "T3", new String[0]);
        addRelic("Strawberry", "Strawberry", "草莓", "普通", "T3", new String[0]);
        addRelic("Potion Belt", "Potion Belt", "药水腰带", "普通", "T2", new String[0]);
        addRelic("Tiny Chest", "Tiny Chest", "小宝箱", "普通", "T3", new String[0]);
        addRelic("Whetstone", "Whetstone", "磨刀石", "普通", "T3", new String[0]);
        addRelic("MealTicket", "Meal Ticket", "餐券", "普通", "T3", new String[0]);
        addRelic("MawBank", "Maw Bank", "巨口储蓄罐", "普通", "T2", new String[0]);
        addRelic("War Paint", "War Paint", "战纹涂料", "普通", "T3", new String[0]);
        addRelic("Toy Ornithopter", "Toy Ornithopter", "玩具扑翼飞机", "普通", "T2", new String[0]);
        addRelic("Juzu Bracelet", "Juzu Bracelet", "佛珠手链", "普通", "T3", new String[0]);
        addRelic("Ancient Tea Set", "Ancient Tea Set", "古茶具套装", "普通", "T2", new String[0]);
        addRelic("Art of War", "Art of War", "孙子兵法", "普通", "T2", new String[0]);
        addRelic("Sling", "Sling of Courage", "勇气投石索", "商店", "T2", new String[0]);
        addRelic("The Courier", "The Courier", "送货员", "罕见", "T2", new String[0]);
        addRelic("Bottled Flame", "Bottled Flame", "瓶装火焰", "罕见", "T2", new String[0]);
        addRelic("Bottled Lightning", "Bottled Lightning", "瓶装闪电", "罕见", "T2", new String[0]);
        addRelic("Bottled Tornado", "Bottled Tornado", "瓶装旋风", "罕见", "T2", new String[0]);
        addRelic("Eternal Feather", "Eternal Feather", "永恒羽毛", "罕见", "T3", new String[0]);
        addRelic("Frozen Egg 2", "Frozen Egg", "冻结之蛋", "罕见", "T2", new String[0]);
        addRelic("Gremlin Horn", "Gremlin Horn", "地精之角", "罕见", "T2", new String[0]);
        addRelic("Letter Opener", "Letter Opener", "开信刀", "罕见", "T3", new String[0]);
        addRelic("Matryoshka", "Matryoshka", "套娃", "罕见", "T3", new String[0]);
        addRelic("Mercury Hourglass", "Mercury Hourglass", "水银沙漏", "罕见", "T2", new String[0]);
        addRelic("Molten Egg 2", "Molten Egg", "熔火之蛋", "罕见", "T2", new String[0]);
        addRelic("Ninja Scroll", "Ninja Scroll", "忍术卷轴", "罕见", "T3", new String[0]);
        addRelic("Pear", "Pear", "梨子", "罕见", "T3", new String[0]);
        addRelic("Yang", "Duality", "两仪", "罕见", "T3", new String[0]);
        addRelic("InkBottle", "Ink Bottle", "墨水瓶", "罕见", "T2", new String[0]);
        addRelic("StrikeDummy", "Strike Dummy", "打击木偶", "罕见", "T3", new String[0]);
        addRelic("Sundial", "Sundial", "日晷", "罕见", "T2", new String[0]);
        addRelic("StoneCalendar", "Stone Calendar", "历石", "稀有", "T3", new String[0]);
        addRelic("WingedGreaves", "Wing Boots", "羽翼之靴", "稀有", "T2", new String[0]);
        addRelic("CloakClasp", "Cloak Clasp", "斗篷扣", "稀有", "T2", new String[0]);
        addRelic("CaptainsWheel", "Captain's Wheel", "舵盘", "稀有", "T3", new String[0]);
        addRelic("Champion Belt", "Champion Belt", "冠军腰带", "稀有", "T3", new String[0]);
        addRelic("Ginger", "Ginger", "生姜", "稀有", "T2", new String[0]);
        addRelic("Girya", "Girya", "壶铃", "稀有", "T2", new String[0]);
        addRelic("Magic Flower", "Magic Flower", "魔法花", "稀有", "T2", new String[0]);
        addRelic("Mango", "Mango", "芒果", "稀有", "T3", new String[0]);
        addRelic("Old Coin", "Old Coin", "古钱币", "稀有", "T2", new String[0]);
        addRelic("Peace Pipe", "Peace Pipe", "宁静烟斗", "稀有", "T2", new String[0]);
        addRelic("Shovel", "Shovel", "铲子", "稀有", "T2", new String[0]);
        addRelic("Turnip", "Turnip", "萝卜", "稀有", "T2", new String[0]);
        addRelic("SlaversCollar", "Slaver's Collar", "奴隶贩子颈环", "Boss", "T1", new String[0]);
        addRelic("HolyWater", "Holy Water", "圣水", "Boss", "T1", new String[0]);
        addRelic("Nilry's Codex", "Nilry's Codex", "尼利的宝典", "事件", "T1", new String[0]);
        addRelic("Golden Idol", "Golden Idol", "金神像", "事件", "T2", new String[0]);
        addRelic("NeowsBlessing", "Neow's Lament", "涅奥的悲恸", "事件", "T2", new String[0]);
        addRelic("Enchiridion", "Enchiridion", "英雄宝典", "事件", "T2", new String[0]);
        addRelic("Nloth's Gift", "N'loth's Gift", "恩洛斯的礼物", "事件", "T2", new String[0]);
        addRelic("NlothsMask", "N'loth's Hungry Face", "恩洛斯的饥饿的脸", "事件", "T3", new String[0]);
        addRelic("Odd Mushroom", "Odd Mushroom", "奇怪蘑菇", "事件", "T2", new String[0]);
        addRelic("Lee's Waffle", "Lee's Waffle", "李家华夫饼", "商店", "T3", new String[0]);
        addRelic("Mark of the Bloom", "Mark of the Bloom", "绽放印记", "事件", "T3", new String[0]);
        addRelic("Cauldron", "Cauldron", "大锅", "商店", "T2", new String[0]);
        addRelic("Membership Card", "Membership Card", "会员卡", "商店", "T1", new String[0]);
        addRelic("Strange Spoon", "Strange Spoon", "奇怪的勺子", "商店", "T2", new String[0]);
        addRelic("Toolbox", "Toolbox", "工具箱", "商店", "T2", new String[0]);
        addRelic("TheAbacus", "The Abacus", "算盘", "商店", "T2", new String[0]);
        addRelic("Toxic Egg 2", "Toxic Egg", "毒素之蛋", "罕见", "T2", new String[0]);
        addRelic("GoldenEye", "Golden Eye", "黄金眼", "稀有", "T2", new String[0]);
        addRelic("Du-Vu Doll", "Du-Vu Doll", "毒巫娃娃", "稀有", "T3", new String[0]);
        addRelic("Emotion Chip", "Emotion Chip", "情感芯片", "稀有", "T2", new String[0]);
        addRelic("Thread and Needle", "Thread and Needle", "针线", "稀有", "T2", new String[0]);
        addRelic("Spirit Poop", "Spirit Poop", "精灵便便", "事件", "T3", new String[0]);
        addRelic("FaceOfCleric", "Face of Cleric", "牧师的脸", "事件", "T2", new String[0]);
        addRelic("CultistMask", "Cultist Headpiece", "邪教徒头套", "事件", "T3", new String[0]);
        addRelic("GremlinMask", "Gremlin Visage", "地精容貌", "事件", "T3", new String[0]);
        addRelic("WarpedTongs", "Warped Tongs", "弯曲铁钳", "事件", "T2", new String[0]);
        addRelic("ClockworkSouvenir", "Clockwork Souvenir", "齿轮工艺品", "商店", "T1", new String[0]);
        addRelic("Melange", "Melange", "美琅脂", "商店", "T2", new String[0]);
        addRelic("Circlet", "Circlet", "头环", "特殊", "T3", new String[0]);
    }

    private GuideData() {
    }

    private static void addBuild(String key, String charId, String tier, String nameCn, String nameEn,
                                  String shortCn, String shortEn, String[] core, String[] keys) {
        Build b = new Build(key, charId, tier, nameCn, nameEn, shortCn, shortEn, core, keys);
        BUILDS.add(b);
        BUILD_BY_KEY.put(key, b);
    }

    private static void addCard(String id, String name, String cn, String tier, String[] builds) {
        CARDS.put(id, new Card(id, name, cn, tier, builds));
    }

    private static void addRelic(String id, String name, String cn, String rarity, String tier, String[] builds) {
        RELICS.put(id, new Relic(id, name, cn, rarity, tier, builds));
    }
}
