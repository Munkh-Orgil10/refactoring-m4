package theater;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

/**
 * Generates a formatted statement for a given invoice and collection of plays.
 */
public class StatementPrinter {

    protected final StatementData statementData;

    /**
     * Constructs a statement printer.
     *
     * @param invoice the invoice to generate a statement for
     * @param plays   mapping of play IDs to Play objects
     */
    public StatementPrinter(Invoice invoice, Map<String, Play> plays) {
        this.statementData = new StatementData(invoice, plays);
    }

    /**
     * Returns a formatted text statement for the invoice.
     *
     * @return the statement text
     */
    public String statement() {
        return renderPlainText(statementData);
    }

    /**
     * Renders a plain-text statement using the given statement data.
     *
     * @param data the precomputed statement data
     * @return the formatted statement text
     */
    private String renderPlainText(StatementData data) {
        StringBuilder result =
                new StringBuilder("Statement for " + data.getCustomer()
                        + System.lineSeparator());

        for (PerformanceData performanceData : data.getPerformances()) {
            result.append(String.format(
                    "  %s: %s (%s seats)%n",
                    performanceData.getPlayName(),
                    usd(performanceData.getAmount()),
                    performanceData.getAudience()));
        }

        result.append(String.format(
                "Amount owed is %s%n",
                usd(data.totalAmount())));

        result.append(String.format(
                "You earned %s credits%n",
                data.volumeCredits()));

        return result.toString();
    }

    /**
     * Formats an amount in cents as a US dollar currency string.
     *
     * @param amountInCents the amount in cents
     * @return the formatted dollar string
     */
    protected String usd(int amountInCents) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
        return formatter.format(amountInCents / Constants.PERCENT_FACTOR);
    }
}
