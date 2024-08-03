package com.thoughtworks.movierental.calculator;

import com.thoughtworks.movierental.Customer;
import com.thoughtworks.movierental.Movie;
import com.thoughtworks.movierental.Rental;

public class FrequentRenterPointsCalculator {

    public int calculateFrequentRenterPoints(Customer customer) {
        return customer.getRentals().stream()
                       .mapToInt(this::calculatePointsForRental)
                       .sum();
    }

    private int calculatePointsForRental(Rental rental) {
        int points = 1;
        if (rental.getMovie().getPriceCode() == Movie.NEW_RELEASE && rental.getDaysRented() > 1) {
            points++;
        }
        return points;
    }
}
