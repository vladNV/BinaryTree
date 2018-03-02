package collection;

import java.util.Comparator;

public class BinaryTree <K, E> {
    private Comparator<K> comp;
    private Node<K, E> root;

    private Node<K, E> min;
    private Node<K, E> max;

    public BinaryTree(Comparator<K> comp) {
        this.comp = comp;
    }

    public int length() {
        return Node.counter;
    }

    public E min() {
        return min.element;
    }

    public E max() {
        return max.element;
    }

    public E peek() {
        return root.element;
    }

    public boolean put(K key, E element) {
        if (element == null) return false;
        if (root == null) {
            root = new Node<>(key, element);
            return true;
        }
        Node<K, E> current = root;
        while (true) {
            if (comp.compare(key, current.key) > 0) {
                if (current.right == null) {
                    current.right = new Node<>(key, element);
                    max = current.right;
                    return true;
                } else {
                    current = current.right;
                }
            } else if (comp.compare(current.key, key) < 0){
                if (current.left == null) {
                    current.left = new Node<>(key, element);
                    min = current.left;
                    return true;
                } else {
                    current = current.left;
                }
            } else {
                // such key already exists
                return false;
            }
        }
    }

    public E remove(K key) {
        if (root != null && key != null) {
            Node<K, E> temp;
            Node<K, E> parent = root;
            Node<K, E> current = root;
            boolean isLeft = true;
            while (!current.key.equals(key)) {
                parent = current;
                if (comp.compare(key, current.key) > 0) {
                    isLeft = false;
                    current = current.right;
                } else {
                    isLeft = true;
                    current = current.left;
                }
                if (current == null) return null;
            }
            // has not children
            if (current.left == null && current.right == null) {
                if (current.equals(root)) {
                    temp = root;
                    root = null;
                } else if (isLeft) {
                    temp = parent.left;
                    parent.left = null;
                } else {
                    temp = parent.right;
                    parent.right = null;
                }
                return temp.element;
                // has left child
            } else if (current.right == null) {
                if (current == root) root = current.left;
                else if (isLeft) parent.left = current.left;
                else parent.right = current.left;
                // has right child
            } else if (current.left == null) {
                if (current == root) root = current.right;
                else if (isLeft) parent.left = current.right;
                else parent.right = current.right;
                // has both children
            } else {
                Node<K, E> successor = seek(current);
                temp = current;
                if (current == root) root = successor;
                else if (isLeft) parent.left = successor;
                else parent.right = successor;
                successor.left = current.left;
                return temp.element;
            }
        }
        return null;
    }

    private Node<K, E> seek(Node<K, E> deletable) {
        Node<K, E> successorParent = deletable;
        Node<K, E> successor = deletable;
        Node<K, E> current = deletable.right;
        while (current != null) {
            successorParent = successor;
            successor = current;
            current = current.left;
        }
        if (successor != deletable.right) {
            successorParent.left = successor.right;
            successor.right = deletable.right;
        }
        return successor;
    }


    public E find(K key) {
        if (root != null && key != null) {
            if (root.key.equals(key)) return root.element;
            Node<K, E> current = root;
            while (!current.key.equals(key)) {
                if (comp.compare(key, current.key) > 0) {
                    current = current.right;
                } else if (comp.compare(key, current.key) < 0) {
                    current = current.left;
                } else {
                    // has not found
                    return null;
                }
            }
            return current.element;
        }
        return null;
    }


    private static class Node<K, E> {
        private static int counter = 0;
        private final E element;
        private final K key;
        private Node<K, E> left;
        private Node<K, E> right;

        Node(K key, E element) {
            ++counter;
            this.element = element;
            this.key = key;
        }

        @Override
        public int hashCode() {
            return element.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj != null && obj.getClass() == getClass()) {
                Node node = (Node) obj;
                return node.key.equals(this.key);
            }
            return false;
        }
    }
}
