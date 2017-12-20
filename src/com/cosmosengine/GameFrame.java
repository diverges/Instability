package com.cosmosengine;

import javax.swing.JFrame;

/**
 * This class provides a frame for the canvas to go into.
 */
public class GameFrame {

	public static void main(String[] args) {
		JFrame frame = new JFrame("InSTaBILiTi");
		frame.setIgnoreRepaint(true);
		//frame.setUndecorated(true);
		frame.setBounds(0, 0, CosmosConstants.WIDTH, CosmosConstants.HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		GameCanvas game = new GameCanvas();
		frame.add(game);
		frame.setVisible(true);
	}
}
