package insideGame;

import cards.overall.CardType;
import cards.overall.GameFrame;
import cards.LoneWanderer;
import cards.overall.AllCards;
import cards.overall.Card;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

/**
 * V triede Deckbuilding si hráč vyberá do akého z 3 balíkov si chce zapísať karty, následne sa presunie do výberu kariet kde si môže vyberať karty,
 * ktoré chce mať v hracom balíčku.
 * @author (Marek Saniga)
 * @version (1.0)
 */
public class DeckBuilding implements ActionListener, KeyListener {
    private JPanel allCardsPanel;
    private JPanel cardsInDeck;
    private PrintWriter printWriter;
    private JButton deck1;
    private JButton deck2;
    private JButton deck3;
    private JButton back;
    private GameFrame gameFrame;
    private AllCards allCards;
    private JLabel numberOfCardsInDeck;

    /**
     * Konštruktor, ktorý používa triedu GameFrame na zobrazenie výberu balíkov do ktorých sa dá zapísať karty.
     */
    public DeckBuilding() {
        this.gameFrame = GameFrame.getInstance();
        this.gameFrame.setLayout(new BorderLayout());
        this.gameFrame.setBackground(null);
        this.gameFrame.setTitle("Deckbuilding");
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Img/menu/Deckbuild.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ImageIcon image = new ImageIcon(bufferedImage);
        JLabel jLabel = new JLabel(image);
        jLabel.setBounds(0, 0, image.getIconWidth(), image.getIconHeight());

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

        this.gameFrame.setVisible(true);
    }

    /**
     * (DeckBuilding) Metóda priradí tlačidlám špecifické farby.
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
     * (DeckBuilding) Všetkým tlačidlám nastaví focusable na zadaný parameter.
     * @param focusable Predstavuje to či chceme aby boli tlačidlá focusable alebo nie.
     */
    public void setAllFocusable(boolean focusable) {
        this.deck1.setFocusable(focusable);
        this.deck2.setFocusable(focusable);
        this.deck3.setFocusable(focusable);
        this.back.setFocusable(focusable);
    }

    /**
     * (Dekcbuilding) Všetkým tlačidlám nastaví viditeľnosť na zadaný parameter.
     * @param visible Predstavuje to či chceme aby boli tlačidlá viditeľné alebo nie.
     */
    public void setAllVisible(boolean visible) {
        this.deck1.setVisible(visible);
        this.deck2.setVisible(visible);
        this.deck3.setVisible(visible);
        this.back.setVisible(visible);
    }

