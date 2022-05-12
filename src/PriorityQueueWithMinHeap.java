import java.util.*;

/**
 * NOTES:
 *
 * Heaps need to follow a 'heap invariant' or 'heap property', else arranging them as a tree is useless!
 * Heaps are 'almost complete' tree. Tree in which every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible
 * Complete binary heaps are binary heaps that are as full as possible and new elements are assigned to as left as possible in the tree (The max difference in the leaf levels cannot be > 1)
 * For binary heap, if i is the parent node index then, left child index = 2i+1, right child index = 2i+2
 * Min heap OR Max heap are heaps where for all nodes the parent is lesser OR greater than the children (heap invariants)
 *
 * Priority queue (ADT) can be implemented using a binary heap that gives the best time complexities.
 * For a PQ, the items in the tree need to be Comparable (else heap invariant cannot be tested!)
 * Use min heap for min PQ and max heap for max PQ so that poll is always at the root and hence achieved in O(1)!
 * Implementation of min PQ can be easily converted to max PQ by:
 *      1. Inverting the Comparator
 *      2. Hack: For numbers, negate all elements, read out the elements and negate again!
 *  Whether we poll or remove a specific item in the heap, we need to readjust the heap to satisfy the heap invariant.
 *  For this we need to bubble/sift up/down.
 *
 *  Insertion into heap:
 *      1. Add node to as much left most as possible to keep the tree balanced!
 *      2. Bubble up (swim) till heap satisfies the heap invariant
 *
 *  Poll from heap:
 *      1. Swap root with last element in array (which represents the rightmost of the left most nodes!)
 *      2. Remove the element
 *      3. Bubble down (sink) till heap satisfies the heap invariant
 *
 *  Remove from heap (O(n)):
 *      1. Linear search to find element
 *      2. Swap element with last element in array (which represents the rightmost of the left most nodes!)
 *      3. Bubble up (swim) or down (sink) till heap satisfies the heap invariant.
 *
 *  Remove from heap (O(log n), but takes some more space complexity):
 *      1. Maintain a hashtable (key=node data; value=set of indices where node is present in array)
 *      2. While doing same process as insertion, poll, O(n) heap removal, also swap the index positions in hashtable!
 *      3. This lakes O(log n) because it avoids linear search in the tress as index information for every node is already available.
 *
 *  While bubbling down, choose the smallest child to swap with. If chidren are equal, choose leftmost subtree.
 *
 *  Important facts to be used to 'heapify'
 *      1. All leaf nodes are already heaps, so they dont need to be heapified!
 *      2. The first non-leaf node in a heap is at index (n/2-1).
 */

public class PriorityQueueWithMinHeap <T extends Comparable<T>> {
    private int heapSize = 0;

    private int heapCapacity = 0;

    private List<T> heap;

    //Map for the O(log(n)) removal and O(1) containment check
    //TreeSet in java uses balanced binary trees so all operations are O(log(n))
    private final Map<T, TreeSet<Integer>> map = new HashMap<>();

    public PriorityQueueWithMinHeap() {
        this(1);
    }

    public PriorityQueueWithMinHeap(int size) {
        heap = new ArrayList<>(size);
    }

    public PriorityQueueWithMinHeap(T[] elems) {
        heapSize = heapCapacity = elems.length;
        heap = new ArrayList<>(heapCapacity);

        //put all the elements inside the heap
        for (int i = 0; i < heapSize; i++) {
            mapAdd(elems[i], i);
            heap.add(elems[i]);
        }

        //Heapify! O(n) all non-leaf nodes and start from the first non-leaf node at n/2 - 1
        for(int i = Math.max(0, (heapSize/2) - 1); i >= 0; i--) {
            sink(i);
        }

    }

    public PriorityQueueWithMinHeap (Collection <T> elems) {
        this(elems.size());
        for (T elem : elems) {
            add(elem);
        }
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    public void clear() {
        for (int i = 0; i < heapCapacity; i++) {
            heap.set(i, null);
        }
        heapSize = 0;
        map.clear();
    }

    public int size() {
        return heapSize;
    }

    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return heap.get(0);
    }

    public T poll() {
        return removeAt(0);
    }

    public boolean contains(T elem) {
        if (elem == null) {
            return false;
        }

        //Because of map, lookup is O(1)
        return map.containsKey(elem);

        //If no map, lookup is O(n), linear search (can't use binary search as elements in heap are not sorted)
//        for(int i = 0; i < heapSize; i++) {
//            return heap.get(i).equals(elem);
//        }
//        return false;
    }

