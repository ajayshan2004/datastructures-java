import java.util.*;

/**
 * Contains all implementations of tree traversals
 * IN PROGRESS
 */
public class BinarySearchTreeWithTraversalIterators<T extends Comparable<T>> extends BinarySearchTree<T> {
    public Iterator<T> traverse(TreeTraversalOrder order) {
        switch (order) {
            case PRE_ORDER: return preOrderTraversal();
            case POST_ORDER: return postOrderTraversal();
            case IN_ORDER: return inOrderTraversal();
            case LEVEL_ORDER: return levelOrderTraversal();
            default: return null;
        }

    }

    private Iterator<T> levelOrderTraversal() {
        int expectedNodeCount = nodeCount;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                //If someone tried to update tree while accessing, exception!
                //If a root is present and the queue has at least one value, traversing is still left, hence hasNext() = true
                if (expectedNodeCount != nodeCount) throw new ConcurrentModificationException();
                return root != null && !queue.isEmpty();
            }

            @Override
            public T next() {
                //Get current node, and push the left and right child in the order into the queue until, do this until the subtrees run out!
                if (expectedNodeCount != nodeCount) throw new ConcurrentModificationException();
                Node node = queue.poll();
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
                return node.data;
            }
        };
    }

    private Iterator<T> inOrderTraversal() {
        int expectedNodeCount = nodeCount;
        //Need a stack to keep track of the nodes visited so that in the reverse direction when it is time to print after subtree traversal
        //we know what to print!
        Stack<Node> stack = new Stack<>();
        //We start with inorder traversal of left subtree, so I need to keep track of root to print later!
        stack.push(root);
        return new Iterator<T>() {
            Node trav = root;
            @Override
            public boolean hasNext() {
                //If someone tried to update tree while accessing, exception!
                //If a root is present and the stack has at least one value, traversing is still left, hence hasNext() = true
                if (expectedNodeCount != nodeCount) throw new ConcurrentModificationException();
                return root != null && !stack.isEmpty();
            }

            //Why is this implementation a bit hard?
            //Because, I need to keep track of current node. I always need to dig to as far left as possible before I can
            //take action on current node. Even if I find something on the right after visting current node, again I
            // need to do the digLeft business!
            @Override
            public T next() {
                if (expectedNodeCount != nodeCount) throw new ConcurrentModificationException();
                //Dig left subtree
                while (trav != null && trav.left != null) {
                    stack.push(trav.left);
                    trav = trav.left;
                }
                //Print node
                Node node = stack.pop();

                //Try to move once to the right. Why is this enough?
                //Answer - I have already reached the left most subtree. If this is a leaf I am done and I need to backtrack
                // and the stack value is popped and I won't come here again! If the node has a right subtree by chance, I
                //go there and again do the left subtree part logic as a part of the next hasNext()!
                if (node.right != null) {
                    stack.push(node.right);
                    trav = node.right;
                }
                return node.data;
            }
        };
    }

    private Iterator<T> postOrderTraversal() {
        int expectedNodeCount = nodeCount;
        //Why 2 stacks? - stack 1 to push in the parent, visit the parent and push in the children, stack 2 stores the
        //post ordered data ready for a fetch with inserts in reverse order!
        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();
        stack1.push(root);
        while (!stack1.isEmpty()) {
            //popped from stack1 into stack2, hence visited and already in the postorder in its position in stack2!
            Node node = stack1.pop();
            if (node != null) {
                stack2.push(node);
                //Why first right and then left - Because its a stack! While popping right considered first!
                //and then right!!!
                if (node.left != null) stack1.push(node.left);
                if (node.right != null) stack1.push(node.right);
            }
        }
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                //If someone tried to update tree while accessing, exception!
                //If a root is present and the stack has at least one value, traversing is still left, hence hasNext() = true
                if (expectedNodeCount != nodeCount) throw new ConcurrentModificationException();
                return root != null && !stack2.isEmpty();
            }

            @Override
            public T next() {
                if (expectedNodeCount != nodeCount) throw new ConcurrentModificationException();
                return stack2.pop().data;
            }
        };
    }

    private Iterator<T> preOrderTraversal() {
        int expectedNodeCount = nodeCount;
        //Need a stack to keep track of the nodes to be visited after printing current node!
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                //If someone tried to update tree while accessing, exception!
                //If a root is present and the stack has at least one value, traversing is still left, hence hasNext() = true
                if (expectedNodeCount != nodeCount) throw new ConcurrentModificationException();
                return root != null && !stack.isEmpty();
            }

            //Why is this so easy?
            //Because, the current node is immediately visited and I don't have to keep track of it for later.
            //I can just proceed with the children and I only need to make sure that I do the left before the right!
            @Override
            public T next() {
                if (expectedNodeCount != nodeCount) throw new ConcurrentModificationException();
                Node node = stack.pop();
                //Why first right and then left - Because its a stack! While popping left considered first (left subtree)
                //and then right!!!
                if (node.right != null) stack.push(node.right);
                if (node.left != null) stack.push(node.left);
                return node.data;

            }
        };
    }
}
