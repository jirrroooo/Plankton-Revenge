package com.game.plankton_revenge;

import javafx.scene.image.Image;

import java.net.URL;
import java.util.LinkedHashSet;
import java.util.Set;

final class ResourceLoader {
    private static final String IMAGE_PATH = "/com/game/plankton_revenge/images/";

    private ResourceLoader() {
    }

    static Image image(String fileName) {
        return new Image(imageUrl(fileName));
    }

    static Image image(String fileName, double requestedWidth, double requestedHeight, boolean preserveRatio, boolean smooth) {
        return new Image(imageUrl(fileName), requestedWidth, requestedHeight, preserveRatio, smooth);
    }

    private static String imageUrl(String fileName) {
        for (String candidate : candidates(fileName)) {
            URL resource = ResourceLoader.class.getResource(IMAGE_PATH + candidate);
            if (resource != null) {
                return resource.toExternalForm();
            }
        }
        throw new IllegalArgumentException("Missing image resource: " + fileName);
    }

    private static Set<String> candidates(String fileName) {
        Set<String> names = new LinkedHashSet<>();
        names.add(fileName);

        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > -1) {
            String baseName = fileName.substring(0, dotIndex + 1);
            String extension = fileName.substring(dotIndex + 1);
            names.add(baseName + extension.toUpperCase());
            names.add(baseName + extension.toLowerCase());
        }

        return names;
    }
}
