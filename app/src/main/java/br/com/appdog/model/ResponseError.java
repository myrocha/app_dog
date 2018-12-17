package br.com.appdog.model;

import com.google.gson.annotations.SerializedName;

public class ResponseError {
    @SerializedName("message")
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
