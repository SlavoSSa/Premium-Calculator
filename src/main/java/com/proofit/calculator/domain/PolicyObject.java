package com.proofit.calculator.domain;

import java.util.List;

public class PolicyObject {

    private String name;
    private List<PolicySubObject> policySubObject;

    public PolicyObject() { }

    public PolicyObject(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PolicySubObject> getPolicySubObject() {
        return policySubObject;
    }

    public void setPolicySubObject(List<PolicySubObject> policySubObject) {
        this.policySubObject = policySubObject;
    }


}
