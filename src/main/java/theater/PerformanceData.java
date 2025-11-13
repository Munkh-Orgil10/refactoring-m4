package theater;

/**
 * Holds the calculated data for a single performance.
 */
public class PerformanceData {

    private final Performance performance;
    private final Play play;
    private final int amount;
    private final int volumeCredits;

    /**
     * Constructs data for a performance.
     *
     * @param performance   the performance record
     * @param play          the play details
     * @param amount        amount owed for this performance (in cents)
     * @param volumeCredits volume credits earned for this performance
     */
    public PerformanceData(Performance performance,
                           Play play,
                           int amount,
                           int volumeCredits) {
        this.performance = performance;
        this.play = play;
        this.amount = amount;
        this.volumeCredits = volumeCredits;
    }

    /** Returns audience size. */
    public int getAudience() {
        return performance.getAudience();
    }

    /** Returns the play type. */
    public String getType() {
        return play.getType();
    }

    /** Returns the play name. */
    public String getPlayName() {
        return play.getName();
    }

    /** Returns the pre-computed amount (in cents). */
    public int getAmount() {
        return amount;
    }

    /** Returns the pre-computed volume credits. */
    public int getVolumeCredits() {
        return volumeCredits;
    }
}
