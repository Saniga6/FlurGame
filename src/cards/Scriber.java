package cards;

import cards.overall.Card;
import cards.overall.Initial;
import cards.overall.Types;
import insideGame.Battlefield;
import insideGame.Deck;
import insideGame.Hand;
import insideGame.Player;

/**
 * Trieda Scriber je reprezentácia jednej z kariet
 * @author (Marek Saniga)
 * @version (1.1)
 */
public class Scriber extends Card implements Initial {
    /**
     * (Scriber) Bezparametrický konštruktor, v ktorom sa inicializujú základné staty karty.
     */
    public Scriber() {
        super("Scriber", 4, 3, 2, "Lone wanderer.png", Types.NONE, "Draw 2 cards");
    }
    /**
     * (Scriber) Podľa toho či patrí karta botovi alebo hráčovi vykoná svoju činnosť.
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
        if (!this.isBotCard()) {
            myDeck.drawACard(myHand, player);
            myDeck.drawACard(myHand, player);
        } else {
            oponnentDeck.drawACard(oponnentHand, oponnentPlayer);
            oponnentDeck.drawACard(oponnentHand, oponnentPlayer);
        }
    }
}
