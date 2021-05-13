package GUIForOOPProject;

import GUIForOOPProject.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class aboutController implements Initializable {


    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    BorderPane aboutPane = new BorderPane();
    @FXML
    Button closeAboutButton = new Button();

    @FXML
    public void closeAbout( ActionEvent e) throws IOException {

        Stage aboutPaneRoot = (Stage) aboutPane.getScene().getWindow();
        aboutPaneRoot.close();
    }
}
