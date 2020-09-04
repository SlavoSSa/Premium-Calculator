package com.proofit.calculator.service.helpers;

import com.proofit.calculator.domain.Policy;
import com.proofit.calculator.domain.PolicyObject;
import com.proofit.calculator.domain.PolicySubObject;
import com.proofit.calculator.domain.RiskType;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Component
public class PremiumCalculator {

    public BigDecimal calculatePremium(Policy policy) {

        BigDecimal coefficientFire = BigDecimal.valueOf(0.014);
        BigDecimal coefficientTheft = BigDecimal.valueOf(0.11);

        BigDecimal sumInsuredFire = BigDecimal.valueOf(0);
        BigDecimal sumInsuredTheft = BigDecimal.valueOf(0);

        BigDecimal premiumFire;
        BigDecimal premiumTheft;
        BigDecimal premium;


        List<PolicyObject> policyObjects = policy.getPolicyObjects();

        for (PolicyObject policyObject : policyObjects) {

            List<PolicySubObject> policySubObjects = policyObject.getPolicySubObjects();

            for (PolicySubObject policySubObject : policySubObjects) {

                if (policySubObject.getRiskType() == RiskType.FIRE) {
                    sumInsuredFire = sumInsuredFire.add(policySubObject.getInsuredSum());
                }

                if (policySubObject.getRiskType() == RiskType.THEFT) {
                    sumInsuredTheft = sumInsuredTheft.add(policySubObject.getInsuredSum());
                }
            }

        }

        premiumFire = calculatePremiumFire(coefficientFire, sumInsuredFire);
        premiumTheft = calculatePremiumTheft(coefficientTheft, sumInsuredTheft);

        premium = premiumFire.add(premiumTheft);

        return premium.setScale(2, RoundingMode.CEILING);

    }

    public BigDecimal calculatePremiumTheft(BigDecimal coefficientTheft, BigDecimal sumInsuredTheft) {

        if (sumInsuredTheft.compareTo(BigDecimal.valueOf(15)) == 0 || sumInsuredTheft.compareTo(BigDecimal.valueOf(15)) > 0) {
            coefficientTheft = BigDecimal.valueOf(0.05);
        }

        return sumInsuredTheft.multiply(coefficientTheft);
    }

    public BigDecimal calculatePremiumFire(BigDecimal coefficientFire, BigDecimal sumInsuredFire) {

        if (sumInsuredFire.compareTo(BigDecimal.valueOf(100)) > 0) {
            coefficientFire = BigDecimal.valueOf(0.024);
        }

        return sumInsuredFire.multiply(coefficientFire);
    }


}
