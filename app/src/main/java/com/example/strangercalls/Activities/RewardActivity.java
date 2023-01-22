package com.example.strangercalls.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.strangercalls.R;
import com.example.strangercalls.databinding.ActivityRewardBinding;

public class RewardActivity extends AppCompatActivity {
    
    ActivityRewardBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reward);
       /* binding= ActivityRewardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());*/

    }
}