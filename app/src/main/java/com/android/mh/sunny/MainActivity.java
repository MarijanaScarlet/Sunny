 package com.android.mh.sunny;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.android.mh.sunny.database.Database;
import com.android.mh.sunny.database.entitets.User;

 public class MainActivity extends AppCompatActivity {


    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = Database.getInstance(getApplicationContext());

    }


     public void login(View view) {
         String username = ((EditText)findViewById(R.id.username)).getText().toString();
         String password = ((EditText)findViewById(R.id.password)).getText().toString();

         User user = db.userDao().findUser(username, password);
         if(user != null)
             startActivity(new Intent(this, WeatherActivity.class));
         else
             startActivity(new Intent(this, ErrorActivity.class));
     }
 }
