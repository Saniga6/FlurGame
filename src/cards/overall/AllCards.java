package cards.overall;

import cards.AcolyteOfDeath;
import cards.AngryWolf;
import cards.Behemoth;
import cards.Cake;
import cards.Fireball;
import cards.Fireplace;
import cards.Frostburn;
import cards.HeraldOfChaos;
import cards.InstantDeath;
import cards.LoneWanderer;
import cards.MysticRevelation;
import cards.Pumpkinman;
import cards.Scriber;
import cards.SpareBlood;
import cards.TinyGoblin;
import cards.TomeOfStrength;
import cards.Trickster;
import cards.Vampire;
import cards.Yeti;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

/**
 * Trieda AllCards reprezentuje všetky karty v hre a ich následné delenie na podčasti.
 * @author (Marek Saniga)
 * @version (1.0)
 */
public class AllCards {
    private ArrayList<Card> allCards = new ArrayList<>();
    private ArrayList<Card> allBotCards = new ArrayList<>();
    private ArrayList<Card> cardsInDeck;
    private ArrayList<Card> allSpells = new ArrayList<>();
    private ArrayList<Card> allMinions = new ArrayList<>();

    /**
     * (AllCards) Konštruktor pridá karty do zoznamu všetkých kariet. Následne ich ešte porozdeľuje podľa typu karty na zoznam minionov a zoznam spellov.
     */
    public AllCards() {
        this.allCards.add(new LoneWanderer());
        this.allCards.add(new AngryWolf());
        this.allCards.add(new Pumpkinman());
        this.allCards.add(new Fireplace());
        this.allCards.add(new Trickster());
        this.allCards.add(new TomeOfStrength());
        this.allCards.add(new Fireball());
        this.allCards.add(new Yeti());
        this.allCards.add(new SpareBlood());
        this.allCards.add(new Vampire());
        this.allCards.add(new Cake());
        this.allCards.add(new Scriber());
        this.allCards.add(new Frostburn());
        this.allCards.add(new InstantDeath());
        this.allCards.add(new Behemoth());
        this.allCards.add(new AcolyteOfDeath());
        this.allCards.add(new TinyGoblin());
        this.allCards.add(new HeraldOfChaos());
        this.allCards.add(new MysticRevelation());

        for (var card : this.allCards) {
            if (card.getCardType().equals(CardType.MINION)) {
                this.allMinions.add(card);
            }
        }

        for (var card : this.allCards) {
            if (card.getCardType().equals(CardType.SPELL)) {
                this.allSpells.add(card);
            }
        }
    }

    /**
     *(AllCards)
     * @return Vráti zoznam všetkých kariet
     */
    public ArrayList<Card> getAllCards() {
        return this.allCards;
    }

    /**
     *(AllCards)
     * @param nameOfCard Meno hľadanej karty.
     * @return Vráti kartu podľa mena hľadanej karty.
     */
    public Card getCardFromName(String nameOfCard) {
        for (var card : this.allCards) {
            if (card.getNameOfCard().equals(nameOfCard)) {
                return card;
            }
        }
        return null;
    }

    /**
     * (AllCards) Metóda vytvorí balík pre bota
     * @return Zoznam kariet pre bota.
     */
    public ArrayList<Card> botDeck() {
        ArrayList<Card> botCards = new ArrayList<>();
        this.allBotCards.add(new LoneWanderer());
        this.allBotCards.add(new AngryWolf());
        this.allBotCards.add(new Pumpkinman());
        this.allBotCards.add(new Fireplace());
        this.allBotCards.add(new Trickster());
        this.allBotCards.add(new TomeOfStrength());
        this.allBotCards.add(new Fireball());
        this.allBotCards.add(new Yeti());
        this.allBotCards.add(new SpareBlood());
        this.allBotCards.add(new Vampire());
        this.allBotCards.add(new Cake());
        this.allBotCards.add(new Scriber());
        this.allBotCards.add(new Frostburn());
        this.allBotCards.add(new InstantDeath());
        this.allBotCards.add(new Behemoth());
        this.allBotCards.add(new AcolyteOfDeath());
        this.allBotCards.add(new TinyGoblin());
        this.allBotCards.add(new HeraldOfChaos());
        this.allBotCards.add(new MysticRevelation());

        Collections.shuffle(this.allBotCards);
        int counting = 0;
        for (var card : this.allBotCards) {
            if (counting < 14) {
                counting++;
                botCards.add(card);
            } else {
                break;
            }
        }
        for (var card : botCards) {
            try {
                BufferedImage bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Img/Bot card in hand.png")));
                card.getCardImage().setIcon(new ImageIcon(bufferedImage));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            card.setBotCard();
        }
        return botCards;
    }

    /**
     * (AllCards)
     * @return Vráti náhodného miniona zo zoznamu minionov.
     */
    public Card getRandomMinion() {
        Collections.shuffle(this.allMinions);
        return this.allMinions.get(0);
    }

    /**
     * (AllCards)
     * @return Vráti náhodný spell zo zoznamu spellov.
     */
    public Card getRandomSpell() {
        Collections.shuffle(this.allSpells);
        return this.allSpells.get(0);
    }

    /**
     * (AllCards)
     * @return Vráti náhodnú kartu spomedzi všetkých kariet.
     */
    public Card getRandomCard() {
        Collections.shuffle(this.allCards);
        return this.allCards.get(0);
    }
}
