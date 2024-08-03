package com.thoughtworks.movierental;

public class CustomerTestHelper {

    public static Customer createCustomerWithRentals() {
        Customer customer = new Customer("John Doe");

        customer.addRental(new Rental(new Movie("The Matrix", Movie.REGULAR), 3));
        customer.addRental(new Rental(new Movie("Frozen", Movie.CHILDRENS), 4));
        customer.addRental(new Rental(new Movie("Avengers: Endgame", Movie.NEW_RELEASE), 2));

        return customer;
    }

    public static String generateExpectedHtml(Customer customer) {
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

    private static double calculateAmount(Rental rental) {
        RentalAmountCalculator calculator = new RentalAmountCalculator();
        return calculator.calculateAmount(rental);
    }

    private static double calculateTotalAmount(Customer customer) {
        return customer.getRentals().stream()
                .mapToDouble(CustomerTestHelper::calculateAmount)
                .sum();
    }

    private static int calculateFrequentRenterPoints(Customer customer) {
        FrequentRenterPointsCalculator calculator = new FrequentRenterPointsCalculator();
        return calculator.calculateFrequentRenterPoints(customer);
    }
}
