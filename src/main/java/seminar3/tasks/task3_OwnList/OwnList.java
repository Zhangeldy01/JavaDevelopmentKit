package seminar3.tasks.task3_OwnList;

import java.util.*;

public class OwnList<T> implements Iterable<T> {
    private Object[] arr;
    private int size;

    public OwnList() {
        arr = new Object[10];
    }

    public void add(T item) {
        if(size == arr.length) {
            Object[] newArr = new Object[arr.length * 2];
            System.arraycopy(arr, 0, newArr, 0, arr.length);
            arr = newArr;
        }
        arr[size++] = item;
    }

    public T remove() {
        if(size == 0) throw new NoSuchElementException();
        T temp = (T) arr[--size];
        arr[size] = null;
        return temp;
    }

    @Override
    public String toString() {
        return Arrays.toString(arr);
    }

    @Override
    public Iterator<T> iterator() {
        List<T> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add((T)arr[i]);
        }
        return new MyIterator<>(list);
    }
}
