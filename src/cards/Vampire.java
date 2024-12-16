package cards;

import cards.overall.Card;
import cards.overall.Dying;
import cards.overall.Types;
import insideGame.Battlefield;
import insideGame.Deck;
import insideGame.Hand;
import insideGame.Player;

/**
 * Trieda Vampire je reprezentácia jednej z kariet
 * @author (Marek Saniga)
 * @version (1.0)
 */
public class Vampire extends Card implements Dying {
    /**
     * (Vampire) Bezparametrický konštruktor, v ktorom sa inicializujú základné staty karty.
     */
    public Vampire() {
        super("Vampire", 6, 3, 3, "Vampire.png", Types.LIFE, "Death: Deal 2 damage to all enemies, for each enemy restore 2 health to you");
    }
    /**
     * (Vampire) Podľa toho či patrí karta botovi alebo hráčovi vykoná svoju činnosť akonáhle zomrie.
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
            int heal = 0;
            for (var card : oponnentBattlefield.getCardsOnBattleField()) {
                card.subtractHealth(2);
                heal += 2;
            }
            oponnentPlayer.decreaseHP(2);
            heal += 2;

            player.increaseHP(heal);
        } else {
            int heal = 0;
            for (var card : myBattlefield.getCardsOnBattleField()) {
                card.subtractHealth(2);
                heal += 2;
            }
            player.decreaseHP(2);
            heal += 2;

            oponnentPlayer.increaseHP(heal);
        }
    }
}
