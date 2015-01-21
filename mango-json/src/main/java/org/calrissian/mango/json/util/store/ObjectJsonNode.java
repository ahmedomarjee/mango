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
package org.calrissian.mango.json.util.store;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.HashMap;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.calrissian.mango.json.util.store.JsonUtil.objectToNode;

/**
 * A container json tree node which represents a json object which holds entries
 * under different field names.
 */
class ObjectJsonNode implements JsonTreeNode {

    private final ObjectMapper objectMapper;
    private final Map<String, JsonTreeNode> children = new HashMap<>();

    public ObjectJsonNode(ObjectMapper objectMapper) {
        checkNotNull(objectMapper);
        this.objectMapper = objectMapper;
    }

    /**
     * Sets state on current instance based on the flattened tree representation from the input.
     * This will also determine the next child, if necessary, and propagate the next level of
     * the tree (down to the child).
     * @param keys
     * @param level
     * @param levelToIdx
     * @param valueJsonNode
     */
    @Override
    public void visit(String[] keys, int level, Map<Integer, Integer> levelToIdx, ValueJsonNode valueJsonNode) {

        if(level == keys.length-1)
            children.put(keys[level], valueJsonNode);
        else {

            JsonTreeNode child = children.get(keys[level]);

            // look toJson see if next item exists, otherwise, create it, add it toJson children, and visit it.
            String nextKey = keys[level+1];

            if(child == null) {

                if (nextKey.equals("[]"))
                    child = new ArrayJsonNode(objectMapper);
                else
                    child = new ObjectJsonNode(objectMapper);
                children.put(keys[level], child);
            }

            child.visit(keys, level+1, levelToIdx, valueJsonNode);
        }
    }

    @Override
    public JsonNode toJsonNode() {

        ObjectNode jsonNode = objectMapper.createObjectNode();

        for(Map.Entry<String, JsonTreeNode> entry : children.entrySet()) {
            Object value = entry.getValue();
            if(entry.getValue() instanceof ValueJsonNode)
                value = objectToNode(((ValueJsonNode) entry.getValue()).getValue());

            jsonNode.putPOJO(entry.getKey(), value);
        }

        return jsonNode;
    }


    @Override
    public String toString() {
        return toJsonNode().toString();
    }
}
