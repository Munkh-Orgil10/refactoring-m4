package theater;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Holds all data needed to render a statement.
 */
public class StatementData {

    private final String customer;
    private final List<PerformanceData> performances = new ArrayList<>();

    /**
     * Constructs statement data from the given invoice and plays.
     *
     * @param invoice the invoice
     * @param plays   mapping of play IDs to Play objects
     */
    public StatementData(Invoice invoice, Map<String, Play> plays) {
        this.customer = invoice.getCustomer();
        for (Performance performance : invoice.getPerformances()) {
            Play play = plays.get(performance.getPlayID());
            performances.add(new PerformanceData(performance, play));
        }
    }

    /**
     * Returns the customer name.
     *
     * @return the customer name
     */
    public String getCustomer() {
        return customer;
    }

    /**
     * Returns the list of per-performance data.
     *
     * @return the performance data list
     */
    public List<PerformanceData> getPerformances() {
        return performances;
    }

    /**
     * Computes the total amount owed for this statement.
     *
     * @return the total amount in cents
     */
    public int totalAmount() {
        int result = 0;
        for (PerformanceData performanceData : performances) {
            result += performanceData.getAmount();
        }
        return result;
    }

    /**
     * Computes the total volume credits for this statement.
     *
     * @return the total volume credits
     */
    public int volumeCredits() {
        int result = 0;
        for (PerformanceData performanceData : performances) {
            result += performanceData.getVolumeCredits();
        }
        return result;
    }
}
