package com.example.monica.finalprojectthree.adapters;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.monica.finalprojectthree.R;
import com.example.monica.finalprojectthree.dataBase.IngredientContract;
import com.example.monica.finalprojectthree.models.Ingredients;
import com.example.monica.finalprojectthree.models.Recipe;
import com.example.monica.finalprojectthree.widget.BakingWidgetProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by monica on 6/12/2017.
 */

public class RecipesAdapter  extends RecyclerView.Adapter<RecipesAdapter.RecipesAdapterViewHolder>{

    private RecipeAdapterOnClickHandler mClickHandler;
    int Position ;
    private List<Recipe> mRecipes;
    private Context mContext;
    private ArrayList<Ingredients> ingredientsArrayList;


    public interface RecipeAdapterOnClickHandler {
        void onClick(Recipe recipe);
    }

    public RecipesAdapter(Context context,RecipeAdapterOnClickHandler mClickHandler, ArrayList<Recipe> recipes) {
        this.mClickHandler = mClickHandler;
        this.mContext=context;
        this.mRecipes=recipes;
    }

    public class RecipesAdapterViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {


        public TextView mRecipeIDTextView;
        public TextView mRecipeServingTextView;
        public ImageView mPosterImageView;
        public FloatingActionButton mFloatingActionButton;



        public RecipesAdapterViewHolder(View view) {
            super(view);
            mRecipeIDTextView = (TextView) view.findViewById(R.id.recipe_name);
            mRecipeServingTextView=(TextView) view.findViewById(R.id.recipe_serving);
            mPosterImageView=(ImageView) view.findViewById(R.id.recipe_image);
            mFloatingActionButton=(FloatingActionButton)view.findViewById(R.id.fab_button);

            mPosterImageView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            Position = getAdapterPosition();
            Recipe recipe=mRecipes.get(Position);
            mClickHandler.onClick(recipe);
        }
    }

    @Override
    public RecipesAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.recycle_view_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        return new RecipesAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecipesAdapter.RecipesAdapterViewHolder holder, final int position) {
        final Recipe recipe =mRecipes.get(position);
        holder.mRecipeIDTextView.setText(recipe.getName());
        holder.mRecipeServingTextView.setText(String.valueOf(recipe.getServe()));
        if(!recipe.getPoster().equals(""))
        Glide.with(mContext).load(recipe.getPoster()).into(holder.mPosterImageView);

        ingredientsArrayList=recipe.getIngredientsArrayList();
        holder.mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try {
                    //Ingredients
                    ArrayList<Ingredients> ingredientsArrayList = recipe.getIngredientsArrayList();
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(IngredientContract.IngredientEntry.COLUMN_INGREDIENTS_RECIPE_ID, recipe.getRecipeID());
                    contentValues.put(IngredientContract.IngredientEntry.COLUMN_INGREDIENTS_RECIPE_NAME, recipe.getName());

                    String strQuantity = "";
                    String strMeasure = "";
                    String strIngredient = "";
                    for (int i = 0;i<ingredientsArrayList.size(); i++) {
                        Ingredients ingredients = ingredientsArrayList.get(i);
                        strQuantity = ingredients.getQuantity().trim()+","+strQuantity;
                        strMeasure=ingredients.getMeasure().trim()+","+strMeasure;
                        strIngredient=ingredients.getIngredient().trim()+","+strIngredient;

                    }
                    contentValues.put(IngredientContract.IngredientEntry.COLUMN_INGREDIENTS_QUANTITY, strQuantity);
                    contentValues.put(IngredientContract.IngredientEntry.COLUMN_INGREDIENTS_INGREDIENT, strIngredient);
                    contentValues.put(IngredientContract.IngredientEntry.COLUMN_INGREDIENTS_MEASURE, strMeasure);
                    mContext.getContentResolver().insert(IngredientContract.IngredientEntry.CONTENT_URI, contentValues);

                    AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(mContext);
                    ComponentName thisAppWidget = new ComponentName(mContext.getPackageName(), BakingWidgetProvider.class.getName());
                    int[] appWidgetIds = appWidgetManager.getAppWidgetIds(thisAppWidget);
                    appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.widget_grid_view);

                    Toast.makeText(mContext,"You Successfully add this to wigdet ;)",Toast.LENGTH_SHORT).show();
                }
                catch (Exception e)
                {}

            }







        });

    }


    @Override
    public int getItemCount() {
        if (null == mRecipes)
            return 0;
        return mRecipes.size();
    }




}
