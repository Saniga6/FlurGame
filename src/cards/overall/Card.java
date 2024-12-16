package cards.overall;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * Trieda karta reprezentuje základný vzor pre každú kartu, obsahuje všetky metódy potrebné na funkčnosť karty.
 * @author (Marek Saniga)
 * @version (1.0)
 */
public class Card {
    private JLabel cardImage;
    private JDialog info;
    private String name;
    private String nameOfImage;
    private int mana;
    private int health;
    private int attack;
    private int maxHP;
    private Types type;
    private String textOfCard;
    private CardType cardType;
    private SpellType spellType;
    private boolean isInAllCards = true;
    private boolean canAttack = false;
    private boolean botCard = false;

    /**
     * (Card) Parametrický konštruktor na vytvorenie karty typu minion.
     * @param name Meno karty.
     * @param mana Cena karty.
     * @param health Životy karty.
     * @param attack Sila útoku karty.
     * @param imageName Meno obrázku karty.
     * @param type Typ karty (LIFE/DEATH/CHOAS/NATURE).
     * @param textOfCard Popis schopnosti karty.
     */
    public Card(String name, int mana, int health, int attack, String imageName, Types type, String textOfCard) {
        this.attack = attack;
        this.mana = mana;
        this.name = name;
        this.health = health;
        this.maxHP = health;
        this.type = type;
        this.cardType = CardType.MINION;
        this.textOfCard = textOfCard;
        this.nameOfImage = imageName;
        Label text = new Label(this.getNameOfCard() + "Attack: " +  this.getAttack() + " Health: " + this.getHealth());
        this.info = new JDialog();
        this.info.setLocationRelativeTo(null);
        this.info.add(text);
        info.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);

        try {
            BufferedImage bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Img/" + imageName)));
            this.cardImage = new JLabel(new ImageIcon(bufferedImage));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.changeImagePosition();
        this.cardImage.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                showInfoWindow(); // Zobrazenie informačného okna pri prejdení myšou nad kartou
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                hideInfoWindow(); // Skrytie informačného okna pri opustení oblasti karty myšou
            }
        });
    }

    /**
     * (Card) Parametricky konštruktor na vytvorenie karty typu spell.
     * @param name Meno karty.
     * @param mana Cena karty.
     * @param imageName Meno obrázku karty.
     * @param type Typ karty (LIFE/DEATH/CHOAS/NATURE).
     * @param textOfCard Popis schopnosti karty.
     */
    public Card(String name, int mana, String imageName, Types type, SpellType spellType ,String textOfCard) {
        this.mana = mana;
        this.name = name;
        this.type = type;
        this.textOfCard = textOfCard;
        this.cardType = CardType.SPELL;
        this.nameOfImage = imageName;
        this.spellType = spellType;
        Label text = new Label(this.getNameOfCard() + "Attack: " +  this.getAttack() + " Health: " + this.getHealth());
        this.info = new JDialog();
        this.info.setLocationRelativeTo(null);
        this.info.add(text);
        info.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);

        try {
            BufferedImage bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Img/" + imageName)));
            this.cardImage = new JLabel(new ImageIcon(bufferedImage));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.changeImagePosition();
        this.cardImage.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                showInfoWindow(); // Zobrazenie informačného okna pri prejdení myšou nad kartou
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                hideInfoWindow(); // Skrytie informačného okna pri opustení oblasti karty myšou
            }
        });
    }

    private void showInfoWindow() {
        info.setVisible(true);
    }

    private void hideInfoWindow() {
        info.setVisible(false); // Skrytie informačného okna
    }

    public boolean isBotCard() {
        return this.botCard;
    }
    public int getMaxHP() {
        return this.maxHP;
    }
    public void changeIsInAllCards() {
        this.isInAllCards = !this.isInAllCards;
    }
    public boolean isInAllCards() {
        return this.isInAllCards;
    }
    public String getNameOfImage() {
        return this.nameOfImage;
    }
    public String getNameOfCard() {
        return this.name;
    }
    public Types getTypeOfCard() {
        return this.type;
    }
    public SpellType getSpellType() {
        return this.spellType;
    }
    public int getMana() {
        return this.mana;
    }
    public JLabel getCardImage() {
        return this.cardImage;
    }
    public String getTextOfCard() {
        return this.textOfCard;
    }
    public int getHealth() {
        if (this.cardType.equals(CardType.MINION)) {
            return this.health;
        }
        return 0;
    }
    public int getAttack() {
        if (this.cardType.equals(CardType.MINION)) {
            return this.attack;
        }
        return 0;
    }


    public CardType getCardType() {
        return this.cardType;
    }

    public boolean getCanAttack() {
        return this.canAttack;
    }
    public void canAttack() {
        this.canAttack = true;
    }
    public void cannotAttack() {
        this.canAttack = false;
    }

    public boolean isInPanel(JPanel jPanel) {
        Component[] components = jPanel.getComponents();
        for (var card : components) {
            if (this.cardImage.equals(card)) {
                return true;
            }
        }
        return false;
    }

    public void subtractAttack(int minusAttack) {
        if (this.cardType.equals(CardType.MINION)) {
            this.attack -= minusAttack;
        }
    }

    public void addAttack(int plusAttack) {
        if (this.cardType.equals(CardType.MINION)) {
            this.attack += plusAttack;
        }
    }

    public void subtractHealth(int minusHealth) {
        if (this.cardType.equals(CardType.MINION)) {
            this.health -= minusHealth;
        }
    }

    public void addHealth(int plusHealth) {
        if (this.cardType.equals(CardType.MINION)) {
            this.health += plusHealth;
            this.maxHP = this.health;
        }
    }

    public void restoreHealth(int heal) {
        if (this.cardType.equals(CardType.MINION) && heal + this.health <= this.maxHP) {
            this.health += heal;
        } else {
            int restoringHP = this.maxHP;
            this.health = restoringHP;
        }
    }

    public void subtractMana(int minusMana) {
        if (this.mana >= minusMana) {
            this.mana -= minusMana;
        } else {
            this.mana = 0;
        }
    }

    public void addMana(int plusMana) {
        if (this.mana + plusMana < 10) {
            this.mana += plusMana;
        } else {
            this.mana = 10;
        }
    }

    public void changeImagePosition() {
        this.cardImage.setVisible(false);
        this.cardImage.setBounds(this.cardImage.getX(), this.cardImage.getY(), this.cardImage.getWidth(), this.cardImage.getHeight());
        this.cardImage.setVisible(true);
    }

    public boolean isAlive() {
        return this.health > 0;
    }

    public void setBotCard() {
        this.botCard = true;
    }
}

