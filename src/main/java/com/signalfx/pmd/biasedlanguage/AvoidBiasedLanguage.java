/*
 * Copyright (C) 2020 Splunk, Inc. All rights reserved.
 */
package com.signalfx.pmd.biasedlanguage;

import java.util.regex.Pattern;

import net.sourceforge.pmd.lang.java.ast.JavaNode;
import net.sourceforge.pmd.lang.java.rule.AbstractJavaRule;

/**
 * Attempts to identify usages of racially charged and biased language in Java code.
 *
 * Blacklist/whitelist and master/slave (and their derivatives) reflect racially charged language
 * for which much better alternatives exist and should be used instead.
 *
 * @author mpetazzoni
 * @see #REGEX
 */
public class AvoidBiasedLanguage extends AbstractJavaRule {

    private static final String REGEX = "((black|white).?list|(master|slave))";
    private static final Pattern PATTERN = Pattern.compile(REGEX, Pattern.CASE_INSENSITIVE);

    static boolean violates(String name) {
        return name != null && name.length() > 0 && PATTERN.matcher(name).find();
    }

    @Override
    public Object visit(JavaNode node, Object data) {
        if (violates(node.getImage())) {
            addViolation(data, node);
            return data;
        }
        return super.visit(node, data);
    }
}
