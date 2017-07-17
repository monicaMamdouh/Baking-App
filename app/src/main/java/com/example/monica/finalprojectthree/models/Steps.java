package com.example.monica.finalprojectthree.models;

import java.io.Serializable;

/**
 * Created by monica on 6/12/2017.
 */

public class Steps implements Serializable {
    int StepID;
    String ShortDescription;
    String Description;
    String VideoURL;
    String ThumbURL;

    public Steps(int stepID, String shortDescription, String description, String videoURL, String thumbURL) {
        StepID = stepID;
        ShortDescription = shortDescription;
        Description = description;
        VideoURL = videoURL;
        ThumbURL = thumbURL;
    }

    public int getStepID() {
        return StepID;
    }


    public String getShortDescription() {
        return ShortDescription;
    }



    public String getDescription() {
        return Description;
    }


    public String getVideoURL() {
        return VideoURL;
    }


    public String getThumbURL() {
        return ThumbURL;
    }

}
