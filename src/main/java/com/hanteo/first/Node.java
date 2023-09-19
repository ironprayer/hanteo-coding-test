package com.hanteo.first;


public class Node<T> {
    private Integer id;
    private T data;
    private Integer parent_idx;
    private Integer child_id;

    public Node(Integer id, T data, Integer parent_idx, Integer child_id) {
        this.id = id;
        this.data = data;
        this.parent_idx = parent_idx;
        this.child_id = child_id;
    }

    public void changeParent_idx(int parent_idx){
        this.parent_idx = parent_idx;
    }

    public void changeChild_id(int child_id){
        this.child_id = child_id;
    }

    public Integer getChild_id() {
        return child_id;
    }

    public Integer getParent_idx() {
        return parent_idx;
    }

    public Integer getId() {
        return id;
    }

    public T getData() {
        return data;
    }
}
