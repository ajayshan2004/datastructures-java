/**
 *  NOTES:
 *
 *  UnionFind/Disjoint Set DS used to determine connected components, grid percolation, networking, Kruskal's minimum spanning tree etc.,
 *  Union - connects 2 components
 *  Find - finds which component a node is a part of
 *
 *  Path compression, once a node is connected, compress the path to connect the new node brought in directly to the new root so that finding eventual parent is shorter
 *  Easier to convert any problem with other data types into integer so that we can use array indices to play with!
 *  We need to create a bijection mapping to then map the numbers to the actual nodes, possibly using a map to refer to the original nodes.
 */

public class UnionFind {

    private int size;

    private int sizes[];        //track sizes of each component

    private int id[];           //id[i] = parent(i). If id[i] = i, then i is root of the component

    private int numComponents;

    public UnionFind(int size) {
        if(size == 0) throw new IllegalArgumentException("Size <=0 not allowed");
        this.size = numComponents = size;
        sizes = new int[size];
        id = new int[size];
        for (int i = 0; i < size; i++) {
            sizes[i] = 1;
            id[i] = i;
        }
    }


    public int find(int p) {
        int root = p;

        //traverse from p to the component root
        while (root != id[root]) {
            root = id[root];
        }

        //compress the path once done, gives amortized constant time. Attach p directly to the newly found root and do the same to the old parent and above recursively
        while (p != root) {
            int next = id[p];
            id[p] = root;
            p = next;
        }

        return root;
    }

    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    public int componentSize(int p) {
        return sizes[find(p)];
    }

    public int size() {
        return size;
    }

    public int components() {
        return numComponents;
    }

    public void union(int p, int q) {
        int root1 = find(p);
        int root2 = find(q);
        if (root2 == root1) return;     //don't unify already unified nodes, else there will be a cycle and future traversals go for a toss!

        //connect the smaller component to the larger component by making the root of the larger as the parent of the root of the smaller and update the component sizes as needed
        if (sizes[root1] < sizes[root2]) {
            sizes[root2] += sizes[root1];
            id[root1] = root2;
        }
        else {
            sizes[root1] += sizes[root2];
            id[root2] = root1;
        }

        numComponents--;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }
        else {
            StringBuilder sb = new StringBuilder(size).append("[");
            for (int i = 0; i < size -1; i++) {
                sb.append(id[i] + ", ");
            }
            return sb.append(id[size -1] + "]").toString();
        }
    }
}