    public void add(T elem) {
        if (elem == null) {
            throw new IllegalArgumentException();
        }
        if (heapSize < heapCapacity) {
            //add new element at the end of ArrayList at a position already within the ArrayList allocated memory
            heap.add(heapSize, elem);
        }
        else {
            //heap has grown beyond the ArrayList so increase capacity
            heap.add(elem); //this increases heapsize automatically
            heapCapacity++;
        }

        mapAdd(elem, heapSize);

        swim(heapSize);
        heapSize++;
    }

    private boolean less(int i, int j) {
        T node1 = heap.get(i);
        T node2 = heap.get(j);
        return node1.compareTo(node2) <= 0;
    }

    //Bottom up node swim (O(log n))
    private void swim(int k) {
        int parent = (k - 1) / 2;
        //Swimming should include all nodes as it is possible for leaf node also to swim up somewhere!
        while (k > 0 && less(k, parent)) {  //if current node k is less than the parent and k is not root
            swap(parent, k);                //swap the parent and k
            k = parent;                     //make k the new parent
            parent = (k - 1) / 2;           //from the new parent get it's parent to check whether to swim upwards!
        }
    }

    //Top down node sink (O(log n))
    private void sink(int k) {
        while (true) {
            //fetch the children
            int left = 2*k + 1;
            int right = 2*k + 2;
            int smallest = left;
            //determine which child is smallest
            if(right < heapSize && less(right, left)) {
                smallest = right;
            }

            //if tree bound is crossed or if we cant sink the node anymore and it is in its right position, STOP!
            if (left >= heapSize || less(k, smallest)) break;
            swap(smallest, k);
        }
    }

    private void swap(int i, int j) {
        T i_elem = heap.get(i);
        T j_elem = heap.get(j);
        heap.set(i, j_elem);
        heap.set(j, i_elem);

        //Disadvantage of using map is everytime swap is called, the map needs to be updated.
        mapSwap(i_elem, j_elem, i, j);
    }

    private boolean remove(T elem) {
        if (elem == null) return false;

        //Linear Search removal O(n)
//        for(int i = 0; i < heapSize; i++) {
//            if (heap.get(i).equals(elem)) {
//                removeAt(i);
//                return true;
//            }
//        }

        //Removal with map O(log(n))
        Integer index = mapGet(elem);
        if (index != null) {
            removeAt(index);
        }
        return index != null;
    }

    private T removeAt(int i) {
        if (isEmpty()) return null;
        heapSize--;
        //Fetch data and immediately swap with last element in the list and obliterate the last value
        T data = heap.get(i);
        swap(i, heapSize);
        heap.set(heapSize, null);
        mapRemove(data);

        //If the removed node was already the last element, there is no need for further sinking etc.,
        if (i == heapSize) return data;

        T elem = heap.get(i);

        //try to sink element
        sink(i);

        //if sinking doesn't work and node did not move anywhere, try to swim
        if (heap.get(i).equals(elem)) {
            swim(i);
        }
        return data;
    }

    //Test method to check whether the tree rooted at k is a min heap or not
    public boolean isMinheap(int k) {
        if (k >= heapSize) return true;     //we are outside the heap bounds, return true

        int left = 2*k + 1;
        int right = 2*k + 2;

        //Make sure k, left, right nodes satisfy the heap property
        if (left <= heapSize && less(k, left)) return false;
        if (right <= heapSize && less(k, right)) return false;

        //Recursively check whether the above is true for the subtrees of the children!
        return isMinheap(left) && isMinheap(right);
    }

    private void mapAdd(T elem, int index) {
        TreeSet<Integer> set = map.get(elem);
        if (set == null) {
            set = new TreeSet<>();
            set.add(index);
            map.put(elem, set);
        }
        else {
            set.add(index);
        }
    }

    private void mapRemove(T elem) {
        TreeSet<Integer> set = map.get(elem);
        set.remove(elem);
        if (set.size() == 0) {
            map.remove(elem);
        }
    }

    //For a node lookup in map, if data is duplicated in the tree, it does not matter which index is fetched (last index arbitrarily chosen here)
    //Anyway further sink or swim to maintain heap property will adjust the tree correctly.
    private Integer mapGet(T elem) {
        TreeSet<Integer> set = map.get(elem);
        if (set != null) return set.last();
        return null;
    }

    private void mapSwap(T i_elem, T j_elem, int i, int j) {
        Set<Integer> set1 = map.get(i_elem);
        Set<Integer> set2 = map.get(j_elem);

        set1.remove(i);
        set1.add(j);

        set2.remove(j);
        set2.add(i);
    }

    @Override
    public String toString() {
        return heap.toString();
    }
}
