package com.example.monica.finalprojectthree.fragments;



import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.monica.finalprojectthree.R;
import com.example.monica.finalprojectthree.activities.RecipesActivity;
import com.example.monica.finalprojectthree.activities.StepsDetailActivity;
import com.example.monica.finalprojectthree.adapters.IngredientMainAdapter;
import com.example.monica.finalprojectthree.adapters.StepsAdapter;
import com.example.monica.finalprojectthree.dataBase.IngredientContract;
import com.example.monica.finalprojectthree.models.Ingredients;
import com.example.monica.finalprojectthree.models.Recipe;
import com.example.monica.finalprojectthree.models.Steps;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeDetailFragment extends Fragment implements StepsAdapter.StepAdapterOnClickHandler {


    public static final String EXTRA_CURSOR_POSITION ="CursorPosition";
    private boolean mTwoPane;
    private RecyclerView mIngredientRecyclerView;
    private IngredientMainAdapter ingredientAdapter;

    private RecyclerView mStepsRecyclerView;
    private StepsAdapter stepsAdapter;
    private static final int LOADER_ID = 1;

    private TextView mNameTextView;
    private Cursor cursor;
    private Cursor widgetCursor;
    private int positionCursor;
    private ArrayList<Ingredients> ingredientarrayList;
    private ArrayList<Ingredients> ingredientsList;

    int mRecipeId;


    public RecipeDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recipe_detail, container, false);

        final Intent intent = getActivity().getIntent();
        Bundle bundle = intent.getExtras();
        mNameTextView = (TextView) rootView.findViewById(R.id.name);
        mIngredientRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view_ingredients);
        mIngredientRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));



        Recipe recipe =
                (Recipe) bundle.getSerializable("RecipeObject");
        if(recipe!=null) {

            mNameTextView.setText(recipe.getName());

            mRecipeId = recipe.getRecipeID();

            //ingredient
            ingredientarrayList = recipe.getIngredientsArrayList();
            ingredientAdapter = new IngredientMainAdapter(getContext(), ingredientarrayList);
            mIngredientRecyclerView.setAdapter(ingredientAdapter);

            //Steps
            ArrayList<Steps> stepsArrayList = recipe.getStepsArrayList();

            mStepsRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view_Steps);
            mStepsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            stepsAdapter = new StepsAdapter(getContext(), this, stepsArrayList);
            mStepsRecyclerView.setAdapter(stepsAdapter);
        }
        else
        {

            positionCursor=getActivity().getIntent().getIntExtra(EXTRA_CURSOR_POSITION,0);
            try {

             cursor = getActivity().getContentResolver().query(IngredientContract.IngredientEntry.CONTENT_URI,
                        null,
                        null,
                        null,
                        null);

            } catch (Exception e) {
                e.printStackTrace();
            }


            cursor.moveToPosition(positionCursor);
            int index=cursor.getColumnIndex(IngredientContract.IngredientEntry.COLUMN_INGREDIENTS_RECIPE_NAME);
            int idIndex = cursor.getColumnIndex(IngredientContract.IngredientEntry.COLUMN_INGREDIENTS_RECIPE_ID);
            int ingredientIndex = cursor.getColumnIndex(IngredientContract.IngredientEntry.COLUMN_INGREDIENTS_INGREDIENT);
            int quantityIndex = cursor.getColumnIndex(IngredientContract.IngredientEntry.COLUMN_INGREDIENTS_QUANTITY);
            int measureIndex = cursor.getColumnIndex(IngredientContract.IngredientEntry.COLUMN_INGREDIENTS_MEASURE);
            String name = cursor.getString(index);
            int id = cursor.getInt(idIndex);
            String quantity = cursor.getString(quantityIndex);
            String measure = cursor.getString(measureIndex);
            String ingredient = cursor.getString(ingredientIndex);



            ingredientsList=new ArrayList<>();

            String[] mesureArr = measure.split(",");
            String[] ingredientArr=ingredient.split(",");
            String[] quantityArr=quantity.split(",");


            for(int i=0;i<mesureArr.length;i++)
            {


                ingredientsList.add(i,new Ingredients(quantityArr[i],ingredientArr[i],mesureArr[i]));

            }



            ingredientAdapter = new IngredientMainAdapter(getContext(), ingredientsList);
            mIngredientRecyclerView.setAdapter(ingredientAdapter);
            mNameTextView.setText(name);

        }

        return rootView;
    }



    public void isTwoPane(boolean twoPane) {
        mTwoPane=twoPane;
    }

    @Override
    public void onClick(Steps steps) {

          if(mTwoPane==true) {
              StepsDetailFragment stepsDetailsFragment = new StepsDetailFragment();
              stepsDetailsFragment.setStepIndex(steps);
              getActivity().getSupportFragmentManager().beginTransaction()
                      .replace(R.id.container_Step, stepsDetailsFragment)
                      .commit();
          }
          else
          {
              Intent intent = new Intent(getActivity(), StepsDetailActivity.class);
              Bundle bundle = new Bundle();
              bundle.putSerializable("StepObject", steps);
              intent.putExtras(bundle);
              startActivity(intent);
          }



    }


}
