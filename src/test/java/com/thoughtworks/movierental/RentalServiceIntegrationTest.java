package com.thoughtworks.movierental;

import com.thoughtworks.movierental.builder.HtmlBuilder;
import com.thoughtworks.movierental.calculator.FrequentRenterPointsCalculator;
import com.thoughtworks.movierental.calculator.RentalAmountCalculator;
import com.thoughtworks.movierental.calculator.StatementCalculator;
import com.thoughtworks.movierental.pricecode.PriceCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RentalServiceIntegrationTest {

    private StatementCalculator statementCalculator;
    private HtmlBuilder htmlBuilder;

    @BeforeEach
    void setUp() {
        RentalAmountCalculator amountCalculator = new RentalAmountCalculator();
        FrequentRenterPointsCalculator pointsCalculator = new FrequentRenterPointsCalculator();
        statementCalculator = new StatementCalculator(amountCalculator, pointsCalculator);
        htmlBuilder = new HtmlBuilder(statementCalculator);
    }

    @Test
    void testCompleteRentalProcess() {
        // Given
        Customer customer = new Customer("John Doe");
        Movie regularMovie = new Movie("Regular Movie", PriceCode.REGULAR);
        Movie newReleaseMovie = new Movie("New Release Movie", PriceCode.NEW_RELEASE);
        Movie childrensMovie = new Movie("Children's Movie", PriceCode.CHILDRENS);
        
        customer.addRental(new Rental(regularMovie, 3));
        customer.addRental(new Rental(newReleaseMovie, 2));
        customer.addRental(new Rental(childrensMovie, 5));

        // Expected calculations
        double expectedAmount = 3.50 + 6.00 + 4.50;
        int expectedPoints = 1 + 2 + 1;  // Assuming frequent renter points are 1 for regular, 2 for new release, and 1 for children's movie

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
                "  <tr>\n" +
                "    <td>Children's Movie</td>\n" +
                "    <td>4.50</td>\n" +
                "  </tr>\n" +
                "</table>\n" +
                "<p>Amount owed is 14.00</p>\n" +
                "<p>You earned 4 frequent renter points</p>\n" +
                "</body>\n" +
                "</html>";

        // When
        double actualAmount = statementCalculator.calculateTotalAmount(customer);
        int actualPoints = statementCalculator.calculateFrequentRenterPoints(customer);
        String actualHtml = htmlBuilder.buildStatement(customer);

        // Then
        assertEquals(expectedAmount, actualAmount);
        assertEquals(expectedPoints, actualPoints);
        assertEquals(expectedHtml, actualHtml);
    }
}
