package com.proofit.calculator.businesslogic;

import static com.proofit.calculator.domain.RiskType.FIRE;
import static java.math.BigDecimal.valueOf;
import java.math.BigDecimal;
import org.springframework.stereotype.Component;
import com.proofit.calculator.domain.RiskType;

@Component
public class FireRiskCalculator implements RiskCalculator {

    private final BigDecimal coefficientFire = valueOf(0.014);
    private final BigDecimal coefficientFireIncreased = valueOf(0.024);
    private final BigDecimal sumInsuredFireIncreased = valueOf(100);


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

        if (sumInsured.compareTo(sumInsuredFireIncreased) > 0) {
            return sumInsured.multiply(coefficientFireIncreased);
        }

        return sumInsured.multiply(coefficientFire);
    }

}
