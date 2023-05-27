package lu.uni.rpg.controller;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lu.uni.rpg.UI.Renderer;
import lu.uni.rpg.event.EventManager;
import lu.uni.rpg.event.events.MapChangeEvent;
import lu.uni.rpg.event.listeners.*;
import lu.uni.rpg.model.Entities.Player;
import lu.uni.rpg.model.Map;

// Engine Game Class, aim to manage the overall functionalities in the game
public class RpgEngine {

    // Create an instance for the Map (Enumeration of each Room), the Player, the Renderer (UI), and
    // the Controller (Do the link between the UI and the Objects/Actions)
    private Map room;
    private Player player;
    private Renderer renderer;
    private RoomController controller;
    private final EventManager eventManager = new EventManager();

    public RpgEngine() {

        // Set the spawning room as Hub and set the player in it
        room = Map.HUB;
        // Set the spawning coordinate for the Player
        player = new Player(5, 5, room.getRoom());

        room.getRoom().setPlayer(player);

        // Create an new instance of the Renderer (UI), initiate with the corresponding room and an
        // instance of the Controller with the access of the UI and all the Objects present in the Map
        // Enumeration
        renderer = new Renderer(room.getRoom());
        controller = new RoomController(renderer, this);

        // Register event listeners
        new onPlayerChangeMap(eventManager);
        new onButtonPressed(eventManager);
        new onPlayerMoveEvent(eventManager, this);
        new onPlayerHitEntity(eventManager);
        new onEntityDie(eventManager);
        new onEntityEnterDoor(eventManager);
    }

    // Method triggered by a key or by a mouse input, it update the controller based on this input
    public void handleKey(Scene scene, Stage s) {
        // Key input
        scene.setOnKeyReleased(
                new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        switch (event.getCode()) {
                            case W:
                                controller.onArrowPress(s, 0, -1);
                                break;
                            case S:
                                controller.onArrowPress(s, 0, 1);
                                break;
                            case A:
                                controller.onArrowPress(s, -1, 0);
                                break;
                            case D:
                                controller.onArrowPress(s, 1, 0);
                                break;
                            case Z:
                                System.out.println(getMap().name());
                            default:
                                break;
                        }
                    }
                });
        // Mouse input
        scene.setOnMouseClicked(
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        // Transform the input into our scale: pixel -> 11x11 grid
                        controller.onMouseClick(s, ((int) (e.getX() / 64)), ((int) (e.getY() / 64)));
                    }
                });
    }

    // Render/Update the visual of the scene, it also give the access to the input controller
    public void render(Stage s) {
        Scene scene = (renderer).render(s);
        handleKey(scene, s);
        s.setScene(scene);
    }

    // Get the instance of the renderer
    public Renderer getRenderer() {
        return renderer;
    }

    // Get the instance of the Map
    public Map getMap() {
        return room;
    }

    // Set/Change the room within the map
    public void setMap(Map room) {
        eventManager.callEvent(new MapChangeEvent(this.room, room));
        this.room = room;
    }

    public EventManager getEventManager() {
        return eventManager;
    }

    public RoomController getController() {
        return controller;
    }

    private Stage stage;
    public void setStage(Stage stage) {
    	this.stage = stage;
    }
    public Stage getStage() {
    	return stage;
    }

    public RoomController getRoomController() {
    	return controller;
    }
}