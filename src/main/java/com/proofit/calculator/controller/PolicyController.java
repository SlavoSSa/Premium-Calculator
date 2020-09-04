package com.proofit.calculator.controller;

import com.proofit.calculator.domain.Policy;
import com.proofit.calculator.exception.ApiRequestException;
import com.proofit.calculator.service.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/policies")
public class PolicyController {


    private final PolicyService policyService;

    public PolicyController(PolicyService policyService) {
        this.policyService = policyService;
    }


    @GetMapping("/calculate-premium")
    public BigDecimal calculatePremium (@RequestBody Policy policy){
        return Optional.ofNullable(policyService.calculatePolicyPremium(policy))
                .orElseThrow(() -> new ApiRequestException("Something wrong with policy data"));
    }

}
