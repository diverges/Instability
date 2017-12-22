package com.cosmosengine;

import com.cosmosengine.entities.interactive.ButtonEntity;
import com.cosmosengine.interfaces.Clickable;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * This is the Heads Up Display class. Where the buttons/notifiers will be placed statically on the screen.
 */
public class CosmosHUD implements Clickable {
    private GameCanvas game;
    private ArrayList<CosmosEntity> staticEntities = new ArrayList<>();
    private ButtonEntity pressing;
    private boolean enabled = true;

    CosmosHUD(GameCanvas game) {
        this.game = game;
        loadHUD();
    }

    private void loadHUD() {
        /*
         * staticEntities.add(new ButtonEntity(game, "sound", null, null, null, game.getParent().getWidth() - 75, 15, 50, 50, "Sound"));
         */


        ButtonEntity reset = new ButtonEntity(game, "Reset", "hud", "reset-button-idle.png;reset-button-clicked.png", null, 0, 0);
        ButtonEntity inventory = new ButtonEntity(game, "Inventory", "hud", "inventory-button-idle.png;inventory-button-clicked.png", null, 0, 0);
        staticEntities.add(reset);
        staticEntities.add(inventory);

        reset.setX(CosmosConstants.WIDTH - reset.getWidth() - 20);
        reset.setY(CosmosConstants.HEIGHT - reset.getHeight() - 20);

        inventory.setX(reset.getX() - inventory.getWidth() - 20);
        inventory.setY(CosmosConstants.HEIGHT - inventory.getHeight() - 20);
    }

    public void draw(Graphics g) {
        //if (enabled) {
        g.setColor(Color.WHITE);
        CosmosConstants.LAST_STRING_BOUNDS = CosmosConstants.drawStringFromTop((Graphics2D) g, "Objective: " + game.getObjective(), 32, 32);
        CosmosConstants.LAST_STRING_BOUNDS = CosmosConstants.drawStringFromTop((Graphics2D) g, game.level.name, 32, CosmosConstants.LAST_STRING_BOUNDS.y + CosmosConstants.LAST_STRING_BOUNDS.height + 5);
        for (CosmosEntity c : staticEntities) {
            c.draw(g);
        }
        //}
    }

    public ArrayList<CosmosEntity> getStaticEntities() {
        return staticEntities;
    }

    @Override
    public void mousePressed(MouseEvent event) {
        if (enabled) {
            for (CosmosEntity b : staticEntities) {
                if (b instanceof ButtonEntity) {
                    if (((ButtonEntity) b).isInButton(event.getPoint())) {
                        pressing = (ButtonEntity) b;
                        pressing.pushDown();
                        break;
                    }
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        if (enabled) {
            if (pressing != null) {
                pressing.pullUp();
                if (pressing.isInButton(event.getPoint())) {
                    String ref = (pressing).getRef();
                    switch (ref) {
                        case "Reset":
                            game.player.kill();
                            break;
                        case "Inventory":
                            // when inventory button is pressed
                            if (!game.player.getInventory().isDisplay()) {
                                // pause game and load inventory
                                game.player.getInventory().show();
                                game.pause();
                            } else {
                                // unpause game and unload inventory
                                game.player.getInventory().hide();
                                game.unpause();
                            }
                            if (CosmosConstants.DEBUG)
                                System.out.println("Load Inventory");
                            break;
                        case "sound":
                            CosmosConstants.SOUNDS = !CosmosConstants.SOUNDS;
                            break;
                    }
                }
                pressing = null;
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent event) {
    }

    @Override
    public void mouseExited(MouseEvent event) {
    }

    @Override
    public void mouseDragged(MouseEvent event) {
    }

    @Override
    public void mouseMoved(MouseEvent event) {
    }

    @Override
    public void mouseClicked(MouseEvent event) {
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void resize(int deltaWidth, int deltaHeight) {
        for (CosmosEntity obj : staticEntities) {
            obj.getBounds().y = (obj.getBounds().y + deltaHeight);
            obj.getBounds().x = (obj.getBounds().x + deltaWidth);
        }
    }
}
