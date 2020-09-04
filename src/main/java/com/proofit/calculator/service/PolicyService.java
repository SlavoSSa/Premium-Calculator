package com.proofit.calculator.service;

import com.proofit.calculator.domain.*;
import com.proofit.calculator.service.helpers.PremiumCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
public class PolicyService {

    private final PremiumCalculator premiumCalculator;

    @Autowired
    public PolicyService(PremiumCalculator premiumCalculator) {
        this.premiumCalculator = premiumCalculator;
    }

    public BigDecimal calculatePolicyPremium(Policy policy){
        return premiumCalculator.calculatePremium(policy);
    }



}
