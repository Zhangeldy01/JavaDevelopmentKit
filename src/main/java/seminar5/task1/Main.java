package seminar5.task1;

/* task1
 * В рамках выполнения задачи необходимо:
 * Создать два класса ObjectA, ObjectB
 * Реализовать класс в котором два потока при запуске провоцируют DeadLock для объектов ObjectA, objectB
 */
public class Main {
    public static void main(String[] args) {
        Object objectA = new Object();
        Object objectB = new Object();

        MyThread thread1 = new MyThread(objectA, objectB);
        MyThread thread2 = new MyThread(objectB, objectA);

        thread1.start();
        thread2.start();

        System.out.println("Конец программы");
    }
}

class MyThread extends Thread {
    private Object objectA, objectB;

    public MyThread(Object objectA, Object objectB) {
        this.objectA = objectA;
        this.objectB = objectB;
    }

    public void run() {
        synchronized (objectA) { // блокирование объектаА
            try {
                sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Попытка блокировки");
            synchronized (objectB) {
                System.out.println("Успешно!");
            }
        }
    }
}