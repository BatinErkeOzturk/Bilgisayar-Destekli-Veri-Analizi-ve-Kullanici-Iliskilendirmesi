package com.mycompany.analysisapp;

public class CustomNode<T> {
    public T data;
    public CustomNode<T> next;

    public CustomNode(T data) {
        this.data = data;
        this.next = null;
    }
    
    public T getData() {
        return data;
    }

    public CustomNode<T> getNext() {
        return next;
    }
    
}


