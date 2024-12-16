package insideGame;

import cards.LoneWanderer;
import cards.overall.Card;
import cards.overall.GameFrame;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

/**
 * V triede Rules sa nachádzajú všetky základné pravidlá, ktoré by mohli začiatočný hráči využiť.
 */
public class Rules implements ActionListener {
    private GameFrame gameFrame;
    private JPanel oponnentBattlefieldPanel;
    private JPanel yourBattlefieldPanel;
    private JPanel oponnentHandPanel;
    private JPanel yourHandPanel;
    private JButton endTurn;
    private JButton menuButton;
    private JButton next;
    private JButton previous;
    private JButton back;
    private JLabel playerHealth;
    private JLabel playerMana;
    private JLabel oponentHealth;
    private JLabel oponentMana;
    private Player oponent;
    private Player you;
    private int page = 1;

    /**
     * Konštruktor, ktorý vytvorí všetky potrebné komponenty na zobrazenie do GameFrame.
     */
    public Rules() {
        this.gameFrame = GameFrame.getInstance();
        this.gameFrame.setLayout(null);
        this.gameFrame.setTitle("Rules");
        this.gameFrame.getContentPane().setBackground(Color.decode("#323c39"));

        this.you = new Player("/Img/Life.png");
        this.you.getPlayerImage().setBounds(0, 635, 135, 130);
        this.oponent = new Player("/Img/Life.png");
        this.oponent.getPlayerImage().setBounds(1500 - 150, 0, 135, 130);

        this.oponnentBattlefieldPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        this.yourBattlefieldPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        this.oponnentHandPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        this.yourHandPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        this.oponnentHandPanel.setBounds(150, 0, 1200, 180);
        this.yourHandPanel.setBounds(150, 580, 1200, 180);
        this.oponnentBattlefieldPanel.setBounds(150, this.oponnentHandPanel.getY() + 190, 1200, 180);
        this.yourBattlefieldPanel.setBounds(150, this.yourHandPanel.getY() - 190, 1200, 180);

        this.oponnentBattlefieldPanel.setBackground(Color.decode("#f5f5dc"));
        this.yourBattlefieldPanel.setBackground(Color.decode("#f8f5dc"));
        this.oponnentHandPanel.setBackground(Color.decode("#f5f5dc"));
        this.yourHandPanel.setBackground(Color.decode("#f5f5dc"));

        this.endTurn = new JButton("End turn");
        this.endTurn.setBounds(1350, 345, 140, 80);

        this.menuButton = new JButton();
        this.menuButton.setBounds(0, 0, 52, 46);
        this.menuButton.setBorder(null);
        try {
            BufferedImage bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Img/menu/Menu wheel.png")));
            this.menuButton.setIcon(new ImageIcon(bufferedImage));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        JLabel textYourHand = new JLabel("This is Your Hand");
        JLabel textYourBattlefield = new JLabel("This is Your Battlefield");
        JLabel textOponentHand = new JLabel("This is Oponent Hand");
        JLabel textOponentBattlefield = new JLabel("This is Oponent Battlefield");

        textYourHand.setForeground(Color.BLACK);
        textYourBattlefield.setForeground(Color.BLACK);
        textOponentHand.setForeground(Color.BLACK);
        textOponentBattlefield.setForeground(Color.BLACK);

        textYourHand.setFont(new Font("Arial", Font.BOLD, 30));
        textYourBattlefield.setFont(new Font("Arial", Font.BOLD, 30));
        textOponentBattlefield.setFont(new Font("Arial", Font.BOLD, 30));
        textOponentHand.setFont(new Font("Arial", Font.BOLD, 30));

        this.next = new JButton("Next");
        this.next.setForeground(Color.BLACK);
        this.next.setBounds(1390, 500, 100, 80);
        this.next.addActionListener(this);
        this.next.setBorder(null);
        this.next.setBackground(Color.decode("#323c39"));
        this.next.setFocusable(false);

        this.previous = new JButton();
        this.previous.setForeground(Color.BLACK);
        this.previous.setBounds(0, 500, 100, 80);
        this.previous.addActionListener(this);
        this.previous.setBorder(null);
        this.previous.setBackground(Color.decode("#323c39"));
        this.previous.setFocusable(false);

        this.back = new JButton("Back");
        this.back.setForeground(Color.BLACK);
        this.back.setBounds(1390, 200, 100, 80);
        this.back.addActionListener(this);
        this.back.setBorder(null);
        this.back.setBackground(Color.decode("#323c39"));
        this.back.setFocusable(false);

        this.playerHealth = new JLabel();
        this.playerHealth.setForeground(Color.BLACK);
        this.playerHealth.setText("HP: 20/20");
        this.playerMana = new JLabel();
        this.playerMana.setForeground(Color.BLACK);
        this.playerMana.setText("Mana 0/0");
        this.oponentHealth = new JLabel();
        this.oponentHealth.setForeground(Color.BLACK);
        this.oponentHealth.setText("HP: 20/20");
        this.oponentMana = new JLabel();
        this.oponentMana.setForeground(Color.BLACK);
        this.oponentMana.setText("Mana: 0/0");

        this.playerHealth.setBounds(0, 600, 100, 50);
        this.playerMana.setBounds(0, 570, 100, 50);
        this.oponentMana.setBounds(1350, 140, 100, 50);
        this.oponentHealth.setBounds(1350, 110, 100, 50);

        this.oponnentBattlefieldPanel.add(textOponentBattlefield);
        this.oponnentHandPanel.add(textOponentHand);
        this.yourHandPanel.add(textYourHand);
        this.yourBattlefieldPanel.add(textYourBattlefield);

        this.gameFrame.add(this.oponnentBattlefieldPanel);
        this.gameFrame.add(this.yourBattlefieldPanel);
        this.gameFrame.add(this.oponnentHandPanel);
        this.gameFrame.add(this.yourHandPanel);
        this.gameFrame.add(this.next);
        this.gameFrame.add(this.previous);
        this.gameFrame.add(this.back);


        this.gameFrame.setVisible(true);
    }

    /**
     * (Rules) Nastaví všetky komponenty inicializované na začiatku znovu na viditeľné (používa sa na konci triedy, keďže v procese prechádzania pravidiel sa nastavujú na neviditeľné).
     */
    public void setEverythingVisible() {
        this.menuButton.setVisible(true);
        this.endTurn.setVisible(true);
        this.you.getPlayerImage().setVisible(true);
        this.oponent.getPlayerImage().setVisible(true);
        this.oponentHealth.setVisible(true);
        this.oponentMana.setVisible(true);
        this.playerMana.setVisible(true);
        this.playerHealth.setVisible(true);
    }

    /**
     * Odstráni všetky komponenty z GameFrame a následne pridá späť tlačidlá spať a ďalej.
     */
    public void removeAll() {
        Container contentPane = this.gameFrame.getContentPane();
        for (Component c : contentPane.getComponents()) {
            c.setVisible(false);
            contentPane.remove(c);
        }
        this.gameFrame.setLayout(null);
        this.next.setVisible(true);
        this.previous.setVisible(true);
        this.back.setVisible(true);
        this.gameFrame.add(this.next);
        this.gameFrame.add(this.back);
    }

    /**
     * (Rules) Odstráni všetky komponenty z GameFrame.
     */
    public void reallyRemoveAll() {
        Container contentPane = this.gameFrame.getContentPane();
        for (Component c : contentPane.getComponents()) {
            c.setVisible(false);
            contentPane.remove(c);
        }
        this.gameFrame.setLayout(null);
    }

    /**
     * (Rules) Metóda nastaví tlačidlám funkcie.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.next) {
            switch (this.page) {
                case 1 -> {
                    this.page++;
                    this.removeAll();
                    this.gameFrame.add(this.you.getPlayerImage());
                    this.gameFrame.add(this.oponent.getPlayerImage());
                    this.gameFrame.add(this.oponentHealth);
                    this.gameFrame.add(this.oponentMana);
                    this.gameFrame.add(this.playerHealth);
                    this.gameFrame.add(this.playerMana);
                    JLabel generalKnowlege = new JLabel("At start of each player turn they draw a card and gain 1 mana.");
                    JLabel oponentText = new JLabel("This is your oponent (bot); under his icon you can see his Hp/MaxHp; under his hp you can see his mana/maxMana.");
                    JLabel youText = new JLabel("This is you; over your icon you can see your Hp/MaxHp; and over hp you can see your mana/maxMana.");
                    oponentText.setFont(new Font("Arial", Font.BOLD, 22));
                    youText.setFont(new Font("Arial", Font.BOLD, 22));
                    generalKnowlege.setFont(new Font("Arial", Font.BOLD, 22));
                    oponentText.setBounds(150, 0, 1200, 100);
                    youText.setBounds(150, 650, 1200, 100);
                    generalKnowlege.setBounds(400, 330, 1200, 100);
                    oponentText.setForeground(Color.BLACK);
                    youText.setForeground(Color.BLACK);
                    generalKnowlege.setForeground(Color.BLACK);
                    this.gameFrame.add(oponentText);
                    this.gameFrame.add(youText);
                    this.gameFrame.add(generalKnowlege);
                }
                case 2 -> {
                    this.page++;
                    this.removeAll();
                    JLabel endTurnText = new JLabel("With this button you end your turn and start bot turn.");
                    JLabel menuButtonText = new JLabel("With this button you get to choose whether you like to go back to menu, continue in game or exit game entirely.");
                    endTurnText.setFont(new Font("Arial", Font.BOLD, 22));
                    menuButtonText.setFont(new Font("Arial", Font.BOLD, 22));
                    endTurnText.setBounds(800, 345, 1200, 100);
                    menuButtonText.setBounds(150, 0, 1200, 100);
                    endTurnText.setForeground(Color.BLACK);
                    menuButtonText.setForeground(Color.BLACK);
                    this.gameFrame.add(endTurnText);
                    this.gameFrame.add(menuButtonText);
                    this.gameFrame.add(this.endTurn);
                    this.gameFrame.add(this.menuButton);
                    break;
                }
                case 3 -> {
                    this.page++;
                    this.removeAll();
                    Card example = new LoneWanderer();
                    Card attackExample = new LoneWanderer();
                    JLabel exampleText = new JLabel("When you left click on card in your hand and have enough mana to play it it will be played to battlefield.");
                    JLabel rightClickText = new JLabel("When you right click on the all cards in your hand and battlefield it will show it health, attack, mana, type and ability.");
                    JLabel attackText = new JLabel("When you left click on minion on the battlefield with grey texture you get to choose whether you attack enemy hero or enemy minions.");
                    rightClickText.setBounds(180, 50, 1500, 50);
                    exampleText.setBounds(200, 270, 1500, 50);
                    attackText.setBounds(100, 330, 1500, 50);
                    rightClickText.setFont(new Font("Arial", Font.BOLD, 22));
                    exampleText.setFont(new Font("Arial", Font.BOLD, 22));
                    attackText.setFont(new Font("Arial", Font.BOLD, 22));
                    rightClickText.setForeground(Color.BLACK);
                    exampleText.setForeground(Color.BLACK);
                    attackText.setForeground(Color.BLACK);

                    BufferedImage bufferedImage;
                    try {
                        bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Img/" + attackExample.getNameOfImage())));
                    } catch (IOException ioException) {
                        throw new RuntimeException(ioException);
                    }
                    ImageIcon originalIcon = new ImageIcon(bufferedImage);
                    Image originalImage = originalIcon.getImage();
                    BufferedImage grayImage = new BufferedImage(originalImage.getWidth(null), originalImage.getHeight(null), BufferedImage.TYPE_BYTE_GRAY);
                    Graphics2D g2d = grayImage.createGraphics();
                    g2d.drawImage(originalImage, 0, 0, null);
                    g2d.dispose();
                    ImageIcon grayIcon = new ImageIcon(grayImage);
                    attackExample.getCardImage().setIcon(grayIcon);

                    example.getCardImage().setBounds(700, 100, 128, 160);
                    attackExample.getCardImage().setBounds(700, 400, 128, 160);

                    this.gameFrame.add(example.getCardImage());
                    this.gameFrame.add(attackExample.getCardImage());
                    this.gameFrame.add(attackText);
                    this.gameFrame.add(exampleText);
                    this.gameFrame.add(rightClickText);
                    break;
                }
            }
        }

        if (e.getSource() == this.back) {
            this.page = 1;
            this.reallyRemoveAll();
            this.gameFrame.dispose();
            this.setEverythingVisible();
            Menu menu = new Menu();
        }
    }
}
