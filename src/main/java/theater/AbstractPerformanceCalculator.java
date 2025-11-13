package theater;

/**
 * Calculator for quantities related to a performance.
 * (Will later be subclassed for different play types.)
 */
public class AbstractPerformanceCalculator {

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
     * Factory method for creating a performance calculator.
     * For now, this simply returns a basic calculator, but
     * later it will select an appropriate subclass.
     *
     * @param performance the performance
     * @param play        the play details
     * @return a new calculator
     */
    public static AbstractPerformanceCalculator createPerformanceCalculator(
            Performance performance, Play play) {
        return new AbstractPerformanceCalculator(performance, play);
    }

    /**
     * Computes the amount owed for this performance (in cents).
     */
    public int getAmount() {
        int result;

        switch (play.getType()) {
            case "tragedy":
                result = Constants.TRAGEDY_BASE_AMOUNT;
                if (performance.getAudience() > Constants.TRAGEDY_AUDIENCE_THRESHOLD) {
                    result += Constants.TRAGEDY_OVER_BASE_CAPACITY_PER_PERSON
                            * (performance.getAudience() - Constants.TRAGEDY_AUDIENCE_THRESHOLD);
                }
                break;

            case "comedy":
                result = Constants.COMEDY_BASE_AMOUNT;
                if (performance.getAudience() > Constants.COMEDY_AUDIENCE_THRESHOLD) {
                    result += Constants.COMEDY_OVER_BASE_CAPACITY_AMOUNT
                            + Constants.COMEDY_OVER_BASE_CAPACITY_PER_PERSON
                            * (performance.getAudience() - Constants.COMEDY_AUDIENCE_THRESHOLD);
                }
                result += Constants.COMEDY_AMOUNT_PER_AUDIENCE * performance.getAudience();
                break;

            default:
                throw new RuntimeException(
                        String.format("unknown type: %s", play.getType()));
        }

        return result;
    }

    /**
     * Computes the volume credits for this performance.
     */
    public int getVolumeCredits() {
        int result = Math.max(
                performance.getAudience() - Constants.BASE_VOLUME_CREDIT_THRESHOLD, 0);

        if ("comedy".equals(play.getType())) {
            result += performance.getAudience() / Constants.COMEDY_EXTRA_VOLUME_FACTOR;
        }

        return result;
    }
}
