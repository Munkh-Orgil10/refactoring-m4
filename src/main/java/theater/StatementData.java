package theater;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Holds all calculated data related to an invoice.
 */
public class StatementData {

    private final String customer;
    private final List<PerformanceData> performances = new ArrayList<>();

    /**
     * Constructs the calculated statement data for an invoice.
     *
     * @param invoice the invoice information
     * @param plays   the map of playID to Play objects
     */
    public StatementData(Invoice invoice, Map<String, Play> plays) {
        this.customer = invoice.getCustomer();

        for (Performance performance : invoice.getPerformances()) {
            Play play = plays.get(performance.getPlayID());
            performances.add(createPerformanceData(performance, play));
        }
    }

    /** Returns customer name. */
    public String getCustomer() {
        return customer;
    }

    /** Returns performance-level calculated data. */
    public List<PerformanceData> getPerformances() {
        return performances;
    }

    /**
     * Extracted helper that creates a PerformanceData object
     * and initializes a calculator for future refactoring steps.
     */
    private PerformanceData createPerformanceData(Performance performance, Play play) {

        // This will eventually choose a subclass (tragedy/comedy/etc.)
        AbstractPerformanceCalculator calculator =
                AbstractPerformanceCalculator.createPerformanceCalculator(performance, play);

        // Currently calculator is not used yet — that comes in Task 4.2.
        return new PerformanceData(performance, play);
    }

    /**
     * Computes the total amount owed.
     */
    public int totalAmount() {
        int result = 0;
        for (PerformanceData perf : performances) {
            result += perf.getAmount();
        }
        return result;
    }

    /**
     * Computes the total volume credits.
     */
    public int volumeCredits() {
        int result = 0;
        for (PerformanceData perf : performances) {
            result += perf.getVolumeCredits();
        }
        return result;
    }
}
