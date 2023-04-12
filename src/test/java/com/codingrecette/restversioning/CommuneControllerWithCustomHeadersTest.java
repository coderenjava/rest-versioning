package com.codingrecette.restversioning;

import org.instancio.Instancio;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import com.codingrecette.restversioning.model.CommuneV1;
import com.codingrecette.restversioning.model.CommuneV2;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CommuneControllerWithCustomHeadersTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    @Order(1)
    void addV0() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-VERSION", "v1.0");
        HttpEntity<CommuneV1> httpEntity = new HttpEntity<>(Instancio.create(CommuneV1.class), headers);
        ResponseEntity<CommuneV1> p = restTemplate.exchange("/commune-custom-header", HttpMethod.POST, httpEntity, CommuneV1.class);
        assertTrue(p.getStatusCode().is2xxSuccessful());
        assertNotNull(p.getBody());
        assertNotNull(p.getBody().getCodePostal());
        assertNotNull(p.getBody().getAnciennete());
    }

    @Test
    @Order(2)
    void addV2() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-VERSION", "v1.2");
        HttpEntity<CommuneV2> httpEntity = new HttpEntity<>(Instancio.create(CommuneV2.class), headers);
        ResponseEntity<CommuneV2> p = restTemplate.exchange("/commune-custom-header", HttpMethod.POST, httpEntity, CommuneV2.class);
        assertTrue(p.getStatusCode().is2xxSuccessful());
        assertNotNull(p.getBody());
        assertNotNull(p.getBody().getCodePostal());
        assertTrue(p.getBody().getAge() > 0);
    }

    @Test
    @Order(3)
    void findByIdV0() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-VERSION", "v1.0");
        HttpEntity<CommuneV2> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<CommuneV1> p = restTemplate.exchange("/commune-custom-header/{codePostal}", HttpMethod.GET, httpEntity, CommuneV1.class, 44120);
        assertTrue(p.getStatusCode().is2xxSuccessful());
        assertNotNull(p.getBody());
        assertNotNull(p.getBody().getCodePostal());
    }

    @Test
    @Order(3)
    void findByIdV2() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-VERSION", "v1.2");
        HttpEntity<CommuneV2> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<CommuneV2> p = restTemplate.exchange("/commune-custom-header/{codePostal}", HttpMethod.GET, httpEntity, CommuneV2.class, 44115);
        assertTrue(p.getStatusCode().is2xxSuccessful());
        assertNotNull(p.getBody());
        assertNotNull(p.getBody().getCodePostal());
        assertTrue(p.getBody().getAge() > 0);
    }

    @Test
    @Order(3)
    void findByIdV2ToV1Compability() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-VERSION", "v1.2");
        HttpEntity<CommuneV2> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<CommuneV2> p = restTemplate.exchange("/commune-custom-header/{codePostal}", HttpMethod.GET, httpEntity, CommuneV2.class, 44120);
        assertTrue(p.getStatusCode().is2xxSuccessful());
        assertNotNull(p.getBody());
        assertNotNull(p.getBody().getCodePostal());
        assertTrue(p.getBody().getAge() > 0);
    }
}
