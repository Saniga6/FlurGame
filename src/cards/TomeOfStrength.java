package cards;

import cards.overall.Card;
import cards.overall.Initial;
import cards.overall.SpellType;
import cards.overall.Types;
import insideGame.Battlefield;
import insideGame.Choosing;
import insideGame.Deck;
import insideGame.Hand;
import insideGame.Player;
import java.util.Random;

/**
 * Trieda TomeOfStregth je reprezentácia jednej z kariet
 * @author (Marek Saniga)
 * @version (1.0)
 */
public class TomeOfStrength extends Card implements Initial {
    /**
     * (TomeOfStrength) Bezparametrický konštruktor, v ktorom sa inicializujú základné staty karty.
     */
    public TomeOfStrength() {
        super("Tome of strength", 2 , "Tome of strength.png", Types.NONE, SpellType.BUFFING, "Give a friendly minion +4ATK");
    }
    /**
     * (TomeOfStrength) Podľa toho či patrí karta botovi alebo hráčovi vykoná svoju činnosť.
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
            if (myBattlefield.getCardsOnBattleField().size() > 0) {
                Choosing choosing = new Choosing();
                Card chosenCard = choosing.chooseAMinion(myBattlefield);
                myBattlefield.getCard(chosenCard).addAttack(4);
            }
        } else {
            Random random = new Random();
            if (oponnentBattlefield.getCardsOnBattleField().size() > 0) {
                if (oponnentBattlefield.getCardsOnBattleField().size() != 1) {
                    int indexOfMinion = random.nextInt(0, oponnentBattlefield.getCardsOnBattleField().size() - 1);
                    oponnentBattlefield.getCardsOnBattleField().get(indexOfMinion).addAttack(4);
                } else {
                    oponnentBattlefield.getCardsOnBattleField().get(0).addAttack(4);
                }
            }
        }
    }
}
