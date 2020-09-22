package com.proofit.calculator.controller;

import com.proofit.calculator.domain.Policy;
import com.proofit.calculator.exception.ApiRequestException;
import com.proofit.calculator.service.PolicyService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.math.BigDecimal;


@RestController
@Validated
@RequestMapping("/api/v1/policies")
public class PolicyController {

    private final PolicyService policyService;

    public PolicyController(PolicyService policyService) {
        this.policyService = policyService;
    }

    @ResponseBody
    @PostMapping("/calculate-premium")
    public BigDecimal calculatePremium(@Valid @RequestBody(required = false) Policy policy) {
        if (policy == null) throw new ApiRequestException("Policy data is empty");

        return policyService.calculatePolicyPremium(policy);
    }

}
