package engine;

import javafx.scene.canvas.Canvas;

public class Game extends Canvas implements Runnable
{

    @Override
    public void run() {

    }

    public static void main(String[] args)
    {
        new Window(800, 600, "Juggernaut Engine", new Game());
    }
}
