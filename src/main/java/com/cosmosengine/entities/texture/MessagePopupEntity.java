package com.cosmosengine.entities.texture;

import com.cosmosengine.CosmosEntity;
import com.cosmosengine.GameCanvas;
import com.cosmosengine.SoundManager.CosmosSound;
import com.cosmosengine.SoundManager.SoundLoader;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Event base popup message box.
 */
public class MessagePopupEntity extends CosmosEntity {

    private String[] parts;
    private String[] message;
    private int ySpace = 15;
    private int row = 0;
    private int charCount = 0;
    private int timeCount = 0;
    private int doneCount = 0;
    private CosmosSound sound = null;
    private boolean playing = false;

    public MessagePopupEntity(GameCanvas game, long millis, String msg, String soundRef) {
        super(game, null, null, null, 150, 150, 200, 200, millis);
        parts = msg.split("-n");
        message = new String[parts.length];

        for (int i = 0; i < parts.length; i++) {
            message[i] = "";
        }

        isAlive = false; // objects starts of "dead" until event
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
            } else {
                doneCount++;
                if (doneCount >= 100) {
                    // when done un-pause
                    game.unpause();
                    game.hud.setEnabled(true);
                    this.setAlive(false);
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

        g.setColor(Color.GRAY);
        g.fillRect(me.x, me.y, me.width, me.height); // draw
        // background
        g.setColor(Color.WHITE);
        for (String letter : message) {
            g.drawString(letter, me.x + 10, me.y + 10 + ySpace);
            ySpace += 15;
        }
        ySpace = 15;
        g.drawString("Press enter to hide", me.x + 10, me.y + me.height - 10);
    }

    private boolean isDone() {
        return message[message.length - 1].equals(parts[message.length - 1]);
    }
}
