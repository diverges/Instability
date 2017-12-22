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

    boolean isBeingCollidedRight = false;
    boolean isBeingCollidedLeft = false;
    boolean isBeingCollidedUp = false;
    boolean isBeingCollidedDown = false;
    private Rectangle rightColl;
    private Rectangle leftColl;
    private Rectangle downColl;
    private Rectangle upColl;
    boolean dying = false;

    public WallEntity(GameCanvas game, String folder, String ref, String onDeath, int x, int y, int width, int height, long millis) {
        super(game, folder, ref, onDeath, x, y, width, height, millis);
    }

    @Override
    public void draw(Graphics g) {
        if (this.sprites != null)
            super.draw(g);
        if (CosmosConstants.DEBUG) {
            g.setColor(Color.WHITE);
            if (this.rightColl != null && this.isBeingCollidedRight) {
                g.drawRect(rightColl.x, rightColl.y, rightColl.width, rightColl.height);
                g.drawString(rightColl.x + ", " + rightColl.y, rightColl.x, rightColl.y);
            }
            if (this.leftColl != null && this.isBeingCollidedLeft) {
                g.drawRect(leftColl.x, leftColl.y, leftColl.width, leftColl.height);
                g.drawString(leftColl.x + ", " + leftColl.y, leftColl.x, leftColl.y);
            }
            if (this.upColl != null && this.isBeingCollidedUp) {
                g.drawRect(upColl.x, upColl.y, upColl.width, upColl.height);
                g.drawString(upColl.x + "," + upColl.y, upColl.x, upColl.y);
            }
            if (this.downColl != null && this.isBeingCollidedDown) {
                g.drawRect(downColl.x, downColl.y, downColl.width, downColl.height);
                g.drawString(downColl.x + "," + downColl.y, downColl.x, downColl.y + me.height);
            }
        }
    }

    @Override
    public void act() {

    }

    @Override
    public void collision() {
        // Check to see if we are being collided from the top.
        if (game.player.isMovingDown()) {
            if (checkDown()) {
                if (!game.player.getCollidingWithDown().contains(this))
                    game.player.getCollidingWithDown().add(this);
                isBeingCollidedDown = true;
            } else if (isBeingCollidedDown) {
                isBeingCollidedDown = false;
                game.player.getCollidingWithDown().remove(this);
            }
        } else {
            if (game.player.getCollidingWithDown().contains(this))
                game.player.getCollidingWithDown().remove(this);
            isBeingCollidedDown = false;
        }

        // Check to see if we are being collided from the bottom.
        if (game.player.isMovingUp()) {
            if (checkUp()) {
                if (!game.player.getCollidingWithUp().contains(this))
                    game.player.getCollidingWithUp().add(this);
                isBeingCollidedUp = true;
            } else if (isBeingCollidedUp) {
                isBeingCollidedUp = false;
                game.player.getCollidingWithUp().remove(this);
            }
        } else {
            if (game.player.getCollidingWithUp().contains(this))
                game.player.getCollidingWithUp().remove(this);
            isBeingCollidedUp = false;
        }

        if (game.player.isMovingRight()) {
            if (checkRight() && !checkDown() && !checkUp()) {
                if (!game.player.getCollidingWithRight().contains(this))
                    game.player.getCollidingWithRight().add(this);
                isBeingCollidedRight = true;
            } else {
                isBeingCollidedRight = false;
                game.player.getCollidingWithRight().remove(this);
            }

        } else {
            if (game.player.getCollidingWithRight().contains(this))
                game.player.getCollidingWithRight().remove(this);
            isBeingCollidedRight = false;
        }

        // Check to see if we are being collided from the left.
        if (game.player.isMovingLeft()) {
            if (checkLeft() && !checkDown() && !checkUp()) {
                if (!game.player.getCollidingWithLeft().contains(this))
                    game.player.getCollidingWithLeft().add(this);
                isBeingCollidedLeft = true;
            } else if (isBeingCollidedLeft) {
                isBeingCollidedLeft = false;
                game.player.getCollidingWithLeft().remove(this);
            }
        } else {
            if (game.player.getCollidingWithLeft().contains(this))
                game.player.getCollidingWithLeft().remove(this);
            isBeingCollidedLeft = false;
        }

    }

    private boolean checkRight() {
        if (!isAlive || dying)
            return false;
        rightColl = new Rectangle(me.x - 4, me.y, (int) this.getWidth(), (int) this.getHeight());
        return game.player.getBounds().intersects(rightColl);
    }

    private boolean checkLeft() {
        if (!isAlive || dying)
            return false;
        leftColl = new Rectangle(me.x, me.y, (int) this.getWidth() + 4, (int) this.getHeight());
        return game.player.getBounds().intersects(leftColl);
    }

    private boolean checkDown() {
        if (!isAlive || dying)
            return false;
        downColl = new Rectangle(me.x, me.y - 4, (int) this.getWidth(), (int) this.getHeight());
        return game.player.getBounds().intersects(downColl);
    }

    private boolean checkUp() {
        if (!isAlive || dying)
            return false;
        upColl = new Rectangle(me.x, me.y, (int) this.getWidth(), (int) this.getHeight() + 4);
        return game.player.getBounds().intersects(upColl);
    }

}
