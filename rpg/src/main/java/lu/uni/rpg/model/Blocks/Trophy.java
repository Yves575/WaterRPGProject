package lu.uni.rpg.model.Blocks;

public class Trophy extends Block {

    public Trophy(int x, int y) {
        super(x, y);
        Obstruct = true;
    }

    @Override
    public String getTexture() {
        return "sprites/trophy-tile.png";
    }

    @Override
    public String getString() {
        return "Trophy Block";
    }
}