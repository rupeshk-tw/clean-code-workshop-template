package com.thoughtworks.movierental;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerTest {

    private final StatementService statementService = new StatementService();

    @Test
    void testStatementHtml() {
        Customer customer = CustomerTestHelper.createCustomerWithRentals();
        String expectedHtml = CustomerTestHelper.generateExpectedHtml(customer);
        String actualHtml = customer.generateStatement(statementService);
        assertEquals(expectedHtml, actualHtml);
    }
}
