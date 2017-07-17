package com.example.monica.finalprojectthree.loaders;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;


import static com.example.monica.finalprojectthree.dataBase.IngredientContract.BASE_CONTENT_URI;
import static com.example.monica.finalprojectthree.dataBase.IngredientContract.PATH_INGREDIENTS;


/**
 * Created by monica on 6/13/2017.
 */

public class IngredientLoader implements LoaderManager.LoaderCallbacks<Cursor> {

    private Context mContext;
    private onDeliver deliver;
    private int recipeId;


    public IngredientLoader( onDeliver onDeliver,Context context, int mRecipeId) {


        this.recipeId=mRecipeId;
        this.deliver=onDeliver;
        this.mContext = context;
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return  new AsyncTaskLoader<Cursor>(mContext) {
            Cursor mData = null;

            @Override
            protected void onStartLoading() {
                if (mData != null) {
                    deliverResult(mData);
                } else {
                    forceLoad();
                }
            }

            @Override
            public Cursor loadInBackground() {

                try {
                    Uri recipeURI = ContentUris.withAppendedId(
                            BASE_CONTENT_URI.buildUpon().appendPath(PATH_INGREDIENTS).build(), recipeId);
                    return mContext.getContentResolver().query(recipeURI,
                            null,
                            null,
                            null,
                            null);

                } catch (Exception e) {
                    return null;
                }
            }

            public void deliverResult(Cursor data) {
                mData = data;
                super.deliverResult(data);
            }

        };

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        deliver.onDataDeliver(data);


    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

        deliver.onDataDeliver(null);

    }



    public interface onDeliver {
        void  onDataDeliver(Cursor cursor);
    }
}



