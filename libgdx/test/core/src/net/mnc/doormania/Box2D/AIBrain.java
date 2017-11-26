package net.mnc.doormania.Box2D;

import com.badlogic.gdx.math.Vector2;

/**
 * handles the brain of the ai.
 */
public class AIBrain {

    /**
     * <p> controls ai movement. </p>
     */
    public AIBrain() {
        moveAi = new Vector2(0, 0);
    }

    /**
     * <p> allows ai to track there target </p>
     * @param aiX position of the ai.
     * @param chasingX position of the thing you are chasing.
     * @param doorX door position to go to.
     * @param speed the speed the ai.
     * @param onSameFloor if the door is on the same floor.
     */
    public void targetCharacter(float aiX, float chasingX, float doorX,
                                float speed, boolean onSameFloor) {

        //move right
        if ((chasingX > aiX && onSameFloor) || (doorX > aiX && !onSameFloor))
        {
            moveAi.x = speed;
            right = true;
            left = false;
        }

        //move left
        if ((chasingX < aiX && onSameFloor) || (doorX < aiX && !onSameFloor))
        {
            moveAi.x = -speed;
            left = true;
            right = false;
        }

        // make the ai jump
        if(jump)
            moveAi.y = 200;
        else
            moveAi.y = 0;

        if(left)
            direction = -1;
        else
            direction = 1;


    }

    /**
     * <p> returns the vector </p>
     * @return vector2
     */
    public Vector2 getMoveAi() {
        return moveAi;
    }

    /**
     * <p> manage the attack. </p>
     * @param onSameFloor if the ai and the user is on the same floor.
     * @param ifClose if the ai is close to what it is chasing.
     * @param canAttack if the ai can attack it will. a limiter.
     */
    public void manageAttack(boolean onSameFloor, boolean ifClose, boolean canAttack)
    {
        if(ifClose && canAttack) {
            shortRange = true;
            longRange = false;
        }
        else if(canAttack && onSameFloor) {
            longRange = true;
            shortRange = false;
        }
        else{
            longRange = false;
            shortRange = false;
        }

    }

    public void setJump(boolean jump) {
        this.jump = jump;
    }

    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }

    public int getDirection() {
        return direction;
    }

    public boolean isLongRange() {
        return longRange;
    }

    Vector2 moveAi;
    boolean longRange;
    boolean shortRange;
    boolean right;
    boolean left;
    boolean jump;
    int direction;

}
