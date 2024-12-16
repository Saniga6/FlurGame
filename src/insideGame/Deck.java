package insideGame;

import cards.overall.Card;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Trieda Deck obsahuje zoznam kariet v balíku, z ktorých si každý hráč nasledne ťahá karty.
 * @author (Marek Saniga)
 * @version (1.0)
 */
public class Deck {
    private ArrayList<Card> cardInDeck;
    private int emptiness = 1;

    /**
     * Parametrický konštruktor, ktorý berie zoznam kariet a pridá ho do zoznamu kariet v balíku a zamieša ho.
     * @param cards Zoznam kariet.
     */
    public Deck(ArrayList<Card> cards) {
        this.cardInDeck = cards;
        Collections.shuffle(this.cardInDeck);
    }

    /**
     * (Deck) Metóda vracia zoznam kariet v balíku.
     * @return Karty v balíku.
     */
    public ArrayList<Card> getCardsInDeck() {
        return this.cardInDeck;
    }

    /**
     * (Deck) Pridá si kartu na ruku z balíčka, pokiaľ hráč nemá v ruke viac, ako 8 kariet v tom prípade kartu z balíčka zahodí.
     * Ak už nie sú žiadne karty v balíčku znižuje počet životov hráčovi a zvýši toto poškodenie do hráča.
     * @param hand Hráčova ruka.
     * @param player Hráč.
     */
    public void drawACard(Hand hand, Player player) {
        if (!this.cardInDeck.isEmpty()) {
            if (hand.getCardsInHand().size() < 8) {
                hand.getCardsInHand().add(this.cardInDeck.get(0));
                this.cardInDeck.remove(0);
            } else {
                JOptionPane.showMessageDialog(null, this.getCardsInDeck().get(0).getNameOfCard() + " have been burnt ", "Burnt card", JOptionPane.WARNING_MESSAGE);
                this.cardInDeck.remove(0);
            }
        } else {
            player.decreaseHP(this.emptiness);
            this.emptiness++;
        }
    }
}
