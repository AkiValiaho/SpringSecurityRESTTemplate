package com.valiaho.Domain.Builders;

import com.valiaho.Domain.Image;

/**
 * Created by akivv on 8.4.2016.
 */
public class AbstractImageObjectBuilder implements ImageBuilder {
    private String imageLocationInSystem;
    private String imageName;

    @Override
    public String getImageLocationInSystem() {
        return this.imageLocationInSystem;
    }

    @Override
    public void setImageLocationInSystem(String imageLocationInSystem) {
        this.imageLocationInSystem = imageLocationInSystem;
    }

    @Override
    public String getImageName() {
        return this.imageName;
    }

    @Override
    public void setImageName(String imageName) {
        this.imageName = imageName;

    }

    @Override
    public Image build() throws IllegalArgumentException {
        //TODO create an image builder that created random location
        //for the image if imageLocationInSystem is invalid or null
        return new Image(imageName, imageLocationInSystem);
    }
}
