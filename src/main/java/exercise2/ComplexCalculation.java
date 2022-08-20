package exercise2;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class ComplexCalculation {
    public BigInteger calculateResult(BigInteger base1, BigInteger power1, BigInteger base2, BigInteger power2) throws InterruptedException {
        BigInteger result = BigInteger.ZERO;

        List<PowerCalculatingThread> threadList = new ArrayList<>();
        threadList.add(new PowerCalculatingThread(base1,power1));
        threadList.add(new PowerCalculatingThread(base2,power2));

        /*
            Calculate result = ( base1 ^ power1 ) + (base2 ^ power2).
            Where each calculation in (..) is calculated on a different thread
        */
        for (Thread thread : threadList){
            thread.setDaemon(true);
            thread.start();
        }

        for (Thread thread : threadList){
            thread.join(2000);
        }
        result = threadList.get(0).getResult().add(threadList.get(1).getResult());
        return result;
    }

    private static class PowerCalculatingThread extends Thread {
        private BigInteger result = BigInteger.ONE;
        private BigInteger base;
        private BigInteger power;

        public PowerCalculatingThread(BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
        }

        @Override
        public void run() {
           /*
           Implement the calculation of result = base ^ power
           */
            this.result = getPower(base,power);
        }

        BigInteger getPower(BigInteger base, BigInteger exponent) {
            BigInteger result = BigInteger.ONE;
            for (BigInteger i = BigInteger.ZERO; i.compareTo(exponent) != 0; i = i.add(BigInteger.ONE)) {
                result = result.multiply(base);
            }
            return result;
        }

        public BigInteger getResult() { return result; }
    }
}