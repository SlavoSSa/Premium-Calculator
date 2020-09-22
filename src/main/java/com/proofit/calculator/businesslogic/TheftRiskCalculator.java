package com.proofit.calculator.businesslogic;

import static com.proofit.calculator.domain.RiskType.THEFT;
import static java.math.BigDecimal.valueOf;
import java.math.BigDecimal;
import org.springframework.stereotype.Component;
import com.proofit.calculator.domain.RiskType;

@Component
public class TheftRiskCalculator implements RiskCalculator {

    private final BigDecimal coefficientTheft = valueOf(0.11);
    private final BigDecimal coefficientTheftDecreased = valueOf(0.05);
    private final BigDecimal sumInsuredTheftConfigured = valueOf(15);

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

        if(isEqualOrGreaterThanConfigured(sumInsured)){
            return sumInsured.multiply(coefficientTheftDecreased);
        }

        return sumInsured.multiply(coefficientTheft);
    }


     boolean isEqualOrGreaterThanConfigured(BigDecimal sumInsured){
        return sumInsured.compareTo(sumInsuredTheftConfigured) == 0 || sumInsured.compareTo(sumInsuredTheftConfigured) > 0;
    }

}
