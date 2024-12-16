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
 * Trieda HeraldOfChaos je reprezentácia jednej z kariet
 * @author (Marek Saniga)
 * @version (1.0)
 */
public class HeraldOfChaos extends Card implements Initial {
    /**
     * (HeraldOfChaos) Bezparametrický konštruktor, v ktorom sa inicializujú základné staty karty.
     */
    public HeraldOfChaos() {
        super("Herald of chaos", 2, 3, 2, "Vampire.png", Types.CHAOS, "When i´m played add a random CHAOS minion to your hand and give it +3hp");
    }
    /**
     * (HeraldOfChaos) Podľa toho či patrí karta botovi alebo hráčovi vykoná svoju činnosť.
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
        ArrayList<Card> chaosCards = new ArrayList<>();
        for (var card : allCards.getAllCards()) {
            if (card.getTypeOfCard().equals(Types.CHAOS)) {
                chaosCards.add(card);
            }
        }
        Collections.shuffle(chaosCards);
        if (!this.isBotCard()) {
            chaosCards.get(0).addHealth(3);
            myHand.addCardToHand(chaosCards.get(0));
        } else {
            chaosCards.get(0).setBotCard();
            chaosCards.get(0).addHealth(3);
            oponnentHand.addCardToHand(chaosCards.get(0));
        }
    }
}
