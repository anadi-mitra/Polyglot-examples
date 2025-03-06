package org.gammaraylab;

import org.graalvm.polyglot.Value;
import org.graalvm.polyglot.proxy.ProxyArray;
import java.util.List;

public class ProxyList<T> implements ProxyArray {
    private final List<T> list;

    public ProxyList(List<T> list) {
        this.list = list;
    }

    @Override
    public Object get(long index) {
        return list.get((int) index);
    }

    @Override
    public void set(long index, Value value) {
        list.set((int) index, (T) value);

    }

    @Override
    public long getSize() {
        return list.size();
    }
}
