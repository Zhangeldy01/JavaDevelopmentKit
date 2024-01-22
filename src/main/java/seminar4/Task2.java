package seminar4;

import java.rmi.server.ServerNotActiveException;
import java.util.*;

/*
* В рамках выполнения задачи необходимо:
* 1. Создайте коллекцию мужских и женских имен с помощью интерфейса List - добавьте повторяющиеся значения
* 2. Определите наименьший элемент (алфавитный порядок)
* 3. Определите наибольший элемент (по количеству букв в слове но в обратном порядке)
* 4. Удалите все элементы содержащие букву 'А'.
 */
public class Task2 {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>(); // 1 задание
        names.add("Tom");
        names.add("Mary");
        names.add("Kate");
        names.add("Max");
        names.add("Arman");
        names.add("Dana");
        names.add("Lidia");
        names.add("Mary");
        names.add("Max");
        names.add("Bill");
        Set<String> setNames = new HashSet<>(names);
        System.out.println(setNames);

        System.out.println(setNames.stream().max(Comparator.naturalOrder()).get()); // 2 задание
        //System.out.println(setNames.stream().min(Comparator.reverseOrder()).get()); //тоже самое выведет

        System.out.println(setNames.stream().max(Comparator.comparingInt(String::length)).get()); // 3 задание
        //System.out.println(setNames.stream().max((s1, s2) -> s1.length() - s2.length()).get()); //тоже самое выведет
        //System.out.println(setNames.stream().min(Comparator.naturalOrder()).get());  //тоже самое выведет

        System.out.println(setNames.stream().filter((s1) -> !(s1.contains("a") || s1.contains("A"))).toList()); // 4 задание это выборка, а не удаление
        setNames.removeIf((s1) -> s1.contains("a") || s1.contains("A")); // 4 задание удаление элементов
        System.out.println(setNames);
    }
}
