package rpg;

import javafx.application.Application;
import javafx.stage.Stage;
import rpg.controller.RpgEngine;

// MAIN Class Aims to Initiate the Project
public class RPG extends Application {
  // main function that lunch the project
  public static void main(String[] args) {
    launch(args);
  }

  // Create an instance of the Game Engine
  private RpgEngine engine = new RpgEngine();

  // Set the base parameters for the UI and lunch the system
  @Override
  public void start(Stage stage) {
    engine.render(stage);
    stage.setWidth(640);
    stage.setHeight(640);
    stage.sizeToScene();
    stage.show();
  }
}
