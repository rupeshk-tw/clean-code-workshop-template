package com.thoughtworks.movierental;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Statement {
    private String customerName;
    private List<RentalInfo> rentals;
    private double totalAmount;
    private int frequentRenterPoints;

    @Override
    public String toString() {
        StringBuilder html = new StringBuilder();
        html.append("<html>\n")
                .append("<head><title>Rental Statement</title></head>\n")
                .append("<body>\n")
                .append("<h1>Rental Record for ").append(customerName).append("</h1>\n")
                .append("<table border=\"1\">\n")
                .append("  <tr><th>Title</th><th>Amount</th></tr>\n");

        for (RentalInfo rental : rentals) {
            html.append("  <tr>\n")
                    .append("    <td>").append(rental.getTitle()).append("</td>\n")
                    .append("    <td>").append(rental.getAmount()).append("</td>\n")
                    .append("  </tr>\n");
        }

        html.append("</table>\n")
                .append("<p>Amount owed is ").append(totalAmount).append("</p>\n")
                .append("<p>You earned ").append(frequentRenterPoints).append(" frequent renter points</p>\n")
                .append("</body>\n")
                .append("</html>");

        return html.toString();
    }
}
