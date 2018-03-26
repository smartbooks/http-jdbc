package com.github.smartbooks.httpjdbc.core;

import com.github.smartbooks.httpjdbc.core.util.Md5Util;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Md5UtilTest {

    @Test
    public void toMd5Test() {

        String hexText = Md5Util.toMd5("1").toUpperCase();

        assertEquals("C4CA4238A0B923820DCC509A6F75849B", hexText);

    }

}
