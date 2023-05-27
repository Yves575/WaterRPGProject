package lu.uni.rpg.model.Rooms;

import lu.uni.rpg.model.Blocks.Door;
// Class of the Main Room

public class MainRoom extends Room {

    public MainRoom() {
        super();
        // Define the 6 Doors
        Door finalDoor = new Door(5, 0);
        this.setBlock(finalDoor, 5, 0);
        finalDoor.setLinkedRoomName("FINAL");
        finalDoor.setObstruct(true);

        Door puisDoor = new Door(0, 6);
        this.setBlock(puisDoor, 0, 6);
        puisDoor.setLinkedRoomName("PUIS");

        Door hubDoor = new Door(5, 10);
        this.setBlock(hubDoor, 5, 10);
        hubDoor.setLinkedRoomName("HUB");


        int[][] coordinates = {
                {3, 0}, {3, 10}, {6, 10}
        };

        String[] roomNames = {
                "POKE", "RPG", "ZOO"
        };

        for (int i = 0; i < coordinates.length; i++) {
            int x = coordinates[i][0];
            int y = coordinates[i][1];

            Door door = new Door(y, x);
            this.setBlock(door, y, x);
            door.setLinkedRoomName(roomNames[i]);
            door.setObstruct(true);
        }
    }

    @Override
    public String toString() {
        return "Main Room";
    }
}