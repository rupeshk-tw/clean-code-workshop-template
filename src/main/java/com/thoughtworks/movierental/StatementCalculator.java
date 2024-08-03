package com.thoughtworks.movierental;

import com.thoughtworks.movierental.calculator.FrequentRenterPointsCalculator;
import com.thoughtworks.movierental.calculator.RentalAmountCalculator;

public class StatementCalculator {

    private final RentalAmountCalculator amountCalculator = new RentalAmountCalculator();
    private final FrequentRenterPointsCalculator pointsCalculator = new FrequentRenterPointsCalculator();

    public double calculateTotalAmount(Customer customer) {
        return customer.getRentals().stream()
                .mapToDouble(amountCalculator::calculateAmount)
                .sum();
    }

    public int calculateFrequentRenterPoints(Customer customer) {
        return pointsCalculator.calculateFrequentRenterPoints(customer);
    }
}
