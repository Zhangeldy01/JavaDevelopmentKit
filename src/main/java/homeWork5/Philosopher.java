package homeWork5;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
* 1. Пять безмолвных философов сидят вокруг круглого стола, перед каждым философом стоит тарелка спагетти.
* 2. Вилки лежат на столе между каждой парой ближайших философов.
* 3. Каждый философ может либо есть, либо размышлять.
* 4. Философ может есть только тогда, когда держит две вилки — взятую справа и слева.
* 5. Философ не может есть два раза подряд, не прервавшись на размышления (можно не учитывать)
* Описать в виде кода такую ситуацию. Каждый философ должен поесть три раза.
 */

public class Philosopher implements Runnable{
    private final int id;
    private final Lock leftFork;
    private final Lock rightFork;
    private int eatCount;

    public Philosopher(int id, Lock leftFork, Lock rightFork) {
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.eatCount = 0;
    }

    private void think() throws InterruptedException {
        System.out.println("Философ " + id + " думает");
        Thread.sleep(1000);
    }

    private void eat() throws InterruptedException {
        leftFork.lock();
        rightFork.lock();

        System.out.println("Философ " + id + " ест");
        eatCount++;
        Thread.sleep(1000);

        rightFork.unlock();
        leftFork.unlock();
    }

    @Override
    public void run() {
        try {
            while (eatCount < 3) {
                think();
                eat();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }
    }

    public static void main(String[] args) {
        int numPhilosophers = 5;
        Philosopher[] philosophers = new Philosopher[numPhilosophers];
        Lock[] forks = new ReentrantLock[numPhilosophers];

        for (int i = 0; i < numPhilosophers; i++) {
            forks[i] = new ReentrantLock();
        }

        for (int i = 0; i < numPhilosophers; i++) {
            Lock leftFork = forks[i];
            Lock rightFork = forks[(i + 1) % numPhilosophers];
            philosophers[i] = new Philosopher(i, leftFork, rightFork);
            Thread thread = new Thread(philosophers[i]);
            thread.start();
        }
    }
}
