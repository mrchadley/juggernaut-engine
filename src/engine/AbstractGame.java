package engine;

public abstract class AbstractGame
{
    public abstract void update(JuggernautHub jh, float dt);
    public abstract void render(JuggernautHub jg, JuggernautRenderer jr);
}
