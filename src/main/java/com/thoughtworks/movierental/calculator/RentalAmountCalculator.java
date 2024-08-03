package com.thoughtworks.movierental.calculator;

import com.thoughtworks.movierental.Movie;
import com.thoughtworks.movierental.Rental;

public class RentalAmountCalculator {

    public double calculateAmount(Rental rental) {
        switch (rental.getMovie().getPriceCode()) {
            case Movie.REGULAR:
                return calculateRegularAmount(rental);
            case Movie.NEW_RELEASE:
                return calculateNewReleaseAmount(rental);
            case Movie.CHILDRENS:
                return calculateChildrensAmount(rental);
            default:
                throw new IllegalArgumentException("Unknown price code");
        }
    }

    private double calculateRegularAmount(Rental rental) {
        double amount = 2;
        if (rental.getDaysRented() > 2) {
            amount += (rental.getDaysRented() - 2) * 1.5;
        }
        return amount;
    }

    private double calculateNewReleaseAmount(Rental rental) {
        return rental.getDaysRented() * 3;
    }

    private double calculateChildrensAmount(Rental rental) {
        double amount = 1.5;
        if (rental.getDaysRented() > 3) {
            amount += (rental.getDaysRented() - 3) * 1.5;
        }
        return amount;
    }
}
