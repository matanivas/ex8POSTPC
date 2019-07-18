package com.example.ex8;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Headers;
import retrofit2.http.Body;

    public interface RetrofitServer {

        @Headers({"Content-Type: application/json" })
        @POST("/user/edit/")
        Call<UserResponse> edit(@Body SetUserPrettyNameRequest prettyName, @Header("Authorization") String auth);


        @GET("/users/{username}/token/")
        Call<TokenResponse> token(@Path("username") String username);


        @GET("/user")
        Call<UserResponse> user(@Header("Authorization") String auth);




    }
