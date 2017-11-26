package net.mnc.doormania.Box2D;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;

/**
 * @author David Lee
 * @version 0.1
 * @since 2014 - 31 - 08
 * <p> Box2D class </p>
 */
public class Box2D {
    /**
     * <h1> Box2D Constructor </h1>
     * <p> initializes the fixture
     *  and body definitions </p>
     */
    public Box2D(){
        this.bDef = new BodyDef();
        this.fDef = new FixtureDef();
    }

    /**
     *<p> sets the position of the body</p>
     *@param x Sets the x position of the body.
     *@param y Sets the y position of the body.
     */
    public void setBodyDefPos(float x, float y){
        this.bDef.position.set(x, y);
    }

    /**
     * <p> returns the body position in Vector2</p>.
     */
    public Vector2 returnBodyPos(){
        return this.body.getPosition();
    }

    /**
     * <p> Defies the body type. </p>
     * @param fix <code>true</code> StaticBody <code>false</code> DynamicBody
     */
    public void setBodyDefType(boolean fix){
        if(fix)
            this.bDef.type = BodyDef.BodyType.StaticBody;
        else
            this.bDef.type = BodyDef.BodyType.DynamicBody;
    }

    /**
     * <p> sets fixture to the body. </p>
     * @param def an instance of the FixtureDef.
     */
    public void setFixtureToBody(FixtureDef def){
        this.body.createFixture(def);
    }

    /**
     *<p> sets shape to fixture. </p>
     *@param shape instance of the Shape class.
     */
    public void setShapeToFixtureDef(Shape shape){
        this.fDef.shape = shape;
    }

    public Body body;
    public BodyDef bDef;
    public FixtureDef fDef;
}
