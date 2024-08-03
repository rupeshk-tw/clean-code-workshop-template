package com.thoughtworks.movierental.builder;

import com.thoughtworks.movierental.Customer;

public interface StatementBuilder {
    String buildStatement(Customer customer);
}
