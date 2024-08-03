package com.thoughtworks.movierental;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerTest {

    private final StatementService statementService = new StatementService();

    @Test
    void testStatementHtml() {
        Customer customer = createCustomerWithRentals();
        String expectedHtml = generateExpectedHtml(customer);
        String actualHtml = statementService.generateHtmlStatement(customer);
        assertEquals(expectedHtml, actualHtml);
    }

    private Customer createCustomerWithRentals() {
        Customer customer = new Customer("John Doe");

        customer.addRental(new Rental(new Movie("The Matrix", Movie.REGULAR), 3));
        customer.addRental(new Rental(new Movie("Frozen", Movie.CHILDRENS), 4));
        customer.addRental(new Rental(new Movie("Avengers: Endgame", Movie.NEW_RELEASE), 2));

        return customer;
    }

    private String generateExpectedHtml(Customer customer) {
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

        double totalAmount = calculateTotalAmount(customer);
        int frequentRenterPoints = calculateFrequentRenterPoints(customer);

        html.append("</table>\n")
                .append("<p>Amount owed is ").append(totalAmount).append("</p>\n")
                .append("<p>You earned ").append(frequentRenterPoints).append(" frequent renter points</p>\n")
                .append("</body>\n")
                .append("</html>");

        return html.toString();
    }

    private double calculateAmount(Rental rental) {
        double amount = 0;
        switch (rental.getMovie().getPriceCode()) {
            case Movie.REGULAR:
                amount += 2;
                if (rental.getDaysRented() > 2) amount += (rental.getDaysRented() - 2) * 1.5;
                break;
            case Movie.NEW_RELEASE:
                amount += rental.getDaysRented() * 3;
                break;
            case Movie.CHILDRENS:
                amount += 1.5;
                if (rental.getDaysRented() > 3) amount += (rental.getDaysRented() - 3) * 1.5;
                break;
        }
        return amount;
    }

    private double calculateTotalAmount(Customer customer) {
        return customer.getRentals().stream()
                .mapToDouble(this::calculateAmount)
                .sum();
    }

    private int calculateFrequentRenterPoints(Customer customer) {
        return customer.getRentals().stream()
                .mapToInt(rental -> {
                    int points = 1;
                    if (rental.getMovie().getPriceCode() == Movie.NEW_RELEASE && rental.getDaysRented() > 1) {
                        points++;
                    }
                    return points;
                })
                .sum();
    }
}