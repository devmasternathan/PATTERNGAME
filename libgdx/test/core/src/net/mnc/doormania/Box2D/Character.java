package net.mnc.doormania.Box2D;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import net.mnc.doormania.Utility.AnimatedSprite;


/**
 * @author David Lee and Ernst Nathaniel Blanchard
 * @version 1.0
 * @since 2015 - 10 - 02
 * <p> Box2D class </p>
 */

public abstract class Character extends Box2D
{

    public abstract void initCharacter(float x, float y, World world, AnimatedSprite sprite);

    public abstract float returnX();

    public abstract float returnY();

    public abstract int getSize();

    public abstract float getSpeed();

    public abstract void setActive(boolean active);

    public abstract boolean ifActive();

    public abstract void setLimitation(boolean limit);

    public abstract void setHp(int hp);

    public abstract int getHp();

    public abstract void manageMovement(Vector2 dir);

    public abstract int getRange();

    public abstract void setSpeed(float speed);

    public abstract void applyDamage(int damage);

    public abstract boolean isDead();

}
