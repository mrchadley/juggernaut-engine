package engine.game_objects.render_components;

import engine.framework.Vector2;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;

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


    public GridPane GetProperties()
    {
        GridPane spriteAnimationContent = new GridPane();
        spriteAnimationContent.setPadding(new Insets(10,15,10,15));
        spriteAnimationContent.setHgap(5);
        spriteAnimationContent.setVgap(5);

        ImageView current = new ImageView();
        if(spriteSheet != null)
            current = new ImageView(spriteSheet);
        current.setPreserveRatio(true);
        current.setFitHeight(200);

        Button imgButton = new Button("Change");
        imgButton.setOnAction(event ->{
            //open file chooser
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(new File("."));
            try {
                spriteSheet = new Image(new FileInputStream(fileChooser.showOpenDialog(null)));
            }catch(IOException e)
            {
                e.printStackTrace();
            }
        });

        ScrollPane scroll = new ScrollPane(current);
        scroll.setPrefWidth(250);
        GridPane.setColumnSpan(scroll, 2);

        TextField xField = new TextField(frameSize.getX() + "");
        xField.setPrefWidth(70);
        xField.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER || event.getCode() == KeyCode.TAB)
            {
                frameSize.setX(Float.parseFloat(xField.getText()));
            }
        });
        TextField yField = new TextField(frameSize.getY() + "");
        yField.setPrefWidth(70);
        yField.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER || event.getCode() == KeyCode.TAB)
            {
                frameSize.setY(Float.parseFloat(yField.getText()));
            }
        });


        TextField cntField = new TextField(frameCount + "");
        cntField.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER || event.getCode() == KeyCode.TAB)
            {
                frameCount = Integer.parseInt(cntField.getText());
            }
        });
        TextField lngField = new TextField(frameLength + "");
        lngField.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER || event.getCode() == KeyCode.TAB)
            {
                frameLength = Float.parseFloat(lngField.getText());
            }
        });
        TextField ofstField = new TextField(frameOffset + "");
        ofstField.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER || event.getCode() == KeyCode.TAB)
            {
                frameOffset = Float.parseFloat(ofstField.getText());
            }
        });


        GridPane size = new GridPane();
        size.setHgap(5);
        size.setVgap(5);

        CheckBox loopTog = new CheckBox();
        loopTog.setSelected(loopable);
        loopTog.setOnAction(event -> {
            loopable = loopTog.isSelected();
            System.out.println(loopable);
        });
        CheckBox playTog = new CheckBox();
        playTog.setSelected(playing);
        playTog.setOnAction(event -> {
            playing = playTog.isSelected();
            System.out.println(playing);
        });

        spriteAnimationContent.add(new Label("Frame Size:"), 0, 0);

        size.add(new Label("x:"), 0, 0);
        size.add(xField, 1, 0);
        size.add(new Label("y:"), 2, 0);
        size.add(yField, 3, 0);
        GridPane.setColumnSpan(size, 2);

        spriteAnimationContent.add(size, 0, 1);

        spriteAnimationContent.add(new Label("Frame Count:"), 0, 2);
        spriteAnimationContent.add(cntField, 1, 2);
        spriteAnimationContent.add(new Label("Frame Length:"), 0, 3);
        spriteAnimationContent.add(lngField, 1, 3);

        spriteAnimationContent.add(new Label("Frame Offset:"), 0, 4);
        spriteAnimationContent.add(ofstField, 1, 4);

        spriteAnimationContent.add(new Label("Loopable:"), 0, 5);
        spriteAnimationContent.add(loopTog, 1, 5);
        spriteAnimationContent.add(new Label("Playing:"), 0, 6);
        spriteAnimationContent.add(playTog, 1, 6);

        spriteAnimationContent.add(new Label("Sprite:"), 0, 7);
        spriteAnimationContent.add(imgButton, 1, 7);

        spriteAnimationContent.add(scroll, 0, 8);

        return spriteAnimationContent;
    }
}

