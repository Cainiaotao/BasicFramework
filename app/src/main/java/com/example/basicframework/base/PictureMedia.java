package com.example.basicframework.base;

import android.os.Parcel;

public class PictureMedia{

    private String id;
    private String path;
    private String size;
    private String name;
    private String type;

    public PictureMedia(String id, String path, String size, String name, String type) {
        this.id = id;
        this.path = path;
        this.size = size;
        this.name = name;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
