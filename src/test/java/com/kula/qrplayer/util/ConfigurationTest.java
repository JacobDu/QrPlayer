package com.kula.qrplayer.util;

import junit.framework.Assert;

import org.junit.Test;

public class ConfigurationTest {

    @Test
    public void test() {
        Assert.assertEquals(128, Configuration.getINSTANCE().getMaxFileSize());
    }
}
