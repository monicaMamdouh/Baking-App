package com.example.monica.finalprojectthree.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.monica.finalprojectthree.R;
import com.example.monica.finalprojectthree.fragments.StepsDetailFragment;

public class StepsDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps_detail);


        getSupportFragmentManager().beginTransaction()
                .add(R.id.container_Step, new StepsDetailFragment())
                .commit();
    }

}
