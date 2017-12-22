package com.cosmosengine.SpriteManager;

import com.cosmosengine.GameFrame;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.HashMap;

import javax.imageio.ImageIO;

/**
 * The loader class for retrieving cached sprites. Will add the sprite to the cache if it is not already there.
 */
public class ImageLoader {

    private static ImageLoader INSTANCE = new ImageLoader();

    public static ImageLoader get() {
        return INSTANCE;
    }

    private HashMap<String, CosmosSprite> images = new HashMap<>();

    public CosmosSprite getSprite(String ref) {
        if (images.get(ref) != null)
            return images.get(ref);
        CosmosSprite sprite = null;
        BufferedImage sourceImage = null;
        URL url = GameFrame.class.getClassLoader().getResource("assets/" + ref);
        try {
            if (url != null)
                sourceImage = ImageIO.read(url);
            else
                System.err.println("assets/" + ref + " not found.");
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (sourceImage != null) {
            sprite = new CosmosSprite(toCompatibleImage(sourceImage));
            images.put(ref, sprite);
        }
        return sprite;
    }

    private BufferedImage toCompatibleImage(BufferedImage image) {
        // obtain the current system graphical settings
        GraphicsConfiguration gfxConfig = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();

        /*
         * if image is already compatible and optimized for current system
         * settings, simply return it
         */
        if (image.getColorModel().equals(gfxConfig.getColorModel()))
            return image;

        // image is not optimized, so create a new image that is
        BufferedImage newImage = gfxConfig.createCompatibleImage(image.getWidth(), image.getHeight(), image.getTransparency());

        // get the graphics context of the new image to draw the old image on
        Graphics2D g2d = (Graphics2D) newImage.getGraphics();

        // actually draw the image and dispose of context no longer needed
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();

        // return the new optimized image
        return newImage;
    }
}
