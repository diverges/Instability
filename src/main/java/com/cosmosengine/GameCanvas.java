package com.cosmosengine;

import com.cosmosengine.entities.players.PlayerEntity;
import com.cosmosengine.inventory.ItemSlot;
import com.cosmosengine.levels.menu.Menu;
import com.cosmosengine.levels.menu.MenuMain;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.util.HashMap;

/**
 * The main class where all of the updating/rendering/drawing will be processed.
 */
public class GameCanvas extends Canvas implements Runnable, MouseListener, MouseMotionListener {
    private static final long serialVersionUID = -6890545626028822792L;
    /*
     * Graphics and Buffer variables.
     */
    private BufferStrategy buffer;
    private Graphics graphics;

    /*
     * Game Components
     */
    public PlayerEntity player;
    private Thread mainThread;
    public LevelLoader level; // loads the level
    public Menu menu; // holds current menu, null when playing
    public CosmosHUD hud;

    /*
     * Game Attributes
     */
    // specify the current objective to display onLoad()
    private String currentObjective = "No objective, you're in DEBUG mode...";
    private boolean isPaused = false; // control pause status if game
    public boolean noclip = false;
    private HashMap<Integer, ItemSlot> slots;

    public GameCanvas() {
        this.setIgnoreRepaint(true);
        this.setBounds(0, 0, CosmosConstants.WIDTH, CosmosConstants.HEIGHT);
        this.setBackground(Color.black);
        this.setVisible(true);

        // SoundLoader.get().getSound("background.mid").playSound();

        // define center
        CosmosConstants.X_OFFSET = CosmosConstants.WIDTH / 2;
        CosmosConstants.Y_OFFSET = CosmosConstants.HEIGHT / 2;
        player = new PlayerEntity(this, "player_sprites", "player-move01.png;player-move02.png;player-move03.png;player-move04.png;player-move05.png;", null, CosmosConstants.X_OFFSET, CosmosConstants.Y_OFFSET, 35, 35, -1, 3); // load player
        menu = new MenuMain(this); // load main menu on boot

    }

    /**
     * Loads all game components.
     */

    @Override
    public void addNotify() {
        super.addNotify(); // load canvas functions

        // double buffer
        this.createBufferStrategy(2);
        buffer = this.getBufferStrategy();

        // event listeners
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addKeyListener(player);

        // load game
        this.requestFocus();
        startGame();
    }

    /**
     * Start main thread
     */
    private void startGame() {
        if (mainThread == null) {
            mainThread = new Thread(this);
            mainThread.start();
        }
    }

