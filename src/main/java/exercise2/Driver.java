package exercise2;

import java.math.BigInteger;


public class Driver {

    public static void main(String[] args) {
        ComplexCalculation calculation = new ComplexCalculation();
        BigInteger base1 = BigInteger.valueOf(1234342L), base2 = BigInteger.valueOf(123L);
        BigInteger pow1 = BigInteger.valueOf(434244L) , pow2 = BigInteger.valueOf(32L);
        try {
            BigInteger result = calculation.calculateResult(base1,pow1,base2,pow2);
            System.out.println("The result is : " + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
