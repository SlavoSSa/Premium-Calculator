package com.proofit.calculator.service.helpers;

import static com.proofit.calculator.domain.RiskType.THEFT;
import static java.math.BigDecimal.valueOf;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.proofit.calculator.domain.RiskType;

@Component
public class TheftRiskCalculator implements RiskCalculator {

    private final BigDecimal coefficientTheft = valueOf(0.11);

    @Override
    public RiskType applicableFor() {
        return THEFT;
    }

    @Override
    public boolean isApplicable(RiskType riskType) {
        return applicableFor().equals(riskType);
    }

    @Override
    public BigDecimal applyRisk(BigDecimal sumInsured) {
        BigDecimal calculatedCoefficient = coefficientTheft;

        if (sumInsured.compareTo(valueOf(15)) == 0 || sumInsured.compareTo(valueOf(15)) > 0) {
            calculatedCoefficient = valueOf(0.05);
        }

        return sumInsured.multiply(calculatedCoefficient);
    }

}
