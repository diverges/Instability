package com.cosmosengine.levels.menu;

import com.cosmosengine.CosmosEntity;
import com.cosmosengine.GameCanvas;
import com.cosmosengine.SoundManager.CosmosSound;
import com.cosmosengine.SoundManager.SoundLoader;
import com.cosmosengine.SpriteManager.CosmosSprite;
import com.cosmosengine.entities.interactive.ButtonEntity;
import com.cosmosengine.interfaces.Clickable;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Parent of all menu classes, contains the click listener for CosmosButtons
 */
public abstract class Menu implements Clickable {
    GameCanvas game;
    protected ArrayList<CosmosEntity> levelTextureObjects = new ArrayList<CosmosEntity>();
    protected CosmosSprite background;
    public CosmosSound sound;

    public Menu(GameCanvas game) {
        this.game = game;
        sound = SoundLoader.get().getSound("menu/melospace.wav");
        if (sound != null)
            sound.playSound();
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        for (CosmosEntity b : levelTextureObjects) {
            if (b instanceof ButtonEntity) {
                ButtonEntity button = (ButtonEntity) b;
                if ((button.isInButton(e.getPoint()))) {
                    button.pushDown();
                }
            }
        }
    }

    public void mouseDragged(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
        for (CosmosEntity b : levelTextureObjects) {
            if (b instanceof ButtonEntity) {
                ButtonEntity button = (ButtonEntity) b;
                button.pullUp();
            }
        }
    }

    public void draw(Graphics g) {
        if (background != null)
            background.draw(g, 0, 0);
        for (CosmosEntity b : levelTextureObjects)
            b.draw(g);
    }
}
