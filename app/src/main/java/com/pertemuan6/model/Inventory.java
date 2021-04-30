package com.pertemuan6.model;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;

public class Inventory implements Parcelable {
    private Integer id;
    private String name;
    private String description;
    private Integer qty;

    public Inventory (){}

    public Inventory(Integer id, String name, String description, Integer qty) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.qty = qty;
    }

    public Inventory(String name, String description, Integer qty){
        this.name = name;
        this.description = description;
        this.qty = qty;
    }


    protected Inventory(Parcel in) {
        id = in.readInt();
        name = in.readString();
        description = in.readString();
        qty = in.readInt();
    }

    public static final Creator<Inventory> CREATOR = new Creator<Inventory>() {
        @Override
        public Inventory createFromParcel(Parcel in) {
            return new Inventory(in);
        }

        @Override
        public Inventory[] newArray(int size) {
            return new Inventory[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeInt(qty);
    }
}
