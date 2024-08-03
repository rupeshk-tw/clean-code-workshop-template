package com.thoughtworks.movierental;

import com.thoughtworks.movierental.calculator.FrequentRenterPointsCalculator;
import com.thoughtworks.movierental.calculator.RentalAmountCalculator;
import com.thoughtworks.movierental.builder.HtmlBuilder;

public class CustomerTestHelper {

    public static Customer createCustomerWithRentals() {
        Customer customer = new Customer("John Doe");

        customer.addRental(new Rental(new Movie("The Matrix", Movie.REGULAR), 3));
        customer.addRental(new Rental(new Movie("Frozen", Movie.CHILDRENS), 4));
        customer.addRental(new Rental(new Movie("Avengers: Endgame", Movie.NEW_RELEASE), 2));

        return customer;
    }

    public static String generateExpectedHtml(Customer customer) {
        HtmlBuilder htmlBuilder = new HtmlBuilder();
        return htmlBuilder.buildStatement(customer);
    }

    // Optionally, if you want to keep the calculation methods:
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