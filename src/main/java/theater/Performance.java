package theater;

/**
 * Class representing a performance of a play,
 * including the play ID and audience size.
 */
public class Performance {

    private final String playID;
    private final int audience;

    /**
     * Creates a performance record.
     *
     * @param playID   the identifier of the play
     * @param audience audience size for the performance
     */
    public Performance(String playID, int audience) {
        this.playID = playID;
        this.audience = audience;
    }

    /**
     * Returns the play ID associated with this performance.
     *
     * @return the play ID
     */
    public String getPlayID() {
        return playID;
    }

    /**
     * Returns the audience size for this performance.
     *
     * @return the audience size
     */
    public int getAudience() {
        return audience;
    }
}
