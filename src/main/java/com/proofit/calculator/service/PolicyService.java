package com.proofit.calculator.service;

import com.proofit.calculator.domain.*;
import com.proofit.calculator.businesslogic.PremiumCalculator;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PolicyService {

    private final PremiumCalculator premiumCalculator;

    public PolicyService(PremiumCalculator premiumCalculator) {
        this.premiumCalculator = premiumCalculator;
    }

    public BigDecimal calculatePolicyPremium(Policy policy) {
        return premiumCalculator.calculatePremium(policy);
    }

}
