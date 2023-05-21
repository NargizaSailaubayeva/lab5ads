public class BST<K extends Comparable <K>,V> implements Iterable<K>{
    private Node root;
    private class Node{
        private K key;
        private V value;
        private Node left, right;
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
}
