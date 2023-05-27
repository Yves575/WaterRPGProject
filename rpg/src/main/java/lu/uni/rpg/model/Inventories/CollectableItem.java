package lu.uni.rpg.model.Inventories;

import javafx.scene.image.Image;

public class CollectableItem {

    private Image sprite;
    private String name;
    private static int MAX_STACK = 10;

    public CollectableItem(String name, String spritePath) {
        this.name = name;
        this.sprite = new Image(spritePath);
    }

    public Image getSprite() {
        return sprite;
    }

    public String getName() {
        return name;
    }
}
