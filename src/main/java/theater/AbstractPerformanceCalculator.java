package theater;

/**
 * Calculator for quantities related to a performance.
 * Different play types will be handled by subclasses.
 */
public abstract class AbstractPerformanceCalculator {

    protected final Performance performance;
    protected final Play play;

    /**
     * Constructs a calculator for the given performance and play.
     *
     * @param performance the performance
     * @param play        the play details
     */
    public AbstractPerformanceCalculator(Performance performance, Play play) {
        this.performance = performance;
        this.play = play;
    }

    /**
     * Factory method for creating a performance calculator for
     * the given performance and play.
     *
     * @param performance the performance
     * @param play        the play details
     * @return a calculator instance appropriate for the play type
     */
    public static AbstractPerformanceCalculator createPerformanceCalculator(
            Performance performance, Play play) {
        switch (play.getType()) {
            case "tragedy":
                return new TragedyCalculator(performance, play);
            case "comedy":
                return new ComedyCalculator(performance, play);
            case "history":
                return new HistoryCalculator(performance, play);
            case "pastoral":
                return new PastoralCalculator(performance, play);
            default:
                throw new RuntimeException(
                        String.format("unknown type: %s", play.getType()));
        }
    }

    /**
     * Computes the amount owed for this performance (in cents).
     * Subclasses must implement this.
     *
     * @return the amount in cents
     */
    public abstract int getAmount();

    /**
     * Computes the volume credits for this performance.
     * Subclasses may override to add extra behaviour.
     *
     * @return the volume credits
     */
    public int getVolumeCredits() {
        return Math.max(
                performance.getAudience() - Constants.BASE_VOLUME_CREDIT_THRESHOLD, 0);
    }
}
