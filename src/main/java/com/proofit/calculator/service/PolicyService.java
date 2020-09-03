package com.proofit.calculator.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proofit.calculator.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class PolicyService {


    public String getPolicyNumber(Policy policy){
        return  policy.getNumber();
    }

    public Policy testJsonPolicy(){
        //Policy policy = new Policy("123","OK");

        return  null;
    }

    public String helloMethod() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Policy policy = new Policy("123", PolicyStatus.PENDING);

        String jsonInString = mapper.writeValueAsString(policy);


        return  jsonInString;

    }

    public BigDecimal calculate(Policy policy){
       // BigDecimal premiumFire = policy.

        BigDecimal coefficientFire = BigDecimal.valueOf(0.014);
        BigDecimal coefficientTheft = BigDecimal.valueOf(0.11);

        BigDecimal sumInsuredFire = BigDecimal.valueOf(0);
        BigDecimal sumInsuredTheft = BigDecimal.valueOf(0);

        BigDecimal premiumFire = BigDecimal.valueOf(0);//sumInsuredFire * coefficientFire
        BigDecimal premiumTheft = BigDecimal.valueOf(0);//sumInsuredTheft * coefficientTheft
        BigDecimal premium = BigDecimal.valueOf(0); // premiumFire + premiumTheft

       // policy.getPolicyObjects()

        List<PolicyObject> policyObjects = policy.getPolicyObjects();

        for (PolicyObject policyObject : policyObjects) {

            List<PolicySubObject> policySubObjects = policyObject.getPolicySubObject();

            for (PolicySubObject policySubObject : policySubObjects) {

                if(policySubObject.getRiskType() == RiskType.FIRE){
                    sumInsuredFire =  sumInsuredFire.add(policySubObject.getInsuredSum());
                }

                if(policySubObject.getRiskType() == RiskType.THEFT){
                    sumInsuredTheft =  sumInsuredTheft.add(policySubObject.getInsuredSum());
                }
            }

        }

        if(sumInsuredFire.compareTo(BigDecimal.valueOf(100)) > 0){
            coefficientFire = BigDecimal.valueOf(0.024);
        }

        if(sumInsuredTheft.compareTo(BigDecimal.valueOf(15)) == 0 || sumInsuredTheft.compareTo(BigDecimal.valueOf(15)) > 0){
           coefficientTheft = BigDecimal.valueOf(0.05);
        }

        premiumFire = sumInsuredFire.multiply(coefficientFire);
        premiumTheft = sumInsuredTheft.multiply(coefficientTheft);


        premium = premiumFire.add(premiumTheft);

        return  premium;
    }

    public Policy getPol(Policy policy){
        return  policy;
    }


}
