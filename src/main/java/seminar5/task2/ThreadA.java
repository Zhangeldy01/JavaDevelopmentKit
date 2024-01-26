package seminar5.task2;

public class ThreadA extends Thread {
    private Boolean switcher;

    public ThreadA(Boolean switcher) {
        this.switcher = switcher;
    }

    @Override
    public void run() {
        while(true) {
            Main.switcher = !Main.switcher;
            switcher = !switcher;
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
