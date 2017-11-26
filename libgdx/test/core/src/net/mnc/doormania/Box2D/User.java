package net.mnc.doormania.Box2D;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import net.mnc.doormania.Utility.AnimatedSprite;


/**
 * Created by luffy on 3/12/2015.
 */
public class User extends Character {

    public User(int size, int range, int speed){
        this.setBodyDefType(false);
        this.speed = speed;
        this.size = size;
        this.range = range;
        userShape = new PolygonShape();
    }

    @Override
    public void initCharacter(float x, float y, World world, AnimatedSprite sprite){
        this.bDef.position.set(x, y);
        this.body = world.createBody(this.bDef);
        userShape.setAsBox(size, size);
        this.setShapeToFixtureDef(userShape);
        this.body.createFixture(this.fDef).setUserData("user");
        this.body.setUserData(sprite);
    }

    @Override
    public float returnX() {
        return this.body.getPosition().x;
    }

    @Override
    public float returnY() {
        return this.body.getPosition().y;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void setSpeed(float speed){
        this.speed = speed;
    }

    @Override
    public float getSpeed() {
        return speed;
    }

    @Override
    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean ifActive() {
        return active;
    }

    @Override
    public void setLimitation(boolean limit) {
    // TODO: Set a limitation to user with the time
    }

    @Override
    public void setHp(int hp) {
        this.hp = hp;
    }

    @Override
    public int getHp() {
        return hp;
    }

    @Override
    public void applyDamage(int damage) {
            hp = hp - damage;
    }

    @Override
    public void manageMovement(Vector2 dir) {
        this.body.applyForceToCenter(dir, true);
    }

    @Override
    public int getRange() {
        return range;
    }

    @Override
    public boolean isDead() {
        return hp > 0;
    }

    private int hp;
    private int size = 0;
    private int range = 0;
    private float speed = 0;
    private boolean active = true;
    private PolygonShape userShape;
}
