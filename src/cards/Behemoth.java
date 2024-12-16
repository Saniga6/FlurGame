package cards;

import cards.overall.Card;
import cards.overall.Types;
/**
 * Trieda Behemoth je reprezentácia jednej z kariet
 * @author (Marek Saniga)
 * @version (1.1)
 */
public class Behemoth extends Card {
    /**
     * (Behemoth) Bezparametrický konštruktor, v ktorom sa inicializujú základné staty karty.
     */
    public Behemoth() {
        super("Behemoth", 6, 10, 12, "Lone wanderer.png", Types.CHAOS, "");
    }
}
