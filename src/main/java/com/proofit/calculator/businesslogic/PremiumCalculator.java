package com.proofit.calculator.businesslogic;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.proofit.calculator.domain.Policy;
import com.proofit.calculator.domain.PolicyObject;
import com.proofit.calculator.domain.RiskType;

@Component
public class PremiumCalculator {

    private final List<RiskCalculator> riskCalculators;

    public PremiumCalculator(List<RiskCalculator> riskCalculators) {
        this.riskCalculators = riskCalculators;
    }

    public BigDecimal calculatePremium(Policy policy) {

        Map<RiskType, SumInsured> sumInsured = SumInsuredInitializer.generateSumInsuredMap(riskCalculators);

        getSumInsuredForPolicy(policy, sumInsured);

        return riskCalculators
                .stream()
                .map(riskCalculator -> (calculateSumInsuredForRiskType(riskCalculator, sumInsured)))
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.HALF_EVEN);
    }


    public void getSumInsuredForPolicy(Policy policy, Map<RiskType, SumInsured> sumInsured) {
        policy.getPolicyObjects()
                .stream()
                .map(PolicyObject::getPolicySubObjects)
                .flatMap(Collection::stream)
                .forEach(policySubObject -> sumInsured.get(policySubObject.getRiskType()).add(policySubObject.getInsuredSum()));
    }

    public BigDecimal calculateSumInsuredForRiskType(RiskCalculator riskCalculator, Map<RiskType, SumInsured> sumInsured) {
        SumInsured sum = sumInsured.get(riskCalculator.applicableFor());
        return riskCalculator.applyRiskCalculationIfApplicable(sum.getRiskType(), sum.getSum());
    }

}
