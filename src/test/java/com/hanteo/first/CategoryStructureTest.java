package com.hanteo.first;

import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

class CategoryStructureTest {
    CategoryStructure<String> categoryStructure = new CategoryStructure<>();

    CategoryStructureTest() {
        Node<String> man = categoryStructure.add("남자", null);
        Node<String> currentNode = categoryStructure.add("엑소", man);
        categoryStructure.connectBoard(1, categoryStructure.add("공지사항", currentNode));
        categoryStructure.connectBoard(2, categoryStructure.add("첸", currentNode));
        categoryStructure.connectBoard(3, categoryStructure.add("백현", currentNode));
        categoryStructure.connectBoard(4, categoryStructure.add("시우민", currentNode));

        currentNode = categoryStructure.add("방탄소년단", man);
        categoryStructure.connectBoard(5, categoryStructure.add("공지사항", currentNode));
        categoryStructure.connectBoard(6, categoryStructure.add("익명게시판", currentNode));
        categoryStructure.connectBoard(7, categoryStructure.add("뷔", currentNode));

        Node<String> woman = categoryStructure.add("여자", null);
        currentNode = categoryStructure.add("블랙핑크", woman);
        categoryStructure.connectBoard(8, categoryStructure.add("공지사항", currentNode));
        categoryStructure.connectBoard(6, categoryStructure.add("익명게시판", currentNode));
        categoryStructure.connectBoard(9, categoryStructure.add("로제", currentNode));

        currentNode = categoryStructure.add("뉴진스", woman);
        categoryStructure.connectBoard(10, categoryStructure.add("공지사항", currentNode));
        categoryStructure.connectBoard(6, categoryStructure.add("익명게시판", currentNode));
        categoryStructure.connectBoard(11, categoryStructure.add("민지", currentNode));
        categoryStructure.connectBoard(12, categoryStructure.add("하니", currentNode));
    }

    @Test
    @DisplayName("카테고리 명을 사용한 단일 조회 테스트")
    void findOneCategoryWithName(){
        Node<String> findWoman = categoryStructure.findOneByData("여자");
        Node<String> findMan = categoryStructure.findOneByData("남자");

        Assertions.assertEquals("여자", findWoman.getData());
        Assertions.assertEquals("남자", findMan.getData());

    }

    @Test
    @DisplayName("카테고리 명을 사용한 전체 조회 테스트")
    void findAllCategoryWithName() {
        HashMap findCategories = categoryStructure.findAllByData("여자");

        System.out.println(new JSONObject(categoryStructure.convertJson(findCategories)));
        Assertions.assertDoesNotThrow(() -> new JSONObject(categoryStructure.convertJson(findCategories)));
    }

    @Test
    @DisplayName("카테고리 ID를 사용한 단일 조회 테스트")
    void findOneCategoryWithId(){
        Node<String> findWoman = categoryStructure.findOneById(11);
        Node<String> findBlackFink = categoryStructure.findOneById(12);

        Assertions.assertEquals("여자", findWoman.getData());
        Assertions.assertEquals("블랙핑크", findBlackFink.getData());
    }

    @Test
    @DisplayName("카테고리 ID을 사용한 전체 조회 테스트")
    void findAllCategoryWithId() {
        HashMap findCategories = categoryStructure.findAllById(12);

        System.out.println(new JSONObject(categoryStructure.convertJson(findCategories)));
        Assertions.assertDoesNotThrow(() -> new JSONObject(categoryStructure.convertJson(findCategories)));
    }



    @Test
    @DisplayName("삭제 테스트")
    void removeCategory(){
        Node<String> findWoman = categoryStructure.findOneByData("여자");
        categoryStructure.remove(findWoman);

        Assertions.assertNull(categoryStructure.findOneByData("여자"));
        Assertions.assertNull(categoryStructure.findOneByData("블랙핑크"));
        Assertions.assertNull(categoryStructure.findOneByData("로제"));
    }

    @Test
    void categoryTest(){
        JSONObject jObject = new JSONObject(categoryStructure.convertJson());
        System.out.println(jObject);
    }
}