package seminar5.task3;
 import java.util.ArrayList;
 import java.util.List;
 import java.util.concurrent.CountDownLatch;

public class Race extends Thread{
    List<Runner> runners;
    CountDownLatch cdl;

    @Override
    public void run() {
        try {
            init();
            goOnStartLine();
            while(cdl.getCount() > 1) {

            }
            command();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void command() {
        try {
            System.out.println("На старт!");
            sleep(1000);
            System.out.println("Внимание!");
            sleep(1000);
            System.out.println("Марш!");
            cdl.countDown();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void goOnStartLine() {
        for(Runner runner : runners) {
            runner.start();
        }
    }
    private void init() {
        int countRunners = 3;
        cdl = new CountDownLatch(countRunners + 1);
        runners = new ArrayList<>();
        runners.add(new Runner("Fedor", cdl));
        runners.add(new Runner("Chack", cdl));
        runners.add(new Runner("Piter", cdl));
    }
}
