package com.example.ex8;

import android.app.ProgressDialog;

import java.sql.SQLOutput;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Server {

    private RetrofitServer server = null;
    private String urlServer = "http://hujipostpc2019.pythonanywhere.com/";
    private String loged;
    MainActivity call_ma;
    private boolean dialogShowed;
    private String token;
    private ProgressDialog dialog;

    Server(MainActivity call, String usrname){
        this.call_ma = call;
        loged = usrname;
        dialog = new ProgressDialog(this.call_ma);
        dialog.setCancelable(false);
        dialog.setInverseBackgroundForced(false);
        dialog.setMessage("LOADING");
        if(server==null) {
            OkHttpClient c = new OkHttpClient();
            server = (new Retrofit.Builder()
                    .client(c).baseUrl(urlServer)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build())
                    .create(RetrofitServer.class);
        }
        serverConection();
    }


    public synchronized void serverConection () {
        dialog.show();
        dialogShowed = true; //Todo check if necessary
        server.token(loged).enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                if(response.isSuccessful()&&response.body()!=null){
                    token=response.body().data;
                    String auth = "token "+ token;
                    server.user(auth).enqueue(new Callback<UserResponse>() {
                        @Override
                        public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {

                            if(response!= null && response.isSuccessful() && response.body()!=null){

                                call_ma.display(response.body().data);
                                dialog.hide();
                            }
                        }

                        @Override
                        public void onFailure(Call<UserResponse> call, Throwable t) {
                            User incorrectUser=new User("FAILED TO LOAD","FAILED TO LOAD","");
                            call_ma.display(incorrectUser);
                            dialog.hide();
                        }
                    });

                }


            }
            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {
                System.out.println("ruti"+t);
                System.out.println("ruti call is "+call);

                User incorrectUser=new User("FAILED TO LOAD","FAILED TO LOAD","");
                call_ma.display(incorrectUser);
                dialog.hide();
            }

        });



    }
    public synchronized void pretty_set(String name){

        dialog.show();
        SetUserPrettyNameRequest pName=new SetUserPrettyNameRequest(name);

        server.edit(pName,"token "+token).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if(response.isSuccessful()&&response.body()!=null){
                    call_ma.display(response.body().data);
                    dialog.hide();
                }

            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                User incorrectUser=new User("FAILED TO LOAD","FAILED TO LOAD","");
                call_ma.display(incorrectUser);
                dialog.hide();
            }
        });
    }

    }
