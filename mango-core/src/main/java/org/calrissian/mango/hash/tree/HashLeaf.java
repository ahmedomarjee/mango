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
package org.calrissian.mango.hash.tree;


import java.util.List;

/**
 * A leaf represents a single hashed "bucket" of data- it needs to be sortable. Raw data should NOT be carried along
 * as serializable properties of the Leaf because this will negate the compressed effect of the data structure.
 */
public abstract class HashLeaf implements Node {
    private static final long serialVersionUID = 1L;

    protected String hash;

    public HashLeaf() {
    }

    public HashLeaf(String hash) {
        this.hash = hash;
    }

    @Override
    public List<Node> getChildren() {
        return null;
    }

    @Override
    public String getHash() {
        return hash;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "hash='" + hash + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HashLeaf)) return false;

        HashLeaf leaf = (HashLeaf) o;

        if (hash != null ? !hash.equals(leaf.hash) : leaf.hash != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return hash != null ? hash.hashCode() : 0;
    }
}
