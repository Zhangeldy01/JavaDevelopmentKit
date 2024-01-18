package seminar3.tasks.task2;

public class MyCollection<T> {
    private Object[] arr;
    private int size;

    public MyCollection(Object[] arr) {
        this.arr = arr;
    }

    public MyCollection() {
        arr = new Object[10];
    }

    public void add(T element) {
        if(size >= arr.length) {
            Object[] temp = new Object[arr.length * 2];
            System.arraycopy(arr, 0, temp, 0, arr.length);
            arr = temp;
        }
        arr[size] = element;      // или запись в одну строку arr[size++] = element;
        size++;
    }

    public void remove(int index) {
        if(index >= 0 && index < size) {
            System.arraycopy(arr, index + 1, arr, index, arr.length - 1 - index);
            size--;
        }
    }

    public void remove(T element) {
        for(int i = 0; i < arr.length; i++) {
            if(arr[i].equals(element)) {
                remove(i);
                return;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        if (size != 0) {
            stringBuilder.append(arr[0]);
        }
        for (int i = 1; i < size; i++) {
            stringBuilder.append(", ");
            stringBuilder.append(arr[i]);

        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
