package editor;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

/**
 * Created by austin on 31/03/16.
 */
public class JE_startup {

    @FXML private Label header;
    @FXML private GridPane layout;
    @FXML private Button newProject;
    @FXML private Button openProject;
    @FXML private Button exitProject;
    @FXML private Stage initial;

    private ButtonType yes = new ButtonType("Yes");
    private ButtonType no = new ButtonType("No");
    private final String defaultTitle = "Juggernaut Engine [Editor]";

    public void initialize() {

        Font.loadFont(JE_startup.class.getResource("redseven.ttf").toExternalForm(), 10);

        header.getStyleClass().add("header");
        header.setText("Juggernaut\nEngine");

        layout.getStyleClass().add("back");

        newProject.setText("New Project...");
        openProject.setText("Open Project...");
        exitProject.setText("Exit");
    }

    public void newProj(ActionEvent actionEvent) throws IOException {
        Dialog<Pair<String, File>> newProjectDialog = new Dialog<>();
        newProjectDialog.setTitle("New Project - Juggernaut Engine");
        newProjectDialog.setHeaderText("");

        ButtonType dirChoose = new ButtonType("Choose Directory");
        newProjectDialog.getDialogPane().getButtonTypes().addAll(dirChoose, ButtonType.CANCEL);
        DirectoryChooser dir = null;
        File workingDir = new File(".");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        TextField projName = new TextField();

        grid.add(new Label("Project Name:"), 0, 0);
        grid.add(projName, 0, 1);


        newProjectDialog.setResultConverter(dialogButton -> {
            if (dialogButton == dirChoose) {
                return new Pair<>(projName.getText(), workingDir);
            }
            return null;
        });

        Optional<Pair<String, File>> result = newProjectDialog.showAndWait();



        Stage main = FXMLLoader.load(getClass().getResource("juggernaut.fxml"));
        main.setTitle(result.get().getKey() + " - " + defaultTitle);
        main.show();
        initial.close();
        /*
        if(name.get() != null)
        {

        } */
    }

    public void openProj(ActionEvent actionEvent) {

    }


    public void exit(ActionEvent actionEvent) {
        initial.close();
    }

}
