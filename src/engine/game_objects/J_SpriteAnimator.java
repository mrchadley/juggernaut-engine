package engine.game_objects;

import java.util.LinkedList;

public class J_SpriteAnimator extends J_Component
{
    private LinkedList<J_SpriteAnimation> animations = new LinkedList<>();
    private J_SpriteAnimation currentAnim;

    private float xPosition = 0;
    private float frameTimeCounter = 0.0f;


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
}
