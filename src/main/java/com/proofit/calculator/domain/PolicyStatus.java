package com.proofit.calculator.domain;

import java.util.HashMap;
import java.util.Map;

public enum PolicyStatus {

    REGISTERED(0),
    APPROVED(1),
    PENDING(2);

    private static Map map = new HashMap<>();

    static {
        for (PolicyStatus status : PolicyStatus.values()) {
            map.put(status.statusIntVal, status);
        }
    }

    private int statusIntVal;

    PolicyStatus(int statusIntVal) {
        this.statusIntVal = statusIntVal;
    }

    public static PolicyStatus valueOf(int status) {
        return (PolicyStatus) map.get(status);
    }

    public int getStatusIntVal() {
        return statusIntVal;
    }

}
