package theater;

/**
 * Calculator for tragedy performances.
 */
public class TragedyCalculator extends AbstractPerformanceCalculator {

    /**
     * Constructs a tragedy calculator.
     *
     * @param performance the performance
     * @param play        the tragedy play
     */
    public TragedyCalculator(Performance performance, Play play) {
        super(performance, play);
    }

    @Override
    public int getAmount() {
        int result = Constants.TRAGEDY_BASE_AMOUNT;

        if (performance.getAudience() > Constants.TRAGEDY_AUDIENCE_THRESHOLD) {
            result += Constants.TRAGEDY_OVER_BASE_CAPACITY_PER_PERSON
                    * (performance.getAudience() - Constants.TRAGEDY_AUDIENCE_THRESHOLD);
        }

        return result;
    }
}
