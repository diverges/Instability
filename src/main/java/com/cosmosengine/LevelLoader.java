package com.cosmosengine;

import com.cosmosengine.SoundManager.CosmosSound;
import com.cosmosengine.SpriteManager.CosmosSprite;
import com.cosmosengine.entities.interactive.DrillUpEntity;
import com.cosmosengine.entities.interactive.EventTriggerZoneEntity;
import com.cosmosengine.entities.interactive.HealthUpEntity;
import com.cosmosengine.entities.interactive.JumperEntity;
import com.cosmosengine.entities.interactive.TerminalEntity;
import com.cosmosengine.entities.players.EnemyEntity;
import com.cosmosengine.entities.players.SpikeEnemyEntity;
import com.cosmosengine.entities.texture.BlockAnim;
import com.cosmosengine.entities.texture.BlockEntity;
import com.cosmosengine.entities.texture.GasBlockEntity;
import com.cosmosengine.entities.texture.GateEntity;
import com.cosmosengine.entities.texture.MessageEntity;
import com.cosmosengine.entities.texture.TimedWallEntity;
import com.cosmosengine.entities.texture.WallEntity;
import com.cosmosengine.interfaces.Loadable;
import com.cosmosengine.inventory.Item;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Converts a given text file with specific parameters into a playable level.<br />
 * ------------------- GUIDE TO LEVEL LOADING SYSTEM -------------------<br />
 * Level system is separated into a grid of 40 x 40 pixel squares, a large string is slowly split up into individual characters which hold specific information on what to draw.<br />
 * <br />
 * Note: Use "\n" to specify a new row<br />
 * <br />
 * Player ('S') : Specify the starting location of the player, if not specified then defaulted to (0, 0)<br />
 * <br />
 * A complex block may be define in the standard grid by placing an '#' - the grid will read them from left to right and connect the proper block with another stirng, reading from top to bottom and
 * left to right. --- List of Texture Elements ---<br />
 * -':' : empty space<br />
 * -'w' : WallEntity<br />
 * -'d' : Dirt Entity Block<br />
 * -'H' : Hydrogen Block<br />
 * -'A' : Manganese <br />
 * -'M' : Magnisium <br />
 * -'N' : Nitrogen <br />
 * -'R' : Chromium <br />
 * -'E' : Radonite <br />
 * -'U' : Uranium <br />
 * -'g' : Gas Block<br />
 * -'X' : No Path Block -'[b;text;reference value]' : Invisible block<br />
 * --- List of Interactive Objects ---<br />
 * -'[tz;width;height;reference name;trigger amnt]' - A trigger block with a specific name "reference name" that is used by a level to activate a specific event with that name. Trigger amount
 * specifies how many times event should execute, "1" - only once "0" - infinitely many<br />
 * -'[tw;interval;orientation] - wall that turns ON and OFF every set amount of time, can be vertical or horizontal.<br />
 * -'[jumper;x_pos;y_pos] : Jumpers map the player to a specified x and y position when player collided with them.<br />
 * -'[terminal;class_level_name] : Access different levels when passing through this object<br />
 * -'[gt;Item (d = dirt block, s = stone block... etc.);5;width;height;on death objective string] : <br />
 * -'[dp;amount] : Power-Up block that boosts the players drill power by "amount" until the next level.<br />
 * -'[hp;amount] : Restores X amount of hp when picked up. --- List of Enemy Elements ---<br />
 * -'e' : Enemy Object<br />
 * [sk;direction;distance;speed] : Specifies direction to move and distance at a certain speed. Must have a unique id set by name.
 */
public abstract class LevelLoader implements Loadable, Cloneable {

    public LevelLoader(GameCanvas game) {
        this.game = game;
    }

    protected GameCanvas game;
    // determine initial loading status
    public boolean isLoading = true;
    public boolean isOnLoadFinished = false;

    // level name
    public String name = "";

    protected String level = "";

