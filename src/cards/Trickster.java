package cards;

import cards.overall.AllCards;
import cards.overall.Card;
import cards.overall.Initial;
import cards.overall.Types;
import insideGame.Battlefield;
import insideGame.Deck;
import insideGame.Hand;
import insideGame.Player;

/**
 * Trieda Trickster je reprezentácia jednej z kariet
 * @author (Marek Saniga)
 * @version (1.0)
 */
public class Trickster extends Card implements Initial {
    /**
     * (Trickster) Bezparametrický konštruktor, v ktorom sa inicializujú základné staty karty.
     */
    public Trickster() {
        super("Trickster", 4, 2, 4, "Trickster.png", Types.CHAOS, "When i´m summoned: Add spell to your hand and reduce it´s cost by (2).");
    }
    /**
     * (Trickster) Podľa toho či patrí karta botovi alebo hráčovi vykoná svoju činnosť.
     * @param myBattlefield Tu sa nachádzajú minioni, ktorých si vyhodil z ruky.
     * @param oponnentBattlefield Tu sa nachádzajú minioni, ktoré bot vyhodil z ruky.
     * @param player Tvoja postava.
     * @param oponnentPlayer Postava bota.
     * @param myHand Tvoja ruka, z ktorej hráš karty.
     * @param oponnentHand Botova ruka, z ktorej bot hrá karty.
     * @param myDeck Tvoj balíček z ktorého si ťaháš karty.
     * @param oponnentDeck Botov balíček, zktorého si ťahá bot.
     */
    @Override
    public void doWhenPlayed(Battlefield myBattlefield, Battlefield oponnentBattlefield, Player player, Player oponnentPlayer, Hand myHand, Hand oponnentHand, Deck myDeck, Deck oponnentDeck) {
        AllCards allCards = new AllCards();
        Card spell =  allCards.getRandomSpell();
        spell.subtractMana(2);
        if (!this.isBotCard()) {
            myHand.addCardToHand(spell);
        } else {
            spell.setBotCard();
            oponnentHand.addCardToHand(spell);
        }
    }
}
