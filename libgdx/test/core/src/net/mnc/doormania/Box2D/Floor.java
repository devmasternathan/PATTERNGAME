package net.mnc.doormania.Box2D;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Floor extends Box2D{

    /**
     * <p> initializes the floor by setting it to static and setting a temporary PolygonShape. </p>
     */
    public Floor(){
        // TODO : make the floor class use edgeshape because ai and user don't get stuck on floor.
        this.setBodyDefType(true);
        floorShape = new PolygonShape();
    }

    /**
     * <p> sets the floor boundaries in the world so each object knows what floor they are on.</p>
     * @param floorSize the space between floors.
     */
    public void setFloors(float groundFloor, int floorSize, boolean firstFloor, boolean secondFloor,
                          boolean thirdFloor, boolean fourthFloor, boolean fifthFloor)
    {
        this.floorSize = floorSize;
        this.groundFloor = groundFloor;
        this.firstFloor = firstFloor;
        this.secondFloor = secondFloor;
        this.thirdFloor = thirdFloor;
        this.fourthFloor = fourthFloor;
        this.fifthFloor = fifthFloor;
    }

    /**
     * <p> creates the floors in box2D. </p>
     * @param x position in the box2D world.
     * @param y position in the box2D world.
     * @param size the size of the PolygonShape.
     * @param world an instance of the World class.
     * @param sprite an instance of the sprite class.
     */
    public void initFloorBody(float x, float y, int size, World world, Sprite sprite)
    {
        this.bDef.position.set(x, y);
        this.body = world.createBody(this.bDef);
        floorShape.setAsBox(size, size - (size / 2));
        this.setShapeToFixtureDef(floorShape);
        this.body.createFixture(this.fDef).setUserData("floor");
        this.body.setUserData(sprite);
    }

    public float getUserFloor() {
        return userFloor;
    }

    public float getAiFloor() {
        return aiFloor;
    }

    /**
     * <p> changes the floor using world coordinates (the left bottom is (0,0) </p>
     * @param userY float value representing the users y coordinate after conversion to world coordinates.
     * @param aiY float value representing the ais y coordinate after conversion to world coordinates.
     */
    public void changeFloors(float userY, float aiY)
    {

        /************************************************************************************************************/
        // set Ais floor
        if (aiY > groundFloor + (floorSize * 4) && aiY < groundFloor + (floorSize * 5) && fifthFloor)
            aiFloor = 5;

        else if (aiY > groundFloor + (floorSize * 3) && aiY < groundFloor + (floorSize * 4) && fourthFloor)
            aiFloor = 4;

        else if (aiY > groundFloor + (floorSize * 2) && aiY < groundFloor + (floorSize * 3) && thirdFloor)
            aiFloor = 3;

        else if (aiY > groundFloor + floorSize && aiY < groundFloor + (floorSize * 2) && secondFloor)
            aiFloor = 2;

        else if (aiY > groundFloor && aiY < groundFloor + floorSize && firstFloor)
            aiFloor = 1;

        /************************************************************************************************************/

        // set user floor
        if (userY > groundFloor + (floorSize * 4) && userY < groundFloor + (floorSize * 5) && fifthFloor){
            userFloor = 5;

            // check if the user is on the floor
            if(userY > groundFloor + (floorSize * 4) - 3 && userY < groundFloor + (floorSize * 4) + 3)
                onFloor = true;
            else
                onFloor = false;

        }

            // set user floor
        else if (userY > groundFloor + (floorSize * 3) && userY < groundFloor + (floorSize * 4) && fourthFloor){
            userFloor = 4;

            // check if the user is on the floor
            if(userY > groundFloor + (floorSize * 3) - 3 && userY < groundFloor + (floorSize * 3) + 3)
                onFloor = true;
            else
                onFloor = false;

        }

            // set user floor
        else if (userY > groundFloor + (floorSize * 2) && userY < groundFloor + (floorSize * 3) && thirdFloor){
            userFloor = 3;

            // check if the user is on the floor
            if(userY > groundFloor + (floorSize * 2) - 3 && userY < groundFloor + (floorSize * 2) + 3)
                onFloor = true;
            else
                onFloor = false;

        }

        // set user floor
        else if(userY > groundFloor + floorSize && userY < groundFloor + (floorSize * 2) && secondFloor) {
            userFloor = 2;

            // check if the user is on the floor
            if(userY > groundFloor + floorSize - 3 && userY < groundFloor + floorSize + 3)
                onFloor = true;
            else
                onFloor = false;

        }
        // set user floor
        else if(userY > groundFloor && userY < groundFloor + floorSize && firstFloor) {
            userFloor = 1;

        // check if the user is on the floor
            if(userY > groundFloor - 3 && userY < groundFloor + 3)
                onFloor = true;
            else
                onFloor = false;
        }

        /************************************************************************************************************/
    }

    public boolean isOnFloor() {
        return onFloor;
    }

    /**
     * <p> get what floor an element is on </p>
     */
    public int getFloorOn(float y){
        int floorOn = 0;

        // set user floor
        if(y > groundFloor + (floorSize * 4) && y < groundFloor + (floorSize * 5) && fifthFloor)
            floorOn = 5;

            // set user floor
        else if(y > groundFloor + (floorSize * 3) && y < groundFloor + (floorSize * 4) && fourthFloor)
            floorOn = 4;

            // set user floor
        else if(y > groundFloor + (floorSize * 2) && y < groundFloor + (floorSize * 3) && thirdFloor)
            floorOn = 3;

            // set user floor
        else if(y > groundFloor + floorSize && y < groundFloor + (floorSize * 2) && secondFloor)
            floorOn = 2;

            // set user floor
        else if(y > groundFloor && y < groundFloor + floorSize && firstFloor)
            floorOn = 1;

        /************************************************************************************************************/

        return floorOn;
    }

    /**
     * <p> determine if the ai is on the same floor as the  user. </p>
     * @return
     */
    public boolean isOnSameFloor(){
        return aiFloor == userFloor;
    }


    float aiFloor = 0;
    int floorSize = 0;
    float groundFloor = 0;
    float userFloor = 0;
    private PolygonShape floorShape;
    boolean onFloor = false;
    boolean thirdFloor = false;
    boolean fifthFloor = false;
    boolean firstFloor = false;
    boolean secondFloor = false;
    boolean fourthFloor = false;
}
