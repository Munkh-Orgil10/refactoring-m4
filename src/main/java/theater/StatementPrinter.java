package theater;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

/**
 * Generates a formatted statement for a given invoice and collection of plays.
 */
public class StatementPrinter {

    private final Invoice invoice;
    private final Map<String, Play> plays;

    /**
     * Constructs a statement printer.
     *
     * @param invoice the invoice to generate a statement for
     * @param plays   mapping of play IDs to Play objects
     */
    public StatementPrinter(Invoice invoice, Map<String, Play> plays) {
        this.invoice = invoice;
        this.plays = plays;
    }

    /**
     * Returns a formatted text statement for the invoice.
     *
     * @return the statement text
     * @throws RuntimeException if the play type is unknown
     */
    public String statement() {
        int totalAmount = 0;
        int volumeCredits = 0;

        final StringBuilder result =
                new StringBuilder("Statement for " + invoice.getCustomer() + System.lineSeparator());

        final NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);

        for (Performance performance : invoice.getPerformances()) {

            final Play play = plays.get(performance.getPlayID());
            int thisAmount;

            switch (play.getType()) {
                case "tragedy":
                    thisAmount = Constants.TRAGEDY_BASE_AMOUNT;
                    if (performance.getAudience() > Constants.TRAGEDY_AUDIENCE_THRESHOLD) {
                        thisAmount += Constants.TRAGEDY_OVER_BASE_CAPACITY_PER_PERSON
                                * (performance.getAudience() - Constants.TRAGEDY_AUDIENCE_THRESHOLD);
                    }
                    break;

                case "comedy":
                    thisAmount = Constants.COMEDY_BASE_AMOUNT;
                    if (performance.getAudience() > Constants.COMEDY_AUDIENCE_THRESHOLD) {
                        thisAmount += Constants.COMEDY_OVER_BASE_CAPACITY_AMOUNT
                                + Constants.COMEDY_OVER_BASE_CAPACITY_PER_PERSON
                                * (performance.getAudience() - Constants.COMEDY_AUDIENCE_THRESHOLD);
                    }
                    thisAmount += Constants.COMEDY_AMOUNT_PER_AUDIENCE * performance.getAudience();
                    break;

                default:
                    throw new RuntimeException(
                            String.format("unknown type: %s", play.getType()));
            }

            volumeCredits += Math.max(
                    performance.getAudience() - Constants.BASE_VOLUME_CREDIT_THRESHOLD, 0);

            if ("comedy".equals(play.getType())) {
                volumeCredits += performance.getAudience() / Constants.COMEDY_EXTRA_VOLUME_FACTOR;
            }

            result.append(String.format(
                    "  %s: %s (%s seats)%n",
                    play.getName(),
                    formatter.format(thisAmount / Constants.PERCENT_FACTOR),
                    performance.getAudience()));

            totalAmount += thisAmount;
        }

        result.append(String.format(
                "Amount owed is %s%n",
                formatter.format(totalAmount / Constants.PERCENT_FACTOR)));

        result.append(String.format(
                "You earned %s credits%n",
                volumeCredits));

        return result.toString();
    }
}
