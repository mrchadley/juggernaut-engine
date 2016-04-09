package editor;

import editor.chatroom.ClientThread;
import editor.chatroom.ServerThread;
import engine.J_Game;
import engine.J_Level;
import engine.TestGame;
import engine.framework.Vector2;
import engine.game_objects.J_GameObject;
import engine.game_objects.J_Transform;
import engine.game_objects.render_components.*;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Pair;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;


public class JE_Controller
{
    @FXML private Stage stage;
    @FXML private BorderPane layout;
    @FXML private Canvas levelEditorCanvas;
    @FXML private BorderPane border;
    @FXML private Tab ProjectTab;
    @FXML private Tab OutlinerTab;
    @FXML private TreeView<File> locationTreeView;


    @FXML private VBox sidePanel;
    @FXML private TabPane propertiesPanel;

    @FXML private Tab propertiesTab;
    @FXML private TreeView<J_GameObject> levelOutliner;

    private final String defaultTitle = "Juggernaut Engine [Editor]";

    private JE_Renderer renderer = JE_Renderer.GetInstance();
    private J_Level currentLevel = new J_Level();

    private ButtonType yes = new ButtonType("Yes");
    private ButtonType no = new ButtonType("No");

    private File projectDirectory = new File(".");
    private File currentLevelFile = null;

