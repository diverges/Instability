package com.cosmosengine;

import com.cosmosengine.inventory.Combination;
import com.cosmosengine.inventory.Item;

/**
 * This class will define all constants needed for the game.
 */
public class CosmosConstants {
	public static final long period = 10; // amount in ms to wait each loop
											// (will vary depending on
											// computer's speed)
	public static final boolean debug = false; // DEBUG CONTROL TOOL
	public static boolean sounds = true;
	/*
	 * Canvas Properties
	 */
	public static int WIDTH = 854; // screen width
	public static int HEIGHT = 480; // screen height
	public static int x_Offset = 400; // defines sidescrolling
	public static int y_Offset = 200; // defines sidescrolling
	static int screenXBound = 0;
	static int screenYBound = 0;

	/*
	 * Items
	 */
	public static final Item DIRT_BLOCK = new Item("inventory/item_dirt.png",
		"Dirt Block", true);
	public static final Item STONE_BLOCK = new Item(
		"inventory/cosmos_block_wall.png", "Stone Block", true);
	public static final Item HYDROGEN = new Item("inventory/item_hydrogen.png",
		"Hydrogen", true);
	public static final Item OXYGEN = new Item("inventory/item_oxygen.png",
		"Oxygen", true);
	public static final Item CHLORINE = new Item("inventory/item_chlorine.png",
		"Chlorine", true);
	public static final Item CARBON = new Item("inventory/item_carbon.png",
		"Carbon", true);
	public static final Item NITROGEN = new Item("inventory/item_nitrogen.png",
		"Nitrogen", true);
	public static final Item WATER = new Item("inventory/item_water.png",
		"Water", true);
	public static final Item SULFUR = new Item("inventory/item_sulfur.png",
		"Sulfur", true);
	public static final Item MAGNESIUM = new Item(
		"inventory/item_magnesium.png", "Magnesium", true);
	public static final Item MANGANESE = new Item(
		"inventory/item_manganese.png", "Manganese", true);
	public static final Item CHROMIUM = new Item("inventory/item_chromium.png",
		"Chromium", true);
	public static final Item URANIUM = new Item("inventory/item_uranium.png",
		"Uranium", true);
	public static final Item NEUTRON = new Item("inventory/item_neutron.png",
		"Neutron", true);
	public static final Item HYDROCHLORIC_ACID = new Item(
		"inventory/item_water.png", "Hydrochloric Acid", true);
	public static final Item HYDROGEN_PEROXIDE = new Item(
		"inventory/item_water.png", "Hydrogen Peroxide", true);
	public static final Item HYDROXIDE = new Item("inventory/item_water.png",
		"Hydroxide", true);
	public static final Item CARBON_DIOXIDE = new Item(
		"inventory/item_water.png", "Carbon Dioxide", true);
	public static final Item CARBONATE = new Item("inventory/item_water.png",
		"Carbonate", true);
	public static final Item CARBONIC_ACID = new Item(
		"inventory/item_water.png", "Carbonic Acid", true);
	public static final Item CYANIDE = new Item("inventory/item_water.png",
		"Cyanide", true);
	public static final Item HYDROCYANIC_ACID = new Item(
		"inventory/item_water.png", "Hydrocyanic Acid", true);
	public static final Item SULFATE = new Item("inventory/item_water.png",
		"Sulfate", true);
	public static final Item SULFURIC_ACID = new Item(
		"inventory/item_water.png", "Sulfuric Acid", true);
	public static final Item NITRITE = new Item("inventory/item_water.png",
		"Nitrite", true);
	public static final Item NITROUS_ACID = new Item(
		"inventory/item_water.png", "Nitrous Acid", true);
	public static final Item CHROMATE = new Item("inventory/item_water.png",
		"Chromate", true);
	public static final Item DICHROMATE = new Item("inventory/item_water.png",
		"Dichromate", true);
	public static final Item PERMANGANATE = new Item(
		"inventory/item_water.png", "Permanganate", true);
	public static final Item MAGNESIUM_OXIDE = new Item(
		"inventory/item_water.png", "Magnesium Oxide", true);
	public static final Item PERCHLORATE = new Item("inventory/item_water.png",
		"Perchlorate", true);
	public static final Item NUCLEAR_FISSION = new Item(
		"inventory/item_nuclear_fission.png", "Nuclear Fission", true);
	public static final Item RADONITE = new Item(
			"inventory/item_radonite.png", "Radonite", true);

	/*
	 * Combinations
	 */
	public static final Combination[] COMBINATIONS = new Combination[] {
			new Combination(DIRT_BLOCK.getName(), STONE_BLOCK.getName(),
				DIRT_BLOCK, 20, 3, 2),
			new Combination(HYDROGEN.getName(), OXYGEN.getName(), WATER, 1, 2,
				1),
			new Combination(HYDROGEN.getName(), CHLORINE.getName(),
				HYDROCHLORIC_ACID, 1, 1, 1),
			new Combination(HYDROGEN.getName(), OXYGEN.getName(),
				HYDROGEN_PEROXIDE, 1, 2, 2),
			new Combination(HYDROGEN.getName(), OXYGEN.getName(), HYDROXIDE, 1,
				1, 1),
			new Combination(CARBON.getName(), OXYGEN.getName(), CARBON_DIOXIDE,
				2, 1, 2),
			new Combination(CARBON.getName(), OXYGEN.getName(), CARBONATE, 1,
				1, 3),
			new Combination(HYDROGEN.getName(), CARBONATE.getName(),
				CARBONIC_ACID, 1, 2, 1),
			new Combination(CARBON.getName(), NITROGEN.getName(), CYANIDE, 2,
				1, 1),
			new Combination(CYANIDE.getName(), HYDROGEN.getName(),
				HYDROCYANIC_ACID, 1, 1, 1),
			new Combination(SULFUR.getName(), OXYGEN.getName(), SULFATE, 1, 1,
				4),
			new Combination(HYDROGEN.getName(), SULFATE.getName(),
				SULFURIC_ACID, 1, 2, 1),
			new Combination(NITROGEN.getName(), OXYGEN.getName(), NITRITE, 1,
				1, 2),
			new Combination(HYDROGEN.getName(), NITRITE.getName(),
				NITROUS_ACID, 1, 1, 1),
			new Combination(CHROMIUM.getName(), OXYGEN.getName(), CHROMATE, 1,
				1, 4),
			new Combination(CHROMIUM.getName(), OXYGEN.getName(), DICHROMATE,
				1, 2, 7),
			new Combination(MANGANESE.getName(), OXYGEN.getName(),
				PERMANGANATE, 1, 1, 4),
			new Combination(MAGNESIUM.getName(), OXYGEN.getName(),
				MAGNESIUM_OXIDE, 1, 1, 1),
			new Combination(CHLORINE.getName(), OXYGEN.getName(), PERCHLORATE,
				1, 1, 4),
			new Combination(URANIUM.getName(), NEUTRON.getName(), NUCLEAR_FISSION,
				1, 1, 1),

	};

	/*
	 * Cheat codes
	 */
	public static final String LEVEL_SCREEN = "disruptedsector";
	public static final String GOD_MODE = "iwillwin";
	public static final String NO_CLIP = "noclip";
}
