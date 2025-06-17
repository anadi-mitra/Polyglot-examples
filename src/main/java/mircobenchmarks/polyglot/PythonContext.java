package mircobenchmarks.polyglot;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.HostAccess;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class PythonContext {
    private final Context context;

    public PythonContext() {
        String VENV_EXECUTABLE = Paths.get(System.getProperty("user.home"), "docker-wd/IdeaProjects/Polyglot-examples", ".venv", "bin", "python").toString();
        context = Context.newBuilder("python")
                .option("python.Executable", VENV_EXECUTABLE)
                .allowHostAccess(HostAccess.ALL)
                .allowHostClassLookup(s -> true)
                .option("python.ForceImportSite", "true")
                .build();
    }

    public Value getBindings(File file) throws IOException {
        context.eval(Source.newBuilder("python", file).build());
        return context.getBindings("python");
    }
}
