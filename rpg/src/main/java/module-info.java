module lu.uni.rpg {
    requires javafx.controls;
    requires javafx.fxml;


    opens lu.uni.rpg to javafx.fxml;
    exports lu.uni.rpg;
}