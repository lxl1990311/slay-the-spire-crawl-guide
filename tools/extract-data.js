const fs = require('fs');

const htmlPath = process.argv[2];
const outPath = process.argv[3];
const html = fs.readFileSync(htmlPath, 'utf8');

const start = html.indexOf('const CHARS = {');
if (start < 0) throw new Error('CHARS block not found');
const tailMarker = '/* 总榜是已评级遗物的唯一梯队来源';
const tailIdx = html.indexOf(tailMarker, start);
if (tailIdx < 0) throw new Error('tail marker not found');
const codeEnd = html.indexOf('}));', tailIdx);
if (codeEnd < 0) throw new Error('block end not found');
const code = html.slice(start, codeEnd + 4);

const fn = new Function(code + `
return {CHARS, CARD_DB, CARD_TIER, CARD_BUILDS, RELIC_DB, RELIC_TIER, RELIC_MAP, RELIC_BUILDS};
`);

const D = fn();

const locDir = process.argv[4] || 'build-tools/localization';
const norm = s => String(s).toLowerCase().replace(/[^a-z0-9]/g, '');
const cardsLoc = JSON.parse(fs.readFileSync(locDir + '/cards.json.eng.json', 'utf8'));
const relicsLoc = JSON.parse(fs.readFileSync(locDir + '/relics.json.eng.json', 'utf8'));
const cardIdsByName = {};
Object.entries(cardsLoc).forEach(([id, v]) => {
  cardIdsByName[norm(v.NAME)] = id;
  cardIdsByName[norm(id)] = id;
});
const relicIdsByName = {};
Object.entries(relicsLoc).forEach(([id, v]) => {
  relicIdsByName[norm(v.NAME)] = id;
  relicIdsByName[norm(id)] = id;
});
const ALIASES = { 'Thousand Cuts': 'A Thousand Cuts' };
const cardId = name => {
  const key = ALIASES[name] || name;
  return cardIdsByName[norm(key)] || key;
};
const relicId = name => relicIdsByName[norm(name)] || name;
const buildCardIds = list => list
  .map(t => cardId(t.slice(t.indexOf(' ') + 1)))
  .filter(id => cardsLoc[id]);

const CHAR_ID = { ironclad: 'i', silent: 's', defect: 'd', watcher: 'w' };
const SHORT = {
  '腐化消耗流': ['消耗', 'Exhaust'],
  '力量流': ['力量', 'Strength'],
  '格挡堡垒流': ['格挡', 'Block'],
  '撕裂自伤流': ['自伤', 'SelfDmg'],
  '毒流': ['毒', 'Poison'],
  '小刀流': ['小刀', 'Shiv'],
  '弃牌流': ['弃牌', 'Discard'],
  '无限流': ['无限', 'Infinite'],
  '冰霜坦克流': ['冰霜', 'Frost'],
  '闪电球流': ['闪电', 'Lightning'],
  '能力流': ['能力', 'Power'],
  '暗球 / 爪击流': ['暗球', 'Dark/Claw'],
  '姿态循环无限流': ['姿态无限', 'StanceLoop'],
  '姿态舞（常规）': ['姿态舞', 'StanceDance'],
  '神格真言流': ['真言', 'Divinity'],
};

const builds = [];
const buildByKey = {};
Object.keys(D.CHARS).forEach(ck => {
  const c = D.CHARS[ck];
  const cid = CHAR_ID[ck];
  (c.builds || []).forEach(b => {
    const key = cid + '|' + b.name;
    const short = SHORT[b.name] || [b.name, b.en];
    const build = {
      key,
      char: cid,
      tier: b.tier,
      nameCn: b.name,
      nameEn: b.en,
      shortCn: short[0],
      shortEn: short[1],
      core: buildCardIds(b.core || []),
      keys: buildCardIds(b.key || []),
    };
    builds.push(build);
    buildByKey[key] = build;
  });
});

const cards = {};
Object.keys(D.CARD_DB).forEach(en => {
  const row = D.CARD_DB[en];
  const tier = D.CARD_TIER[en] || null;
  const b = D.CARD_BUILDS[en] || [];
  if (!tier && b.length === 0) return;
  const buildKeys = b.map(x => CHAR_ID[x[0]] + '|' + x[1]).filter(k => buildByKey[k]);
  const id = cardId(en);
  cards[id] = { id, name: en, cn: row[0], tier, builds: buildKeys };
});

const relics = {};
function addRelic(name) {
  const row = D.RELIC_DB[name];
  if (!row) return;
  const tier = D.RELIC_TIER[name] || null;
  const bl = D.RELIC_BUILDS[name] || [];
  const buildKeys = bl.map(x => (CHAR_ID[x[0]] || x[0]) + '|' + x[1]).filter(k => buildByKey[k]);
  if (!tier && buildKeys.length === 0) return;
  const id = relicId(name);
  relics[id] = { id, name, cn: row[0], rarity: row[1], tier, builds: buildKeys };
}
Object.keys(D.RELIC_DB).forEach(addRelic);
Object.keys(D.RELIC_TIER).forEach(addRelic);
Object.keys(D.RELIC_BUILDS).forEach(id => {
  if (id.includes(' / ')) id.split(' / ').forEach(x => addRelic(x.trim()));
  else addRelic(id);
});

const out = {
  builds,
  cards,
  relics,
  stats: {
    builds: builds.length,
    cards: Object.keys(cards).length,
    cardsWithTier: Object.values(cards).filter(c => c.tier).length,
    relics: Object.keys(relics).length,
    relicsWithTier: Object.values(relics).filter(r => r.tier).length,
    relicMapGroups: D.RELIC_MAP.length,
    relicBuildKeys: Object.keys(D.RELIC_BUILDS).length,
    relicBuildSample: Object.keys(D.RELIC_BUILDS).slice(0, 5),
    missingCardIds: Object.keys(cards).filter(id => !cardsLoc[id]),
    missingRelicIds: Object.keys(relics).filter(id => !relicsLoc[id]),
    missingBuildCardIds: [...new Set(builds.flatMap(b => b.core.concat(b.keys)))].filter(id => !cardsLoc[id]),
  },
};
fs.writeFileSync(outPath, JSON.stringify(out, null, 2), 'utf8');
console.log(JSON.stringify(out.stats));
