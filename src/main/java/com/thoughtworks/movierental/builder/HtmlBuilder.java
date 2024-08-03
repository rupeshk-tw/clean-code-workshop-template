package com.thoughtworks.movierental.builder;

import com.thoughtworks.movierental.Customer;
import com.thoughtworks.movierental.Rental;
import com.thoughtworks.movierental.calculator.StatementCalculator;

import java.text.DecimalFormat;
import java.util.List;

public class HtmlBuilder implements StatementBuilder {
    private final StatementCalculator calculator;
    private final DecimalFormat decimalFormat = new DecimalFormat("#.00");

    public HtmlBuilder(StatementCalculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public String buildStatement(Customer customer) {
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
                .append(String.format("<h1>Rental Record for %s</h1>\n", customerName));
    }

    private void appendHtmlTable(StringBuilder html, List<Rental> rentals) {
        html.append("<table border=\"1\">\n")
                .append("  <tr><th>Title</th><th>Amount</th></tr>\n");

        for (Rental rental : rentals) {
            appendRentalRow(html, rental);
        }

        html.append("</table>\n");
    }

    private void appendRentalRow(StringBuilder html, Rental rental) {
        double amount = calculator.calculateAmountForRental(rental);
        html.append("  <tr>\n")
                .append(String.format("    <td>%s</td>\n", rental.getMovie().getTitle()))
                .append(String.format("    <td>%s</td>\n", decimalFormat.format(amount)))
                .append("  </tr>\n");
    }

    private void appendHtmlFooter(StringBuilder html, double totalAmount, int frequentRenterPoints) {
        html.append(String.format("<p>Amount owed is %s</p>\n", decimalFormat.format(totalAmount)))
                .append(String.format("<p>You earned %d frequent renter points</p>\n", frequentRenterPoints))
                .append("</body>\n")
                .append("</html>");
    }
}
