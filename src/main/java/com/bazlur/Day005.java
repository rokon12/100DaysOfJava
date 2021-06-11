package com.bazlur;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class Day005 {

    public static void main(String[] args) throws IOException {
        var text = Files.readString(Path.of("file.txt"));

        long count = Arrays.stream(text
                .replaceAll("\\p{Punct}", "")
                .split(" "))
                .distinct()
                .count();
        System.out.println("count = " + count);
    }
}
