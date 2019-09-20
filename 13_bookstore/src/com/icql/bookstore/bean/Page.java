package com.icql.bookstore.bean;

import java.util.List;
import java.util.Map;

public class Page<T> {
    private int currentPage;
    private int pageSize;
    private int count;
    private int totalPage;
    private List<T> lsT;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List <T> getLsT() {
        return lsT;
    }

    public void setLsT(List <T> lsT) {
        this.lsT = lsT;
    }
}
