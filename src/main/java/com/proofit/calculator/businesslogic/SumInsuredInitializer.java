package com.proofit.calculator.businesslogic;

import static java.util.stream.Collectors.toMap;
import java.util.List;
import java.util.Map;
import com.proofit.calculator.domain.RiskType;

public final class SumInsuredInitializer {

    public static Map<RiskType, SumInsured> generateSumInsuredMap(List<RiskCalculator> riskCalculators) {
        return riskCalculators.stream()
                .map(SumInsuredInitializer::generateSumInsuredForRiskType)
                .collect(toMap(SumInsured::getRiskType, sumInsured -> sumInsured));
    }

    public static SumInsured generateSumInsuredForRiskType(RiskCalculator riskCalculator){
        return new SumInsured(riskCalculator.applicableFor());
    }

}
