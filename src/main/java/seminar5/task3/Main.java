package seminar5.task3;

/* task3
* В рамках выполнения задачи необходимо:
* 3 бегуна должны прийти к старту гонки
* Программа должна гарантировать, что гонка начнется только тогда когда все три участника будут на старте
* Программа должна отсчитать "На старт", "Внимание", "Марш"
* Программа должна завершиться когда все участники завершат гонку.
* Подумайте об использовании примитива синхронизации
 */

public class Main {
    public static void main(String[] args) {
        new Race().start();
    }
}
