package seminar3.tasks.task3;

import seminar3.tasks.task2.MyCollection;

import java.awt.event.MouseAdapter;

/*
Написать итератор по массиву. Итератор - это объект, осуществляющий движение по коллекциям любого типа,
содержащим любые типы данных. Итераторы обычно имеют только два метода - проверка на наличие следующего элемента
и переход к следующему элементу. Но также, особенно в других языках программирования, возможно встретить итераторы,
реализующие дополнительную логику.
 */
public class Main {
    public static void main(String[] args) {
        OwnList<String> list = new OwnList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");

        for (String string : list) {
            System.out.println(string);
    }

    }
}

