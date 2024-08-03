package com.thoughtworks.movierental.builder.html;

import com.thoughtworks.movierental.Customer;
import com.thoughtworks.movierental.builder.StatementBuilder;
import com.thoughtworks.movierental.calculator.StatementCalculator;
import com.thoughtworks.movierental.utils.HtmlUtils;

public class HtmlBuilder implements StatementBuilder {
    private final HtmlHeaderBuilder headerBuilder;
    private final HtmlBodyBuilder bodyBuilder;
    private final HtmlFooterBuilder footerBuilder;
    private final StatementCalculator calculator;

    public HtmlBuilder(StatementCalculator calculator) {
        this.headerBuilder = new HtmlHeaderBuilder();
        this.bodyBuilder = new HtmlBodyBuilder();
        this.footerBuilder = new HtmlFooterBuilder();
        this.calculator = calculator;
    }

    @Override
    public String buildStatement(Customer customer) {
        StringBuilder html = new StringBuilder();
        html.append(headerBuilder.buildHeader(HtmlUtils.escapeHtml(customer.getName())));
        html.append(bodyBuilder.buildBody(customer.getRentals(), calculator));
        html.append(footerBuilder.buildFooter(
                calculator.calculateTotalAmount(customer),
                calculator.calculateFrequentRenterPoints(customer)
        ));
        return html.toString();
    }
}
