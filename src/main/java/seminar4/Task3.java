package seminar4;

import java.util.HashMap;
import java.util.Map;

/*
*  В рамках выполнения задачи необходимо:
*   1. Создайте телефонный справочник с помощью Map - телефон это ключ, а имя значение.
*   2. Найдите человека с самым маленьким номером телефона.
*   3. Найдите номер телефона человека чье имя самое большое в алфавитном порядке.
 */
public class Task3 {
    public static void main(String[] args) {
        Map<String, String> phoneBook = new HashMap<>();
        phoneBook.put("865", "Omar");
        phoneBook.put("438", "Sandy");
        phoneBook.put("901", "Miranda");
        phoneBook.put("445", "Bill");

        System.out.println(phoneBook.entrySet().stream().min((e1, e2) -> Integer.parseInt(e1.getKey()) - Integer.parseInt(e2.getKey())).get()); // 2 задание
        System.out.println(phoneBook.entrySet().stream().max((e1, e2) -> e1.getValue().compareTo(e2.getValue())).get()); // 2 задание

    }
}
