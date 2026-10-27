/*
 * Expands CrawlGuide/data.json from the curated guide cards to the full
 * Slay the Spire 1 card pool.
 *
 * Tier sources:
 *  - Cards already in the guide keep the guide's tier and build tags.
 *  - Remaining cards use SpireSpy's Slay the Spire 1 tier lists
 *    (maybelatergames.co.uk/tools/slaythespire), mapped S/A/B/C -> T0/T1/T2/T3.
 *  - Build tags for new cards are derived from Slay the Spire 1 card text only.
 *
 * No Slay the Spire 2 data is used.
 */
const fs = require('fs');
const path = require('path');

const basePath = process.argv[2] || 'CrawlGuide/data.json';
const tiersPath = process.argv[3] || 'guide/spirespy-tiers.json';
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
const charOfClass = { ironclad: 'i', silent: 's', defect: 'd', watcher: 'w', colorless: 'c' };

// Tokens, event-only cards and basics are not part of the normal reward/shop pool.
const excluded = new Set([
  'shiv', 'smite', 'miracle', 'safety', 'insight', 'throughviolence', 'beta', 'omega',
  'jax', 'bite', 'apparition', 'ritualdagger', 'strike', 'defend',
].map(norm));

function mapGrade(grade) {
  const first = String(grade || '').trim().toUpperCase().charAt(0);
  if (first === 'S') return 'T0';
  if (first === 'A') return 'T1';
  if (first === 'B') return 'T2';
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
    if (/frost/.test(t)
        || (/focus/.test(t) && !/lose .*focus/.test(t))
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

let guideCards = 0;
for (const card of Object.values(base.cards)) {
  card.src = 'guide';
  const keyChar = card.builds.length > 0 ? card.builds[0].split('|')[0] : null;
  if (!card.char) card.char = keyChar;
  guideCards++;
}

let added = 0;
const unmapped = [];
for (const [cls, list] of Object.entries(tiers.classes)) {
  const char = charOfClass[cls];
  for (const entry of list) {
    if (excluded.has(norm(entry.name))) continue;
    const id = nameToId[norm(entry.name)] || idNorm[norm(entry.name)];
    if (!id) {
      unmapped.push(cls + ':' + entry.name);
      continue;
    }
    if (base.cards[id]) continue;
    const name = officialName(id);
    base.cards[id] = {
      id,
      name,
      cn: cnName(id),
      tier: mapGrade(entry.grade),
      builds: classify(char, name, entry.type, description(id)),
      src: 'spirespy',
      char,
      type: entry.type,
    };
    added++;
  }
}

const cards = Object.values(base.cards);
const tierCounts = { guide: 0, spirespy: 0, default: 0 };
const tagCounts = {};
for (const b of base.builds) tagCounts[b.key] = 0;
for (const c of cards) {
  tierCounts[c.src === 'guide' ? 'guide' : 'spirespy']++;
  for (const k of c.builds) {
    if (tagCounts[k] != null) tagCounts[k]++;
  }
}

base.meta = {
  cardTierSources: {
    guide: '原创速查表，114 张卡优先保留原 T 度',
    spirespy: 'SpireSpy Slay the Spire 1 卡牌梯队（maybelatergames.co.uk/tools/slaythespire），S/A/B/C/D 映射为 T0-T3',
  },
  spirespySource: tiers._source,
  spirespyUrls: tiers._urls,
  spirespyRetrieved: tiers._retrieved,
  archetypeNote: '原有卡牌保留速查表流派标签；新增卡牌的流派标签由杀戮尖塔一代卡牌文本规则推导，未使用二代数据。',
};
base.stats.cards = cards.length;
base.stats.cardsWithTier = cards.filter(c => c.tier).length;
base.stats.cardsGuide = guideCards;
base.stats.cardsSpireSpy = added;
base.stats.unmappedSpireSpy = unmapped;

fs.writeFileSync(basePath, JSON.stringify(base, null, 2), 'utf8');

console.log('cards total=' + cards.length + ' guide=' + guideCards + ' added=' + added);
console.log('tier sources=' + JSON.stringify(tierCounts));
console.log('unmapped=' + (unmapped.length ? unmapped.join(', ') : 'none'));
console.log('tag counts=' + JSON.stringify(tagCounts));
