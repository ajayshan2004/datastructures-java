import java.util.Iterator;

//Main class to tryout all implemented DSes
public class Tryout {
    public static void main(String[] args) {
//        System.out.println("Hello World!");
//        testDynamicArray();
//        testDoublyLinkedList();
//        testStackWithBuiltInLinkedList();
//        testQueueWithBuiltInLinkedList();
//        testPQWithMinHeap();
//        testUnionFind();
        testBinarySearchTreeWithTraversalIterators();

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

    //internally tests BinarySearchTree class as there is no way to know tree structure without traversing!!
    public static void testBinarySearchTreeWithTraversalIterators() {
        BinarySearchTreeWithTraversalIterators<Integer> intBST = new BinarySearchTreeWithTraversalIterators<>();
        intBST.add(7);
        intBST.add(20);
        intBST.add(5);
        intBST.add(15);
        intBST.add(10);
        intBST.add(4);
        intBST.add(4);
        intBST.add(33);
        intBST.add(2);
        intBST.add(25);
        intBST.add(6);
        Iterator<Integer> preIter = intBST.traverse(TreeTraversalOrder.PRE_ORDER);
        Iterator<Integer> inIter = intBST.traverse(TreeTraversalOrder.IN_ORDER);
        Iterator<Integer> postIter = intBST.traverse(TreeTraversalOrder.POST_ORDER);
        Iterator<Integer> levelIter = intBST.traverse(TreeTraversalOrder.LEVEL_ORDER);
        System.out.println("\nPreOrder : ");
        while (preIter.hasNext()) {
            System.out.print(preIter.next() + " ");
        }
        System.out.println("\nInOrder : ");
        while (inIter.hasNext()) {
            System.out.print(inIter.next() + " ");
        }
        System.out.println("\nPostOrder : ");
        while (postIter.hasNext()) {
            System.out.print(postIter.next() + " ");
        }
        System.out.println("\nLevelOrder : ");
        while (levelIter.hasNext()) {
            System.out.print(levelIter.next() + " ");
        }

    }
}
