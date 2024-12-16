package cards;

import cards.overall.AllCards;
import cards.overall.Card;
import cards.overall.Initial;
import cards.overall.Types;
import insideGame.Battlefield;
import insideGame.Deck;
import insideGame.Hand;
import insideGame.Player;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Trieda AcolyteOfDeath je reprezentácia jednej z kariet
 * @author (Marek Saniga)
 * @version (1.1)
 */
public class AcolyteOfDeath extends Card implements Initial {
    /**
     * (AcolyteOfDeath) Bezparametrický konštruktor, v ktorom sa inicializujú základné staty karty.
     */
    public AcolyteOfDeath() {
        super("Acolyte of death", 3, 4, 4, "Lone wanderer.png", Types.DEATH, "Add a random death card in your hand");
    }

    /**
     * (AcolyteOfDeath) Podľa toho či patrí karta botovi alebo hráčovi vykoná svoju činnosť.
     * @param myBattlefield Tu sa nachádzajú minioni, ktorých si vyhodil z ruky.
     * @param oponnentBattlefield Tu sa nachádzajú minioni, ktoré bot vyhodil z ruky.
     * @param player Tvoja postava.
     * @param oponnentPlayer Postava bota.
     * @param myHand Tvoja ruka, z ktorej hráš karty.
     * @param oponnentHand Botova ruka, z ktorej bot hrá karty.
     * @param myDeck Tvoj balíček z ktorého si ťaháš karty.
     * @param oponnentDeck Botov balíček, zktorého si ťahá bot.
     */
    @Override
    public void doWhenPlayed(Battlefield myBattlefield, Battlefield oponnentBattlefield, Player player, Player oponnentPlayer, Hand myHand, Hand oponnentHand, Deck myDeck, Deck oponnentDeck) {
        AllCards allCards = new AllCards();
        ArrayList<Card> deathCards = new ArrayList<>();
        for (var card : allCards.getAllCards()) {
            if (card.getTypeOfCard().equals(Types.DEATH)) {
                deathCards.add(card);
            }
        }
        Collections.shuffle(deathCards);
        if (!this.isBotCard()) {
            myHand.addCardToHand(deathCards.get(0));
        } else {
            deathCards.get(0).setBotCard();
            oponnentHand.addCardToHand(deathCards.get(0));
        }
    }
}
