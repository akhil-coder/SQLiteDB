package com.appface.akhil.sqlitedb;

import com.appface.akhil.sqlitedb.Beans.ImageRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("saveCandidateProfile")
    Call<ImageResponse>  uploadImage(@Body ImageRequest imageName );
}
