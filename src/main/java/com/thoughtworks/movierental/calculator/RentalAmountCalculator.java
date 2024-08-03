package com.thoughtworks.movierental.calculator;

import com.thoughtworks.movierental.Rental;
import com.thoughtworks.movierental.pricecode.ChildrensPriceCode;
import com.thoughtworks.movierental.pricecode.NewReleasePriceCode;
import com.thoughtworks.movierental.pricecode.PriceCodeStrategy;
import com.thoughtworks.movierental.pricecode.RegularPriceCode;

public class RentalAmountCalculator {

    private final PriceCodeStrategy regularStrategy = new RegularPriceCode();
    private final PriceCodeStrategy newReleaseStrategy = new NewReleasePriceCode();
    private final PriceCodeStrategy childrensStrategy = new ChildrensPriceCode();

    public double calculateAmount(Rental rental) {
        switch (rental.getMovie().getPriceCode()) {
            case REGULAR:
                return regularStrategy.calculateAmount(rental.getDaysRented());
            case NEW_RELEASE:
                return newReleaseStrategy.calculateAmount(rental.getDaysRented());
            case CHILDRENS:
                return childrensStrategy.calculateAmount(rental.getDaysRented());
            default:
                throw new IllegalArgumentException("Unknown price code");
        }
    }
}
