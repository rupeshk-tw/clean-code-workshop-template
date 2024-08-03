package com.thoughtworks.movierental;

import com.thoughtworks.movierental.builder.HtmlBuilder;
import com.thoughtworks.movierental.builder.StatementBuilder;
import com.thoughtworks.movierental.calculator.FrequentRenterPointsCalculator;
import com.thoughtworks.movierental.calculator.RentalAmountCalculator;
import com.thoughtworks.movierental.calculator.StatementCalculator;
import com.thoughtworks.movierental.pricecode.PriceCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HtmlBuilderTest {

    private StatementBuilder statementBuilder;
    private StatementCalculator statementCalculator;

    @BeforeEach
    void setUp() {
        RentalAmountCalculator amountCalculator = new RentalAmountCalculator();
        FrequentRenterPointsCalculator pointsCalculator = new FrequentRenterPointsCalculator();
        statementCalculator = new StatementCalculator(amountCalculator, pointsCalculator);
        statementBuilder = new HtmlBuilder(statementCalculator);
    }

    @Test
    void testBuildStatement() {
        // Given
        Customer customer = new Customer("John Doe");
        Movie regularMovie = new Movie("Regular Movie", PriceCode.REGULAR);
        Movie newReleaseMovie = new Movie("New Release Movie", PriceCode.NEW_RELEASE);
        customer.addRental(new Rental(regularMovie, 3));
        customer.addRental(new Rental(newReleaseMovie, 2));

        // Expected HTML output
        String expectedHtml = "<html>\n" +
                "<head><title>Rental Statement</title></head>\n" +
                "<body>\n" +
                "<h1>Rental Record for John Doe</h1>\n" +
                "<table border=\"1\">\n" +
                "  <tr><th>Title</th><th>Amount</th></tr>\n" +
                "  <tr>\n" +
                "    <td>Regular Movie</td>\n" +
                "    <td>3.50</td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td>New Release Movie</td>\n" +
                "    <td>6.00</td>\n" +
                "  </tr>\n" +
                "</table>\n" +
                "<p>Amount owed is 9.50</p>\n" +
                "<p>You earned 3 frequent renter points</p>\n" +
                "</body>\n" +
                "</html>";

        // When
        String actualHtml = statementBuilder.buildStatement(customer);

        // Then
        assertEquals(expectedHtml, actualHtml);
    }
}
