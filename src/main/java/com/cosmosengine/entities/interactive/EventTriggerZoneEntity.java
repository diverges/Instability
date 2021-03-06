package com.cosmosengine.entities.interactive;

import com.cosmosengine.CosmosConstants;
import com.cosmosengine.CosmosEntity;
import com.cosmosengine.GameCanvas;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Invisible regions used to detect when player enters a region to trigger a specific event based on level.
 */
public class EventTriggerZoneEntity extends CosmosEntity {

    private String eventID;
    private boolean isEventTriggered = false;
    private boolean wasEventTriggered = false;
    private boolean onlyTriggerOnce;

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
            if (me.contains(game.player.getPoint())) {
                this.isEventTriggered = true;
            }
        }

        if (!isAlive && !me.contains(game.player.getPoint()) && !onlyTriggerOnce) {
            this.isAlive = true; // return to alive
        }
    }

    @Override
    public void draw(Graphics g) {
        if (CosmosConstants.DEBUG) {
            g.setColor(Color.ORANGE);
            g.drawRect(me.x, me.y, me.width, me.height);
        }
    }

    public boolean getEventState() {
        return isEventTriggered;
    }

    public String getEventID() {
        return eventID;
    }
}
