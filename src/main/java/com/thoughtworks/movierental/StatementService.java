package com.thoughtworks.movierental;

public class StatementService {

    private final HtmlBuilder htmlBuilder = new HtmlBuilder();

    public String generateHtmlStatement(Customer customer) {
        return htmlBuilder.buildHtmlStatement(customer);
    }
}
