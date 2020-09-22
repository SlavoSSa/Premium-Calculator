package com.proofit.calculator.domain;

import javax.validation.constraints.*;
import java.math.BigDecimal;


public class PolicySubObject {

    @NotBlank(message = "Policy sub object name is mandatory")
    private String name;

    @DecimalMin(value = "0.00", inclusive = false, message = "Insured sum must be positive and greater then 0")
    private BigDecimal insuredSum;

    @NotNull(message = "Risk type is mandatory")
    private RiskType riskType;


    public PolicySubObject() {
    }

    public PolicySubObject(String name, BigDecimal insuredSum, RiskType riskType) {
        this.name = name;
        this.insuredSum = insuredSum;
        this.riskType = riskType;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getInsuredSum() {
        return insuredSum;
    }

    public void setInsuredSum(BigDecimal insuredSum) {
        this.insuredSum = insuredSum;
    }

    public RiskType getRiskType() {
        return riskType;
    }

    public void setRiskType(RiskType riskType) {
        this.riskType = riskType;
    }


}
