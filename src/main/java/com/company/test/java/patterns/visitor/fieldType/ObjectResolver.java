package com.company.test.java.patterns.visitor.fieldType;

import com.company.test.java.patterns.visitor.visitors.FieldVisitor;

public class ObjectResolver extends FieldValueResolver<Object> {

    protected Object fieldValue;

    @Override
    public String accept(FieldVisitor visitor) {
        throw new UnsupportedOperationException("Unimplemented method 'accept'");
    }

}
