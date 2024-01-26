package seminar5.task2;

public class ThreadB extends Thread {
    private  int sec = 100;
    private Boolean switcher;

    public ThreadB(Boolean switcher) {
        this.switcher = switcher;
    }

    @Override
    public void run() {
        while(sec > 0) {
            if (Main.switcher) {
                sec--;
                System.out.println(sec);
            }
            try {
                sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
