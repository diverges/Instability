package com.cosmosengine.inventory;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;

import com.cosmosengine.CosmosConstants;
import com.cosmosengine.GameCanvas;
import com.cosmosengine.entities.interactive.DroppedItemEntity;
import com.cosmosengine.interfaces.Clickable;

/**
 * Used by player to store picked up items and a combining interface.
 */
public class Inventory implements Clickable {

	private boolean display = false;
	private HashMap<Integer, ItemSlot> itemSlots = new HashMap<Integer, ItemSlot>();
	private Point itemNamePoint = null;
	private String onItemName = null;
	private String error = null;
	private ItemSlot cS1, cS2, pS;
	private GameCanvas game;

	public Inventory(GameCanvas game) {
		int grid_x = CosmosConstants.WIDTH / 6 + 10;
		int grid_y = 125;
		int rowCount = 0;
		this.game = game;
		cS1 = new ItemSlot((int) (grid_x + 100), 50, 99);
		cS2 = new ItemSlot((int) (grid_x + 170), 50, 99);
		pS = new ItemSlot((int) (grid_x + 250), 50, 99);
		for (int i = 0; i <= 59; i++) {
			itemSlots
				.put(i, new ItemSlot(grid_x, grid_y, ItemSlot.DEFAULT_MAX));
			grid_x += 45;
			rowCount++;

			// only 5 level boxes per row
			if (rowCount == 12) {
				grid_y += 45;
				grid_x = CosmosConstants.WIDTH / 6 + 10;
				rowCount = 0;
			}
		}
	}

	private String getText() {
		if (game.getObjective().contains("two hydrogen elements and an oxygen")) return "Click on a Hydrogen atom twice to add two H atoms to the combine.-nThen click once on an Oxygen atom. Now you have a product. Click on it.";
		else if (error != null) return error;
		return null;
	}

	// draws the inventory
	public void displayInventory(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillRect(CosmosConstants.WIDTH / 6, 25, 559, 350);
		cS1.draw(g);
		cS2.draw(g);
		pS.draw(g);
		g.setColor(Color.BLACK);
		Font temp = g.getFont();
		g.setFont(new Font(temp.getFontName(), Font.BOLD, 20));
		g.drawString("+", cS1.x + 47, 75);
		g.drawString("=", cS2.x + 53, 75);
		g.setFont(temp);
		for (int i = 0; i < itemSlots.size(); i++) {
			itemSlots.get(i).draw(g);
		}
		if (itemNamePoint != null) {
			Graphics2D g2 = (Graphics2D) g;
			FontRenderContext frc = g2.getFontRenderContext();
			if (onItemName != null) {
				Rectangle2D bounds = g2.getFont().getStringBounds(onItemName,
					frc);
				g.setColor(new Color(0f, 0f, 0f, .5f));
				if (itemNamePoint != null) {
					g.fillRect(itemNamePoint.x,
						itemNamePoint.y - (int) bounds.getHeight(),
						(int) bounds.getWidth(), (int) bounds.getHeight());
					g.setColor(Color.WHITE);
					if (itemNamePoint != null)
						g.drawString(onItemName, itemNamePoint.x,
							itemNamePoint.y - 3);
				}
			}
		}
		g.setColor(Color.WHITE);
		String tut = getText();
		if (tut != null) {
			String[] tuts = tut.split("-n");
			int y = 105;
			for (String s : tuts) {
				g.drawString(s, CosmosConstants.WIDTH / 6 + 10, y);
				y += 15;
			}
		}
	}

	public boolean add(Item item, int amount) {
		if (amount > 0) { // check that an actual amount is being added
			ItemSlot slot = getAvailable(item, amount);
			if (slot != null) {
				slot.add(item, amount);
				return true;
			}
		}
		return false;
	}

	public void remove(Item item, int amount) {
		if (amount > 0) {
			ItemSlot slot = hasEnough(item, amount);
			if (slot != null) slot.remove(amount);
		}
	}

	/**
	 * Clears the inventory from all items.
	 * 
	 * @param slots
	 *            The old slots, can be null
	 */
	public void resetInv(HashMap<Integer, ItemSlot> slots) {
		if (slots == null) {
			int grid_x = CosmosConstants.WIDTH / 6 + 10;
			int grid_y = 125;
			int rowCount = 0;
			itemSlots = new HashMap<Integer, ItemSlot>();
			for (int i = 0; i <= 59; i++) {
				itemSlots.put(i, new ItemSlot(grid_x, grid_y,
					ItemSlot.DEFAULT_MAX));
				grid_x += 45;
				rowCount++;

				// only 5 level boxes per row
				if (rowCount == 12) {
					grid_y += 45;
					grid_x = CosmosConstants.WIDTH / 6 + 10;
					rowCount = 0;
				}
			}
		} else
			itemSlots = slots;
	}

	/**
	 * @param item
	 *            search for first available slot
	 * @return available item
	 */
	private ItemSlot getAvailable(Item item, int amount) {
		ItemSlot available = null;
		for (int i = itemSlots.size() - 1; i >= 0; i--) {
			if (itemSlots.get(i).isEmpty()
					|| (itemSlots.get(i).hasSpace(amount)
							&& itemSlots.get(i).getItem() != null && itemSlots
						.get(i).getItem().equals(item)))
				if ((available != null && available.isEmpty())
						|| available == null) available = itemSlots.get(i);
		}

		return available;

	}

