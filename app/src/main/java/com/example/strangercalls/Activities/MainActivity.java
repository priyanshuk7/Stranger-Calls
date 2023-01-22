package com.example.strangercalls.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.strangercalls.Models.User;
import com.example.strangercalls.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    FirebaseAuth mAuth;
    FirebaseDatabase database;
    long coins=0;
    String[] permissions= new String[] { Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO};
    private int requestCode =1;
    User user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        mAuth = FirebaseAuth.getInstance();
        database= FirebaseDatabase.getInstance();

        FirebaseUser currentUser= mAuth.getCurrentUser();

        database.getReference().child("profiles")
                        .child(currentUser.getUid())
                                .addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        user= snapshot.getValue(User.class);
                                        coins = user.getCoins();

                                        binding.textView6.setText("You have: "+ coins);

                                        Glide.with(MainActivity.this)
                                                .load(user.getProfile())
                                                .into(binding.profileImage);
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });

        binding.findbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isPermissionGranted()) {
                    if (coins >= 50) {
                        coins= coins-50;
                        database.getReference().child("profiles")
                                .child(currentUser.getUid())
                                        .child("coins")
                                                .setValue(coins);

                        //Toast.makeText(MainActivity.this, "Finding", Toast.LENGTH_SHORT).show();
                        //startActivity(new Intent(MainActivity.this, ConnectingActivity.class));
                        Intent intent = new Intent(MainActivity.this, ConnectingActivity.class);
                        intent.putExtra("profiles", user.getProfile());
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "Insufficient coins", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    askPermissions();
                }
            }
        });
        binding.rewardbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RewardActivity.class));
            }
        });

        /*findViewById(R.id.findbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,ConnectingActivity.class));
            }
        });*/
    }

    void askPermissions(){
        ActivityCompat.requestPermissions(this, permissions, requestCode);
    }

    private Boolean isPermissionGranted(){
        for(String permission : permissions){
            if(ActivityCompat.checkSelfPermission(this, permission)!= PackageManager.PERMISSION_GRANTED){
                return false;
            }
        }
        return true;
    }
}