package br.com.appdog.model.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * class pojo of the server error information.
 */
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
