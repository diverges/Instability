package com.cosmosengine.inventory;

import com.cosmosengine.CosmosConstants;
import com.cosmosengine.GameCanvas;
import com.cosmosengine.entities.interactive.DroppedItemEntity;
import com.cosmosengine.interfaces.Clickable;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;

import static com.cosmosengine.CosmosConstants.HEIGHT;
import static com.cosmosengine.CosmosConstants.SCALE;
import static com.cosmosengine.CosmosConstants.WIDTH;

/**
 * Used by player to store picked up items and a combining interface.
 */
public class Inventory implements Clickable {

    private boolean display = false;
    private HashMap<Integer, ItemSlot> itemSlots = new HashMap<>();
    private Point itemNamePoint = null;
    private String onItemName = null;
    private String error = null;
    private ItemSlot cS1, cS2, pS;
    private GameCanvas game;

    private static final int COL_COUNT = 12;
    private static final int SLOT_COUNT = 60;
    private static final int ROW_COUNT = (int) (SLOT_COUNT / COL_COUNT);

    private static final int SLOT_MARGIN = (int) (5 * SCALE);
    private static final int SLOT_SIZE = (int) (40 * SCALE);
    private static final int HALF_SLOT = SLOT_SIZE / 2;

    private static final int INVENTORY_MARGIN = (int) (20 * SCALE);
    private static final int INVENTORY_WIDTH = (SLOT_SIZE * COL_COUNT) + (SLOT_MARGIN * (COL_COUNT - 1)) + (INVENTORY_MARGIN * 2);
    private static final int INVENTORY_HEIGHT = (SLOT_SIZE * (ROW_COUNT + 1)) + (SLOT_MARGIN * (ROW_COUNT - 1)) + (INVENTORY_MARGIN * 3);

    public Inventory(GameCanvas game) {
        this.game = game;
        cS1 = new ItemSlot(99);
        cS2 = new ItemSlot(99);
        pS = new ItemSlot(99);
        for (int i = 0; i < SLOT_COUNT; i++) {
            itemSlots.put(i, new ItemSlot(ItemSlot.DEFAULT_MAX));
        }
    }

    private String getText() {
        if (game.getObjective().contains("two hydrogen elements and an oxygen"))
            return "Click on a Hydrogen atom twice to add two H atoms to the combine.-nThen click once on an Oxygen atom. Now you have a product. Click on it.";
        else if (error != null)
            return error;
        return null;
    }

