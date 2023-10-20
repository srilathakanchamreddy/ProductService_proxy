package com.example.productservice_proxy.Models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public abstract class BaseModel {
    private long id;
    private Date created_at;
    private Date last_updated_at;
    private boolean is_deleted;

    public void setIsDeleted(boolean b) {
        this.is_deleted = b;
    }
}
