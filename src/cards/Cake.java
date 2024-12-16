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
 * Trieda Cake je reprezentácia jednej z kariet
 * @author (Marek Saniga)
 * @version (1.0)
 */
public class Cake extends Card implements Initial {
    /**
     * (Cake) Bezparametrický konštruktor, v ktorom sa inicializujú základné staty karty.
     */
    public Cake() {
        super("Cake", 1 , "Tome of strength.png", Types.CHAOS, SpellType.WHENEVER, "Choose one: Add 2 copies of this card to your hand // Give your minion +1/+1");
    }
    /**
     * (Cake) Podľa toho či patrí karta botovi alebo hráčovi vykoná svoju činnosť.
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
                String[] options = {"Copies", "Buffing"};
                int chosenOption = -1;
                while (chosenOption == -1) {
                    chosenOption = JOptionPane.showOptionDialog(null, "Choose activity:", "Choosing", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
                }
                if (chosenOption == 0) {
                    myHand.addCardToHand(new Cake());
                    myHand.addCardToHand(new Cake());
                } else if (chosenOption == 1) {
                    Choosing choosing = new Choosing();
                    Card chosenCard = choosing.chooseAMinion(myBattlefield);
                    myBattlefield.getCard(chosenCard).addHealth(1);
                    myBattlefield.getCard(chosenCard).addAttack(1);
                }
            } else {
                myHand.addCardToHand(new Cake());
                myHand.addCardToHand(new Cake());
            }
        } else {
            Random random = new Random();
            int options = random.nextInt(0, 1);
            if (oponnentBattlefield.getCardsOnBattleField().size() > 0) {
                if (options == 1) {
                    Card cake = new Cake();
                    Card cake2 = new Cake();
                    cake.setBotCard();
                    cake2.setBotCard();
                    oponnentHand.addCardToHand(cake);
                    oponnentHand.addCardToHand(cake2);
                }
                if (options == 0) {
                    if (oponnentBattlefield.getCardsOnBattleField().size() != 1) {
                        int indexOfMinion = random.nextInt(0, oponnentBattlefield.getCardsOnBattleField().size() - 1);
                        oponnentBattlefield.getCardsOnBattleField().get(indexOfMinion).addAttack(1);
                        oponnentBattlefield.getCardsOnBattleField().get(indexOfMinion).addHealth(1);
                    } else {
                        oponnentBattlefield.getCardsOnBattleField().get(0).addAttack(1);
                        oponnentBattlefield.getCardsOnBattleField().get(0).addHealth(1);
                    }
                }
            } else {
                Card cake = new Cake();
                Card cake2 = new Cake();
                cake.setBotCard();
                cake2.setBotCard();
                oponnentHand.addCardToHand(cake);
                oponnentHand.addCardToHand(cake2);
            }
        }
    }
}
