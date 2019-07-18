package com.example.ex8;

import android.widget.EditText;
import android.content.SharedPreferences;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    SharedPreferences sp;
    String url_server = "http://hujipostpc2019.pythonanywhere.com/";
    Server server;
    String loged;
    TextView welcome;
    TextView username_txtview ;
    EditText pretty_editxt;
    ImageView img_url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         welcome = (TextView) findViewById(R.id.welcome);
         username_txtview = (TextView) findViewById(R.id.nameOfUser);
         pretty_editxt = (EditText) findViewById(R.id.set_pretty);
         img_url = (ImageView) findViewById(R.id.profile_img);


        sp = getSharedPreferences("saved_username", Context.MODE_PRIVATE);
        String res = sp.getString("saved_username",null);
        loged =res;

        if (loged != null && !loged.isEmpty()){
            server = new Server(this, loged);
        }
        else {
            Intent intent = new Intent(this, ConectUser.class);
            startActivityForResult(intent, 1);
        }

    }

    public void display(User user) {
        if (user != null) {
            String userHeadline = user.username;

            if (user.pretty_name == null || user.pretty_name.isEmpty()) {
                welcome.setText("welcome " + user.username);
            } else {
                welcome.setText("welcome again " + user.pretty_name);
            }
            username_txtview.setText(userHeadline);
            pretty_editxt.setText(user.pretty_name);
            Picasso.get().load(url_server + user.image_url).into(img_url);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode!=RESULT_OK||data==null){
            return;
        }
        if(requestCode== 1){

            loged =data.getStringExtra("filled user name");
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("saved_username", loged);
            editor.apply();

            server = new Server(this, loged);
        }
    }
    public void uploadToServer(View view){
        String pretty_n = ((EditText)(findViewById(R.id.set_pretty))).getText().toString();
        server.pretty_set(pretty_n);
    }



    }

