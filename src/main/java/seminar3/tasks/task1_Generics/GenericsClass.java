package seminar3.tasks.task1_Generics;

import java.io.DataInput;
import java.io.InputStream;

/**
 * Класс GenericsClass - это параметризированный класс (generics), который принимает три типа переменных: T, V, K.
 * На типы наложены ограничения:
 * @param <T> Тип, который должен реализовать интерфейс Comparable<T>, что позваляет сравнивать объекты типа T.
 * @param <V> Тип, который должен быть подтипом InputStream  и реализовать интерфейс DataInputStream.
 * @param <K> Тип, который должен быть подтипом Number.
 */
public class GenericsClass<T extends Comparable<T>, V extends InputStream & DataInput, K extends Number> {
    // Классы пишутся первыми, а интерфейсы после; InputStream это класс,DataInput - интерфейс
    private T tVarName; // переменная типа T
    private V vVarName; // переменная типа V
    private K kVarName; // переменная типа K

    /**
     * конструктор класса GenericsClass.
     * @param tVarName Значение для переменной типа T.
     * @param vVarName Значение для переменной типа V.
     * @param kVarName Значение для переменной типа K.
     */
    public GenericsClass(T tVarName, V vVarName, K kVarName) {
        this.tVarName = tVarName; // Инициализация переменной tVar
        this.vVarName = vVarName; // Инициализация переменной vVar
        this.kVarName = kVarName; // Инициализация переменной kVar
    }

    /**
     * Метод для получения значения переменной tVarName.
     * @return Значение переменной tVarName.
     */
    public T getTVarName() {
        return tVarName; // Возвращаем значение переменной tVarName
    }

    /**
     * Метод для получения значения переменной vVarName.
     * @return Значение переменной vVarName.
     */
    public V getVVarName() {
        return vVarName; // Возвращаем значение переменной vVarName
    }

    /**
     * Метод для получения значения переменной kVarName.
     * @return Значение переменной kVarName.
     */
    public K getKVarName() {
        return kVarName; // Возвращаем значение переменной kVarName
    }

    /**
     * Метод для вывода имен классов переменных tVarName, vVarName, kVarName.
     */
    public void printNamesOfClasses() {
        System.out.println("Name of class for variable tVarName:" + tVarName.getClass().getName()); // Вывод имени класса переменной tVarName
        System.out.println("Name of class for variable vVarName:" + vVarName.getClass().getName()); // Вывод имени класса переменной vVarName
        System.out.println("Name of class for variable KVarName:" + kVarName.getClass().getName()); // Вывод имени класса переменной kVarName
    }
}
