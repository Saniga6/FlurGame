package cards;

import cards.overall.Card;
import cards.overall.Initial;
import cards.overall.SpellType;
import cards.overall.Types;
import insideGame.Battlefield;
import insideGame.Choosing;
import insideGame.Deck;
import insideGame.Hand;
import insideGame.Player;
import java.util.Random;

/**
 * Trieda InstantDeath je reprezentácia jednej z kariet
 * @author (Marek Saniga)
 * @version (1.1)
 */
public class InstantDeath extends Card implements Initial {
    /**
     * (InstantDeath) Bezparametrický konštruktor, v ktorom sa inicializujú základné staty karty.
     */
    public InstantDeath() {
        super("Instant death", 3, "Lone wanderer.png", Types.DEATH, SpellType.DAMAGING, "Destroy an enemy minion");
    }
    /**
     * (InstantDeath) Podľa toho či patrí karta botovi alebo hráčovi vykoná svoju činnosť.
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
        if (!this.isBotCard()) {
            if (oponnentBattlefield.getCardsOnBattleField().size() > 0) {
                Choosing choosing = new Choosing();
                Card chosenCard = choosing.chooseAMinion(oponnentBattlefield);
                oponnentBattlefield.getCard(chosenCard).subtractHealth(chosenCard.getHealth());
            }
        } else {
            Random random = new Random();
            if (myBattlefield.getCardsOnBattleField().size() > 0) {
                if (myBattlefield.getCardsOnBattleField().size() != 1) {
                    int indexOfMinion = random.nextInt(0, myBattlefield.getCardsOnBattleField().size() - 1);
                    myBattlefield.getCardsOnBattleField().get(indexOfMinion).subtractHealth(myBattlefield.getCardsOnBattleField().get(indexOfMinion).getHealth());
                } else {
                    myBattlefield.getCardsOnBattleField().get(0).subtractHealth(myBattlefield.getCardsOnBattleField().get(0).getHealth());
                }
            }
        }
    }
}
