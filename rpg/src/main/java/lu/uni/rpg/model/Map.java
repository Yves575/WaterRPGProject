package lu.uni.rpg.model;

import lu.uni.rpg.model.Rooms.RpgRoom;
import lu.uni.rpg.model.Rooms.FinalRoom;
import lu.uni.rpg.model.Rooms.HubRoom;
import lu.uni.rpg.model.Rooms.MainRoom;
import lu.uni.rpg.model.Rooms.PokeRoom;
import lu.uni.rpg.model.Rooms.PuissanceRoom;
import lu.uni.rpg.model.Rooms.Room;
import lu.uni.rpg.model.Rooms.ZooRoom;

// Set a Enumeration that list each Room, It also create an instance of each of them
public enum Map {
    HUB(new HubRoom()),
    MAIN(new MainRoom()),
    PUIS(new PuissanceRoom()),
    POKE(new PokeRoom()),
    RPG(new RpgRoom()),
    ZOO(new ZooRoom()),
    FINAL(new FinalRoom());

    private Room room;

    Map(Room room) {
        this.room = room;
    }

    public Room getRoom() {
        return room;
    }
}