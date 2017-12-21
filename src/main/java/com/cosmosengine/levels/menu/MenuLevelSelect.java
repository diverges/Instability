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
@SuppressWarnings({
        "deprecation", "rawtypes", "unchecked"
})
public class MenuLevelSelect extends Menu {
    List<Class> classes = null;

    public MenuLevelSelect(GameCanvas game) {
        super(game);

        int grid_x = 25;
        int grid_y = 50;

		/*
         * Load each individual level
		 */
        int rowCount = 0;
        classes = Reflection.LEVELS;
        if (classes != null)
            for (int i = 0; i < classes.size(); i++) {
                if (!classes.get(i).getPackage().getName().contains("menu")) {
                    levelTextureObjects.add(new ButtonEntity(game, classes.get(i).getName(), null, null, null, grid_x, grid_y, 100, 75, classes.get(i)
                                                                                                                                               .getName()
                                                                                                                                               .replace(classes.get(i)
                                                                                                                                                               .getPackage()
                                                                                                                                                               .getName() + ".", "")));
                    grid_x += 150;
                    rowCount++;

                    // only 5 level boxes per row
                    if (rowCount == 5) {
                        grid_y += 100;
                        grid_x = 25;
                        rowCount = 0;
                    }
                }
            }
    }

    public void onLoad(Graphics g) {
    }

    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        try {
            for (CosmosEntity b : levelTextureObjects) {
                if (b instanceof ButtonEntity) {
                    if (((ButtonEntity) b).isInButton(e.getPoint())) {
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
                                } catch (CloneNotSupportedException e1) {
                                    e1.printStackTrace();
                                } catch (IllegalArgumentException e2) {

                                    e2.printStackTrace();
                                } catch (InstantiationException e3) {
                                    e3.printStackTrace();
                                } catch (IllegalAccessException e4) {
                                    e4.printStackTrace();
                                } catch (InvocationTargetException e5) {
                                    e5.printStackTrace();
                                } catch (SecurityException e6) {
                                    e6.printStackTrace();
                                } catch (NoSuchMethodException e7) {
                                    e7.printStackTrace();
                                }
                                break;
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }
}
