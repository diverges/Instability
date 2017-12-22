package com.cosmosengine.entities.texture;

import com.cosmosengine.CosmosConstants;
import com.cosmosengine.CosmosEntity;
import com.cosmosengine.GameCanvas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * An indestructible block. It is the base class for the destructible blocks.
 */
public class WallEntity extends CosmosEntity {

    boolean dying = false;

    private Rectangle c1;
    private Rectangle c2;
    private Rectangle c3;
    private Rectangle c4;

    public WallEntity(GameCanvas game, String folder, String ref, String onDeath, int x, int y, int width, int height, long millis) {
        super(game, folder, ref, onDeath, x, y, width, height, millis);
    }

    @Override
    public void draw(Graphics g) {
        if (this.sprites != null)
            super.draw(g);

        if (CosmosConstants.DEBUG && c1 != null) {
            g.setColor(Color.RED);
            g.drawRect(c1.x, c1.y, c1.width, c1.height);
            g.drawRect(c2.x, c2.y, c2.width, c2.height);
            g.drawRect(c3.x, c3.y, c3.width, c3.height);
            g.drawRect(c4.x, c4.y, c4.width, c4.height);

            c1 = null;
            c2 = null;
            c3 = null;
            c4 = null;
        }
    }

    @Override
    public void act() {

    }

    @Override
    public void collision() {
        boolean d = game.player.isMovingDown();
        boolean u = game.player.isMovingUp();
        boolean r = game.player.isMovingRight();
        boolean l = game.player.isMovingLeft();
        Rectangle pBounds = game.player.getBounds();
        c1 = new Rectangle(me.x - 4, me.y - 4, 4, 4);
        c2 = new Rectangle(me.x + me.width, me.y - 4, 4, 4);
        c3 = new Rectangle(me.x - 4, me.y + me.height, 4, 4);
        c4 = new Rectangle(me.x + me.width, me.y + me.height, 4, 4);
        if (d && checkDown()) {
            if (!game.player.getCollidingWithDown().contains(this)) {
                game.player.getCollidingWithDown().add(this);
            }
        }
        if (u && checkUp()) {
            if (!game.player.getCollidingWithUp().contains(this)) {
                game.player.getCollidingWithUp().add(this);
            }
        }
        if (r && checkRight()) {
            if (!game.player.getCollidingWithRight().contains(this)) {
                game.player.getCollidingWithRight().add(this);
            }
        }
        if (l && checkLeft()) {
            if (!game.player.getCollidingWithLeft().contains(this)) {
                game.player.getCollidingWithLeft().add(this);
            }
        }
        boolean cd = game.player.isCollidingDown();
        boolean cu = game.player.isCollidingUp();
        boolean cr = game.player.isCollidingRight();
        boolean cl = game.player.isCollidingLeft();
        boolean c1Collide = pBounds.intersects(c1);
        boolean c2Collide = pBounds.intersects(c2);
        boolean c3Collide = pBounds.intersects(c3);
        boolean c4Collide = pBounds.intersects(c4);
        boolean cornerCollide = c1Collide || c2Collide || c3Collide || c4Collide;
        if (cornerCollide && (d || u) && (l || r)) {
            if (d && r && (!cd && !cr) && c1Collide) {
                game.player.forceStopMoving();
            } else if (d && l && (!cd && !cl) && c2Collide) {
                game.player.forceStopMoving();
            } else if (u && r && (!cu && !cr) && c3Collide) {
                game.player.forceStopMoving();
            } else if (u && l && (!cu && !cl) && c4Collide) {
                game.player.forceStopMoving();
            }
        }
    }

    public boolean isCollided() {
        if (!isAlive || dying)
            return false;
        Rectangle collide = new Rectangle(me.x - 4, me.y - 4, me.width + 4, me.height + 4);
        return game.player.getBounds().intersects(collide);
    }

    private boolean checkRight() {
        if (!isAlive || dying)
            return false;
        Rectangle rightColl = new Rectangle(me.x - 4, me.y, me.width, me.height);
        return game.player.getBounds().intersects(rightColl);
    }

    private boolean checkLeft() {
        if (!isAlive || dying)
            return false;
        Rectangle leftColl = new Rectangle(me.x, me.y, me.width + 4, me.height);
        return game.player.getBounds().intersects(leftColl);
    }

    private boolean checkDown() {
        if (!isAlive || dying)
            return false;
        Rectangle downColl = new Rectangle(me.x, me.y - 4, me.width, me.height);
        return game.player.getBounds().intersects(downColl);
    }

    private boolean checkUp() {
        if (!isAlive || dying)
            return false;
        Rectangle upColl = new Rectangle(me.x, me.y, me.width, me.height + 4);
        return game.player.getBounds().intersects(upColl);
    }

}
