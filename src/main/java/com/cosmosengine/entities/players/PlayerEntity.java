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
import java.awt.Graphics2D;
import java.awt.Rectangle;
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

    private final static int COLLISION_RADIUS = (int) (100 * CosmosConstants.SCALE);
    public Rectangle collisionRectangle;

    /**
     * Sprite constructor
     *
     * @param game
     * @param folder
     * @param ref
     * @param onDeath
     * @param x
     * @param y
     * @param millis
     * @param speed
     */
    public PlayerEntity(GameCanvas game, String folder, String ref, String onDeath, int x, int y, long millis, int speed) {
        super(game, folder, ref, onDeath, x, y, 35, 35, millis);
        dX = speed;
        dY = speed;
        inventory = new Inventory(game);
    }

    @Override
    public void collision() {
        int w = me.width + (COLLISION_RADIUS);
        int h = me.height + (COLLISION_RADIUS);
        collisionRectangle = new Rectangle((int) (me.x + ((getWidth() - w) / 2)), (int) (me.y + ((getHeight() - h) / 2)), w, h);

        if (!isMoving()) {
            this.collidingWithDown.clear();
            this.collidingWithLeft.clear();
            this.collidingWithRight.clear();
            this.collidingWithUp.clear();
        } else {
            if (!isMovingUp()) {
                this.collidingWithUp.clear();
            }
            if (!isMovingDown()) {
                this.collidingWithDown.clear();
            }
            if (!isMovingLeft()) {
                this.collidingWithLeft.clear();
            }
            if (!isMovingRight()) {
                this.collidingWithRight.clear();
            }

            collidingWithRight.removeIf(wallEntity -> !wallEntity.isCollided());
            collidingWithLeft.removeIf(wallEntity -> !wallEntity.isCollided());
            collidingWithUp.removeIf(wallEntity -> !wallEntity.isCollided());
            collidingWithDown.removeIf(wallEntity -> !wallEntity.isCollided());
        }
    }

    @Override
    public void act() {

        if (this.block != null && this.isMoving()) {
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
        canMove = false;
    }

    public void forceStopMoving() {
        isMovingUp = false;
        isMovingDown = false;
        isMovingLeft = false;
        isMovingRight = false;
    }

    public boolean isMoving() {
        return (isMovingDown || isMovingRight || isMovingLeft || isMovingUp) && canMove;
    }

    public boolean isMovingRight() {
        return isMovingRight && canMove;
    }

    public boolean isMovingLeft() {
        return isMovingLeft && canMove;
    }

    public boolean isMovingUp() {
        return isMovingUp && canMove;
    }

    public boolean isMovingDown() {
        return isMovingDown && canMove;
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
        Graphics2D g2 = (Graphics2D) g;

        // display armor
        g.setColor(Color.RED);
        CosmosConstants.LAST_STRING_BOUNDS = CosmosConstants.drawStringFromTop(g2, "Armor: " + this.armor, 32, CosmosConstants.LAST_STRING_BOUNDS.y + CosmosConstants.LAST_STRING_BOUNDS.height + 5);

        // display the drill power
        g.setColor(Color.GREEN);
        CosmosConstants.LAST_STRING_BOUNDS = CosmosConstants.drawStringFromTop(g2, "Saw Power: " + this.damagePower, 32, CosmosConstants.LAST_STRING_BOUNDS.y + CosmosConstants.LAST_STRING_BOUNDS.height + 5);

        if (enteredCheatMode) {
            // entered cheat mode
            g.setColor(Color.GREEN);
            CosmosConstants.LAST_STRING_BOUNDS = CosmosConstants.drawStringFromTop(g2, "ENTER CHEAT: " + cheat, 32, CosmosConstants.LAST_STRING_BOUNDS.y + CosmosConstants.LAST_STRING_BOUNDS.height + 5);
        }

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

        if (CosmosConstants.DEBUG) {
            if (collisionRectangle != null) {
                g.setColor(Color.RED);
                g.drawRect(collisionRectangle.x, collisionRectangle.y, collisionRectangle.width, collisionRectangle.height);
            }
            g.setColor(Color.WHITE);
            if (this.collidingWithDown != null) {
                for (WallEntity entity : collidingWithDown) {
                    g.drawRect((int) entity.getX(), (int) entity.getY(), (int) entity.getWidth(), (int) entity.getHeight());
                    g.drawString(entity.getX() + ", " + entity.getY(), (int) entity.getX(), (int) entity.getY());
                }
            }
            if (this.collidingWithUp != null) {
                for (WallEntity entity : collidingWithUp) {
                    g.drawRect((int) entity.getX(), (int) entity.getY(), (int) entity.getWidth(), (int) entity.getHeight());
                    g.drawString(entity.getX() + ", " + entity.getY(), (int) entity.getX(), (int) entity.getY());
                }
            }
            if (this.collidingWithLeft != null) {
                for (WallEntity entity : collidingWithLeft) {
                    g.drawRect((int) entity.getX(), (int) entity.getY(), (int) entity.getWidth(), (int) entity.getHeight());
                    g.drawString(entity.getX() + ", " + entity.getY(), (int) entity.getX(), (int) entity.getY());
                }
            }
            if (this.collidingWithRight != null) {
                for (WallEntity entity : collidingWithRight) {
                    g.drawRect((int) entity.getX(), (int) entity.getY(), (int) entity.getWidth(), (int) entity.getHeight());
                    g.drawString(entity.getX() + ", " + entity.getY(), (int) entity.getX(), (int) entity.getY());
                }
            }
        }

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
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                case KeyEvent.VK_W:
                    isMovingUp = true;
                    break;
                case KeyEvent.VK_RIGHT:
                case KeyEvent.VK_D:
                    isMovingRight = true;
                    break;
                case KeyEvent.VK_DOWN:
                case KeyEvent.VK_S:
                    isMovingDown = true;
                    break;
                case KeyEvent.VK_LEFT:
                case KeyEvent.VK_A:
                    isMovingLeft = true;
                    break;
            }
            me.setBounds(me.x, me.y, me.width, me.height);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (enteredCheatMode) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_BACK_SPACE:
                    cheat = cheat.substring(0, cheat.length() - 1);
                    break;
                case KeyEvent.VK_PAUSE:
                    enteredCheatMode = false;
                    game.unpause();
                    break;
                case KeyEvent.VK_ENTER:
                    boolean validCheat = false;
                    switch (cheat) {
                        case CosmosConstants.LEVEL_SCREEN:
                            game.menu = new MenuLevelSelect(game);
                            validCheat = true;
                            break;
                        case CosmosConstants.GOD_MODE:
                            armor = MAXARMOR * 500;
                            damagePower = 100;
                            validCheat = true;
                            break;
                        case CosmosConstants.NO_CLIP:
                            game.noClip = !game.noClip;
                            validCheat = true;
                            break;
                        case "debug":
                            CosmosConstants.DEBUG = !CosmosConstants.DEBUG;
                            validCheat = true;
                            break;
                    }
                    if (validCheat) {
                        System.out.println("Cheat entered: " + cheat);
                        enteredCheatMode = false;
                        game.unpause();
                    }
                    cheat = "";
                    break;
                default:
                    cheat += e.getKeyChar();
                    break;
            }
        } else {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                case KeyEvent.VK_W:
                    isMovingUp = false;
                    break;
                case KeyEvent.VK_RIGHT:
                case KeyEvent.VK_D:
                    isMovingRight = false;
                    break;
                case KeyEvent.VK_DOWN:
                case KeyEvent.VK_S:
                    isMovingDown = false;
                    break;
                case KeyEvent.VK_LEFT:
                case KeyEvent.VK_A:
                    isMovingLeft = false;
                    break;
                case KeyEvent.VK_PAUSE:
                    if (game.level != null && !game.level.isLoading) {
                        enteredCheatMode = true;
                        game.pause();
                        cheat = "";
                    }
                    break;
                case KeyEvent.VK_ESCAPE:
                    if (inventory.isDisplay()) {
                        inventory.hide();
                        game.unpause();
                    } else if (game.isPaused()) {
                        game.unpause();
                    } else {
                        game.pause();
                    }
                    break;
                case KeyEvent.VK_I:
                case KeyEvent.VK_E:
                    if (inventory.isDisplay()) {
                        inventory.hide();
                        game.unpause();
                    } else {
                        inventory.show();
                        game.pause();
                    }
                    break;
                case KeyEvent.VK_ENTER:
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
                    break;
            }
        }
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
