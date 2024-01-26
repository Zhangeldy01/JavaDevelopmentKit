package seminar5.task2;

/* task2
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
    volatile static Boolean switcher = true;
    public static void main(String[] args) {

        ThreadB threadB = new ThreadB(switcher);
        ThreadA threadA = new ThreadA(switcher);

        threadB.start();
        threadA.start();
    }
}
