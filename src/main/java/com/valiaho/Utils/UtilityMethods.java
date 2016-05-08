package com.valiaho.Utils;

import com.valiaho.Domain.Builders.PersistImageObjectBuilder;
import com.valiaho.Domain.Image;
import com.valiaho.Service.ImageBuilderFactory;

public class UtilityMethods {
    public static Image buildRandomImageAsRaster() {
        final Image build = getImageFromFactory();
        return build;
    }

    private static Image getImageFromFactory() {
        ImageBuilderFactory imageBuilderFactory = new ImageBuilderFactory();
        final PersistImageObjectBuilder persistImageObjectBuilder = useImageBuilderFactoryToBuildImage(
                imageBuilderFactory, "tmpImage.jpg", "/home/akivv/images.png");
        final Image build = persistImageObjectBuilder.build();
        return build;
    }

    private static PersistImageObjectBuilder useImageBuilderFactoryToBuildImage(ImageBuilderFactory imageBuilderFactory,
                                                                                String imageName, String imageLocation) {
        final PersistImageObjectBuilder persistImageObjectBuilder = imageBuilderFactory
                .buildPersistImageObjectBuilder();
        setImageInfoToBuilder(imageName, imageLocation, persistImageObjectBuilder);
        return persistImageObjectBuilder;
    }

    private static void setImageInfoToBuilder(String imageName, String imageLocation,
                                              final PersistImageObjectBuilder persistImageObjectBuilder) {
        persistImageObjectBuilder.setImageName(imageName);
        persistImageObjectBuilder.setImageLocationInSystem(imageLocation);
    }
}