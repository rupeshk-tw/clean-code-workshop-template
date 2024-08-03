package com.thoughtworks.movierental;

import com.thoughtworks.movierental.calculator.FrequentRenterPointsCalculator;
import com.thoughtworks.movierental.calculator.RentalAmountCalculator;
import com.thoughtworks.movierental.calculator.StatementCalculator;
import com.thoughtworks.movierental.pricecode.PriceCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StatementCalculatorTest {

    private StatementCalculator statementCalculator;
    private RentalAmountCalculator amountCalculator;
    private FrequentRenterPointsCalculator pointsCalculator;

    @BeforeEach
    void setUp() {
        amountCalculator = new RentalAmountCalculator();
        pointsCalculator = new FrequentRenterPointsCalculator();
        statementCalculator = new StatementCalculator(amountCalculator, pointsCalculator);
    }

    @Test
    void testCalculateTotalAmount() {
        Customer customer = new Customer("John Doe");
        Movie regularMovie = new Movie("Regular Movie", PriceCode.REGULAR);
        Movie newReleaseMovie = new Movie("New Release Movie", PriceCode.NEW_RELEASE);
        customer.addRental(new Rental(regularMovie, 3));
        customer.addRental(new Rental(newReleaseMovie, 2));
        assertEquals(9.5, statementCalculator.calculateTotalAmount(customer));
    }

    @Test
    void testCalculateFrequentRenterPoints() {
        Customer customer = new Customer("John Doe");
        Movie newReleaseMovie = new Movie("New Release Movie", PriceCode.NEW_RELEASE);
        customer.addRental(new Rental(newReleaseMovie, 2));
        assertEquals(2, statementCalculator.calculateFrequentRenterPoints(customer));
    }
}
