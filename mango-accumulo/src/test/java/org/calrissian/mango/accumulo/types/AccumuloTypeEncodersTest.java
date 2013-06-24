/*
 * Copyright (C) 2013 The Calrissian Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.calrissian.mango.accumulo.types;

import org.calrissian.mango.domain.IPv4;
import org.calrissian.mango.types.TypeEncoder;
import org.calrissian.mango.types.exception.TypeDecodingException;
import org.calrissian.mango.types.exception.TypeEncodingException;
import org.junit.Test;

import java.net.URI;
import java.util.Date;


import static org.calrissian.mango.accumulo.types.AccumuloTypeEncoders.*;
import static org.junit.Assert.assertEquals;

public class AccumuloTypeEncodersTest {

    private static<T> void verifyBasicFunctionality(String alias, T testObject, TypeEncoder<T, String> encoder) throws TypeEncodingException, TypeDecodingException {
        assertEquals(alias, encoder.getAlias());
        assertEquals(testObject.getClass(), encoder.resolves());

        //test encode decode returns same value
        assertEquals(testObject, encoder.decode(encoder.encode(testObject)));
    }

    @Test
    public void testBasicFunctionality() throws Exception{
        verifyBasicFunctionality("boolean", true, booleanEncoder());
        verifyBasicFunctionality("byte", (byte) 3, byteEncoder());
        verifyBasicFunctionality("date", new Date(), dateEncoder());
        verifyBasicFunctionality("double", -1.5D, doubleEncoder());
        verifyBasicFunctionality("float", -1.5F, floatEncoder());
        verifyBasicFunctionality("integer", 3, integerEncoder());
        verifyBasicFunctionality("ipv4", new IPv4("192.168.1.1"), ipv4Encoder());
        verifyBasicFunctionality("long", 3L, longEncoder());
        verifyBasicFunctionality("string", "testing", stringEncoder());
        verifyBasicFunctionality("uri", new URI("http://testing.org"), uriEncoder());
    }

    @Test
    public void testCorrectEncoding () throws Exception {

        assertEquals("true", booleanEncoder().encode(true));
        assertEquals("false", booleanEncoder().encode(false));

        assertEquals("00000000003", byteEncoder().encode((byte) 3));

        assertEquals("00000000000000000010", dateEncoder().encode(new Date(10)));

        assertEquals("04609434218613702656", doubleEncoder().encode(1.5D));
        assertEquals("-4613937818241073152", doubleEncoder().encode(-1.5D));

        assertEquals("01069547520", floatEncoder().encode(1.5F));
        assertEquals("-1077936128", floatEncoder().encode(-1.5F));

        assertEquals("00000000003", integerEncoder().encode(3));
        assertEquals("-0000000003", integerEncoder().encode(-3));

        assertEquals("00000000003232235777", ipv4Encoder().encode(new IPv4("192.168.1.1")));

        assertEquals("00000000000000000003", longEncoder().encode(3L));
        assertEquals("-0000000000000000003", longEncoder().encode(-3L));

        assertEquals("test", stringEncoder().encode("test"));

        assertEquals("http://testing.org", uriEncoder().encode(new URI("http://testing.org")));
    }
}
