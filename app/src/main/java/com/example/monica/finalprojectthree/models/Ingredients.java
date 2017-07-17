package com.example.monica.finalprojectthree.models;

import java.io.Serializable;

/**
 * Created by monica on 6/12/2017.
 */

public class Ingredients  implements Serializable {

    private int recipeID;
    private String recipeName;
    private String quantity;
    private String ingredient ;
    private String measure;

    public Ingredients() {
    }

    public Ingredients(int recipeID, String recipeName, String quantity, String ingredient, String measure) {
        this.recipeID = recipeID;
        this.recipeName = recipeName;
        this.quantity = quantity;
        this.ingredient = ingredient;
        this.measure = measure;
    }

    public Ingredients(String quantity, String ingredient, String measure) {
        this.quantity = quantity;
        this.ingredient = ingredient;
        this.measure = measure;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeID(int recipeID) {
        this.recipeID = recipeID;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public int getRecipeID() {
        return recipeID;
    }

    public String getQuantity() {
        return quantity;
    }


    public String getIngredient() {
        return ingredient;
    }


    public String getMeasure() {
        return measure;
    }

}
