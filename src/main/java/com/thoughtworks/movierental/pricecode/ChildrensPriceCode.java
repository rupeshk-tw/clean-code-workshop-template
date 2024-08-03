package com.thoughtworks.movierental.pricecode;

public class ChildrensPriceCode implements PriceCodeStrategy {
    @Override
    public double calculateAmount(int daysRented) {
        double amount = 1.5;
        if (daysRented > 3) {
            amount += (daysRented - 3) * 1.5;
        }
        return amount;
    }
}
