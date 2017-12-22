package com.cosmosengine;

import java.awt.GraphicsDevice;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * This class provides a frame for the canvas to go into.
 */
public class GameFrame {

    private static GraphicsDevice CURRENT_DEVICE = null;

    public static void main(String[] args) {
        final JFrame frame = new JFrame("InSTaBILiTi");
        frame.setIgnoreRepaint(true);
        frame.setBounds(0, 0, CosmosConstants.WIDTH, CosmosConstants.HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(true);
        final GameCanvas game = new GameCanvas();
        frame.add(game);
        frame.setVisible(true);

        game.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                int width = e.getComponent().getWidth();
                int height = e.getComponent().getHeight();
                boolean evenWidth = width % 2 == 0;
                boolean evenHeight = height % 2 == 0;
                if (evenWidth && evenHeight) {
                    int deltaWidth = width - CosmosConstants.WIDTH;
                    int deltaHeight = height - CosmosConstants.HEIGHT;
                    if (deltaWidth != 0 || deltaHeight != 0)
                        game.resizeCanvas(e.getComponent().getWidth(), e.getComponent().getHeight());
                } else {
                    if (!evenWidth) {
                        width--;
                    }
                    if (!evenHeight) {
                        height--;
                    }
                    game.setSize(width, height);
                }
            }

            @Override
            public void componentMoved(ComponentEvent e) {

            }

            @Override
            public void componentShown(ComponentEvent e) {

            }

            @Override
            public void componentHidden(ComponentEvent e) {

            }
        });

        frame.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
            }

            @Override
            public void componentMoved(ComponentEvent e) {
                GraphicsDevice deviceOn = frame.getGraphicsConfiguration().getDevice();
                if (CURRENT_DEVICE != deviceOn) {
                    CURRENT_DEVICE = deviceOn;
                    game.resetBuffer();
                }
            }

            @Override
            public void componentShown(ComponentEvent e) {
            }

            @Override
            public void componentHidden(ComponentEvent e) {
            }
        });

        CURRENT_DEVICE = frame.getGraphicsConfiguration().getDevice();
    }
}
