package com.proofit.calculator.service.helpers;

import com.proofit.calculator.domain.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PremiumCalculatorTest {

    @Test
    public void shouldCalculatePremiumDefault() {
        PolicySubObject policySubObjectFire = new PolicySubObject("TV", BigDecimal.valueOf(100), RiskType.FIRE);
        PolicySubObject policySubObjectTheft = new PolicySubObject("Laptop", BigDecimal.valueOf(8), RiskType.THEFT);

        List<PolicySubObject> policySubObjects = new ArrayList<>();
        policySubObjects.add(policySubObjectFire);
        policySubObjects.add(policySubObjectTheft);


        PolicyObject policyObject = new PolicyObject("House", policySubObjects);

        List<PolicyObject> policyObjects = new ArrayList<>();
        policyObjects.add(policyObject);

        Policy policyForTest = new Policy("123", PolicyStatus.REGISTERED, policyObjects);

        PremiumCalculator victim = new PremiumCalculator();
        BigDecimal expectedResult = BigDecimal.valueOf(2.28);
        BigDecimal actualResult = victim.calculatePremium(policyForTest);

        assertEquals(expectedResult, actualResult);
    }


    @Test
    public void shouldCalculatePremiumGreater() {
        PolicySubObject policySubObjectFire = new PolicySubObject("TV", BigDecimal.valueOf(500), RiskType.FIRE);
        PolicySubObject policySubObjectTheft = new PolicySubObject("Laptop", BigDecimal.valueOf(102.51), RiskType.THEFT);

        List<PolicySubObject> policySubObjects = new ArrayList<>();
        policySubObjects.add(policySubObjectFire);
        policySubObjects.add(policySubObjectTheft);


        PolicyObject policyObject = new PolicyObject("House", policySubObjects);

        List<PolicyObject> policyObjects = new ArrayList<>();
        policyObjects.add(policyObject);

        Policy policyForTest = new Policy("123", PolicyStatus.REGISTERED, policyObjects);

        PremiumCalculator victim = new PremiumCalculator();
        BigDecimal expectedResult = BigDecimal.valueOf(17.13);
        BigDecimal actualResult = victim.calculatePremium(policyForTest);

        assertEquals(expectedResult, actualResult);
    }

}
