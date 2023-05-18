package rpg.model;

import rpg.model.Rooms.FinalRoom;
import rpg.model.Rooms.HubRoom;
import rpg.model.Rooms.MainRoom;
import rpg.model.Rooms.PokeRoom;
import rpg.model.Rooms.PuissanceRoom;
import rpg.model.Rooms.Room;
import rpg.model.Rooms.RpgRoom;
import rpg.model.Rooms.ZooRoom;

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
