package org.gammaraylab;

import java.util.logging.Logger;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.HostAccess;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;
import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TestCases {
//    private static final Logger logger = Logger.getLogger(TestCases.class.getName());
    String VENV_EXECUTABLE;
    Context context = null;

    public TestCases() {
        VENV_EXECUTABLE = Paths.get(System.getProperty("user.home"), "docker-wd/IdeaProjects/Polyglot-examples", ".venv", "bin", "python").toString();
    }

    public void testTree(){
        context = Context.newBuilder("python")
                .option("python.Executable", VENV_EXECUTABLE)
//                .allowHostAccess(HostAccess.ALL)
//                .allowHostClassLookup(s -> true)
//                .option("python.ForceImportSite", "true")
                .build();
        try {
            /*
             *           13
             *       /   |   \
             *      14   15   16
             * */
            ProxyTernaryTreeNode.TernaryTreeNode root= new ProxyTernaryTreeNode.TernaryTreeNode(); //O12
            root.val=13;
            root.left= new ProxyTernaryTreeNode.TernaryTreeNode();              //O13
            root.left.val=14;
            root.mid= new ProxyTernaryTreeNode.TernaryTreeNode();              //O14
            root.mid.val=15;
            root.right= new ProxyTernaryTreeNode.TernaryTreeNode();              //O15
            root.right.val=16;
            populateTree(root.right, 3);
            ProxyTernaryTreeNode proxyNode1= new ProxyTernaryTreeNode(root);              //O16

            /*
            *           2
            *       /   |   \
            *    null   7   8
            * */    
            root= new ProxyTernaryTreeNode.TernaryTreeNode();              //O17
            root.val=2;
            root.mid= new ProxyTernaryTreeNode.TernaryTreeNode();              //O18
            root.mid.val=7;
            root.right= new ProxyTernaryTreeNode.TernaryTreeNode();              //O19
            root.right.val=8;
            populateTree(root.right, 5);
            ProxyTernaryTreeNode proxyNode2= new ProxyTernaryTreeNode(root);              //O20

            File file = new File("src/main/polyglot/TernaryTreeNode.py");   //O21
            Value pythonBindings = context.getBindings("python");
            ProxyTernaryTreeNode proxyNodeFactory= new ProxyTernaryTreeNode(0);  //O23
            pythonBindings.putMember("proxyNodeFactory", proxyNodeFactory);

            ProxyTernaryTreeNode.TernaryTreeNode nodeFactory= new ProxyTernaryTreeNode.TernaryTreeNode();  //O24
            pythonBindings.putMember("nodeFactory", nodeFactory);

            context.eval(Source.newBuilder("python", file).build());
            Value merge_trees = pythonBindings.getMember("merge_trees");
            List<Value> results= new ArrayList<>();
            results.add(merge_trees.execute(proxyNode1, proxyNode2));
            System.out.println("size of results="+results.size());
        } catch (Exception e) {
            throw new RuntimeException(e);  //O25
        }
    }

    public void populateTree(ProxyTernaryTreeNode.TernaryTreeNode node, int n){
        node.left=new ProxyTernaryTreeNode.TernaryTreeNode();
        node.left.val=2*n;
        node.right=new ProxyTernaryTreeNode.TernaryTreeNode();
        node.right.val=2+n;
        node= node.left;
    }
    public int javaTest(){
        ProxyTernaryTreeNode.TernaryTreeNode root= new ProxyTernaryTreeNode.TernaryTreeNode();
        ProxyTernaryTreeNode.TernaryTreeNode root2= new ProxyTernaryTreeNode.TernaryTreeNode();
        int i=10;
        while(i-->0)
            populateTree(root,10);
        populateTree(root2,112);
        mergeTree(root,root2);
        printTree(root);

        return root.val;
    }

    public void printTree(ProxyTernaryTreeNode.TernaryTreeNode r){
        if(r!=null)
        {
//            logger.info(String.valueOf(r.val));
            printTree(r.left);
            printTree(r.right);
        }
//        logger.info("noooo");
    }
    private void mergeTree(ProxyTernaryTreeNode.TernaryTreeNode r1, ProxyTernaryTreeNode.TernaryTreeNode r2){
        if(r1.left==null)
            r1.left=r2.left;
        else if(r2.left!=null) {
            r1.left.val = r2.left.val+r1.left.val;
            mergeTree(r1.left, r2.left);
        }

        if(r1.right==null)
            r1.right=r2.right;
        else if(r2.right!=null) {
            r1.right.val = r2.right.val+r1.right.val;
            mergeTree(r1.right, r2.right);
        }
//        logger.info("yessss");
    }
}