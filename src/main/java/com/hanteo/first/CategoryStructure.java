package com.hanteo.first;

import java.util.*;

public class CategoryStructure<T> {
    private final int ROOT_IDX = 0;
    private int nodeId = 1;
    private List<Node<T>> nodes = new ArrayList<>();
    private HashMap<Integer, List<Node<T>>> childes = new HashMap<>();
    private HashMap<Integer, List<Node<T>>> boardMapper = new HashMap<>();

    public CategoryStructure() {
        nodes.add(new Node<>(ROOT_IDX, null, null, null));
        childes.put(ROOT_IDX, new ArrayList<>());
    }

    public Node<T> add(T data, Node<T> parent) {
        Node<T> currentParent = (parent == null) ? nodes.get(ROOT_IDX) : parent;

        Integer parent_idx = nodes.indexOf(currentParent);
        Integer child_id = parent_idx;

        Node<T> newNode = new Node<>(nodeId++, data, parent_idx, null);

        if(currentParent.getChild_id() == null) {
            childes.put(child_id, new ArrayList<>(Arrays.asList(newNode)));
        } else {
            childes.get(child_id).add(newNode);
        }

        currentParent.changeChild_id(child_id);
        nodes.add(newNode);

        return newNode;
    }

    public void remove(Node<T> node){
        if(node == null) return;

        if (nodes.contains(node)){
            removeSubCategories(node);
        }

        nodes.remove(node);
    }

    private void removeSubCategories(Node<T> node){
        if(node.getChild_id() == null) return;

        for(Node<T> child : childes.get(node.getChild_id())){
            removeSubCategories(child);
            nodes.remove(child);
        }
        childes.remove(node.getChild_id());
    }

    public Node<T> findOneByData(T data){
        for(Node<T> node : nodes){
            if(node.getData() != null && node.getData().equals(data)){
                return node;
            }
        }

        return null;
    }

    public Node<T> findOneById(Integer id){
        for(Node<T> node : nodes){
            if(node.getId() != null && node.getId().equals(id)){
                return node;
            }
        }

        return null;
    }

    public HashMap<Integer, List<Node<T>>> findAllByData(T data){
        Node<T> findNode = null;
        HashMap<Integer, List<Node<T>>> result = new HashMap<>();

        for(Node<T> node : nodes){
            if(node.getData() != null && node.getData().equals(data)){
                findNode = node;
            }
        }

        result.put(ROOT_IDX, Arrays.asList(findNode));
        findAllChildNode(result, findNode);

        return result;
    }

    public HashMap<Integer, List<Node<T>>> findAllById(Integer id){
        Node<T> findNode = null;
        HashMap<Integer, List<Node<T>>> result = new HashMap<>();

        for(Node<T> node : nodes){
            if(node.getId().equals(id)){
                findNode = node;
            }
        }

        result.put(ROOT_IDX, Arrays.asList(findNode));
        findAllChildNode(result, findNode);

        return result;
    }

    private void findAllChildNode(HashMap<Integer, List<Node<T>>> nodes, Node<T> startNode){
        nodes.put(startNode.getChild_id(), childes.get(startNode.getChild_id()));
        for(Node<T> node : childes.get(startNode.getChild_id())) {
            if(node.getChild_id() == null) return;
            findAllChildNode(nodes, node);
        }
    }

    public void connectBoard(Integer boardId, Node<T> node){
        if(boardMapper.containsKey(boardId)){
            boardMapper.get(boardId).add(node);
        } else {
            boardMapper.put(boardId, new ArrayList<>(Arrays.asList(node)));
        }
    }

    public String convertJson(){
        return convertJson(nodes.get(ROOT_IDX));
    }

    public String convertJson(HashMap<Integer, List<Node<T>>> categories) {
        return convertJson(categories.get(ROOT_IDX).get(0));
    }

    public String convertJson(Node<T> startNode){
        String result = "{ \"data\" : [\n" + recursiveToJson(startNode) +"\n]}";

        return result.replace(",\n]" , "]").replace(",]", "]");
    }

    private String recursiveToJson(Node<T> currentNode){
        String result = "";
        String end = ",";
        if(nodes.indexOf(currentNode) != ROOT_IDX) {
            Integer currentBoarId = findBoardId(currentNode);
            String boardJson = (currentBoarId != null) ?  (",\n \"boardId\" : " + currentBoarId) : "";
            result = "{ \"id\" : "
                    + currentNode.getId()
                    + ",\n \"category\" : "
                    + "\"" + currentNode.getData() + "\""
                    + boardJson
                    + ",\n \"parent_idx\" : "
                    + currentNode.getParent_idx()
                    + ",\n \"childes\" : ";

            if (currentNode.getChild_id() == null) {
                return result + null + "},\n";
            }
            result = result + "[";
            end = "]},";
        }

        if (currentNode.getChild_id() == null) {
            return result + "";
        }

        for (Node<T> child : childes.get(currentNode.getChild_id())) {
            result = result + recursiveToJson(child);
        }

        return result + end;
    }

    private Integer findBoardId(Node<T> node){
        for(Integer key : boardMapper.keySet()){
            if(boardMapper.get(key).contains(node)) return key;
        }
        return null;
    }

}
