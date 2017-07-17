package com.example.monica.finalprojectthree.WidgetService;


import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;


import com.example.monica.finalprojectthree.R;
import com.example.monica.finalprojectthree.activities.RecipeDetailActivity;
import com.example.monica.finalprojectthree.dataBase.IngredientContract;
import com.example.monica.finalprojectthree.models.Recipe;

import static com.example.monica.finalprojectthree.dataBase.IngredientContract.BASE_CONTENT_URI;
import static com.example.monica.finalprojectthree.dataBase.IngredientContract.PATH_INGREDIENTS;

/**
 * Created by monica on 6/12/2017.
 */

public class ListWidgetService  extends RemoteViewsService {

  @Override
  public RemoteViewsFactory onGetViewFactory(Intent intent) {

      return new ListRemoteViewsFactory(this.getApplicationContext());

}


 class ListRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

     Context mContext;
     Cursor mCursor;


     public ListRemoteViewsFactory(Context applicationContext) {
         mContext = applicationContext;

     }

     @Override
     public void onCreate() {

     }

     //called on start and when notifyAppWidgetViewDataChanged is called
     @Override
     public void onDataSetChanged() {

         mCursor = mContext.getContentResolver().query(
                 IngredientContract.IngredientEntry.CONTENT_URI,
                 null,
                 null,
                 null,
                 null
         );
     }
     @Override
     public void onDestroy() {
         mCursor.close();
     }

     @Override
     public int getCount() {
         if (mCursor == null) return 0;
         return mCursor.getCount();
     }

     @Override
     public RemoteViews getViewAt(int position) {
         if (mCursor == null || mCursor.getCount() == 0)
             return null;


         mCursor.moveToPosition(position);

         int recipeIdIndex=mCursor.getColumnIndex(IngredientContract.IngredientEntry.COLUMN_INGREDIENTS_RECIPE_ID);
         int recipeId=mCursor.getInt(recipeIdIndex);
         int recipeNameIndex=mCursor.getColumnIndex(IngredientContract.IngredientEntry.COLUMN_INGREDIENTS_RECIPE_NAME);
         String recipeName=mCursor.getString(recipeNameIndex);
         RemoteViews views = new RemoteViews(mContext.getPackageName(), R.layout.widget_list_item);

         views.setTextViewText(R.id.widget_Recipe_name,recipeName);
         views.setImageViewResource(R.id.widget_Recipe_image,R.drawable.widget_icon);

         Bundle extras = new Bundle();
         extras.putInt(RecipeDetailActivity.EXTRA_CURSOR_POSITION,position);
         Intent fillInIntent = new Intent();
         fillInIntent.putExtras(extras);
         views.setOnClickFillInIntent(R.id.widget_Recipe_image, fillInIntent);

         return views;

     }

     @Override
     public RemoteViews getLoadingView() {

         return null;
     }

     @Override
     public int getViewTypeCount() {
         return 1;
     }
     @Override
     public long getItemId(int i) {

         return i;
     }

     @Override
     public boolean hasStableIds() {
         return true;
     }
 }
}
