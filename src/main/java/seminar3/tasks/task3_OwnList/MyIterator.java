package seminar3.tasks.task3_OwnList;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class MyIterator<T> implements Iterator<T> {
    private List<T> list;
    private int index;

    public MyIterator(List<T> list) {
        this.list = list;
    }

    public MyIterator(T[] arr){
        this(Arrays.asList(arr));
    }

    @Override
    public boolean hasNext() {
        return index < list.size();
    }

    @Override
    public T next() {
        return list.get(index++);
    }
}
