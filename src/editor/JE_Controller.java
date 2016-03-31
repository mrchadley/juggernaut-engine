package editor;

import engine.J_Level;
import engine.game_objects.J_GameObject;
import javafx.scene.control.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.control.ComboBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


public class JE_Controller
{
    @FXML private Stage stage;
    @FXML private BorderPane layout;
    @FXML private Canvas levelEditorCanvas;
    @FXML private TextField x;
    @FXML private TextField y;
    @FXML private TextField w;
    @FXML private TextField h;
    @FXML private BorderPane border;
    @FXML private Tab ProjectTab;
    @FXML private Tab OutlinerTab;
    @FXML private TreeView<File> locationTreeView;
    @FXML private ComboBox<String> colorCombo;

    @FXML private HBox propertiesPane;
    @FXML private TreeView<String> levelOutliner;

    private final String defaultTitle = "Juggernaut Engine [Editor]";

    Map<String, Color> colors = new HashMap<String, Color>();
    private JE_Renderer renderer = JE_Renderer.GetInstance();
    private J_Level currentLevel = new J_Level();

    private ButtonType yes = new ButtonType("Yes");
    private ButtonType no = new ButtonType("No");

    private File projectDirectory = new File(".");
    private File currentLevelFile = null;

    public void initialize()
    {
        stage.setTitle(defaultTitle);
        levelEditorCanvas.setWidth(800);
        levelEditorCanvas.setHeight(600);


        levelOutliner.setShowRoot(false);
        TreeItem<String> root = new TreeItem<>();
        for(J_GameObject obj : currentLevel.GetObjects())
        {
            TreeItem<String> item = new TreeItem<>(obj.GetName());
            root.getChildren().add(item);
        }
        levelOutliner.setRoot(root);

        File currentDir = new File("src/"); // current directory
        loadTreeItems(currentDir);

        renderer.SetCanvas(levelEditorCanvas);
        renderer.SetLevel(currentLevel);
     }

    public void loadTreeItems(File dir) {
        TreeItem<File> root = new TreeItem<>(new File("Files:"));
        root.setExpanded(true);
        try {
            File[] files = dir.listFiles();
            for (File file : files) {
                if (file.isDirectory()) {
                    System.out.println("directory:" + file.getCanonicalPath());
                    loadTreeItems(file);
                } else {
                    System.out.println("     file:" + file.getCanonicalPath());
                    root.getChildren().add(new TreeItem<>(file));
                }
                root.getChildren().add(new TreeItem<>(file));
            }

            locationTreeView.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void test(ActionEvent actionEvent)
    {
        renderer.SetRunning(!renderer.isRunning());
    }

    public void addObject(ActionEvent actionEvent)
    {
        float _x = Float.parseFloat(x.getText());
        float _y = Float.parseFloat(y.getText());
        float _w = Float.parseFloat(w.getText());
        float _h = Float.parseFloat(h.getText());
        String _c = colorCombo.getValue().toLowerCase();
        // NEED MORE COLORS
        colors.put("red", Color.RED);
        colors.put("blue", Color.BLUE);
        colors.put("green", Color.GREEN);

        //Rectangle newObj = new Rectangle(new Vector2(_x, _y), new Vector2(_w, _h), colors.get(_c.toLowerCase()));
        x.clear();
        x.setText("0");
        y.clear();
        y.setText("0");
        w.clear();
        w.setText("0");
        h.clear();
        h.setText("0");

        //currentLevel.AddObject(newObj);
    }

    public void NewLevel(ActionEvent actionEvent)
    {
        Alert newLevelAlert = new Alert(Alert.AlertType.CONFIRMATION);
        newLevelAlert.setTitle("Create New Level?");
        newLevelAlert.setHeaderText("Any unsaved changes will be overwritten.");
        newLevelAlert.setGraphic(null);
        newLevelAlert.setContentText("Do you wish to continue?");

        newLevelAlert.getButtonTypes().setAll(yes, no);

        Optional<ButtonType> result = newLevelAlert.showAndWait();
        if(result.get() == yes)
        {
            currentLevel = new J_Level();
            renderer.SetLevel(currentLevel);
            levelOutliner.getRoot().getChildren().clear();
            currentLevelFile = null;
        }
    }
    public void OpenLevel(ActionEvent actionEvent)
    {
        Alert openLevelAlert = new Alert(Alert.AlertType.CONFIRMATION);
        openLevelAlert.setTitle("Open Level?");
        openLevelAlert.setHeaderText("Any unsaved changes will be overwritten.");
        openLevelAlert.setGraphic(null);
        openLevelAlert.setContentText("Do you wish to continue?");

        openLevelAlert.getButtonTypes().setAll(yes, no);

        Optional<ButtonType> result = openLevelAlert.showAndWait();
        if(result.get() == yes)
        {
            FileChooser openLevel = new FileChooser();
            openLevel.setTitle("Open Level");
            openLevel.getExtensionFilters().setAll(new FileChooser.ExtensionFilter("Juggernaut Level Files", "*.jLevel"));
            openLevel.setInitialDirectory(projectDirectory);
            File openedLevel = openLevel.showOpenDialog(null);
            if(openedLevel != null)
            {
                try {
                    currentLevelFile = openedLevel;
                    currentLevel.LoadLevel(openedLevel);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
    public void SaveLevel(ActionEvent actionEvent)
    {
        if(currentLevelFile != null)
        {
            try {
                currentLevel.SaveLevel(currentLevelFile);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        else SaveLevelAs(actionEvent);
    }
    public void SaveLevelAs(ActionEvent actionEvent)
    {
        FileChooser saveLevel = new FileChooser();
        saveLevel.setTitle("Save Level");
        saveLevel.getExtensionFilters().setAll(new FileChooser.ExtensionFilter("Juggernaut Level Files", "*.jLevel"));
        saveLevel.setInitialDirectory(projectDirectory);
        File savedLevel = saveLevel.showSaveDialog(null);
        if(savedLevel != null)
        {
            try {
                currentLevelFile = savedLevel;
                currentLevel.SaveLevel(savedLevel);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }


    public void NewProject(ActionEvent actionEvent)
    {
        Alert newLevelAlert = new Alert(Alert.AlertType.CONFIRMATION);
        newLevelAlert.setTitle("Create New Project?");
        newLevelAlert.setHeaderText("Any unsaved changes will be overwritten.");
        newLevelAlert.setGraphic(null);
        newLevelAlert.setContentText("Do you wish to continue?");

        newLevelAlert.getButtonTypes().setAll(yes, no);

        Optional<ButtonType> result = newLevelAlert.showAndWait();
        if(result.get() == yes)
        {
            TextInputDialog newProjectDialog = new TextInputDialog();
            newProjectDialog.setTitle("New Project - Juggernaut Engine");
            newProjectDialog.setHeaderText("");
            newProjectDialog.setContentText("Project Name:");

            Optional<String> name = newProjectDialog.showAndWait();
            if(name.get() != null)
            {
                stage.setTitle(name.get() + " - " + defaultTitle);
            }
        }
    }
    public void OpenProject(ActionEvent actionEvent)
    {

    }
}
