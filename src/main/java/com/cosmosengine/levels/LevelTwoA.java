package com.cosmosengine.levels;

import com.cosmosengine.GameCanvas;
import com.cosmosengine.LevelLoader;
import com.cosmosengine.SoundManager.SoundLoader;
import com.cosmosengine.SpriteManager.CosmosSprite;
import com.cosmosengine.SpriteManager.ImageLoader;
import com.cosmosengine.entities.texture.MessagePopupEntity;

import java.awt.Color;
import java.awt.Graphics;

public class LevelTwoA extends LevelLoader {

    MessagePopupEntity levelStartScript, enemyScript, uraniumScript;
    CosmosSprite loadImage;

    public LevelTwoA(GameCanvas game) {
        super(game);
        this.name = "Asteroid - Upper Passage";
        bg_sound = SoundLoader.get().getSound("a_s2.wav");

        game.player.setDamagePower(10); // reset the players drill strength

        level = "wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww\n" + "wwwwwwwwwwwdd:ddd:::wwwwwwwwwdddw#::w#wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww\n" + "wwwwwwwwwwwdNd:d::::::#:#::#wdHd::www:wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww\n" + "wwwwwwwwwwwdddd:::::wwwwwww:wddd::www:www::::wwwwwwwwwwwwwwwwwwwwwwwwwwwwww\n" + "wwwwwwwwwwwww::ww::wwwwwwww:wwwww:::#::#::w:::::wwwwwwwwwwwwwwwwwwwwwwwwwww\n" + "wwwwwwwwwwwww##ww##wwwwww:::::#ww:wwwwwwwwwwdww:wwwwwwwwwwwwwwwwwwwwwwwwwww\n" + "wwwwwwwwwwwwwddww::dddwwww::wwwww:wwwwwwwddwww::wwwwwwwwwwwwwwwwwwwwwwwwwww\n" + "wwwwwwwwwwww:ddd:wwdddwwww::ww::::www:::dgdwdd::::#:#:wwwwwwwwwwwwwwwwwwwww\n" + "wwwwwwwwwwww#wdw#wddCdw#:::::::wwww:w:ddggdwdddwwwwww::#::#:::wwwwwwwwwwwww\n" + "wwwwwwwwwwww:w#wdwwdddwwwwwwwwwwwww:w:wddwwwww:wwwwwwddddwwww:wwwwwwwwwwwww\n" + "wwwwwwwwwwwwd:dddwwwwwwwwwwwwwwwdd#::::::::::::wwwwddHddwwwww:wwwwwwwwwwwww\n" + "wwwwwwwwwwwww:wwwwwwwwwwwwwwwwddddw:w:w::ww:wwdwwwwdgHHdwwwww:wwwwwwwwwwwww\n" + "wwwwwwwwwwwww:wwwwwwwwwwwwwwwwdggdw:::::::::::#wwdwdddddwwwww:wwwwwwwwwwwww\n" + "wwwwwwwwwwww:#:wwwwwwwwwwwwwwdssgddwwwwwwwwwwwwwdNdwwwwwwwwww:wwwwwwwwwwwww\n" + "wwwwwwwwwww:::::wwwwwwwwwwwwwdggddww:ddddd::::::dCd#w#::::::::::::::dwwwwww\n" + "wwwwwwwwww:::S:::wwwwwwwwwwwwwdddwww:dggNd::::::ddd:ww:::::dddddddddddwwwww\n" + "wwwwwwwwwww:::::wwwwwwwwwwwwwwwwwwww:ddddd:::w#:::::#::::::::::::::::wwwwww\n" + "wwwwwwwwwwww:::wwwwwwwwwwwwwwwwwwwww:::dCd::::::::::ww::::::::::::::wwwwwww\n" + "wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww::dddwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww\n" + "wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww:::::ww::::::::#:::ddddddddwwww:::wwww\n" + "wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww:#::#:::::::::::ddddddddUdd::::#:wwww\n" + "wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww::::::::::::ddddddddwwww:::wwww\n" + "wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww\n";

        String complex = "[sk;SOUTH;160;1]\n" + "[sk;SOUTH;120;1]\n" + "[msg;Weakness - Cyanide;50]\n" + "[gt;cyanide;1;40;40;Seek the source of the radiation.]\n" + "[tz;40;40;enemyScript;0]\n" + "[msg;Weakness - Hydrocyanic Acid;50]\n" + "[gt;hcyanide;1;40;40;Seek the source of the radiation.]\n" + "[tw;2500;1]\n" + "[tw;2500;1]\n" + "[tw;2500;1]\n" + "[tw;2500;1]\n" + "[sk;WEST;200;1]\n" + "[msg;Weakness - Sulfate;50]\n" + "[gt;sulfate;1;40;40;Seek the source of the radiation.]\n" + "[tw;1750;1]\n" + "[tw;1500;1]\n" + "[sk;EAST;240;1]\n" + "[msg;Weakness - Sulfuric Acid;50]\n" + "[gt;hsulfate;1;40;40;Seek the source of the radiation.]\n" + "[tw;1750;1]\n" + "[sk;EAST;480;1]\n" + "[sk;WEST;440;1]\n" + "[tz;40;40;levelStartScript;0]\n" + "[sk;SOUTH;120;1]\n" + "[sk;EAST;280;2]\n" + "[sk;EAST;440;2]\n" + "[tw;750;0]\n" + "[tz;120;120;uraniumScript;0]\n" + "[msg;Weakness - Nitrous Acid;50]\n" + "[gt;nitrous;1;40;40;Seek the source of the radiation.]\n" + "[terminal;LevelTwo]\n";

        levelStartScript = new MessagePopupEntity(game, 50, "Scanners detect a large-n" + "amount of radiation being-n" + "emitted by an unknown-n" + "source in the cavern.", null);

        enemyScript = new MessagePopupEntity(game, 50, "Leftover defence droid-n" + "from the mining operation.-n" + "Their blades can harm-n" + "our armor.", null);

        uraniumScript = enemyScript = new MessagePopupEntity(game, 70, "Source of radiation confirmed.-n-n" + "Uranium isotope located ahead.-n" + "Although not the expected-n" + "Radonite, substance may -n" + "prove valuable in our search.", null);

        this.levelConstantObjects.add(levelStartScript);
        this.levelConstantObjects.add(enemyScript);
        this.levelConstantObjects.add(uraniumScript);

        // load background image
        //background = ImageLoader.get().getSprite("background.jpg");
        loadImage = ImageLoader.get().getSprite("loading_screen.png");
        game.player.setCanMove(true);
        game.setObjective("Explore the Asteroid.");
        // load level
        loadLevel(complex);
    }

    @Override
    public void onLoad(Graphics g) {
        // Stop previous backgroudn sounds
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
        if (source.equals("levelStartScript")) {
            levelStartScript.setAlive(true);
        }
        if (source.equals("enemyScript")) {
            enemyScript.setAlive(true);
        }
        if (source.equals("uraniumScript")) {
            uraniumScript.setAlive(true);
            game.setObjective("Collect the Uranium.");
        }

    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return new LevelTwoA(game);
    }

}
