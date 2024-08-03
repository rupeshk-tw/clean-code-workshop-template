package com.thoughtworks.movierental;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Rental {
    private Movie movie;
    private int daysRented;
}