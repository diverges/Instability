package com.cosmosengine.levels;

import java.awt.Color;
import java.awt.Graphics;

import com.cosmosengine.GameCanvas;
import com.cosmosengine.LevelLoader;
import com.cosmosengine.SoundManager.SoundLoader;
import com.cosmosengine.SpriteManager.CosmosSprite;
import com.cosmosengine.SpriteManager.ImageLoader;
import com.cosmosengine.entities.texture.MessagePopupEntity;

public class LevelTwoB extends LevelLoader {

	MessagePopupEntity neutronScript, jumperScript, dangerScript, endScript;
	CosmosSprite loadImage;

	public LevelTwoB(GameCanvas game) {
		super(game);
		this.name = "Asteroid - Lower Passage";
		bg_sound = SoundLoader.get().getSound("a_s2.wav");

		level = "ddddddddddddddddddddddddddddddddddddddddddddddddddd\n"
				+ "dddddddwwwddddddddddddddddddddddddddddddddddddddddd\n"
				+ "ddddddwwdwwwddddddddddddddddddddddddddddddddddddddd\n"
				+ "dddddwwdddwwwwwwwwwwddddddddddddddddddddddddddddddd\n"
				+ "ddddwwdddww:::::wwwwwwwwwwwwwwwdddddddddddddddddddd\n"
				+ "dddwwdddww:::S:::wwddddddddddwwwwwwdddddddddddddddd\n"
				+ "ddwwdddddww:::::wwdddd:::dddddddddwwwwwwwwwwwdddddd\n"
				+ "dwwddddddwww#::wwwddd:::::dddddddddddwdddddwwwwdddd\n"
				+ "dwwddddddddd:::::#:::::::::::::::::::#::dddddwwwwwd\n"
				+ "dwwddddddddd:::::#:::::::::::::::::::#::dddddddwwwd\n"
				+ "dwwddddddddd:::ddwddd:::::ddddwdwddddw##wdddddwwwdd\n"
				+ "ddwwwddddddd:::ddgdddd:::dddddwMwdHddd::dddddddwwwd\n"
				+ "dddwwwdddddw###wddddddddddddddwdwddddw##wdddddwwwwd\n"
				+ "ddddwwwddddd:::#:::::::::::::::::::::#::#:ddwwwwddd\n"
				+ "dddwwwdddddw###wdddddw####wdwdwwwdwddw##wddwwwwdddd\n"
				+ "ddddwwwddddd:::ddddddd::::ddwddMwwwddd::ddwwwdddddd\n"
				+ "dddwwwdddddd:::dddgddd::::ddwwwwwMwddd::dwwwwdddddd\n"
				+ "ddwwwddddddd:::wdddddw####wdddddwdwddd::wwwdddddddd\n"
				+ "dddwwwdddddw:::::::::::#::::::::::::::::wwwdddddddd\n"
				+ "ddddwwwdddddddddddddddd:ddddddddddddddddwwwdddddddd\n"
				+ "dddddwwwwwwwwwwwwwwwwww#wwwwwwwwwwwwwwwwwwwdddddddd\n"
				+ "dddwwwdgdgddwwww:::::::#:::::wwdddddddddddddddddddd\n"
				+ "ddww:::wdddwwww:::::::::::::wwwdddddddddddddddddddd\n"
				+ "dww::::::::::wwww:::::#:::wwwdddddddddddddddddddddd\n"
				+ "www##www#######www:::::::wwwddddddddddddddddddddddd\n"
				+ "www::::::::::::#wwww::::wwwdddddddddddddddddddddddd\n"
				+ "wwdddd:w::w######w#wwwwwwwddddddddddddddddddddddddd\n"
				+ "dwwgd::::ddddd:d:#:::wwdwwddwwddddddddddddddddddddd\n"
				+ "dwwwwd#wdgdddd:dd#:::wwwwwwwwwwdddddddddddddddddddd\n"
				+ "ddwwwwwwww#::::::w::wwdwdwwddwwwwdddddddwwwwddddddd\n"
				+ "dddddddwww:::::::#:::wwwdwwdddddwwwddddddwwwwdddddd\n"
				+ "ddddddddwww::dddd#::wwwddwddddd::wwwwddwwwddddddddd\n"
				+ "dddddddwwwdddddwww:wwwwwdwddgdd::::dwwwwwdddddddddd\n" // 33
				+ "ddddddwwwwdgdwwwww#ww:wwdwddddd:::dddddwwwddddddddd\n"
				+ "dddddwwwddwdww::w#::w::wwdwddd:::dddddwwwdddddddddd\n"
				+ "dddddwwwwdd:wwd:w:::w:::wwwwd:::dddddwwwddddddddddd\n"
				+ "ddddddwwwddwwdddww:ww::::::::::dddddddwwwdddddddddd\n"
				+ "dddddwwwwdwwddddddddddddddddd:::dddddwwwddddddddddd\n"
				+ "ddddddwwwwwddddgdddddddddddddd:::dddwwwwwwwwwwwwwwd\n"
				+ "dddddddwwwddddddddddddddddddddd:::dwwwwwwwwddddddww\n"
				+ "ddddddwwwdddddddddgddddddddddddd::::::::wwwwdddddww\n"
				+ "dddddddwwwwddddddddddddddddddddddd::::::#::#::::www\n"
				+ "ddddddddwwwwwwwwwwwwwwwwwwwwddddddd::::::wwwdd:::ww\n"
				+ "ddddddwwwddddddddddddddddwwwwwwwwwwwwwwwwwwwd:::ddw\n"
				+ ":ddddwwwwwdd:::w::::::::::::wwdd::::#:::wwwww::::ww\n"
				+ "ddddwwwwRwdw:::#::www###wwwwwdgdd:::#::::::#ww::::w\n"
				+ ":ddwwdddd::::::#::w#:::::wwwdddd::::wwww::::::::www\n"
				+ "dddwwdddd::::::w#::::::::w:::::::#wwww::::::::wwwd:\n"
				+ ":ddwwwdddww#::::::::ww#::::::wwwwwwwwww:::::::wwdd:\n"
				+ "::ddwwwwwwdd:::w###ww:::::wwwwwwwwwwww::::::::wwddd\n"
				+ ":::ddwwwwwwddd::::ww::::dddddddddd::::::::::wwwwdd:\n"
				+ ":dddwwwRddddgddddwwdd:::ddddddddgdddd:#::wwwwwwwwdd\n"
				+ "ddddwwwwddwwddddwwddddwddddddddwwwwwww#wwwdddddwwwd\n"
				+ "dddddwdwwwdddddwwdgdwwwdddddddwwddd::::::dddddwwwdd\n"
				+ "dddddwwwdwwwwwwwwwwwwwddddwwwwwddd::::#:::ddddwwddd\n"
				+ "dddddddwwwddddddddddwwwwwwwwwwddddd::::::ddwwwwdddd\n"
				+ "ddddddwwdwwwddddddddddddwwwwwwddddddddddddwwwdddddd\n"
				+ "dddddddwwwdddddddddddddddwwwwwwdddddwwwwwwwwddddddd\n"
				+ "ddddddwwdwwwddddddddddddddwwwwwwwwwwwwwwddddddddddd\n"
				+ "ddddwwwddwwwwwwwwwwwwwwwwwwwwwww::::::::wwwwwwwwwwd\n"
				+ ":::ddwwwdddw#::#::wwwddgddwwdddw:::::::dddddddwwwd:\n"
				+ "::ddwwwddgdw:::w::::wwddddwwdgdww:::::wwddgdgddwwwd\n"
				+ ":::ddwwwddw::::wddd::ww###wwddddww::::::ddddddddwwd\n"
				+ "::ddwwwddww::wwdddddww#::::wwddddw:::wwwwddddddwwd:\n"
				+ ":ddwwgdddd#:::wwwAwww##w###ww:::::::wwwwwwdddwwwd::\n"
				+ "::ddwwwddd#::::wwwww:::#:::ww::::::wwdddwwwdwwddd::\n"
				+ ":::ddwwwwww::::::#:::::::::www::::wwddgddwwddwwdd::\n"
				+ "::ddwwwgd::::::www:::::w:::::#:::ww::ddd::wwddww:::\n"
				+ ":::ddwwwwd::::wwddd::::#::#::#::::ww:::::::wwwwd:::\n"
				+ "::::dwwwww#::wwddgdd:::#:::::#::::#::::::::wwwdd:::\n"
				+ "dddddddwwww#wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww\n"
				+ "dddddddwwd:::ddddwwdddddd:::dddddddddddddd:::wwwwww\n"
				+ "ddddddwwwdd:::ddwwwddddd::::::ddddddddddd::::::wwww\n"
				+ "dddddwwwdddd:::dwRwwddd:::dd:::ddwwwdddd:::ww:::www\n"
				+ "dddddwwwddddd:::dddddd:::dddd:::ddRwwdd:::wwww:#::w\n"
				+ "dddddddwwddddd:::dddd:::dddddd:::wwwdd:::wwddww#:ww\n"
				+ "ddddddwwwdgdddd:::dd:::ddgddddd:::ddd:::wwddddd#www\n"
				+ "dddddwwwdddddddd::::::dddddddddd::::::wwdddddnddwww\n"
				+ "dddddwwwdddddgddd:::ddddddddddddd:::ww::#:dddddwwww\n"
				+ "dddddddwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww\n"
				+ "ddddddddddddddddddddddddddddddddddddddddddddddddddd\n"
				+ "ddddddddddddddddddddddddddddddddddddddddddddddddddd\n"
				+ "ddddddddddddddddddddddddddddddddddddddddddddddddddd\n"
				+ "ddddddddddddddddddddddddddddddddddddddddddddddddddd\n"
				+ "ddddddddddddddddddddddddddddddddddddddddddddddddddd\n"
				+ "ddddddddddddddddddddddddddddddddddddddddddddddddddd\n"
				+ "ddddddddddddddddddddddddddddddddddddddddddddddddddd\n"
				+ "ddddddddddddddddddddddddddddddddddddddddddddddddddd\n"
				+ "ddddddddddddddddddddddddddddwwwwwdddddddddddddddddd\n"
				+ "ddddddddddddddddddddddddwwww:::::wwwwdddddddddddddd\n"
				+ "dddddddddddddddddddddddwwww:::::::wwwwddddddddddddd\n"
				+ "dddddddddddddddddddddddw:::::#:::::::wddddddddddddd\n"
				+ "dddddddddddddddddddddddww:::::::::::wwddddddddddddd\n"
				+ "dddddddddddddddddddddddwwwwwwwwwwwwwwdddddddddddddd\n"
				+ "ddddddddddddddddddddddddddddddddddddddddddddddddddd\n"
				+ "ddddddddddddddddddddddddddddddddddddddddddddddddddd\n";

		String complex = "[tz;120;120;neutronScript;0]\n" + "[tw;500;0]\n"
				+ "[tw;750;0]\n" + "[tw;500;0]\n" + "[tw;750;0]\n"
				+ "[tw;850;1]\n" + "[tw;850;1]\n" + "[tw;750;1]\n"
				+ "[tw;750;1]\n" + "[tw;750;1]\n" + "[tw;850;1]\n"
				+ "[tw;850;1]\n" + "[tw;500;0]\n" + "[tw;500;0]\n"
				+ "[tw;500;0]\n" + "[tw;900;1]\n" + "[tw;900;1]\n"
				+ "[tw;900;1]\n" + "[tw;1000;1]\n" + "[tw;1000;1]\n"
				+ "[tw;1000;1]\n" + "[tw;1000;1]\n" + "[tw;500;1]\n"
				+ "[tw;500;1]\n" + "[tw;750;1]\n" + "[tw;750;1]\n"
				+ "[tw;750;1]\n" + "[tw;750;1]\n"
				+ "[msg;Weakness - Magnesium Oxide;50]\n"
				+ "[gt;mgo;1;40;40;Track down the particle.]\n"
				+ "[tz;40;40;jumperScript;0]\n" + "[jumper;300;720]\n"
				+ "[tw;750;1]\n" + "[tw;750;1]\n" + "[tw;925;1]\n"
				+ "[tw;925;1]\n" + "[tw;925;1]\n" + "[tw;925;1]\n"
				+ "[tw;925;1]\n" + "[tw;925;1]\n" + "[tw;925;1]\n"
				+ "[sk;WEST;240;2]\n" + "[tw;750;1]\n" + "[tw;750;1]\n"
				+ "[tw;750;1]\n" + "[tw;750;1]\n" + "[tw;750;1]\n"
				+ "[tw;750;1]\n" + "[sk;SOUTH;240;1]\n" + "[tw;500;0]\n"
				+ "[sk;NORTH;60;2]\n" + "[tw;500;0]\n" + "[sk;EAST;120;2]\n"
				+ "[tw;500;0]\n" + "[tw;500;0]\n" + "[dp;10]\n"
				+ "[tz;120;120;dangerScript;0]\n"
				+ "[msg;Weakness - Perchlorate(x2);50]\n"
				+ "[gt;perchlorate;2;40;40;Track down the particle.]\n"
				+ "[tw;1250;0]\n" + "[tw;1250;0]\n" + "[tw;1000;1]\n"
				+ "[tw;1000;1]\n" + "[tw;1000;1]\n" + "[tw;1250;0]\n"
				+ "[sk;WEST;120;2]\n" + "[tw;1250;0]\n" + "[sk;EAST;200;1]\n"
				+ "[sk;EAST;160;2]\n" + "[sk;WEST;140;2]\n"
				+ "[sk;EAST;160;2]\n" + "[sk;SOUTH;100;1]\n" + "[tw;850;1]\n"
				+ "[tw;850;1]\n" + "[tw;850;1]\n"
				+ "[msg;Weakness - Chromate;50]\n"
				+ "[gt;chromate;1;40;40;Track down the particle.]\n"
				+ "[jumper;240;520]\n" + "[sk;SOUTH;120;3]\n" + "[tw;1500;0]\n"
				+ "[tw;750;1]\n" + "[tw;750;1]\n" + "[tw;750;1]\n"
				+ "[sk;EAST;160;1]\n" + "[tw;1500;0]\n" + "[tw;950;1]\n"
				+ "[tw;950;1]\n" + "[tw;950;1]\n" + "[tw;950;1]\n"
				+ "[tw;950;1]\n" + "[tw;1500;0]\n" + "[sk;SOUTH;40;1]\n"
				+ "[tw;1500;0]\n" + "[tw;1500;0]\n" + "[tw;1500;0]\n"
				+ "[hp;20]\n" + "[tw;1500;0]\n"
				+ "[msg;Weakness - Permanganate;50]\n" + "[tw;1500;0]\n"
				+ "[tw;1500;0]\n" + "[tw;1500;0]\n"
				+ "[gt;permanganate;1;40;40;Track down the particle.]\n"
				+ "[msg;Weakness - Dichromate;50]\n"
				+ "[gt;dichromate;1;40;40;Track down the particle.]\n"
				+ "[tz;120;120;endScript;0]\n" + "[jumper;200;780]\n"
				+ "[terminal;LevelTwo]\n";

		neutronScript = new MessagePopupEntity(game, 40,
			"It appears that a supercharged-n" + "particle has made its way-n"
					+ "through this section of the-n"
					+ "asteroid. There exists the-n"
					+ "possibility that it was-n"
					+ "pulled in by the Radonite.", null);

		jumperScript = new MessagePopupEntity(game, 40,
			"Miners previously used these-n"
					+ "jumpers to transfer vast amounts-n"
					+ "of material from one region to-n"
					+ "another. We may be able to use-n"
					+ "them to our advantage.", null);

		dangerScript = new MessagePopupEntity(game, 40,
			"Malfunctioning droids detected.", null);

		endScript = new MessagePopupEntity(game, 40,
			"It appears that the particle-n" + "has been trapped within the-n"
					+ "nearby ore. We have insufficient-n"
					+ "information to track down its-n"
					+ "previous destination. ", null);

		this.levelConstantObjects.add(neutronScript);
		this.levelConstantObjects.add(jumperScript);
		this.levelConstantObjects.add(dangerScript);
		this.levelConstantObjects.add(endScript);

		// load background image
		//background = ImageLoader.get().getSprite("background.jpg");
		loadImage = ImageLoader.get().getSprite("loading_screen.png");
		game.player.setCanMove(true);
		game.setObjective("Track down the particle.");
		// load level
		loadLevel(complex);
	}

	@Override
	public void onLoad(Graphics g) {
		loadImage.draw(g, 0, 0);
		g.setColor(Color.WHITE);
		isOnLoadFinished = true;
		if (isOnLoadFinished) {
			// load game sound
			bg_sound.playSound();
		}

	}

	@Override
	public void act(String source) {
		if (source.equals("neutronScript")) {
			neutronScript.setAlive(true);
			game.setObjective("Track down the particle.");
		}
		if (source.equals("jumperScript")) {
			jumperScript.setAlive(true);
			game.setObjective("Track down the particle.");
		}
		if (source.equals("dangerScript")) {
			dangerScript.setAlive(true);
			game.setObjective("Track down the particle.");
		}
		if (source.equals("endScript")) {
			endScript.setAlive(true);
			game.setObjective("Collect the particle.");
		}
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return new LevelTwoB(game);
	}

}
