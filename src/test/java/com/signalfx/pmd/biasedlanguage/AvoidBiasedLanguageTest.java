/*
 * Copyright (C) 2020 Splunk, Inc. All rights reserved.
 */
package com.signalfx.pmd.biasedlanguage;

import static com.signalfx.pmd.biasedlanguage.AvoidBiasedLanguage.violates;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class AvoidBiasedLanguageTest {

    @Test
    public void testViolatesBlacklist() {
        assertTrue(violates("blacklist"));
        assertTrue(violates("Blacklist"));
        assertTrue(violates("BlackList"));
        assertTrue(violates("blackList"));
        assertTrue(violates("bLAcklist"));
        assertTrue(violates("black_list"));
        assertTrue(violates("Black_List"));
        assertTrue(violates("black_List"));
        assertTrue(violates("black.list"));
        assertTrue(violates("getBlackList"));
        assertTrue(violates("getBlackLists"));
        assertTrue(violates("getUserBlackList"));
        assertTrue(violates("getUserBlackLists"));
    }

    @Test
    public void testViolatesWhitelist() {
        assertTrue(violates("whitelist"));
        assertTrue(violates("Whitelist"));
        assertTrue(violates("WhiteList"));
        assertTrue(violates("whiteList"));
        assertTrue(violates("whiTEList"));
        assertTrue(violates("white_list"));
        assertTrue(violates("White_List"));
        assertTrue(violates("white_List"));
        assertTrue(violates("white.list"));
        assertTrue(violates("getWhitelist"));
        assertTrue(violates("getWhitelists"));
        assertTrue(violates("getUserWhitelist"));
        assertTrue(violates("getUserWhitelists"));
    }

    @Test
    public void testViolatesMaster() {
        assertTrue(violates("getMasterNodeAddress"));
        assertTrue(violates("SERVICE_MASTER"));
        assertTrue(violates("blob/master/foo.bar"));
    }

    @Test
    public void testViolatesSlave() {
        assertTrue(violates("getSlaveAddresses"));
        assertTrue(violates("slaveNode"));
    }

    @Test
    public void testDoesNotViolate() {
        assertFalse(violates("\"black\", \"list\""));
        assertFalse(violates("\"white\", \"list\""));
        assertFalse(violates("getListOfBlackColors"));
    }
}