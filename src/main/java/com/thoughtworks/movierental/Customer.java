package com.thoughtworks.movierental;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class Customer {
    private final String name;
    private final List<Rental> rentals = new ArrayList<>();

    public void addRental(Rental rental) {
        rentals.add(rental);
    }

    public String statement() {
        StatementService statementService = new StatementService();
        return statementService.generateHtmlStatement(this);
    }
}
