package com.bazlur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EchoServer {
  private final ServerSocket serverSocket;
  private final ExecutorService executors = Executors.newFixedThreadPool(10);

  public EchoServer(int portNumber) throws IOException {
    this.serverSocket = new ServerSocket(portNumber);
  }

  public static void main(String[] args) throws IOException {
    new EchoServer(1200).start();
  }

  public void start() {
    while (true) {
      try {
        var socket = serverSocket.accept();
        executors.submit(() -> handle(socket));
      } catch (IOException e) {
        System.err.println("Server was unable to accept connection: " + e.getMessage());
      }
    }
  }

  private void handle(Socket socket) {
    try (var in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
         var out = new PrintWriter(socket.getOutputStream(), true)) {
      String line;
      while ((line = in.readLine()) != null) {

        //out.write(line.toUpperCase() + "\n");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}


//