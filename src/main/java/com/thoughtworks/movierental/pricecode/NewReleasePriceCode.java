package com.thoughtworks.movierental.pricecode;

public class NewReleasePriceCode implements PriceCodeStrategy {
    @Override
    public double calculateAmount(int daysRented) {
        return daysRented * 3.0;
    }
}
