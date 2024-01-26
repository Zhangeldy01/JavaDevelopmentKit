package seminar5.task11;

/* task1 Вариант от преподавателя
 * В рамках выполнения задачи необходимо:
 * Создать два класса ObjectA, ObjectB
 * Реализовать класс в котором два потока при запуске провоцируют DeadLock для объектов ObjectA, objectB
 */

public class Main {
    public static void main(String[] args) {
        Friend friend1 = new Friend("Bob");
        Friend friend2 = new Friend("Dan");

        MyThread thread1 = new MyThread(friend1, friend2);
        MyThread thread2 = new MyThread(friend2, friend1);

        thread1.start();
        thread2.start();

        System.out.println("end");

    }
}
