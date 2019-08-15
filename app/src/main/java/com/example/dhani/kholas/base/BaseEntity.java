package com.example.dhani.kholas.base;

import java.io.Serializable;
import java.util.Date;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

/**
 * Created by Muhammad Natsir on 16,April,2019
 * Jakarta, Indonesia.
 */
@Entity
public class BaseEntity implements Serializable {
    @Id
    private long id;
    private Date createdDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
