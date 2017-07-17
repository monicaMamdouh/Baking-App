package com.example.monica.finalprojectthree.loaders;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;

import com.example.monica.finalprojectthree.models.Recipe;
import com.example.monica.finalprojectthree.utility.NetworkFunctions;

import java.util.List;

/**
 * Created by monica on 6/12/2017.
 */

public class RecipeLoader  implements LoaderManager.LoaderCallbacks<List<Recipe>> {

    private onRecipesDeliver recipesDeliver;
    private Context mContext;
    private List<Recipe> recipesList;


    public RecipeLoader(onRecipesDeliver recipesDeliver , Context context) {

        this.recipesDeliver=recipesDeliver;
        this.mContext = context;
    }


    @Override
    public Loader<List<Recipe>> onCreateLoader(int id, Bundle args) {
        return new AsyncTaskLoader<List<Recipe>>(mContext) {
            @Override
            public List<Recipe> loadInBackground() {
                try {
                    NetworkFunctions networkFunctions = new NetworkFunctions(getContext());
                    recipesList = networkFunctions.recipeNetwork();
                    return recipesList;
                }
                catch (Exception e)
                {
                    return null;
                }
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<List<Recipe>> loader, List<Recipe> data) {

        recipesDeliver.onRecipesDeliver(data);
    }

    @Override
    public void onLoaderReset(Loader<List<Recipe>> loader) {


        recipesDeliver.onRecipesDeliver(null);
    }

    public interface onRecipesDeliver {
        void  onRecipesDeliver(List<Recipe> recipes);

    }

}


