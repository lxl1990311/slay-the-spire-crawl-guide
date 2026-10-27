package crawlguide;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import java.util.HashSet;
import java.util.Set;

/**
 * Heuristic read of the current master deck: which guide build does it look like,
 * and how many of that build's core / key cards are already owned.
 */
public final class DeckFit {
    public final GuideData.Build build;
    public final int coreOwned;
    public final int keyOwned;

    private DeckFit(GuideData.Build build, int coreOwned, int keyOwned) {
        this.build = build;
        this.coreOwned = coreOwned;
        this.keyOwned = keyOwned;
    }

    public boolean matches(String[] cardBuilds) {
        for (String key : cardBuilds) {
            if (key.equals(this.build.key)) {
                return true;
            }
        }
        return false;
    }

    public boolean isCore(String cardId) {
        for (String id : this.build.core) {
            if (id.equals(cardId)) {
                return true;
            }
        }
        return false;
    }

    public static DeckFit current() {
        AbstractPlayer player = AbstractDungeon.player;
        if (player == null) {
            return null;
        }
        String charId = charIdOf(player);
        if (charId == null) {
            return null;
        }

        Set<String> deck = new HashSet<String>();
        for (AbstractCard card : player.masterDeck.group) {
            deck.add(card.cardID);
        }

        DeckFit best = null;
        int bestScore = 0;
        for (GuideData.Build build : GuideData.BUILDS) {
            if (!build.charId.equals(charId)) {
                continue;
            }
            int core = 0;
            int key = 0;
            for (String id : build.core) {
                if (deck.contains(id)) {
                    core++;
                }
            }
            for (String id : build.keys) {
                if (deck.contains(id)) {
                    key++;
                }
            }
            int score = core * 4 + key * 2;
            if (score > bestScore) {
                bestScore = score;
                best = new DeckFit(build, core, key);
            }
        }

        if (best == null || (best.coreOwned == 0 && best.keyOwned < 2)) {
            return null;
        }
        return best;
    }

    private static String charIdOf(AbstractPlayer player) {
        if (player.chosenClass == AbstractPlayer.PlayerClass.IRONCLAD) {
            return "i";
        }
        if (player.chosenClass == AbstractPlayer.PlayerClass.THE_SILENT) {
            return "s";
        }
        if (player.chosenClass == AbstractPlayer.PlayerClass.DEFECT) {
            return "d";
        }
        if (player.chosenClass == AbstractPlayer.PlayerClass.WATCHER) {
            return "w";
        }
        return null;
    }
}
