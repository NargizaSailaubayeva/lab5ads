public class BST<K extends Comparable <K>,V> {
    private Node root;
    private class Node{
        private K key;
        private V value;
        private Node left, right;
        public Node(K key, V value){
            this.key =key;
            this.value = value;
        }
    }
}
