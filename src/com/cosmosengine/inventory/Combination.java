package com.cosmosengine.inventory;

/**
 * Combination class which contains methods and variables needed for chemical combinations to work.
 */
public class Combination {

	private String blockName1;
	private String blockName2;
	private Item itemMade;
	private int quantityMade;
	private int quantityNeeded1;
	private int quantityNeeded2;

	/**
	 * @param blockName1
	 * @param blockName2
	 * @param itemMade
	 * @param quantityMade
	 * @param quantityNeeded1
	 * @param quantityNeeded2
	 */
	public Combination(String blockName1, String blockName2, Item itemMade,
			int quantityMade, int quantityNeeded1, int quantityNeeded2) {
		super();
		this.blockName1 = blockName1;
		this.blockName2 = blockName2;
		this.itemMade = itemMade;
		this.quantityMade = quantityMade;
		this.quantityNeeded1 = quantityNeeded1;
		this.quantityNeeded2 = quantityNeeded2;
	}

	public String getBlockName1() {
		return blockName1;
	}

	public String getBlockName2() {
		return blockName2;
	}

	public Item getItemMade() {
		return itemMade;
	}

	public int getQuantityMade() {
		return quantityMade;
	}

	public int getQuantityNeeded1() {
		return quantityNeeded1;
	}

	public int getQuantityNeeded2() {
		return quantityNeeded2;
	}

}
