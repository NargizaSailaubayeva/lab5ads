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
