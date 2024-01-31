package homeWork6;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/*
В качестве задачи предлагаю вам реализовать код для демонстрации парадокса Монти Холла (Парадокс Монти Холла — Википедия)
и наглядно убедиться в верности парадокса (запустить игру в цикле на 1000 и вывести итоговый счет).
Необходимо:
Создать свой Java Maven или Gradle проект;
Самостоятельно реализовать прикладную задачу;
Сохранить результат в HashMap<шаг теста, результат>
Вывести на экран статистику по победам и поражениям
 */

/*
В этом коде мы используем класс `Random` для генерации случайных чисел.
Мы проводим 1000 тестов, в каждом из которых выбираем случайную дверь с призом и случайную дверь игроком.
Затем ведущий открывает одну из оставшихся дверей, не содержащую приз, и игрок может решить остаться на своем выборе
или переключиться на другую дверь. Если игрок выбирает дверь с призом, мы увеличиваем счетчик побед,
в противном случае - счетчик поражений.
Затем мы выводим общую статистику побед и поражений, а также результаты для каждого шага теста.
*/

public class MontyHallParadox {
    public static void main(String[] args) {
        int numTests = 1000; // Количество тестов

        Map<Integer, String> results = new HashMap<>(); // Хранение результатов

        int wins = 0; // Количество побед
        int losses = 0; // Количество поражений

        Random random = new Random();

        for (int i = 1; i <= numTests; i++) {
            int prizeDoor = random.nextInt(3) + 1; // Выбор случайной двери с призом
            int chosenDoor = random.nextInt(3) + 1; // Выбор случайной двери игроком

            int openedDoor; // Дверь, которую открывает ведущий

            do {
                openedDoor = random.nextInt(3) + 1;
            } while (openedDoor == prizeDoor || openedDoor == chosenDoor);

            int switchDoor; // Дверь, на которую игрок переключается

            do {
                switchDoor = random.nextInt(3) + 1;
            } while (switchDoor == chosenDoor || switchDoor == openedDoor);

            if (switchDoor == prizeDoor) {
                wins++;
                results.put(i, "Выигрыш");
            } else {
                losses++;
                results.put(i, "Проигрыш");
            }
        }

        System.out.println("Количество побед: " + wins);
        System.out.println("Количество поражений: " + losses);

        // Вывод результатов для каждого шага теста
        for (Map.Entry<Integer, String> entry : results.entrySet()) {
            System.out.println("Step " + entry.getKey() + ": " + entry.getValue());
        }
    }
}



