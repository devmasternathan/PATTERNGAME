package net.mnc.doormania.Box2D;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import net.mnc.doormania.Utility.AnimatedSprite;

import java.lang.*;


/**
 * @author David Lee and Ernst Nathaniel Blanchard
 * @version 1.0
 * @since 2015 - 10 - 02
 * <p> AI class: when initialize set speed, size, attack range and movement </p>
 */

public class AI extends Character {

    public AI(int size, int range, float speed){
        this.setBodyDefType(false);
        this.speed = speed;
        this.size = size;
        this.range = range;
        aiShape = new PolygonShape();
    }

    @Override
    public void initCharacter(float x, float y, World world, AnimatedSprite sprite){
        this.bDef.position.set(x, y);
        this.body = world.createBody(this.bDef);
        aiShape.setAsBox(size, size);
        this.setShapeToFixtureDef(aiShape);
        this.body.createFixture(this.fDef).setUserData("ai");
        this.body.setUserData(sprite);
        this.world = world;
    }

    @Override
    public  float returnX() {
        return this.body.getPosition().x;
    }

    @Override
    public  float returnY() {
        return this.body.getPosition().y;
    }

    @Override
    public void applyDamage(int damage) {
            hp = hp - damage;
    }

    @Override
    public  void manageMovement(Vector2 dir) {
        this.body.applyForceToCenter(dir, true);
    }

    @Override
    public  void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public  int getSize() {
        return size;
    }

    @Override
    public void setSpeed(float speed){
        this.speed = speed;
    }

    @Override
    public  float getSpeed() {
        return speed;
    }

    @Override
    public  boolean ifActive() {
        return active;
    }

    @Override
    public  int getRange() {
        return range;
    }

    @Override
    public  int getHp() {
        return hp;
    }

    @Override
    public  void setLimitation(boolean limit) {
        if(!canAttack && limit)
            canAttack = true;
        else
            canAttack= false;
    }

    @Override
    public  void setHp(int hp) {
        this.hp = hp;
    }

    @Override
    public boolean isDead() {
        return hp > 0;
    }

    /**
     * <p> get if anything is close to the ai. </p>
     * @param onSameFloor if the user is on the same floor as the ai.
     * @param chasingX the chasing variable position.
     * @param aiX the ai position.
     * @return boolean
     */
    public boolean ifClose(boolean onSameFloor,float chasingX, float aiX){
        close = (onSameFloor && chasingX < aiX + 70
                && aiX - 70 < chasingX);

        return close;
    }

    public boolean isCanAttack() {
        return canAttack;
    }

    public World world;
    private int hp;
    private int size = 0;
    private int range = 0;
    private float speed = 0;
    private PolygonShape aiShape;
    private boolean active = true;
    private boolean canAttack = true;
    boolean close ;
}
