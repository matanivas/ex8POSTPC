package com.example.ex8;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ConectUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conect_user);



        Button next  = (Button)findViewById(R.id.next);
        EditText username_typed;
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username_typed = ((EditText)(findViewById(R.id.username))).getText().toString();

                if (username_typed.isEmpty()){ //didnt check if alphanumeric, only at the xml
                    Toast.makeText(ConectUser.this, "user name should not be empty",
                            Toast.LENGTH_LONG).show();
                }
                else{
                    Intent intent =new Intent();
                    intent.putExtra("filled user name",username_typed);
                    setResult(RESULT_OK,intent);
                    finish();
                }
            }
        });
    }
}