    // draws the inventory
    public void displayInventory(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g.setColor(Color.GRAY);

        int topX = (WIDTH / 2) - (INVENTORY_WIDTH / 2);
        int topY = (HEIGHT / 2) - (INVENTORY_HEIGHT / 2);
        int marginY = topY + INVENTORY_MARGIN;
        int marginX = topX + INVENTORY_MARGIN;
        int textMargin = 10;

        Rectangle plusBounds = CosmosConstants.getStringBounds(g2, "+", topX, topY);
        Rectangle equalsBounds = CosmosConstants.getStringBounds(g2, "=", topX, topY);
        int spaceForThreeSlots = SLOT_SIZE * 3 + plusBounds.width + equalsBounds.width + (textMargin * 4);

        g.fillRect(topX, topY, INVENTORY_WIDTH, INVENTORY_HEIGHT);

        int cS1X = topX + INVENTORY_WIDTH - spaceForThreeSlots - INVENTORY_MARGIN;
        int cS2X = cS1X + SLOT_SIZE + (textMargin * 2) + plusBounds.width;
        int pSX = cS2X + SLOT_SIZE + (textMargin * 2) + equalsBounds.width;

        g.setColor(Color.BLACK);
        Font temp = g.getFont();
        g.setFont(new Font(temp.getFontName(), Font.BOLD, (int) (20 * SCALE)));

        CosmosConstants.drawStringFromTop(g2, "+", cS1X + SLOT_SIZE + textMargin, marginY + HALF_SLOT - (plusBounds.height / 2));
        CosmosConstants.drawStringFromTop(g2, "=", cS2X + SLOT_SIZE + textMargin, marginY + HALF_SLOT - (equalsBounds.height / 2));
        g.setFont(temp);

        cS1.draw(g, cS1X, marginY, SLOT_SIZE);
        cS2.draw(g, cS2X, marginY, SLOT_SIZE);
        pS.draw(g, pSX, marginY, SLOT_SIZE);

        int xPos = marginX;
        int yPos = marginY + SLOT_SIZE + INVENTORY_MARGIN;
        int colCount = 0;
        for (int i = 0; i < itemSlots.size(); i++) {
            itemSlots.get(i).draw(g, xPos, yPos, SLOT_SIZE);
            colCount++;

            xPos += SLOT_MARGIN + SLOT_SIZE;
            if (colCount == COL_COUNT) {
                colCount = 0;
                xPos = marginX;
                yPos += SLOT_MARGIN + SLOT_SIZE;
            }
        }
        if (itemNamePoint != null) {
            FontRenderContext frc = g2.getFontRenderContext();
            if (onItemName != null) {
                Rectangle2D bounds = g2.getFont().getStringBounds(onItemName, frc);
                g.setColor(new Color(0f, 0f, 0f, .5f));
                if (itemNamePoint != null) {
                    g.fillRect(itemNamePoint.x, itemNamePoint.y - (int) bounds.getHeight(), (int) bounds.getWidth(), (int) bounds.getHeight());
                    g.setColor(Color.WHITE);
                    if (itemNamePoint != null)
                        g.drawString(onItemName, itemNamePoint.x, itemNamePoint.y - 3);
                }
            }
        }
        g.setColor(Color.WHITE);
        String tut = getText();
        if (tut != null) {
            String[] tuts = tut.split("-n");
            Rectangle last = new Rectangle(topX + INVENTORY_MARGIN, topY + INVENTORY_MARGIN, 0, 0);
            for (String s : tuts) {
                last = CosmosConstants.drawStringFromTop(g2, s, last.x, last.y + last.height + SLOT_MARGIN);
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
            if (slot != null)
                slot.remove(amount);
        }
    }

    /**
     * Clears the inventory from all items.
     *
     * @param slots The old slots, can be null
     */
    public void resetInv(HashMap<Integer, ItemSlot> slots) {
        if (slots == null) {
            itemSlots = new HashMap<>();
            for (int i = 0; i < SLOT_COUNT; i++) {
                itemSlots.put(i, new ItemSlot(ItemSlot.DEFAULT_MAX));
            }
        } else
            itemSlots = slots;
    }

    /**
     * @param item search for first available slot
     *
     * @return available item
     */
    private ItemSlot getAvailable(Item item, int amount) {
        ItemSlot available = null;
        for (int i = itemSlots.size() - 1; i >= 0; i--) {
            if (itemSlots.get(i).isEmpty() || (itemSlots.get(i).hasSpace(amount) && itemSlots.get(i).getItem() != null && itemSlots.get(i).getItem().equals(item)))
                if ((available != null && available.isEmpty()) || available == null)
                    available = itemSlots.get(i);
        }

        return available;

    }

    public ItemSlot hasEnough(Item item, int amount) {
        for (int i = 0; i < itemSlots.size(); i++) {
            if (itemSlots.get(i).getQuantity() >= amount && itemSlots.get(i).getItem() != null && itemSlots.get(i).getItem().equals(item))
                return itemSlots.get(i);
        }
        return null;

    }

    public void hide() {
        if (cS1.getQuantity() > 0 || cS2.getQuantity() > 0) {
            if (cS1.getItem() != null) {
                game.level.getLevelInteractiveObjects()
                          .add(new DroppedItemEntity(game, null, cS1.getItem().getRef(), null, (int) game.player.getX(), (int) game.player.getY(), 40, 40, -1, cS1.getItem(), cS1.getQuantity(), true));
                cS1.remove(cS1.getQuantity());
            }
            if (cS2.getItem() != null) {
                game.level.getLevelInteractiveObjects()
                          .add(new DroppedItemEntity(game, null, cS2.getItem().getRef(), null, (int) game.player.getX(), (int) game.player.getY(), 40, 40, -1, cS2.getItem(), cS2.getQuantity(), true));
                cS2.remove(cS2.getQuantity());
            }
            pS.remove(pS.getQuantity());
        }
        this.display = false;
        this.error = null;
        this.itemNamePoint = null;
        this.onItemName = null;
    }

    @Override
    public void mousePressed(MouseEvent event) {
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        this.mouseClicked(event);
    }

    @Override
    public void mouseMoved(MouseEvent event) {
        boolean onItem = false;
        for (int i = 0; i < itemSlots.size(); i++) {
            if (!itemSlots.get(i).isEmpty()) {
                if (itemSlots.get(i).getBounds().contains(event.getPoint())) {
                    itemNamePoint = event.getPoint();
                    onItemName = itemSlots.get(i).getItem().getName();
                    onItem = true;
                    break;
                }
            }
        }
        if (!onItem) {
            if (!cS1.isEmpty()) {
                if (cS1.getBounds().contains(event.getPoint())) {
                    itemNamePoint = event.getPoint();
                    onItemName = cS1.getItem().getName();
                    onItem = true;
                }
            }
            if (!cS2.isEmpty()) {
                if (cS2.getBounds().contains(event.getPoint())) {
                    itemNamePoint = event.getPoint();
                    onItemName = cS2.getItem().getName();
                    onItem = true;
                }
            }
            if (!pS.isEmpty()) {
                if (pS.getBounds().contains(event.getPoint())) {
                    itemNamePoint = event.getPoint();
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
    public void mouseClicked(MouseEvent event) {
        if (event.getButton() == MouseEvent.BUTTON3) {
            for (int i = 0; i < this.itemSlots.size(); i++) {
                ItemSlot slot = itemSlots.get(i);
                if (slot.getBounds().contains(event.getPoint())) {
                    if (slot.getItem() != null) {
                        game.level.getLevelInteractiveObjects()
                                  .add(new DroppedItemEntity(game, null, slot.getItem().getRef(), null, (int) game.player.getX(), (int) game.player.getY(), 40, 40, -1, slot.getItem(), 1, true));
                        slot.remove(1);
                        break;
                    }
                }
            }
        } else {
            for (int i = 0; i < this.itemSlots.size(); i++) {
                ItemSlot slot = itemSlots.get(i);
                if (slot.getBounds().contains(event.getPoint())) {
                    if (!slot.isEmpty()) {
                        if (cS1.getItem() == null || (cS1.getItem() != null && cS1.getItem().getName().equals(slot.getItem().getName()))) {
                            cS1.add(slot.getItem(), 1);
                            slot.remove(1);
                        } else if (cS2.getItem() == null || (cS2.getItem() != null && cS2.getItem().getName().equals(slot.getItem().getName()))) {
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
            if (cS1.getBounds().contains(event.getPoint())) {
                if (!cS1.isEmpty()) {
                    add(cS1.getItem(), cS1.getQuantity());
                    cS1.remove(cS1.getQuantity());
                    if (pS.getItem() != null)
                        pS.remove(pS.getQuantity());
                }
            } else if (cS2.getBounds().contains(event.getPoint())) {
                if (!cS2.isEmpty()) {
                    add(cS2.getItem(), cS2.getQuantity());
                    cS2.remove(cS2.getQuantity());
                    if (pS.getItem() != null)
                        pS.remove(pS.getQuantity());
                }
            } else if (pS.getBounds().contains(event.getPoint())) {
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
    public void mouseEntered(MouseEvent event) {

    }

    @Override
    public void mouseExited(MouseEvent event) {

    }

    @Override
    public void mouseDragged(MouseEvent event) {

    }

    public boolean isDisplay() {
        return display;
    }

    public void show() {
        display = true;
    }

    public HashMap<Integer, ItemSlot> getItemSlots() {
        return itemSlots;
    }
}
