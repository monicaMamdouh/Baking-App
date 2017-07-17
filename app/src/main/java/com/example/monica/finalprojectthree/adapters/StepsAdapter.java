package com.example.monica.finalprojectthree.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.monica.finalprojectthree.R;
import com.example.monica.finalprojectthree.models.Steps;

import java.util.ArrayList;

/**
 * Created by monica on 6/12/2017.
 */

public class StepsAdapter  extends RecyclerView.Adapter<StepsAdapter.StepsAdapterViewHolder>{

    private StepsAdapter.StepAdapterOnClickHandler mClickHandler;
    int Position ;
    private ArrayList<Steps> mSteps;
    private Context mContex;


    public interface StepAdapterOnClickHandler {
        void onClick(Steps steps);
    }

    public StepsAdapter(Context context,StepsAdapter.StepAdapterOnClickHandler mClickHandler, ArrayList<Steps> stepsArrayList) {
        this.mClickHandler = mClickHandler;
        this.mContex=context;
        this.mSteps=stepsArrayList;
    }

    public class StepsAdapterViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {


        public TextView mStepsDetailTextView;


        public StepsAdapterViewHolder(View view) {
            super(view);
            mStepsDetailTextView = (TextView) view.findViewById(R.id.recipe_Steps);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            Position = getAdapterPosition();
            Steps steps=mSteps.get(Position);
            mClickHandler.onClick(steps);
        }
    }

    @Override
    public StepsAdapter.StepsAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.recycle_view_steps_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        return new StepsAdapter.StepsAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StepsAdapter.StepsAdapterViewHolder holder, int position) {
        Steps steps=mSteps.get(position);
        holder.mStepsDetailTextView.setText("Step "+steps.getStepID()+" : "+steps.getShortDescription());

    }


    @Override
    public int getItemCount() {
        if (null == mSteps)
            return 0;
        return mSteps.size();
    }



}