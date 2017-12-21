package com.cosmosengine.entities.texture;

import com.cosmosengine.CosmosConstants;
import com.cosmosengine.GameCanvas;
import com.cosmosengine.entities.interactive.DroppedItemEntity;
import com.cosmosengine.entities.players.PlayerEntity;
import com.cosmosengine.interfaces.Killable;
import com.cosmosengine.inventory.Item;

import java.awt.Graphics;
import java.awt.Point;

/**
 * A generic block that can drop any item and can have any health. It is re-spawnable if the constructor is passed with a true boolean.
 */
public class BlockEntity extends WallEntity implements Killable {

    int health = 40;
    int finalHealth = 1;
    PlayerEntity player;
    // damage timer
    public int damageStep = 100;

    // death
    int deathTimer = 0;
    int deathStep = 0;
    boolean respawn = false;

    Item onDeathDrop;

    public BlockEntity(GameCanvas game, String folder, String ref, String onDeath, int x, int y, int width, int height, Item onDeathDrop, int health, boolean respawn) {
        super(game, folder, ref, onDeath, x, y, width, height, -1);
        this.health = health;
        this.finalHealth = this.health;
        player = game.player;
        this.onDeathDrop = onDeathDrop;
        this.respawn = respawn;
    }

    @Override
    public void collision() {
        super.collision();
        if (isAlive) {
            if (player.isMoving()) {
                if ((isBeingCollidedDown && player.isMovingDown()) || (isBeingCollidedUp && player.isMovingUp()) || (isBeingCollidedLeft && player.isMovingLeft()) || (isBeingCollidedRight && player.isMovingRight())) {
                    Point playerPoint = player.getPoint();
                    double distance = new Point(playerPoint.x, (int) (playerPoint.y - player.getHeight() / 2)).distance(this.getPoint());
                    if (distance < player.distance || player.distance == -1) {
                        if (player.block != null) {
                            if (!player.block.dying) {
                                player.block.damageStep = 0;
                                player.block.health = player.block.finalHealth;
                                player.block.setCurrent(0);
                            }
                        }
                        player.distance = distance;
                        player.block = this;
                    }
                } else {
                    if (player.block == this) {
                        player.block = null;
                        player.distance = -1;
                    }
                    if (!dying) {
                        damageStep = 0;
                        health = finalHealth;
                        setCurrent(0);
                    }
                }
            }
        }

    }

    @Override
    public void act() {
        super.act();
        if (isAlive) {
            if (this.health <= 0)
                onDeath(); // trigger on death
        } else if (respawn) {
            if (!game.player.getBounds().intersects(this.getBounds())) {
                deathTimer++;
                if (deathTimer > 24000) {
                    setCurrent(0);
                    deathStep = 0;
                    deathTimer = 0;
                    isAlive = true;
                    health = finalHealth;
                }
            }
        }
    }

    @Override
    public boolean checkIfAlive() {
        return isAlive;
    }

    @Override
    public void onDeath() {
        dying = true;
        super.collision(); // call the last collision to set the correct
        // isColliding for the player.
        if (player.block == this) {
            player.block = null;
            player.distance = -1;
        }
        if (this.onDeathSprites != null) {
            deathTimer++;
            if (deathTimer >= 5) {
                deathTimer = 0;
                if (deathStep < onDeathSprites.length - 1) {
                    deathStep++;
                } else {
                    deathTimer = 0;
                    dying = false;
                    this.isAlive = false;
                }
            }
        } else {
            this.isAlive = false;
        }
        if (!isAlive) {
            deathDrop();
        }
    }

    @Override
    public void draw(Graphics g) {
        if (!dying)
            super.draw(g);
        else
            onDeathSprites[deathStep].draw(g, (int) me.x, (int) me.y);
    }

    /**
     * On death item add event.
     */
    public void deathDrop() {
        if (CosmosConstants.debug)
            System.out.println("DEATH!");
        if (this.onDeathDrop != null) {
            game.level.getLevelInteractiveObjects()
                      .add(new DroppedItemEntity(game, null, onDeathDrop.getRef(), null, (int) me.x, (int) me.y, (int) me.width, (int) me.height, -1, onDeathDrop, 1, false));
            //game.player.getInventory().add(onDeathDrop, 1);
        }
    }

    public int dealDamage(int damage) {
        if (sprites.length > 1) {
            int step = health / this.sprites.length;
            if (step != 0)
                setCurrent(step);
        }
        this.health -= damage;
        if (CosmosConstants.debug)
            System.out.println(health);
        return damage;
    }

}
