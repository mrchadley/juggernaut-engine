package engine.game_objects.render_components;

import engine.framework.Vector2;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.io.*;

public class J_SpriteAnimation implements Externalizable
{
    private Image spriteSheet;
    private Vector2 frameSize; //size of each frame
    private int frameCount; //total number of frames
    private float frameOffset = 0; //offset between the end of one frame and the start of the next

    private int frame = 0; //current frame
    private float frameLength; //<-counter

    private boolean playing = false;
    private boolean loopable = false;

    public J_SpriteAnimation()
    {
        frameSize = new Vector2();
        frameCount = 1;
        frame = 0;
        frameLength = 0.0f;
    }

    public J_SpriteAnimation(Image spriteSheet, Vector2 frameSize, int frameCount, float frameLength)
    {
        this.spriteSheet = spriteSheet;
        this.frameSize = frameSize;
        this.frameCount = frameCount;
        this.frameLength = frameLength;
    }
    public J_SpriteAnimation(Image spriteSheet)
    {
        this.spriteSheet = spriteSheet;
        this.frameSize = new Vector2((float)spriteSheet.getWidth(), (float)spriteSheet.getHeight());
        this.frameCount = 1;
        this.frameLength = 0;
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

    @Override
    public void writeExternal(ObjectOutput out) throws IOException
    {
        out.writeInt(frameCount);
        out.writeFloat(frameOffset);
        out.writeInt(frame);
        out.writeFloat(frameLength);
        out.writeBoolean(playing);
        out.writeBoolean(loopable);
        out.writeObject(frameSize);
        ImageIO.write(SwingFXUtils.fromFXImage(spriteSheet, null), "png", ImageIO.createImageOutputStream(out));
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException
    {

        this.frameCount = in.readInt();
        this.frameOffset = in.readFloat();
        this.frame = in.readInt();
        this.frameLength = in.readFloat();
        this.playing = in.readBoolean();
        this.loopable = in.readBoolean();
        this.frameSize = (Vector2)in.readObject();
        this.spriteSheet = SwingFXUtils.toFXImage(ImageIO.read(ImageIO.createImageInputStream(in)), null);
    }

}

