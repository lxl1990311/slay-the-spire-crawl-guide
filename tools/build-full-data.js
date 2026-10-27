/*
 * Builds CrawlGuide/data.json for the full Slay the Spire 1 card pool.
 *
 * Single tier source: Unduel community tier list (85 voters / 9.3K votes),
 * S/A/B/C/D/F mapped to T0/T1/T2/T3. No guide-card exceptions.
 * Archetype tags: derived from Slay the Spire 1 card text with the 16 build
 * definitions kept in the guide. No Slay the Spire 2 data is used.
 */
const fs = require('fs');
const path = require('path');

const basePath = process.argv[2] || 'CrawlGuide/data.json';
const tiersPath = process.argv[3] || 'guide/unduel-tiers.json';
const locDir = process.argv[4] || '.build/localization';

const base = JSON.parse(fs.readFileSync(basePath, 'utf8'));
const tiers = JSON.parse(fs.readFileSync(tiersPath, 'utf8'));
const eng = JSON.parse(fs.readFileSync(path.join(locDir, 'cards.json.eng.json'), 'utf8'));
const zhs = JSON.parse(fs.readFileSync(path.join(locDir, 'cards.json.zhs.json'), 'utf8'));

const norm = s => String(s).toLowerCase().replace(/[^a-z0-9]/g, '');
const nameToId = {};
const idNorm = {};
for (const [id, v] of Object.entries(eng)) {
  nameToId[norm(v.NAME)] = id;
  idNorm[norm(id)] = id;
}
const officialName = id => (eng[id] && eng[id].NAME) || id;
const cnName = id => (zhs[id] && zhs[id].NAME) || officialName(id);
const description = id => (eng[id] && eng[id].DESCRIPTION) || '';

const buildKeys = new Set(base.builds.map(b => b.key));
const charOfTag = { Ironclad: 'i', Silent: 's', Defect: 'd', Watcher: 'w', Colorless: 'c' };

function mapTier(value) {
  if (value >= 6) return 'T0';
  if (value === 5) return 'T1';
  if (value === 4) return 'T2';
  return 'T3';
}

function classify(char, name, type, text) {
  const out = [];
  const add = key => {
    if (buildKeys.has(key) && out.indexOf(key) < 0) out.push(key);
  };
  if (char === 'c') return out;

  const n = String(name || '').toLowerCase();
  const t = String(text || '').toLowerCase();
  const is = (...names) => names.indexOf(n) >= 0;
  const isType = wanted => String(type || '').toLowerCase() === wanted;

  if (char === 'i') {
    if (/exhausted|exhaust (?:a|all|non-attack|your hand)|whenever you exhaust|exhaust pile/.test(t)
        || is('corruption', 'dark embrace', 'feel no pain', 'sentinel', 'burning pact', 'true grit',
              'second wind', 'fiend fire', 'exhume', 'sever soul')) {
      add('i|腐化消耗流');
    }
    if (/gain .*strength|strength.*(?:each|per|times)|strength by/.test(t)
        || is('limit break', 'demon form', 'inflame', 'spot weakness', 'flex', 'heavy blade',
              'sword boomerang', 'twin strike', 'whirlwind', 'pummel', 'bludgeon', 'reaper')
        || /deal .*damage .*times|deal .*damage twice/.test(t)) {
      add('i|力量流');
    }
    if (/gain .*block/.test(t)
        || is('barricade', 'entrench', 'body slam', 'juggernaut', 'metallicize', 'flame barrier',
              'impervious', 'ghostly armor', 'power through', 'armaments', 'rage')) {
      add('i|格挡堡垒流');
    }
    if (/lose .*hp|hp loss/.test(t)
        || is('rupture', 'bloodletting', 'offering', 'berserk', 'combust', 'hemokinesis',
              'brutality', 'blood for blood')) {
      add('i|撕裂自伤流');
    }
  }

  if (char === 's') {
    if (/poison/.test(t)) add('s|毒流');
    if (/shiv/.test(t)
        || is('accuracy', 'after image', 'finisher', 'thousand cuts', 'infinite blades',
              'blade dance', 'cloak and dagger', 'storm of steel')) {
      add('s|小刀流');
    }
    if (/discard/.test(t)
        || is('acrobatics', 'calculated gamble', 'tactician', 'reflex', 'eviscerate', 'prepared',
              'well-laid plans', 'concentrate', 'setup', 'dagger throw', 'sneaky strike', 'survivor',
              'tools of the trade')) {
      add('s|弃牌流');
    }
    if ((/draw|gain .*energy|return.*hand|cost 0/.test(t) && /draw|energy|return|cost/.test(t))
        || is('acrobatics', 'backflip', 'reflex', 'tactician', 'adrenaline', 'prepared',
              'calculated gamble', 'concentrate', 'setup', 'well-laid plans', 'grand finale',
              'doppelganger', 'expertise', 'escape plan', 'dagger throw', 'sneaky strike',
              'tools of the trade')) {
      add('s|无限流');
    }
  }

  if (char === 'd') {
    if (/frost/.test(t) || /gain .*focus/.test(t)
        || is('glacier', 'coolheaded', 'chill', 'blizzard', 'capacitor', 'defragment', 'buffer',
              'loop', 'recursion', 'multi-cast', 'echo form', 'biased cognition')
        || (/gain .*block/.test(t) && isType('skill'))) {
      add('d|冰霜坦克流');
    }
    if (/lightning/.test(t)
        || is('electrodynamics', 'zap', 'ball lightning', 'thunder strike', 'storm', 'tempest')) {
      add('d|闪电球流');
    }
    if (isType('power') || is('amplify')) add('d|能力流');
    if (/dark/.test(t) || /claw/.test(t) || /cost 0|0 cost/.test(t)
        || is('darkness', 'multi-cast', 'claw', 'all for one', 'recursion', 'loop', 'steam barrier',
              'reboot', 'turbo', 'overclock', 'scrape')) {
      add('d|暗球 / 爪击流');
    }
  }

  if (char === 'w') {
    const stance = /calm|wrath|stance/.test(t)
        || is('eruption', 'vigilance', 'inner peace', 'fear no evil', 'empty fist', 'empty mind',
              'tranquility', 'crescendo', 'indignation', 'rushdown', 'mental fortress', 'like water',
              'flurry of blows', 'tantrum', 'halt', 'empty body', 'simmering fury');
    if (stance) {
      add('w|姿态舞（常规）');
      if (/draw|energy|calm|exit|return this card/.test(t)
          || is('inner peace', 'fear no evil', 'empty fist', 'empty mind', 'rushdown',
                'mental fortress', 'like water', 'flurry of blows')) {
        add('w|姿态循环无限流');
      }
    }
    if (/mantra|divinity|divine/.test(t)
        || is('devotion', 'pray', 'prostrate', 'worship', 'blasphemy', 'brilliance')) {
      add('w|神格真言流');
    }
    if (/whenever a card is retained|retained|retain a card|retain up to/.test(t)
        || is('pressure points', 'perseverance', 'sands of time', 'establishment', 'meditate',
              'worship', 'windmill strike')) {
      add('w|点穴 / 保留流');
    }
    if (is('scrawl', 'vault', 'deva form')) add('w|姿态循环无限流');
  }

  return out;
}

