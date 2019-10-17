package com.appface.akhil.sqlitedb;

import com.google.gson.annotations.SerializedName;

public class ImageClass {

    @SerializedName("title")
    private String title;

    @SerializedName("image")
    private String Image;

    @SerializedName("response")
    private String Response;

    public String getResponse() {
        return Response;
    }
}
