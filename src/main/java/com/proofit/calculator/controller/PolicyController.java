package com.proofit.calculator.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.proofit.calculator.domain.Policy;
import com.proofit.calculator.domain.PolicyObject;
import com.proofit.calculator.domain.PolicySubObject;
import com.proofit.calculator.service.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/policies")
public class PolicyController {


    private PolicyService policyService;

    @Autowired
    public PolicyController(PolicyService policyService) {
        this.policyService = policyService;
    }

    @GetMapping("/hello")
    public String getHello() throws JsonProcessingException {
       return policyService.helloMethod();
    }

    @GetMapping("/")
    public Policy testJsonPolicy(){
        return policyService.testJsonPolicy();
    }


    @PostMapping("/test")
    public Policy testPost(@RequestBody Policy policy){
        return policyService.getPol(policy);
    }


    @PostMapping("/test1")
    public PolicyObject testPostSu(@RequestBody PolicyObject policyObject){
        return policyObject;
    }

    @PostMapping("/calculator")
    public BigDecimal calculatePremium (@RequestBody Policy policy){
        return policyService.calculate(policy);
    }

}
