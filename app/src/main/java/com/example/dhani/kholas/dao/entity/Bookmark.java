package com.example.dhani.kholas.dao.entity;

import com.example.dhani.kholas.base.BaseEntity;

import io.objectbox.annotation.Entity;

/**
 * Created by Muhammad Natsir on 15,August,2019
 * Jakarta, Indonesia.
 */
@Entity
public class Bookmark extends BaseEntity {

    public int page;
    public int target;
    public String time;

    public Bookmark() {
    }

    public Bookmark(int page) {
        this.page = page;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
