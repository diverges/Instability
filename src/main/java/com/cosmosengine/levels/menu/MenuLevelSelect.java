package com.cosmosengine.levels.menu;

import com.cosmosengine.CosmosEntity;
import com.cosmosengine.GameCanvas;
import com.cosmosengine.LevelLoader;
import com.cosmosengine.Reflection;
import com.cosmosengine.entities.interactive.ButtonEntity;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Level select menu for testing purposes only. (Will not be visible to normal user.)
 */
public class MenuLevelSelect extends Menu {
    private List<Class> classes;

    public MenuLevelSelect(GameCanvas game) {
        super(game);

        int gridX = 25;
        int gridY = 50;

        /*
         * Load each individual level
         */
        int rowCount = 0;
        classes = Reflection.LEVELS;
        if (classes != null)
            for (Class aClass : classes) {
                if (!aClass.getPackage().getName().contains("menu")) {
                    levelTextureObjects.add(new ButtonEntity(game, aClass.getName(), null, null, null, gridX, gridY, 100, 75, aClass.getName().replace(aClass.getPackage().getName() + ".", "")));
                    gridX += 150;
                    rowCount++;

                    // only 5 level boxes per row
                    if (rowCount == 5) {
                        gridY += 100;
                        gridX = 25;
                        rowCount = 0;
                    }
                }
            }
    }

    public void onLoad(Graphics g) {
    }

    @Override
    @SuppressWarnings("unchecked")
    public void mouseReleased(MouseEvent event) {
        super.mouseReleased(event);
        for (CosmosEntity b : levelTextureObjects) {
            if (b instanceof ButtonEntity) {
                if (((ButtonEntity) b).isInButton(event.getPoint())) {
                    for (Class c : classes) {
                        if (((ButtonEntity) b).getRef().equals(c.getName())) {
                            try {
                                Class partypes[] = new Class[1];
                                partypes[0] = GameCanvas.class;
                                Constructor ct = c.getConstructor(partypes);
                                Object arglist[] = new Object[1];
                                arglist[0] = game;
                                Object retobj = ct.newInstance(arglist);
                                game.startLevel((LevelLoader) ((LevelLoader) retobj).clone());
                            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                                e.printStackTrace();
                            }
                            break;
                        }
                    }
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent event) {

    }
}
