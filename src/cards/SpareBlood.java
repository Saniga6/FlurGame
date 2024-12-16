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
import javax.swing.JOptionPane;
import java.util.Random;

/**
 * Trieda SpareBlood je reprezentácia jednej z kariet
 * @author (Marek Saniga)
 * @version (1.0)
 */
public class SpareBlood extends Card implements Initial {
    /**
     * (SpareBlood) Bezparametrický konštruktor, v ktorom sa inicializujú základné staty karty.
     */
    public SpareBlood() {
        super("Spare blood", 3 , "Fireplace.png", Types.LIFE, SpellType.BUFFING, "Restore 8 health to hero/your minion");
    }
    /**
     * (SpareBlood) Podľa toho či patrí karta botovi alebo hráčovi vykoná svoju činnosť.
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
                String[] options = {"Player", "Minion"};
                int chosenOption = -1;
                while (chosenOption == -1) {
                    chosenOption = JOptionPane.showOptionDialog(null, "Choose ally:", "Choosing", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
                }
                if (chosenOption == 0) {
                    player.increaseHP(8);
                }
                if (chosenOption == 1) {
                    Choosing choosing = new Choosing();
                    Card chosenCard = choosing.chooseAMinion(myBattlefield);
                    myBattlefield.getCard(chosenCard).restoreHealth(8);
                }
            } else {
                player.increaseHP(8);
            }
        } else {
            Random random = new Random();
            int options = random.nextInt(0, 1);
            if (oponnentBattlefield.getCardsOnBattleField().size() > 0) {
                if (options == 1) {
                    oponnentPlayer.increaseHP(8);
                }
                if (options == 0) {
                    if (oponnentBattlefield.getCardsOnBattleField().size() != 1) {
                        int indexOfMinion = random.nextInt(0, oponnentBattlefield.getCardsOnBattleField().size() - 1);
                        oponnentBattlefield.getCardsOnBattleField().get(indexOfMinion).restoreHealth(8);
                    } else {
                        oponnentBattlefield.getCardsOnBattleField().get(0).restoreHealth(8);
                    }
                }
            } else {
                oponnentPlayer.increaseHP(8);
            }
        }
    }
}
