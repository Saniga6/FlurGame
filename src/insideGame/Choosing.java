package insideGame;

import cards.overall.Card;

import javax.swing.JOptionPane;

/**
 * Trieda Choosing je pomocná trieda, ktorá slúži na výber z kariet na bojovom poli.
 * @author (Marek Saniga)
 * @version (1.1)
 */
public class Choosing {
    private int index = 0;
    private Card chosenCard = null;

    public Choosing() {

    }

    /**
     * (Choosing) Metóda nám vracia pole Stringov, ktoré pozostáva z 2 konštantných Stringov a jedného, ktorý sa mení podľa zadaného indexu a zadaného bojového poľa.
     * @param battlefield Vybrané bojové pole.
     * @param index Číslo v zozname kariet v bojovom poli.
     * @return pole Stringov.
     */
    public String[] getMinions(Battlefield battlefield, int index) {
        return new String[]{"Previous page", battlefield.getCardsOnBattleField().get(index).getNameOfCard(), "Next page"};
    }

    /**
     * (Choosing) Metóda, ktorá používa JOptionPane na vybranie si možností karty, zmeny karty na predošlú alebo zmeny karty na ďalšiu podľa zoznamu kariet zo zadaného bojového poľa.
     * Pokiaľ bola vybraná možnosť karta zistí sa jej meno z výberu možnosti a bojové pole si zistí z jej názvu o akú kartu sa jedná a tú kartu metóda vráti.
     * @param battlefield Vybrané bojové pole.
     * @return Vybranú kartu zo zoznamu bojového pola.
     */
    public Card chooseAMinion(Battlefield battlefield) {
        int choosing = -1;
        while (choosing == -1) {
            choosing = JOptionPane.showOptionDialog(null, "Choose a minion", "Choosing", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, this.getMinions(battlefield, this.index), -1);
        }
        if (choosing == 0) {
            if (this.index > 0) {
                this.index--;
                this.chooseAMinion(battlefield);
            } else {
                this.chooseAMinion(battlefield);
            }
        }
        if (choosing == 1) {
            String[] chosenOptions = this.getMinions(battlefield, this.index);
            String chosenMinion = chosenOptions[1];
            this.chosenCard = battlefield.getCardOutOfString(chosenMinion);
            this.index = 0;
        }
        if (choosing == 2) {
            if (this.index < battlefield.getCardsOnBattleField().size() - 1) {
                this.index++;
                this.chooseAMinion(battlefield);
            } else {
                this.chooseAMinion(battlefield);
            }
        }
        choosing = 1;
        return this.chosenCard;
    }
}
