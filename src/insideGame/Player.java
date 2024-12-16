package insideGame;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

/**
 * Trieda Player reprezentuje hráča/bota, ktorý ma informácie o svojom počte životov a počte many a obrázku pre neho.
 * @author (Marek Saniga)
 * @version (1.0)
 */
public class Player {
    private JLabel playerImage;
    private int healthPoints;
    private int mana;
    private int maxMana = 0;

    /**
     * Parametrický konštruktor v ktorom sa nastaví obrázok hráča, počet životov a počet many na začiatočný stav.
     * @param imageName Názov obrázku pre hráča.
     */
    public Player(String imageName) {
        this.mana = 0;
        this.healthPoints = 25;
        try {
            BufferedImage bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResource(imageName)));
            this.playerImage = new JLabel(new ImageIcon(bufferedImage));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.playerImage.setBounds(20, 20, this.playerImage.getWidth(), this.playerImage.getHeight());
    }

    /**
     * (Player) Vráti počet životov hráča.
     * @return Počet životov hráča.
     */
    public int getHealthPoints() {
        return this.healthPoints;
    }

    /**
     * (Player) Vráti počet many hráča.
     * @return Počet many hráča.
     */
    public int getMana() {
        return this.mana;
    }

    /**
     * (Player) Vráti maximálny počet many hráča.
     * @return Maximálny počet many pre hráča.
     */
    public int getMaxMana() {
        return this.maxMana;
    }

    /**
     * (Player) Zníži počet životov o dané číslo zadané pri vstupe.
     * @param damage Počet životov odstránených z hráčových životov.
     */
    public void decreaseHP(int damage) {
        this.healthPoints -= damage;
    }

    /**
     * (Player) Vylieči hráča o dané číslo zadané pri vstupe do maxima 25.
     * @param heal Počet liečených životov.
     */
    public void increaseHP(int heal) {
        if (this.healthPoints + heal <= 25) {
            this.healthPoints += heal;
        }
        this.healthPoints = 25;
    }

    /**
     * (Player) Odčítanie many hráča od hodnoty vstupu.
     * @param cost Cena many.
     */
    public void payForMana(int cost) {
        this.mana -= cost;
    }

    /**
     * (Player) Získanie 1 many pokiaľ nie je maximálna mana na hodnote 10, v tom prípade sa mana nezvyšuje a zostáva na hodnote 10.
     */
    public void increaseMana() {
        if (this.maxMana < 10) {
            this.maxMana++;
        } else {
            this.maxMana = 10;
        }
        this.mana = this.maxMana;
    }

    /**
     * (Player) Vráti obrázok hráča na následnú manipuláciu s ním.
     * @return JLabel obrázok hráča.
     */
    public JLabel getPlayerImage() {
        return this.playerImage;
    }
}
