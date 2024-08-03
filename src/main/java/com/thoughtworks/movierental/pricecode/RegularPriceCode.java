package com.thoughtworks.movierental.pricecode;

public class RegularPriceCode implements PriceCodeStrategy {
    @Override
    public double calculateAmount(int daysRented) {
        double thisAmount = 2.0; // Base amount for the first 2 days
        if (daysRented > 2) {
            // Additional amount for days beyond 2
            thisAmount += (daysRented - 2) * 1.5;
        }
        return thisAmount;
    }
}
