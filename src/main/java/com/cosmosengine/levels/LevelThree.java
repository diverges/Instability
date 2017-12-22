package com.cosmosengine.levels;

import com.cosmosengine.GameCanvas;
import com.cosmosengine.LevelLoader;
import com.cosmosengine.SoundManager.SoundLoader;
import com.cosmosengine.SpriteManager.CosmosSprite;
import com.cosmosengine.SpriteManager.ImageLoader;
import com.cosmosengine.entities.texture.MessagePopupEntity;

import java.awt.Color;
import java.awt.Graphics;

public class LevelThree extends LevelLoader {

    private MessagePopupEntity startScript;
    private MessagePopupEntity endScript;
    private CosmosSprite loadImage;

    public LevelThree(GameCanvas game) {
        super(game);
        this.name = "Asteroid - Core";
        backgroundSound = SoundLoader.get().getSound("a_s2.wav");

        // @formatter:off

        level = ""
                + ":::::::::::::::::::::::::::::::::::::::::w::::::::::::::::::::w::::::::::::::::::::::::::::::::::::\n"
                + "::::::::::::::::::::::::::::::::::dd::wwwdwwddwwwdwwww:www:wwww::::::::::::::::::::::::::::::::::::\n"
                + ":::::::::::::::::::::::::::::wwwwwdddwwwwwwwwwwdwwwdwwwwdwwwwww::::::::::::::::::::::::::::::::::::\n"
                + ":::::::::::::::::::::::::::wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww:::::::::::::::::::::::::::::::::\n"
                + "::::::::::::::::::::::::wwwwwwwdHddddddddddddddddddddd::::##::wwwwwwwwwwwww::::::::::::::::::::::::\n"
                + ":::::::::::::::::::::wwwwwwwddHddddHddddddgwwwwwwwwwwwwwwww#wwww::::::::wwwwwwwwwwwww::::::::::::::\n"
                + "::::::::::::::::::wwwww::::www::::dddwwwwwwwwwwwwwwww:::::::::#w##w::::::::::::wwdddwww::::::::::::\n"
                + "::::::::::::::::wwwwww::#:::ww:S::dHwwwwdddsdddddw###::::::::::::::::::www::#::#:dgddwwwwww::::::::\n"
                + "::::::::::::wwwwwwddddd::ddddw:::wwwww:ddddw#::::::wwwwwwwwwwwwww#######wwwwwwwwddddddddwwww:::::::\n"
                + ":::::::::::wwwwddddddddddddddwwwwwww:::::::::::w#####wdgddwwwwwwww:::::::www#::::::::::::wwww::::::\n"
                + "::::::::::wwwwwdgddd::::::::::::#wwwwwdddd::::ww#:::::::::w:::www#:::::wwww##wwwwwwwddddddwww::::::\n"
                + "::::::::wwwwwRwwddwwwwww##wwwwdgwwwwwwddgdd::www:d:dddd:::#:::::#:::wwwwww::::wwwwwdgddddd:ww::::::\n"
                + "::::::wwwwwddddwwww#::::::ww:#ww:#wwddwwd:#::ddddd:ddgddd:#::ddd#::wwwww:::::::wwwddddddwwww:::::::\n"
                + "::::wwwww::ddddd:wwww##wwww:::::::dddwwwww#wwwwwwwwwwwwwwwwwwdgdwwwwwwwddd::::::w:::dwwwwwww:::::::\n"
                + ":::wwww:::wwwwwwwwwww::www:::::::::ddd::::::wwwwwwwwddddwdwwwwwwwwwwwwgdddd::::::::ddddwwww::::::::\n"
                + ":wwwww#:::::#::::::ww:::www::::::::dgdd:::::wwwww::::ddwwwwwwwwww:::wwww:::wwww:wwwwdwwwww:::::::::\n"
                + "::wwwww::dddw::::#www:::::::::::::dddddwwwwwwwww:#::::ddEdddd#::::::::www###ww:::ww##wwww::::::::::\n"
                + "::::wwwwddddw::wwww:::::::ww#:ww#:wwd::#:#:dwwwww::::::wwwwwwwwwww:::::www::::::::::#wwwww:::::::::\n"
                + ":::::wwwwwgdw:::::::::::::::ww::ww:wwwwwwwdddwwwwwwwwwwwwwwww:::ww::::::www#wwwwww#wwww::::::::::::\n"
                + ":::::::wwwwww::::wwwwdwwwww::::::wwwwdddddddwwwwwwwwwwwwwww:#::ww::::::www#::::::::wwww::::::::::::\n"
                + "::::::::wwwwww:::wwwddddwwwdddwwwwwdddgddddwww::wwwwwwww::::#::www::::ww:::::::::wwww::::::::::::::\n"
                + ":::::::::::wwww:::wwdgddwwdddwwwwddddddddwwww:::::ww::::::::#::ww::::wwwww:::www::www::::::::::::::\n"
                + "::::::::::wwww:::::wwwwwwdddwwwMdd:dddddwwww::::::ww:::::::#w:ww::::wwwww:::wwwwwwww:::::::::::::::\n"
                + ":::::::::::wwww:::::ddddwgwwwwwwdddddsdwww:::::w:::wwww####ww::::::wwdddddddddddd:www::::::::::::::\n"
                + ":::::::::::::wwww::ddgdddwwwwwwdddddddddd#:::::ww###w#::::::ww::::wwddddddddddgdddwww::::::::::::::\n"
                + ":::::::::::::::wwwwdwdwdwwww:wwwddNdwwwwwww#:::ww:::::#wdddddwwwwwwwwwdddwwwwwwwwww::::::::::::::::\n"
                + "::::::::::::::::wwwwwRwwwww:::wwwdCwwww::www#::www::::ww:wwwwwwwww::wwwAwwww:wwww::::::::::::::::::\n"
                + "::::::::::::::::::wwwwww:::::::wwwwww:::::www#:wwww::wwwwwwwwww::::::wwwww:::::::::::::::::::::::::\n"
                + "::::::::::::::::::::::::::::::::::::::::::wwww#wwwwwwwww:::::::::::::::::::::::::::::::::::::::::::\n"
                + ":::::::::::::::::::::::::::::::::::::::::::wwwwwwwwwwwwww::::::::::::::::::::::::::::::::::::::::::\n"
                + ":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n"
                + ":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n"
                + ":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n";

        String complex = "[tz;40;40;startScript;0]\n"
                + "[msg;Weakness - Water;50]\n"
                + "[gt;w;1;40;40;Retrieve the Radonite]\n"
                + "[sk;WEST;180;2]\n" + "[tw;950;1]\n" + "[tw;950;1]\n"
                + "[hp;50]\n" + "[sk;SOUTH;100;2]\n" + "[tw;950;0]\n"
                + "[sk;EAST;240;3]\n" + "[msg;Weakness - Sulfuric Acid;50]\n"
                + "[gt;hsulfate;1;40;40;Find the Radonite.]\n"
                + "[sk;EAST;120;2]\n" + "[tw;1000;1]\n" + "[tw;1000;1]\n"
                + "[tw;1000;1]\n" + "[tw;1000;1]\n" + "[tw;1000;1]\n"
                + "[tw;1000;1]\n" + "[tw;1000;1]\n" + "[tw;950;1]\n"
                + "[tw;950;1]\n" + "[tw;950;1]\n" + "[tw;950;1]\n"
                + "[tw;950;1]\n" + "[sk;EAST;160;3]\n" + "[sk;WEST;160;3]\n"
                + "[sk;EAST;180;2]\n" + "[sk;SOUTH;80;1]\n" + "[tw;950;1]\n"
                + "[tw;950;1]\n" + "[tw;1000;1]\n" + "[tw;1000;1]\n"
                + "[tw;650;0]\n" + "[tw;650;0]\n" + "[sk;EAST;120;2]\n"
                + "[sk;SOUTH;100;2]\n" + "[sk;SOUTH;100;2]\n"
                + "[msg;Weakness - Permanganate;50]\n" + "[tw;650;0]\n"
                + "[tw;650;0]\n" + "[tw;750;1]\n" + "[tw;750;1]\n"
                + "[gt;permanganate;1;40;40;Find the Radonite.]\n"
                + "[sk;EAST;240;2]\n" + "[tw;1000;0]\n" + "[sk;WEST;160;1]\n"
                + "[terminal;Credits]\n" + "[tz;40;40;endScript;0]\n"
                + "[tw;1050;1]\n" + "[tw;1050;1]\n" + "[tw;1050;1]\n"
                + "[tw;1050;1]\n" + "[tw;1050;1]\n" + "[sk;NORTH;100;2]\n"
                + "[sk;NORTH;100;2]\n" + "[msg;Weakness - Dichromate;50]\n"
                + "[gt;dichromate;1;40;40;Find the Radonite.]\n"
                + "[sk;WEST;200;2]\n" + "[tw;1050;1]\n" + "[tw;1050;1]\n"
                + "[tw;950;0]\n" + "[sk;EAST;160;2]\n" + "[tw;950;0]\n"
                + "[tw;950;0]\n" + "[sk;WEST;140;2]\n" + "[tw;1050;1]\n"
                + "[tw;1050;1]\n" + "[tw;1050;1]\n" + "[tw;1050;1]\n"
                + "[tw;1000;0]\n" + "[tw;750;1]\n" + "[tw;750;1]\n"
                + "[tw;750;1]\n" + "[sk;EAST;120;2]\n" + "[sk;NORTH;80;1]\n"
                + "[sk;WEST;100;2]\n" + "[sk;NORTH;80;2]\n"
                + "[sk;NORTH;80;3]\n" + "[sk;NORTH;80;4]\n";

        // @formatter:on

        startScript = new MessagePopupEntity(game, 50, "Warning! Warning! Vast-n" + "amount of radiation detected-n" + "nearby, presence of Radonite-n" + "confirmed.", null);

        endScript = new MessagePopupEntity(game, 50, "There is no mistake,-n" + "we have located the Radonite.-n" + "Great work BK201, your creator,-n" + " AgentK, will be most pleased-n" + "with your success. Now collect-n" + "the substance and begin your-n" + "voyage back to Earth.-n-n" + "N.E.L. Signing Off.", null);

        this.levelConstantObjects.add(startScript);
        this.levelConstantObjects.add(endScript);

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
        loadImage.draw(g, 0, 0);
        g.setColor(Color.WHITE);
        isOnLoadFinished = true;
        // load game sound
        backgroundSound.playSound();
    }

    @Override
    public void act(String source) {

        if (source.equals("startScript")) {
            startScript.setAlive(true);
            game.setObjective("Find the Radonite.");
        }

        if (source.equals("endScript")) {
            endScript.setAlive(true);
            game.setObjective("Collect the Radonite.");
        }

    }

    @Override
    public Object clone() {
        return new LevelThree(game);
    }

}
