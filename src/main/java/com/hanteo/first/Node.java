package com.hanteo.first;

public class Node<T> {
    int id;
    T data;
    Node<T> parent_idx;
    Node<T> child_idx;

    public Node(int id, T data, Node parent_idx, Node child_idx) {
        this.id = id;
        this.data = data;
        this.parent_idx = parent_idx;
        this.child_idx = child_idx;
    }
}
