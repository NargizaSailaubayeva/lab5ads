import java.util.Iterator;
import java.util.Stack;
public class BST<K extends Comparable <K>,V> implements Iterable<K>{
    private class Node<K, V>{
        private K key;
        private V value;
        private Node<K, V> left, right;
        public Node(K key, V value){
            this.key =key;
            this.value = value;
            left= right = null;
        }
    }
    private Node<K, V> root;
    private int size;

    public BST(){
        root = null;
        size = 0;
    }
    public int size(){
        return size;
    }
    private Node<K, V> insert(Node<K, V> current, K key, V value){
        if (current == null){
            return new Node<>(key, value);
        }
        if (key.compareTo(current.key) < 0) {
            current.left = insert(current.left, key, value);
        }
        else {
            current.right = insert(current.right, key, value);
        }
        return current;
    }
    public void put(K key, V value) {
        root = insert(root, key, value);
        size++;
    }
    public Iterator<K> iterator() {
        return new BSTIterator();
    }
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
    public V get(K key) {
        Node<K, V> node = getNode(root, key);
        if (node == null) {
            return null;
        }
        return node.value;
    }
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
    private K findSmallValue(Node<K, V> current) {
        if (current.left == null) {
            return current.key;
        }
        return findSmallValue(current.left);
    }
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

        private void pushLeftNodes(Node<K, V> node) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }

        public K next() {
            Node<K, V> node = stack.pop();
            pushLeftNodes(node.right);
            return node.key;
        }
    }
}
