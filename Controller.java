package GUIForOOPProject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static jdk.jfr.consumer.EventStream.openFile;

public class Controller implements Initializable {


    int lineFirstNode = 0;
    int lineLastNode = 0 ;
    int DFSFirstNode = 0 ;
    int BFSFirstNode = 0;
    File openFile ;
    File saveFile ;

    Button yesButton = new Button("Yes");
    Button noButton = new Button("No");
    Stage eventStage = new Stage();
    Label eventLabel = new Label();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inputLabel.setFont(Font.font(14));
        lineFirstNodeTF.setFont(Font.font(14));
        lineFirstNodeTF.setPrefWidth(50);

        lineLastNodeTF.setFont(Font.font(14));
        lineLastNodeTF.setPrefWidth(50);


        firstLabel.setFont(Font.font(14));
        lastLabel.setFont(Font.font(14));
        setNodeButton.setFont(Font.font(14));
        resetNodeButton.setFont(Font.font(14));
        inputNodeHBox.getChildren().addAll(firstLabel, lineFirstNodeTF, lastLabel, lineLastNodeTF, setNodeButton, resetNodeButton);

        firstLabel.setVisible(false);
        lastLabel.setVisible(false);
        lineFirstNodeTF.setVisible(false);
        lineLastNodeTF.setVisible(false);
        setNodeButton.setVisible(false);
        resetNodeButton.setVisible(false);

