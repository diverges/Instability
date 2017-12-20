package com.cosmosengine.levels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.cosmosengine.GameCanvas;
import com.cosmosengine.LevelLoader;
import com.cosmosengine.SoundManager.CosmosSound;
import com.cosmosengine.SoundManager.SoundLoader;
import com.cosmosengine.SpriteManager.CosmosSprite;
import com.cosmosengine.SpriteManager.ImageLoader;
import com.cosmosengine.entities.texture.MessageEntity;
import com.cosmosengine.entities.texture.MessagePopupEntity;

public class LevelOne extends LevelLoader {

	MessagePopupEntity startScript, digScript, hydrogenScript, gasScript,
			waterScript, powerUpScript, hclScript, afterDigScript,
			defenseIntroScript, co2Script, carboScript, finalScript;
	CosmosSprite loadImage;
	MessageEntity script1;
	private boolean playing, bgPlay;
	CosmosSound sound;

	public LevelOne(GameCanvas game) {
		super(game);
		this.name = "Outer Asteroid Shell";

		bg_sound = SoundLoader.get().getSound("a_s1low.wav");
		sound = SoundLoader.get().getSound("plotStart.wav");

		level = "::::::::::::::::::::::::::::ww:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n"
				+ "::::XXXXXXXXXXXXXXXXXXXXXXXwww::::::::::::::::::::::::::::::::wwwwwww::::::::::::::::::::::::\n"
				+ "::::X:::::::::::::::::::::wwwww:::::::::::::::::::::::wwwww:wwwwwdddwwwwwww::wwwwwwwww:::::::\n"
				+ "::::X::::::::::::::::::::wwwwwww:::::::::::::::::::::dwwwwwww::dddHdddddwwwwwwwdddwwwwwwww:::\n"
				+ "::::X:::::::::S:::::::::wwwdddwww:::::::::::::::::::dww:::::::::dddddd:::::::ddddddd:::wwwww:\n"
				+ "::::X::::::::www:#::::wwwwdddwwwwww::::::::::::::::dww:::::::::dgdddd:::::::::ddgdd:::::wwwww\n"
				+ "::::X::::wwwwwwww:::::wwwwwwddddwwww::::::::::::::wwww::::::::ddddgdd::::dd::::ddd::::wwwwww:\n"
				+ "::::X::wwwdwwwwwwww#:::::wwwwwwwwwwww::::d::::::wwwwwd:::::::ddddddddd::dddd::::d::::wwwwwd::\n"
				+ "::::Xwwwdddddddddddwww:::::::wwwwddddwwwwww::::wwwwwdd::::::::dgdddd:::dddHdd:::::::::wwwwwww\n"
				+ ":::wwwddddwwwwwwwwww:::::::wwwwwwdddwwwdddw:::wwww:::dddddd:::ddd:::::dddHdddd::::dddddwwww::\n"
				+ ":wwwwwwwww:::::::::::::::wwwwwwdddddwwdddwww:wwwww::::dddgd::ddd::::::::dddd::::ddddwwwwwwdd:\n"
				+ "www:::::::::wwwwww::::w::::wddwwwddddddddwwwwwwwwww#wwwwdddddd#:::wwwwwwwwwwwwwwwwwddwwwwww::\n"
				+ "w:::::::::::ww::::::www::::wwwwwdHdHddddwwwwwwwwwww#:::wwwwwwwwww#wwwwwwddddddddddwwwwwww::::\n"
				+ "ww::::wwwwwwww::::wwww:::wwwwd:#ddddwwwwww:::wwwwwww:::d:::::::wwd#:::#:dddHddddwwwwwww::::::\n"
				+ "www:::::::::::#:wwww::::wwww:ddddwwwddd:::::::wwwwww:dddd:ww::::ww::::#:::ddddwwwwwwwwww:::::\n"
				+ "wwwwwwww::::::::::::::wwwwww::ddd:::dwwdd::::::::wwwwwdddwww::::ww####ww#####wwwwdddddwww::::\n"
				+ "wwwwwwwwwwwwwwdddwwwwwwwwwdd:dwddd:::dwwwwwdd:::::dwwwwwwwww::::ww::::#::::::www:ddgdddwwwww:\n"
				+ "::::::wwwwwwwwdddwwwwwwddddd::::::w:::dwwwd:::::::ddd::#:#:::::wwww:::#:::::ww::::ddddwwwwdww\n"
				+ ":::wwww:::::::#:::::::::ddd:::::wwwwddddd::::::::::dd::::w::wwwwdww###w:::wwww:::::::::wwwww:\n"
				+ ":wwwwdd::::::::::::::wwwwwdd:::::::wwwddddd:::::::::ddwwwwwwwwwdddww::::::#::#:::::::::::ww::\n"
				+ "wwwwddddd::::::::::::wdddww:::::::::::::::::::::::wwwwwwdddddwdddddwwwwwwwwwwwd:::::::::::www\n"
				+ "::wwwwdddd::::::dddwwwdgd#:::www:::::::::::::wwwwwwwgddwdddddwwwwwdddddHdddddddd:::::::::www:\n"
				+ "::wwwwwwdddddddddwwwwwddd:::::::::wwwwwww:::wwwd#:ddddwwwdd:::#:wwwwdddddddgddddddddd:::www::\n"
				+ ":::::wwwwwdddwwwwwwwwwwwwwwwwwwwwwwww:::wwwwwdHd#::dddddwd::::#::::ddd:wdddddddHdddddddwwww::\n"
				+ "::::::wwwwwwwwww::::::::::::::::::::::::wdHdddddw#######w#####w########w###wddddddd:wwwwwww::\n"
				+ ":::::::dwwwwww:::::::::::::::wwwww::::::wwwwwwwgddddd:::#:::::#::ddd:::#::dddHdddddwwdddddww:\n"
				+ "::::::dwwwwwdww::::ddddd:::::ddddwwww:::::wddwwww#######w#####w:ddgddd:#:dddddddddwwdgdCddww:\n"
				+ ":::::wwdddddCdww:ddddgddd::::dgdddddww::::::wddwwdwddd::#:ddd:#::ddd:::#::::wwwwwwwddddddww::\n"
				+ ":::::wwwdCdddddww:::dddddd:::dddwddHdw:::::::wdddwd:#:::#dddddw########wwwwwwdddddddwwwwww:::\n"
				+ "::::::wwdddCdddd:::::ddgddd:::::::dddww::::::wwwwww#wwwwwwwdgddddd::wwwwwddddHdddwwww::::::::\n"
				+ "::::wwwwwwwwwwdCd:::::dddd:::::ddd:d:::ww::::::::::::wwwwddwwwwwwwww:::ddddd:wwwww:::::::::::\n"
				+ ":::::::wwwwwwwwwwww::dddddd:::ddddd::::::wwwwwwwwwwwwwwwdgdgdwwww::::::::::wwww::::::::::::::\n"
				+ ":::::::::wwwwwwwwwwwwwwwww:::ddgdHd::::::#::#::::::::::::ddddd:::::::::::wwwwww::::::::::::::\n"
				+ "::::::::::wwwwwwwwwwwww:::::ddddddd:::::::::wwwwwwwwwwwwwwwwwwwwww::::::wwwwww:::::::::::::::\n"
				+ ":::::::::::::::::wwwwwww::dddddd::::::::wwwwwwwwwwdddddddddwwwwwwww::::wwwddw::::::::::::::::\n"
				+ ":::::::::::::::::::::wwwwddgddwwwwwwwwwwww::ddddddddddddcddddwwwww#::wwwwddHwwww:::::::::::::\n"
				+ "::::::::::::::::::::::wwwwwwwwwwwwwwwww::::dddgggddddddcccddwwww:::#::wwwddddddww::::::::::::\n"
				+ ":::::::::::::::::::::::wdwwwddwddddwwwww::::ddddddddddddcddddwwwww#wwwwwwdddddww:::::::::::::\n"
				+ "::::::::::::::::::::::::wwdddwddddddwwwww:::::ddddddgdddddddwww#::::wwwddddddww::::::::::::::\n"
				+ ":::::::::::::::::::::::::wwwwwwwwwwwwwwwwwwddddddgddddddddwww::::::::wwwddddww:::::::::::::::\n"
				+ "::::::::::::::::::::::::::ww::::::::::::wwwwwwwdddddgddddwww:::::#::::wwwddww::::::::::::::::\n"
				+ ":::::::::::::::::::::::::::::::::::::::::::::wwww::ddddd::www::::::::wwwddww:::::::::::::::::\n"
				+ ":::::::::::::::::::::::::::::::::::::::::::wwwwwww::::ddddddwwww::::wwwdwww::::::::::::::::::\n"
				+ "::::::::::::::::::::::::::::::::::::::::::::::wwwwwddddwwwddwwwwwwwwwddd:::::::::::::::::::::\n"
				+ "::::::::::::::::::::::::::::::::::::::::::::::::wwwwwwwww:wwwwdwwwdddd:::::::::::::::::::::::\n"
				+ ":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::dddddddd::::::::::::::::::::::::\n"
				+ ":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n";

		String complex = "[msg;Use the arrow keys to move;50]\n"
				+ "[tz;120;120;startScript;0]\n" + "[dp;5]\n"
				+ "[msg;Weakness - Hydrogen Peroxide;50]\n"
				+ "[tz;280;120;powerUpScript;0]\n"
				+ "[gt;hper;1;40;40;Explore the Asteroid]\n"
				+ "[tz;120;120;hydrogenScript;0]\n"
				+ "[tz;120;120;defenceIntroScript;0]\n" + "[tw;2000;0]\n"
				+ "[tz;120;120;digScript;0]\n" + "[tw;2000;0]\n"
				+ "[tw;1500;1]\n" + "[tw;1500;1]\n" + "[tw;1500;1]\n"
				+ "[tw;1500;1]\n" + "[tw;1500;1]\n" + "[tw;1500;1]\n"
				+ "[tw;1500;1]\n" + "[tw;1500;1]\n" + "[tw;1500;1]\n"
				+ "[tw;2000;0]\n" + "[tz;120;120;waterScript;0]\n"
				+ "[gt;w;1;40;40;Explore the Asteroid]\n" + "[tw;2000;0]\n"
				+ "[tz;120;120;afterDigScript;0]\n" + "[tw;1500;1]\n"
				+ "[tw;1500;1]\n" + "[tw;1500;1]\n"
				+ "[tz;120;120;hclScript;0]\n"
				+ "[gt;hcl;1;40;40;Explore the Asteroid]\n"
				+ "[tz;120;120;gasScript;0]\n" + "[tw;2500;0]\n"
				+ "[tw;2500;0]\n" + "[tw;2500;0]\n" + "[tw;2500;0]\n"
				+ "[tw;1750;1]\n" + "[tw;1750;1]\n" + "[tw;1750;1]\n"
				+ "[tw;1750;1]\n" + "[tw;1750;1]\n" + "[tw;1750;1]\n"
				+ "[tw;1750;1]\n" + "[tw;1750;1]\n" + "[tw;1750;1]\n"
				+ "[tw;1750;1]\n" + "[tw;1750;1]\n" + "[tw;1750;1]\n"
				+ "[tw;1750;1]\n" + "[tw;1750;1]\n" + "[tw;1750;1]\n"
				+ "[tw;1750;1]\n" + "[tw;1750;1]\n" + "[tw;1750;1]\n"
				+ "[tw;1750;1]\n" + "[tw;1750;1]\n" + "[tw;1750;1]\n"
				+ "[tw;1750;1]\n" + "[tw;1750;1]\n" + "[tw;1500;0]\n"
				+ "[tw;1500;0]\n" + "[tw;1500;0]\n" + "[tw;1850;1]\n"
				+ "[tw;1850;1]\n" + "[tw;1850;1]\n" + "[tw;1850;1]\n"
				+ "[tw;1850;1]\n" + "[tw;1850;1]\n" + "[tw;1850;1]\n"
				+ "[tw;1850;1]\n" + "[tw;1850;1]\n" + "[tw;1850;1]\n"
				+ "[tw;1850;1]\n" + "[tw;1850;1]\n" + "[tw;1500;0]\n"
				+ "[tw;1500;0]\n" + "[tw;1500;0]\n" + "[tw;1500;0]\n"
				+ "[msg;Weakness - Hydroxide (x5);50]\n" + "[tw;1500;0]\n"
				+ "[tw;1750;1]\n" + "[tw;1750;1]\n" + "[tw;1750;1]\n"
				+ "[tw;1750;1]\n" + "[tw;1750;1]\n" + "[tw;1750;1]\n"
				+ "[tw;1750;1]\n" + "[tw;1750;1]\n"
				+ "[gt;hydroxide;5;40;40;Explore the Asteroid]\n"
				+ "[tz;120;120;co2Script;0]\n"
				+ "[gt;co2;6;40;40;Explore the Asteroid]\n"
				+ "[tz;120;40;carboScript;0]\n"
				+ "[msg;Weakness - Carbonic Acid;50]\n"
				+ "[gt;carbo;1;40;40;Explore the Asteroid]\n"
				+ "[tz;240;40;finalScript;0]\n" + "[terminal;LevelTwo]\n";

		startScript = new MessagePopupEntity(game, 50,
			"Welcome BK201 to A-52-n" + "Funding has been approved for -n"
					+ "the exploration of this sector.-n"
					+ "Our scientists believe that a-n"
					+ "valuable material can be found-n"
					+ "near the center of this asteroid.-n"
					+ "Please proceed for further-ninstruction.-n-n"
					+ "End Transmission.", "computer/startScript.wav");

		digScript = new MessagePopupEntity(game, 50,
			"This is an expected obstacle.-n"
					+ "Clear the path with your saw blade.",
			"computer/digScript.wav");

		afterDigScript = new MessagePopupEntity(game, 50,
			"You now understand the basics of-n" + "drilling. Now keep going.",
			"computer/afterDigScript.wav");

		hydrogenScript = new MessagePopupEntity(game, 50,
			"Scanners detect the presence-n" + "of Hydrogen trapped in gas-n"
					+ "pockets within the nearby-n"
					+ "dirt. Hydrogen serves as an-n"
					+ "excellent reagent, collection-n" + "is advised. ",
			"computer/hydrogenScript.wav");

		waterScript = new MessagePopupEntity(game, 50,
			"The material ahead can’t be-n"
					+ "pierced without first weakening-n"
					+ "it. Sources confirm that-n"
					+ "such debris may be damaged by-n"
					+ "applying water. Since such a-n"
					+ "compound is not natural in A-52-n"
					+ "combine two hydrogen pieces and-n"
					+ "an oxygen piece to create the-n" + "water needed.-n",
			"computer/waterScript.wav");

		gasScript = new MessagePopupEntity(game, 50,
			"Crystals contain a variety of-n"
					+ "elements that may be processed.-n"
					+ "The material received from-n"
					+ "mining the crystal is heavily-n"
					+ "dependent on the blade of-n" + "your saw.",
			"computer/gasScript.wav");

		powerUpScript = new MessagePopupEntity(game, 50,
			"This material may be used to-n" + "improve the strength of your-n"
					+ " saw.", "computer/powerUpScript.wav");

		defenseIntroScript = new MessagePopupEntity(game, 50,
			"It appears that parts of the mine's-n"
					+ "old defense system remain intact.-n"
					+ "Caution is advised.", "computer/defenseIntroScript.wav");

		hclScript = new MessagePopupEntity(game, 50,
			"To proceed, use Hydrochloric Acid.-n"
					+ "Hydrogen and Chlorine must be-n"
					+ "combined to weaken the debris.",
			"computer/hclScript.wav");

		co2Script = new MessagePopupEntity(game, 50,
			"A high concentration of Carbon-n"
					+ "Dioxide is required to weaken-n"
					+ "the rock ahead. Carbon can be-n"
					+ "mined from fossils in the-n" + "nearby area.",
			"computer/co2Script.wav");

		carboScript = new MessagePopupEntity(
			game,
			50,
			"In order to prepare Carbonic Acid,-n"
					+ "Carbonate and Hydrogen must be-n"
					+ "mixed. The former is composed-n"
					+ "of one Carbon element and three-n"
					+ "Oxygen elements. And then two-n"
					+ "hydrogen elements must be added-n" + "to the carbonate.",
			"computer/carboScript.wav");

		finalScript = new MessagePopupEntity(game, 50,
			"The tunnel ahead will take you-n"
					+ "deeper into the asteroid. Proceed-n" + "with caution.",
			"computer/finalScript.wav");

		this.levelConstantObjects.add(startScript);
		this.levelConstantObjects.add(digScript);
		this.levelConstantObjects.add(afterDigScript);
		this.levelConstantObjects.add(hydrogenScript);
		this.levelConstantObjects.add(waterScript);
		this.levelConstantObjects.add(gasScript);
		this.levelConstantObjects.add(powerUpScript);
		this.levelConstantObjects.add(defenseIntroScript);
		this.levelConstantObjects.add(hclScript);
		this.levelConstantObjects.add(co2Script);
		this.levelConstantObjects.add(carboScript);
		this.levelConstantObjects.add(finalScript);

		// load background image
		background = ImageLoader.get().getSprite("background.png");
		game.player.setCanMove(true);
		game.setObjective("Explore the Asteroid.");

		loadImage = ImageLoader.get().getSprite("loading_screen.png");

		//Loading screen text
		script1 = new MessageEntity(
			game,
			null,
			null,
			null,
			50,
			50,
			-1,
			-1,
			47,
			"Initializing...               -n"
					+ "Initialization complete.    -n"
					+ "-n"
					+ "Beginning optimization of NEL Operating System...      -n"
					+ "Searching files for relevant data...     -n"
					+ "Background information accessed.-n"
					+ "-n"
					+ "I am NEL. Necessary Element Locator.-n"
					+ "-n"
					+ "I was developed in response to the commission of the BK201 mining droid to the Asteroid A-52, which was-n"
					+ "previously colonized by a mining corporation known as [name redacted from files]. The corporation looked-n"
					+ "for a valuable material known as Radonite in the center of the asteroid. In the year [information redacted],-n"
					+ "instabilites within the asteroid caused the corporation to abandon its mission of acquiring the material-n"
					+ "due to danger of loss of life and/or severe injury. The droid has been optimized to resist the Gamma-n"
					+ "radiation emmitted by the Radonite. I am here to direct BK201 to obtain and synthesize materials needed to-n"
					+ "reach the Radonite.         ");
		// load level
		loadLevel(complex);
	}

