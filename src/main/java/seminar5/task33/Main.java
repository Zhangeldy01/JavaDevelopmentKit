package seminar5.task33;

public class Main {
    /* task3
    2-й вариант решения
    В рамках выполнения задачи необходимо:
    3 бегуна должны прийти к старту гонки
    Программа должна гарантировать, что гонка начнется только когда все три участника будут на старте
    Программа должна отсчитать “На старт”, “Внимание”, “Марш”
    Программа должна завершиться когда все участники закончат гонку.
    Подумайте об использовании примитива синхронизации
     */
    public static void main(String[] args) {
        Race race = new Race();
        race.start();
    }
}
