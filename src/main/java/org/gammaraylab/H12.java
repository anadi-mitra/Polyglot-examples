package org.gammaraylab;

import org.graalvm.polyglot.Value;
import org.graalvm.polyglot.proxy.ProxyArray;
import org.graalvm.polyglot.proxy.ProxyObject;

import java.util.Map;

public class H12 implements ProxyObject {

    Map<String, Object> values;
    H12(Map<String, Object> values){
        this.values=values;
    }
    @Override
    public void putMember(String key, Value value) {
        values.put(key, value.isHostObject() ? value.asHostObject() : value);
    }

    @Override
    public boolean hasMember(String key) {
        return values.containsKey(key);
    }

    @Override
    public Object getMemberKeys() {
        return new ProxyArray() {
            private final Object[] keys = values.keySet().toArray();

            @Override
            public void set(long index, Value value) {
                throw new UnsupportedOperationException();
            }

            @Override
            public long getSize() {
                return keys.length;
            }

            @Override
            public Object get(long index) {
                if (index < 0 || index > Integer.MAX_VALUE) {
                    throw new ArrayIndexOutOfBoundsException();
                }
                return keys[(int) index];
            }
        };
    }

    @Override
    public Object getMember(String key) {
        return values.get(key);
    }

    @Override
    public boolean removeMember(String key) {
        if (values.containsKey(key)) {
            values.remove(key);
            return true;
        } else {
            return false;
        }
    }}
