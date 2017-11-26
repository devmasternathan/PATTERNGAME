package net.mnc.doormania.Utility;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Ernst on 8/15/2015.
 */
public class MyInputHandler1 implements  GestureDetector.GestureListener
{
    public MyInputHandler1()
    {
        userDir= new Vector2();
        gestureDetector = new GestureDetector(this);
    }


    @Override
    public boolean touchDown(float x, float y, int pointer, int button)
    {

        test = gestureDetector.isLongPressed(3);

        // go left
        if(test)
        {
            if (button > 0 && x < this.x) {
                action = 1;
                direction = -1;
                control = 2;
            }
            // go right
            if (button > 0 && x > this.x) {
                action = 1;
                direction = 1;
                control = 1;
            }
        }
        else
        {
            action = 0;
            control = 0;
        }


        return true;
    }

    // this is jump
    @Override
    public boolean tap(float x, float y, int count, int button)
    {

        return true;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    // this is attack
    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    public Vector2 getUserDir()
    {
        return userDir;
    }

    public int getAction() {
        return action;
    }

    public int getDirection() {
        return direction;
    }

    public void manageMovement(boolean jump,  float x, float y)
    {
        this.x = x;
        this.y = y;
        this.jump = jump;

        switch (control)
        {
            case 0:
                userDir.x = 0;
                break;
            case 1:
                userDir.x = 200;
                break;
            case 2:
                userDir.x = -200;
                break;
            case 3:
                userDir.y = 4000;
                break;
        }
    }

    public GestureDetector getGestureDetector() {
        return gestureDetector;
    }

    public boolean isTest() {
        return test;
    }

    int action = 0;
    int control = 0;
    int direction = 1;
    boolean jump;
    Vector2 userDir;
    boolean test;
    float x;
    float y;
    GestureDetector gestureDetector;
}
