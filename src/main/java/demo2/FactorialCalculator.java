package demo2;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FactorialCalculator {

    public static void main(String[] args) throws InterruptedException {
        List<FactorialThread> threads = new ArrayList<>();
        List<Long> numbers = Arrays.asList(123L,456L,7890L,1235645335L);

        for (long number : numbers){
            threads.add(new FactorialThread(number));
        }
        for (Thread thread : threads){
            thread.setDaemon(true); //this will help to stop the application  in case of large inputs
            thread.start();
        }

        for (Thread thread : threads){
            thread.join(2000); // 2 seconds for thread to complete or else terminate
                                    // also, thread.join() will let the application wait for the thread to terminate before doing get
        }


        for (int i = 0 ; i < numbers.size() ; i++){
            FactorialThread thread = threads.get(i);

            if (thread.isFinished) {
                System.out.println("Factorial of " + numbers.get(i) + " is " + threads.get(i).result);
            } else {
                System.out.println("Factorial of " + numbers.get(i) + " is still in progress" );
            }
        }
    }

    public static class FactorialThread extends Thread{
        private long inputNumber;
        private BigInteger result = BigInteger.ZERO;
        private boolean isFinished = false;

        public FactorialThread(long input){
            inputNumber = input;
        }

        @Override
        public void run(){
            this.result = factorial(this.inputNumber);
            this.isFinished = true;
        }

        public BigInteger factorial(long number){
            BigInteger tempResult = BigInteger.ONE;

            for(long i = number ; i>0 ; i--){
                tempResult = tempResult.multiply(new BigInteger(Long.toString(i)));
            }

            return tempResult;
        }
    }
}
