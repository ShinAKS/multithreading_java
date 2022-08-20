package exercise1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MultiExecutorRunner {

    public static class MultiExecutor {

        // Add any necessary member variables here
        private List<Runnable> tasks;
        /*
         * @param tasks to executed concurrently
         */
        public MultiExecutor(List<Runnable> tasks) {
            // Complete your code here
            this.tasks = tasks;
        }

        /**
         * Starts and executes all the tasks concurrently
         */
        public void executeAll() {
            // complete your code here

            List<Thread> threads = new ArrayList<>(tasks.size());

            for (Runnable task : tasks){
                Thread thread = new Thread(task);
                threads.add(thread);
            }

            for (Thread thread : threads){
                thread.start();
            }
        }
    }

    private static class Task1 implements Runnable{
        @Override
        public void run() {
            try {
                Thread.sleep(10); //deliberate addition of delay to verify sanity
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Running task1");
        }
    }

    private static class Task2 implements Runnable{
        @Override
        public void run() {
            System.out.println("Running task2");
        }
    }

    public static void main(String[] args) {
        Task1 t1 = new Task1();

        Task2 t2 = new Task2();

        MultiExecutor mx = new MultiExecutor(Arrays.asList(t1,t2));

        mx.executeAll();
    }
}
