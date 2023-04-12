package com.codingrecette.restversioning;

import org.instancio.Instancio;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import com.codingrecette.restversioning.model.CommuneV1;
import com.codingrecette.restversioning.model.CommuneV2;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CommuneControllerTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    @Order(1)
    void addV0() {
        CommuneV1 p = restTemplate.postForObject("/commune/v1.1", Instancio.create(CommuneV1.class), CommuneV1.class);
        assertNotNull(p);
        assertNotNull(p.getCodePostal());
    }

    @Test
    @Order(2)
    void addV2() {
        CommuneV2 p = restTemplate.postForObject("/commune/v1.2", Instancio.create(CommuneV2.class), CommuneV2.class);
        assertNotNull(p);
        assertNotNull(p.getCodePostal());
        assertTrue(p.getAge() > 0);
    }

    @Test
    @Order(3)
    void findByIdV0() {
        CommuneV1 p = restTemplate.getForObject("/commune/v1.0/{codePostal}", CommuneV1.class, 44120);
        assertNotNull(p);
        assertNotNull(p.getCodePostal());
    }

    @Test
    @Order(3)
    void findByIdV2() {
        CommuneV2 p = restTemplate.getForObject("/commune/v1.2/{codePostal}", CommuneV2.class, 44115);
        assertNotNull(p);
        assertNotNull(p.getCodePostal());
        assertTrue(p.getAge() > 0);
    }

    @Test
    @Order(3)
    void findByIdV2ToV1Compability() {
        CommuneV2 p = restTemplate.getForObject("/commune/v1.2/{codePostal}", CommuneV2.class, 44120);
        assertNotNull(p);
        assertNotNull(p.getCodePostal());
        assertTrue(p.getAge() > 0);
    }
}
