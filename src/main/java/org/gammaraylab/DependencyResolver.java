package org.gammaraylab;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.Iterator;
import java.util.List;

public class DependencyResolver {

    public static class EscapeTarget {
        public String source;
        public String method;
        public int argIndex;

        @Override
        public String toString() {
            return "(source=" + source + ", method=" + method + ", argIndex=" + argIndex + ")";
        }
    }

    public static class ParamInfo {
        public List<EscapeTarget> escapesTo;
        public boolean resolved;
    }

    public static void print() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        // Load your updated JSON file
        JsonNode root = mapper.readTree(new File("src/main/java/mircobenchmarks/python/generatedDependency.json"));

        // Traverse files / classes
        for (Iterator<String> fileIt = root.fieldNames(); fileIt.hasNext(); ) {
            String srcClass = fileIt.next();
            JsonNode classNode = root.get(srcClass);

            System.out.println("Class: " + srcClass);
            // Traverse methods inside each class
            for (Iterator<String> methodIt = classNode.fieldNames(); methodIt.hasNext(); ) {
                String method = methodIt.next();
                if ("%global".equals(method) || "%vars".equals(method))
                    continue;

                JsonNode methodNode = classNode.get(method);
                JsonNode paramsNode = methodNode.get("%param");
                if (paramsNode == null)
                    continue;

                System.out.println("  Method: " + method);
                // Traverse each parameter index
                for (Iterator<String> paramIt = paramsNode.fieldNames(); paramIt.hasNext(); ) {
                    String idx = paramIt.next();
                    JsonNode paramNode = paramsNode.get(idx);

                    //converting to java type
                    ParamInfo info = mapper.convertValue(paramNode, new TypeReference<>() {});
                    System.out.print("    Param " + idx + " => ");

                    if (info.resolved) {
                        if (info.escapesTo == null || info.escapesTo.isEmpty()) {
                            System.out.println("Captured");
                        } else {
                            System.out.println("Escaped");
                        }
                    } else {
                        System.out.println("Unresolved, escaping to:");
                        for (EscapeTarget et : info.escapesTo) {
                            System.out.println("       → " + et);
                        }
                    }
                }
            }
        }
    }
}