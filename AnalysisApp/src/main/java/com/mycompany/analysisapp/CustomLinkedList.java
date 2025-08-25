package com.mycompany.analysisapp;

public class CustomLinkedList<T> {
    public CustomNode<T> head;
    private int size;

    public CustomLinkedList() {
        this.head = null;
        this.size = 0;
    }

    public void insertNode(T value) {
        CustomNode<T> newNode = new CustomNode<>(value);

        if (head == null) {
            head = newNode;
        } else {
            CustomNode<T> temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
        size++;
    }

    public void printList() {
        CustomNode<T> temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    public CustomNode<T> getHead() {
        return head;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        CustomNode<T> temp = head;
        while (temp != null) {
            result.append(temp.data);
            if (temp.next != null) {
                result.append(", ");
            }
            temp = temp.next;
        }
        result.append("]");
        return result.toString();
    }
    
    public int getSize(){
        return this.size;
    }
    
}

