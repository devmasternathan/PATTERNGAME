package net.mnc.doormania.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;

/**
 * Created by Ernst on 8/6/2015.
 * <h1> GUI </h1>
 * <p> This class is used to keep things on the screen such
 * as health meter ora timer. </p>
 */
public class GUI
{
    /**
     * <p> screen element that will be constantly shown on screen like hp or time. </p>
     * @param w the size of the sprite itself.
     * @param h the size of the sprite itself.
     */
    public GUI(float w, float h)
    {
        cam = new OrthographicCamera(w, h);
        this.screenWidth = w;
        this.screenHeight = h;

        Gdx.app.log("screen widthSize :", String.valueOf(screenWidth));
        Gdx.app.log("screen heightSize :", String.valueOf(screenHeight));

        if(screenWidth > 719 && screenWidth < 1281)
        {
            widthSize = (screenWidth / 12);
            heightSize = (screenWidth / 12);
        }

        if(screenWidth > 230 && screenWidth < 500)
        {
            widthSize = (screenWidth / 12);
            heightSize = (screenWidth / 12);
        }

        Gdx.app.log("size width :", String.valueOf(widthSize));
        Gdx.app.log("size height :", String.valueOf(heightSize));

    }

    /**
     * <p> Returns the camera that will be used on a level. </p>
     * @return
     */
    public OrthographicCamera getCam()
    {
        return cam;
    }

    /**
     * <p> put the screen to the middle of the screen. </p>
     * @return
     */
    public float middleWidth()
    {
        return screenWidth / 2;
    }

    /**
     * <p> put the screen to the middle of the screen. </p>
     * @return
     */
    public float middleHeight()
    {
        return screenHeight / 2;
    }

    /**
     * <p> puts you at the top of the screen. Enter a negitive number to put the object lower on the screen. </p>
     */
    public float top()
    {
    return screenHeight;
    }

    /**
     * <p> bottom of the screen </p>
     */
    public float bottom()
    {
        return 0;
    }

    /**
     * <p> put the screen to the left of the screen. Add a number to put closer to the right. </p>
     * @return
     */
    public float left()
    {
        return 0;
    }

    /**
     * <p> put the screen to the right of the screen. Add a negitive number to bring the item closer to the left.</p>
     * @return
     */
    public  float right()
    {
        return screenWidth;
    }

    /**
     * <p> convert x value to real world coordinates. </p>
     * @param x
     */
    public static float RWC_X(float x)
    {
     //   return x + 200;
        return x + 150;
    }


    /**
     * <p> convert x value to fake world coordinates. </p>
     * @param x
     */
    public static float FWC_X(float x)
    {
        return x - 160;

    }

    /**
     * <p> convert y value to real world coordinates. </p>
     * @param y
     */
    public static float RWC_Y(float y)
    {
        return y + 19;
    }

    /**
     * <p> convert y value to fake world coordinates. </p>
     * @param y
     */
    public static float FWC_Y(float y)
    {
        return y - 19;

    }

    public float getHeightSize()
    {
        return heightSize;
    }

    public float getWidthSize()
    {
        return widthSize;
    }

    float screenWidth;
    float screenHeight;
    float widthSize;
    float heightSize;
    OrthographicCamera cam;

}