    public void initialize()
    {
        stage.setTitle(defaultTitle);

        setCanvasScale();

        levelOutliner.setShowRoot(false);
        TreeItem<J_GameObject> root = new TreeItem<>();
        levelOutliner.setRoot(root);
        levelOutliner.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null) {
                DisplayProperties(newValue.getValue());
            }
        });

        UpdateOutliner();

        File currentDir = new File("src/"); // current directory
        loadTreeItems(currentDir);

        renderer.controller = this;
        renderer.SetCanvas(levelEditorCanvas);
        renderer.SetLevel(currentLevel);
        renderer.Run();

        sidePanel.setPrefWidth(320);
        propertiesPanel.prefHeightProperty().bind(stage.heightProperty().multiply(.5));
        stage.setMaximized(true);
     }
    public void setCanvasScale()
    {
        levelEditorCanvas.setWidth(1280);
        levelEditorCanvas.setHeight(720);

        levelEditorCanvas.scaleXProperty().bind(border.widthProperty().subtract(20 * 2).divide(levelEditorCanvas.widthProperty()));
        levelEditorCanvas.scaleYProperty().bind(levelEditorCanvas.scaleXProperty());

        levelEditorCanvas.translateXProperty().bind(levelEditorCanvas.widthProperty().multiply(levelEditorCanvas.scaleXProperty().subtract(1).divide(2)).add(20));
        levelEditorCanvas.translateYProperty().bind(levelEditorCanvas.heightProperty().multiply(levelEditorCanvas.scaleYProperty().subtract(1).divide(2)).add(20));
    }
    public void loadTreeItems(File dir) {
        TreeItem<File> root = new TreeItem<>(new File("Files:"));
        root.setExpanded(true);
        //try {
            File[] files = dir.listFiles();
            for (File file : files) {
                if (file.isDirectory()) {
                   // System.out.println("directory:" + file.getCanonicalPath());
                    loadTreeItems(file);
                } else {
                   // System.out.println("     file:" + file.getCanonicalPath());
                    root.getChildren().add(new TreeItem<>(file));
                }
                root.getChildren().add(new TreeItem<>(file));
            }

            locationTreeView.setRoot(root);
        /*} catch (IOException e) {
            e.printStackTrace();
        }*/
    }
    public void test(ActionEvent actionEvent)
    {
        //renderer.SetRunning(!renderer.isRunning());
        TestGame.LaunchGame(currentLevel);
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
            UpdateOutliner();
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
        UpdateOutliner();
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
        Alert newProjectAlert = new Alert(Alert.AlertType.CONFIRMATION);
        newProjectAlert.setTitle("Create New Project?");
        newProjectAlert.setHeaderText("Any unsaved changes will be overwritten.");
        newProjectAlert.setGraphic(null);
        newProjectAlert.setContentText("Do you wish to continue?");

        newProjectAlert.getButtonTypes().setAll(yes, no);

        Optional<ButtonType> result = newProjectAlert.showAndWait();
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
    public void LoadJavaDocs(ActionEvent actionEvent)
    {
        Stage stage = new Stage();
        Group root = new Group();
        WebView webView = new WebView();
        webView.getEngine().load("https://docs.oracle.com/javase/7/docs/api/");

        root.getChildren().add(webView);
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(getClass().getResource("juggernaut-style.css").toString());
        stage.setScene(scene);
        stage.setTitle("JavaDocs");
        stage.show();
    }
    public void LoadChatRoom(ActionEvent actionEvent)
    {
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.initStyle(StageStyle.UNIFIED);
        dialog.setTitle("ChatRoom Login");
        dialog.setHeaderText("");

        ButtonType login = new ButtonType("Login", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(login, ButtonType.CANCEL);

        GridPane dialogPane = new GridPane();
        dialogPane.setHgap(10);
        dialogPane.setVgap(10);
        dialogPane.setPadding(new Insets(25,25,25,25));

        TextField username = new TextField();
        username.setPromptText("Username");
        TextField ipAddress = new TextField();
        ipAddress.setPromptText("Leave blank if hosting");

        dialogPane.add(new Label("Username:"), 0, 0);
        dialogPane.add(username, 1, 0);

        dialogPane.add(new Label("Server IP:"), 0, 1);
        dialogPane.add(ipAddress, 1, 1);

        Node loginButton = dialog.getDialogPane().lookupButton(login);
        loginButton.setDisable(true);

        username.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(dialogPane);


        dialog.setResultConverter(dialogButton -> {
            if(dialogButton == login)
                return new Pair<String, String>(username.getText(), ipAddress.getText());
            return null;
        });


        Optional<Pair<String, String>> ip = dialog.showAndWait();
        ip.ifPresent(result -> {
            if(result.getValue().intern() == "")
            {
                System.out.println("no ip");
                ServerThread serverThread = new ServerThread(result.getKey());
            }
            else
            {
                System.out.println(result.getValue());
                ClientThread clientThread = new ClientThread(result.getValue(), result.getKey());
            }
        });
    }
    public void UpdateOutliner()
    {
        levelOutliner.getRoot().getChildren().clear();
        for(J_GameObject obj : currentLevel.GetObjects())
        {
            TreeItem<J_GameObject> item = new TreeItem<>(obj);
            levelOutliner.getRoot().getChildren().add(item);
        }
    }
    public void DisplayProperties(J_GameObject obj)
    {
        Pane test = obj.GetProperties(this);
        ScrollPane scrollPane = new ScrollPane(test);
        scrollPane.setPadding(new Insets(0, 10, 0, 10));
        scrollPane.prefViewportHeightProperty().bind(propertiesTab.getTabPane().tabMaxHeightProperty());
        propertiesTab.setContent(scrollPane);
    }
    public void AddEmpty(ActionEvent actionEvent)
    {
        currentLevel.AddObject(new J_GameObject("Empty", new J_Transform(new Vector2(), new Vector2(50, 50))));
        UpdateOutliner();
    }
    public void AddOval(ActionEvent actionEvent)
    {
        J_GameObject oval = new J_GameObject("Oval", new J_Transform(new Vector2(25, 25), new Vector2(50, 50)));
        oval.SetRenderer(new J_OvalRenderer(oval.GetTransform(), Color.web("#ffa300"), Color.web("323232")));
        currentLevel.AddObject(oval);
        UpdateOutliner();
    }
    public void AddRect(ActionEvent actionEvent)
    {
        J_GameObject rect = new J_GameObject("Rect", new J_Transform(new Vector2(25, 25), new Vector2(50, 50)));
        rect.SetRenderer(new J_RectangleRenderer(rect.GetTransform(), Color.web("#ffa300"), Color.web("323232")));
        currentLevel.AddObject(rect);
        UpdateOutliner();
    }
    public void AddSprite(ActionEvent actionEvent)
    {
        J_GameObject sprite = new J_GameObject("Sprite", new J_Transform(new Vector2(25, 25), new Vector2(50, 50)));
        sprite.SetRenderer(new J_SpriteRenderer(sprite.GetTransform()));
        _AddSpriteRenderer(sprite);
        currentLevel.AddObject(sprite);
        UpdateOutliner();
    }
    public void AddAnimatedSprite(ActionEvent actionEvent)
    {
        J_GameObject sprite = new J_GameObject("Sprite", new J_Transform(new Vector2(25, 25), new Vector2(50, 50)));
        sprite.SetRenderer(new J_SpriteAnimator(sprite.GetTransform()));
        currentLevel.AddObject(sprite);
        UpdateOutliner();
    }


    public void AddOvalRenderer(ActionEvent actionEvent){}
    public void AddRectRenderer(ActionEvent actionEvent){}
    public void AddSpriteRenderer(ActionEvent actionEvent)
    {
        if(levelOutliner.getSelectionModel().getSelectedItem() != null)
            _AddSpriteRenderer(levelOutliner.getSelectionModel().getSelectedItem().getValue());
    }
    public void _AddSpriteRenderer(J_GameObject obj)
    {
        J_SpriteRenderer renderer = new J_SpriteRenderer(obj.GetTransform());
        _AddSpriteAnimation(renderer);
        obj.SetRenderer(renderer);
    }
    public void AddSpriteAnimator(ActionEvent actionEvent){}

    public void AddInputBinder(ActionEvent actionEvent){}

    public void AddSpriteAnimation(ActionEvent actionEvent){}
    public void _AddSpriteAnimation(J_SpriteRenderer spriteRenderer)
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("."));
        try {
            Image img = new Image(new FileInputStream(fileChooser.showOpenDialog(null)));
            spriteRenderer.SetSprite(new J_SpriteAnimation(img));
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    public void _AddSpriteAnimation(J_SpriteAnimator spriteAnimator){}
}
