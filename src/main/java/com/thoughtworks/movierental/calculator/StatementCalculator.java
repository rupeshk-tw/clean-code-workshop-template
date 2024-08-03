package com.thoughtworks.movierental.calculator;

import com.thoughtworks.movierental.Customer;
import com.thoughtworks.movierental.Rental;

public class StatementCalculator {

    private final RentalAmountCalculator amountCalculator;
    private final FrequentRenterPointsCalculator pointsCalculator;

    public StatementCalculator(RentalAmountCalculator amountCalculator, FrequentRenterPointsCalculator pointsCalculator) {
        this.amountCalculator = amountCalculator;
        this.pointsCalculator = pointsCalculator;
    }

    public double calculateTotalAmount(Customer customer) {
        return customer.getRentals().stream()
                .mapToDouble(amountCalculator::calculateAmount)
                .sum();
    }

    public int calculateFrequentRenterPoints(Customer customer) {
        return pointsCalculator.calculateFrequentRenterPoints(customer);
    }

    public double calculateAmountForRental(Rental rental) {
        return amountCalculator.calculateAmount(rental);
    }
}
