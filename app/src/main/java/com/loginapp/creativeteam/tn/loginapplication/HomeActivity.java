package com.loginapp.creativeteam.tn.loginapplication;

import android.app.ProgressDialog;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.loginapp.creativeteam.tn.loginapplication.firebase_login_signup.SignUpActivity;
import com.parse.ParseUser;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    TextView tvName, tvEmail;
    Button nav_button;
    Button ques;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        final ParseUser currentUser = ParseUser.getCurrentUser();

        tvName = findViewById(R.id.tvName);
        tvEmail = findViewById(R.id.tvEmail);
        ques=findViewById(R.id.button_ques);
        if(currentUser!=null){
            tvName.setText(currentUser.getString("name"));
            tvEmail.setText(currentUser.getEmail());
        }
        nav_button=findViewById(R.id.nav_button);
        nav_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, prev_query
                        .class);
                startActivity(intent);
                finish();
            }
        });
        ques.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Intent intent = new Intent(HomeActivity.this, SignUpActivity.class);
               // startActivity(intent);

                // Call to destroy an activity
                Intent myIntent = new Intent(HomeActivity.this, SignUpActivity.class);
                myIntent.putExtra("email",currentUser.getEmail());
                myIntent.putExtra("pass",currentUser.getObjectId());
                myIntent.putExtra("name",currentUser.getString("name"));
                startActivity(myIntent);
                finish();
            }
        });
    }

    public void logout(View view) {
        ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage("Loading ...");
        progress.show();
        ParseUser.logOut();
        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
        progress.dismiss();
    }
}
