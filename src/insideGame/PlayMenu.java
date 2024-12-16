package insideGame;

import cards.overall.AllCards;
import cards.overall.Card;
import cards.overall.GameFrame;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 * Trieda PlayMenu vykonáva činnosť výberu balíka z 3 možných, oskenovanie balíka a následné presmerovanie do RunningGame triedy, v ktorej sa vykonáva hra.
 * @author (Marek Saniga)
 * @version (1.0)
 */
public class PlayMenu implements ActionListener {
    private GameFrame gameFrame;
    private JButton deck1;
    private JButton deck2;
    private JButton deck3;
    private JButton back;
    private AllCards allCards;
    private Scanner scanner;

    /**
     * V konštruktore sa nastavia komponenty do triedy GameFrame.
     */
    public PlayMenu() {
        this.gameFrame = GameFrame.getInstance();
        this.gameFrame.setTitle("Play menu");
        this.gameFrame.setLayout(new BorderLayout());
        this.gameFrame.setBackground(null);
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Img/menu/Deckbuild.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ImageIcon image = new ImageIcon(bufferedImage);
        JLabel jLabel = new JLabel(image);

        this.deck1 = new JButton("Deck 1");
        this.deck2 = new JButton("Deck 2");
        this.deck3 = new JButton("Deck 3");
        this.back = new JButton("Back");
        this.back.setBorder(null);

        this.deck1.setBounds(200, 325, 200, 50);
        this.deck2.setBounds(650, 325, 200, 50);
        this.deck3.setBounds(1100, 325, 200, 50);
        this.back.setBounds(0, 0, 200, 82);

        this.deck1.addActionListener(this);
        this.deck2.addActionListener(this);
        this.deck3.addActionListener(this);
        this.back.addActionListener(this);

        this.setAllFocusable(false);
        this.setAllColor();

        this.gameFrame.add(this.deck1);
        this.gameFrame.add(this.deck2);
        this.gameFrame.add(this.deck3);
        this.gameFrame.add(this.back);
        this.gameFrame.add(jLabel);

        this.allCards = new AllCards();

        this.gameFrame.setVisible(true);
    }

    /**
     * (PlayMenu) Metóda nastaví pre všetky tlačidlá focusable na vstupnú hodnotu metódy.
     * @param focusable Určuje či sú tlačidlá focusable.
     */
    public void setAllFocusable(boolean focusable) {
        this.deck1.setFocusable(focusable);
        this.deck2.setFocusable(focusable);
        this.deck3.setFocusable(focusable);
        this.back.setFocusable(focusable);
    }

    /**
     * (PlayMenu) Metóda nastaví farbu tlačidiel na vopred dané hodnoty.
     */
    public void setAllColor() {
        this.deck1.setBackground(Color.decode("#111120"));
        this.deck1.setForeground(Color.white);

        this.deck2.setBackground(Color.decode("#111120"));
        this.deck2.setForeground(Color.white);

        this.deck3.setBackground(Color.decode("#111120"));
        this.deck3.setForeground(Color.white);

        this.back.setBackground(Color.decode("#111120"));
        this.back.setForeground(Color.white);
    }

    /**
     * (PlayMenu) Metóda odstráni všetky komponenty z GameFrame.
     */
    public void removeEverything() {
        Container contentPane = this.gameFrame.getContentPane();
        for (Component c : contentPane.getComponents()) {
            c.setVisible(false);
            contentPane.remove(c);
        }
        this.gameFrame.revalidate();
        this.gameFrame.repaint();
    }

    /**
     * (PlayMenu) Metóda vytvorí nový scanner, ktorý oskenuje súbor podľa vstupnej hodnoty String a pokiaľ je sa v zozname názvov kariet menej, ako 14 kariet vráti hráča spať
     * pridať si viac kariet do balíku, v opačnom prípade si pre každé meno karty nájde podľa mena karty o akú kartu sa jedná a pridá ju do zoznamu kariet v hernom balíku.
     * @param deck Názov skenovaného súboru.
     */
    public void scanAndCreateDeck(String deck) {
        ArrayList<String> cards = new ArrayList<>();
        this.scanner = null;
        try {
            this.scanner = new Scanner(new File("src/decks/" + deck + ".txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (this.scanner.hasNext()) {
            String nameOfCard = this.scanner.nextLine();
            cards.add(nameOfCard);
        }
        if (cards.size() < 14) {
            JOptionPane.showMessageDialog(null, "You are low on cards, go add a few another and come back", "Low cards!", JOptionPane.WARNING_MESSAGE);
        } else {
            ArrayList<Card> myDeckList = new ArrayList<>();
            for (var cardName : cards) {
                myDeckList.add(this.allCards.getCardFromName(cardName));
            }
            this.gameFrame.dispose();
            this.removeEverything();
            Deck myDeck = new Deck(myDeckList);
            Deck oponnentDeck = new Deck(this.allCards.botDeck());
            RunningGame runningGame = new RunningGame(myDeck, oponnentDeck);
        }
    }

    /**
     * (PlayMenu) Pridaná funkcionalita pre každé tlačidlo.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.deck1) {
            this.scanAndCreateDeck("Deck1");
        }

        if (e.getSource() == this.deck2) {
            this.scanAndCreateDeck("Deck2");
        }

        if (e.getSource() == this.deck3) {
            this.scanAndCreateDeck("Deck3");
        }

        if (e.getSource() == this.back) {
            this.removeEverything();
            this.gameFrame.dispose();
            Menu menu = new Menu();
        }
    }
}
