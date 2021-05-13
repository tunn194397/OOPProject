package GUIForOOPProject;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Objects;

public class GUIProject extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("GUI.fxml")));
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Object Oriented Programming Project - Team 1B");
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
