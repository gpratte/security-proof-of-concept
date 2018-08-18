package com.example.poc.controller;

import com.example.poc.PocApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {"accept.tkn1=true"})

public class WidgetControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    /**
     * Should pass A&A for tkn1 and user
     */
    @Test
    public void failAuthNUnknownToken() {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "TknABC 12345");

        String url = "http://localhost:" + port + "/tkn1/user/widgets";
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        Assert.assertEquals("Should be 400 failure", 401, response.getStatusCodeValue());
    }

    /**
     * Should pass A&A for tkn1 and user
     */
    @Test
    public void passTkn1AndUser() {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Tkn1 12345");

        String url = "http://localhost:" + port + "/tkn1/user/widgets";
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        Assert.assertEquals("Should be 200 success", 200, response.getStatusCodeValue());
    }

    /**
     * Should not pass AuthZ for tkn1 and user
     * @throws Exception
     */
    @Test
    public void failAuthZTkn1AndUser() throws Exception {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Tkn1 12345");

        String url = "http://localhost:" + port + "/tkn1/superuser/widgets";
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        System.out.println(response);

        Assert.assertEquals("Should be 403 unauthorized", 403, response.getStatusCodeValue());
    }

    /**
     * Should pass A&A for tkn1 and super user
     * @throws Exception
     */
    @Test
    public void passTkn1AndSuperUser() throws Exception {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Tkn1 23456");

        String url = "http://localhost:" + port + "/tkn1/superuser/widgets";
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        Assert.assertEquals("Should be 200 success", 200, response.getStatusCodeValue());
    }


}