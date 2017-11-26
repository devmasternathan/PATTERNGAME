package net.mnc.doormania.Utility;


import com.badlogic.gdx.graphics.g2d.Animation;

/**
 * Created by nathanblanchard on 5/30/2015.
 */
public class AnimationHandler
{
    public AnimationHandler(Animation[] anim, int size)
    {
        this.anim = anim;
        shownAnimation = new AnimatedSprite(anim[0]);
        shownAnimation.setSize(size, size);
        this.size = size;
    }

    public AnimatedSprite getShownAnimation(int action, int direction)
    {
        switch(action)
        {
            case 0:
                shownAnimation.setAnimation(anim[0]); // stand still
                break;
            case 1:
                shownAnimation.setScale(direction, 1);
                shownAnimation.setAnimation(anim[1]); // run
                anim[1].setPlayMode(Animation.PlayMode.LOOP);
                break;
            case  2:
                shownAnimation.setAnimation(anim[2]); // jump
                break;
            case 3:
                shownAnimation.setAnimation(anim[3]);
                break;
            case 4:
                shownAnimation.setAnimation(anim[4]);
                break;
        }
        return shownAnimation;
    }

    public void setPosition(float x, float y)
    {
        shownAnimation.setPosition(x - (size/2) , y - (size/2));
    }

    Animation[] anim;
    AnimatedSprite shownAnimation;
    float size;
}