    // Load all type of object holders
    protected ArrayList<CosmosEntity> levelTextureObjects = new ArrayList<CosmosEntity>();
    protected ArrayList<CosmosEntity> levelEnemyObjects = new ArrayList<CosmosEntity>();
    protected ArrayList<CosmosEntity> levelInteractiveObjects = new ArrayList<CosmosEntity>();
    // specify specific level objects that cannot be paused
    protected ArrayList<CosmosEntity> levelConstantObjects = new ArrayList<CosmosEntity>();

    // Load background image if any
    protected CosmosSprite background;
    protected int background_x = 0;
    protected int background_y = 0;

    // Background file
    public CosmosSound bg_sound;

    int x_player = 0; // player x
    int y_player = 0; // player y

    int complexCount = -1;

    // Part 1: Load entire level string
    // complex may be ""
    protected void loadLevel(String complex) {

        String[] complexBlocks = null;

        if (!complex.equals("")) {
            complexCount = 0;
            complexBlocks = complex.split("\n");
        }

        Scanner scan = new Scanner(level);

        int x_pos = 0; // current block x component
        int y_pos = 0; // current block y component

        y_pos = 0; // reset y

        while (scan.hasNext()) {
            String row = scan.nextLine();
            // load row
            loadRow(complexBlocks, row, x_pos, y_pos);
            if (CosmosConstants.debug)
                System.out.println(row);

            y_pos += 40; // move to next row

        }

        // Center player object
        positionCamera(x_player, y_player);

    }

    // Part 2: grab each individual row provided by loadLevel() and grab each
    // individual
    // block
    private void loadRow(String[] complexBlocks, String row, int x_pos, int y_pos) {
        x_pos = 0; // reset x
        // String param = "";
        // boolean loadComplexBlock = false;
        for (int i = 0; i < row.length(); i++) {
            char block = row.charAt(i);
            if (CosmosConstants.debug)
                System.out.println(x_pos);

            if (block != '#')
                loadBlock(block, x_pos, y_pos); // load block
            // else
            // loadComplexBlock = true; // define when loading complex blocks

            if (block == '#' && complexCount != -1) {
                loadBlock(complexBlocks[complexCount], x_pos, y_pos);
                complexCount++;
            }

			/*
             * if (loadComplexBlock && block != '[') { if (block != ']') { param = param + Character.toString(block); // load into // param } else { loadBlock(param, x_pos, y_pos); param = "";
			 * loadComplexBlock = false; // close load } } else
			 */

            x_pos += 40; // move to next block
        }
    }

