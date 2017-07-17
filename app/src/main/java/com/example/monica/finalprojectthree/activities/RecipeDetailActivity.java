package com.example.monica.finalprojectthree.activities;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.monica.finalprojectthree.R;
import com.example.monica.finalprojectthree.dataBase.IngredientContract;
import com.example.monica.finalprojectthree.fragments.RecipeDetailFragment;
import com.example.monica.finalprojectthree.models.Ingredients;
import com.example.monica.finalprojectthree.models.Recipe;
import com.example.monica.finalprojectthree.widget.BakingWidgetProvider;

import java.util.ArrayList;

public class RecipeDetailActivity extends AppCompatActivity  {

    private boolean mTwoPane;
    public static final String EXTRA_CURSOR_POSITION ="CursorPosition";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        if (findViewById(R.id.container_Step) != null) {
            mTwoPane = true;
        } else {
            mTwoPane = false;
        }





        RecipeDetailFragment recipeDetailFragment = new RecipeDetailFragment();
        recipeDetailFragment.isTwoPane(mTwoPane);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container_Ingred, recipeDetailFragment)
                .commit();
    }}
