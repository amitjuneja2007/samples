package com.company.test.java.patterns.visitor.fieldType;

import java.util.List;

import com.company.test.java.patterns.visitor.visitors.FieldVisitor;

public class ArrayResolver extends FieldValueResolver<List<String>> {

    protected List<String> fieldValue;

    @Override
    public String accept(FieldVisitor visitor) {
        throw new UnsupportedOperationException("Unimplemented method 'accept'");
    }

}
