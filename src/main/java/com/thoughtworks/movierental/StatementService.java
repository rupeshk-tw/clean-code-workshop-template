package com.thoughtworks.movierental;

import lombok.Getter;

@Getter
public class StatementService {

    public String generateHtmlStatement(Customer customer) {
        double totalAmount = calculateTotalAmount(customer);
        int frequentRenterPoints = calculateFrequentRenterPoints(customer);

        StringBuilder html = new StringBuilder();
        html.append("<html>\n")
                .append("<head><title>Rental Statement</title></head>\n")
                .append("<body>\n")
                .append("<h1>Rental Record for ").append(customer.getName()).append("</h1>\n")
                .append("<table border=\"1\">\n")
                .append("  <tr><th>Title</th><th>Amount</th></tr>\n");

        for (Rental rental : customer.getRentals()) {
            double amount = calculateAmount(rental);
            html.append("  <tr>\n")
                    .append("    <td>").append(rental.getMovie().getTitle()).append("</td>\n")
                    .append("    <td>").append(amount).append("</td>\n")
                    .append("  </tr>\n");
        }

        html.append("</table>\n")
                .append("<p>Amount owed is ").append(totalAmount).append("</p>\n")
                .append("<p>You earned ").append(frequentRenterPoints).append(" frequent renter points</p>\n")
                .append("</body>\n")
                .append("</html>");

        return html.toString();
    }

    private double calculateAmount(Rental rental) {
        double thisAmount = 0;
        switch (rental.getMovie().getPriceCode()) {
            case Movie.REGULAR:
                thisAmount += 2;
                if (rental.getDaysRented() > 2) thisAmount += (rental.getDaysRented() - 2) * 1.5;
                break;
            case Movie.NEW_RELEASE:
                thisAmount += rental.getDaysRented() * 3;
                break;
            case Movie.CHILDRENS:
                thisAmount += 1.5;
                if (rental.getDaysRented() > 3) thisAmount += (rental.getDaysRented() - 3) * 1.5;
                break;
        }
        return thisAmount;
    }

    private double calculateTotalAmount(Customer customer) {
        double totalAmount = 0;
        for (Rental rental : customer.getRentals()) {
            totalAmount += calculateAmount(rental);
        }
        return totalAmount;
    }

    private int calculateFrequentRenterPoints(Customer customer) {
        int points = 0;
        for (Rental rental : customer.getRentals()) {
            points++;
            if (rental.getMovie().getPriceCode() == Movie.NEW_RELEASE && rental.getDaysRented() > 1) {
                points++;
            }
        }
        return points;
    }
}