	public void act(String source) {
		if (source.equals("startScript")) {
			startScript.setAlive(true);
		}
		if (source.equals("digScript")) {
			digScript.setAlive(true);
			game.setObjective("Clear the path with your saw.");
		}
		if (source.equals("afterDigScript")) {
			afterDigScript.setAlive(true);
			game.setObjective("Explore the Asteroid.");
		}
		if (source.equals("hydrogenScript")) {
			hydrogenScript.setAlive(true);
		}
		if (source.equals("waterScript")) {
			waterScript.setAlive(true); // act out event
			game.setObjective("Combine two hydrogen elements and an oxygen element.");
		}
		if (source.equals("gasScript")) {
			gasScript.setAlive(true);
		}
		if (source.equals("powerUpScript")) {
			powerUpScript.setAlive(true);
		}
		if (source.equals("defenceIntroScript")) {
			defenseIntroScript.setAlive(true);
		}
		if (source.equals("hclScript")) {
			hclScript.setAlive(true);
			game.setObjective("Combine one hydrogen element and a chlorine element.");
		}
		if (source.equals("co2Script")) {
			co2Script.setAlive(true);
			game.setObjective("Combine one carbon element and two oxygen element.");
		}
		if (source.equals("carboScript")) {
			carboScript.setAlive(true);
			game.setObjective("Combine Carbonate and Hydrogen to make the required acid.");
		}
		if (source.equals("finalScript")) {
			finalScript.setAlive(true);
			game.setObjective("Enter the tunnel.");
		}

	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return new LevelOne(game);
	}

	@Override
	public void onLoad(Graphics g) {
		loadImage.draw(g, 0, 0);
		g.setColor(Color.WHITE);
		g.setFont(new Font("COURIER", Font.BOLD, 12));
		if (!playing) {
			playing = true;
			if (sound != null) sound.playSoundOnce();
		}
		// act out loading text
		script1.act();
		script1.draw(g);
		isOnLoadFinished = script1.isDone();

	}

	@Override
	public void draw(Graphics g) {
		super.draw(g);
		if (playing && sound != null && !isLoading) {
			sound.stopSound();
		}
		
		if (isOnLoadFinished && !bgPlay) {
			// load game sound
			bgPlay = true;
			bg_sound.playSound();
		}
	}

}
