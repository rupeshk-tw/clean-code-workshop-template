package com.thoughtworks.movierental;

import com.thoughtworks.movierental.builder.html.HtmlBuilder;
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
        Customer customer = createCustomerWithRentals();

        // Expected calculations
        double expectedAmount = 14.00;
        int expectedPoints = 4;

        // Expected HTML output with proper escaping
        String expectedHtml = generateExpectedHtml();

        // When
        double actualAmount = statementCalculator.calculateTotalAmount(customer);
        int actualPoints = statementCalculator.calculateFrequentRenterPoints(customer);
        String actualHtml = htmlBuilder.buildStatement(customer);

        // Then
        assertEquals(expectedAmount, actualAmount, "The total amount calculated does not match the expected value.");
        assertEquals(expectedPoints, actualPoints, "The frequent renter points calculated do not match the expected value.");
        assertEquals(expectedHtml, actualHtml, "The generated HTML statement does not match the expected output.");
    }

    private Customer createCustomerWithRentals() {
        Customer customer = new Customer("John Doe");
        customer.addRental(new Rental(new Movie("Regular Movie", PriceCode.REGULAR), 3));
        customer.addRental(new Rental(new Movie("New Release Movie", PriceCode.NEW_RELEASE), 2));
        customer.addRental(new Rental(new Movie("Children's Movie", PriceCode.CHILDRENS), 5));
        return customer;
    }

    private String generateExpectedHtml() {
        return "<html>\n" +
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
                "    <td>Children&#39;s Movie</td>\n" +
                "    <td>4.50</td>\n" +
                "  </tr>\n" +
                "</table>\n" +
                "<p>Amount owed is 14.00</p>\n" +
                "<p>You earned 4 frequent renter points</p>\n" +
                "</body>\n" +
                "</html>";
    }
}
