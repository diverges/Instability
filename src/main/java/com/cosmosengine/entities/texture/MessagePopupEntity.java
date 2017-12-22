package com.cosmosengine.entities.texture;

import com.cosmosengine.CosmosConstants;
import com.cosmosengine.CosmosEntity;
import com.cosmosengine.GameCanvas;
import com.cosmosengine.SoundManager.CosmosSound;
import com.cosmosengine.SoundManager.SoundLoader;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * Event base popup message box.
 */
public class MessagePopupEntity extends CosmosEntity {

    private String[] parts;
    private String[] message;
    private int row = 0;
    private int charCount = 0;
    private int timeCount = 0;
    private CosmosSound sound = null;
    private boolean playing = false;

    public MessagePopupEntity(GameCanvas game, long millis, String msg, String soundRef) {
        super(game, null, null, null, 0, 0, (int) (200 * CosmosConstants.SCALE), (int) (200 * CosmosConstants.SCALE), millis);
        parts = msg.split("-n");
        message = new String[parts.length];

        for (int i = 0; i < parts.length; i++) {
            message[i] = "";
        }

        isAlive = false; // objects starts off "dead" until event
        if (soundRef != null)
            sound = SoundLoader.get().getSound(soundRef);

    }

    @Override
    public void act() {
        // when acting pause game and disable HUD
        game.pause();
        game.hud.setEnabled(false);
        try {
            if (isAlive && !isDone()) {
                if (timeCount < millis / 10) {
                    // count every passed amount of milliseconds
                    timeCount++;
                } else {
                    // print out one character at a time
                    if (message[row].length() < parts[row].length()) {
                        message[row] = message[row] + parts[row].charAt(charCount);
                        charCount++;
                    } else {
                        row++;
                        charCount = 0;
                    }
                    timeCount = 0;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void setAlive(boolean isAlive) {
        super.setAlive(isAlive);
        if (!playing && isAlive) {
            if (sound != null)
                sound.playSoundOnce();
            playing = true;
        } else if (playing && !isAlive) {
            if (sound != null)
                sound.stopSound();
            playing = false;
        }
    }

    @Override
    public void collision() {
        // TODO Auto-generated method stub

    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        me.x = game.player.getBounds().x - me.width - 50;
        me.y = game.player.getBounds().y - (me.height / 2) + (game.player.getBounds().height / 2);
        g.setColor(Color.GRAY);
        g2.draw3DRect(me.x, me.y, me.width, me.height, true);
        g2.fillRect(me.x, me.y, me.width, me.height); // draw
        // background
        g.setColor(Color.WHITE);
        float lastY = me.y + 10;
        for (int i = 0; i < message.length; i++) {
            String msg = message[i];
            String realMsg = parts[i];
            if (!msg.isEmpty()) {
                int width = (int) CosmosConstants.getStringBounds(g2, realMsg).getWidth();
                lastY += (15 * CosmosConstants.SCALE);
                g2.drawString(msg, me.x + (me.width / 2) - (width / 2), lastY);
            }
        }
        g.drawString("Press enter to hide", me.x + 10, me.y + me.height - 10);
    }

    private boolean isDone() {
        return message[message.length - 1].equals(parts[message.length - 1]);
    }
}
