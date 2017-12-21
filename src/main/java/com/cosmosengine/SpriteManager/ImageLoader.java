package com.cosmosengine.SpriteManager;

import com.cosmosengine.GameFrame;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.HashMap;

import javax.imageio.ImageIO;

/**
 * The loader class for retrieving cached sprites. Will add the sprite to the cache if it is not already there.
 */
public class ImageLoader {

    private static ImageLoader single = new ImageLoader();

    public static ImageLoader get() {
        return single;
    }

    private HashMap<String, CosmosSprite> images = new HashMap<String, CosmosSprite>();

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

        GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
        if (url != null) {
            Image img = gc.createCompatibleImage(sourceImage.getWidth(), sourceImage.getHeight(), Transparency.BITMASK);

            img.getGraphics().drawImage(sourceImage, 0, 0, null);

            sprite = new CosmosSprite(img);
            images.put(ref, sprite);
        }
        return sprite;
    }
}
