package com.bazlur;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class Day011 {
  public static final String CHUCK_NORRIS_RANDOM_JOKES_API = "https://api.chucknorris.io/jokes/random";

  public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
    var client = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .connectTimeout(Duration.ofSeconds(20))
            .build();

    var request = HttpRequest.newBuilder()
            .uri(new URI(CHUCK_NORRIS_RANDOM_JOKES_API))
            .header("Accept", "application/json")
            .GET()
            .build();

//    var response = client.send(request, HttpResponse.BodyHandlers.ofString());
//    var response2 = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
//    var body = response.body();
//    System.out.println("body = " + body);

    var completableFuture = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
            .thenApply(HttpResponse::body);

  }
}
