package com.thoughtworks.movierental;

import com.thoughtworks.movierental.calculator.FrequentRenterPointsCalculator;
import com.thoughtworks.movierental.pricecode.PriceCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FrequentRenterPointsCalculatorTest {

    private FrequentRenterPointsCalculator pointsCalculator;

    @BeforeEach
    void setUp() {
        pointsCalculator = new FrequentRenterPointsCalculator();
    }

    @Test
    void testCalculateFrequentRenterPointsForRegularMovie() {
        Movie regularMovie = new Movie("Regular Movie", PriceCode.REGULAR);
        Rental rental = new Rental(regularMovie, 1);
        Customer customer = new Customer("John Doe");
        customer.addRental(rental);
        assertEquals(1, pointsCalculator.calculateFrequentRenterPoints(customer));
    }

    @Test
    void testCalculateFrequentRenterPointsForNewReleaseMovie() {
        Movie newReleaseMovie = new Movie("New Release Movie", PriceCode.NEW_RELEASE);
        Rental rental = new Rental(newReleaseMovie, 2);
        Customer customer = new Customer("John Doe");
        customer.addRental(rental);
        assertEquals(2, pointsCalculator.calculateFrequentRenterPoints(customer));
    }
}
