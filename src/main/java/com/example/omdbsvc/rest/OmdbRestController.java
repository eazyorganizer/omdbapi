package com.example.omdbsvc.rest;

import java.util.HashMap;
import java.util.Map;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author iotmade.com
 */
@Log4j2
@RestController
public class OmdbRestController {

    @Value("${omdb.api.key}")
    private String apiKey;

    @Value("${omdb.api.uri}")
    private String baseUrl;

    private final RestTemplate restTemplate;

    public OmdbRestController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<?> searchMovie(@PathVariable String title) {
        UriComponentsBuilder ucb = UriComponentsBuilder.fromHttpUrl(baseUrl);
        
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("apiKey", apiKey);
        uriVariables.put("t", title);
        
        UriComponents buildAndExpand = ucb.query("apikey={apiKey}")
                .query("t={t}").buildAndExpand(uriVariables);

        log.info(buildAndExpand.toString());

        ResponseEntity<String> responseEntity = this.restTemplate
                .exchange(buildAndExpand.toString(), HttpMethod.GET, null, String.class);
        return responseEntity;
    }
}
