package com.proofit.calculator.domain;

import java.util.Objects;

public class Policy {

    private String number;
    private String status;

    public Policy(String number, String status) {
        this.number = number;
        this.status= status;
    }


    public String getNumber() {
        return number;
    }

    public String getStatus() {
        return status;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "1 Policy number - " + number + "\n"
                + "2 Policy status - " + status + "\n"
                + "----------------------------- \n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Policy policy = (Policy) o;
        return Objects.equals(number, policy.number) &&
                Objects.equals(status, policy.status)
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number,status);
    }

}
