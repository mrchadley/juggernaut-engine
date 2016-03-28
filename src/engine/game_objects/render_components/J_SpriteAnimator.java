package engine.game_objects.render_components;

import engine.game_objects.J_Transform;
import javafx.scene.canvas.GraphicsContext;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.LinkedList;

public class J_SpriteAnimator extends J_RendererComponent
{
    private LinkedList<J_SpriteAnimation> animations = new LinkedList<>();
    private J_SpriteAnimation currentAnim;

    private float xPosition = 0;
    private float frameTimeCounter = 0.0f;

    public J_SpriteAnimator()
    {

    }

    public J_SpriteAnimator(J_Transform transform)
    {
       super(transform);
    }

    @Override
    public void Update(float dt)
    {
        if(currentAnim.IsPlaying())
        {
            if(frameTimeCounter > 0)
            {
                frameTimeCounter -= dt;
            }
            else
            {
                currentAnim.NextFrame();
                frameTimeCounter = currentAnim.GetFrameLength();
            }
        }
        xPosition = currentAnim.GetOffset();
    }

    @Override
    public void Draw(GraphicsContext gc)
    {
        gc.drawImage(currentAnim.GetSpriteSheet(), xPosition, 0, currentAnim.GetFrameSize().getX(), currentAnim.GetFrameSize().getY(),
                transform.GetCorner().getX(), transform.GetCorner().getY(), transform.GetSize().getX(), transform.GetSize().getY());
    }

    public J_SpriteAnimation GetCurrentAnimation()
    {
        return currentAnim;
    }

    public float GetXPosition()
    {
        return xPosition;
    }

    public void AddAnimation( J_SpriteAnimation animation)
    {
        animations.add(animation);
    }

    public void PlayAnimation(int index)
    {
        if(index < animations.size())
        {
            currentAnim = animations.get(index);

            currentAnim.Play();
        }
    }

    @Override
    public void DisplayProperties()
    {

    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException
    {
        out.writeObject(animations);
        out.writeObject(currentAnim);
        out.writeFloat(xPosition);
        out.writeFloat(frameTimeCounter);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException
    {
        this.animations = (LinkedList<J_SpriteAnimation>)in.readObject();
        this.currentAnim = (J_SpriteAnimation)in.readObject();
        this.xPosition = in.readFloat();
        this.frameTimeCounter = in.readFloat();
    }
}
