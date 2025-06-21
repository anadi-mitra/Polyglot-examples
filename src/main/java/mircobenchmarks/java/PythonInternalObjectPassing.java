package mircobenchmarks.java;

import mircobenchmarks.java.exceptions.KeyNotFoundException;
import mircobenchmarks.java.exceptions.UnsupportedOperationException;
import mircobenchmarks.polyglot.PythonContext;
import org.graalvm.polyglot.Value;
import org.graalvm.polyglot.proxy.ProxyObject;
import java.io.File;
import java.io.IOException;

public class PythonInternalObjectPassing implements  Test{
    private static final String PYTHON_SRC_FILE_PATH="src/main/java/mircobenchmarks/python/PythonInternalObjectPassing.py";

    private ProxyNode createList(int len) {
        ProxyNode head;
        ProxyNode tmp;

        if(true) {
            head = new ProxyNode(len);
            tmp = head;
            while (len > 1) {
                tmp.next = new ProxyNode(len - 1);
                tmp = tmp.next;
                len--;
            }
        }
        else{
            int i=10;
            head = new ProxyNode(10);
            tmp = head;
            while (i<len) {
                tmp.next = new ProxyNode(i+1);
                tmp = tmp.next;
                i++;
            }
        }

        return head;
    }

    @Override
    public void runTest(){
        ProxyNode head= createList(300);
        PythonContext pythonContext= new PythonContext();
        File pySrcFile= new File(PYTHON_SRC_FILE_PATH);
        try {
            Value bindings= pythonContext.getBindings(pySrcFile);
            //getting the desired function from python
            Value sliceList=bindings.getMember("sliceList");
            sliceList.execute(head);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static final class ProxyNode implements ProxyObject {
        private final String KEY_VALUE="value";
        private final String KEY_NEXT="next";

        public int value;
        public ProxyNode next;

        public ProxyNode(int value) {
            this.value = value;
        }

        @Override
        public Object getMember(String key) {
            return switch (key){
                case KEY_VALUE->value;
                case KEY_NEXT->next;
                default -> throw new KeyNotFoundException(key);
            };
        }

        @Override
        public Object getMemberKeys() {
            return new String[]{KEY_NEXT,KEY_VALUE};
        }

        @Override
        public boolean hasMember(String key) {
            return key.equals(KEY_VALUE)||key.equals(KEY_NEXT);
        }

        @Override
        public void putMember(String key, Value value) {
            switch (key){
                case KEY_VALUE: this.value=value.asInt(); break;
                case KEY_NEXT: this.next= value.as(ProxyNode.class); break;
                default: throw new KeyNotFoundException(key);
            }
        }

        @Override
        public boolean removeMember(String key) {
            throw new UnsupportedOperationException("trying to remove '"+key+"'");
        }
    }
}

