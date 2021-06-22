package com.bazlur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;

public class Day016 {
  public static void main(String[] args) throws IOException {
    if (args.length != 1) throw new IllegalArgumentException("Please specify port");
    var port = Integer.parseInt(args[0]);

    var serverSocket = new ServerSocket(port);
    System.out.println("Started server on port " + port);

    while (true) {
      try (var socket = serverSocket.accept();
           var in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
           var out = new PrintWriter(socket.getOutputStream(), true)) {
        String line;
        while ((line = in.readLine()) != null) {
          out.println(line.toUpperCase());
        }
      } catch (IOException e) {
        System.out.println("Was unable to establish or communicate with client socket:" + e.getMessage());
      }
    }
  }
}
