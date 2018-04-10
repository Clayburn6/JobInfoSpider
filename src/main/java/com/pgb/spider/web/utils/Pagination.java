package com.pgb.spider.web.utils;

import java.util.ArrayList;
import java.util.List;

public class Pagination<T> {
    private Integer number;

    private Integer totalPages;

    private Integer size;

    private List<T> content = new ArrayList<T>();

    public Pagination(Integer number, Integer totalPages, Integer size, List<T> content) {
        this.number = number;
        this.totalPages = totalPages;
        this.size = size;
        this.content = content;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }
}