	public ItemSlot hasEnough(Item item, int amount) {
		for (int i = 0; i < itemSlots.size(); i++) {
			if (itemSlots.get(i).getQuantity() >= amount
					&& itemSlots.get(i).getItem() != null
					&& itemSlots.get(i).getItem().equals(item))
				return itemSlots.get(i);
		}
		return null;

	}

	public boolean hide() {
		if (cS1.getQuantity() > 0 || cS2.getQuantity() > 0) {
			if (cS1.getItem() != null) {
				game.level.getLevelInteractiveObjects().add(
					new DroppedItemEntity(game, null, cS1.getItem().getRef(),
						null, (int) game.player.getX(), (int) game.player
							.getY(), 40, 40, -1, cS1.getItem(), cS1
							.getQuantity(), true));
				cS1.remove(cS1.getQuantity());
			}
			if (cS2.getItem() != null) {
				game.level.getLevelInteractiveObjects().add(
					new DroppedItemEntity(game, null, cS2.getItem().getRef(),
						null, (int) game.player.getX(), (int) game.player
							.getY(), 40, 40, -1, cS2.getItem(), cS2
							.getQuantity(), true));
				cS2.remove(cS2.getQuantity());
			}
			pS.remove(pS.getQuantity());
		}
		this.display = false;
		this.error = null;
		this.itemNamePoint = null;
		this.onItemName = null;
		return true;
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		this.mouseClicked(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		boolean onItem = false;
		for (int i = 0; i < itemSlots.size(); i++) {
			if (!itemSlots.get(i).isEmpty()) {
				if (itemSlots.get(i).getBounds().contains(e.getPoint())) {
					itemNamePoint = e.getPoint();
					onItemName = itemSlots.get(i).getItem().getName();
					onItem = true;
					break;
				}
			}
		}
		if (!onItem) {
			if (!cS1.isEmpty()) {
				if (cS1.getBounds().contains(e.getPoint())) {
					itemNamePoint = e.getPoint();
					onItemName = cS1.getItem().getName();
					onItem = true;
				}
			}
			if (!cS2.isEmpty()) {
				if (cS2.getBounds().contains(e.getPoint())) {
					itemNamePoint = e.getPoint();
					onItemName = cS2.getItem().getName();
					onItem = true;
				}
			}
			if (!pS.isEmpty()) {
				if (pS.getBounds().contains(e.getPoint())) {
					itemNamePoint = e.getPoint();
					onItemName = pS.getItem().getName();
					onItem = true;
				}
			}
			if (!onItem) {
				itemNamePoint = null;
				onItemName = null;
			}
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON3) {
			for (int i = 0; i < this.itemSlots.size(); i++) {
				ItemSlot slot = itemSlots.get(i);
				if (slot.getBounds().contains(e.getPoint())) {
					if (slot.getItem() != null) {
						game.level.getLevelInteractiveObjects().add(
							new DroppedItemEntity(game, null, slot.getItem()
								.getRef(), null, (int) game.player.getX(),
								(int) game.player.getY(), 40, 40, -1, slot
									.getItem(), 1, true));
						slot.remove(1);
						break;
					}
				}
			}
		} else {
			for (int i = 0; i < this.itemSlots.size(); i++) {
				ItemSlot slot = itemSlots.get(i);
				if (slot.getBounds().contains(e.getPoint())) {
					if (!slot.isEmpty()) {
						if (cS1.getItem() == null
								|| (cS1.getItem() != null && cS1.getItem()
									.getName().equals(slot.getItem().getName()))) {
							cS1.add(slot.getItem(), 1);
							slot.remove(1);
						} else if (cS2.getItem() == null
								|| (cS2.getItem() != null && cS2.getItem()
									.getName().equals(slot.getItem().getName()))) {
							cS2.add(slot.getItem(), 1);
							slot.remove(1);
						} else {
							error = "Only two elements can be combined";
						}
						error = cS1.combine(cS2, pS);
						break;
					}
				}
			}
			if (cS1.getBounds().contains(e.getPoint())) {
				if (!cS1.isEmpty()) {
					add(cS1.getItem(), cS1.getQuantity());
					cS1.remove(cS1.getQuantity());
					if (pS.getItem() != null) pS.remove(pS.getQuantity());
				}
			} else if (cS2.getBounds().contains(e.getPoint())) {
				if (!cS2.isEmpty()) {
					add(cS2.getItem(), cS2.getQuantity());
					cS2.remove(cS2.getQuantity());
					if (pS.getItem() != null) pS.remove(pS.getQuantity());
				}
			} else if (pS.getBounds().contains(e.getPoint())) {
				if (!pS.isEmpty()) {
					if (add(pS.getItem(), pS.getQuantity())) {
						cS2.remove(cS2.getQuantity());
						cS1.remove(cS1.getQuantity());
						pS.remove(pS.getQuantity());
					}
				}
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	public boolean isDisplay() {
		return display;
	}

	public boolean show() {
		display = true;
		return this.display;
	}

	public HashMap<Integer, ItemSlot> getItemSlots() {
		return itemSlots;
	}
}
