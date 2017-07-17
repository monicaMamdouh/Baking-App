package com.example.monica.finalprojectthree.fragments;


import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.monica.finalprojectthree.R;
import com.example.monica.finalprojectthree.activities.RecipeDetailActivity;
import com.example.monica.finalprojectthree.adapters.RecipesAdapter;
import com.example.monica.finalprojectthree.loaders.RecipeLoader;
import com.example.monica.finalprojectthree.models.Recipe;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecipesFragment extends Fragment implements RecipesAdapter.RecipeAdapterOnClickHandler,RecipeLoader.onRecipesDeliver {

    private RecyclerView mRecyclerView;
    private RecipesAdapter recipesAdapter;
    private static final int RECIPE_LOADER_ID = 0;


    public RecipesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_recipes, container, false);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);


        getLoaderManager().initLoader(RECIPE_LOADER_ID, null, new RecipeLoader(this,getContext())).forceLoad();

        return rootView;

    }

    public void loadAdapterWithData(ArrayList<Recipe> recipes) {


        if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        } else if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {

            mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        } else {

            mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 4));

        }
        recipesAdapter = new RecipesAdapter(getContext(), this, recipes);
        mRecyclerView.setAdapter(recipesAdapter);



    }

    @Override
    public void onClick(Recipe recipe) {
        Intent intent = new Intent(getActivity(), RecipeDetailActivity.class);

        Bundle bundle = new Bundle();
        bundle.putSerializable("RecipeObject", recipe);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void onRecipesDeliver(List<Recipe> recipes) {
        if(recipes!=null) {
            loadAdapterWithData((ArrayList) recipes);
        }
    }

}
