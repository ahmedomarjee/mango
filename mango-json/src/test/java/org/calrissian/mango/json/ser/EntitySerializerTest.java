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
package org.calrissian.mango.json.ser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.calrissian.mango.domain.Tuple;
import org.calrissian.mango.domain.entity.BaseEntity;
import org.calrissian.mango.domain.entity.Entity;
import org.calrissian.mango.json.MangoModule;
import org.junit.Test;

import static org.calrissian.mango.types.SimpleTypeEncoders.SIMPLE_TYPES;
import static org.junit.Assert.assertEquals;

public class EntitySerializerTest {

    private ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new MangoModule(SIMPLE_TYPES));

    @Test
    public void testSerializes() throws JsonProcessingException {

        Entity entity = new BaseEntity("type", "id");
        entity.put(new Tuple("key", "value"));
        entity.put(new Tuple("key1", "valu1"));

        String serialized = objectMapper.writeValueAsString(entity);

        assertEquals(serialized, "{\"type\":\"type\",\"id\":\"id\",\"tuples\":{\"key1\":[{\"key\":\"key1\",\"type\":\"string\",\"value\":\"valu1\",\"metadata\":[]}],\"key\":[{\"key\":\"key\",\"type\":\"string\",\"value\":\"value\",\"metadata\":[]}]}}");
    }

}
