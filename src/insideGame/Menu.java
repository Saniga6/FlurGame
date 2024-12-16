package insideGame;


import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

/**
 * Trieda Menu je základná trieda, ktorá sa volá v Main triede, ktorá otvára ostatné okná.
 * @author (Marek Saniga)
 * @version (1.0)
 */
public class Menu implements ActionListener {
    private JButton play;
    private JButton end;
    private JButton rulesButton;
    private JButton buildDeck;
    private JFrame menuFrame;
    private JLabel gameName;
    public Menu() {
        this.menuFrame = new JFrame();

        try {
            BufferedImage bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Img/menu/Main menu.png")));
            this.gameName = new JLabel(new ImageIcon(bufferedImage));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.gameName.setBounds(0, 0, 1000, 750);


        this.play = new JButton("Play");
        this.play.setBounds(400, 280, 200, 50);
        this.play.addActionListener(this);
        this.play.setBackground(Color.decode("#111120"));
        this.play.setForeground(Color.white);
        this.play.setFocusable(false);

        this.buildDeck = new JButton("Build deck");
        this.buildDeck.setBounds(400, 330, 200, 50);
        this.buildDeck.addActionListener(this);
        this.buildDeck.setBackground(Color.decode("#111120"));
        this.buildDeck.setForeground(Color.white);
        this.buildDeck.setFocusable(false);

        this.rulesButton = new JButton("Basic rules");
        this.rulesButton.setBounds(400, 450, 200, 50);
        this.rulesButton.addActionListener(this);
        this.rulesButton.setBackground(Color.decode("#111120"));
        this.rulesButton.setForeground(Color.white);
        this.rulesButton.setFocusable(false);

        this.end = new JButton("End game");
        this.end.setBounds(400, 550, 200, 50);
        this.end.addActionListener(this);
        this.end.setBackground(Color.decode("#111120"));
        this.end.setForeground(Color.white);
        this.end.setFocusable(false);

        this.menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.menuFrame.setSize(1000, 750);
        this.menuFrame.setLocationRelativeTo(null);
        this.menuFrame.setTitle("Flur");
        this.menuFrame.setResizable(false);

        this.menuFrame.add(this.play);
        this.menuFrame.add(this.end);
        this.menuFrame.add(this.rulesButton);
        this.menuFrame.add(this.buildDeck);
        this.menuFrame.add(this.gameName);

        this.menuFrame.setVisible(true);
    }

    /**
     * (Menu) Činnosť tlačidiel v hlavnom menu
     * @param a the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent a) {

        if (a.getSource() == this.play) {
            this.menuFrame.dispose();
            PlayMenu playMenu = new PlayMenu();
        }

        if (a.getSource() == this.end) {
            this.menuFrame.dispose();
            System.exit(0);
        }

        if (a.getSource() == this.rulesButton) {
            this.menuFrame.dispose();
            Rules rules = new Rules();
        }

        if (a.getSource() == this.buildDeck) {
            this.menuFrame.dispose();
            DeckBuilding deckBuilding = new DeckBuilding();
        }
    }
}