    // Part 3a: loads the specific block into it's proper array
    private void loadBlock(char block, int x_pos, int y_pos) {

        // detect car type
        switch (block) {
            case 'w':
                this.levelTextureObjects.add(new WallEntity(game, null, "cosmos_block_wall.png", null, x_pos, y_pos, 40, 40, -1));
                break;
            case 'd':
                this.levelTextureObjects.add(new BlockEntity(game, "dirt_sprites", "dirt-break1.png;dirt-break8.png;dirt-break7.png;dirt-break6.png;dirt-break5.png;dirt-break4.png;dirt-break3.png;dirt-break2.png", "dirt-get1.png;dirt-get2.png;dirt-get3.png;dirt-get4.png;dirt-get5.png;dirt-get6.png", x_pos, y_pos, 40, 40, null, 40, false));
                break;
            case 'H':
                this.levelTextureObjects.add(new BlockEntity(game, "hydrogen_sprites", "hydrogen-break1.png;dirt-break8.png;dirt-break7.png;dirt-break6.png;dirt-break5.png;dirt-break4.png;dirt-break3.png;dirt-break2.png", "dirt-get1.png;dirt-get2.png;dirt-get3.png;dirt-get4.png;dirt-get5.png;dirt-get6.png", x_pos, y_pos, 40, 40, CosmosConstants.HYDROGEN, 50, true));
                break;

            case 'C':
                this.levelTextureObjects.add(new BlockEntity(game, "carbon_sprites", "carbon-break1.png;carbon-break8.png;carbon-break7.png;carbon-break6.png;carbon-break5.png;carbon-break4.png;carbon-break3.png;carbon-break2.png", "carbon-get1.png;carbon-get2.png;carbon-get3.png;carbon-get4.png;carbon-get5.png;carbon-get6.png", x_pos, y_pos, 40, 40, CosmosConstants.CARBON, 40, true));
                break;
            case 'U':
                this.levelTextureObjects.add(new BlockAnim(game, "uranium_sprites", "constant/Uranium1.png;Uranium-break7.png;Uranium-break6.png;Uranium-break5.png;Uranium-break4.png;Uranium-break3.png;Uranium-break2.png", "Uranium-get1.png;Uranium-get2.png;Uranium-get3.png;Uranium-get4.png;Uranium-get5.png;Uranium-get6.png", "Uranium1.png;Uranium2.png;Uranium3.png;Uranium4.png", x_pos, y_pos, 40, 40, 40, true, CosmosConstants.URANIUM));
                break;
            case 'E':
                this.levelTextureObjects.add(new BlockAnim(game, "uranium_sprites", "constant/Uranium1.png;Uranium-break7.png;Uranium-break6.png;Uranium-break5.png;Uranium-break4.png;Uranium-break3.png;Uranium-break2.png", "Uranium-get1.png;Uranium-get2.png;Uranium-get3.png;Uranium-get4.png;Uranium-get5.png;Uranium-get6.png", "Uranium1.png;Uranium2.png;Uranium3.png;Uranium4.png", x_pos, y_pos, 40, 40, 40, true, CosmosConstants.RADONITE));
                break;
            case 'n':
                this.levelTextureObjects.add(new BlockAnim(game, "uranium_sprites", "constant/Uranium1.png;Uranium-break7.png;Uranium-break6.png;Uranium-break5.png;Uranium-break4.png;Uranium-break3.png;Uranium-break2.png", "Uranium-get1.png;Uranium-get2.png;Uranium-get3.png;Uranium-get4.png;Uranium-get5.png;Uranium-get6.png", "Uranium1.png;Uranium2.png;Uranium3.png;Uranium4.png", x_pos, y_pos, 40, 40, 40, true, CosmosConstants.NEUTRON));
                break;
            case 'N':
                this.levelTextureObjects.add(new BlockAnim(game, "nitrogen_sprites", "constant/gasgreen1.png;gas-break7.png;gas-break6.png;gas-break5.png;gas-break4.png;gas-break3.png;gas-break2.png;gas-break1.png", "dirt-get-green1.png;dirt-get-green2.png;dirt-get-green3.png;dirt-get-green4.png;dirt-get-green5.png;dirt-get-green6.png", "gasgreen1.png;gasgreen2.png;gasgreen3.png;gasgreen4.png;gasgreen5.png;gasgreen6.png", x_pos, y_pos, 40, 40, 40, true, CosmosConstants.NITROGEN));
                break;
            case 'A':
                this.levelTextureObjects.add(new BlockEntity(game, "mn_sprites", "redcrystal.png;redcrystal-break7.png;redcrystal-break6.png;redcrystal-break5.png;redcrystal-break4.png;redcrystal-break3.png;redcrystal-break2.png;redcrystal-break1.png", "redcrystal-get1.png;redcrystal-get2.png;redcrystal-get3.png;redcrystal-get4.png;redcrystal-get5.png;redcrystal-get6.png", x_pos, y_pos, 40, 40, CosmosConstants.MANGANESE, 40, true));
                break;
            case 's':
                this.levelTextureObjects.add(new BlockAnim(game, "sulfur_sprites", "constant/gasred1.png;gas-breakred7.png;gas-breakred6.png;gas-breakred5.png;gas-breakred4.png;gas-breakred3.png;gas-breakred2.png;gas-breakred1.png", "dirt-get-redgas1.png;dirt-get-redgas2.png;dirt-get-redgas3.png;dirt-get-redgas4.png;dirt-get-redgas5.png;dirt-get-redgas6.png", "gasred1.png;gasred2.png;gasred3.png;gasred4.png;gasred5.png;gasred6.png", x_pos, y_pos, 40, 40, 40, true, CosmosConstants.SULFUR));
                break;
            case 'M':
                this.levelTextureObjects.add(new BlockEntity(game, "mg_sprites", "bluecrystal.png;bluecrystal-break7.png;bluecrystal-break6.png;bluecrystal-break5.png;bluecrystal-break4.png;bluecrystal-break3.png;bluecrystal-break2.png;bluecrystal-break1.png", "bluecrystal-get1.png;bluecrystal-get2.png;bluecrystal-get3.png;bluecrystal-get4.png;bluecrystal-get5.png;bluecrystal-get6.png", x_pos, y_pos, 40, 40, CosmosConstants.MAGNESIUM, 40, true));
                break;
            case 'g':
                this.levelTextureObjects.add(new GasBlockEntity(game, "gas_sprites", "constant/gasblue1.png;gas-breakblue7.png;gas-breakblue6.png;gas-breakblue5.png;gas-breakblue4.png;gas-breakblue3.png;gas-breakblue2.png;gas-breakblue1.png", "dirt-get-bluegas1.png;dirt-get-bluegas2.png;dirt-get-bluegas3.png;dirt-get-bluegas4.png;dirt-get-bluegas5.png;dirt-get-bluegas6.png", "gasblue1.png;gasblue2.png;gasblue3.png;gasblue4.png;gasblue5.png;gasblue6.png;", x_pos, y_pos, 40, 40, 40, true));
                break;
            case 'R':
                this.levelTextureObjects.add(new BlockEntity(game, "cr_sprites", "orangecrystal.png;orangecrystal-break7.png;orangecrystal-break6.png;orangecrystal-break5.png;orangecrystal-break4.png;orangecrystal-break3.png;orangecrystal-break2.png;orangecrystal-break1.png", "orangecrystal-get1.png;orangecrystal-get2.png;orangecrystal-get3.png;orangecrystal-get4.png;orangecrystal-get5.png;orangecrystal-get6.png", x_pos, y_pos, 40, 40, CosmosConstants.CHROMIUM, 40, true));
                break;
            case 'X':
                this.levelTextureObjects.add(new WallEntity(game, null, null, null, x_pos, y_pos, 40, 40, -1));
                break;
            case 'S':
                game.player.getBounds().x = CosmosConstants.x_Offset;
                game.player.getBounds().y = CosmosConstants.y_Offset;
                x_player = x_pos;
                y_player = y_pos;
                break;
            case 'e':
                levelEnemyObjects.add(new EnemyEntity(game, null, null, null, x_pos, y_pos, 40, 40, -1));
                break;
        }

    }

