package crawlguide;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.screens.CardRewardScreen;

/**
 * Entry point. ModTheSpire discovers @SpireInitializer and applies every @SpirePatch
 * class in this jar automatically.
 */
@SpireInitializer
public final class CrawlGuide {
    public static void initialize() {
        // Nothing to set up; the patches below do the work.
    }

    @SpirePatch(clz = AbstractCard.class, method = "render", paramtypez = {SpriteBatch.class})
    public static class CardRenderPatch {
        @SpirePostfixPatch
        public static void Postfix(AbstractCard __instance, SpriteBatch sb) {
            Overlay.card(__instance, sb);
        }
    }

    @SpirePatch(clz = AbstractRelic.class, method = "render", paramtypez = {SpriteBatch.class})
    public static class RelicRenderPatch {
        @SpirePostfixPatch
        public static void Postfix(AbstractRelic __instance, SpriteBatch sb) {
            Overlay.relic(__instance, sb);
        }
    }

    @SpirePatch(clz = AbstractRelic.class, method = "renderWithoutAmount",
            paramtypez = {SpriteBatch.class, Color.class})
    public static class ShopRelicRenderPatch {
        @SpirePostfixPatch
        public static void Postfix(AbstractRelic __instance, SpriteBatch sb, Color c) {
            Overlay.relic(__instance, sb);
        }
    }

    @SpirePatch(clz = CardRewardScreen.class, method = "render", paramtypez = {SpriteBatch.class})
    public static class CardRewardSummaryPatch {
        @SpirePostfixPatch
        public static void Postfix(CardRewardScreen __instance, SpriteBatch sb) {
            Overlay.deckSummary(sb);
        }
    }
}
