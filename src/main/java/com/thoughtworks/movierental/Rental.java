package com.thoughtworks.movierental;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Rental {
    private final Movie movie;
    private final int daysRented;
}