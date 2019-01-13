package com.example.omdbsvc;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author iotmade.com
 */
@Configuration
public class AppConfig {

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplateBuilder()
                .interceptors((ClientHttpRequestInterceptor) (httpRequest, bytes, execution) -> {

                    httpRequest.getHeaders().add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_UTF8_VALUE);

                    return execution.execute(httpRequest, bytes);
                })
                .build();
    }
}
