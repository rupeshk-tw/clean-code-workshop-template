package com.thoughtworks.movierental.pricecode;

public interface PriceCodeStrategy {
    double calculateAmount(int daysRented);
}
