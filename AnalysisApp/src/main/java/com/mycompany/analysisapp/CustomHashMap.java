package com.mycompany.analysisapp;

public class CustomHashMap<K extends Comparable<K>, V> {

    private static final int INITIAL_CAPACITY = 16; 
    private static final double LOAD_FACTOR = 0.75; 

    private Node<K, V>[] table;
    private int size;

    public CustomHashMap() {
        this.table = new Node[INITIAL_CAPACITY];
        this.size = 0;
    }

    public void put(K key, V value) {
        int index = calculateIndex(key);

        if (table[index] == null) {
            table[index] = new Node<>(key, value);
            size++;
        } else {
            Node<K, V> current = table[index];
            while (current.next != null) {
                if (current.key.equals(key)) {
                    current.value = value;
                    return;
                }
                current = current.next;
            }
            current.next = new Node<>(key, value);
            size++;
        }

        if ((double) size / table.length > LOAD_FACTOR) {
            resize();
        }
    }

    public V get(K key) {
        int index = calculateIndex(key);
        Node<K, V> current = table[index];

        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }

        return null;
    }

    public void remove(K key) {
        int index = calculateIndex(key);
        Node<K, V> current = table[index];
        Node<K, V> prev = null;

        while (current != null) {
            if (current.key.equals(key)) {
                if (prev == null) {
                    table[index] = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                return;
            }
            prev = current;
            current = current.next;
        }
    }

    public int size() {
        return size;
    }

    private void resize() {
        Node<K, V>[] oldTable = table;
        table = new Node[2 * oldTable.length];
        size = 0;

        for (Node<K, V> entry : oldTable) {
            while (entry != null) {
                put(entry.key, entry.value);
                entry = entry.next;
            }
        }
    }

    private int calculateIndex(K key) {
      int hash = hash(key);
      return Math.abs(hash) % table.length;
}

    private int hash(K key) {
        return key == null ? 0 : key.hashCode();
    }
       
}