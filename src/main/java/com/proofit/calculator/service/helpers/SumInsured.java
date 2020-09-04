package com.proofit.calculator.service.helpers;

import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;

import com.proofit.calculator.domain.RiskType;

public class SumInsured {

    private final RiskType riskType;

    private BigDecimal sum = ZERO;

    public SumInsured(RiskType riskType) {
        this.riskType = riskType;
    }

    public RiskType getRiskType() {
        return riskType;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void add(BigDecimal sumInsured) {
        this.sum = sum.add(sumInsured);
    }

}
