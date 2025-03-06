package org.gammaraylab;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.HostAccess;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;
import org.graalvm.polyglot.proxy.ProxyArray;
import org.graalvm.polyglot.proxy.ProxyExecutable;
import org.graalvm.polyglot.proxy.ProxyObject;

import java.io.File;
import java.nio.file.Paths;
import java.util.*;

public class TestCases {
    String VENV_EXECUTABLE;
    Context context = null;

    public TestCases() {
        VENV_EXECUTABLE = Paths.get(System.getProperty("user.home"), "docker-wd/IdeaProjects/Polyglot-examples", ".venv", "bin", "python").toString();
    }

    void passingJavaObj() {
        context = Context.newBuilder("python")
                .option("python.Executable", VENV_EXECUTABLE)
                .option("python.ForceImportSite", "true")
                .build();
        try {
            List<Point> publicKeyList= new ArrayList<>();

            List<Point> generatorList= new ArrayList<>();
            generatorList.add(new Point(72344,5426));
            generatorList.add(new Point(63234,26));
            generatorList.add(new Point(6234,56));
            generatorList.add(new Point(82443,6709826));
            generatorList.add(new Point(8464,437));
            generatorList.add(new Point(234356,552));

            List<Integer> secretKeyList= new ArrayList<>(Arrays.asList(2345,435,67,5485,2323,7643));
            ProxyList<Point> generatorProxy = new ProxyList<>(generatorList);
            ProxyList<Integer> secretKeyProxy = new ProxyList<>(secretKeyList);

            File file = new File("src/main/polyglot/ecc.py");
            Value pythonBindings = context.getBindings("python");
            pythonBindings.putMember("Point", new Point());
            context.eval(Source.newBuilder("python", file).build());
            Value processList = pythonBindings.getMember("processList");
            // calling processList and passing objects to it
            Value result = processList.execute(generatorProxy, secretKeyProxy);

            // Convert result back to Java list
            for (int i = 0; i < result.getArraySize(); i++) {
                Value pointValue = result.getArrayElement(i);
                Point p = pointValue.as(Point.class);
                System.out.println(p);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}