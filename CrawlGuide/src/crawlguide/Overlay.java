package crawlguide;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

/** Draws tier badges, archetype tags and deck-fit hints on top of the game UI. */
public final class Overlay {
    private static final Color T0 = new Color(0.949f, 0.788f, 0.298f, 1f);
    private static final Color T1 = new Color(0.773f, 0.561f, 0.878f, 1f);
    private static final Color T2 = new Color(0.373f, 0.659f, 0.863f, 1f);
    private static final Color T3 = new Color(0.490f, 0.518f, 0.580f, 1f);
    private static final Color GOLD = new Color(0.910f, 0.769f, 0.455f, 1f);
    private static final Color TEXT = new Color(0.937f, 0.906f, 0.965f, 1f);
    private static final Color DIM = new Color(0.654f, 0.616f, 0.690f, 1f);
    private static final Color PILL_BG = new Color(0.02f, 0.01f, 0.04f, 0.92f);
    private static final Color TIER_TEXT = new Color(0.06f, 0.04f, 0.09f, 1f);
    private static final Color SHADOW = new Color(0f, 0f, 0f, 0.85f);
    private static final Color PANEL_BG = new Color(0f, 0f, 0f, 0.68f);
    private static final Color OUTLINE = new Color(0.910f, 0.769f, 0.455f, 0.80f);

    private static boolean logged;

    private Overlay() {
    }

    public static void card(AbstractCard card, SpriteBatch sb) {
        try {
            if (AbstractDungeon.player == null || Settings.hideCards) {
                return;
            }
            AbstractDungeon.CurrentScreen screen = AbstractDungeon.screen;
            if (screen != AbstractDungeon.CurrentScreen.CARD_REWARD
                    && screen != AbstractDungeon.CurrentScreen.SHOP) {
                return;
            }
            GuideData.Card info = GuideData.CARDS.get(card.cardID);
            if (info == null) {
                return;
            }

            float scale = Settings.scale;
            float rowY = card.current_y + AbstractCard.IMG_HEIGHT * card.drawScale * 0.5f + 26f * scale;
            float pillH = 26f * scale;
            float maxW = AbstractCard.IMG_WIDTH * card.drawScale - 8f * scale;
            float gap = 5f * scale;
            float tierFont = 0.86f;
            float tagFont = 0.78f;

            float tierW = 0f;
            if (info.tier != null && info.tier.length() > 0) {
                tierW = Math.max(36f * scale, measure(FontHelper.cardTitleFont, info.tier, tierFont) + 16f * scale);
            }

            DeckFit fit = DeckFit.current();
            boolean fitMatch = fit != null && info.builds.length > 0 && fit.matches(info.builds);
            boolean core = fitMatch && fit.isCore(card.cardID);
            String fitText = fitMatch
                    ? (zh() ? (core ? "*核心" : "*契合") : (core ? "*core" : "*fit"))
                    : null;
            float fitW = fitText == null ? 0f
                    : Math.max(52f * scale, measure(FontHelper.cardTitleFont, fitText, tagFont) + 14f * scale);

            java.util.ArrayList<String> tags = new java.util.ArrayList<String>();
            for (String key : info.builds) {
                GuideData.Build b = GuideData.BUILD_BY_KEY.get(key);
                if (b != null) {
                    tags.add(zh() ? b.shortCn : b.shortEn);
                }
            }

            float total = tierW;
            if (fitW > 0f) {
                total += (total > 0f ? gap : 0f) + fitW;
            }
            int shown = 0;
            int tagLimit = fitMatch ? 1 : 2;
            while (shown < tags.size() && shown < tagLimit) {
                float w = measure(FontHelper.cardTitleFont, tags.get(shown), tagFont) + 14f * scale;
                if (total + w + (total > 0f ? gap : 0f) > maxW) {
                    break;
                }
                total += w + (total > 0f ? gap : 0f);
                shown++;
            }

            float x = card.current_x - total * 0.5f;
            if (tierW > 0f) {
                pill(sb, x + tierW * 0.5f, rowY, tierW, pillH, tierColor(info.tier), TIER_TEXT,
                        info.tier, FontHelper.cardTitleFont, tierFont, false);
                x += tierW + gap;
            }
            if (fitW > 0f) {
                pill(sb, x + fitW * 0.5f, rowY, fitW, pillH, PILL_BG, GOLD,
                        fitText, FontHelper.cardTitleFont, tagFont, true);
                x += fitW + gap;
            }
            for (int i = 0; i < shown; i++) {
                String text = tags.get(i);
                float w = measure(FontHelper.cardTitleFont, text, tagFont) + 14f * scale;
                pill(sb, x + w * 0.5f, rowY, w, pillH, PILL_BG, TEXT,
                        text, FontHelper.cardTitleFont, tagFont, true);
                x += w + gap;
            }

            if (fitMatch) {
                outlineCard(card, sb);
            }
        } catch (Throwable t) {
            logOnce(t);
        }
    }