        openFile =  new File("D:\\openFile.txt");
    }


    @FXML
    BorderPane leftRoot = new BorderPane();
    @FXML
    BorderPane mainPane = new BorderPane();
    @FXML
    BorderPane topLeftRootPane = new BorderPane();

    @FXML
    MenuBar menuBar = new MenuBar();
    @FXML
    Menu file = new Menu();
    @FXML
    Menu edit = new Menu();
    @FXML
    Menu graphAnimation = new Menu();
    @FXML
    Menu help = new Menu();

    @FXML
    MenuItem newGraphMenuItem = new MenuItem();
    @FXML
    MenuItem openGraphMenuItem = new MenuItem();
    @FXML
    MenuItem saveAsGraphMenuItem = new MenuItem();
    @FXML
    MenuItem saveGraphMenuItem = new MenuItem();
    @FXML
    MenuItem exitProgramMenuItem = new MenuItem();
    @FXML
    MenuItem exitGraphMenuItem = new MenuItem();
    @FXML
    MenuItem deleteGraphMenuItem = new MenuItem();
    @FXML
    MenuItem resetGraphMenuItem = new MenuItem();
    @FXML
    MenuItem aboutGraphMenuItem = new MenuItem();

    @FXML
    Label inputLabel = new Label();
    @FXML
    HBox inputNodeHBox = new HBox();


    @FXML
    Button drawGraphButton = new Button();
    @FXML
    Button repairGraphButton = new Button();
    @FXML
    Button showDFSButton = new Button();
    @FXML
    Button showBFSButton = new Button();
    @FXML
    Button showLineButton = new Button();
    @FXML
    Button screenShotButton = new Button();

    @FXML
    TextArea graphNodeTextArea = new TextArea();
    @FXML
    TextArea showLineTextArea = new TextArea();

    TextField lineFirstNodeTF = new TextField();
    TextField lineLastNodeTF  = new TextField();
    Label firstLabel = new Label();
    Label lastLabel = new Label();
    Button setNodeButton = new Button("Set");
    Button resetNodeButton = new Button("Reset");


    /* Menu Item event */
    @FXML
    private void addPane(ActionEvent e) throws IOException {

        eventDialog("New");
        eventLabel.setText("Are you want to take a new window?");

        noButton.setOnAction(event -> {
            firstLabel.setVisible(false);
            lastLabel.setVisible(false);
            lineFirstNodeTF.setVisible(false);
            lineLastNodeTF.setVisible(false);
            setNodeButton.setVisible(false);
            resetNodeButton.setVisible(false);

            graphNodeTextArea.setEditable(true);
            graphNodeTextArea.setText("");
            eventStage.close();
        });

        yesButton.setOnAction(event -> {
            Parent root = null;
            try {
                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("GUI.fxml")));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            Scene scene = new Scene(root);

            Stage primaryStage = new Stage();
            primaryStage.setScene(scene);
            primaryStage.setTitle("Object Oriented Programming Project - Team 1B");
            primaryStage.show();
            eventStage.close();
        });

    }
    @FXML
    private void openPane(ActionEvent e) throws IOException {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        FileChooser fc = new FileChooser();
        fc.setTitle("Choose a input graph file");
        FileChooser.ExtensionFilter inputFilter = new FileChooser.ExtensionFilter("input(*.txt)","*.txt");
        fc.getExtensionFilters().add(inputFilter);
        openFile = fc.showOpenDialog(stage);
        if ( openFile != null) {
            readOpenFile(openFile);
        }
        graphNodeTextArea.setEditable(false);
    }

    @FXML
    private void deletePane(ActionEvent e) {

        eventDialog("Delete");

        yesButton.setOnAction(event ->{
            if ( openFile != null) {
                if (!openFile.equals(new File("D:\\openFile.txt"))) {
                    openFile.delete();
                }
            }
            eventStage.close();
            showLineTextArea.setText("");
            DFSFirstNode = 0;
            BFSFirstNode = 0;
            lineFirstNode = 0;
            lineLastNode = 0;

            inputLabel.setText("        Graph has been deleted!");
            firstLabel.setVisible(false);
            lastLabel.setVisible(false);
            lineFirstNodeTF.setVisible(false);
            lineLastNodeTF.setVisible(false);
            setNodeButton.setVisible(false);
            resetNodeButton.setVisible(false);
        });
        noButton.setOnAction(event -> {
            eventStage.close();
        });
    }
    @FXML
    private void saveAs(ActionEvent e) {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        FileChooser fc = new FileChooser();
        fc.setTitle("Save Dialog");
        fc.setInitialFileName("newInput");
        FileChooser.ExtensionFilter inputFilter = new FileChooser.ExtensionFilter("input(*.txt)","*.txt");
        fc.getExtensionFilters().add(inputFilter);
        try {
            saveFile  = fc.showSaveDialog(stage);
            fc.setInitialDirectory(saveFile.getParentFile());
            String s = graphNodeTextArea.getText();
            FileWriter out = new FileWriter(saveFile);
            out.write(s);
            out.close();
        }
        catch (Exception ex) {};
    }
    @FXML
    private void save(ActionEvent e) throws IOException {
        String s = graphNodeTextArea.getText();
        FileWriter out = new FileWriter(openFile);
        out.write(s);
        out.close();
    }
    @FXML
    private void exitProgram( ActionEvent e) {

        eventDialog("Exit Program");
        eventLabel.setText("Are you want to exit this program ?");
        yesButton.setOnAction(event ->{
            eventStage.close();
            System.exit(0);
        });
        noButton.setOnAction(event -> {
            eventStage.close();
        });
    }
    @FXML
    private void resetPane(ActionEvent e) throws IOException {

        eventDialog("Reset");
        yesButton.setOnAction(event -> {
            showLineTextArea.setText("");
            DFSFirstNode = 0;
            BFSFirstNode = 0;
            lineFirstNode = 0;
            lineLastNode = 0;

            inputLabel.setText("        Graph has been reset!");
            firstLabel.setVisible(false);
            lastLabel.setVisible(false);
            lineFirstNodeTF.setVisible(false);
            lineLastNodeTF.setVisible(false);
            setNodeButton.setVisible(false);
            resetNodeButton.setVisible(false);

            eventStage.close();
        });
        noButton.setOnAction(event -> {
            eventStage.close();
        });


    }

    @FXML
    private void exitPane(ActionEvent e) {

        eventDialog("Exit");
        yesButton.setOnAction(event -> {
            graphNodeTextArea.setText("");
            showLineTextArea.setText("");
            DFSFirstNode = 0;
            BFSFirstNode = 0;
            lineFirstNode = 0;
            lineLastNode = 0;

            inputLabel.setText("        Graph has been exited!");
            firstLabel.setVisible(true);
            firstLabel.setText(" Open new Graph : ");
            lastLabel.setVisible(false);
            lastLabel.setText("");
            lineFirstNodeTF.setVisible(false);
            lineLastNodeTF.setVisible(false);
            setNodeButton.setVisible(true);
            setNodeButton.setText("Open");
            setNodeButton.setOnAction(ev -> {
                try {
                    openPane(ev);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                setNodeButton.setText("Set");
                inputLabel.setVisible(false);
                firstLabel.setVisible(false);
                setNodeButton.setVisible(false);
            });
            resetNodeButton.setVisible(false);
            eventStage.close();
        });

        noButton.setOnAction(event -> {
            eventStage.close();
        });

    }

    @FXML
    private void aboutPane(ActionEvent e) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("aboutPane.fxml"));
        Scene scene = new Scene(root);
        Stage aboutStage = new Stage();
        aboutStage.setScene(scene);
        aboutStage.setTitle("About our Program ");
        aboutStage.show();
    }

    @FXML
    private void dfsPane(ActionEvent e) {
        inputLabel.setText("   Input the first node in Depth First Search algorithm here :");
        inputLabel.setVisible(true);
        firstLabel.setText("The first node");
        firstLabel.setVisible(true);
        lineFirstNodeTF.setText(String.valueOf(DFSFirstNode));
        lineFirstNodeTF.setEditable(true);
        lineFirstNodeTF.setVisible(true);
        setNodeButton.setVisible(true);
        resetNodeButton.setVisible(true);
        lastLabel.setText("");
        lastLabel.setVisible(false);
        lineLastNodeTF.setVisible(false);
        setNodeButton.setOnAction(event -> {
            DFSFirstNode = Integer.parseInt(lineFirstNodeTF.getText());
            lineFirstNodeTF.setEditable(false);
        });
        resetNodeButton.setOnAction(event -> {
            lineFirstNodeTF.setEditable(true);
        });
    }

    @FXML
    private void bfsPane(ActionEvent e) throws IOException {
        inputLabel.setText("   Input the first node in Breadth First Search algorithm here :");
        inputLabel.setVisible(true);
        firstLabel.setText("The first node");
        firstLabel.setVisible(true);
        lineFirstNodeTF.setText(String.valueOf(BFSFirstNode));
        lineFirstNodeTF.setEditable(true);
        lineFirstNodeTF.setVisible(true);
        setNodeButton.setVisible(true);
        resetNodeButton.setVisible(true);
        lastLabel.setText("");
        lastLabel.setVisible(false);
        lineLastNodeTF.setVisible(false);
        setNodeButton.setOnAction(event -> {
            BFSFirstNode = Integer.parseInt(lineFirstNodeTF.getText());
            lineFirstNodeTF.setEditable(false);
        });
        resetNodeButton.setOnAction(event -> {
            lineFirstNodeTF.setEditable(true);
        });
    }

    @FXML
    private void showLinePane(ActionEvent e){
        inputLabel.setText("   Input the first node and last node in the path here :");
        inputLabel.setVisible(true);
        firstLabel.setText("First");
        firstLabel.setVisible(true);
        lineFirstNodeTF.setText(String.valueOf(lineFirstNode));
        lineFirstNodeTF.setEditable(true);
        lineFirstNodeTF.setVisible(true);
        lastLabel.setText("  Last");
        lastLabel.setVisible(true);
        lineLastNodeTF.setText(String.valueOf(lineLastNode));
        lineLastNodeTF.setEditable(true);
        lineLastNodeTF.setVisible(true);

        setNodeButton.setVisible(true);
        resetNodeButton.setVisible(true);
        setNodeButton.setOnAction(event -> {
            lineFirstNode = Integer.parseInt(lineFirstNodeTF.getText());
            lineLastNode = Integer.parseInt(lineLastNodeTF.getText());
            lineFirstNodeTF.setEditable(false);
            lineLastNodeTF.setEditable(false);
        });
        resetNodeButton.setOnAction(event -> {
            lineFirstNodeTF.setEditable(true);
            lineLastNodeTF.setEditable(true);
        });
    }


    @FXML
    public void drawGraph(ActionEvent event) {
        showLineTextArea.setText("Draw Graph\n" + graphNodeTextArea.getText());
        graphNodeTextArea.setEditable(false);

    }
    @FXML
    public void repairGraph(ActionEvent event) {
        graphNodeTextArea.setEditable(true);
    }
    @FXML
    public void screenShot(ActionEvent event ) {

    }



    public Controller() {

    }

    public void readOpenFile( File openFile) throws FileNotFoundException {
        String s = "";
        if (openFile != null) {
            Scanner sc = new Scanner(openFile.getAbsoluteFile());
            while (sc.hasNextLine()) {
                s += sc.nextLine() + "\n";
            }
        }
        graphNodeTextArea.setText(s.trim());
    }

    public void eventDialog ( String s) {
        eventLabel.setText("Are you want to " + s.toLowerCase() + " this opening graph ?");
        eventLabel.setFont(Font.font(14));
        yesButton.setFont(Font.font(14));
        noButton.setFont(Font.font(14));
        HBox hBox = new HBox(yesButton, noButton);
        VBox vBox = new VBox(eventLabel, hBox);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(30);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(40);

        Scene scene = new Scene(vBox,280,130);
        eventStage.setScene(scene);
        eventStage.setTitle( s + " Graph ");
        eventStage.show();
    }
}

