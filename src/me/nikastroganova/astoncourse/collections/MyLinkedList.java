package me.nikastroganova.astoncourse.collections;

import java.util.Iterator;

public class MyLinkedList<T> implements Iterable<T> {

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    private class Node<T> {
        private T value;
        private Node<T> next;
        private Node<T> previos;

        public Node(T value) {
            this.value = value;
        }

    }
    private Node<T> first;
    private Node<T> last;

    private int size;

    public MyLinkedList() {
        size = 0;
    }

    public void addFirst(T element) {
        Node<T> node = new Node<T>(element);
        if(size == 0) {
            first = last = node;
        }
        else {
            first.previos = node;
            node.next = first;
            first = node;
        }
        size++;
    }
    public void addLast(T element) {
        Node node = new Node(element);
        if(size == 0) {
            first = last = node;
        }
        else {
            node.previos = last;
            last.next = node;
            last = node;
        }
        size++;
    }

    public void addAll(Iterable<? extends T> collection) {
        for (T element: collection)
            addLast(element);
    }

    public boolean insert(T element, int index) {
        if (index >= size || index < 0)
            return false;
        Node node = new Node(element);
        Node currentNode = getNodeByIndex(index);
        node.previos = currentNode.previos;
        node.previos.next = node;
        node.next = currentNode;
        currentNode.previos = node;
        size++;
        return true;
    }

    public boolean set(T element, int index) {
        Node node = getNodeByIndex(index);
        if (node == null)
            return false;
        node.value = element;
        return true;
    }

    public T getFirst() {
        return first.value;
    }

    public T getLast() {
        return last.value;
    }

    public T get(int index) {
        Node<T> node = getNodeByIndex(index);
        if (node != null)
            return node.value;
        return null;
    }

    public void removeFirst() {
        if (size == 0)
            return;
        if (size > 1) {
            last = last.previos;
            last.next = null;
        }
        else {
            last = null;
            first = null;
        }
        size--;
    }

    public void removeLast() {
        if (size == 0)
            return;
        if (size > 1) {
            first = first.next;
            first.previos = null;
        }
        else
            first = null;
        size--;
    }

    public boolean remove(int index) {
        Node node = getNodeByIndex(index);
        if (node == null)
            return false;
        node.previos.next = node.next; // Предыдущий нод теперь ссылается на следующий
        node.next.previos = node.previos; // А следующий на предыдущий
        size--;
        return true;
    }

    public int indexOf(T element) {
        int index = 0;
        Node currentNode = first;
        while(currentNode != null) {
            if (currentNode.value == element)
                return index;
            currentNode = currentNode.next;
            index++;
        }
        return -1;
    }

    public boolean contains(T element) {
        return indexOf(element) != -1;
    }

    public int getSize() {
        return size;
    }

    public T[] toArray() {
        T[] array = (T[]) new Object[size];
        Node currentNode = first;
        for (int i = 0; i < size; size++) {
            array[i] = (T) currentNode.value;
            currentNode = currentNode.next;
        }
        return array;
    }

    public String toString() {
        Node currentNode = first;
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for(int i = 0; i < size - 1; i++) {
            T value = (T) currentNode.value;
            builder.append(value.toString());
            builder.append(", ");
            currentNode = currentNode.next;
        }
        T value = (T) currentNode.value;
        builder.append(value.toString());
        builder.append("]");
        return builder.toString();
    }

    private Node getNodeByIndex(int index){
        if (index >= size || index < 0)
            return null;
        Node currentNode;
        if (index < size / 2) {
            currentNode = first;
            for (int currentIndex = 0; currentIndex < index; currentIndex++)
                currentNode = currentNode.next;
        } else {
            currentNode = last;
            for (int currentIndex = size - 1; currentIndex > index; currentIndex--)
                currentNode = currentNode.previos;
        }
        return currentNode;
    }

    private class Iter<T> implements Iterator<T>{

        private int currentElement;
        private Node<T> currentNode;

        public Iter() {
            currentElement = 0;
            currentNode = (Node<T>) first;
        }
        @Override
        public boolean hasNext() {
            return currentElement < size;
        }

        @Override
        public T next() {
            T value = currentNode.value;
            currentNode = currentNode.next;
            return value;
        }
    }

}


