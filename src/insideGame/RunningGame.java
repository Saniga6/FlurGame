package insideGame;

import cards.overall.Card;
import cards.overall.CardType;
import cards.overall.Dying;
import cards.overall.GameFrame;
import cards.overall.Initial;
import cards.overall.SpellType;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

/**
 * Trieda, v ktorej je zakomponovaná celá logika plynutia hry.
 */
public class RunningGame implements ActionListener {
    private GameFrame gameFrame;
    private JPanel oponnentBattlefieldPanel;
    private JPanel yourBattlefieldPanel;
    private JPanel oponnentHandPanel;
    private JPanel yourHandPanel;
    private JButton endTurn;
    private JButton menuButton;
    private JLabel playerHealth;
    private JLabel playerMana;
    private JLabel oponentHealth;
    private JLabel oponentMana;
    private JLabel oponentDeckInfo;
    private JLabel yourDeckInfo;
    private Deck oponentDeck;
    private Deck yourDeck;
    private Player oponent;
    private Player you;
    private Hand oponnentHand;
    private Hand yourHand;
    private Battlefield oponnentBattlefield;
    private Battlefield yourBattlefield;
    private boolean gameEnded = false;
    /**
     * Konštruktor triedy vytvorí všetky grafické časti hry potrebné na hranie hry a podľa vložených tried Deck vytvorí balíky pre hráča a pre bota, následne začne
     * hru, nastaví potrebné texty v hre pre lepšiu navigáciu v hre a náhodne sa začne nepriateľovo alebo tvoje kolo.
     * @param yourDeck
     * @param oponentDeck
     */
    public RunningGame(Deck yourDeck, Deck oponentDeck) {
        this.yourHand = new Hand();
        this.yourBattlefield = new Battlefield();
        this.oponnentBattlefield = new Battlefield();
        this.oponnentHand = new Hand();
        this.yourDeck = yourDeck;
        this.oponentDeck = oponentDeck;

        this.gameFrame = GameFrame.getInstance();
        this.gameFrame.setLayout(null);
        this.gameFrame.setTitle("Game");
        this.gameFrame.getContentPane().setBackground(Color.decode("#323c39"));

        this.you = new Player("/Img/Death.png");
        this.you.getPlayerImage().setBounds(0, 635, 135, 130);
        this.oponent = new Player("/Img/Death.png");
        this.oponent.getPlayerImage().setBounds(1500 - 150, 0, 135, 130);

        this.oponentDeckInfo = new JLabel("Cards in bot deck: 15/15");
        this.yourDeckInfo = new JLabel("Cards in your deck: 15/15");
        this.oponentDeckInfo.setBounds(0, 300, 200, 50);
        this.yourDeckInfo.setBounds(0, 420, 200, 50);
        this.oponentDeckInfo.setForeground(Color.BLACK);
        this.yourDeckInfo.setForeground(Color.BLACK);

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
        this.endTurn.addActionListener(this);

        this.menuButton = new JButton();
        this.menuButton.setBounds(0, 0, 52, 46);
        this.menuButton.addActionListener(this);
        this.menuButton.setBorder(null);
        try {
            BufferedImage bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Img/menu/Menu wheel.png")));
            this.menuButton.setIcon(new ImageIcon(bufferedImage));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.playerHealth = new JLabel();
        this.playerHealth.setForeground(Color.BLACK);
        this.playerMana = new JLabel();
        this.playerMana.setForeground(Color.BLACK);
        this.oponentHealth = new JLabel();
        this.oponentHealth.setForeground(Color.BLACK);
        this.oponentMana = new JLabel();
        this.oponentMana.setForeground(Color.BLACK);

        this.playerHealth.setBounds(0, 600, 100, 50);
        this.playerMana.setBounds(0, 570, 100, 50);
        this.oponentMana.setBounds(1350, 140, 100, 50);
        this.oponentHealth.setBounds(1350, 110, 100, 50);

        this.gameFrame.add(this.oponnentBattlefieldPanel);
        this.gameFrame.add(this.yourBattlefieldPanel);
        this.gameFrame.add(this.oponnentHandPanel);
        this.gameFrame.add(this.yourHandPanel);
        this.gameFrame.add(this.you.getPlayerImage());
        this.gameFrame.add(this.oponent.getPlayerImage());
        this.gameFrame.add(this.endTurn);
        this.gameFrame.add(this.menuButton);
        this.gameFrame.add(this.playerHealth);
        this.gameFrame.add(this.playerMana);
        this.gameFrame.add(this.oponentHealth);
        this.gameFrame.add(this.oponentMana);
        this.gameFrame.add(this.yourDeckInfo);
        this.gameFrame.add(this.oponentDeckInfo);

        this.gameFrame.setVisible(true);

        this.startOfGame();
        this.updateText();
        Random random = new Random();
        int firstPlayer = random.nextInt(0, 2);
        if (firstPlayer == 0) {
            JOptionPane.showMessageDialog(null, "Game has started you are going first", "Start of game", JOptionPane.INFORMATION_MESSAGE);
            this.myTurn();
        } else if (firstPlayer == 1) {
            JOptionPane.showMessageDialog(null, "Game has started bot is going first", "Start of game", JOptionPane.INFORMATION_MESSAGE);
            this.oponentTurn();
        }
    }

    /**
     *(RunningGame) Aktualizuje informácie o hráčových a protivníkových počtoch životov a počte many.
     */
    private void updateText() {
        this.playerHealth.setText("HP: " + this.you.getHealthPoints() + "/25");
        this.playerMana.setText("Mana: " + this.you.getMana() + "/" + this.you.getMaxMana());
        this.oponentHealth.setText("HP: " + this.oponent.getHealthPoints() + "/25");
        this.oponentMana.setText("Mana: " + this.oponent.getMana() + "/" + this.oponent.getMaxMana());
    }

    /**
     * (RunningGame) Aktualizuje informácie o zostávajúcom počte kariet v balíku hráča a protivníka.
     */
    private void updateDeckText() {
        this.oponentDeckInfo.setText("Cards in bot deck: " + this.oponentDeck.getCardsInDeck().size() + "/15");
        this.yourDeckInfo.setText("Cards in your deck: " + this.yourDeck.getCardsInDeck().size() + "/15");
    }

    /**
     * (RunningGame) Kontrola počtu životov oboch hráčov
     * @return Vracia informáciu o tom či počet životov klesnul na nulu alebo pod nulu.
     */
    private boolean deathOfPlayer() {
        return this.you.getHealthPoints() <= 0 || this.oponent.getHealthPoints() <= 0;
    }

    /**
     * (RunningGame) Výpis o víťazstve alebo prehre hráča v hre.
     */
    private void setResultOfGame() {
        if (this.deathOfPlayer()) {
            if (this.you.getHealthPoints() > this.oponent.getHealthPoints()) {
                this.gameEnded = true;
                String[] options = {"Left game", "Back to menu"};
                String message = "🏆🏆🏆 You have won!!! 🏆🏆🏆";
                int choose = -1;
                while (choose == -1) {
                    choose = JOptionPane.showOptionDialog(null, message, "Victory", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, 0);
                }
                if (choose == 0) {
                    System.exit(0);
                } else if (choose == 1) {
                    this.removeAll();
                    Menu menu = new Menu();
                }
            }

            if (this.you.getHealthPoints() < this.oponent.getHealthPoints()) {
                String[] options = {"Left game", "Back to menu"};
                String message = "😂😂😂 You have lost 😂😂😂";
                this.gameEnded = true;
                int choose = -1;
                while (choose == -1) {
                    choose = JOptionPane.showOptionDialog(null, message, "Loss", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, 0);
                }
                if (choose == 0) {
                    System.exit(0);
                } else if (choose == 1) {
                    this.removeAll();
                    Menu menu = new Menu();
                }
            }
        }
    }

    /**
     * (RunningGame) Prvotné ťahanie kariet na začiatku hry.
     */
    private void startOfGame() {
        this.youDraw();
        this.youDraw();
        this.youDraw();

        this.oponnentDraw();
        this.oponnentDraw();
        this.oponnentDraw();
    }

    /**
     * (RunningGame) Kolo hráča, prebehnú kontroly na správny chod hry a aktualizácie pre informácie o hráčskych informáciách,
     * získanie many a potiahnutie si karty, následne hráč môže zahrať kartu alebo ukončiť kolo a zavolá sa kolo bota.
     */
    private void myTurn() {
        if (!this.deathOfPlayer()) {
            for (var card : this.yourBattlefield.getCardsOnBattleField()) {
                card.canAttack();
                BufferedImage bufferedImage;
                try {
                    bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Img/" + card.getNameOfImage())));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                ImageIcon originalIcon = new ImageIcon(bufferedImage);
                Image originalImage = originalIcon.getImage();
                BufferedImage grayImage = new BufferedImage(originalImage.getWidth(null), originalImage.getHeight(null), BufferedImage.TYPE_BYTE_GRAY);
                Graphics2D g2d = grayImage.createGraphics();
                g2d.drawImage(originalImage, 0, 0, null);
                g2d.dispose();

                ImageIcon grayIcon = new ImageIcon(grayImage);
                card.getCardImage().setIcon(grayIcon);
            }
            this.you.increaseMana();
            this.youDraw();
            this.updateText();
            this.updateDeckText();
            this.updateEverything();
            for (var card : this.yourBattlefield.getCardsOnBattleField()) {
                this.setListenerBattlefield(card);
            }
        } else {
            this.setResultOfGame();
        }
    }

    /**
     * (RunningGame) Kolo bota, prebehnú kontroly na správny chod hry a aktualizácie pre informácie o hráčskych informáciách,
     * získanie many a potiahnutie si karty, následne bot zahrá karty, ktoré môže a zaútočí s minionmi, s ktorými môže útočit a nasleduje hráčovo kolo.
     */
    private void oponentTurn() {
        if (!this.deathOfPlayer()) {
            this.deathOfPlayer();
            for (var card : this.oponnentBattlefield.getCardsOnBattleField()) {
                card.canAttack();
            }

            this.oponent.increaseMana();
            this.oponnentDraw();
            this.updateHand(this.oponnentHand);
            this.updateDeckText();
            this.updateText();

            ArrayList<Card> playableCards = new ArrayList<>();
            for (var card : this.oponnentHand.getCardsInHand()) {
                if (card.getMana() <= this.oponent.getMana()) {
                    playableCards.add(card);
                }
            }
            for (int repeating = 0; repeating < 3; repeating++) {
                ArrayList<Card> playedCards = new ArrayList<>();
                for (var card : playableCards) {
                    if (card.getMana() <= this.oponent.getMana()) {
                        this.botPlayCard(card);
                        playedCards.add(card);
                    }
                }
                for (var playedCard : playedCards) {
                    playableCards.remove(playedCard);
                }
            }
            for (var card : this.oponnentBattlefield.getCardsOnBattleField()) {
                if (card.getCanAttack()) {
                    this.attackBotCard(card);
                }
            }
            this.checkMinionsHealth();
            this.updateEverything();
            this.setResultOfGame();
            this.endTurn.setEnabled(true);
            JOptionPane.showMessageDialog(null, "Bot ended their turn", "Your turn", JOptionPane.INFORMATION_MESSAGE);
            this.myTurn();
        } else {
            if (!this.gameEnded) {
                this.setResultOfGame();
            }
        }
    }

    /**
     * (RunningGame) Aktualizácia kariet na ruke a bojovom poli hráča a bota.
     */
    private void updateEverything() {
        this.updateBattleField(this.yourBattlefield);
        this.updateBattleField(this.oponnentBattlefield);
        this.updateHand(this.yourHand);
        this.updateHand(this.oponnentHand);
    }

    /**
     * (RunningGame) Aktualizácia Listenera na obrázku karty v ruke, ktorá je zadaná v parametri.
     * @param card Vybraná karta na aktualizáciu.
     */
    private void updateListeners(Card card) {
        MouseListener[] mouseListeners = card.getCardImage().getListeners(MouseListener.class);
        for (MouseListener listener : mouseListeners) {
            card.getCardImage().removeMouseListener(listener);
        }
        this.setListenerInHand(card);
        if (!card.isInPanel(this.oponnentHandPanel)) {
            this.setListenerRightMouse(card);
        }
    }

    /**
     * (RunningGame) Aktualizácia Listenera na obrázku karty v bojovom poli, ktorá je zadaná v parametri.
     * @param card Vybraná karta na aktualizáciu.
     */
    private void updateListenersBattlefield(Card card) {
        MouseListener[] mouseListeners = card.getCardImage().getListeners(MouseListener.class);
        for (MouseListener listener : mouseListeners) {
            card.getCardImage().removeMouseListener(listener);
        }
        this.setListenerBattlefield(card);
        this.setListenerRightMouse(card);
    }

    /**
     * (RunningGame) Nastavuje Listener na obrázok zadanej karty, ktorý bude dávať informácie o karte.
     * @param card Zadaná karta.
     */
    private void setListenerRightMouse(Card card) {
        card.getCardImage().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    if (card.getCardType().equals(CardType.MINION)) {
                        String message = String.format("Name: %s%nType: %s%nMana cost: %d%nAttack: %d%nHealth: %d%nAbility: %s%n",
                                card.getNameOfCard(), card.getTypeOfCard(), card.getMana(), card.getAttack(), card.getHealth(), card.getTextOfCard());
                        JOptionPane.showMessageDialog(null, message, "Card Information", JOptionPane.INFORMATION_MESSAGE);
                    }

                    if (card.getCardType().equals(CardType.SPELL)) {
                        String message = String.format("Name: %s%nType: %s%nMana cost: %d%nAbility: %s%n",
                                card.getNameOfCard(), card.getTypeOfCard(), card.getMana(), card.getTextOfCard());
                        JOptionPane.showMessageDialog(null, message, "Card Information", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
    }

    /**
     * (RunningGame) Nastavuje Listener na obrázok zadanej karty, ktorý hrať danú kartu z ruky na bojové pole, taktiež vykoná svoju začiatočnú schopnosť pokiaľ nejakú má.
     * @param card Zadaná karta.
     */
    private void setListenerInHand(Card card) {
        if (card.isInPanel(this.yourHandPanel)) {
            card.getCardImage().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (SwingUtilities.isLeftMouseButton(e)) {
                        if (card.getCardType().equals(CardType.MINION) && RunningGame.this.you.getMana() >= card.getMana()) {
                            RunningGame.this.you.payForMana(card.getMana());
                            if (card instanceof Initial) {
                                ((Initial)card).doWhenPlayed(RunningGame.this.yourBattlefield, RunningGame.this.oponnentBattlefield, RunningGame.this.you, RunningGame.this.oponent, RunningGame.this.yourHand, RunningGame.this.oponnentHand, RunningGame.this.yourDeck, RunningGame.this.oponentDeck);
                            }
                            RunningGame.this.checkMinionsHealth();
                            RunningGame.this.yourBattlefield.getCardsOnBattleField().add(card);
                            RunningGame.this.yourHand.getCardsInHand().remove(card);
                            card.getCardImage().setVisible(false);
                            RunningGame.this.yourHandPanel.remove(card.getCardImage());
                            RunningGame.this.yourBattlefieldPanel.add(card.getCardImage());
                            card.getCardImage().setVisible(true);
                            RunningGame.this.updateText();
                            RunningGame.this.updateDeckText();
                            RunningGame.this.updateEverything();
                            card.getCardImage().removeMouseListener(this);
                            RunningGame.this.setResultOfGame();
                        }
                        if (card.getCardType().equals(CardType.SPELL) && RunningGame.this.you.getMana() >= card.getMana()) {
                            RunningGame.this.you.payForMana(card.getMana());
                            if (card instanceof Initial) {
                                ((Initial)card).doWhenPlayed(RunningGame.this.yourBattlefield, RunningGame.this.oponnentBattlefield, RunningGame.this.you, RunningGame.this.oponent, RunningGame.this.yourHand, RunningGame.this.oponnentHand, RunningGame.this.yourDeck, RunningGame.this.oponentDeck);
                            }
                            RunningGame.this.checkMinionsHealth();
                            RunningGame.this.updateListeners(card);
                            card.getCardImage().setVisible(false);
                            RunningGame.this.yourHand.getCardsInHand().remove(card);
                            RunningGame.this.yourHandPanel.remove(card.getCardImage());
                            RunningGame.this.updateEverything();
                            RunningGame.this.updateText();
                            RunningGame.this.updateDeckText();
                            card.getCardImage().removeMouseListener(this);
                            RunningGame.this.setResultOfGame();
                        }
                    }
                }
            });
        }
    }

    /**
     * (RunningGame) Nastavuje Listener na obrázok zadanej karty, ktorý bude slúžiť, ako útok karty na nepriateľa.
     * @param card Zadaná karta.
     */
    private void setListenerBattlefield(Card card) {
        card.getCardImage().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e) && card.getCanAttack()) {
                    if (RunningGame.this.oponnentBattlefield.getCardsOnBattleField().size() > 0) {
                        String[] options = {"Player", "Minions"};
                        int chosenOption = -1;
                        while (chosenOption == -1) {
                            chosenOption = JOptionPane.showOptionDialog(null, "Choose a thing to attack:", "Choosing", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
                        }
                        if (chosenOption == 0 && card.getCanAttack()) {
                            RunningGame.this.oponent.decreaseHP(card.getAttack());
                        }

                        if (chosenOption == 1) {
                            Card chosenCard =  RunningGame.this.chooseAMinion(RunningGame.this.oponnentBattlefield);
                            RunningGame.this.oponnentBattlefield.getCard(chosenCard).subtractHealth(card.getAttack());
                            card.subtractHealth(RunningGame.this.oponnentBattlefield.getCard(chosenCard).getAttack());
                        }
                    } else if (RunningGame.this.oponnentBattlefield.getCardsOnBattleField().size() == 0) {
                        RunningGame.this.oponent.decreaseHP(card.getAttack());
                    }
                    card.cannotAttack();
                    RunningGame.this.checkMinionsHealth();
                    RunningGame.this.updateEverything();
                    try {
                        BufferedImage bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Img/" + card.getNameOfImage())));
                        card.getCardImage().setIcon(new ImageIcon(bufferedImage));
                    } catch (IOException ioException) {
                        throw new RuntimeException(ioException);
                    }
                    RunningGame.this.updateText();
                    card.getCardImage().removeMouseListener(this);
                    RunningGame.this.setResultOfGame();
                }
            }
        });
    }

    /**
     * (RunnningGame) Aktualizuje obrázky kariet na vybranej ruke na všetky karty, ktoré sú na vybranej ruke. Toto slúži na to, keď niektoré karty
     * pridávajú inú kartu na ruku aby ju následne vykreslilo.
     * @param hand Vybraná ruka
     */
    private void updateHand(Hand hand) {
        for (var card : hand.getCardsInHand()) {
            if (hand.equals(this.yourHand)) {
                if (!this.yourHandPanel.isAncestorOf(card.getCardImage())) {
                    this.yourHandPanel.add(card.getCardImage());
                    card.getCardImage().setVisible(true);
                    this.updateListeners(card);
                }
            }
            if (hand.equals(this.oponnentHand)) {
                if (!this.oponnentHandPanel.isAncestorOf(card.getCardImage())) {
                    this.oponnentHandPanel.add(card.getCardImage());
                    this.updateListeners(card);
                    try {
                        BufferedImage bufferedImage = ImageIO.read(new File("src/Img/Bot card in hand.png"));
                        card.getCardImage().setIcon(new ImageIcon(bufferedImage));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    card.getCardImage().setVisible(true);
                }
            }
        }
    }

    /**
     * (RunnningGame) Aktualizuje obrázky kariet na vybratom bojovom poli na všetky karty, ktoré sú na vybratom bojovom poli. Toto slúži na to aby bola lepšia
     * kontrola nad kartami v bojovom poli.
     * @param battlefield Vybrané bojové pole.
     */
    private void updateBattleField(Battlefield battlefield) {
        for (var card : battlefield.getCardsOnBattleField()) {
            if (battlefield.equals(this.yourBattlefield)) {
                if (!this.yourBattlefieldPanel.isAncestorOf(card.getCardImage())) {
                    this.yourBattlefieldPanel.add(card.getCardImage());
                    card.getCardImage().setVisible(true);
                    this.updateListenersBattlefield(card);
                }
            }
        }
    }

    /**
     * (RunningGame) Kontroluje počet životov na všetkých kartách v bojovom poli hráča a bota, pokiaľ je rovný alebo menší než nula, skontroluje sa či má schopnosť
     * umierania a odstráni sa z bojového poľa.
     */
    private void checkMinionsHealth() {
        ArrayList<Card> deadMinions = new ArrayList<>();
        boolean dyingTriggeredBotMinion = false;
        for (var card : this.oponnentBattlefield.getCardsOnBattleField()) {
            if (!card.isAlive()) {
                card.getCardImage().setVisible(false);
                deadMinions.add(card);
                if (card instanceof Dying) {
                    ((Dying)card).doWhenDie(RunningGame.this.yourBattlefield, RunningGame.this.oponnentBattlefield, RunningGame.this.you, RunningGame.this.oponent, RunningGame.this.yourHand, RunningGame.this.oponnentHand, RunningGame.this.yourDeck, RunningGame.this.oponentDeck);
                    JOptionPane.showMessageDialog(null, card.getNameOfCard() + " have died and activates Dying", "Info", JOptionPane.INFORMATION_MESSAGE);

                    dyingTriggeredBotMinion = true;
                }
                this.oponnentBattlefieldPanel.remove(card.getCardImage());
            }
        }
        for (var card : deadMinions) {
            this.oponnentBattlefield.removeFromBattlefield(card);
        }
        if (dyingTriggeredBotMinion) {
            dyingTriggeredBotMinion = false;
            this.checkMinionsHealth();
        }

        ArrayList<Card> deadMinionsYours = new ArrayList<>();
        boolean dyingTriggeredYourMinion = false;
        for (var card : this.yourBattlefield.getCardsOnBattleField()) {
            if (!card.isAlive()) {
                card.getCardImage().setVisible(false);
                deadMinionsYours.add(card);
                if (card instanceof Dying) {
                    ((Dying)card).doWhenDie(RunningGame.this.yourBattlefield, RunningGame.this.oponnentBattlefield, RunningGame.this.you, RunningGame.this.oponent, RunningGame.this.yourHand, RunningGame.this.oponnentHand, RunningGame.this.yourDeck, RunningGame.this.oponentDeck);
                    JOptionPane.showMessageDialog(null, card.getNameOfCard() + " have died and activates Dying", "Info", JOptionPane.INFORMATION_MESSAGE);
                    dyingTriggeredYourMinion = true;
                }
                this.yourBattlefieldPanel.remove(card.getCardImage());
            }
        }
        for (var card : deadMinionsYours) {
            this.yourBattlefield.removeFromBattlefield(card);
        }
        if (dyingTriggeredYourMinion) {
            this.checkMinionsHealth();
        }
    }

    /**
     * (RunningGame) Potiahne kartu pre bota, ale pokiaľ už nemá žiadnu kartu v balíku tak ju nezobrazí v balíku.
     */
    private void oponnentDraw() {
        if (this.oponentDeck.getCardsInDeck().size() > 0) {
            Card card = this.oponentDeck.getCardsInDeck().get(0);
            this.oponnentHandPanel.add(card.getCardImage());
        }
        this.oponentDeck.drawACard(this.oponnentHand, this.oponent);
    }

    /**
     * (RunningGame) Potiahne kartu pre hráča.
     */
    private void youDraw() {
        this.yourDeck.drawACard(this.yourHand, this.you);
    }

    /**
     * (RunningGame) Metóda, ktorá je grafické znázornenie zahranie karty botom.
     * @param card Zahraná karta.
     */
    private void botPlayCard(Card card) {
        if (card.getCardType().equals(CardType.MINION)) {
            this.oponent.payForMana(card.getMana());
            this.oponnentHand.getCardsInHand().remove(card);
            card.getCardImage().setVisible(false);
            this.oponnentHandPanel.remove(card.getCardImage());
            this.oponnentBattlefield.getCardsOnBattleField().add(card);
            this.oponnentBattlefieldPanel.add(card.getCardImage());
            card.getCardImage().setVisible(true);
            this.updateText();
            this.updateDeckText();
            this.updateEverything();
            try {
                BufferedImage bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Img/" + card.getNameOfImage())));
                card.getCardImage().setIcon(new ImageIcon(bufferedImage));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            card.getCardImage().setVisible(true);
            if (card instanceof Initial) {
                ((Initial)card).doWhenPlayed(RunningGame.this.yourBattlefield, RunningGame.this.oponnentBattlefield, RunningGame.this.you, RunningGame.this.oponent, RunningGame.this.yourHand, RunningGame.this.oponnentHand, RunningGame.this.yourDeck, RunningGame.this.oponentDeck);
            }
            JOptionPane.showMessageDialog(null, "Bot have played " + card.getNameOfCard(), "Bot play", JOptionPane.INFORMATION_MESSAGE);
            card.getCardImage().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (SwingUtilities.isRightMouseButton(e)) {
                        if (card.getCardType().equals(CardType.MINION)) {
                            String message = String.format("Name: %s%nType: %s%nMana cost: %d%nAttack: %d%nHealth: %d%nAbility: %s%n",
                                    card.getNameOfCard(), card.getTypeOfCard(), card.getMana(), card.getAttack(), card.getHealth(), card.getTextOfCard());
                            JOptionPane.showMessageDialog(null, message, "Card Information", JOptionPane.INFORMATION_MESSAGE);
                        }

                        if (card.getCardType().equals(CardType.SPELL)) {
                            String message = String.format("Name: %s%nType: %s%nMana cost: %d%nAbility: %s%n",
                                    card.getNameOfCard(), card.getTypeOfCard(), card.getMana(), card.getTextOfCard());
                            JOptionPane.showMessageDialog(null, message, "Card Information", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
            });
            this.checkMinionsHealth();
        } else if (card.getCardType().equals(CardType.SPELL)) {
            if (!this.oponnentBattlefield.isEmpty() && card.getSpellType().equals(SpellType.BUFFING) || !this.yourBattlefield.isEmpty() && card.getSpellType().equals(SpellType.DAMAGING) || card.getSpellType().equals(SpellType.WHENEVER)) {
                if (card instanceof Initial) {
                    ((Initial)card).doWhenPlayed(RunningGame.this.yourBattlefield, RunningGame.this.oponnentBattlefield, RunningGame.this.you, RunningGame.this.oponent, RunningGame.this.yourHand, RunningGame.this.oponnentHand, RunningGame.this.yourDeck, RunningGame.this.oponentDeck);
                }
                JOptionPane.showMessageDialog(null, "Bot have played " + card.getNameOfCard() + ": " + card.getTextOfCard(), "Bot play", JOptionPane.INFORMATION_MESSAGE);
                this.oponent.payForMana(card.getMana());
                this.oponnentHand.getCardsInHand().remove(card);
                card.getCardImage().setVisible(false);
                this.oponnentHandPanel.remove(card.getCardImage());
                this.updateEverything();
                this.updateDeckText();
                this.checkMinionsHealth();
            }
        }
    }

    /**
     * (RunningGame) Znázornenie útoku botovho miniona z bojového poľa na hráča alebo hráčových minionov na jeho bojovom poli.
     * @param card Útočiaca karta.
     */
    private void attackBotCard(Card card) {
        Random random = new Random();
        if (this.yourBattlefield.getCardsOnBattleField().size() > 0 && card.getCanAttack()) {
            int attackChoosing = random.nextInt(0, 2);
            //Attack player
            if (attackChoosing == 0) {
                this.you.decreaseHP(card.getAttack());
                JOptionPane.showMessageDialog(null, "Bot have attacked with " + card.getNameOfCard() + " to your face", "Bot attack", JOptionPane.INFORMATION_MESSAGE);
                card.cannotAttack();
            }
            //Attack enemy minion
            if (attackChoosing == 1) {
                int index = random.nextInt(0, this.yourBattlefield.getCardsOnBattleField().size());
                this.yourBattlefield.getCardsOnBattleField().get(index).subtractHealth(card.getAttack());
                card.subtractHealth(this.yourBattlefield.getCardsOnBattleField().get(index).getAttack());
                JOptionPane.showMessageDialog(null, "Bot have attacked with " + card.getNameOfCard() + " to " + this.yourBattlefield.getCardsOnBattleField().get(index).getNameOfCard(), "Bot attack", JOptionPane.INFORMATION_MESSAGE);
                card.cannotAttack();
            }
        } else if (card.getCanAttack()) {
            this.you.decreaseHP(card.getAttack());
            JOptionPane.showMessageDialog(null, "Bot have attacked with " + card.getNameOfCard() + " to your face", "Bot attack", JOptionPane.INFORMATION_MESSAGE);
            card.cannotAttack();

        }
        this.updateText();
    }

    /**
     * (RunningGame) Výber z minionov na vabranom bojovom poli. Metóda sa používa pri útočení hráča.
     * @param battlefield Vybrané bojové pole
     * @return Vybraného miniona z bojového poľa.
     */
    private Card chooseAMinion(Battlefield battlefield) {
        Choosing choosing = new Choosing();
        return choosing.chooseAMinion(battlefield);
    }

    /**
     *(RunningGame) ODstráni všetky komponenty na GameFrame.
     */
    private void removeAll() {
        Container contentPane = this.gameFrame.getContentPane();
        for (Component c : contentPane.getComponents()) {
            c.setVisible(false);
            contentPane.remove(c);
        }
        this.gameFrame.dispose();
    }

    /**
     * (RunningGame) Pridáva funkcionalitu tlačidlám na vykonanie koniec kola a na prechod do menu na výber pre hru.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.endTurn) {
            this.endTurn.setEnabled(false);
            this.oponentTurn();
        }

        if (e.getSource() == this.menuButton) {
            String[] options = {"Menu", "Continue", "Quit game"};
            int chosenOption = -1;
            while (chosenOption == -1) {
                chosenOption = JOptionPane.showOptionDialog(null, "", "Pause", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
            }
            if (chosenOption == 0) {
                this.removeAll();
                Menu menu = new Menu();
            }
            if (chosenOption == 2) {
                System.exit(0);
            }
        }
    }
}
