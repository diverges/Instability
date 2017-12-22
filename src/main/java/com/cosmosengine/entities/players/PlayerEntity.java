package com.cosmosengine.entities.players;

import com.cosmosengine.CosmosConstants;
import com.cosmosengine.CosmosEntity;
import com.cosmosengine.GameCanvas;
import com.cosmosengine.entities.texture.BlockEntity;
import com.cosmosengine.entities.texture.MessagePopupEntity;
import com.cosmosengine.entities.texture.WallEntity;
import com.cosmosengine.interfaces.Clickable;
import com.cosmosengine.interfaces.Killable;
import com.cosmosengine.inventory.Inventory;
import com.cosmosengine.levels.menu.MenuLevelSelect;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * The main player class which contains movement variables, attack variables,
 * and the inventory.
 */
public class PlayerEntity extends CosmosEntity implements Killable, Clickable, KeyListener {
    private static final int MAXARMOR = 75;
    private int armor = 75; // specific health of the player, counter to game
    private Inventory inventory;
    // over screen

    private boolean moving = false;
    private ArrayList<WallEntity> collidingWithDown = new ArrayList<>();
    private ArrayList<WallEntity> collidingWithLeft = new ArrayList<>();
    private ArrayList<WallEntity> collidingWithRight = new ArrayList<>();
    private ArrayList<WallEntity> collidingWithUp = new ArrayList<>();
    // move to

    // DAMAGE
    private int dealtDamage = -1;

    // DAMAGE on other objects
    private int damagePower = 10; // determines default damage player deals on other
    // objects
    public double distance = -1; // The closest distance to an entity block.
    public BlockEntity block = null; // not null if we need to attack this
    // block.

    private int drawStep = 0;

    // confirm player movement
    private boolean isMovingRight = false;
    private boolean isMovingLeft = false;
    private boolean isMovingUp = false;
    private boolean isMovingDown = false;

    private boolean canMove = false;

    // cheats
    private String cheat = "";
    private boolean enteredCheatMode = false;

    /**
     * Sprite constructor
     *
     * @param game
     * @param folder
     * @param ref
     * @param onDeath
     * @param x
     * @param y
     * @param width
     * @param height
     * @param millis
     * @param speed
     */
    public PlayerEntity(GameCanvas game, String folder, String ref, String onDeath, int x, int y, int width, int height, long millis, int speed) {
        super(game, folder, ref, onDeath, x, y, width, height, millis);
        dX = speed;
        dY = speed;
        inventory = new Inventory(game);
    }

    @Override
    public void collision() {
    }

    @Override
    public void act() {

        if (this.block != null && this.moving) {
            block.damageStep++;

            // after a certain interval this objects will take damage
            if (block.damageStep >= 10) {
                if (armor >= 0)
                    block.dealDamage(this.damagePower);
                block.damageStep = 0; // reset timer
            }

            if (step > stepsNeeded) {
                step = 0;
                if (getCurrent() < this.sprites.length - 1)
                    setCurrent(getCurrent() + 1);
                else
                    setCurrent(0);
            }
            step++;
        } else {
            setCurrent(0);
        }

    }

    private void resetPlayer() {
        isMovingUp = false;
        isMovingDown = false;
        isMovingLeft = false;
        isMovingRight = false;
        this.collidingWithDown.clear();
        this.collidingWithLeft.clear();
        this.collidingWithRight.clear();
        this.collidingWithUp.clear();
        moving = false;
        canMove = false;
    }

    public boolean isMoving() {
        return moving;
    }

    public boolean isMovingRight() {
        return isMovingRight;
    }

    public boolean isMovingLeft() {
        return isMovingLeft;
    }

    public boolean isMovingUp() {
        return isMovingUp;
    }

    public boolean isMovingDown() {
        return isMovingDown;
    }

    public boolean isCanMove() {
        return canMove;
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }

    public boolean isCollidingUp() {
        return !this.collidingWithUp.isEmpty();
    }

    public boolean isCollidingDown() {
        return !this.collidingWithDown.isEmpty();
    }

    public boolean isCollidingRight() {
        return !this.collidingWithRight.isEmpty();
    }

    public boolean isCollidingLeft() {
        return !this.collidingWithLeft.isEmpty();
    }

    public int getArmor() {
        return armor;
    }

    public ArrayList<WallEntity> getCollidingWithDown() {
        return collidingWithDown;
    }

    public ArrayList<WallEntity> getCollidingWithLeft() {
        return collidingWithLeft;
    }

    public ArrayList<WallEntity> getCollidingWithRight() {
        return collidingWithRight;
    }

    public ArrayList<WallEntity> getCollidingWithUp() {
        return collidingWithUp;
    }

    @Override
    public boolean checkIfAlive() {
        return armor > 0;
    }

    @Override
    public void onDeath() {
        // TODO: Animate player death. For now let's use a sleep to
        // produce the feeling of animation.
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        revive(MAXARMOR); // revive player
        // game.startLevel(game.currentLevel);
        game.restartLevel();

    }

    @Override
    public int dealDamage(int damage) {
        // subtract damage from armor
        if (armor - damage <= 0) {
            int temp = armor;
            armor = 0;
            dealtDamage = damage;
            return temp;
        } else
            armor -= damage;
        dealtDamage = damage;
        return damage; // return damage dealt (used if armor/power-up is
        // present)
    }

