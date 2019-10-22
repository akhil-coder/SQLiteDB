package com.appface.akhil.sqlitedb;

import com.google.gson.annotations.SerializedName;

public class ImageResponse {

    @SerializedName("message")
    private String message;

    @SerializedName("statusCode")
    private int statusCode;

    public String getMessage() {
        return message;
    }
}