const cards = {};
const unmapped = [];
for (const entry of tiers.cards) {
  const id = nameToId[norm(entry.name)] || idNorm[norm(entry.name)];
  if (!id) {
    unmapped.push(entry.name);
    continue;
  }
  const tags = entry.tags || [];
  const char = tags.map(t => charOfTag[t]).find(Boolean);
  if (!char) {
    unmapped.push(entry.name + ' (no class tag)');
    continue;
  }
  const typeTag = tags.find(t => t === 'Attack' || t === 'Skill' || t === 'Power');
  const name = officialName(id);
  cards[id] = {
    id,
    name,
    cn: cnName(id),
    tier: mapTier(entry.tierValue),
    builds: classify(char, name, typeTag || '', description(id)),
    char,
    type: (typeTag || '').toLowerCase(),
    rating: entry.rating,
    consensus: entry.consensus,
    votes: entry.voterCount,
  };
}

const list = Object.values(cards);
const tierCounts = { T0: 0, T1: 0, T2: 0, T3: 0 };
const tagCounts = {};
for (const b of base.builds) tagCounts[b.key] = 0;
for (const c of list) {
  tierCounts[c.tier]++;
  for (const k of c.builds) {
    if (tagCounts[k] != null) tagCounts[k]++;
  }
}

base.cards = cards;
base.meta = {
  cardTierSource: 'Unduel community tier list: The Best Slay the Spire Cards',
  cardTierUrl: tiers._url,
  cardTierVoters: tiers._voters,
  cardTierVotes: tiers._votes,
  cardTierUpdated: tiers._updated,
  cardTierRetrieved: tiers._retrieved,
  cardTierMapping: tiers._mapping,
  cardTierNote: '320 可获取卡牌；不包含不会出现在卡牌奖励/商店的基础牌、状态、诅咒和 token。',
  archetypeNote: '所有卡牌流派标签均由杀戮尖塔一代卡牌文本规则推导；未使用二代数据。',
};
base.stats.cards = list.length;
base.stats.cardsWithTier = list.filter(c => c.tier).length;
base.stats.tiers = tierCounts;
base.stats.unmappedUnduel = unmapped;

fs.writeFileSync(basePath, JSON.stringify(base, null, 2), 'utf8');

console.log('cards total=' + list.length + ' tiers=' + JSON.stringify(tierCounts));
console.log('unmapped=' + (unmapped.length ? unmapped.join(', ') : 'none'));
console.log('tag counts=' + JSON.stringify(tagCounts));
