package com.proofit.calculator.domain;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

public class PolicyObject {

    @NotBlank(message = "Policy object name is mandatory")
    private String name;

    @Valid
    private List<PolicySubObject> policySubObjects;

    public PolicyObject() {
    }


    public PolicyObject(String name, List<PolicySubObject> policySubObjects) {
        this.name = name;
        this.policySubObjects = policySubObjects;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<PolicySubObject> getPolicySubObjects() {
        return policySubObjects;
    }


    public void setPolicySubObject(List<PolicySubObject> policySubObjects) {
        this.policySubObjects = policySubObjects;
    }




}
