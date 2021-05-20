package com.api.sample.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Slf4j
@Component
public class TestClient {
    public void pokeIntegration() {
        WebClient client = WebClient.builder()
                                    .baseUrl("https://pokeapi.co/api/v2")
                                    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                                    .build();

        ObjectNode response = client.get()
                                    .uri("/pokemon/ditto")
                                    .retrieve()
                                    .bodyToMono(ObjectNode.class)
                                    .block();

        System.out.println(response);
    }
}
