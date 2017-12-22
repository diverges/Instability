package com.cosmosengine;

import com.cosmosengine.entities.players.PlayerEntity;
import com.cosmosengine.inventory.ItemSlot;
import com.cosmosengine.levels.menu.Menu;
import com.cosmosengine.levels.menu.MenuMain;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.util.HashMap;

import javax.swing.SwingUtilities;

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
    public boolean noClip = false;
    private HashMap<Integer, ItemSlot> slots;

    public GameCanvas() {
        this.setIgnoreRepaint(true);
        this.setBounds(0, 0, CosmosConstants.WIDTH, CosmosConstants.HEIGHT);
        this.setBackground(Color.black);
        this.setVisible(true);

        // SoundLoader.get().getSound("background.mid").playSound();

    }

    /**
     * Loads all game components.
     */

    @Override
    public void addNotify() {
        super.addNotify(); // load canvas functions

        new Thread(() -> {
            while (getParent().getWidth() == 0) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ignored) {
                }
            }
            SwingUtilities.invokeLater(() -> {
                if (CosmosConstants.DEBUG) {
                    System.out.println("JFrame size: " + getParent().getBounds());
                    System.out.println("Canvas size: " + getBounds());
                    System.out.println("Constant size: W: " + CosmosConstants.WIDTH + " H: " + CosmosConstants.HEIGHT);
                }
                resizeCanvas(getParent().getWidth(), getParent().getHeight());

                resetBuffer();

                player = new PlayerEntity(this, "player_sprites", "player-move01.png;player-move02.png;player-move03.png;player-move04.png;player-move05.png;", null, CosmosConstants.X_OFFSET, CosmosConstants.Y_OFFSET, -1, 3); // load player
                menu = new MenuMain(this); // load main menu on boot

                // event listeners
                this.addMouseListener(this);
                this.addMouseMotionListener(this);
                this.addKeyListener(player);

                // load game
                this.requestFocus();
                startGame();
            });
        }).start();
    }

    public synchronized void resetBuffer() {
        if (buffer != null) {
            buffer.dispose();

            if (graphics != null) {
                graphics.dispose();
            }
        }
        this.createBufferStrategy(2);
        buffer = this.getBufferStrategy();
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
            if (menu == null && level != null) {
                if (!player.checkIfAlive()) { // if player is dead
                    player.onDeath();
                }
                if (!level.isLoading) // prevent update while rendering
                    update(); // only update level after loading finishes
                render();
                draw();

                try {
                    Thread.sleep(CosmosConstants.PERIOD);
                } catch (InterruptedException e) {
                    break;
                }

                if (level.isLoading) {
                    if (level.isOnLoadFinished) {
                        level.isLoading = false;
                    }
                }

            } else if (menu != null) { // when menu is loaded
                if (level != null)
                    level.backgroundSound.stopSound();
                level = null;
                render();
                draw();
                try {
                    Thread.sleep(CosmosConstants.PERIOD);
                } catch (InterruptedException e) {
                    break;
                }
            }
        }
    }

    /**
     * Update all dynamic variables of game and responsible for all drawing and
     * movement permission granted to entities.
     */
    private void update() {
        if (!noClip)
            collision();

        sideScroll();

        /*
         * Call the update , act(), method from each individual object if game
         * is not paused
         */
        if (!isPaused) {
            for (CosmosEntity obj : level.getLevelEnemyObjects()) {
                if (obj.isAlive()) {
                    obj.act();
                }
            }
            for (CosmosEntity obj : level.getLevelInteractiveObjects()) {
                if (obj.isAlive()) {
                    obj.act();
                }
            }
            for (CosmosEntity obj : level.getLevelTextureObjects()) {
                obj.act();
            }

            player.act(); // calls player act method, results in animation
        }
        for (CosmosEntity anim : level.getlevelConstantObjects()) {
            if (anim.isAlive()) {
                anim.act();
            }
        }
    }

    /**
     * Detect collision
     */
    private void collision() {
        player.collision();
        // Checks for collision objects in a radius.
        Rectangle bounds = player.collisionRectangle;
        for (CosmosEntity obj : level.levelTextureObjects) {
            if (bounds.intersects(obj.getBounds())) {
                obj.collision();
            }
        }

        for (CosmosEntity obj : level.levelEnemyObjects) {
            if (bounds.intersects(obj.getBounds())) {
                obj.collision();
            }
        }

        for (CosmosEntity obj : level.levelInteractiveObjects) {
            if (bounds.intersects(obj.getBounds())) {
                obj.collision();
            }
        }
    }


    /**
     * Side-scrolling
     */
    private void sideScroll() {
        if (player.isMoving()) {

            // left and right side-scrolling
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
    private synchronized void render() {
        try {
            graphics = buffer.getDrawGraphics();
            // refresh image
            graphics.setColor(Color.black);
            graphics.fillRect(0, 0, CosmosConstants.WIDTH, CosmosConstants.HEIGHT);
            if (menu == null) {
                graphics.setFont(CosmosConstants.DEFAULT_FONT);
                level.draw(graphics);
                if (hud == null)
                    hud = new CosmosHUD(this);
                hud.draw(graphics);
                player.draw(graphics);
                if (CosmosConstants.DEBUG)
                    debug(graphics);
                if (level.isLoading) // hold on load
                    level.onLoad(graphics);
            } else {
                menu.draw(graphics);
            }
        } catch (IllegalStateException e) {
            System.err.println("If you have a secondary monitor the issue should fix itself");
            e.printStackTrace();
        }
    }

    private void debug(Graphics g) {
        g.setColor(Color.WHITE);
        CosmosConstants.LAST_STRING_BOUNDS = CosmosConstants.drawStringFromTop((Graphics2D) g, "Moving Right: " + player.isMovingRight(), 32, CosmosConstants.LAST_STRING_BOUNDS.y + CosmosConstants.LAST_STRING_BOUNDS.height + 5);
        CosmosConstants.LAST_STRING_BOUNDS = CosmosConstants.drawStringFromTop((Graphics2D) g, "Moving Left: " + player.isMovingLeft(), 32, CosmosConstants.LAST_STRING_BOUNDS.y + CosmosConstants.LAST_STRING_BOUNDS.height + 5);
        CosmosConstants.LAST_STRING_BOUNDS = CosmosConstants.drawStringFromTop((Graphics2D) g, "Moving Up: " + player.isMovingUp(), 32, CosmosConstants.LAST_STRING_BOUNDS.y + CosmosConstants.LAST_STRING_BOUNDS.height + 5);
        CosmosConstants.LAST_STRING_BOUNDS = CosmosConstants.drawStringFromTop((Graphics2D) g, "Moving Down: " + player.isMovingDown(), 32, CosmosConstants.LAST_STRING_BOUNDS.y + CosmosConstants.LAST_STRING_BOUNDS.height + 5);

        CosmosConstants.LAST_STRING_BOUNDS = CosmosConstants.drawStringFromTop((Graphics2D) g, "Colliding Right: " + player.isCollidingRight(), 32, CosmosConstants.LAST_STRING_BOUNDS.y + CosmosConstants.LAST_STRING_BOUNDS.height + 5);
        CosmosConstants.LAST_STRING_BOUNDS = CosmosConstants.drawStringFromTop((Graphics2D) g, "Colliding Left: " + player.isCollidingLeft(), 32, CosmosConstants.LAST_STRING_BOUNDS.y + CosmosConstants.LAST_STRING_BOUNDS.height + 5);
        CosmosConstants.LAST_STRING_BOUNDS = CosmosConstants.drawStringFromTop((Graphics2D) g, "Colliding Up: " + player.isCollidingUp(), 32, CosmosConstants.LAST_STRING_BOUNDS.y + CosmosConstants.LAST_STRING_BOUNDS.height + 5);
        CosmosConstants.LAST_STRING_BOUNDS = CosmosConstants.drawStringFromTop((Graphics2D) g, "Colliding Down: " + player.isCollidingDown(), 32, CosmosConstants.LAST_STRING_BOUNDS.y + CosmosConstants.LAST_STRING_BOUNDS.height + 5);
    }

    /**
     * Draw components
     */
    private synchronized void draw() {
        try {
            if (!buffer.contentsLost()) {
                buffer.show();

                if (graphics != null) {
                    graphics.dispose();
                }
            }
        } catch (IllegalStateException e) {
            System.err.println("If you have a secondary monitor the issue should fix itself");
            e.printStackTrace();
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
            slots.put(i, new ItemSlot(slot.getMax()));
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
        this.level = (LevelLoader) level.clone();
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
        if (player != null) {
            player.setCanMove(false);
        }
    }

    public void unpause() {
        this.isPaused = false;
        if (player != null) {
            player.setCanMove(true);
        }
    }

    public boolean isPaused() {
        return isPaused;
    }

    public synchronized void resizeCanvas(int width, int height) {
        int deltaWidth = width - CosmosConstants.WIDTH;
        int deltaHeight = height - CosmosConstants.HEIGHT;

        if (CosmosConstants.DEBUG) {
            System.out.println("Resizing old width = " + CosmosConstants.WIDTH + " | old height = " + CosmosConstants.HEIGHT);
            System.out.println("Resizing new width = " + width + " | new height = " + height);
            System.out.println("Resizing delta width = " + deltaWidth + " | delta height = " + deltaHeight);
        }

        CosmosConstants.HEIGHT = height;
        CosmosConstants.WIDTH = width;

        CosmosConstants.X_OFFSET = (int) ((width / 2) - (20 * CosmosConstants.SCALE));
        CosmosConstants.Y_OFFSET = (int) ((height / 2) - (20 * CosmosConstants.SCALE));
        CosmosConstants.SCREEN_X_BOUND = CosmosConstants.WIDTH - CosmosConstants.X_OFFSET;
        CosmosConstants.SCREEN_Y_BOUND = CosmosConstants.HEIGHT - CosmosConstants.Y_OFFSET;

        setBounds(0, 0, CosmosConstants.WIDTH, CosmosConstants.HEIGHT);

        if (CosmosConstants.DEBUG) {
            System.out.println("JFrame size: " + getParent().getBounds());
            System.out.println("Canvas size: " + getBounds());
            System.out.println("Constant size: W: " + CosmosConstants.WIDTH + " H: " + CosmosConstants.HEIGHT);
        }

        if (player != null) {
            player.getBounds().x = CosmosConstants.X_OFFSET;
            player.getBounds().y = CosmosConstants.Y_OFFSET;
        }
        if (level != null) {
            level.resize(deltaWidth, deltaHeight);
        }
        if (menu != null) {
            menu.resize(deltaWidth, deltaHeight);
        }
        if (hud != null) {
            hud.resize(deltaWidth, deltaHeight);
        }
    }
}
