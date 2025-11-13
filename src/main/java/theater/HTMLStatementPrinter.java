package theater;

import java.util.Map;

/**
 * Renders statements in HTML format.
 */
public class HTMLStatementPrinter extends StatementPrinter {

    /**
     * Constructs an HTML statement printer.
     *
     * @param invoice the invoice to generate a statement for
     * @param plays   mapping of play IDs to Play objects
     */
    public HTMLStatementPrinter(Invoice invoice, Map<String, Play> plays) {
        super(invoice, plays);
    }

    @Override
    public String statement() {
        StringBuilder result = new StringBuilder(
                String.format("<h1>Statement for %s</h1>%n",
                        statementData.getCustomer()));

        result.append("<table>").append(System.lineSeparator());
        result.append(String.format(
                " <caption>Statement for %s</caption>%n",
                statementData.getCustomer()));
        result.append(" <tr><th>play</th><th>seats</th><th>cost</th></tr>")
                .append(System.lineSeparator());

        for (PerformanceData perfData : statementData.getPerformances()) {
            // print line for this order
            result.append(String.format(
                    " <tr><td>%s</td><td>%s</td><td>%s</td></tr>%n",
                    perfData.getPlayName(),
                    perfData.getAudience(),
                    usd(perfData.getAmount())));
        }
        result.append("</table>").append(System.lineSeparator());

        result.append(String.format(
                "<p>Amount owed is <em>%s</em></p>%n",
                usd(statementData.totalAmount())));
        result.append(String.format(
                "<p>You earned <em>%s</em> credits</p>%n",
                statementData.volumeCredits()));

        return result.toString();
    }
}
