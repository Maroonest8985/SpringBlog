package com.example.demo123.DTO;

public class PageDTO {
    int nowPageCounter;

    public int getNowPageCounter() {
        return nowPageCounter;
    }

    public void setNowPageCounter(int nowPageCounter) {
        this.nowPageCounter = nowPageCounter;
    }

    public int getMaxPage() {
        return maxPage;
    }

    public void setMaxPage(int maxPage) {
        this.maxPage = maxPage;
    }

    public int getNowPage() {
        return nowPage;
    }

    public void setNowPage(int nowPage) {
        this.nowPage = nowPage;
    }

    int maxPage;
    int nowPage;
}
