package seminar5.task22;

/* task2 Вариант от преподавателя
 * В рамках выполнения задачи необходимо:
 * Создайте два потока А и В.
 * Поток А меняет значение Boolean переменной switcher с задержкой 1000 миллисекунд
 * (true  в сосотоянии false и наоборот)
 * Поток В ожидает сосотояния true  переменной switcher и выводит на консоль обратный отсчет от 100
 * с задержкой 100 миллисекунд и приостанавливает свое действие, как только поток А переключит switcher
 * в состояние false
 * Условием завершения работы потоков является достижение отсчета нулевой отметки.
 */

public class Main {
    public static void main(String[] args) {

        MyProgramState state = new MyProgramState();

        MyThread1 thread1 = new MyThread1(state);
        MyThread2 thread2 = new MyThread2(state);

        thread1.start();
        thread2.start();
    }
}