    // Part 3b: Loads an extended block
    private void loadBlock(String param, int x_pos, int y_pos) {
        param = param.substring(1, param.length() - 1);
        if (CosmosConstants.debug)
            System.out.println(param);
        String[] components = param.split(";");
        String obj = components[0];

		/*
		 * Load Texture Objects
		 */
        if (obj.equals("msg")) {
            // Load Message Entity
            levelTextureObjects.add(new MessageEntity(game, null, null, null, x_pos - 40, y_pos, 40, 40, Long.parseLong(components[2]), components[1]));
        } else if (obj.equals("tw")) {
            // Load timed wall
            // component[1] - Interval
            // component[2] - Orientation
            levelTextureObjects.add(new TimedWallEntity(game, null, null, null, x_pos, y_pos, Long.parseLong(components[1]), Integer.parseInt(components[2])));
        } else if (obj.equals("tz")) {
            levelInteractiveObjects.add(new EventTriggerZoneEntity(game, x_pos, y_pos, Integer.parseInt(components[1]), Integer.parseInt(components[2]), components[3], components[4]));
        } else if (obj.equals("gt")) {
            Item item = null;
            if (components[1].equals("d"))
                item = CosmosConstants.DIRT_BLOCK;
            else if (components[1].equals("w"))
                item = CosmosConstants.WATER;
            else if (components[1].equals("hcl"))
                item = CosmosConstants.HYDROCHLORIC_ACID;
            else if (components[1].equals("hper"))
                item = CosmosConstants.HYDROGEN_PEROXIDE;
            else if (components[1].equals("hydroxide"))
                item = CosmosConstants.HYDROXIDE;
            else if (components[1].equals("co2"))
                item = CosmosConstants.CARBON_DIOXIDE;
            else if (components[1].equals("carbo"))
                item = CosmosConstants.CARBONIC_ACID;
            else if (components[1].equals("cyanide"))
                item = CosmosConstants.CYANIDE;
            else if (components[1].equals("hcyanide"))
                item = CosmosConstants.HYDROCYANIC_ACID;
            else if (components[1].equals("sulfate"))
                item = CosmosConstants.SULFATE;
            else if (components[1].equals("hsulfate"))
                item = CosmosConstants.SULFURIC_ACID;
            else if (components[1].equals("nitrous"))
                item = CosmosConstants.NITROUS_ACID;
            else if (components[1].equals("chromate"))
                item = CosmosConstants.CHROMATE;
            else if (components[1].equals("dichromate"))
                item = CosmosConstants.DICHROMATE;
            else if (components[1].equals("mgo"))
                item = CosmosConstants.MAGNESIUM_OXIDE;
            else if (components[1].equals("perchlorate"))
                item = CosmosConstants.PERCHLORATE;
            else if (components[1].equals("permanganate"))
                item = CosmosConstants.PERMANGANATE;
            else if (components[1].equals("nuke"))
                item = CosmosConstants.NUCLEAR_FISSION;

            this.levelTextureObjects.add(new GateEntity(game, "dirt_sprites", "dirt-break1.png;dirt-break8.png;dirt-break7.png;dirt-break6.png;dirt-break5.png;dirt-break4.png;dirt-break3.png;dirt-break2.png", "dirt-get1.png;dirt-get2.png;dirt-get3.png;dirt-get4.png;dirt-get5.png;dirt-get6.png", x_pos, y_pos, Integer
                    .parseInt(components[3]), Integer.parseInt(components[4]), item, Integer.parseInt(components[2]), components[5], 40));
        }

		/*
		 * Load Interactive Objects
		 */
        if (obj.equals("jumper")) {
            // Load JumperEntity
            levelInteractiveObjects.add(new JumperEntity(game, "jumper_sprites", "jumper.png", null, x_pos, y_pos, 40, 40, -1, Integer.parseInt(components[1]), Integer.parseInt(components[2])));
        } else if (obj.equals("dp")) {
            // Load a Power-Up
            levelInteractiveObjects.add(new DrillUpEntity(game, null, "blade.png", null, x_pos, y_pos, 40, 40, -1, Integer.parseInt(components[1])));
        } else if (obj.equals("hp")) {
            // Load a Power-Up
            levelInteractiveObjects.add(new HealthUpEntity(game, null, null, null, x_pos, y_pos, 40, 40, -1, Integer.parseInt(components[1])));
        } else if (obj.equals("terminal")) {
            // Load terminal
            this.levelInteractiveObjects.add(new TerminalEntity(game, null, "tunnel.png", null, x_pos, y_pos, components[1]));
        }

		/*
		 * Load Enemy Objects
		 */
        if (obj.equals("sk")) {
            // load a spike block
            int direction = 0;
            if (components[1].equals("SOUTH"))
                direction = SpikeEnemyEntity.SOUTH;
            if (components[1].equals("NORTH"))
                direction = SpikeEnemyEntity.NORTH;
            if (components[1].equals("WEST"))
                direction = SpikeEnemyEntity.WEST;
            if (components[1].equals("EAST"))
                direction = SpikeEnemyEntity.EAST;

            this.levelEnemyObjects.add(new SpikeEnemyEntity(game, "spiked_enemy_sprites", "bot1.png;bot2.png", x_pos, y_pos, 100, direction, Integer.parseInt(components[2]), Integer.parseInt(components[3])));
        }
    }

