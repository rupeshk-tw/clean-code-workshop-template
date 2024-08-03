package com.thoughtworks.movierental;

import com.thoughtworks.movierental.pricecode.PriceCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Movie {
    private final String title;
    private final PriceCode priceCode;
}
