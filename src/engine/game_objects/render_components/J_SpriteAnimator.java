package engine.game_objects.render_components;

import engine.game_objects.J_Transform;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

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
        if(currentAnim.GetSpriteSheet() != null) {
            gc.drawImage(currentAnim.GetSpriteSheet(), xPosition, 0, currentAnim.GetFrameSize().getX(), currentAnim.GetFrameSize().getY(),
                    transform.GetCorner().getX(), transform.GetCorner().getY(), transform.GetSize().getX(), transform.GetSize().getY());
        }
    }

    public J_SpriteAnimation GetCurrentAnimation()
    {
        return currentAnim;
    }

    public float GetXPosition()
    {
        return xPosition;
    }

    public LinkedList<J_SpriteAnimation> GetAnimations() {
        return animations;
    }

    public void AddAnimation(J_SpriteAnimation animation)
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

    @Override
    public TitledPane GetProperties()
    {
        TitledPane spriteAnimatorPane = new TitledPane();
        spriteAnimatorPane.setText("Sprite Animator");

        GridPane spriteAnimatorContent = new GridPane();
        spriteAnimatorContent.setPadding(new Insets(10,0,10,0));
        spriteAnimatorContent.setHgap(5);
        spriteAnimatorContent.setVgap(5);

        spriteAnimatorContent.add(new Label("     Current Animation:"), 0, 0);
        TextField curr = new TextField(animations.indexOf(currentAnim) + "");
        curr.setOnAction(event -> {
            int index = Integer.parseInt(curr.getText());

            if(index < animations.size() && index >= 0)
                currentAnim = animations.get(index);
            else
                curr.setText(animations.indexOf(currentAnim) + "");
        });
        spriteAnimatorContent.add(curr, 1, 0);

        Pane currAnim = currentAnim.GetProperties();

        GridPane.setColumnSpan(currAnim, 2);
        spriteAnimatorContent.add(currAnim, 0, 1);

        TitledPane all = new TitledPane();
        all.setText("All Animations");

        GridPane allContent = new GridPane();
        allContent.setPadding(new Insets(0,0,0,0));
        allContent.setHgap(5);
        allContent.setVgap(5);

        int i = 0;
        for(J_SpriteAnimation anim : animations)
        {
            allContent.add(anim.GetProperties(), 0, i);
            i++;
        }
        all.setContent(allContent);
        GridPane.setColumnSpan(all, 2);
        spriteAnimatorContent.add(all, 0, 2);

        spriteAnimatorPane.setContent(spriteAnimatorContent);

        return spriteAnimatorPane;
    }
}
