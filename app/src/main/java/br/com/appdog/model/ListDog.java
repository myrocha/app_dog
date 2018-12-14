package br.com.appdog.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListDog {

    @SerializedName("category")
    private String category;
    @SerializedName("list")
    private List<String> list = null;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}
