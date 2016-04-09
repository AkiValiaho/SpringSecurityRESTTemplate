package com.valiaho.Domain.Builders;

import com.valiaho.Domain.Image;

/**
 * Created by akivv on 8.4.2016.
 */
public interface ImageBuilder {
    String getImageLocationInSystem();

    void setImageLocationInSystem(String imageLocationInSystem);

    String getImageName();

    void setImageName(String imageName);

    Image build() throws IllegalArgumentException;
}
