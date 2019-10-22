package com.appface.akhil.sqlitedb.Beans;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.sql.Blob;

public class ImageRequest  {

    @SerializedName("imageName")
    String imageName;
    @SerializedName("description")
    String description;
    @SerializedName("imageUrl")
    String imageUrl;

    public ImageRequest(String imageName, String description, String imaUrl) {
        this.imageName = imageName;
        this.description = description;
        this.imageUrl = imaUrl;
    }

    public ImageRequest() {
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImaUrl() {
        return imageUrl;
    }

    public void setImaUrl(String imaUrl) {
        this.imageUrl = imaUrl;
    }


    @Override
    public String toString() {
        return "ImageRequest{" +
                "imageName='" + imageName + '\'' +
                ", description='" + description + '\'' +
                ", imaUrl='" + imageUrl + '\'' +
                '}';
    }
}
