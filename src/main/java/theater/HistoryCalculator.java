package theater;

/**
 * Calculator for history performances.
 */
public class HistoryCalculator extends AbstractPerformanceCalculator {

    /**
     * Constructs a history calculator.
     *
     * @param performance the performance
     * @param play        the history play
     */
    public HistoryCalculator(Performance performance, Play play) {
        super(performance, play);
    }

    @Override
    public int getAmount() {
        int result = Constants.HISTORY_BASE_AMOUNT;
        if (performance.getAudience() > Constants.HISTORY_AUDIENCE_THRESHOLD) {
            result += Constants.HISTORY_OVER_BASE_CAPACITY_PER_PERSON
                    * (performance.getAudience() - Constants.HISTORY_AUDIENCE_THRESHOLD);
        }
        return result;
    }

    @Override
    public int getVolumeCredits() {
        return Math.max(
                performance.getAudience() - Constants.HISTORY_VOLUME_CREDIT_THRESHOLD, 0);
    }
}
