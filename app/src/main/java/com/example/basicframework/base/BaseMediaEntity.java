package com.example.basicframework.base;

import android.os.Parcel;
import android.os.Parcelable;


public abstract class BaseMediaEntity implements Parcelable {

    protected enum TYPE{
        IMAGE,
        VIDEO
    }
    protected String id;
    protected String path;
    protected String size;
    protected String name;
    public Boolean isSelected = false;

    public BaseMediaEntity() {
    }

    public BaseMediaEntity(String id, String path) {
        this.id = id;
        this.path = path;
    }

    public BaseMediaEntity(Parcel in) {
        this.id   = in.readString();
        this.path = in.readString();
        this.size = in.readString();
        this.name = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.path);
        dest.writeString(this.size);
        dest.writeString(this.name);
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

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }
}