    public void kill() {
        armor = 0;
    }

    public void restore(int amount) {
        int armorLost = MAXARMOR - armor;
        if (amount >= armorLost)
            amount = armorLost;
        this.armor += amount;
    }

    private void revive(int armor) {
        resetPlayer();
        this.armor = armor;
    }

    /**
     * Load player interface.
     */

    @Override
    public void draw(Graphics g) {
        super.draw(g);

        // display armor
        g.setColor(Color.RED);
        g.drawString("Armor: " + this.armor, 32, 50);

        // display the drill power
        g.setColor(Color.GREEN);
        g.drawString("Saw Power: " + this.damagePower, 32, 62);

        if (dealtDamage != -1) {
            drawStep++;
            // display damage dealt to player
            g.setColor(Color.RED);
            g.drawString("" + dealtDamage, (int) (game.player.getX() + (game.player.getWidth() / 2)), (int) (game.player.getY() - 5));
            if (drawStep >= 90) {
                dealtDamage = -1;
                drawStep = 0;
            }
        }

        // display inventory
        if (this.inventory.isDisplay())
            inventory.displayInventory(g);

    }

    @Override
    public void mouseDragged(MouseEvent event) {
    }

    @Override
    public void mouseReleased(MouseEvent event) {
    }

    @Override
    public void mousePressed(final MouseEvent event) {
    }

    public int getDamagePower() {
        return damagePower;
    }

    public void setDamagePower(int damagePower) {
        this.damagePower = damagePower;
    }

    @Override
    public void mouseEntered(MouseEvent event) {
    }

    @Override
    public void mouseExited(MouseEvent event) {
    }

    @Override
    public void mouseMoved(MouseEvent event) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (canMove) {
            if (e.getKeyChar() == 'w' || e.getKeyCode() == KeyEvent.VK_UP) {
                isMovingUp = true;
                moving = true;
            }
            if (e.getKeyChar() == 'd' || e.getKeyCode() == KeyEvent.VK_RIGHT) {
                isMovingRight = true;
                moving = true;
            }
            if (e.getKeyChar() == 's' || e.getKeyCode() == KeyEvent.VK_DOWN) {
                isMovingDown = true;
                moving = true;
            }
            if (e.getKeyChar() == 'a' || e.getKeyCode() == KeyEvent.VK_LEFT) {
                isMovingLeft = true;
                moving = true;
            }
            me.setBounds(me.x, me.y, me.width, me.height);
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (enteredCheatMode) {
            cheat += e.getKeyChar();
            switch (cheat) {
                case CosmosConstants.LEVEL_SCREEN:
                    game.menu = new MenuLevelSelect(game);
                    game.unpause();
                    enteredCheatMode = false;
                    break;
                case CosmosConstants.GOD_MODE:
                    armor = MAXARMOR * 500;
                    damagePower = 100;
                    game.unpause();
                    enteredCheatMode = false;
                    break;
                case CosmosConstants.NO_CLIP:
                    game.noclip = !game.noclip;
                    game.unpause();
                    enteredCheatMode = false;
                    break;
            }
            if (!enteredCheatMode)
                System.out.println("Cheat entered: " + cheat);
        }
        if (e.getKeyCode() == KeyEvent.VK_PAUSE) {
            if (enteredCheatMode) {
                enteredCheatMode = false;
                game.unpause();
            } else {
                enteredCheatMode = true;
                game.pause();
            }
            cheat = "";
        }
        if (enteredCheatMode && e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            cheat = "";
        }
        if (e.getKeyChar() == 'w' || e.getKeyCode() == KeyEvent.VK_UP) {
            isMovingUp = false;
        }
        if (e.getKeyChar() == 'd' || e.getKeyCode() == KeyEvent.VK_RIGHT) {
            isMovingRight = false;
        }
        if (e.getKeyChar() == 's' || e.getKeyCode() == KeyEvent.VK_DOWN) {
            isMovingDown = false;
        }
        if (e.getKeyChar() == 'a' || e.getKeyCode() == KeyEvent.VK_LEFT) {
            isMovingLeft = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            if (game.isPaused())
                game.unpause();
            else
                game.pause();
        } else if (e.getKeyChar() == 'i' && !enteredCheatMode) {
            if (inventory.isDisplay()) {
                inventory.hide();
                game.unpause();
            } else {
                inventory.show();
                game.pause();
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (game.level != null) {
                if (game.level.isLoading) {
                    game.level.isLoading = false;
                    game.level.isOnLoadFinished = true;
                } else {
                    for (CosmosEntity obj : game.level.getlevelConstantObjects()) {
                        if (obj.isAlive()) {
                            if (obj instanceof MessagePopupEntity) {
                                obj.setAlive(false);
                                game.hud.setEnabled(true);
                                game.unpause();
                                break;
                            }
                        }
                    }
                }
            }
        }

        if (!isMovingUp && !isMovingDown && !isMovingLeft && !isMovingRight)
            moving = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Inventory getInventory() {
        return inventory;
    }

    @Override
    public void mouseClicked(MouseEvent event) {

    }
}
