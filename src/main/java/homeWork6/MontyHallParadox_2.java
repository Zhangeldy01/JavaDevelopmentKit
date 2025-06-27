package homeWork6;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Класс MontyHallParadox_2 моделирует и демонстрирует парадокс Монти Холла с улучшенной структурой кода.
 * Парадокс показывает, что смена выбора двери увеличивает вероятность выигрыша.
 * В этой версии логика выбора дверей вынесена в отдельные методы для улучшения читаемости и модульности.
 */
public class MontyHallParadox_2 {
    private static final int NUM_DOORS = 3; // Количество дверей, используемых в симуляции

    /**
     * Основной метод программы.
     * @param args Аргументы командной строки (не используются).
     */
    public static void main(String[] args) {
        int numTests = 1000; // Количество тестов, проводимых для оценки вероятности выигрыша/проигрыша

        // Создание Map для хранения результатов каждого теста. Ключ - номер теста, значение - "Win" или "Loss"
        Map<Integer, String> results = new HashMap<>();

        int wins = 0; // Счетчик побед
        int losses = 0; // Счетчик поражений

        Random random = new Random(); // Создание объекта Random для генерации случайных чисел

        for (int i = 1; i <= numTests; i++) { // Цикл, повторяющийся numTests раз, каждый раз моделируя игру
            int prizeDoor = choosePrizeDoor(random); // Случайный выбор двери, за которой находится приз (1, 2 или 3 двери)
            int chosenDoor = chooseInitialDoor(random); // Случайный выбор двери игроком (1, 2 или 3 двери)
            int openedDoor = openNonPrizeDoor(random, prizeDoor, chosenDoor); // Дверь, которую открывает ведущий
            int switchDoor = switchDoor(random, chosenDoor, openedDoor); // Дверь, на которую игрок меняет свой выбор

            if (switchDoor == prizeDoor) { // Если дверь, на которую сменили выбор, содержит приз
                wins++; // Увеличение счетчика побед
                results.put(i, "Win"); // Запись результата в карту как "Win"
            } else { // Если дверь, на которую сменили выбор, не содержит приз
                losses++; // Увеличение счетчика поражений
                results.put(i, "Loss"); // Запись результата в карту как "Loss"
            }
        }
        System.out.println("Number of wins: " + wins); // Вывод общего количества побед
        System.out.println("Number of losses: " + losses); // Вывод общего количества поражений

        double winRate = (double) wins / numTests; // Вычисление вероятности выигрыша
        System.out.println("The probability of winning when changing the door: " + winRate); // Вывод вероятности выигрыша при смене двери

        // Вывод результата для каждого теста
        for (Map.Entry<Integer, String> entry : results.entrySet()) { // Перебор всех записей в карте результатов
            System.out.println("Step: " + entry.getKey() + ": " + entry.getValue()); // Вывод номера шага и результат (Win или Loss)
        }
    }

    /**
     * Метод для случайного выбора двери, за которой находится приз.
     * @param random Объект Random для генерации случайных чисел.
     * @return Номер двери с призом (1, 2 или 3).
     */
    private static int choosePrizeDoor(Random random) {
        return random.nextInt(NUM_DOORS) + 1; // Генерация случайного числа от 0 до NUM_DOORS-1 и добавление 1 для получения номера двери от 1 до NUM_DOORS
    }

    /**
     * Метод для случайного выбора двери игроком.
     * @param random Объект Random для генерации случайных чисел.
     * @return Номер выбранной игроком двери (1, 2 или 3).
     */
    private static int chooseInitialDoor(Random random) {
        return random.nextInt(NUM_DOORS) + 1; // Генерация случайного числа от 0 до NUM_DOORS-1 и добавление 1 для получения номера двери от 1 до NUM_DOORS
    }

    /**
     * Метод для выбора двери, которую открывает ведущий. Ведущий не открывает дверь с призом или дверь, выбранную игроком.
     * @param random      Объект Random для генерации случайных чисел.
     * @param prizeDoor   Номер двери, за которой находится приз.
     * @param chosenDoor  Номер двери, выбранной игроком.
     * @return Номер двери, которую открывает ведущий (1, 2 или 3).
     */
    private static int openNonPrizeDoor(Random random, int prizeDoor, int chosenDoor) {
        int openedDoor; // Номер двери, которую откроет ведущий
        do {
            openedDoor = random.nextInt(NUM_DOORS) + 1; // Генерация случайного числа от 0 до NUM_DOORS-1 и добавление 1 для получения номера двери от 1 до NUM_DOORS
        } while (openedDoor == prizeDoor || openedDoor == chosenDoor); // Повторяем, пока не будет выбрана дверь, отличная от двери с призом и двери, выбранной игроком
        return openedDoor; // Возвращаем номер двери, которую откроет ведущий
    }

    /**
     * Метод для выбора двери, на которую игрок меняет свой выбор. Игрок не может сменить выбор на свою первоначальную дверь или открытую ведущим.
     * @param random      Объект Random для генерации случайных чисел.
     * @param chosenDoor  Номер двери, выбранной игроком изначально.
     * @param openedDoor  Номер двери, открытой ведущим.
     * @return Номер двери, на которую игрок меняет свой выбор (1, 2 или 3).
     */
    private static int switchDoor(Random random, int chosenDoor, int openedDoor) {
        int switchDoor; // Номер двери, на которую игрок меняет свой выбор
        do {
            switchDoor = random.nextInt(NUM_DOORS) + 1; // Генерация случайного числа от 0 до NUM_DOORS-1 и добавление 1 для получения номера двери от 1 до NUM_DOORS
        } while (switchDoor == chosenDoor || switchDoor == openedDoor); // Повторяем, пока не будет выбрана дверь, отличная от первоначального выбора игрока и двери, открытой ведущим

        return switchDoor; // Возвращаем номер двери, на которую игрок меняет свой выбор
    }
}