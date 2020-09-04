package com.proofit.calculator.domain;

import java.util.List;
import java.util.Objects;

public class Policy {

    private String number;
    private PolicyStatus status;
    private List<PolicyObject> policyObjects;

    public Policy() { }

    public Policy(String number, PolicyStatus status, List<PolicyObject> policyObjects) {
        this.number = number;
        this.status = status;
        this.policyObjects = policyObjects;
    }


    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public PolicyStatus getStatus() {
        return status;
    }

    public void setStatus(PolicyStatus status) {
        this.status = status;
    }

    public List<PolicyObject> getPolicyObjects() {
        return policyObjects;
    }

    public void setPolicyObjects(List<PolicyObject> policyObjects) {
        this.policyObjects = policyObjects;
    }

    @Override
    public String toString() {
        return "1 Policy number - " + number + "\n"
                + "2 Policy status - " + status + "\n"
                + "3 Policy objects - " + policyObjects + "\n"
                + "----------------------------- \n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Policy policy = (Policy) o;
        return Objects.equals(number, policy.number) &&
                Objects.equals(status, policy.status) &&
                Objects.equals(policyObjects, policy.policyObjects)
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, status, policyObjects);
    }

}
