package com.example.monica.finalprojectthree.utility;

import android.content.Context;
import android.net.Uri;

import com.example.monica.finalprojectthree.R;
import com.example.monica.finalprojectthree.models.Ingredients;
import com.example.monica.finalprojectthree.models.Recipe;
import com.example.monica.finalprojectthree.models.Steps;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by monica on 6/12/2017.
 */

public class NetworkFunctions {

    Context context;
    public NetworkFunctions(Context context)
    {

        this.context=context;
    }

    private ArrayList<Recipe> recipes;
    public ArrayList<Recipe> recipeNetwork()
    {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        String RecipeJsonStr = null;

        try {


            Uri BuildURI=Uri.parse(context.getResources().getString(R.string.JsonURL));
            URL url = new URL(BuildURI.toString());

            //Log.v(LOG_TAG, "Built URI " + builtUri.toString());

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {

                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                return null;
            }
            RecipeJsonStr = buffer.toString();

        } catch (IOException e) {
            // Log.e(LOG_TAG, "Error ", e);
            return null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    // Log.e(LOG_TAG, "Error closing stream", e);
                }
            }
        }

        try {
            return getRecipesFromJson(RecipeJsonStr);
        } catch (JSONException e) {
            // Log.e(LOG_TAG, e.getMessage(), e);
            e.printStackTrace();
        }

        return null;

    }
    public ArrayList<Recipe> getRecipesFromJson(String JsonStr) throws JSONException {


        final String Name = "name";
        final String id="id";
        final String Serving="servings";
        final String Ingredients="ingredients";
        final String Image="image";

        //Ingredients
        final String Ingreds="ingredients";
        final String Quantity="quantity";
        final String Measure="measure";
        final String Ingred="ingredient";

        //Steps
        final String Steps="steps";
        final String StepID="id";
        final String ShortDescription="shortDescription";
        final String Description="description";
        final String VideoURL="videoURL";
        final String ThumbURL="thumbnailURL";


        recipes= new ArrayList<>();

        try {

            JSONArray recipeArray = new JSONArray(JsonStr);
            for (int i = 0; i < recipeArray.length(); i++) {
                JSONObject currentRecipe = recipeArray.getJSONObject(i);
                int idJson=currentRecipe.getInt(id);
                String nameJson = currentRecipe.getString(Name);
                int servingJson=currentRecipe.getInt(Serving);
                String ImageJson=currentRecipe.getString(Image);
                //Ingredients Stream
                JSONArray ingredientsArray= currentRecipe.getJSONArray(Ingredients);
                ArrayList<com.example.monica.finalprojectthree.models.Ingredients> ingredientsArrayList=new ArrayList<>();

                for (int j = 0; j < ingredientsArray.length(); j++) {
                    JSONObject currentIng = ingredientsArray.getJSONObject(j);
                    String quantityJson = String.valueOf(currentIng.getDouble(Quantity));
                    String measureJson = currentIng.getString(Measure);
                    String ingredJson = currentIng.getString(Ingred);
                    Ingredients ingredients = new Ingredients(idJson,nameJson,quantityJson, ingredJson,measureJson);
                    ingredientsArrayList.add(ingredients);

                }
                //Steps Stream
                JSONArray StepsArray= currentRecipe.getJSONArray(Steps);
                ArrayList<Steps> StepsArrayList=new ArrayList<>();

                for (int k = 0; k < StepsArray.length(); k++) {
                    JSONObject currentStep = StepsArray.getJSONObject(k);
                    int stepsIDJson = currentStep.getInt(StepID);
                    String ShortDescriptionJson = currentStep.getString(ShortDescription);
                    String descriptionJson = currentStep.getString(Description);
                    String VideoURLJson = currentStep.getString(VideoURL);
                    String ThumbURLJson = currentStep.getString(ThumbURL);

                    com.example.monica.finalprojectthree.models.Steps Step=new Steps(stepsIDJson,ShortDescriptionJson,descriptionJson,VideoURLJson,ThumbURLJson);
                    StepsArrayList.add(Step);
                }


                Recipe recipe=new Recipe(idJson,nameJson,servingJson,ingredientsArrayList,StepsArrayList,ImageJson);

                recipes.add(recipe);
            }

        } catch (JSONException e) {
            e.printStackTrace();

        }
        return recipes;
    }



}