    /**
     * Move all objects around, such that given x and y values are in center.
     *
     * @param x_pos
     * @param y_pos
     */
    // Part 4: Position player in center.
    public void positionCamera(int x, int y) {
        for (CosmosEntity obj : getLevelTextureObjects()) {
            obj.getBounds().y = ((int) (obj.getBounds().y - (y - CosmosConstants.y_Offset)));
            obj.getBounds().x = ((int) (obj.getBounds().x - (x - CosmosConstants.x_Offset)));
        }

        for (CosmosEntity obj : getLevelEnemyObjects()) {
            obj.getBounds().y = ((int) (obj.getBounds().y - (y - CosmosConstants.y_Offset)));
            obj.getBounds().x = ((int) (obj.getBounds().x - (x - CosmosConstants.x_Offset)));
        }

        for (CosmosEntity obj : getLevelInteractiveObjects()) {
            obj.getBounds().y = ((int) (obj.getBounds().y - (y - CosmosConstants.y_Offset)));
            obj.getBounds().x = ((int) (obj.getBounds().x - (x - CosmosConstants.x_Offset)));
        }

        if (background != null) {
            background_x = ((int) (background_x - (x - CosmosConstants.x_Offset)));
            background_y = ((int) (background_y - (y - CosmosConstants.y_Offset)));
        }
    }

