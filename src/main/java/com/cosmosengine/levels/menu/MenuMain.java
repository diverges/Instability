package com.cosmosengine.levels.menu;

import com.cosmosengine.CosmosConstants;
import com.cosmosengine.CosmosEntity;
import com.cosmosengine.GameCanvas;
import com.cosmosengine.SpriteManager.ImageLoader;
import com.cosmosengine.entities.interactive.ButtonEntity;
import com.cosmosengine.levels.LevelOne;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

/**
 * The menu that will start up when the game is loaded.
 */
public class MenuMain extends Menu {
    public MenuMain(GameCanvas game) {
        super(game);

        this.background = ImageLoader.get().getSprite("menumain/galaxy_menu.png");

        ButtonEntity start = new ButtonEntity(game, "start", "menumain", "start-button-idle.png;start-button-clicked.png", null, 0, 0);
        ButtonEntity quit = new ButtonEntity(game, "quit", "menumain", "quit-button-idle.png;quit-button-clicked.png", null, 0, 0);
        levelTextureObjects.add(quit);
        levelTextureObjects.add(start);

        quit.setX(CosmosConstants.WIDTH - quit.getWidth() - 20);
        quit.setY(CosmosConstants.HEIGHT - quit.getHeight() - 20);

        start.setX(quit.getX() - start.getWidth() - 20);
        start.setY(CosmosConstants.HEIGHT - start.getHeight() - 20);
    }

    public void onLoad(Graphics g) {
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        super.mouseReleased(event);
        for (CosmosEntity b : levelTextureObjects) {
            if (b instanceof ButtonEntity) {
                if (((ButtonEntity) b).isInButton(event.getPoint())) {
                    ButtonEntity button = (ButtonEntity) b;
                    if (button.getRef().equals("start")) {
                        game.startLevel(new LevelOne(game));
                        break;
                    } else if (button.getRef().equals("quit")) {
                        System.exit(0);
                        break;
                    }
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        // TODO Auto-generated method stub

    }

}
