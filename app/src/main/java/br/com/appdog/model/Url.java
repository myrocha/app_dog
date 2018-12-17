package br.com.appdog.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class Url {
    @PrimaryKey(autoGenerate = true)
    public long id;

    @SerializedName("url")
    @ColumnInfo(name = "url")
    public String url;

    @SerializedName("category")
    @ColumnInfo(name = "category")
    public String category;




    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
