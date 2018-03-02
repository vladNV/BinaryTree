import collection.BinaryTree;

import java.util.HashMap;
import java.util.Iterator;

public class App {

    public static void main(String[] args) {

        BinaryTree<Integer, String> tree = new BinaryTree<>(Integer::compareTo);
        tree.put(431, "ABC");
        tree.put(234, "DCE");
        tree.put(345, "DS");
        tree.put(536, "SDFF");
        tree.put(347, "SDSD");
        tree.put(120, "SDGFD");
        tree.put(482, "SDL");
        tree.put(264, "SAS");
        tree.put(312, "SDF");
        tree.put(714, "HDF");
        System.out.println(tree.remove(444));
        System.out.println(tree.remove(714));
        tree.iterator();

    }

}
