package homeWork3.pair;
/*
 * 3. Напишите обобщенный класс Pair, который представляет собой пару значений разного типа. Класс должен иметь
 *  методы getFirst(), getSecond() для получения значений каждого из составляющих пары, а также переопределение
 * метода toString(), возвращающее строковое представление пары.
*/
public class Pair<T, V> {

     private T firstValue;
     private V secondValue;

     public Pair(T firstValue, V secondValue) {
         this.firstValue = firstValue;
         this.secondValue = secondValue;
     }

     public T getFirst(){
         return firstValue;
     };
    public V getSecond() {
        return secondValue;
    }

    @Override
    public String toString() {

        return "Pair{" +
                "first=" + firstValue +
                ", second=" + secondValue +
                '}';
    }
}
