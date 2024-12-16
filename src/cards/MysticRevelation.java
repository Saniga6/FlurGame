package cards;

import cards.overall.*;
import insideGame.Battlefield;
import insideGame.Deck;
import insideGame.Hand;
import insideGame.Player;

public class MysticRevelation extends Card implements Initial {
    public MysticRevelation() {
        super("Mystic revelation", 2 , "Fireplace.png", Types.NONE, SpellType.WHENEVER, "Discover a random spell to your hand, reduce its cost by 2.");
    }

    @Override
    public void doWhenPlayed(Battlefield myBattlefield, Battlefield oponentBattlefield, Player player, Player oponentPlayer, Hand myHand, Hand oponentHand, Deck myDeck, Deck oponentDeck) {
        AllCards allCards = new AllCards();
        Card newCard = allCards.getRandomSpell();
        newCard.subtractMana(2);
        if (!this.isBotCard()) {
            myHand.addCardToHand(newCard);
        } else {
            newCard.setBotCard();
            oponentHand.addCardToHand(newCard);
        }
    }
}
