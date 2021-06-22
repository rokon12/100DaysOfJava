package com.bazlur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class Day015_1 {
  public static void main(String[] args) throws IOException {
    var command = new ProcessBuilder("java", "--version");
    var process = command.start();
    var inputStream = process.getInputStream();
    var output = new BufferedReader(new InputStreamReader(inputStream))
            .lines()
            .collect(Collectors.joining("\n"));

    System.out.println(output);
  }
}

//output:
//openjdk 16.0.1 2021-04-20
//OpenJDK Runtime Environment (build 16.0.1+9-24)
//OpenJDK 64-Bit Server VM (build 16.0.1+9-24, mixed mode, sharing)