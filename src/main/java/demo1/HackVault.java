package demo1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HackVault {

    public static final int MAX_GUESS = 9999;

    private static class Vault{

        private int password;

        public Vault(int password){
            this.password = password;
        }

        public boolean guessPassword(int guess){

            try {
                Thread.sleep(5);
            }
            catch (Exception e){
                System.out.println("Exception occured while guessPassword process : " + e);
            }
            return guess == this.password;
        }
    }

    private static abstract class HackerThread extends Thread{

        protected Vault vault;

        public HackerThread(Vault vault){
            this.vault = vault;
            this.setName(this.getClass().getName());
            this.setPriority(Thread.MAX_PRIORITY);
        }

        @Override
        public void start(){
            System.out.println("Starting thread : " + this.getName());
            super.start();
        }
    }

    private static class AscendingHackerThread extends HackerThread{

        public AscendingHackerThread(Vault vault) {
            super(vault);
        }

        @Override
        public void run(){
            for (int guess = 0 ; guess<MAX_GUESS ; guess++){
                System.out.println("Ascending thread guessed: " + guess);
                if (vault.guessPassword(guess)){
                    System.out.println(this.getName() +" guessed the correct password which was : " + guess);
                    System.exit(0);
                }
            }
        }
    }

    private static class DescendingHackerThread extends HackerThread{

        public DescendingHackerThread(Vault vault) {
            super(vault);
        }

        @Override
        public void run(){
            for (int guess = MAX_GUESS ; guess>=0 ; guess--){
                System.out.println("Descending thread guessed: " + guess);
                if (vault.guessPassword(guess)){
                    System.out.println(this.getName() +" guessed the correct password which was : " + guess);
                    System.exit(0);
                }
            }
        }
    }

    private static class PoliceThread extends Thread{

        private static final int COUNTDOWN = 10;

        public PoliceThread(){
            this.setName(this.getClass().getName());
        }
        @Override
        public void run(){
            for (int count = COUNTDOWN ; count>=0 ; count-- ){
                try{
                    Thread.sleep(10);
                    System.out.println("Police counting : " + count);
                }catch(Exception e){

                }
            }
            System.out.println("Game over for you hackers!");
            System.exit(0);
        }

        @Override
        public void start(){
            System.out.println("Starting thread : " + this.getName());
            super.start();
        }
    }

    public static void main(String[] args) {
        Random random = new Random();

        Vault vault = new Vault(random.nextInt(MAX_GUESS));

        List<Thread> threadList = new ArrayList<>();

        threadList.add(new AscendingHackerThread(vault));
        threadList.add(new DescendingHackerThread(vault));
        threadList.add(new PoliceThread());

        for (Thread thread : threadList){
            thread.start();
        }
    }
}
