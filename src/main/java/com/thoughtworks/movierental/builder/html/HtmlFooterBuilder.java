package com.thoughtworks.movierental.builder.html;

import java.text.DecimalFormat;

public class HtmlFooterBuilder {
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.00");

    public String buildFooter(double totalAmount, int frequentRenterPoints) {
        return new StringBuilder()
                .append("<p>Amount owed is ")
                .append(DECIMAL_FORMAT.format(totalAmount))
                .append("</p>\n")
                .append("<p>You earned ")
                .append(frequentRenterPoints)
                .append(" frequent renter points</p>\n")
                .append("</body>\n")
                .append("</html>")
                .toString();
    }
}