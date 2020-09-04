package com.proofit.calculator.service.helpers;

import static com.proofit.calculator.domain.RiskType.FIRE;
import static java.math.BigDecimal.valueOf;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.proofit.calculator.domain.RiskType;

@Component
public class FireRiskCalculator implements RiskCalculator {

    private final BigDecimal coefficientFire = valueOf(0.014);

    @Override
    public RiskType applicableFor() {
        return FIRE;
    }

    @Override
    public boolean isApplicable(RiskType riskType) {
        return applicableFor().equals(riskType);
    }

    @Override
    public BigDecimal applyRisk(BigDecimal sumInsured) {
        BigDecimal calculatedCoefficient = coefficientFire;

        if (sumInsured.compareTo(BigDecimal.valueOf(100)) > 0) {
            calculatedCoefficient = BigDecimal.valueOf(0.024);
        }

        return sumInsured.multiply(calculatedCoefficient);
    }

}
