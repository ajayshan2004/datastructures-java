import java.util.Iterator;

public class DynamicArray<T> implements Iterable<T> {

    private T[] arr;
    private int len = 0;            //length of the array that the user thinks
    private int capacity = 0;       //actual array size

    public DynamicArray(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException(" Illegal capacity: " + capacity);
        }
        this.capacity = capacity;
        arr = (T[]) new Object[capacity];

    }

    public DynamicArray() {
        this(2);            //initial capacity = 2
    }

    public int size() {
        return this.len;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public T get(int index) {
        if (index < 0 || index >= len) {
            throw new IndexOutOfBoundsException();
        }
        return arr[index];
    }

    public void set(int index, T elem) {
        if (index < 0 || index >= len) {
            throw new IndexOutOfBoundsException();
        }
        arr[index] = elem;
    }

    public void clear() {
        for (int i = 0; i < capacity; i++) {
            arr[i] = null;
        }
        len = 0;
    }

    public void add(T elem) {
        if (len + 1 > capacity) {
            if (capacity == 0) {
                capacity = 1;
            } else {
                capacity *= 2;          //if overshot, double the array size
            }
            T[] new_arr = (T[]) new Object[capacity];
            for (int i = 0; i < len; i++) {
                new_arr[i] = arr[i];
            }
            arr = new_arr;              //copy old array to new one and replace
        }
        arr[len++] = elem;              //add the new element to end of array
        System.out.println(" Internal capacity: " + capacity);
    }

    public T removeAt(int rm_index) {
        if (rm_index < 0 || rm_index >= len) {
            throw new IndexOutOfBoundsException();
        }
        T data = arr[rm_index];
        T[] new_arr = (T[]) new Object[len - 1];
        for (int i = 0, j = 0; i < len; i++, j++) {
            if (i == rm_index) {
                j--;                //skip the element at rm_index by just ignoring it in j index for new_arr
            } else {
                new_arr[j] = arr[i];
            }
        }
        arr = new_arr;
        capacity = --len;
        System.out.println(" Internal capacity: " + capacity);
        return data;
    }

    public boolean remove(Object obj) {
        for (int i = 0; i < len; i++) {
            if (obj == arr[i]) {
                removeAt(i);
                return true;
            }
        }
        return false;
    }

    public int indexOf(Object obj) {
        for (int i = 0; i < len; i++) {
            if (obj == arr[i]) {
                return i;
            }
        }
        return -1;
    }

    public boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int index = 0;
            @Override
            public boolean hasNext() {
                return index < len;
            }

            @Override
            public T next() {
                return arr[index++];
            }
        };
    }

    @Override
    public String toString() {
        if (len == 0) {
            return "[]";
        }
        else {
            StringBuilder sb = new StringBuilder(len).append("[");
            for (int i = 0; i < len -1; i++) {
                sb.append(arr[i] + ", ");
            }
            return sb.append(arr[len -1] + "]").toString();
        }
    }
}