    /**
     * (DeckBuilding) Metóda odstráni všetky komponenty z JFrame okrem contentPane.
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
     * (Dekcbuilding) Metóda, ktorá sa vykoná po výbere balíku, kde všetky ostatné komponenty vymaže a nastaví do hlavného framu komponenty, do ktorých budú vkladané karty.
     */
    public void setBackgoundToFrame() {
        this.removeEverything();
        this.gameFrame.setLayout(new BorderLayout());
        this.allCardsPanel = new JPanel(new GridLayout(0, 4 , 20, 20));
        this.cardsInDeck = new JPanel(new GridLayout(0, 1));
        JScrollPane scrollPane = new JScrollPane(this.allCardsPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(new Rectangle());
        JScrollPane scrollPane2 = new JScrollPane(this.cardsInDeck, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane2.setBounds(new Rectangle());
        this.allCardsPanel.setBackground(Color.decode("#f5f5dc"));
        this.cardsInDeck.setBackground(Color.decode("#f5f5dc"));
        this.gameFrame.add(scrollPane, BorderLayout.CENTER);
        this.gameFrame.add(scrollPane2, BorderLayout.EAST);
        this.gameFrame.addKeyListener(this);
    }

    /**
     * (DeckBuilding) Metóda zavolá na správu setBackgroundToFrame a následne pridá karty do potrebného JPanelu a každému obrázku karty pridá MouseListener na
     * pravé aj ľavé tlačidlo, po kliknutí sa karta pridá do panelu s pridanými kartami, pokiaľ klikne hráč na kartu v paneli s pridanými kartami karta sa odstráni z pridaných kariet
     * a pridá sa späť ku všetkým kartám. V paneli s pridanými kartami je aj počítadlo kariet, ktoré indikuje koľko kariet má hráč v balíku.
     */
    public void cardPicking() {
        this.setBackgoundToFrame();
        this.allCards = new AllCards();
        for (Card card : this.allCards.getAllCards()) {
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
            card.getCardImage().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (SwingUtilities.isLeftMouseButton(e)) {
                        if (card.isInAllCards()) {
                            if (DeckBuilding.this.getNumberOfCardsInDeck() <= 14) {
                                for (Component component : DeckBuilding.this.getComponentsOfAllCardsPanel()) {
                                    if (component.equals(card.getCardImage())) {
                                        card.getCardImage().setVisible(false);
                                        DeckBuilding.this.allCardsPanel.remove(card.getCardImage());
                                        DeckBuilding.this.cardsInDeck.add(card.getCardImage());
                                        card.getCardImage().setVisible(true);
                                        DeckBuilding.this.numberOfCardsInDeck.setText(DeckBuilding.this.textOfJLabel());
                                    }
                                }
                            }
                        } else {
                            card.getCardImage().setVisible(false);
                            DeckBuilding.this.cardsInDeck.remove(card.getCardImage());
                            DeckBuilding.this.allCardsPanel.add(card.getCardImage());
                            card.getCardImage().setVisible(true);
                            DeckBuilding.this.numberOfCardsInDeck.setText(DeckBuilding.this.textOfJLabel());
                        }
                        card.changeIsInAllCards();
                    }
                }
            });
            card.getCardImage().setVisible(true);
            this.allCardsPanel.add(card.getCardImage());
        }
        this.numberOfCardsInDeck = new JLabel("Cards in deck: 0 / 15" );
        this.numberOfCardsInDeck.setVisible(true);
        JLabel text = new JLabel("For exit press ESC");
        Card loneWanderer = new LoneWanderer();
        loneWanderer.getCardImage().setVisible(false);
        loneWanderer.getCardImage().setBounds(0, 0, 128, 1);
        this.cardsInDeck.add(text);
        this.cardsInDeck.add(this.numberOfCardsInDeck);
        this.cardsInDeck.add(loneWanderer.getCardImage());
    }

    /**
     * (DeckBuilding) Metóda vracia text pre počítadlo kariet, ktoré sa mení po každej pridanej/odobranej karte.
     * @return Text pre počítadlo kariet.
     */
    public String textOfJLabel() {
        return "Cards in deck: " + this.getNumberOfCardsInDeck() + " / 15";
    }

    /**
     * (DeckBuilding) Metóda vracia pole komponentov v paneli všetkých kariet.
     * @return Pole komponentov v paneli všetkých kariet.
     */
    public Component[] getComponentsOfAllCardsPanel() {
        return this.allCardsPanel.getComponents();
    }

    /**
     * (DeckBuilding) Metóda vracia pole komponentov v paneli vybraných kariet.
     * @return Pole komponentov v paneli vybraných kariet.
     */
    public Component[] getComponentsInDeck() {
        return this.cardsInDeck.getComponents();
    }

    /**
     * (Deckbuilding) Metóda vracia počet kariet v paneli vybraných kariet.
     * @return Počet kariet.
     */
    public int getNumberOfCardsInDeck() {
        int numberOfCards = 0;
        for (var card : this.getComponentsInDeck()) {
            numberOfCards++;
        }
        return numberOfCards - 3;
    }

    @Override
    /**
     * (DeckBuild) Metóda pridáva tlačidlám funkcionality a vytvára nový PrintWriter podľa vybraného tlačidla.
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.deck1) {
            try {
                this.printWriter = new PrintWriter(new File("src/decks/Deck1.txt"));
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            this.setAllVisible(false);
            this.cardPicking();
        }

        if (e.getSource() == this.deck2) {
            try {
                this.printWriter = new PrintWriter(new File("src/decks/Deck2.txt"));
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            this.setAllVisible(false);
            this.cardPicking();
        }

        if (e.getSource() == this.deck3) {
            try {
                this.printWriter = new PrintWriter(new File("src/decks/Deck3.txt"));
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            this.setAllVisible(false);
            this.cardPicking();
        }

        if (e.getSource() == this.back) {
            this.gameFrame.setVisible(false);
            this.removeEverything();
            this.gameFrame.dispose();
            this.gameFrame = null;
            Menu menu = new Menu();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    /**
     * (DeckBuilding) Metóda pridáva tlačidlu ESC funkcionalitu odchodu z výberu kariet do balíka pokiaľ je počet kariet menej alebo rovný 15. Zároveň
     * cez PritWriter zapíše mená kariet do súboru.
     */
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE && this.getNumberOfCardsInDeck() <= 15) {
            for (var card : this.allCards.getAllCards()) {
                if (card.isInPanel(this.cardsInDeck)) {
                    this.printWriter.println(card.getNameOfCard());
                }
            }
            this.printWriter.close();
            this.gameFrame.removeKeyListener(this);
            this.removeEverything();
            DeckBuilding deckBuilding = new DeckBuilding();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
