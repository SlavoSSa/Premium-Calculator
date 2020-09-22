package com.proofit.calculator.businesslogic;

import com.proofit.calculator.domain.RiskType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;


public class SumInsuredTest {

    private final BigDecimal sumToAdd = new BigDecimal("10.00");
    private SumInsured sumInsured;

    @BeforeEach
    void setUp() {
        sumInsured = new SumInsured(RiskType.FIRE);
    }

    @Test
    public void checkAddCalculationForInsuredSum() {
        BigDecimal expectedResult = new BigDecimal("10.00");

        sumInsured.add(sumToAdd);
        BigDecimal actualResult = sumInsured.getSum();

        assertEquals(expectedResult, actualResult);
    }

}