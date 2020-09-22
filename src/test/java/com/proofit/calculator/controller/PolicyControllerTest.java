package com.proofit.calculator.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proofit.calculator.domain.*;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PolicyControllerTest {

    String url;
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        url = "http://localhost:" + port + "/api/v1/policies/calculate-premium";
    }


    @Test
    public void checkCalculatePremium() throws Exception {
        Policy policyForTest = createPolicyForTest();
        String requestJson = objectMapper.writeValueAsString(policyForTest);


        BigDecimal expectedResult = new BigDecimal("2.28");
        String actualResult = createRestTemplateResponseForTesting(url, requestJson);

        assertEquals(expectedResult.toString(), actualResult);
    }

    @Test
    public void shouldThrowApiExceptionForEmptyPolicyObject() throws JsonProcessingException {
        String requestJson = "";

        String dataFromResponse = createRestTemplateResponseForTesting(url, requestJson);

        JSONObject jsonDataFromResponse = (JSONObject) JSONValue.parse(dataFromResponse);

        String expectedResult = "Policy data is empty";
        String actualResult = (String) jsonDataFromResponse.get("message");

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void shouldThrowValidationExceptionForEmptyPolicyNumber() throws JsonProcessingException {
        Policy policyForTest = createPolicyWithEmptyNumberForTest();
        String requestJson = objectMapper.writeValueAsString(policyForTest);

        String dataFromResponse = createRestTemplateResponseForTesting(url, requestJson);

        JSONObject jsonDataFromResponse = (JSONObject) JSONValue.parse(dataFromResponse);
        JSONArray jsonArray = (JSONArray) jsonDataFromResponse.get("validation errors");

        String expectedResult = "Policy number is mandatory";
        String actualResult = jsonArray.toString().replaceAll("[\\[\\]\"]", "");

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void shouldThrowValidationExceptionForPolicyWithInsuredSumEqualToZero() throws JsonProcessingException {
        Policy policyForTest = createPolicyWithInsuredSumEqualToZero();
        String requestJson = objectMapper.writeValueAsString(policyForTest);

        String dataFromResponse = createRestTemplateResponseForTesting(url, requestJson);

        JSONObject jsonDataFromResponse = (JSONObject) JSONValue.parse(dataFromResponse);
        JSONArray jsonArray = (JSONArray) jsonDataFromResponse.get("validation errors");

        String expectedResult = "Insured sum must be positive and greater then 0";
        String actualResult = jsonArray.toString().replaceAll("[\\[\\]\"]", "");

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void shouldThrowValidationExceptionForPolicyWithNegativeInsuredSum() throws JsonProcessingException {
        Policy policyForTest = createPolicyWithNegativeInsuredSum();
        String requestJson = objectMapper.writeValueAsString(policyForTest);

        String dataFromResponse = createRestTemplateResponseForTesting(url, requestJson);

        JSONObject jsonDataFromResponse = (JSONObject) JSONValue.parse(dataFromResponse);
        JSONArray jsonArray = (JSONArray) jsonDataFromResponse.get("validation errors");

        String expectedResult = "Insured sum must be positive and greater then 0";
        String actualResult = jsonArray.toString().replaceAll("[\\[\\]\"]", "");

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void shouldThrowValidationExceptionForPolicyWithEmptyPolicyObjectName() throws JsonProcessingException {
        Policy policyForTest = createPolicyWithEmptyPolicyObjectName();
        String requestJson = objectMapper.writeValueAsString(policyForTest);

        String dataFromResponse = createRestTemplateResponseForTesting(url, requestJson);

        JSONObject jsonDataFromResponse = (JSONObject) JSONValue.parse(dataFromResponse);
        JSONArray jsonArray = (JSONArray) jsonDataFromResponse.get("validation errors");

        String expectedResult = "Policy object name is mandatory";
        String actualResult = jsonArray.toString().replaceAll("[\\[\\]\"]", "");

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void shouldThrowValidationExceptionForPolicyWithEmptyPolicySubObjectName() throws JsonProcessingException {
        Policy policyForTest = createPolicyWithEmptyPolicySubObjectName();
        String requestJson = objectMapper.writeValueAsString(policyForTest);

        String dataFromResponse = createRestTemplateResponseForTesting(url, requestJson);

        JSONObject jsonDataFromResponse = (JSONObject) JSONValue.parse(dataFromResponse);
        JSONArray jsonArray = (JSONArray) jsonDataFromResponse.get("validation errors");

        String expectedResult = "Policy sub object name is mandatory";
        String actualResult = jsonArray.toString().replaceAll("[\\[\\]\"]", "");

        assertEquals(expectedResult, actualResult);
    }


    String createRestTemplateResponseForTesting(String url, String requestJson) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);

        return response.getBody();
    }


    Policy createPolicyForTest() {
        PolicySubObject policySubObjectFire = new PolicySubObject("TV", BigDecimal.valueOf(100), RiskType.FIRE);
        PolicySubObject policySubObjectTheft = new PolicySubObject("Laptop", BigDecimal.valueOf(8), RiskType.THEFT);

        List<PolicySubObject> policySubObjects = Arrays.asList(policySubObjectFire, policySubObjectTheft);

        PolicyObject policyObject = new PolicyObject("House", policySubObjects);

        List<PolicyObject> policyObjects = new ArrayList<>();
        policyObjects.add(policyObject);

        return new Policy("123", PolicyStatus.REGISTERED, policyObjects);
    }


    Policy createPolicyWithEmptyNumberForTest() {
        PolicySubObject policySubObjectFire = new PolicySubObject("TV", BigDecimal.valueOf(100), RiskType.FIRE);
        PolicySubObject policySubObjectTheft = new PolicySubObject("Laptop", BigDecimal.valueOf(8), RiskType.THEFT);

        List<PolicySubObject> policySubObjects = Arrays.asList(policySubObjectFire, policySubObjectTheft);

        PolicyObject policyObject = new PolicyObject("House", policySubObjects);

        List<PolicyObject> policyObjects = new ArrayList<>();
        policyObjects.add(policyObject);

        return new Policy("", PolicyStatus.REGISTERED, policyObjects);
    }

    Policy createPolicyWithInsuredSumEqualToZero() {
        PolicySubObject policySubObjectFire = new PolicySubObject("TV", BigDecimal.valueOf(0), RiskType.FIRE);
        PolicySubObject policySubObjectTheft = new PolicySubObject("Laptop", BigDecimal.valueOf(8), RiskType.THEFT);

        List<PolicySubObject> policySubObjects = Arrays.asList(policySubObjectFire, policySubObjectTheft);

        PolicyObject policyObject = new PolicyObject("House", policySubObjects);

        List<PolicyObject> policyObjects = new ArrayList<>();
        policyObjects.add(policyObject);

        return new Policy("123", PolicyStatus.REGISTERED, policyObjects);
    }

    Policy createPolicyWithNegativeInsuredSum() {
        PolicySubObject policySubObjectFire = new PolicySubObject("TV", BigDecimal.valueOf(-10), RiskType.FIRE);
        PolicySubObject policySubObjectTheft = new PolicySubObject("Laptop", BigDecimal.valueOf(8), RiskType.THEFT);

        List<PolicySubObject> policySubObjects = Arrays.asList(policySubObjectFire, policySubObjectTheft);

        PolicyObject policyObject = new PolicyObject("House", policySubObjects);

        List<PolicyObject> policyObjects = new ArrayList<>();
        policyObjects.add(policyObject);

        return new Policy("123", PolicyStatus.REGISTERED, policyObjects);
    }

    Policy createPolicyWithEmptyPolicyObjectName() {
        PolicySubObject policySubObjectFire = new PolicySubObject("TV", BigDecimal.valueOf(100), RiskType.FIRE);
        PolicySubObject policySubObjectTheft = new PolicySubObject("Laptop", BigDecimal.valueOf(8), RiskType.THEFT);

        List<PolicySubObject> policySubObjects = Arrays.asList(policySubObjectFire, policySubObjectTheft);

        PolicyObject policyObject = new PolicyObject("", policySubObjects);

        List<PolicyObject> policyObjects = new ArrayList<>();
        policyObjects.add(policyObject);

        return new Policy("123", PolicyStatus.REGISTERED, policyObjects);
    }

    Policy createPolicyWithEmptyPolicySubObjectName() {
        PolicySubObject policySubObjectFire = new PolicySubObject("", BigDecimal.valueOf(100), RiskType.FIRE);
        PolicySubObject policySubObjectTheft = new PolicySubObject("Laptop", BigDecimal.valueOf(8), RiskType.THEFT);

        List<PolicySubObject> policySubObjects = Arrays.asList(policySubObjectFire, policySubObjectTheft);

        PolicyObject policyObject = new PolicyObject("House", policySubObjects);

        List<PolicyObject> policyObjects = new ArrayList<>();
        policyObjects.add(policyObject);

        return new Policy("123", PolicyStatus.REGISTERED, policyObjects);
    }


}