//Main class to tryout all implemented DSes
public class Tryout {
    public static void main(String[] args) {
//        System.out.println("Hello World!");
//        testDynamicArray();
//        testDoublyLinkedList();
//        testStackWithBuiltInLinkedList();
//        testQueueWithBuiltInLinkedList();
//        testPQWithMinHeap();
        testUnionFind();

    }

    public static void testDynamicArray() {
        DynamicArray<Integer> intArr = new DynamicArray<>();
        System.out.println(intArr + ", size: " + intArr.size());
        intArr.add(7);
        System.out.println(intArr + ", size: " + intArr.size());
        intArr.add(-9);
        System.out.println(intArr + ", size: " + intArr.size());
        intArr.add(3);
        System.out.println(intArr + ", size: " + intArr.size());
        intArr.add(12);
        System.out.println(intArr + ", size: " + intArr.size());
        intArr.add(5);
        System.out.println(intArr + ", size: " + intArr.size());
        intArr.add(-6);
        System.out.println(intArr + ", size: " + intArr.size());
        intArr.remove(3);
        System.out.println(intArr + ", size: " + intArr.size());
        intArr.remove(12);
        System.out.println(intArr + ", size: " + intArr.size());
        intArr.remove(7);
        System.out.println(intArr + ", size: " + intArr.size());
        intArr.add(3);
        System.out.println(intArr + ", size: " + intArr.size());
        intArr.add(12);
        System.out.println(intArr + ", size: " + intArr.size());
        intArr.add(7);
        System.out.println(intArr + ", size: " + intArr.size());
        intArr.add(14);
        System.out.println(intArr + ", size: " + intArr.size());
    }

    public static void testDoublyLinkedList() {
        DoublyLinkedList<Integer> intlist= new DoublyLinkedList<>();
        System.out.println(intlist);
        intlist.addFirst(1);
        System.out.println(intlist);
        intlist.addLast(99);
        System.out.println(intlist);
        intlist.addAt(50, 1);
        System.out.println(intlist);
        intlist.addAt(67, 2);
        System.out.println(intlist);
        intlist.remove(50);
        System.out.println(intlist);
        intlist.removeAt(2);
        System.out.println(intlist);
        intlist.removeLast();
        System.out.println(intlist);
        intlist.removeFirst();
        System.out.println(intlist);
    }

    public static void testStackWithBuiltInLinkedList() {
        StackWithBuiltInLinkedList<Integer> intStack = new StackWithBuiltInLinkedList<>();
        System.out.println(intStack);
        intStack.push(12);
        System.out.println(intStack);
        intStack.push(13);
        System.out.println(intStack);
        intStack.pop();
        System.out.println(intStack);
        intStack.push(14);
        System.out.println(intStack);
        intStack.push(15);
        System.out.println(intStack);
        intStack.pop();
        System.out.println(intStack);
        intStack.push(16);
        System.out.println(intStack);
        intStack.pop();
        System.out.println(intStack);
        intStack.pop();
        System.out.println(intStack);
        intStack.push(17);
        System.out.println(intStack);
    }

    public static void testQueueWithBuiltInLinkedList() {
        QueueWithBuiltInLinkedList<Integer> intQueue = new QueueWithBuiltInLinkedList<>();
        System.out.println(intQueue);
        intQueue.offer(12);
        System.out.println(intQueue);
        intQueue.offer(13);
        System.out.println(intQueue);
        intQueue.poll();
        System.out.println(intQueue);
        intQueue.offer(14);
        System.out.println(intQueue);
        intQueue.offer(15);
        System.out.println(intQueue);
        intQueue.poll();
        System.out.println(intQueue);
        intQueue.offer(16);
        System.out.println(intQueue);
        intQueue.poll();
        System.out.println(intQueue);
        intQueue.poll();
        System.out.println(intQueue);
        intQueue.offer(17);
        System.out.println(intQueue);
    }

    public static void testPQWithMinHeap() {
        PriorityQueueWithMinHeap<Integer> intHeap = new PriorityQueueWithMinHeap<>();
        System.out.println(intHeap);
        intHeap.add(2);
        System.out.println(intHeap);
        intHeap.add(3);
        System.out.println(intHeap);
        intHeap.add(2);
        System.out.println(intHeap);
        intHeap.add(7);
        System.out.println(intHeap);
        intHeap.add(7);
        System.out.println(intHeap);
        intHeap.add(13);
        System.out.println(intHeap);
        intHeap.add(2);
        System.out.println(intHeap);
        intHeap.add(11);
        System.out.println(intHeap);
        System.out.println("Check whether the data is in correct positions: 2 -> 0,2,6 | 7 -> 3,4 | 11 -> 7 | 13 -> 5 | 3 -> 1");
        int initialSize = intHeap.size();
        for (int i = 0; i < initialSize; i++) {
            System.out.println(intHeap.poll() + " ");
        }

    }

    public static void testUnionFind() {
        UnionFind intUF = new UnionFind(12);
        System.out.println(intUF);
        intUF.union(0, 1);
        System.out.println(intUF);
        intUF.union(2, 3);
        System.out.println(intUF);
        intUF.union(4, 5);
        System.out.println(intUF);
        intUF.union(6, 7);
        System.out.println(intUF);
        intUF.union(8,9 );
        System.out.println(intUF);
        intUF.union(9, 6);
        System.out.println(intUF);
        intUF.union(7, 5);
        System.out.println(intUF);
        intUF.union(0, 2);
        System.out.println(intUF);
        intUF.union(3, 4);
        System.out.println(intUF);
        intUF.union(6, 1);
        System.out.println(intUF);
        intUF.union(8, 9);
        System.out.println(intUF);
        intUF.union(11, 10);
        System.out.println(intUF);
    }
}
