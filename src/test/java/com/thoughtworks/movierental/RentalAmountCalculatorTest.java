package com.thoughtworks.movierental;

import com.thoughtworks.movierental.calculator.RentalAmountCalculator;
import com.thoughtworks.movierental.pricecode.PriceCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RentalAmountCalculatorTest {

    private RentalAmountCalculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new RentalAmountCalculator();
    }

    @Test
    void testCalculateAmountForRegularMovie() {
        Movie regularMovie = new Movie("Regular Movie", PriceCode.REGULAR);
        Rental rental = new Rental(regularMovie, 4);
        assertEquals(5.0, calculator.calculateAmount(rental));
    }

    @Test
    void testCalculateAmountForNewReleaseMovie() {
        Movie newReleaseMovie = new Movie("New Release Movie", PriceCode.NEW_RELEASE);
        Rental rental = new Rental(newReleaseMovie, 2);
        assertEquals(6.0, calculator.calculateAmount(rental));
    }

    @Test
    void testCalculateAmountForChildrensMovie() {
        Movie childrensMovie = new Movie("Children's Movie", PriceCode.CHILDRENS);
        Rental rental = new Rental(childrensMovie, 5);
        assertEquals(4.5, calculator.calculateAmount(rental));
    }
}
