package insideGame;

import cards.LoneWanderer;
import cards.overall.Card;
import java.util.ArrayList;

/**
 * Trieda Battlefield slúži na reprezentáciu zoznamu kariet, ktoré sa nachádzaju na bojovom poli, ktoré vie následne prehľadávať a mazať.
 * @author (Marek Saniga)
 * @version (1.0)
 */
public class Battlefield {
    private ArrayList<Card> cardOnBattleField;

    /**
     * Konštruktor, ktorý nám vytvorí nový prázdny zoznam kariet na bojovom poli.
     */
    public Battlefield() {
        this.cardOnBattleField = new ArrayList<>();
    }

    /**
     * (Battlefield) Metóda na nájdenie karty podľa mena karty.
     * @param name Meno karty.
     * @return Hľadaná karta.
     */
    public Card getCardOutOfString(String name) {
        Card foundCard = new LoneWanderer();
        for (var card : this.cardOnBattleField) {
            if (card.getNameOfCard().equals(name)) {
                foundCard = card;
            }
        }
        return foundCard;
    }

    /**
     * (Battlefield) Metóda na celkové hľadanie karty.
     * @param wantedCard Hľadaná karta.
     * @return Nájdená karta / null.
     */
    public Card getCard(Card wantedCard) {
        for (var card : this.cardOnBattleField) {
            if (card.equals(wantedCard)) {
                return card;
            }
        }
        return null;
    }

    /**
     * (Battlefield) Metóda, ktorá vracia zoznam kariet na bojovom poli.
     * @return Zoznam kariet.
     */
    public ArrayList<Card> getCardsOnBattleField() {
        return this.cardOnBattleField;
    }

    /**
     * (Battlefield) Metóda, ktorá vracia to či je zoznam kariet prázdny.
     * @return true/false.
     */
    public boolean isEmpty() {
        return this.cardOnBattleField.isEmpty();
    }

    /**
     * (Battlefield) Metóda, ktorá odstraňuje zo zoznamu kariet zadanú kartu.
     * @param card Zadaná karta.
     */
    public void removeFromBattlefield(Card card) {
        this.cardOnBattleField.remove(card);
    }
}
