package com.proofit.calculator.businesslogic;

import com.proofit.calculator.domain.RiskType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

public class RiskCalculatorTest {

    private final BigDecimal sumInsuredForTest = BigDecimal.valueOf(500.0);
    private FireRiskCalculator fireRiskCalculator;

    @BeforeEach
    void setUp() {
        fireRiskCalculator = new FireRiskCalculator();
    }

    @Test
    public void checkApplyRiskCalculationIfApplicable() {
        BigDecimal expectedResult = new BigDecimal("12.0000");
        BigDecimal actualResult = fireRiskCalculator.applyRiskCalculationIfApplicable(RiskType.FIRE, sumInsuredForTest);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void checkApplyRiskCalculationIfNotApplicable() {
        BigDecimal expectedResult = new BigDecimal("500.0");
        BigDecimal actualResult = fireRiskCalculator.applyRiskCalculationIfApplicable(RiskType.THEFT, sumInsuredForTest);

        assertEquals(expectedResult, actualResult);
    }


}