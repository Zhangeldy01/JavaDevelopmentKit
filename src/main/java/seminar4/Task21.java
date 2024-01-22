package seminar4;

import java.util.*;
import java.util.stream.Collectors;

/*  2 вариант решения задачи
 * В рамках выполнения задачи необходимо:
 * 1. Создайте коллекцию мужских и женских имен с помощью интерфейса List - добавьте повторяющиеся значения
 * 2. Определите наименьший элемент (алфавитный порядок)
 * 3. Определите наибольший элемент (по количеству букв в слове но в обратном порядке)
 * 4. Удалите все элементы содержащие букву 'А'.
 */
public class Task21 {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>() {{
            add("Tom");
            add("Mary");
            add("Kate");
            add("Max");
            add("Arman");
            add("Dana");
            add("Lidia");
            add("Mary");
            add("Max");
            add("Bill");
        }};

        Set<String> uniqNames = getUniq(names);
        System.out.println(uniqNames);

        System.out.println(getMin(uniqNames));
        System.out.println(getMax(uniqNames));
        System.out.println(uniqNames);
        System.out.println(deleteSomeLoop(uniqNames));
//        System.out.println(deleteSomeFilter(uniqNames));
//        deleteSome(uniqNames);
//        System.out.println(uniqNames);

    }

    public static Set<String> getUniq(List<String> list) {
        return new HashSet<>(list);
    }

    public static String getMin(Set<String> names) {
        return names.stream().max(Comparator.naturalOrder()).get();
    }

    public static String getMax(Set<String> names) {
        return names.stream().min(Comparator.comparing(String::length)).get();
    }

    public static void deleteSome(Set<String> names) {
        names.removeIf(x -> x.contains("Рђ") || x.contains("Р°"));
    }

    public static Set<String> deleteSomeFilter(Set<String> names) {
        return names.stream().filter(x -> !x.contains("Рђ") && !x.contains("Р°")).collect(Collectors.toSet());
    }

    public static Set<String> deleteSomeLoop(Set<String> names) {
        Set<String> result = new HashSet<>();
        for (String s : names) {
            if (!s.contains("Рђ") && !s.contains("Р°")) result.add(s);
        }
        return result;
    }
}
