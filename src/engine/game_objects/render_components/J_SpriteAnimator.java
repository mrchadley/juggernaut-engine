package engine.game_objects.render_components;

import engine.game_objects.J_Transform;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
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
        spriteAnimatorContent.setPadding(new Insets(10,15,10,15));
        spriteAnimatorContent.setHgap(5);
        spriteAnimatorContent.setVgap(5);

        ImageView current = new ImageView(currentAnim.GetSpriteSheet());
        current.setPreserveRatio(true);
        current.setFitHeight(200);

        ScrollPane scroll = new ScrollPane(current);
        scroll.setPrefWidth(250);

        System.out.println(animations.size());

        spriteAnimatorContent.add(new Label("Current Animation:"), 0, 0);
        spriteAnimatorContent.add(scroll, 0, 1);

        spriteAnimatorContent.add(new Label("All:"), 0, 2);

        int i = 0;
        for(J_SpriteAnimation anim : animations)
        {
            ImageView others = new ImageView(animations.get(i).GetSpriteSheet());
            others.setPreserveRatio(true);
            others.setFitHeight(200);

            VBox panel = new VBox(3);
            panel.setPadding(new Insets(0,0,0,0));
            panel.setPrefWidth(250);
            panel.getChildren().add(new Label((i + 1) + ""));
            ScrollPane animScroll = new ScrollPane(others);
            animScroll.setPrefWidth(250);
            panel.getChildren().add(animScroll);

            spriteAnimatorContent.add(panel, 0, i + 3);
            i++;
        }

        spriteAnimatorPane.setContent(spriteAnimatorContent);

        return spriteAnimatorPane;
    }
}