    /**
     * Call garbage collector
     */
    public void clearLevel() {
        background = null;
        getLevelTextureObjects().clear();
        getLevelEnemyObjects().clear();
        getLevelInteractiveObjects().clear();
    }

    public synchronized void draw(final Graphics g) {
        // draw background image
        if (background != null)
            background.draw(g, background_x, background_y);
        Point playerPoint = new Point((int) game.player.getBounds().x, (int) game.player.getBounds().y);
        // Paint all level components
        try {
            List<CosmosEntity> list = new CopyOnWriteArrayList<CosmosEntity>();
            list = levelTextureObjects;
            for (CosmosEntity obj : list) {
                if (playerPoint.distance(new Point((int) obj.getBounds().x, (int) obj.getBounds().y)) < CosmosConstants.WIDTH - 100 && obj.isAlive())
                    obj.draw(g);
            }
            list = levelEnemyObjects;
            for (CosmosEntity obj : list) {
                if (playerPoint.distance(obj.getPoint()) < CosmosConstants.WIDTH - 100) { // check
                    // if
                    // object
                    // is
                    // not
                    // dead
                    obj.draw(g);
                }
            }
            list = levelInteractiveObjects;
            for (CosmosEntity obj : list) {
                if (playerPoint.distance(obj.getPoint()) < CosmosConstants.WIDTH - 100 && obj.isAlive())
                    obj.draw(g);
            }
            list = levelConstantObjects;
            for (CosmosEntity obj : list) {
                if (playerPoint.distance(obj.getPoint()) < CosmosConstants.WIDTH - 100 && obj.isAlive())
                    obj.draw(g);
            }
        } catch (Exception e) {
            // Got rid of the concurrent modification like this (hopefully it
            // won't glitch the game)
        }
    }

    /**
     * Specific action for all level based events, called by all Trigger classes.
     *
     * @param source - Target which is causing level to act
     */
    public abstract void act(String source);

    public ArrayList<CosmosEntity> getLevelTextureObjects() {
        return levelTextureObjects;
    }

    public ArrayList<CosmosEntity> getLevelEnemyObjects() {
        return levelEnemyObjects;
    }

    public ArrayList<CosmosEntity> getLevelInteractiveObjects() {
        return levelInteractiveObjects;
    }

    public ArrayList<CosmosEntity> getlevelConstantObjects() {
        return levelConstantObjects;
    }

    public abstract Object clone() throws CloneNotSupportedException;

}
