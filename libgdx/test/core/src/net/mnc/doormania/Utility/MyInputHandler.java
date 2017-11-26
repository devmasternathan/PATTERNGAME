package net.mnc.doormania.Utility;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

public class MyInputHandler implements InputProcessor
{

	public MyInputHandler()
	{
		timer = new Timer(0, 2);
	}

	public Vector2 getVector2(){
        return userDir;
    }

	@Override
	public boolean keyDown(int keycode)
    {

		switch(keycode)
        {

		case Input.Keys.RIGHT:
			action = 1;
			direction = 1;
			control = 1;
			break;

		case Input.Keys.LEFT:
			action = 1;
			direction = -1;
			control = 2;
		    break;

		case Input.Keys.SPACE:
			jump = true;
			action = 2;
			control = 3;
			break;
		}

		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		switch(keycode) {
			
		case Input.Keys.LEFT:
			action = 0;
			control = 0;
            break;

		case Input.Keys.RIGHT:
			action = 0;
			control = 0;
			break;

		case Input.Keys.SPACE:
			jump = false;
			action = 0;
			control = 0;
			break;

		}
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button)
	{

		// go left
		if (screenX < this.x)
		{
			action = 1;
			direction = -1;
			control = 2;
		}
		// go right
		if (screenX > this.x)
		{
			action = 1;
			direction = 1;
			control = 1;
		}

		touchingDown = true;
		touchingUp = false;

		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button)
	{
		touchingDown = false;
		touchingUp = true;
		action = 0;
		control = 0;

		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer)
    {
		return true;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

    public int getAction() {
        return action;
    }

    public int getDirection() {
        return direction;
    }

	public void manageMovement(boolean onFloor, float x, float y, float delta)
	{

		this.x = x;
		this.y = y;

		// simulate tap
		if(touchingDown)
        {
            timer.runTimer(delta);
            onlyOnce = true;
        }


            if (touchingUp)
            {
                if (timer.time < .13f && timer.time != timer.startTime && onlyOnce)
                {
                    control = 3;
                    jump = true;
                }
                else
                {
                    jump = false;
                    timer.time = 0;
                }
                //onlyOnce = false;
            }


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
				if(jump && onFloor)
                {
                    userDir.y = 4000;
                    onlyOnce = false;
                }
				if(!onFloor)
					userDir.y = -100;
				break;
		}
	}

    Vector2 userDir = new Vector2(0,0);
    boolean jump = false;
	int action = 0;
    int direction = 1;
	int control = 0;
	boolean touchingDown = false;
	boolean touchingUp = false;
    boolean onlyOnce;
	Timer timer;
	float x;
	float y;
}
