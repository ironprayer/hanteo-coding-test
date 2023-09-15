package com.hanteo.first;

public class Tree<T> {
    private final Node<T> root;

    public Tree() {
        this.root = new Node<T>(0, null, null, null);
    }

    void add(){}

    void remove() {}

    void find(T data) {}

    void convertToJson() {};
}
