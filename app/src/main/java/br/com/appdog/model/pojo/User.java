package br.com.appdog.model.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * class return webservice after login.
 */
public class User {

    @SerializedName("user")
    @Expose
    private DetailUser user;

    public DetailUser getUser() {
        return user;
    }

    public void setUser(DetailUser user) {
        this.user = user;
    }
}
