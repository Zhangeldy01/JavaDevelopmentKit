package seminar6.task1;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.math3.util.ArithmeticUtils;

/*
* В рамках выполнения задачи необходимо:
* Создать свой Java Maven проект;
* Добавьте зависимость commons-math3 (предназначена для решения математических задач) и изучить интерфейс библиотеки.
* Воспользоваться пакетом org.apache.commons.math3.stat и классом DescriptiveStatistics.
* Создать коллекцию из числовых элементов.
* С помощью объекта DescriptiveStatistics вычислить минимальное и максимальное значение, сумму и среднее арифметическое.
* Воспользоваться пакетом org.apache.commons.math3.util. С помощью класса ArithmeticUtils найти: факториал числа N.
* Наименьшее общее частное (кратное) двух чисел, наибольший общий делитель двух чисел.
* Проверить, что число N это степень двойки.
 */
public class Main {
    public static void main(String[] args) {
        DescriptiveStatistics statistics = new DescriptiveStatistics();
        statistics.addValue(4);
        statistics.addValue(7);
        statistics.addValue(3);
        statistics.addValue(9);
        System.out.println(statistics.getMin());
        System.out.println(statistics.getMax());
        System.out.println(statistics.getMean()); // среднее арифметическое

        int a = (int) ArithmeticUtils.factorial(5); // нахождение факториала числа N
        System.out.println(a);

        int b = ArithmeticUtils.lcm(12, 96); // Наименьшее общее кратное двух чисел
        System.out.println(b);

        int c = ArithmeticUtils.gcd(12, 96); // наибольший общий делитель двух чисел
        System.out.println(c);

        boolean d = ArithmeticUtils.isPowerOfTwo(1024); // Проверить, что число N это степень двойки (true or false)
        System.out.println(d);
    }
}
