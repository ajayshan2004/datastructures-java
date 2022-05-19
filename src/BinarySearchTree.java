import java.util.*;

/**
 * NOTES:
 * Binary tree - every node has atmost 2 children
 * BST - binary tree that satiffies the BST invariant - left subtree has nodes less than current node, right subtree has nodes greater than current node
 * All nodes need to be comparable.
 *
 * O(log n) for insert, remove, search etc., operations (O(n) in worst case - straight line trees)
 *
 * Refer #BinarySearchTreeWithTraversalIterators for traversal logic
 */
public class BinarySearchTree <T extends Comparable<T>> {
    protected class Node {
        T data;
        Node left, right;
        public Node(Node left, Node right, T elem) {
            this.data = elem;
            this.left = left;
            this.right = right;
        }
    }

    protected int nodeCount = 0;

    protected Node root = null;

    public int size() {
        return nodeCount;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean add(T elem) {

        //choosing to not insert duplicate items. Anyone can choose different!
        if(contains(elem)) {
            return false;
        }
        else {
            root = add(root, elem);
            nodeCount++;
            return true;
        }

    }

    public Node add(Node node, T elem) {
        //if leaf node, add node
        if(node == null) {
            node  = new Node(null, null, elem);
        }
        else {
            //recursively call add depending on how elem compares with the current node
            if (elem.compareTo(node.data) < 0) {
                node.left = add(node.left, elem);
            }
            else {
                node.right = add(node.right, elem);
            }
        }
        return node;
    }

    public boolean remove(T elem) {
        if (contains(elem)) {
            root = remove(root, elem);
            nodeCount--;
            return true;
        }
        return false;
    }

    //After removing the node, return the replaced node at this place
    private Node remove(Node node, T elem) {
        if (node == null) return null;
        int cmp = elem.compareTo(node.data);
        //try to find in left subtree
        if (cmp < 0) {
            node.left = remove(node.left, elem);
        }
        //try to find in right subtree
        else if (cmp > 0) {
            node.right = remove(node.right, elem);
        }
        //found it!
        else {
            /*
                4 cases possible now:
                    1. The node has only right subtree
                    2. The node has only left subtree
                    3. The node has both left and right subtrees
                    4. Trivial case - the node has no (null) subtrees
             */

            //Case 1 - Only right or no subtree at all; then remove element and make root of the child subtree the child of the current node's parent
            if (node.left == null) {
                Node rightChild = node.right;
                node.data = null;
                node = null;
                return rightChild;
            }
            //Case 2 - Only left or no subtree at all; then remove element and make root of the child subtree the child of the current node's parent
            else if (node.right == null) {
                Node leftChild = node.left;
                node.data = null;
                node = null;
                return leftChild;
            }
            /*Case 3 - MOST INTERESTING! There are now 2 roots of subtrees who can be contenders to replace removed nodes.
                    Select
                    Largest in the left subtree - because it is lesser than all elements in right subtree by design
                    AND it is largest in left subtree meaning that it is at the right most point so easy code to reach!

                                                                 OR

                    Smallest in the right subtree - because it is larger than all elements in left subtree by design
                    AND it is smallest in right subtree meaning that it is at the left most point so easy code to reach!

             */
            //Choosing option 2 above
            else {
                //Consider the right subtree root and keep on moving to left till you hit a leaf node, that is the smallest in right subtree
                Node smallestInRightSubtree = digLeft(node.right);
                //swap the new data with current node, now we end up with 2 nodes having same data.
                // The smallestInRightSubtree is either in Case 1 or Case 2 for sure, because we know that one of the
                // subtrees is null already from the while condition above
                node.data = smallestInRightSubtree.data;

                //remove the duplicate node! Since we pass in node.right as root to the call below, we don't need to worry about getting the current node as duplicate!
                node.right = remove(node.right, smallestInRightSubtree.data);

            }
        }

        return node;
    }

    private Node digLeft(Node node) {
        Node cur = node;
        while (cur.left != null) {
            cur = cur.left;
        }
        return cur;
    }

    public boolean contains(T elem) {
        return contains(root, elem);
    }

    //check presence of element in BST by following the BST invariant from the root
    private boolean contains(Node node, T elem) {
        if (node == null) return false;
        int cmp = elem.compareTo(node.data);
        if (cmp < 0) {
            return contains(node.left, elem);
        } else if (cmp > 0) {
            return contains(node.right, elem);
        } else {
          return true;
        }
    }

    public int height() {
        return height(root);
    }

    //INTERESTING - recursive call to get height of tree in O(n)!!
    private int height(Node node) {
        if (node == null) return 0;
        return Math.max(height(node.left), height(node.right)) + 1;
    }




}
