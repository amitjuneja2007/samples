package com.company.test.java.patterns.visitor.visitors;

import com.company.test.java.patterns.visitor.fieldType.ArrayResolver;
import com.company.test.java.patterns.visitor.fieldType.ObjectResolver;
import com.company.test.java.patterns.visitor.fieldType.StringResolver;

public class FieldVisitorImpl implements FieldVisitor {

    @Override
    public String visit(StringResolver valueResolver) {
        // do any operation here
        return valueResolver.getFieldValue().toLowerCase();
    }

    @Override
    public String visit(ArrayResolver valueResolver) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visit'");
    }

    @Override
    public String visit(ObjectResolver valueResolver) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visit'");
    }

}
