/*
 * Copyright (C) 2020 Splunk, Inc. All rights reserved.
 */
package com.signalfx.pmd.biasedlanguage;

import static com.signalfx.pmd.biasedlanguage.AvoidBlackListWhiteList.violates;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class AvoidBlackListWhiteListTest {

    @Test
    public void testViolates() {
        assertTrue(violates("blacklist"));
        assertTrue(violates("Blacklist"));
        assertTrue(violates("BlackList"));
        assertTrue(violates("blackList"));
        assertTrue(violates("bLAcklist"));
        assertTrue(violates("black_list"));
        assertTrue(violates("Black_List"));
        assertTrue(violates("black_List"));
        assertTrue(violates("black.list"));

        assertTrue(violates("whitelist"));
        assertTrue(violates("Whitelist"));
        assertTrue(violates("WhiteList"));
        assertTrue(violates("whiteList"));
        assertTrue(violates("whiTEList"));
        assertTrue(violates("white_list"));
        assertTrue(violates("White_List"));
        assertTrue(violates("white_List"));
        assertTrue(violates("white.list"));

        assertTrue(violates("getBlackList"));
        assertTrue(violates("getUserBlackList"));
        assertTrue(violates("getUserWhitelist"));
    }

    @Test
    public void testDoesNotViolate() {
        assertFalse(violates("\"black\", \"list\""));
        assertFalse(violates("\"white\", \"list\""));
        assertFalse(violates("getListOfBlackColors"));
    }
}