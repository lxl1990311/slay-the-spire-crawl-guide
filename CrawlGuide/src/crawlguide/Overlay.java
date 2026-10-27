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
    private static final Color PILL_BG = new Color(0f, 0f, 0f, 0.72f);
    private static final Color PANEL_BG = new Color(0f, 0f, 0f, 0.55f);
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
            float rowY = card.current_y + AbstractCard.IMG_HEIGHT * card.drawScale * 0.5f + 15f * scale;
            float maxW = AbstractCard.IMG_WIDTH * card.drawScale - 8f * scale;
            float gap = 5f * scale;
            float tierW = 0f;
            if (info.tier != null && info.tier.length() > 0) {
                tierW = Math.max(32f * scale, measure(FontHelper.cardTitleFont, info.tier, 0.70f) + 14f * scale);
            }

            java.util.ArrayList<String> tags = new java.util.ArrayList<String>();
            for (String key : info.builds) {
                GuideData.Build b = GuideData.BUILD_BY_KEY.get(key);
                if (b != null) {
                    tags.add(zh() ? b.shortCn : b.shortEn);
                }
            }

            float total = tierW;
            int shown = 0;
            while (shown < tags.size() && shown < 2) {
                float w = measure(FontHelper.cardDescFont_N, tags.get(shown), 0.70f) + 12f * scale;
                if (total + w + (total > 0f ? gap : 0f) > maxW) {
                    break;
                }
                total += w + (total > 0f ? gap : 0f);
                shown++;
            }

            float x = card.current_x - total * 0.5f;
            if (tierW > 0f) {
                pill(sb, x + tierW * 0.5f, rowY, tierW, 22f * scale, tierColor(info.tier), info.tier,
                        FontHelper.cardTitleFont, 0.70f);
                x += tierW + gap;
            }
            for (int i = 0; i < shown; i++) {
                String text = tags.get(i);
                float w = measure(FontHelper.cardDescFont_N, text, 0.70f) + 12f * scale;
                pill(sb, x + w * 0.5f, rowY, w, 22f * scale, TEXT, text, FontHelper.cardDescFont_N, 0.70f);
                x += w + gap;
            }

            DeckFit fit = DeckFit.current();
            if (fit != null && info.builds.length > 0 && fit.matches(info.builds)) {
                boolean core = fit.isCore(card.cardID);
                String fitText = zh()
                        ? (core ? "* 契合 核心" : "* 契合")
                        : (core ? "* fit core" : "* fit");
                FontHelper.renderFontCentered(sb, FontHelper.cardDescFont_N, fitText,
                        card.current_x, rowY + 25f * scale, GOLD, 0.70f);
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
            if (info.tier != null && info.tier.length() > 0) {
                float w = Math.max(30f * scale, measure(FontHelper.cardTitleFont, info.tier, 0.68f) + 12f * scale);
                float cx = relic.currentX - half + w * 0.5f + 3f * scale;
                float cy = relic.currentY + half - 12f * scale;
                pill(sb, cx, cy, w, 20f * scale, tierColor(info.tier), info.tier, FontHelper.cardTitleFont, 0.68f);
            }

            if (info.builds.length > 0) {
                GuideData.Build build = GuideData.BUILD_BY_KEY.get(info.builds[0]);
                if (build != null) {
                    DeckFit fit = DeckFit.current();
                    boolean match = fit != null && fit.matches(info.builds);
                    String text = (match ? "* " : "> ") + (zh() ? build.shortCn : build.shortEn);
                    float w = measure(FontHelper.cardDescFont_N, text, 0.64f) + 10f * scale;
                    float maxW = half * 2f - 6f * scale;
                    if (w > maxW) {
                        text = (match ? "* " : "> ") + (zh() ? build.shortCn : build.shortEn).substring(0, 2);
                        w = measure(FontHelper.cardDescFont_N, text, 0.64f) + 10f * scale;
                    }
                    pill(sb, relic.currentX, relic.currentY - half + 12f * scale, w, 19f * scale,
                            match ? GOLD : TEXT, text, FontHelper.cardDescFont_N, 0.64f);
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
            float fontScale = 0.82f;
            float w = measure(FontHelper.cardDescFont_N, text, fontScale) + 40f * scale;
            float x = Settings.WIDTH / 2f;
            float y = 288f * scale;
            sb.setColor(PANEL_BG);
            sb.draw(ImageMaster.WHITE_SQUARE_IMG, x - w * 0.5f, y - 17f * scale, w, 34f * scale);
            sb.setColor(Color.WHITE);
            FontHelper.renderFontCentered(sb, FontHelper.cardDescFont_N, text, x, y, fit == null ? DIM : GOLD, fontScale);
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
                             Color textColor, String text, BitmapFont font, float fontScale) {
        sb.setColor(PILL_BG);
        sb.draw(ImageMaster.WHITE_SQUARE_IMG, cx - w * 0.5f, cy - h * 0.5f, w, h);
        sb.setColor(Color.WHITE);
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
