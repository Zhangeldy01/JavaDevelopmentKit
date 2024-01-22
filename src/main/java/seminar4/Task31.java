package seminar4;

import java.util.Map;
import java.util.HashMap;
/*
 *  2 вариант решения задачи
 *  В рамках выполнения задачи необходимо:
 *   1. Создайте телефонный справочник с помощью Map - телефон это ключ, а имя значение.
 *   2. Найдите человека с самым маленьким номером телефона.
 *   3. Найдите номер телефона человека чье имя самое большое в алфавитном порядке.
 */
public class Task31 {
    public static void main(String[] args) {
        Map<String, String> phoneBook = new HashMap<>();
        phoneBook.put("123", "Владимир");
        phoneBook.put("123123", "Наталья");
        phoneBook.put("12311", "Анатолий");
        phoneBook.put("12", "Маргарита");
        phoneBook.put("911", "Владлен");

        for (Map.Entry<String, String> entry: phoneBook.entrySet()){
            String key = entry.getKey();
            String val = entry.getValue();
        }

        System.out.println(phoneBook.entrySet()
                .stream().min((e1, e2) -> e1.getKey().compareTo(e2.getKey())).get().getValue());

        System.out.println(phoneBook.entrySet()
                .stream().max((e1, e2) -> e1.getValue().compareTo(e2.getValue())).get().getKey());
    }
}
