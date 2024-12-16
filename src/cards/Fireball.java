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
 * Trieda Fireball je reprezentácia jednej z kariet
 * @author (Marek Saniga)
 * @version (1.1)
 */
public class Fireball extends Card implements Initial {
    /**
     * (Fireball) Bezparametrický konštruktor, v ktorom sa inicializujú základné staty karty.
     */
    public Fireball() {
        super("Fireball", 2 , "Fireplace.png", Types.NONE, SpellType.WHENEVER, "Deal 4 damage to enemy");
    }
    /**
     * (Fireball) Podľa toho či patrí karta botovi alebo hráčovi vykoná svoju činnosť.
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
            if (oponnentBattlefield.getCardsOnBattleField().size() > 0) {
                String[] options = {"Player", "Minions"};
                int chosenOption = -1;
                while (chosenOption == -1) {
                    chosenOption = JOptionPane.showOptionDialog(null, "Choose enemy:", "Choosing", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
                }
                if (chosenOption == 0) {
                    oponnentPlayer.decreaseHP(4);
                }
                if (chosenOption == 1) {
                    Choosing choosing = new Choosing();
                    Card chosenCard = choosing.chooseAMinion(oponnentBattlefield);
                    oponnentBattlefield.getCard(chosenCard).subtractHealth(4);
                }
            } else {
                oponnentPlayer.decreaseHP(4);
            }
        } else {
            Random random = new Random();
            int options = random.nextInt(0, 1);
            if (myBattlefield.getCardsOnBattleField().size() > 0) {
                if (options == 1) {
                    player.decreaseHP(4);
                }
                if (options == 0) {
                    if (myBattlefield.getCardsOnBattleField().size() != 1) {
                        int indexOfMinion = random.nextInt(0, myBattlefield.getCardsOnBattleField().size() - 1);
                        myBattlefield.getCardsOnBattleField().get(indexOfMinion).subtractHealth(4);
                    } else {
                        myBattlefield.getCardsOnBattleField().get(0).subtractHealth(4);
                    }
                }
            } else {
                player.decreaseHP(4);
            }
        }
    }
}
