package net.mnc.doormania.GUI;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by Ernst on 7/18/2015.
 * Gui element basically is a class that takes care of all
 * of the gui elements in the game.
 */
public class GUIElement
{
    /**
     * <p> used when the gui element has some kind of animation attributed with it. </p>
     * @param x the x-axis location of the gui element.
     * @param y the y-axis location of the gui element.
     * @param width the width of the sprite.
     * @param height th height of the sprite.
     * @param tex a texture instance that is a image representation of the gui.
     */
    public GUIElement(float x, float y, float width, float height, Texture[] tex)
    {
        this.sprite = new Sprite(tex[0]);
        sprite.setSize(width,height);
        sprite.setPosition(x, y);
        this.tex = tex;
    }

    /**
     * <p> used for simple gui elements. </p>
     * @param x the x-axis location of the gui element.
     * @param y the y-axis location of the gui element.
     * @param width the width of the sprite.
     * @param height th height of the sprite.
     * @param tex a texture instance that is a image representation of the gui.
     */
    public GUIElement(float x, float y, float width, float height, Texture tex)
    {
        this.sprite = new Sprite(tex);
        sprite.setSize(width,height);
        sprite.setPosition(x , y );
    }

    public Sprite getSprite()
    {
        return sprite;
    }

    public float getX()
    {
        return sprite.getX();
    }

    public float getY()
    {
        return sprite.getY();
    }

    public void changeTex(int i)
    {
        sprite.setTexture(tex[i]);
    }

    private Sprite sprite;
    private Texture[] tex;

}