    /**
     * Main thread: updates, renders, and draws the game
     */
    @Override
    public void run() {
        while (true) {
            // check if no menu is loaded
            if (menu == null) {
                if (!player.checkIfAlive()) { // if player is dead
                    player.onDeath();
                }
                if (!level.isLoading) // prevent update while rendering
                    update(); // only update level after loading finishes
                render();
                draw();

                try {
                    Thread.sleep(CosmosConstants.PERIOD);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                // Loading screen default sleep time
                if (level.isLoading) {
                    if (level.isOnLoadFinished) {
                        level.isLoading = false;
                        try {
                            /*
                             * Wait a minimum of 2 seconds before displaying the
                             * level
                             */
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

            } else { // when menu is loaded
                if (level != null)
                    level.backgroundSound.stopSound();
                level = null;
                render();
                draw();
                try {
                    Thread.sleep(CosmosConstants.PERIOD);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Update all dynamic variables of game and responsible for all drawing and
     * movement permission granted to entities.
     */
    private void update() {
        if (!isPaused && !noclip)
            collision();
        if (!isPaused)
            sideScroll();

        /*
         * Call the update , act(), method from each individual object if game
         * is not paused
         */
        if (!isPaused) {
            for (CosmosEntity obj : level.getLevelEnemyObjects())
                if (obj.isAlive())
                    obj.act(); // act if object is alive

            for (CosmosEntity obj : level.getLevelInteractiveObjects())
                if (obj.isAlive())
                    obj.act();

            for (CosmosEntity obj : level.getLevelTextureObjects())
                obj.act();

        }

        for (CosmosEntity anim : level.getlevelConstantObjects())
            if (anim.isAlive())
                anim.act();

        if (!isPaused)
            player.act(); // calls player act method, results in animation

    }

    /**
     * Detect collision
     */
    private void collision() {
        // Checks for collision objects in a 100 pixel radius.
        Point playerPoint = player.getPoint();
        for (CosmosEntity obj : level.levelTextureObjects) {
            if (playerPoint.distance(new Point((int) obj.getX(), (int) obj.getY())) < 100) {
                obj.collision();
            }
        }

        for (CosmosEntity obj : level.levelEnemyObjects) {
            if (playerPoint.distance(obj.getPoint()) < 100) {
                obj.collision();
            }
        }

        for (CosmosEntity obj : level.levelInteractiveObjects) {
            if (playerPoint.distance(new Point(obj.getPoint())) < 100) {
                obj.collision();
            }
        }
    }

    /**
     * Side-scrolling
     */
    private void sideScroll() {
        CosmosConstants.X_OFFSET = CosmosConstants.WIDTH / 2;
        CosmosConstants.Y_OFFSET = CosmosConstants.HEIGHT / 2;
        CosmosConstants.SCREEN_X_BOUND = CosmosConstants.WIDTH - CosmosConstants.X_OFFSET;
        CosmosConstants.SCREEN_Y_BOUND = CosmosConstants.HEIGHT - CosmosConstants.Y_OFFSET;
        // left and right side-scrolling
        if (player.isMoving()) {
            if (player.isMovingRight() & !player.isCollidingRight()) {
                // move room right
                for (CosmosEntity obj : level.getLevelTextureObjects())
                    obj.setX((int) (obj.getX() - player.dX));
                for (CosmosEntity obj : level.getLevelInteractiveObjects())
                    obj.setX((int) (obj.getX() - player.dX));
                for (CosmosEntity obj : level.getLevelEnemyObjects())
                    obj.setX((int) (obj.getX() - player.dX));

                level.backgroundX -= player.dX;

            }
            if (player.isMovingLeft() & !player.isCollidingLeft()) {
                // move room left
                for (CosmosEntity obj : level.getLevelTextureObjects())
                    obj.setX((int) (obj.getX() + player.dX));
                for (CosmosEntity obj : level.getLevelInteractiveObjects())
                    obj.setX((int) (obj.getX() + player.dX));
                for (CosmosEntity obj : level.getLevelEnemyObjects())
                    obj.setX((int) (obj.getX() + player.dX));

                level.backgroundX += player.dX;

            }

            // up and down side-scrolling
            if (player.isMovingDown() & !player.isCollidingDown()) {
                // move entire room down
                for (CosmosEntity obj : level.getLevelTextureObjects())
                    obj.setY((int) (obj.getY() - player.dY));
                for (CosmosEntity obj : level.getLevelInteractiveObjects())
                    obj.setY((int) (obj.getY() - player.dY));
                for (CosmosEntity obj : level.getLevelEnemyObjects())
                    obj.setY((int) (obj.getY() - player.dY));

                level.backgroundY -= player.dY;

            }
            if (player.isMovingUp() & !player.isCollidingUp()) {
                // move entire room up
                for (CosmosEntity obj : level.getLevelTextureObjects())
                    obj.setY((int) (obj.getY() + player.dY));
                for (CosmosEntity obj : level.getLevelInteractiveObjects())
                    obj.setY((int) (obj.getY() + player.dY));
                for (CosmosEntity obj : level.getLevelEnemyObjects())
                    obj.setY((int) (obj.getY() + player.dY));

                level.backgroundY += player.dY;

            }
        }

    }

    /**
     * Render components
     */
    private void render() {
        graphics = buffer.getDrawGraphics();
        // refresh image
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, CosmosConstants.WIDTH, CosmosConstants.HEIGHT);
        if (menu == null) {
            graphics.setFont(new Font("HELVETICA", Font.PLAIN, 12));
            level.draw(graphics);
            if (hud == null)
                hud = new CosmosHUD(this);
            hud.draw(graphics);
            if (CosmosConstants.DEBUG)
                debug(graphics);
            player.draw(graphics);
            if (level.isLoading) // hold on load
                level.onLoad(graphics);
        } else {
            menu.draw(graphics);
        }
    }

    private void debug(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawString("Moving Right: " + player.isMovingRight(), 25, 100);
        g.drawString("Moving Left: " + player.isMovingLeft(), 25, 115);
        g.drawString("Moving Up: " + player.isMovingUp(), 25, 130);
        g.drawString("Moving Down: " + player.isMovingDown(), 25, 145);

        g.drawString("Colliding Right: " + player.isCollidingRight(), 150, 100);
        g.drawString("Colliding Left: " + player.isCollidingLeft(), 150, 115);
        g.drawString("Colliding Up: " + player.isCollidingUp(), 150, 130);
        g.drawString("Colliding Down: " + player.isCollidingDown(), 150, 145);
    }

    /**
     * Draw components
     */
    private void draw() {
        if (!buffer.contentsLost()) {
            buffer.show();

            if (graphics != null) {
                graphics.dispose();
            }
        }
        Toolkit.getDefaultToolkit().sync();
    }

    /**
     * Load a specific level.
     *
     * @param level - level to be loaded
     */

    public void startLevel(LevelLoader level) {
        slots = new HashMap<>();
        for (int i = 0; i < player.getInventory().getItemSlots().size(); i++) {
            ItemSlot slot = player.getInventory().getItemSlots().get(i);
            slots.put(i, new ItemSlot(slot.getX(), slot.getY(), slot.getMax()));
            if (slot.getItem() != null)
                slots.get(i).add(slot.getItem(), slot.getQuantity());
        }
        player.setCanMove(true);
        if (menu != null) {
            if (menu.sound != null) {
                menu.sound.stopSound();
            }
            menu = null;
        }

        if (level != null) {
            if (level.backgroundSound != null) {
                level.backgroundSound.stopSound();
            }
            this.level = null;
        }

        this.level = level;
    }

    public void restartLevel() {
        try {
            this.level = (LevelLoader) level.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        player.getInventory().resetInv(slots);
        player.setCanMove(true);
        if (menu != null) {
            menu = null;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (player.getInventory().isDisplay()) {
            player.getInventory().mousePressed(e);
        }
        if (menu == null) {
            if (hud != null)
                hud.mousePressed(e);
        } else {
            menu.mousePressed(e);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (player.getInventory().isDisplay()) {
            player.getInventory().mouseReleased(e);
        }
        if (menu != null) { // if in a menu
            menu.mouseReleased(e);
        } else {
            player.mouseReleased(e);
            if (hud != null)
                hud.mouseReleased(e);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (menu == null) {
            player.mouseDragged(e);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (player.getInventory().isDisplay())
            player.getInventory().mouseMoved(e);
    }

    /**
     * Change the current game objective
     *
     * @param s
     */
    public void setObjective(String s) {
        this.currentObjective = s;
    }

    /**
     * @return current game objective
     */
    public String getObjective() {
        return currentObjective;
    }

    public void pause() {
        this.isPaused = true;
    }

    public void unpause() {
        this.isPaused = false;
    }

    public boolean isPaused() {
        return isPaused;
    }
}
