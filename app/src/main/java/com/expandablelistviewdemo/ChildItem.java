package com.expandablelistviewdemo;

/**
 * Created by Jumy on 16/1/6.
 * 二级列表item
 */
public class ChildItem {
    private int id;
    private String name;
    private String content;

    public ChildItem(int id, String name, String content) {
        this.id = id;
        this.name = name;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
