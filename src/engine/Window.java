package engine;

public class Window
{
    public Window(int width, int height, String title, Game game)
    {
        game.widthProperty().setValue(width);
        game.heightProperty().setValue(height);
    }
}
