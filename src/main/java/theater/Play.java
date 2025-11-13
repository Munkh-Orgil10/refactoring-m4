package theater;

/**
 * Represents a play with a name and type.
 */
public class Play {

    private final String name;
    private final String type;

    /**
     * Constructs a play record.
     *
     * @param name the play's name
     * @param type the play category (e.g., tragedy, comedy)
     */
    public Play(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}
