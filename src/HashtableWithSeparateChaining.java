import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Hash table, key, hash function
 * Hash function must be deterministic; should always return same value for input
 * If H(x) == H(y), the values MAY be the same but if H(x) != H(y), the values are SURELY different
 * Hash collisions
 * Separate chaining - method by using a key and a value=data structure that can add the collided values.
 * The idea is to hash to the data structure and then find the element
 */
public class HashtableWithSeparateChaining <K, V>{

    private static final int DEFAULT_CAPACITY = 3;
    //If filled size goes above the load factor, resize.
    private static final double DEFAULT_LOAD_FACTOR = 0.75;

    private double maxLoadFactor;
    private int capacity, threshold, size = 0;
    private LinkedList<Entry<K, V>>[] table;

    public HashtableWithSeparateChaining() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public HashtableWithSeparateChaining(int capacity) {
        this(capacity, DEFAULT_LOAD_FACTOR);
    }

    public HashtableWithSeparateChaining(int capacity, double maxLoadFactor) {
        if (capacity < 0)
            throw new IllegalArgumentException("Illegal capacity");
        if (maxLoadFactor <= 0 || Double.isNaN(maxLoadFactor) || Double.isInfinite(maxLoadFactor)) {
            throw new IllegalArgumentException("Illegal maxLoadFactor");
        }
        this.maxLoadFactor = maxLoadFactor;
        this.capacity = Math.max(DEFAULT_CAPACITY, capacity);
        threshold = (int)(this.capacity * maxLoadFactor);
        table = new LinkedList[this.capacity];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    //Convert a hashvalue to index. Especially to strip the negative sign and restrict the output to fit in 'capacity' index.
    private int normalizeIndex(int keyHash) {
        return (keyHash & 0x7FFFFFFF) % capacity;
    }

    private void clear() {
        Arrays.fill(table, null);
        size = 0;
    }

    public boolean containsKey(K key) {
        return hasKey(key);
    }

    /*
    //find index that hey hashes to and then search, insert, get, remove in corresponding linked list
     */
    private boolean hasKey(K key) {
        int index = normalizeIndex(key.hashCode());
        return bucketSeekEntry(index, key) != null;
    }

    public V insert(K key, V value) {
        if (key == null)
            throw new IllegalArgumentException("Invalid key");
        Entry<K, V> newEntry = new Entry<>(key, value);
        int index = normalizeIndex(key.hashCode());
        return bucketInsertEntry(index, newEntry);
    }

    public V get(K key) {
        if (key == null)
            return null;
        int index = normalizeIndex(key.hashCode());
        Entry<K, V> entry = bucketSeekEntry(index, key);
        if (entry != null)
            return entry.value;
        return null;
    }

    public V remove(K key) {
        if (key == null)
            return null;
        int index = normalizeIndex(key.hashCode());
        return bucketRemoveEntry(index, key);
    }

    private V bucketRemoveEntry(int index, K key) {
        Entry<K, V> entry = bucketSeekEntry(index, key);
        if (entry != null) {
            LinkedList<Entry<K, V>> bucket = table[index];
            bucket.remove(entry);
            --size;
            return entry.value;
        }
        return null;
    }

    private V bucketInsertEntry(int index, Entry<K, V> entry) {
        LinkedList<Entry<K, V>> bucket = table[index];
        if (bucket == null)
            table[index] = bucket = new LinkedList<>();
        Entry<K, V> existingEntry = bucketSeekEntry(index, entry.key);
        if (existingEntry == null) {
            bucket.add(entry);
            //take care of resizing if threshold crossed
            if (++size > threshold)
                resizeTable();
            //return null to indicate that this is a brand new data getting added
            return null;
        }
        else {
            V oldVal = existingEntry.value;
            existingEntry.value = entry.value;
            return oldVal;
        }
    }

    private Entry<K, V> bucketSeekEntry(int index, K key) {
        if (key == null) return null;
        LinkedList<Entry<K, V>> bucket = table[index];
        if (bucket == null) return null;
        for (Entry<K, V> entry : bucket)
            if (entry.key.equals(key))
                return entry;
        return null;
    }

    private void resizeTable() {
        //double capacity size and mark the new threshold
        capacity *= 2;
        threshold = (int)(capacity * maxLoadFactor);
        LinkedList<Entry<K, V>>[] newTable = new LinkedList[capacity];
        //for every index in previous table array
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                //for every LinkedList entry at table index i
                for (Entry<K, V> entry : table[i]) {
                    int index = normalizeIndex(entry.hash);
                    LinkedList<Entry<K, V>> bucket = newTable[index];
                    if (bucket == null)
                        newTable[index] = bucket = new LinkedList<>();
                    bucket.add(entry);
                }
                //avoid any memory leaks
                table[i].clear();
                table[i] = null;
            }
        }
        table = newTable;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (int i = 0; i < capacity; i++) {
            if (table[i] == null) continue;
            sb.append("(" + i + " => ");
            for (Entry<K, V> entry : table[i])
                sb.append(entry + ", ");
            sb.append(")\n");
        }
        sb.append("}");
        return sb.toString();
    }
}

class Entry <K, V> {
    K key;
    V value;
    int hash;

    public Entry(K key, V value) {
        this.key = key;
        this.value = value;
        this.hash = key.hashCode();
    }

    public boolean equals (Entry<K, V> other) {
        if (this.hash != other.hash) return false;
        return this.key == other.key;
    }

    @Override
    public String toString() {
        return this.key + " - " + this.value;
    }
}
