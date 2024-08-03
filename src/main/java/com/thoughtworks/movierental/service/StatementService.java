package com.thoughtworks.movierental.service;

import com.thoughtworks.movierental.Customer;
import com.thoughtworks.movierental.builder.StatementBuilder;

public class StatementService {

    private final StatementBuilder statementBuilder;

    public StatementService(StatementBuilder statementBuilder) {
        this.statementBuilder = statementBuilder;
    }

    public String generateStatement(Customer customer) {
        return statementBuilder.buildStatement(customer);
    }
}
