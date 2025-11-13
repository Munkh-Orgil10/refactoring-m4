package theater;

/**
 * Holds the calculated data for a single performance.
 */
public class PerformanceData {

    private final Performance performance;
    private final Play play;

    public PerformanceData(Performance performance, Play play) {
        this.performance = performance;
        this.play = play;
    }

    public int getAudience() {
        return performance.getAudience();
    }

    public String getType() {
        return play.getType();
    }

    public String getPlayName() {
        return play.getName();
    }

    public int getAmount() {
        int result;

        switch (getType()) {
            case "tragedy":
                result = Constants.TRAGEDY_BASE_AMOUNT;
                if (getAudience() > Constants.TRAGEDY_AUDIENCE_THRESHOLD) {
                    result += Constants.TRAGEDY_OVER_BASE_CAPACITY_PER_PERSON
                            * (getAudience() - Constants.TRAGEDY_AUDIENCE_THRESHOLD);
                }
                break;

            case "comedy":
                result = Constants.COMEDY_BASE_AMOUNT;
                if (getAudience() > Constants.COMEDY_AUDIENCE_THRESHOLD) {
                    result += Constants.COMEDY_OVER_BASE_CAPACITY_AMOUNT
                            + Constants.COMEDY_OVER_BASE_CAPACITY_PER_PERSON
                            * (getAudience() - Constants.COMEDY_AUDIENCE_THRESHOLD);
                }
                result += Constants.COMEDY_AMOUNT_PER_AUDIENCE * getAudience();
                break;

            default:
                throw new RuntimeException(
                        String.format("unknown type: %s", getType()));
        }

        return result;
    }

    public int getVolumeCredits() {
        int result = Math.max(
                getAudience() - Constants.BASE_VOLUME_CREDIT_THRESHOLD, 0);

        if ("comedy".equals(getType())) {
            result += getAudience() / Constants.COMEDY_EXTRA_VOLUME_FACTOR;
        }

        return result;
    }
}
