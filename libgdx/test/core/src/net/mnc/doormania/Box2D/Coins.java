package net.mnc.doormania.Box2D;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.World;

public class Coins extends Box2D {

    /**
     * <p> initializes the coin instance. </p>
     */
    public Coins(){
        this.setBodyDefType(true);
        cShape = new CircleShape();
    }

    /**
     * <p> creates the coins in box2D. </p>
     * @param x position in the box2D world.
     * @param y position in the box2D world.
     * @param size the size of the PolygonShape.
     * @param world an instance of the World class.
     * @param sprite an instance of the sprite class.
     */
    public void initCoins(float x, float y, int size, World world, Sprite sprite){
        this.bDef.position.set(x, y);
        this.body = world.createBody(this.bDef);
        cShape.setRadius(size);
        this.fDef.isSensor = true;
        this.setShapeToFixtureDef(cShape);
        this.body.createFixture(this.fDef).setUserData("coin");
        this.body.setUserData(sprite);
    }

    /**
     * <p> dispose garbage </p>
     */
    public void dispose(){
        cShape.dispose();
    }

    private CircleShape cShape;
}
