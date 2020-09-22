package com.proofit.calculator.businesslogic;

import com.proofit.calculator.domain.RiskType;
import java.math.BigDecimal;

public interface RiskCalculator {

    default BigDecimal applyRiskCalculationIfApplicable(RiskType riskType, BigDecimal sumInsured) {
        if (isApplicable(riskType)) {
            return applyRisk(sumInsured);
        }
        return sumInsured;
    }

    RiskType applicableFor();

    boolean isApplicable(RiskType riskType);

    BigDecimal applyRisk(BigDecimal sumInsured);
}
