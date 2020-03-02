package com.ahuiali.word.utils;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class PageUtil {

    private int curr = 1;

    private int total;

    private int size = 30;

    private int offset = 0;

    public void create(int curr, int size){
        this.curr = curr;
        this.size = size;
        this.offset = (curr - 1)*size;
    }

    public int getCurr() {
        return curr;
    }

    public void setCurr(int curr) {
        this.curr = curr;

    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;

    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public void renew() {
        this.offset = (curr - 1)*size;
    }

    @Override
    public String toString() {
        return "PageUtil{" +
                "curr=" + curr +
                ", total=" + total +
                ", size=" + size +
                ", offset=" + offset +
                '}';
    }
}
