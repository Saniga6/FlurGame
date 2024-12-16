package cards;

import cards.overall.Card;
import cards.overall.Initial;
import cards.overall.Types;
import insideGame.Battlefield;
import insideGame.Deck;
import insideGame.Hand;
import insideGame.Player;

/**
 * Trieda LoneWanderer je reprezentácia jednej z kariet.
 * @author (Marek Saniga)
 * @version (1.1)
 */
public class LoneWanderer extends Card implements Initial {
    /**
     * (LoneWanderer) Bezparametrický konštruktor, v ktorom sa inicializujú základné staty karty.
     */
    public LoneWanderer() {
        super("Lone wanderer", 2, 2, 2, "Lone wanderer.png", Types.DEATH, "If there is no other minions on your battlefield i get +2HP +2ATK");
    }
    /**
     * (LoneWanderer) Podľa toho či patrí karta botovi alebo hráčovi vykoná svoju činnosť.
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
            if (myBattlefield.isEmpty()) {
                super.addAttack(2);
                super.addHealth(2);
            }
        } else {
            if (oponnentBattlefield.isEmpty()) {
                super.addAttack(2);
                super.addHealth(2);
            }
        }
    }
}


