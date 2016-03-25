package engine.game_objects;

import javafx.scene.image.Image;

public class J_SpriteAnimation
{
    private Image spriteSheet;
    private Vector2 frameSize; //size of each frame
    private int frameCount; //total number of frames
    private float frameOffset = 0; //offset between the end of one frame and the start of the next

    private int frame = 0; //current frame
    private float frameLength; //<-counter

    private boolean playing = false;
    private boolean loopable = false;

    public J_SpriteAnimation(Image spriteSheet, Vector2 frameSize, int frameCount, float frameLength)
    {
        this.spriteSheet = spriteSheet;
        this.frameSize = frameSize;
        this.frameCount = frameCount;
        this.frameLength = frameLength;
    }

    public void NextFrame()
    {
        if(++frame >= frameCount)
        {
            if(loopable)
            {
                frame = 0;
            }
            else
            {
                Stop();
            }
        }
    }

    //setters
    public void SetOffset(float amount)
    {
        frameOffset = amount;
    }

    public void SetFrameCount(int amount)
    {
        frameCount = amount;
    }

    public void SetFrameTime(float length)
    {
        frameLength = length;
    }

    public void Play()
    {
        playing = true;
    }

    public void Stop()
    {
        playing = false;
        frame = 0;
    }

    public boolean IsLoopable()
    {
        return loopable;
    }

    public void SetLoopable(boolean value)
    {
        loopable = value;
    }

    //getters
    public Image GetSpriteSheet()
    {
        return spriteSheet;
    }

    public Vector2 GetFrameSize()
    {
        return frameSize;
    }

    public boolean IsPlaying()
    {
        return playing;
    }

    public float GetFrameLength()
    {
        return frameLength;
    }

    public int GetCurrentFrame()
    {
        return frame;
    }

    public float GetOffset()
    {
        return (frameSize.getX() + frameOffset) * frame;
    }
}
