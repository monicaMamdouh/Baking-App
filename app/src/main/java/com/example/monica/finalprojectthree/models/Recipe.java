package com.example.monica.finalprojectthree.models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by monica on 6/12/2017.
 */

public class Recipe implements Serializable {


    private int recipeID;
    private String name;
    private int Serve;
    private ArrayList<Ingredients> ingredientsArrayList;
    private ArrayList<Steps> stepsArrayList;
    private String poster;



    public ArrayList<Steps> getStepsArrayList() {
        return stepsArrayList;
    }


    public String getPoster() {
        return poster;
    }


    public int getRecipeID() {
        return recipeID;
    }


    public Recipe(int recipeID, String name, int serve, ArrayList<Ingredients> ingredientsArrayList, ArrayList<Steps> stepsArrayList, String poster) {
        this.recipeID = recipeID;
        this.name = name;
        Serve = serve;
        this.ingredientsArrayList = ingredientsArrayList;
        this.stepsArrayList = stepsArrayList;
        this.poster = poster;
    }

    public void setRecipeID(int recipeID) {
        this.recipeID = recipeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getServe() {
        return Serve;
    }

    public void setServe(int serve) {
        Serve = serve;
    }

    public ArrayList<Ingredients> getIngredientsArrayList() {
        return ingredientsArrayList;
    }

    public void setIngredientsArrayList(ArrayList<Ingredients> ingredientsArrayList) {
        this.ingredientsArrayList = ingredientsArrayList;
    }

    public void setStepsArrayList(ArrayList<Steps> stepsArrayList) {
        this.stepsArrayList = stepsArrayList;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
}