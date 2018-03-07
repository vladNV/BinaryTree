package collection;

import java.util.Comparator;

public class RedBlackTree <K, E> extends BinaryTree <K, E> {

    public RedBlackTree(Comparator<K> comp) {
        super(comp);
    }
}
