package seminar5.task3;

import java.util.concurrent.CountDownLatch;

public class Runner extends Thread{
    private String name;
    private CountDownLatch cdl;

    public Runner(String name, CountDownLatch cdl) {
        this.name = name;
        this.cdl = cdl;
    }

    @Override
    public void run() {
        try {
            movementOnStartLine();
            cdl.await();
            movementOnFinishLine();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void movementOnStartLine() throws InterruptedException{
        System.out.println(name + " идет к линии старта");
        sleep((long) (Math.random() * 3000));
        System.out.println(name + " пришел к линии старта и готов бежать");
        cdl.countDown();
    }

    private void movementOnFinishLine() throws InterruptedException{
        System.out.println(name + " побежал");
        sleep((long) (Math.random() * 3000));
        System.out.println(name + " финишировал");
    }
}
