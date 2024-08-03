package com.thoughtworks.movierental;

import com.thoughtworks.movierental.pricecode.ChildrensPriceCode;
import com.thoughtworks.movierental.pricecode.NewReleasePriceCode;
import com.thoughtworks.movierental.pricecode.PriceCodeStrategy;
import com.thoughtworks.movierental.pricecode.RegularPriceCode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PriceCodeStrategyTest {

    @Test
    void testRegularPriceCode() {
        PriceCodeStrategy strategy = new RegularPriceCode();
        assertEquals(2, strategy.calculateAmount(1));
        assertEquals(2, strategy.calculateAmount(2));
        assertEquals(3.5, strategy.calculateAmount(3));
        assertEquals(5.0, strategy.calculateAmount(4));
    }

    @Test
    void testNewReleasePriceCode() {
        PriceCodeStrategy strategy = new NewReleasePriceCode();
        assertEquals(3, strategy.calculateAmount(1));
        assertEquals(6, strategy.calculateAmount(2));
        assertEquals(9, strategy.calculateAmount(3));
    }

    @Test
    void testChildrensPriceCode() {
        PriceCodeStrategy strategy = new ChildrensPriceCode();
        assertEquals(1.5, strategy.calculateAmount(1));
        assertEquals(1.5, strategy.calculateAmount(2));
        assertEquals(1.5, strategy.calculateAmount(3));
        assertEquals(3.0, strategy.calculateAmount(4));
        assertEquals(4.5, strategy.calculateAmount(5));
    }
}
