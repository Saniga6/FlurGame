package insideGame;

import cards.overall.Card;

import java.util.ArrayList;

/**
 * Trieda Hand predstavuje ruku hráča do ktorej budú prichádzať a odchádzať karty.
 */
public class Hand {
    private ArrayList<Card> cardsInHand;

    /**
     * Konštruktor vytvorí nový prázdny zoznam kariet v ruke.
     */
    public Hand() {
        this.cardsInHand = new ArrayList<>();
    }

    /**
     * (Hand) Metóda pridá kartu do ruky pokiaľ je počet kariet v ruke menší než 8.
     * @param card Pridávaná karta.
     */
    public void addCardToHand(Card card) {
        if (this.cardsInHand.size() < 9) {
            this.cardsInHand.add(card);
        }
    }

    /**
     * (Hand) Metóda vráti zoznam kariet v ruke.
     * @return Zoznam kariet v ruke.
     */
    public ArrayList<Card> getCardsInHand() {
        return this.cardsInHand;
    }
}
