package com.marvel.api.marverapi.service.impl;
import com.marvel.api.marverapi.exception.ApiErrorException;
import com.marvel.api.marverapi.service.HttpClientService;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.Collections;
import java.util.Map;

import static java.lang.String.format;

@Service
public class RestTemplateService implements HttpClientService {

    private final RestTemplate restTemplate;

    public RestTemplateService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public <T> T doGet(String endpoint, Map<String, String> queryParams, Class<T> responseType) {
        String finalUrl = buildFinalUrl(endpoint, queryParams);
        HttpEntity httpEntity = new HttpEntity(getHeaders());
        ResponseEntity<T> response = restTemplate.exchange(finalUrl, HttpMethod.GET, httpEntity, responseType);
        ExceptionErrorStatuscode(endpoint, response,HttpMethod.GET);

        return response.getBody();
    }

    @Override
    public <T, R> T doPost(String endpoint, Map<String, String> queryParams, Class<T> responseType, R bodyRequest) {
        String finalUrl = buildFinalUrl(endpoint, queryParams);
        HttpEntity httpEntity = new HttpEntity(bodyRequest, getHeaders());
        ResponseEntity<T> response = restTemplate.exchange(finalUrl, HttpMethod.POST, httpEntity, responseType);
        ExceptionErrorStatuscode(endpoint, response,HttpMethod.POST);

        return response.getBody();
    }

    @Override
    public <T, R> T doPut(String endpoint, Map<String, String> queryParams, Class<T> responseType, R bodyRequest) {
        String finalUrl = buildFinalUrl(endpoint, queryParams);
        HttpEntity httpEntity = new HttpEntity(bodyRequest, getHeaders());
        ResponseEntity<T> response = restTemplate.exchange(finalUrl, HttpMethod.PUT, httpEntity, responseType);
        ExceptionErrorStatuscode(endpoint, response,HttpMethod.PUT);

        return response.getBody();
    }

    @Override
    public <T> T doDelete(String endpoint, Map<String, String> queryParams, Class<T> responseType) {
        String finalUrl = buildFinalUrl(endpoint, queryParams);
        HttpEntity httpEntity = new HttpEntity(getHeaders());
        ResponseEntity<T> response = restTemplate.exchange(finalUrl, HttpMethod.DELETE, httpEntity, responseType);
        ExceptionErrorStatuscode(endpoint, response,HttpMethod.DELETE);

        return response.getBody();
    }

    private static <T> void ExceptionErrorStatuscode(String endpoint, @NotNull ResponseEntity<T> response, HttpMethod method) {
        if (!response.getStatusCode().is2xxSuccessful()) {
            String message = format("Error consumiendo endpoint [ {} - {} ], StatusCode: {} ",
                    method, endpoint, response.getStatusCode());
            throw  new ApiErrorException(message);
        }
    }

    @NotNull
    private static String buildFinalUrl(String endpoint, Map<String, String> queryParams) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(endpoint);

        if (queryParams != null) {
            for (Map.Entry<String, String> entry : queryParams.entrySet()) {
                builder.queryParam(entry.getKey(), entry.getValue());
            }
        }
        String finalUrl = builder.build().encode().toUriString();
        return finalUrl;
    }

    @NotNull
    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.put("Authorization", Collections.singletonList("bearer jwt"));
        return headers;
    }
}
