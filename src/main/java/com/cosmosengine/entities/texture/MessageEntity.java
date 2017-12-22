package com.cosmosengine.entities.texture;

import com.cosmosengine.CosmosEntity;
import com.cosmosengine.GameCanvas;

import java.awt.Color;
import java.awt.Graphics;

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

    public MessageEntity(GameCanvas game, String folder, String ref, String onDeath, int x, int y, int width, int height, long millis, String msg) {
        super(game, folder, ref, onDeath, x, y, width, height, millis);
        parts = msg.split("-n");
        message = new String[parts.length];

        for (int i = 0; i < parts.length; i++) {
            message[i] = "";
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        for (String letter : message) {
            g.drawString(letter, me.x, me.y + ySpace);
            ySpace += 15;
        }
        ySpace = 15;
    }

    @Override
    public void collision() {
        // TODO Auto-generated method stub

    }

    public boolean isDone() {
        return message[message.length - 1].equals(parts[message.length - 1]);
    }

    @Override
    public void act() {
        // TODO Auto-generated method stub
        try {
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
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }

    }
}
