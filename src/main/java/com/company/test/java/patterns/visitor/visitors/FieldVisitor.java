package com.company.test.java.patterns.visitor.visitors;

import com.company.test.java.patterns.visitor.fieldType.ArrayResolver;
import com.company.test.java.patterns.visitor.fieldType.ObjectResolver;
import com.company.test.java.patterns.visitor.fieldType.StringResolver;

public interface FieldVisitor {

    String visit(StringResolver valueResolver);

    String visit(ArrayResolver valueResolver);

    String visit(ObjectResolver valueResolver);

}
