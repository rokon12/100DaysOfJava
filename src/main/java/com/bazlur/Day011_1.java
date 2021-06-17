package com.bazlur;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutionException;

public class Day011_1 {

  public static final String CHUCKNORRIS_RANDOM_JOKES_API = "https://api.chucknorris.io/jokes/random";

  public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException, ExecutionException {

    var objectMapper = new UncheckedObjectMapper();
    objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);

    var httpClient = HttpClient.newHttpClient();
    var request = HttpRequest.newBuilder()
            .uri(new URI(CHUCKNORRIS_RANDOM_JOKES_API))
            .header("Accept", "application/json")
            .GET()
            .build();

    var joke = httpClient.sendAsync(request, BodyHandlers.ofString())
            .thenApply(HttpResponse::body)
            .thenApply(body-> {
              System.out.println("body = " + body);
              return body;
            })
            .thenApply(objectMapper::readValue)
            .get();
    System.out.println("joke = " + joke.value());
  }


  @JsonIgnoreProperties(ignoreUnknown = true)
  record Joke(
          @JsonProperty("created_at")
          String createdAt,
          @JsonProperty("icon_url")
          String iconUrl,
          @JsonProperty("id")
          String id,
          @JsonProperty("updated_at")
          String updatedAt,
          @JsonProperty("url")
          String url,
          @JsonProperty("value")
          String value
  ) { }

  static class UncheckedObjectMapper extends ObjectMapper {
    Joke readValue(String content) {
      try {
        return this.readValue(content, new TypeReference<>() {
        });
      } catch (JsonProcessingException e) {
        throw new CompletionException(e);
      }
    }
  }
}
