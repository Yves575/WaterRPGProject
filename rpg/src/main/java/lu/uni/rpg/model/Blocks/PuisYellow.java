package lu.uni.rpg.model.Blocks;

public class PuisYellow extends Block {

    public PuisYellow(int x, int y) {
        super(x, y);
        Obstruct = false;
    }

    @Override
    public String getTexture() {
        return "sprites/p4-yellow-tile.png";
    }

    @Override
    public String getString() {
        return "Puissance4 Block";
    }
}