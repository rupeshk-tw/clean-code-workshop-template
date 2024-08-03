package com.thoughtworks.movierental;

import java.util.List;

public class HtmlBuilder {

    private final StatementCalculator calculator = new StatementCalculator();
    private final RentalAmountCalculator amountCalculator = new RentalAmountCalculator();

    public String buildHtmlStatement(Customer customer) {
        StringBuilder html = new StringBuilder();
        appendHtmlHeader(html, customer.getName());
        appendHtmlTable(html, customer.getRentals());
        appendHtmlFooter(html, calculator.calculateTotalAmount(customer), calculator.calculateFrequentRenterPoints(customer));
        return html.toString();
    }

    private void appendHtmlHeader(StringBuilder html, String customerName) {
        html.append("<html>\n")
                .append("<head><title>Rental Statement</title></head>\n")
                .append("<body>\n")
                .append("<h1>Rental Record for ").append(customerName).append("</h1>\n");
    }

    private void appendHtmlTable(StringBuilder html, List<Rental> rentals) {
        html.append("<table border=\"1\">\n")
                .append("  <tr><th>Title</th><th>Amount</th></tr>\n");

        for (Rental rental : rentals) {
            double amount = amountCalculator.calculateAmount(rental);
            html.append("  <tr>\n")
                    .append("    <td>").append(rental.getMovie().getTitle()).append("</td>\n")
                    .append("    <td>").append(amount).append("</td>\n")
                    .append("  </tr>\n");
        }

        html.append("</table>\n");
    }

    private void appendHtmlFooter(StringBuilder html, double totalAmount, int frequentRenterPoints) {
        html.append("<p>Amount owed is ").append(totalAmount).append("</p>\n")
                .append("<p>You earned ").append(frequentRenterPoints).append(" frequent renter points</p>\n")
                .append("</body>\n")
                .append("</html>");
    }
}