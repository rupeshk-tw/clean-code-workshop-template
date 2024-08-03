package com.thoughtworks.movierental;

import com.thoughtworks.movierental.builder.HtmlBuilder;
import com.thoughtworks.movierental.service.StatementService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerTest {

    private final StatementService statementService = new StatementService(new HtmlBuilder());

    @Test
    void testStatementHtml() {
        Customer customer = CustomerTestHelper.createCustomerWithRentals();
        String expectedHtml = CustomerTestHelper.generateExpectedHtml(customer);
        String actualHtml = statementService.generateStatement(customer);
        assertEquals(expectedHtml, actualHtml);
    }
}
