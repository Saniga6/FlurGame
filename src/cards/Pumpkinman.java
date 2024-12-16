package cards;

import cards.overall.Card;
import cards.overall.Dying;
import cards.overall.Types;
import insideGame.Battlefield;
import insideGame.Deck;
import insideGame.Hand;
import insideGame.Player;

/**
 * Trieda PumpkinMan je reprezentácia jednej z kariet
 * @author (Marek Saniga)
 * @version (1.0)
 */
public class Pumpkinman extends Card implements Dying {
    /**
     * (Pumpkinman) Bezparametrický konštruktor, v ktorom sa inicializujú základné staty karty.
     */
    public Pumpkinman() {
        super("PumpkinMan", 4, 3, 2, "Pumpkinman.png", Types.NONE, "When i die, deal 2 damage to all enemy minions");
    }
    /**
     * (Pumpkinman) Podľa toho či patrí karta botovi alebo hráčovi vykoná svoju činnosť akonáhle zomrie.
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
    public void doWhenDie(Battlefield myBattlefield, Battlefield oponnentBattlefield, Player player, Player oponnentPlayer, Hand myHand, Hand oponnentHand, Deck myDeck, Deck oponnentDeck) {
        if (!this.isBotCard()) {
            for (var card : oponnentBattlefield.getCardsOnBattleField()) {
                card.subtractHealth(2);
            }
        } else {
            for (var card : myBattlefield.getCardsOnBattleField()) {
                card.subtractHealth(2);
            }
        }
    }
}
