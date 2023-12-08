package com.company.test.java.patterns.visitor.fieldType;

import com.company.test.java.patterns.visitor.visitors.FieldVisitor;

import lombok.Getter;
import lombok.Setter;
import java.util.Map;

public class StringResolver extends FieldValueResolver<String> {

    @Setter
    @Getter
    private String fieldValue;

    public StringResolver(String fieldName, String fieldValue, Map<String, Object> facts) {
        this.fieldValue = fieldValue;
        this.fieldName = fieldName;
        this.facts = facts;

    }

    @Override
    public String accept(FieldVisitor visitor) {
        return visitor.visit(this);
    }
}
