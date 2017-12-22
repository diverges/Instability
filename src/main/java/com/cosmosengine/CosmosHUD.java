package com.cosmosengine;

import com.cosmosengine.entities.interactive.ButtonEntity;
import com.cosmosengine.interfaces.Clickable;

import java.awt.Color;
import java.awt.Graphics;
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

        staticEntities.add(new ButtonEntity(game, "Pause", "hud", "reset-button-idle.png;reset-button-clicked.png", null, game.getParent().getWidth() - 100, game.getParent()
                                                                                                                                                                 .getHeight() - 70, 100, 50));
        staticEntities.add(new ButtonEntity(game, "Inventory", "hud", "inventory-button-idle.png;inventory-button-clicked.png", null, game.getParent().getWidth() - 200, game.getParent()
                                                                                                                                                                             .getHeight() - 70, 100, 50));
    }

    public void draw(Graphics g) {
        if (enabled) {
            g.setColor(Color.WHITE);
            g.drawString("Objective: " + game.getObjective(), 32, 75);
            g.drawString(game.level.name, 32, 35);
            for (CosmosEntity c : staticEntities) {
                c.draw(g);
            }
        }
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
                        case "Pause":
                            game.player.kill();
                            break;
                        case "Inventory":
                            // when inventory button is pressed
                            if (!game.player.getInventory().isDisplay()) {
                                game.player.getInventory().show();
                                game.pause();// pause game and load inventory
                            } else {
                                game.player.getInventory().hide();
                                game.unpause(); // unpause game and unload
                                // inventory
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
}
