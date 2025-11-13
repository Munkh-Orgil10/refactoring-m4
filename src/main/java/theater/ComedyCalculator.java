package theater;

/**
 * Calculator for comedy performances.
 */
public class ComedyCalculator extends AbstractPerformanceCalculator {

    /**
     * Constructs a comedy calculator.
     *
     * @param performance the performance
     * @param play        the comedy play
     */
    public ComedyCalculator(Performance performance, Play play) {
        super(performance, play);
    }

    @Override
    public int getAmount() {
        int result = Constants.COMEDY_BASE_AMOUNT;

        if (performance.getAudience() > Constants.COMEDY_AUDIENCE_THRESHOLD) {
            result += Constants.COMEDY_OVER_BASE_CAPACITY_AMOUNT
                    + Constants.COMEDY_OVER_BASE_CAPACITY_PER_PERSON
                    * (performance.getAudience() - Constants.COMEDY_AUDIENCE_THRESHOLD);
        }

        result += Constants.COMEDY_AMOUNT_PER_AUDIENCE * performance.getAudience();
        return result;
    }

    @Override
    public int getVolumeCredits() {
        int result = super.getVolumeCredits();
        result += performance.getAudience() / Constants.COMEDY_EXTRA_VOLUME_FACTOR;
        return result;
    }
}
