package com.bazlur.threads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Playground {
  public static void main(String[] args) throws IOException {
    var ss = new ServerSocket(4444);
    while (true) {
      var cs = ss.accept();
      var thread = new Thread( () -> handle(cs));
      thread.start();
    }
  }

  private static void handle(Socket cs) {
    System.out.println(Thread.currentThread()+  ": Connected from " + cs.toString());
    try (cs;
         var in = new BufferedReader(new InputStreamReader(cs.getInputStream()));
         var out = new PrintWriter(cs.getOutputStream(), true);
    ) {
      String line;
      while ((line = in.readLine()) != null) {
        System.out.println("Received: " + line);
        out.println(line.toUpperCase());
      }
    } catch (IOException exception) {
      exception.printStackTrace();
    }
  }
}
