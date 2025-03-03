package org.gammaraylab;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.HostAccess;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;
import org.graalvm.polyglot.proxy.ProxyArray;
import org.graalvm.polyglot.proxy.ProxyObject;

import java.io.File;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class TestCases {
    String VENV_EXECUTABLE= Paths.get(System.getProperty("user.home"),"docker-wd/IdeaProjects/Polyglot-examples",".venv","bin","python").toString();

    void passingJavaObj() {
        Context context= Context.newBuilder("python")
                .allowAllAccess(true)
                .option("python.Executable",VENV_EXECUTABLE)
                .option("python.ForceImportSite","true")
                .build();
        try{
            File file= new File("src/main/polyglot/halwa.py");
            Map<String,String> roomMap= new HashMap<>();
            roomMap.put("113", "anadi");
            roomMap.put("110", "utkarsh");
            roomMap.put("103", "david");

            Map<String,Object> obj= new HashMap<>();
            obj.put("roomMap",roomMap);
            H12 h12= new H12(obj);
            Value pythonBindings= context.getBindings("python");
            pythonBindings.putMember("testCases",new TestCases());   //setting the proxy object to python var
            context.eval("python", "testCases.method")
                    .execute(h12);
//            context.eval(Source.newBuilder("python",file).build());
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FunctionalInterface
    interface ProxyObjectProvider {
        ProxyObject getObject();
    }

    @HostAccess.Export
    public void method(Value o) {
        H12 proxy = o.asProxyObject();
        System.out.println(proxy.values); // access values
    }
}