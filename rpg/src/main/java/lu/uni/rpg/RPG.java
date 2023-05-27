package lu.uni.rpg;

import javafx.application.Application;
import javafx.stage.Stage;
import lu.uni.rpg.controller.RpgEngine;


// MAIN Class Aims to Initiate the Project
public class RPG extends Application {
    // main function that lunch the project
    public static void main(String[] args) {
        launch(args);
    }

    // Create an instance of the Game Engine
    private RpgEngine engine = new RpgEngine();
    private static RPG instance;

    // Set the base parameters for the UI and lunch the system
    @Override
    public void start(Stage stage) {
        engine.render(stage);
        stage.setWidth(640);
        stage.setHeight(640);
        stage.sizeToScene();
        stage.show();

        instance = this;

        engine.setStage(stage);
        engine.getRenderer().displayCenterMessage("Hello there! Go through the door and make your way to the end of the maze.", 500, 100);
    }

    public static RPG getInstance() {
        return instance;
    }

    public RpgEngine getEngine() {
        return engine;
    }
}