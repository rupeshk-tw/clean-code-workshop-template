package com.thoughtworks.movierental.builder.html;

import com.thoughtworks.movierental.utils.HtmlUtils;

public class HtmlHeaderBuilder {
    public String buildHeader(String customerName) {
        return new StringBuilder()
                .append("<html>\n")
                .append("<head><title>Rental Statement</title></head>\n")
                .append("<body>\n")
                .append("<h1>Rental Record for ")
                .append(HtmlUtils.escapeHtml(customerName))
                .append("</h1>\n")
                .toString();
    }
}