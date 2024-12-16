package cards.overall;

import javax.swing.JFrame;

/**
 * Trieda GameFrame dedí z triedy JFrame a zároveň je Singleton. Trieda má funkciu zobrazovania triedy JFrame pre GUI projektu.
 * @author (Marek Saniga)
 * @version (1.0)
 */
public class GameFrame extends JFrame {
    private static GameFrame instance = null;

    /**
     * Súkromný konštruktor zmení parametre inštancie JFrame na požadované.
     */
    private GameFrame() {
        this.setSize(1500, 800);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * (GameFrame) Metóda nám vytvorí inštanciu triedy Gameframe pokiaľ už nebola vytvorená (Singleton).
     * @return Inštancia triedy GameFrame.
     */
    public static GameFrame getInstance() {
        if (instance == null) {
            instance = new GameFrame();
        }
        return instance;
    }

}
