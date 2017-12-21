package com.cosmosengine.entities.interactive;

import com.cosmosengine.CosmosConstants;
import com.cosmosengine.CosmosEntity;
import com.cosmosengine.GameCanvas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Invisible regions used to detect when player enters a region to trigger a specific event based on level.
 */
public class EventTriggerZoneEntity extends CosmosEntity {

    private String eventID;
    private boolean isEventTriggered = false;
    private boolean wasEventTriggered = false;
    private boolean onlyTriggerOnce = false;

    /**
     * @param game
     * @param x
     * @param y
     * @param eventID     - specific call ID for level
     * @param triggerOnce - Specify the amount of times event should activate while in current level "0" false, "1" true
     */
    public EventTriggerZoneEntity(GameCanvas game, int x, int y, int width, int height, String eventID, String triggerOnce) {
        super(game, null, null, null, x, y, width, height, -1);
        this.eventID = eventID;
        this.onlyTriggerOnce = triggerOnce.equals("0");
        this.me = new Rectangle(x, y, width, height);

    }

    @Override
    public void act() {
        if (isAlive) {
            // if set to trigger each second player is in region
            if (isEventTriggered && !onlyTriggerOnce) {
                game.level.act(this.eventID); // call event

                this.isEventTriggered = false; // reset param
            } else if (!wasEventTriggered && onlyTriggerOnce && isEventTriggered) {
                // when event is set to only trigger once
                game.level.act(this.eventID);
                this.isAlive = false;
                this.wasEventTriggered = true; // specify to not act again
            }
        }

    }

    @Override
    public void collision() {
        if (isAlive) {
            // redraw rectangle
            this.me = new Rectangle((int) me.x, (int) me.y, (int) me.width, (int) me.height);
            if (me.contains(game.player.getPoint())) {
                this.isEventTriggered = true;
            }
        }

        if (!isAlive && !me.contains(game.player.getPoint()) && !onlyTriggerOnce) {
            this.isAlive = true; // return to alive
        }

    }

    public void draw(Graphics g) {
        if (CosmosConstants.debug) {
            g.setColor(Color.ORANGE);
            g.drawRect((int) me.x, (int) me.y, (int) me.width, (int) me.height);
        }
    }

    public boolean getEventState() {
        return isEventTriggered;
    }

    public String getEventID() {
        return eventID;
    }
}
