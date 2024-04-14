package me.nikastroganova.astoncourse.collections;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

public class MyArrayList<T> implements Iterable<T> {

    private final int DEFAULT_CAPACITY = 10;
    private T[] array;
    private int size;

    public MyArrayList() {
        array = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public MyArrayList(int capacity) {
        array = (T[]) new Object[capacity];
    }

    public void add(T element) {
        if (size == array.length)
            increaseCapacity();
        array[size++] = element;
    }

    public void addAll(Iterable<? extends T> otherCollection) {
        for (T element: otherCollection)
            this.add(element);
    }

    public T get(int index) {
        if (index >= size)
            return null;
        T element = array[index];
        moveElements(index);
        return element;
    }

    public boolean remove(int index) {
        if (index >= size) {
            return false;
        }
        else {
            moveElements(index);
            return true;
        }
    }

    public int size() {
        return size;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        builder.append(array[0].toString());
        for (int i = 1; i < size; i++) {
            builder.append(", ");
            builder.append(array[i].toString());
        }
        builder.append("]");
        return builder.toString();
    }

    public T[] toArray() {
        return Arrays.copyOf(array, size);
    }

    private void increaseCapacity() {
        int newCapacity = (int) Math.ceil(array.length * 1.5);
        array = Arrays.copyOf(array, newCapacity);
    }

    private void moveElements(int index) {
        for (int i = index; i < size - 1; i++)
            array[i] = array[i + 1];
    }

    @Override
    public Iterator<T> iterator() {
        return new Iter();
    }

    private class Iter implements Iterator<T> {

        private int currentElement;

        public Iter() {
            currentElement = 0;
        }
        @Override
        public boolean hasNext() {
            return currentElement < size;
        }

        @Override
        public T next() {
            return array[currentElement++];
        }
    }

    static public <T extends Comparable<? super T>> void sort(Collection<T> collection) {
        T[] arr = (T[]) new Object[collection.size()];
        arr = collection.toArray(arr);
        for(int i = 0; i < arr.length - 1; i ++) {
            for(int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    T temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        collection.clear();
        for(int i = 0; i < arr.length; i++) {
            collection.add(arr[i]);
        }
    }

    static public <T> void sort(Collection<T> collection, Comparator<? super T> comparator) {
        T[] arr = (T[]) collection.toArray();
        for(int i = 0; i < arr.length - 1; i ++) {
            for(int j = 0; j < arr.length - 1 - i; j++) {
                if (comparator.compare(arr[j], arr[j + 1]) > 0) {
                    T temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        collection.clear();
        for(int i = 0; i < arr.length; i++) {
            collection.add(arr[i]);
        }
    }

}

