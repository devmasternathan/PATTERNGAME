package net.mnc.doormania.Box2D;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import net.mnc.doormania.GUI.GUI;
import net.mnc.doormania.Utility.AnimatedSprite;

public class Door extends Box2D {
    /**
     * <p> constructor: sets the door to a staticBody and initializes the doors shape. </p>
     */
    public Door() {
        this.setBodyDefType(true);
        floorShape = new PolygonShape();
    }

    /**
     * <p> creates the doors in box2D. </p>
     *
     * @param x      position in the box2D world.
     * @param y      position in the box2D world.
     * @param size   the size of the PolygonShape.
     * @param world  an instance of the World class.
     * @param sprite an instance of the sprite class.
     */
    public void initDoor(float x, float y, int size, World world, Sprite sprite, int index, Floor floor)
    {
        this.bDef.position.set(x, y);
        this.body = world.createBody(this.bDef);
        floorShape.setAsBox(size, size);
        this.fDef.isSensor = true;
        this.setShapeToFixtureDef(floorShape);
        this.body.createFixture(this.fDef).setUserData("door");
        this.body.setUserData(sprite);
        setA[index * 7] = index + 1;
        setA[(index * 7) + 1] = GUI.RWC_X(x);
        setA[(index * 7) + 2] = GUI.RWC_Y(y);
        setA[(index * 7) + 3] = floor.getFloorOn(GUI.RWC_Y(y));
    }

    /**
     * <p> number of doors </p>
     *
     * @param numOfDoors the number of doors that is in a level.
     */
    public void setNumOfDoors(int numOfDoors)
    {
        this.numOfDoors = numOfDoors;
        setA = new float[numOfDoors * 7];
    }

    /**
     * <p> get the location of the doors and store it in the respective areas </p>
     *
     * @return array that contains the door locations.
     */
    public float[] getSetA() {
        return setA;
    }

    /**
     * <p> if touch a door return the door number. </p>
     *
     * @param x position on the x plane.
     * @param y position on the y plane.
     * @return the door you are on.
     */
    public int doorID(float x, float y, boolean active) {

        if(active) {
            if (setA[1] + 30 > x && setA[1] - 30 < x && setA[2] + 30 > y && setA[2] - 30 < y)
                doorTouching = 1;

            else if (setA[8] + 30 > x && setA[8] - 30 < x && setA[9] + 30 > y && setA[9] - 30 < y)
                doorTouching = 2;

            else if (setA[15] + 30 > x && setA[15] - 30 < x && setA[16] + 30 > y && setA[16] - 30 < y)
                doorTouching = 3;

            else if (setA[22] + 30 > x && setA[22] - 30 < x && setA[23] + 30 > y && setA[23] - 30 < y)
                doorTouching = 4;

            else if (setA[29] + 30 > x && setA[29] - 30 < x && setA[30] + 30 > y && setA[30] - 30 < y)
                doorTouching = 5;

            else if (setA[36] + 30 > x && setA[36] - 30 < x && setA[37] + 30 > y && setA[37] - 30 < y)
                doorTouching = 6;

            else
                doorTouching = 0;
        }

        return doorTouching;
    }

    /**
     * <p> link doors to each other. Better if the doors are even. </p>
     * @param first ...this door links...
     * @param link ... with this door and ...
     */
    public void doorLink(int first, int link) {

        length = setA.length / 7;

        for (int i = 0; i < length ; i++){
            for (int j = 0; j < length; j++){
                if(setA[(i * 7)] == first && setA[(j * 7)] == link){

                    // doors are put into each other.
                            setA[(i * 7) + 4] = setA[(j * 7) + 1];
                            setA[(i * 7) + 5] = setA[(j * 7) + 2];
                            setA[(i * 7) + 6] = setA[(j * 7) + 3];
                            setA[(j * 7) + 4] = setA[(i * 7) + 1];
                            setA[(j * 7) + 5] = setA[(i * 7) + 2];
                            setA[(j * 7) + 6] = setA[(i * 7) + 3];
                    }
                }
            }
        }

    /**
     * <p> enables a character to go into a door. </p>
     * @param x x axis position
     * @param y y axis position
     * @param character an instance of the character class.
     * @param enterDoor allows the character to enter the door.
     * @param world an instance of the world class.
     * @param sprite an instance of the sprite class.
     */
    public void enterDoor(float x, float y, Character character, boolean enterDoor, World world,  AnimatedSprite sprite, boolean touched){

        id = doorID(x , y, touched);

        if(id > 0 && enterDoor)
        {
            world.destroyBody(character.body);
            character.setActive(false);
        }

        if(setA[0] == id && enterDoor)
        {
            character.initCharacter(GUI.FWC_X(setA[4]), GUI.FWC_Y(setA[5]), world, sprite);
            character.setActive(true);
        }

        if(setA[7] == id && enterDoor)
        {
            character.initCharacter(GUI.FWC_X(setA[11]), GUI.FWC_Y(setA[12]), world, sprite);
            character.setActive(true);
        }

        if(setA[14] == id && enterDoor)
        {
            character.initCharacter(GUI.FWC_X(setA[18]), GUI.FWC_Y(setA[19]), world, sprite);
            character.setActive(true);
        }

        if(setA[21] == id && enterDoor)
        {
            character.initCharacter(GUI.FWC_X(setA[25]), GUI.FWC_Y(setA[26]), world, sprite);
            character.setActive(true);
        }

        if(setA[28] == id && enterDoor)
        {
            character.initCharacter(GUI.FWC_X(setA[32]), GUI.FWC_Y(setA[33]), world, sprite);
            character.setActive(true);
        }

        if(setA[35] == id && enterDoor)
        {
            character.initCharacter(GUI.FWC_X(setA[39]), GUI.FWC_Y(setA[40]), world, sprite);
            character.setActive(true);
        }
    }

    /**
     * <p> go to the nearest door. x and y values respectively. </p>
     * @return door value.
     */
    public float seekDoor(float userFloor, float aiFloor, boolean nearDoor)
    {

        return seekDoor;
    }

    float setA[];
    float seekDoor = 0;
    int numOfDoors;
    PolygonShape floorShape;
    int id, doorTouching, length;

}
