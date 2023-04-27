package org.example.itm;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@ExtendWith({OutputCaptureExtension.class})
@SpringBootTest(webEnvironment = RANDOM_PORT)
@ContextConfiguration(classes = CloudFunctionApplication.class)
class CloudFunctionApplicationTest {
    private static final String BASE_URL = "http://localhost";
    private static final String BODY = "ITmagination";
    private final RestTemplate rest;
    private final HttpHeaders headers = new HttpHeaders();
    private final String url;

    public CloudFunctionApplicationTest(
            @Autowired RestTemplateBuilder restTemplate,
            @LocalServerPort int serverPort) {
        rest = restTemplate.build();
        headers.setContentType(MediaType.TEXT_PLAIN);
        url = BASE_URL + ":" + serverPort + "/";
    }

    @Test
    void lambdaHandlerWithFunctionTest() {
        // given
        HttpEntity<String> request = new HttpEntity<>(BODY, headers);
        // when:
        String response = rest.postForEntity(url + "lambdaHandlerWithFunction", request, String.class).getBody();
        // then:
        assertThat(response).isEqualTo("Lambda function handler was triggered: " + new StringBuilder(BODY).reverse());
    }

    @Test
    void lambdaHandlerWithSupplierTest() {
        // given
        HttpEntity<String> request = new HttpEntity<>(null, headers);
        // when:
        String response = rest.postForEntity(url + "lambdaHandlerWithSupplier", request, String.class).getBody();
        // then:
        assertThat(response).isEqualTo("Lambda supplier handler was triggered");

    }

    @Test
    void lambdaHandlerWithConsumerTest(CapturedOutput output) {
        // given
        HttpEntity<String> request = new HttpEntity<>(BODY, headers);
        // when:
        rest.postForEntity(url + "lambdaHandlerWithConsumer", request, String.class);
        // then:
        assertThat(output).contains("Lambda consumer handler was triggered:" + BODY);
    }

    @Test
    void functionHandlerTest(CapturedOutput output) {
        // given
        HttpEntity<String> request = new HttpEntity<>(BODY, headers);
        // when:
        String result = rest.postForEntity(url + "functionHandler", request, String.class).getBody();
        // then:
        assertThat(result).isEqualTo("Lambda function handler from class was triggered: " + BODY);
    }
}