package rpg.UI;

import java.util.List;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import javafx.util.Duration;
import rpg.model.Blocks.Block;
import rpg.model.Entities.Entity;
import rpg.model.Rooms.Room;
// Renderer Class, aim to update and refresh the UI visual

public class Renderer {

  // Get the instance of the current Room, set the size and Set an instance of the sprites, useful
  // for adding or removing visual
  private Room room;
  private static final int size = 11;
  private StackPane[][] sprites;

  public Renderer(Room room) {
    this.room = room;
  }

  // Main method, aim to show each element present in our room
  public Scene render(Stage s) {

    // render the grid initiated in the Room Class
    TilePane tiles = new TilePane();
    sprites = new StackPane[size][size];
    Object[][] grid = room.getGrid();
    List<Entity> entities = room.getEntities();
    tiles.setPrefColumns(size);
    tiles.setPrefRows(size);
    tiles.setTileAlignment(Pos.CENTER);

    // Create a StackPane and add the to the tile of each Blocks present in the Room, if the Object
    // is 'null', it will take the default texture floor
    for (int i = 0; i < size; ++i) {
      for (int j = 0; j < size; ++j) {
        sprites[i][j] = new StackPane();
        if (grid[i][j] instanceof Block) {
          sprites[i][j].getChildren().add(makeView(((Block) grid[i][j]).getTexture()));
          tiles.getChildren().add(sprites[i][j]);
        } else {
          sprites[i][j].getChildren().add(makeView("sprites/lighter-floor-tile.png"));
          tiles.getChildren().add(sprites[i][j]);
        }
      }
    }

    // Create a StackPane for each Entity within the room, those will not be part of the tile,
    // instead we will add them on top of them
    for (Entity entity : entities) {
      sprites[entity.getY()][entity.getX()].getChildren().add(makeView((entity).getTexture()));
    }

    // Create the Scene merging all the informations
    Scene scene = new Scene(tiles);
    return scene;
  }

  // Create an animation for the hit process, it display the hit and remove it 100ms after. Check if
  // the sprite is still present after those 100ms before remove it (there is a case where the
  // sprite is remove before the 100ms (e.g when the Player die by a hit of the NegPlayer)
  public void setHitAnimation(int x, int y) {
    sprites[y][x].getChildren().add(makeView("sprites/hit.png"));
    KeyFrame frame =
        new KeyFrame(
            Duration.millis(100),
            event -> {
              if (((sprites[y][x].getChildren().size()) > 1)) {
                sprites[y][x].getChildren().remove(1);
              }
            });
    Timeline timeline = new Timeline(frame);
    timeline.play();
  }

  // Render a given Entity (like the Player for example)
  public void setEntityRender(Entity entity) {
    sprites[entity.getY()][entity.getX()].getChildren().add(makeView(entity.getTexture()));
  }

  // Remove a given Entity
  public void removeEntityRender(Entity entity) {
    sprites[entity.getY()][entity.getX()].getChildren().remove(1);
  }

  // Text to Image Path
  private ImageView makeView(String image_str) {
    Image image = new Image(image_str);
    ImageView view = new ImageView();
    view.setImage(image);
    return view;
  }

  // Set/Change the room
  public void setRoom(Room room) {
    this.room = room;
  }
}
