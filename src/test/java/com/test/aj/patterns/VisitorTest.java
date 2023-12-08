package com.test.aj.patterns;

import org.junit.Test;

import com.company.test.java.patterns.visitor.fieldType.FieldValueResolver;
import com.company.test.java.patterns.visitor.fieldType.StringResolver;
import com.company.test.java.patterns.visitor.visitors.FieldVisitorImpl;
import com.google.common.collect.Maps;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;

public class VisitorTest {
    public static void setup() {
    }

    @Test
    public void testVisitor() {

        TestClass testClass = new TestClass();
        testClass.setName("Amit Juneja");
        testClass.setAddress("Bronte Road Oakville");

        Map<String, String> fieldAndType = Maps.newHashMap();
        fieldAndType.put("name", "String");
        fieldAndType.put("address", "String");

        fieldAndType.keySet().forEach(fieldName -> {
            StringResolver sr = new StringResolver(fieldName, "some value", Maps.newHashMap());
            StringResolver sr1 = new StringResolver(fieldName, "some value", Maps.newHashMap());
        });
    }

    public static void evaluate(List<FieldValueResolver> resolvers) {
        FieldVisitorImpl visitor = new FieldVisitorImpl();
        String value = "";
        for (FieldValueResolver resolver : resolvers) {
            value = resolver.accept(visitor);
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private class TestClass {
        private String name;
        private String address;
    }

}
