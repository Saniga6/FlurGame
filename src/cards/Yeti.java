package cards;

import cards.overall.Card;
import cards.overall.Types;

/**
 * Trieda Yeti je reprezentácia jednej z kariet
 * @author (Marek Saniga)
 * @version (1.0)
 */
public class Yeti extends Card {
    /**
     * (Yeti) Bezparametrický konštruktor, v ktorom sa inicializujú základné staty karty.
     */
    public Yeti() {
        super("Yeti", 3, 5, 5, "Lone wanderer.png", Types.NATURE, "");
    }
}
