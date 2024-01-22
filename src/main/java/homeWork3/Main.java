package homeWork3;
import homeWork3.pair.RecordPair;

import java.util.Arrays;
import java.util.Random;

/*
* 1. Написать класс Калькулятор (необобщенный), который содержит обобщенные статические методы: sum(), multiply(),
* divide(), subtract(). Параметры этих методов – два числа разного типа, над которыми должна быть произведена операция.
*
* 2. Напишите обобщенный метод compareArrays(), который принимает два массива и возвращает true, если они одинаковые,
* и false в противном случае. Массивы могут быть любого типа данных, но должны иметь одинаковую длину
* и содержать элементы одного типа по парно.
*
* 3. Напишите обобщенный класс Pair, который представляет собой пару значений разного типа. Класс должен иметь
*  методы getFirst(), getSecond() для получения значений каждого из составляющих пары, а также переопределение
* метода toString(), возвращающее строковое представление пары.
 */
public class Main {
    public static void main(String[] args) {
        Task1.run();
        Task2.run();
        Task3.run();
    }
}
class Task1{
    static void run(){
        ConsoleUtils.printlnEmphasized("\nПример работы обобщённых статических методов Калькулятора:");
        Number result;
        result = Calculator.sum(Integer.valueOf(15), Float.valueOf(29.159f));
        System.out.printf("Integer 15 + Float 29.159f = %s as Float\n", result.floatValue());
        result = Calculator.multiply(Double.valueOf( 32.1), Short.valueOf((short) 568));
        System.out.printf("Double 32.1 * Short 568 = %s as Double\n", result.doubleValue());

    }
}

class Task2{
    private static final int VALUE_BOUND = 1000;
    static void run() {
        ConsoleUtils.printlnEmphasized("\nПример работы обобщённого метода compareArrays():");
        // Integer[] arrayA = { 1, 2, 3};
        // Integer[] arrayB = { 1, 2, 3};
        int length = 3; // длина массива
        Random random = new Random();

        int[] arrayA = new int[length];
        for (int i = 0; i < length; i++) {
            // заполняем каждый элемент случайным числом от 0 до 2
            arrayA[i] = random.nextInt(3);
        }

        int[] arrayB = new int[length];
        for (int j = 0; j < length; j++) {
            // заполняем каждый элемент случайным числом от 0 до 2
            arrayB[j] = random.nextInt(3);
        }

            System.out.println("Исходные массивы:");
            System.out.println("arrayA: " + Arrays.toString(arrayA));
            System.out.println("arrayB: " + Arrays.toString(arrayB));
            System.out.println(CompareArr.compareArrays(arrayA, arrayB)
                    ? "Массивы одинаковые."
                    : "Массивы различаются");
    }
}

class Task3{
    static void run() {
        ConsoleUtils.printlnEmphasized("\nПример работы обобщённого класса Pair:");
        RecordPair<String, Integer> pair = new RecordPair<>("Text", 10);
        System.out.println(pair);
    }

}
class ConsoleUtils {
    public static void printlnEmphasized(String text) {
        System.out.println(text);
    }
}
