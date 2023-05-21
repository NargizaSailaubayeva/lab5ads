public class Main {
    public static void main(String[] args) {
        BST bst = new BST();
        bst.put(1, 5);
        bst.put(2, 6);
        bst.put(3, 7);
        bst.put(4, 8);
        bst.put(5, 9);
        bst.get(4);
        bst.get(2);
        bst.delete(3);
        System.out.println(bst.get(4));
        System.out.println(bst.size());
    }
}