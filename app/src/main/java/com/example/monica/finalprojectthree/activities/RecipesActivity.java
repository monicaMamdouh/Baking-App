package com.example.monica.finalprojectthree.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.monica.finalprojectthree.R;
import com.example.monica.finalprojectthree.fragments.RecipesFragment;

public class RecipesActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new RecipesFragment())
                .commit();


    }

}
