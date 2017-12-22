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
    ArrayList<CosmosEntity> levelTextureObjects = new ArrayList<>();
    CosmosSprite background;
    public CosmosSound sound;

    Menu(GameCanvas game) {
        this.game = game;
        sound = SoundLoader.get().getSound("menu/melospace.wav");
        if (sound != null)
            sound.playSound();
    }

    @Override
    public void mouseEntered(MouseEvent event) {
    }

    @Override
    public void mouseExited(MouseEvent event) {
    }

    @Override
    public void mouseMoved(MouseEvent event) {
    }

    @Override
    public void mousePressed(MouseEvent event) {
        for (CosmosEntity b : levelTextureObjects) {
            if (b instanceof ButtonEntity) {
                ButtonEntity button = (ButtonEntity) b;
                if ((button.isInButton(event.getPoint()))) {
                    button.pushDown();
                }
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent event) {
    }

    @Override
    public void mouseReleased(MouseEvent event) {
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
