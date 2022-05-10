//Main class to tryout all implemented DSes
public class Tryout {
    public static void main(String[] args) {
//        System.out.println("Hello World!");
//        testDynamicArray();
        testDoublyLinkedList();

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
}
