import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.LinkedList;

public class QueueWithBuiltInLinkedList<T> implements Iterable<T> {
    LinkedList<T> list = new LinkedList<>();

    public QueueWithBuiltInLinkedList() {}

    public QueueWithBuiltInLinkedList(T firstElem) {
        offer(firstElem);
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void offer(T elem) {
        list.addLast(elem);
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return list.removeFirst();
    }

    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Queue Empty");
        }
        return list.peekFirst();
    }

    public T poll() {
        if (isEmpty()) {
            throw new RuntimeException("Queue Empty");
        }
        return list.removeFirst();
    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
