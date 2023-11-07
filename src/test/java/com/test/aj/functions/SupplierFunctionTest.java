package com.test.aj.functions;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.function.Supplier;

public class SupplierFunctionTest {
    @BeforeClass
    public static void setup() {
    }

    @Test
    public void supplierFunction() {
        Supplier<String> exceptionDetails = () -> "Assertion Error";
        Assert.assertThrows(AssertionError.class, () -> {
            if (true) {
                throw new AssertionError(exceptionDetails.get());
            }
        });
    }
}