    public static void relic(AbstractRelic relic, SpriteBatch sb) {
        try {
            if (AbstractDungeon.player == null || Settings.hideRelics) {
                return;
            }
            AbstractDungeon.CurrentScreen screen = AbstractDungeon.screen;
            if (screen != AbstractDungeon.CurrentScreen.BOSS_REWARD
                    && screen != AbstractDungeon.CurrentScreen.SHOP) {
                return;
            }
            GuideData.Relic info = GuideData.RELICS.get(relic.relicId);
            if (info == null) {
                return;
            }

            float scale = Settings.scale;
            float half = 64f * relic.scale;
            float tierFont = 0.80f;
            if (info.tier != null && info.tier.length() > 0) {
                float w = Math.max(34f * scale, measure(FontHelper.cardTitleFont, info.tier, tierFont) + 14f * scale);
                float cx = relic.currentX - half + w * 0.5f + 3f * scale;
                float cy = relic.currentY + half - 13f * scale;
                pill(sb, cx, cy, w, 24f * scale, tierColor(info.tier), TIER_TEXT, info.tier,
                        FontHelper.cardTitleFont, tierFont, false);
            }

            if (info.builds.length > 0) {
                GuideData.Build build = GuideData.BUILD_BY_KEY.get(info.builds[0]);
                if (build != null) {
                    DeckFit fit = DeckFit.current();
                    boolean match = fit != null && fit.matches(info.builds);
                    String text = (match ? "*" : ">") + (zh() ? build.shortCn : build.shortEn);
                    float fontScale = 0.72f;
                    float maxW = half * 2f - 6f * scale;
                    float w = measure(FontHelper.cardTitleFont, text, fontScale) + 12f * scale;
                    while (w > maxW && fontScale > 0.52f) {
                        fontScale -= 0.04f;
                        w = measure(FontHelper.cardTitleFont, text, fontScale) + 12f * scale;
                    }
                    if (w > maxW) {
                        text = (match ? "*" : ">") + (zh() ? build.shortCn : build.shortEn).substring(0, 2);
                        w = maxW;
                    }
                    pill(sb, relic.currentX, relic.currentY - half + 13f * scale, w, 23f * scale,
                            PILL_BG, match ? GOLD : TEXT, text, FontHelper.cardTitleFont, fontScale, true);
                }
            }
        } catch (Throwable t) {
            logOnce(t);
        }
    }

    public static void deckSummary(SpriteBatch sb) {
        try {
            if (AbstractDungeon.player == null) {
                return;
            }
            if (AbstractDungeon.screen != AbstractDungeon.CurrentScreen.CARD_REWARD) {
                return;
            }
            DeckFit fit = DeckFit.current();
            String text;
            if (fit == null) {
                text = zh() ? "牌组倾向：尚未成型" : "Deck: no clear archetype";
            } else {
                String name = zh() ? fit.build.shortCn : fit.build.shortEn;
                text = zh()
                        ? ("牌组倾向：" + name + " / 核心 " + fit.coreOwned + "/" + fit.build.core.length)
                        : ("Deck: " + name + " / core " + fit.coreOwned + "/" + fit.build.core.length);
            }

            float scale = Settings.scale;
            float fontScale = 0.92f;
            float w = measure(FontHelper.cardDescFont_N, text, fontScale) + 48f * scale;
            float x = Settings.WIDTH / 2f;
            float y = 288f * scale;
            sb.setColor(PANEL_BG);
            sb.draw(ImageMaster.WHITE_SQUARE_IMG, x - w * 0.5f, y - 20f * scale, w, 40f * scale);
            sb.setColor(Color.WHITE);
            FontHelper.renderFontCentered(sb, FontHelper.cardDescFont_N, text,
                    x + 1.5f * scale, y - 1.5f * scale, SHADOW, fontScale);
            FontHelper.renderFontCentered(sb, FontHelper.cardDescFont_N, text, x, y,
                    fit == null ? DIM : GOLD, fontScale);
        } catch (Throwable t) {
            logOnce(t);
        }
    }

    private static void outlineCard(AbstractCard card, SpriteBatch sb) {
        float scale = Settings.scale;
        float hw = AbstractCard.IMG_WIDTH * card.drawScale * 0.5f;
        float hh = AbstractCard.IMG_HEIGHT * card.drawScale * 0.5f;
        float t = 3f * scale;
        sb.setColor(OUTLINE);
        sb.draw(ImageMaster.WHITE_SQUARE_IMG, card.current_x - hw, card.current_y - hh, hw * 2f, t);
        sb.draw(ImageMaster.WHITE_SQUARE_IMG, card.current_x - hw, card.current_y + hh - t, hw * 2f, t);
        sb.draw(ImageMaster.WHITE_SQUARE_IMG, card.current_x - hw, card.current_y - hh, t, hh * 2f);
        sb.draw(ImageMaster.WHITE_SQUARE_IMG, card.current_x + hw - t, card.current_y - hh, t, hh * 2f);
        sb.setColor(Color.WHITE);
    }

    private static void pill(SpriteBatch sb, float cx, float cy, float w, float h,
                             Color fill, Color textColor, String text, BitmapFont font,
                             float fontScale, boolean shadow) {
        sb.setColor(fill);
        sb.draw(ImageMaster.WHITE_SQUARE_IMG, cx - w * 0.5f, cy - h * 0.5f, w, h);
        sb.setColor(Color.WHITE);
        if (shadow) {
            FontHelper.renderFontCentered(sb, font, text, cx + 1.3f * Settings.scale,
                    cy - 1.3f * Settings.scale, SHADOW, fontScale);
        }
        FontHelper.renderFontCentered(sb, font, text, cx, cy, textColor, fontScale);
    }

    private static float measure(BitmapFont font, String text, float scale) {
        return FontHelper.getWidth(font, text, scale);
    }

    private static Color tierColor(String tier) {
        if ("T0".equals(tier)) {
            return T0;
        }
        if ("T1".equals(tier)) {
            return T1;
        }
        if ("T2".equals(tier)) {
            return T2;
        }
        return T3;
    }

    private static boolean zh() {
        return Settings.language == Settings.GameLanguage.ZHS
                || Settings.language == Settings.GameLanguage.ZHT;
    }

    private static void logOnce(Throwable t) {
        if (!logged) {
            logged = true;
            t.printStackTrace();
        }
    }
}
