package cards.overall;

import insideGame.Battlefield;
import insideGame.Deck;
import insideGame.Hand;
import insideGame.Player;

/**
 * Interface Dying má v sebe metódy, ktoré sa neskôr volajú v kartách.
 * @author (Marek Saniga)
 * @version (1.0)
 */
public interface Dying {
    void doWhenDie(Battlefield myBattlefield, Battlefield oponentBattlefield, Player player, Player oponentPlayer, Hand myHand, Hand oponentHand, Deck myDeck, Deck oponentDeck);
}
