package com.proofit.calculator.service.helpers;

import static java.util.stream.Collectors.toMap;

import java.util.List;
import java.util.Map;

import com.proofit.calculator.domain.RiskType;

public final class SumInsuredInitializer {

    public static Map<RiskType, SumInsured> generateSumInsuredMap(List<RiskCalculator> riskCalculators) {
        return riskCalculators.stream()
                .map(riskCalculator -> new SumInsured(riskCalculator.applicableFor()))
                .collect(toMap(SumInsured::getRiskType, sumInsured -> sumInsured));
    }

}
