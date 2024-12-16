package cards.overall;

/**
 * Enum Types má v sebe 5 typov kariet, ktoré sú bonusom ku kartovým typom triedy CardType
 * @author (Marek Saniga)
 * @version (1.0)
 */

public enum Types {
    LIFE("Life"),
    DEATH("Death"),
    NATURE("Nature"),
    CHAOS("Chaos"),
    NONE("None");

    private final String name;

    /**
     * Parametrický konštruktor nám nastaví meno typu.
     * @param name Názov typu.
     */
    Types(String name) {
        this.name = name;
    }
}
