package com.tt.oa.entity;

import java.util.HashMap;
import java.util.Map;

public class Trie {
    private boolean isWord;
    private Map<Character, Trie> next;

    public Trie() {
        this.isWord=false;
        this.next=new HashMap<>();
    }

    @Override
    public String toString() {
        return "Trie{" +
                "isWord=" + isWord +
                ", next=" + next +
                '}';
    }

    public boolean isWord() {
        return isWord;
    }

    public void setWord(boolean word) {
        isWord = word;
    }

    public Map<Character, Trie> getNext() {
        return next;
    }

    public void setNext(Map<Character, Trie> next) {
        this.next = next;
    }
}
