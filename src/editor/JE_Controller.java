package editor;

import engine.framework.Vector2;
import engine.framework.basic_shapes.Oval;
import engine.framework.basic_shapes.Rectangle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;


public class JE_Controller
{
    @FXML private Canvas levelEditorCanvas;
    @FXML private Canvas layer;
    @FXML private BorderPane layout;
    @FXML private TextField x;
    @FXML private TextField y;
    @FXML private TextField w;
    @FXML private TextField h;
    @FXML private Button add;
    @FXML private BorderPane border;
    @FXML private Tab ProjectTab;
    @FXML private Tab OutlinerTab;

    private boolean running = false;
    private JE_Renderer renderer = JE_Renderer.GetInstance();
    private J_Level level = new J_Level();

    Oval test = new Oval(new Vector2(50, 50), new Vector2(50, 50), Color.RED);



    public void initialize()
    {
        levelEditorCanvas.widthProperty().bind(border.widthProperty());
        levelEditorCanvas.heightProperty().bind(border.heightProperty());

        renderer.SetCanvas(levelEditorCanvas);
        renderer.SetLevel(level);
        level.AddObject(test);
    }

    public void addObject(ActionEvent actionEvent)
    {
        float _x = Float.parseFloat(x.getText());
        float _y = Float.parseFloat(y.getText());
        float _w = Float.parseFloat(w.getText());
        float _h = Float.parseFloat(h.getText());
        Rectangle newObj = new Rectangle(new Vector2(_x, _y), new Vector2(_w, _h), Color.DARKCYAN);

        x.clear();
        x.setText("0");
        y.clear();
        y.setText("0");
        w.clear();
        w.setText("0");
        h.clear();
        h.setText("0");

        level.AddObject(newObj);
    }
}
