package com.thoughtworks.movierental.builder.html;

import com.thoughtworks.movierental.Rental;
import com.thoughtworks.movierental.calculator.StatementCalculator;
import com.thoughtworks.movierental.utils.HtmlUtils;

import java.text.DecimalFormat;
import java.util.List;

public class HtmlBodyBuilder {
    private static final String HTML_TABLE_BORDER = "1";
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.00");

    public String buildBody(List<Rental> rentals, StatementCalculator calculator) {
        StringBuilder html = new StringBuilder();
        html.append("<table border=\"")
                .append(HTML_TABLE_BORDER)
                .append("\">\n")
                .append("  <tr><th>Title</th><th>Amount</th></tr>\n");

        for (Rental rental : rentals) {
            appendRentalRow(html, rental, calculator);
        }

        html.append("</table>\n");
        return html.toString();
    }

    private void appendRentalRow(StringBuilder html, Rental rental, StatementCalculator calculator) {
        double amount = calculator.calculateAmountForRental(rental);
        html.append("  <tr>\n")
                .append("    <td>")
                .append(HtmlUtils.escapeHtml(rental.getMovie().getTitle()))
                .append("</td>\n")
                .append("    <td>")
                .append(DECIMAL_FORMAT.format(amount))
                .append("</td>\n")
                .append("  </tr>\n");
    }
}