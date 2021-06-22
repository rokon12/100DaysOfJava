package com.bazlur;

import javax.tools.*;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;

public class Day015 {
  public static void main(String[] args) throws IOException {
    JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

    DiagnosticCollector<JavaFileObject> ds = new
            DiagnosticCollector<>();

    try (var mgr =
                 compiler.getStandardFileManager(ds, null, null)) {

      var file = Path.of("/Users/rokonoid/projects/books/100DaysOfJava/src/main/java/com/bazlur/Day002.java").toFile();
      Iterable<? extends JavaFileObject> sources = mgr.getJavaFileObjectsFromFiles(Arrays.
              asList(file));
      JavaCompiler.CompilationTask task =
              compiler.getTask(null, mgr, ds, null,
                      null, sources);
      var call = task.call();


    } catch (IOException e) {
      e.printStackTrace();
    }

    for (Diagnostic<? extends JavaFileObject>
            d : ds.getDiagnostics()) {
      System.out.format("Line: %d, %s in %s, %s",
              d.getLineNumber(), d.getMessage(null),
              d.getSource().getName(), d.getCode());
    }
  }
}
