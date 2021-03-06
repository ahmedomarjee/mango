/*
 * Copyright (C) 2014 The Calrissian Authors
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
package org.calrissian.mango.types.encoders.simple;


import org.calrissian.mango.types.encoders.AbstractInet6AddressEncoder;

import java.net.Inet6Address;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.net.InetAddresses.toAddrString;
import static org.calrissian.mango.net.MoreInetAddresses.forIPv6String;

public class Inet6AddressEncoder extends AbstractInet6AddressEncoder<String> {
    @Override
    public String encode(Inet6Address value) {
        checkNotNull(value, "Null values are not allowed");
        return toAddrString(value);
    }

    @Override
    public Inet6Address decode(String value) {
        checkNotNull(value, "Null values are not allowed");

        return forIPv6String(value);
    }
}
