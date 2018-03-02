import collection.BinaryTree;

import java.util.ArrayList;

public class App {

    public static void main(String[] args) {

        BinaryTree<String, Integer> tree = new BinaryTree<>(String::compareTo);
        tree.put("A", 1);
        tree.put("B", 2);
        Integer found = tree.find("B");
        System.out.println(found);
    }

}
