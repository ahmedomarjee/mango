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
package org.calrissian.mango.domain.entity;


import org.calrissian.mango.domain.Identifiable;

import static com.google.common.base.Preconditions.checkNotNull;

public class EntityIndex implements Identifiable {

    private final String type;
    private final String id;

    public EntityIndex(String type, String id) {
        checkNotNull(type);
        checkNotNull(id);
        this.type = type;
        this.id = id;
    }

    public EntityIndex(Entity entity) {
        this(entity.getType(), entity.getId());
    }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EntityIndex)) return false;

        EntityIndex that = (EntityIndex) o;

        if (!id.equals(that.id)) return false;
        if (!type.equals(that.type)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = type.hashCode();
        result = 31 * result + id.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "EntityIndex{" +
                "type='" + type + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
