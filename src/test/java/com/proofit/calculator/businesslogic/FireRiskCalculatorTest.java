package com.proofit.calculator.businesslogic;

import com.proofit.calculator.domain.RiskType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class FireRiskCalculatorTest {

    private final BigDecimal sumInsuredFireDefault = BigDecimal.valueOf(100);
    private final BigDecimal sumInsuredFireIncreased = BigDecimal.valueOf(500);
    private FireRiskCalculator fireRiskCalculator;

    @BeforeEach
    void setUp() {
        fireRiskCalculator = new FireRiskCalculator();
    }


    @Test
    public void shouldCalculateFireRiskOnDefaultCoefficient() {
        BigDecimal expectedResult = new BigDecimal("1.400");
        BigDecimal actualResult = fireRiskCalculator.applyRisk(sumInsuredFireDefault);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void shouldCalculateFireRiskOnIncreasedCoefficient() {
        BigDecimal expectedResult = new BigDecimal("12.000");
        BigDecimal actualResult = fireRiskCalculator.applyRisk(sumInsuredFireIncreased);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void checkIsApplicableForFireRisk() {
        boolean actualResult = fireRiskCalculator.isApplicable(RiskType.FIRE);
        assertTrue(actualResult);
    }

    @Test
    public void checkIsApplicableNotForFireRisk() {
        boolean actualResult = fireRiskCalculator.isApplicable(RiskType.THEFT);
        assertFalse(actualResult);
    }
}