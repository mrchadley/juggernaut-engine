package engine;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.paint.Color;

public class Renderer
{
    @FXML
    private Canvas canvas;

    private Scene scene;
    private Group root;
    private GraphicsContext gc;

    //test
    int testInt = 0;

    //private list of drawObjects

    public Renderer(int width, int height)
    {
        canvas = new Canvas();
        canvas.widthProperty().setValue(width);
        canvas.heightProperty().setValue(height);


        root = new Group();
        scene = new Scene(root, width, height);
        root.getChildren().add(canvas);

        gc = canvas.getGraphicsContext2D();
    }

    public void draw() //iterate through list of drawObjects and call their draw functions
    {
        gc.setFill(Color.web("#232329"));
        gc.fillRect(0,0, canvas.getWidth(), canvas.getHeight());

        testInt++;
        gc.setFill(Color.web("#ffaa00"));
        gc.fillRect(50 + testInt, 50, 100, 75);

        //rectangle
        gc.setFill(Color.BLUE);
        gc.fillRect(250 + testInt, 50, 100, 75);

        //line
        gc.setStroke(Color.RED);
        gc.strokeLine(50 + testInt, 250, 150, 450);

        gc.strokePolygon(new double[] {250, 310, 300, 250},
                new double[] {350, 360, 380, 400}, 4);
        gc.fillPolygon(new double[] {250, 310, 300, 250},
                new double[] {500, 510, 530, 550}, 4);
    }

    public void clear()
    {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    public Scene getScene() {
        return scene;
    }

    /*
    public void addDrawObject(DrawObject obj)...
     */
}
