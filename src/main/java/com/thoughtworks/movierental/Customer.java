package com.thoughtworks.movierental;

import com.thoughtworks.movierental.service.StatementService;
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

    public String generateStatement(StatementService statementService) {
        return statementService.generateStatement(this);
    }
}