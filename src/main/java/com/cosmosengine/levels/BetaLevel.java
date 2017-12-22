package com.cosmosengine.levels;

import com.cosmosengine.CosmosConstants;
import com.cosmosengine.GameCanvas;
import com.cosmosengine.LevelLoader;
import com.cosmosengine.SpriteManager.ImageLoader;
import com.cosmosengine.entities.texture.MessageEntity;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Beta Level for testing purposes only
 */
@SuppressWarnings("WeakerAccess")
public class BetaLevel extends LevelLoader {

    private MessageEntity script1;
    private MessageEntity script2;

    public BetaLevel(GameCanvas game) {
        super(game);

        // load level
        // @formatter:off

        level = ""
                + "wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww\n"
                + "wdddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddw\n"
                + "wdddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddw\n"
                + "wdddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddw\n"
                + "wdddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddw\n"
                + "wdddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddw\n"
                + "wdddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddw\n"
                + "wdddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddw\n"
                + "wdddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddw\n"
                + "wdddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddw\n"
                + "wdddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddw\n"
                + "wdddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddw\n"
                + "wdddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddw\n"
                + "wdddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddw\n"
                + "wdddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddw\n"
                + "wdddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddw\n"
                + "wdddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddw\n"
                + "wdddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddw\n"
                + "wdddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddw\n"
                + "wdddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddw\n"
                + "wdddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddw\n"
                + "wdddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddw\n"
                + "wdddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddw\n"
                + "wdddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddw\n"
                + "wdddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddw\n"
                + "wddddddddd           dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddw\n"
                + "wddddddddd     S     dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddw\n"
                + "wddddddddd           dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddw\n"
                + "wdddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddw\n"
                + "wdddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddw\n"
                + "wdddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddw\n"
                + "wdddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddw\n"
                + "wdddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddw\n"
                + "wdddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddw\n"
                + "wdddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddw\n"
                + "wdddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddw\n"
                + "wdddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddw\n"
                + "wdddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddw\n"
                + "wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww\n";

        // @formatter:on

        this.name = "Platform 9";

        // load text
        script1 = new MessageEntity(game, null, null, null, CosmosConstants.WIDTH / 2 - 250, 75, -1, -1, 50, "TRAINING ZONE - Platform 9");

        script2 = new MessageEntity(game, null, null, null, CosmosConstants.WIDTH / 2 + 150, CosmosConstants.HEIGHT / 2 + 100, -1, -1, 50, game.getObjective()); // grab objective from main class

        // load background image
        background = ImageLoader.get().getSprite("background.jpg");
        game.player.setCanMove(true);
        // load level
        loadLevel("");
    }

    @Override
    public void onLoad(Graphics g) {
        // load loading screen
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, CosmosConstants.WIDTH, CosmosConstants.HEIGHT);
        g.setColor(Color.WHITE);

        // update scripts
        if (!script1.isDone()) {
            script1.act();
        } else {
            script2.act();
            isOnLoadFinished = script2.isDone();
        }

        // draw scripts
        script1.draw(g);
        script2.draw(g);

    }

    @Override
    public Object clone() {
        return new BetaLevel(game);
    }

    @Override
    public void act(String source) {
        // TODO Auto-generated method stub

    }

}
