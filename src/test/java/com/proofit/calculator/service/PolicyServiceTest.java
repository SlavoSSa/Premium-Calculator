package com.proofit.calculator.service;

import com.proofit.calculator.businesslogic.PremiumCalculator;
import com.proofit.calculator.domain.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PolicyServiceTest {

    @Mock
    private PremiumCalculator premiumCalculator;

    @InjectMocks
    private PolicyService policyService;


    @Test
    public void shouldCalculatePolicyPremium() {
        Policy policyForTest = createPolicyForTest();

        BigDecimal expectedResult = new BigDecimal("2.28");

        when(premiumCalculator.calculatePremium(policyForTest)).thenReturn(expectedResult);
        BigDecimal actualResult = policyService.calculatePolicyPremium(policyForTest);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void shouldReturnOneCallForCalculatePolicyPremium() {
        Policy policyForTest = createPolicyForTest();

        premiumCalculator.calculatePremium(policyForTest);
        verify(premiumCalculator, times(1)).calculatePremium(policyForTest);
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

}