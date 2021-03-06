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

import com.fasterxml.jackson.core.JsonGenerator;
import com.google.common.base.Strings;
import org.calrissian.mango.domain.event.Event;

import java.io.IOException;

public class EventSerializer extends BaseTupleStoreSerializer<Event> {

    @Override
    protected void writeUniqueFields(Event event, JsonGenerator generator) throws IOException {
        generator.writeObjectField("timestamp", event.getTimestamp());
        generator.writeObjectField("type", event.getType());
        generator.writeObjectField("id", event.getId());
    }
}
