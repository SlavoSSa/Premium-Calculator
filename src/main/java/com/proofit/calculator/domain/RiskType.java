package com.proofit.calculator.domain;

import java.util.HashMap;
import java.util.Map;

public enum RiskType {

    FIRE(0),
    THEFT(1);

    private static Map map = new HashMap<>();

    static {
        for (RiskType riskType : RiskType.values()) {
            map.put(riskType.riskTypeIntVal, riskType);
        }
    }

    private int riskTypeIntVal;

    private RiskType(int riskTypeIntVal) {
        this.riskTypeIntVal = riskTypeIntVal;
    }

    public static RiskType valueOf(int riskType) {
        return (RiskType) map.get(riskType);
    }

    public int getRiskTypeIntVal() {
        return riskTypeIntVal;
    }
}
