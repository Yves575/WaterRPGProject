package lu.uni.rpg.model.Rooms;

import lu.uni.rpg.model.Blocks.Door;
import lu.uni.rpg.model.Entities.Bulbasaur;
import lu.uni.rpg.model.Entities.Entity;
// Class of the Pokemon Room

public class PokeRoom extends Room {
    public PokeRoom() {
        super();
        // Define Door and Pokemon
        grid[3][10] = new Door(10, 3);
        ((Door) grid[3][10]).setLinkedRoomName("MAIN");

        this.summonEntity(new Bulbasaur(5, 5, this), 5, 5);
    }

    @Override
    public String toString() {
        return "Poke Room";
    }
}