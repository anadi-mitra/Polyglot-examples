package org.gammaraylab;

import org.graalvm.polyglot.HostAccess;
import org.graalvm.polyglot.Value;
import org.graalvm.polyglot.proxy.ProxyObject;
import java.util.Set;

public class ProxyTernaryTreeNode implements ProxyObject {
    private final TernaryTreeNode node;

    @HostAccess.Export
    public ProxyTernaryTreeNode(int val) {
        TernaryTreeNode tmp=new TernaryTreeNode();  //O1
        tmp.val=val;
        this.node = tmp;
    }
    // Extra constructor to wrap an existing node
    public ProxyTernaryTreeNode(TernaryTreeNode node) {
        this.node = node;
    }

    @Override
    public Object getMember(String key) {
        return switch (key) {
            case "left" -> node.left != null ? new ProxyTernaryTreeNode(node.left) : null;  //O2
            case "mid" -> node.mid != null ? new ProxyTernaryTreeNode(node.mid) : null; //O3
            case "right" -> node.right != null ? new ProxyTernaryTreeNode(node.right) : null;   //O4
            case "val" -> node.val;
            default -> null;
        };
    }

    @Override
    public Object getMemberKeys() {
        return Set.of("left", "mid", "right", "val");
    }

    @Override
    public boolean hasMember(String key) {
        return switch (key) {
            case "left", "mid", "right", "val" -> true;
            default -> false;
        };
    }

    private TernaryTreeNode unwrap(Value value) {
        if (value.isHostObject() && value.asHostObject() instanceof ProxyTernaryTreeNode proxy) {
            return node;
        }
        return null;
    }

    @Override
    public void putMember(String key, Value value) {
        switch (key) {
            case "left" -> node.left = unwrap(value);
            case "mid" -> node.mid = unwrap(value);
            case "right" -> node.right = unwrap(value);
            case "val" -> node.val = value.asInt();
            default -> System.err.println("Invalid field: " + key);
        }
    }

    public TernaryTreeNode getNode() {
        return node;
    }

    // Inner class remains as a normal Java tree node
    public static class TernaryTreeNode {
        TernaryTreeNode left;
        TernaryTreeNode mid;
        TernaryTreeNode right;
        int val;

        public TernaryTreeNode(){
            this.val= 0;
        }
        @HostAccess.Export
        public ProxyTernaryTreeNode newProxyNode(int val){
            ProxyTernaryTreeNode tmp= new ProxyTernaryTreeNode(val); //O5
            return tmp;
        }
    }
}
