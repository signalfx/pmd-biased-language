/*
 * Copyright (C) 2020 Splunk, Inc. All rights reserved.
 */
package com.signalfx.pmd.biasedlanguage;

import java.util.regex.Pattern;

import net.sourceforge.pmd.lang.java.ast.JavaNode;
import net.sourceforge.pmd.lang.java.rule.AbstractJavaRule;

/**
 * Attempts to identify usages of blacklist/whitelist.
 *
 * Blacklist and whitelist reflect racially charged language for which much better alternatives exist and should be used
 * instead. This rule matches on the <code>/(black|white).?list/</code> regex (case insensitive).
 *
 * @author mpetazzoni
 */
public class AvoidBlackListWhiteList extends AbstractJavaRule {

    private static final Pattern PATTERN = Pattern.compile("(black|white).?list", Pattern.CASE_INSENSITIVE);

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