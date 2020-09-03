package com.proofit.calculator.service;

import com.proofit.calculator.domain.Policy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PolicyService {


    public String getPolicyNumber(Policy policy){
        return  policy.getNumber();
    }

    public String helloMethod(){
        return  "Hello";
    }


}
