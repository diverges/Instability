package com.cosmosengine.entities.texture;

import com.cosmosengine.CosmosConstants;
import com.cosmosengine.GameCanvas;
import com.cosmosengine.entities.interactive.DroppedItemEntity;

/**
 * Extends Block Entity and drops specific items according to the drill power of the player.
 */
public class GasBlockEntity extends BlockAnim {

    public GasBlockEntity(GameCanvas game, String folder, String ref, String onDeath, String consAnim, int x, int y, int width, int height, int health, boolean respawn) {
        super(game, folder, ref, onDeath, consAnim, x, y, width, height, health, respawn, null);
    }

    @Override
    public void deathDrop() {
        if (game.player.getDamagePower() >= 10) {
            game.level.getLevelInteractiveObjects()
                      .add(new DroppedItemEntity(game, null, CosmosConstants.OXYGEN.getRef(), null, me.x, me.y, me.width, me.height, -1, CosmosConstants.OXYGEN, 1, false));
        }

        if (game.player.getDamagePower() >= 15)
            game.level.getLevelInteractiveObjects()
                      .add(new DroppedItemEntity(game, null, CosmosConstants.CHLORINE.getRef(), null, me.x, me.y, me.width, me.height, -1, CosmosConstants.CHLORINE, 1, false));
    }
}
