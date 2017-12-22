package com.cosmosengine.entities.players;

import com.cosmosengine.CosmosEntity;
import com.cosmosengine.GameCanvas;

import java.awt.Graphics;
import java.util.Random;

/**
 * A hostile foe that causes damage to the player when approached.
 */
public class EnemyEntity extends CosmosEntity {

    private int damageStep = 100;

    public EnemyEntity(GameCanvas game, String folder, String ref, String onDeath, int x, int y, int width, int height, long millis) {
        super(game, folder, ref, onDeath, x, y, width, height, millis);
    }

    @Override
    public void collision() {
        if (game.player.getBounds().intersects(me)) {
            damageStep++;
            if (damageStep >= 100) {
                game.player.dealDamage(new Random().nextInt(8) + 15);
                damageStep = 0;
            }
        } else {
            damageStep = 100;
        }
    }

    @Override
    public void act() {

    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        //g.setColor(Color.RED);
        //g.drawRect((int) me.x, (int) me.y, (int) me.width, (int) me.height);
    }
}
