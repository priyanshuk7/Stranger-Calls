package com.example.strangercalls.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.strangercalls.R;
import com.google.firebase.auth.FirebaseAuth;

public class WelcomeActivity extends AppCompatActivity {
Button button;
FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        auth = FirebaseAuth.getInstance();

        if(auth.getCurrentUser()!=null){
            goToNextActivity();
        }

    }
    public void openLoginActivity(View view){
        goToNextActivity();
    }
    void goToNextActivity(){
        startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
        finish();
    }

}