package lu.uni.rpg.model.Inventories;

import java.util.ArrayList;
import java.util.List;

public class PlayerInventory {
    private List<CollectableItem> items;

    public PlayerInventory() {
        items = new ArrayList<>();
    }

    public void addItem(CollectableItem item) {
        items.add(item);
    }

    public void removeItem(CollectableItem item) {
        items.remove(item);
    }

    public void displayItems() {
        if (items.isEmpty()) {
            System.out.println("Inventory is empty.");
        } else {
            System.out.println("Inventory:");
            for (CollectableItem item : items) {
                System.out.println("- " + item.getName());
            }
        }
    }
}