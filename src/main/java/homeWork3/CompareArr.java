package homeWork3;

import java.util.Objects;

/*
2. Напишите обобщенный метод compareArrays(), который принимает два массива и возвращает true, если они одинаковые,
* и false в противном случае. Массивы могут быть любого типа данных, но должны иметь одинаковую длину
* и содержать элементы одного типа по парно.
 */
public class CompareArr {
    public static <T> boolean compareArrays(T[] arrayA, T[] arrayB){
        if (arrayA == null) {
            throw new NullPointerException("arrayA");
        }
        if (arrayB == null) {
            throw new NullPointerException("arrayB");
        }
        if (arrayA.length != arrayB.length) {
            return false;
        }
        for (int i = 0; i < arrayA.length; ++i) {
            if (!Objects.equals(arrayA[i], arrayB[i])) {
                return false;
            }
            // 2 вариант записи if
            // if(!arrayA[i].getClass().equals(arrayB[i].getClass())){
            // return false;
            // }
        }
        return true;
    }
}
