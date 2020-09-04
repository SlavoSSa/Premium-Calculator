package com.proofit.calculator.service.helpers;

import com.proofit.calculator.domain.RiskType;

import java.math.BigDecimal;

public interface RiskCalculator {

    default BigDecimal applyIfApplicable(RiskType riskType, BigDecimal sumInsured) {
        if (isApplicable(riskType)) {
            return applyRisk(sumInsured);
        }
        return sumInsured;
    }

    RiskType applicableFor();

    boolean isApplicable(RiskType riskType);

    BigDecimal applyRisk(BigDecimal sumInsured);
}
