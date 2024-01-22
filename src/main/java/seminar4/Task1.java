package seminar4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
* В рамках выполнения задачи необходимо:
*  1. Создайте коллекцию мужских и женских имен с помощью интерфейса List;
*  2. Отсортируйте коллекцию в алфавитном порядке;
*  3. Отсортируйте коллекцию по количеству букв в слове;
*  4. Разверните коллекцию.
 */
public class Task1 {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("Tom");
        names.add("Mary");
        names.add("Kate");
        names.add("Max");

        // 2 задание
        Collections.sort(names);
        System.out.println(names);

        // 3 задание
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
//                if(o1.length() > o2.length()) {
//                    return 1;
//                } else if(o1.length() < o2.length()){
//                    return -1;
//                } else {
//                    return 0;
//                }
                // 2 вариант
                return o1.length() - o2.length();
            }
        });

        System.out.println(names);

        // 4 задание
        Collections.reverse(names);
        System.out.println(names);
    }
}
