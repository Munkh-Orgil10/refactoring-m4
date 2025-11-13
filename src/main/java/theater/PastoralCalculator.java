package theater;

/**
 * Calculator for pastoral performances.
 */
public class PastoralCalculator extends AbstractPerformanceCalculator {

    /**
     * Constructs a pastoral calculator.
     *
     * @param performance the performance
     * @param play        the pastoral play
     */
    public PastoralCalculator(Performance performance, Play play) {
        super(performance, play);
    }

    @Override
    public int getAmount() {
        int result = Constants.PASTORAL_BASE_AMOUNT;
        if (performance.getAudience() > Constants.PASTORAL_AUDIENCE_THRESHOLD) {
            result += Constants.PASTORAL_OVER_BASE_CAPACITY_PER_PERSON
                    * (performance.getAudience() - Constants.PASTORAL_AUDIENCE_THRESHOLD);
        }
        return result;
    }

    @Override
    public int getVolumeCredits() {
        int result = Math.max(
                performance.getAudience() - Constants.PASTORAL_VOLUME_CREDIT_THRESHOLD, 0);
        result += performance.getAudience() / Constants.PASTORAL_EXTRA_VOLUME_FACTOR;
        return result;
    }
}
