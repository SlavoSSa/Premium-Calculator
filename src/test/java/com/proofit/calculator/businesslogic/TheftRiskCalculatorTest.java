package com.proofit.calculator.businesslogic;

import com.proofit.calculator.domain.RiskType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class TheftRiskCalculatorTest {

    private final BigDecimal sumInsuredTheftDefault = BigDecimal.valueOf(10);
    private final BigDecimal sumInsuredTheftConfigured = BigDecimal.valueOf(20);
    private TheftRiskCalculator theftRiskCalculator;

    @BeforeEach
    void setUp() {
        theftRiskCalculator = new TheftRiskCalculator();
    }


    @Test
    public void shouldCalculateTheftRiskOnDefaultCoefficient() {
        BigDecimal expectedResult = new BigDecimal("1.10");
        BigDecimal actualResult = theftRiskCalculator.applyRisk(sumInsuredTheftDefault);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void shouldCalculateTheftRiskOnConfiguredCoefficient() {
        BigDecimal expectedResult = new BigDecimal("1.00");
        BigDecimal actualResult = theftRiskCalculator.applyRisk(sumInsuredTheftConfigured);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void checkIsApplicableForTheftRisk() {
        boolean actualResult = theftRiskCalculator.isApplicable(RiskType.THEFT);
        assertTrue(actualResult);
    }

    @Test
    public void checkIsApplicableNotForTheftRisk() {
        boolean actualResult = theftRiskCalculator.isApplicable(RiskType.FIRE);
        assertFalse(actualResult);
    }

    @Test
    public void checkIsEqualOrGreaterThanConfigured() {
        boolean actualResult = theftRiskCalculator.isEqualOrGreaterThanConfigured(sumInsuredTheftConfigured);
        assertTrue(actualResult);
    }

    @Test
    public void checkIsNotEqualOrGreaterThanConfigured() {
        boolean actualResult = theftRiskCalculator.isEqualOrGreaterThanConfigured(sumInsuredTheftDefault);
        assertFalse(actualResult);
    }
}