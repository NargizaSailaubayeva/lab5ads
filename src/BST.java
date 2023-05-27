import java.util.Iterator;
import java.util.Stack;

public class BST<K extends Comparable<K>, V> implements Iterable<K> {
    // Inner class representing a node in the binary search tree
    private class Node<K, V> {
        private K key; // Key of the node
        private V value; // Value associated with the key
        private Node<K, V> left, right; // Left child node, Right child node

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = right = null;
        }
    }

    private Node<K, V> root; // Root node of the binary search tree
    private int size;

    public BST() {
        root = null;
        size = 0;
    }

    /**
     * Returns the number of nodes in the binary search tree.
     *
     * @return the number of nodes in the tree
     */
    public int size() {
        return size;
    }

    /**
     * Recursive helper method to insert a node in the tree.
     *
     * @param current the current node being processed
     * @param key     the key of the node to be inserted
     * @param value   the value associated with the key
     * @return the updated node after insertion
     */
    private Node<K, V> insert(Node<K, V> current, K key, V value) {
        if (current == null) {
            return new Node<>(key, value);
        }
        if (key.compareTo(current.key) < 0) {
            current.left = insert(current.left, key, value);
        } else {
            current.right = insert(current.right, key, value);
        }
        return current;
    }

    /**
     * Put a key-value pair into the binary search tree.
     *
     * @param key   the key to be inserted
     * @param value the value associated with the key
     */
    public void put(K key, V value) {
        root = insert(root, key, value);
        size++;
    }

    /**
     * Returns an iterator to traverse the binary search tree in-order.
     *
     * @return an iterator for the binary search tree
     */
    public Iterator<K> iterator() {
        return new BSTIterator();
    }

    /**
     * Recursive helper method to retrieve a node with the given key.
     *
     * @param current the current node being processed
     * @param key     the key to search for
     * @return the node with the given key, or null if not found
     */
    private Node<K, V> getNode(Node<K, V> current, K key) {
        if (current == null || key.compareTo(current.key) == 0) {
            return current;
        }
        if (key.compareTo(current.key) < 0) {
            return getNode(current.left, key);
        } else {
            return getNode(current.right, key);
        }
    }

    /**
     * Returns the value associated with the given key if it exists in the tree.
     *
     * @param key the key to search for
     * @return the value associated with the key, or null if not found
     */
    public V get(K key) {
        Node<K, V> node = getNode(root, key);
        if (node == null) {
            return null;
        }
        return node.value;
    }

    /**
     * Recursive helper method to remove a node with the given key from the tree.
     *
     * @param current the current node being processed
     * @param key     the key of the node to be removed
     * @return the updated node after removal
     */
    private Node<K, V> remove(Node<K, V> current, K key) {
        if (current == null)
            return null;
        if (key.compareTo(current.key) < 0) {
            current.left = remove(current.left, key);
        } else if (key.compareTo(current.key) > 0) {
            current.right = remove(current.right, key);
        } else {
            if (current.left == null && current.right == null) {
                return null;
            }
            if (current.left == null) {
                return current.right;
            }
            if (current.right == null) {
                return current.left;
            }
            K smallValue = findSmallValue(current.right);
            current.value = (V) smallValue;
            current.right = remove(current.right, smallValue);
        }
        return current;
    }

    /**
     * Removes a node with the given key from the tree.
     *
     * @param key the key of the node to be deleted
     */
    public void delete(K key) {
        root = remove(root, key);
        size--;
    }

    /**
     * Recursive helper method to find the smallest key in the subtree rooted at the given node.
     *
     * @param current the current node being processed
     * @return the smallest key in the subtree
     */
    private K findSmallValue(Node<K, V> current) {
        if (current.left == null) {
            return current.key;
        }
        return findSmallValue(current.left);
    }

    /**
     * Inner class representing the iterator for traversing the binary search tree in-order.
     */
    private class BSTIterator implements Iterator<K> {
        private Stack<Node<K, V>> stack;

        public BSTIterator() {
            stack = new Stack<>();
            pushLeftNodes(root);
        }

        public void inOrder(Node root) {
            if (root == null) return;
            inOrder(root.left);
            System.out.println(root.value + " ");
            inOrder(root.right);
        }

        /**
         * Helper method to push all left child nodes of the given node onto the stack.
         *
         * @param node the node to start the traversal from
         */
        private void pushLeftNodes(Node<K, V> node) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }

        /**
         * Checks if there are more nodes to traverse.
         *
         * @return true if there are more nodes, false otherwise
         */
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        /**
         * Returns the next key in the in-order traversal of the binary search tree.
         *
         * @return the next key in the traversal
         */
        public K next() {
            Node<K, V> node = stack.pop();
            pushLeftNodes(node.right);
            return node.key;
        }
    }
    private Node<K, V> consist(K key, Node current) {
        if (current == null && key.compareTo((K) current.key) == 0) {
            return current;
        }
        if (key.compareTo((K) current.key) < 0) {
            return consist(key, current.left);
        } else {
            return consist(key, current.right);
        }
    }
    public boolean consist(K key) {
        Node node = consist(key, root);
        if (node == null) {
            return false;
        } else {
            return true;
        }
    }
}
