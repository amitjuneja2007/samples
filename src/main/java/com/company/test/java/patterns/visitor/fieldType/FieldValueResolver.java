package com.company.test.java.patterns.visitor.fieldType;

import com.company.test.java.patterns.visitor.visitors.FieldVisitor;
import java.util.Map;

public abstract class FieldValueResolver<T> {

    protected String fieldName;

    /**
     *
     */
    protected T fieldValue;

    protected Map<String, Object> facts;

    public abstract String accept(FieldVisitor visitor);
}
