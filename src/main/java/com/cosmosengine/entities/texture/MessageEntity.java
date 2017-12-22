package com.cosmosengine.entities.texture;

import com.cosmosengine.CosmosConstants;
import com.cosmosengine.CosmosEntity;
import com.cosmosengine.GameCanvas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * Message box, use '-n' to start a new line.
 */
public class MessageEntity extends CosmosEntity {

    private String[] parts;
    private String[] message;
    private int ySpace = 15;
    private int row = 0;
    private int charCount = 0;
    private int timeCount = 0;
    private boolean loadingScreen = false;

    public MessageEntity(GameCanvas game, String folder, String ref, String onDeath, int x, int y, long millis, String msg, boolean loadingScreen) {
        super(game, folder, ref, onDeath, x, y, millis);
        parts = msg.split("-n");
        message = new String[parts.length];

        for (int i = 0; i < parts.length; i++) {
            message[i] = "";
        }

        this.loadingScreen = loadingScreen;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(CosmosConstants.DEFAULT_FONT);
        for (String letter : message) {
            g.drawString(letter, me.x, me.y + ySpace);
            ySpace += 15;
        }
        ySpace = 15;

        if (loadingScreen) {
            String continueString = "Press enter to continue...";
            int width = (int) CosmosConstants.getStringBounds((Graphics2D) g, continueString).getWidth();
            g.drawString(continueString, (CosmosConstants.WIDTH / 2) - (width / 2), CosmosConstants.HEIGHT - 20);
        }
    }

    @Override
    public void collision() {
    }

    public boolean isDone() {
        return message[message.length - 1].equals(parts[message.length - 1]);
    }

    @Override
    public void act() {
        if (!isDone()) {
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
    }
}
