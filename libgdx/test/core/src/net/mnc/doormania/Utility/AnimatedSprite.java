package net.mnc.doormania.Utility;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimatedSprite extends Sprite {

private Animation animation;

private float time;

private boolean playing = true;

private boolean autoUpdate = true;

private boolean keepSize;

private boolean centerFrames;

public AnimatedSprite(Animation animation) {
this(animation, false);
}

public AnimatedSprite(Animation animation, boolean keepSize) {
super(animation.getKeyFrame(0));
this.animation = animation;
this.keepSize = keepSize;
}

public void update() {
update(Gdx.graphics.getDeltaTime());
}

public void update(float delta) {
oldX = getX();
oldY = getY();
oldWidth = getWidth();
oldHeight = getHeight();
oldOriginX = getOriginX();
oldOriginY = getOriginY();
if(playing) {
setRegion(animation.getKeyFrame(time += delta));
if(!keepSize)
setSize(getRegionWidth(), getRegionHeight());
}
}

private float oldX, oldY, oldWidth, oldHeight, oldOriginX, oldOriginY;

@Override
public void draw(Batch batch) {
if(autoUpdate)
update();
boolean centerFramesEnabled = centerFrames && !keepSize; // if keepSize is true

if(centerFramesEnabled) {
float differenceX = oldWidth - getRegionWidth(), differenceY =
oldHeight - getRegionHeight();
setOrigin(oldOriginX - differenceX / 2, oldOriginY - differenceY / 2);
setBounds(oldX + differenceX / 2, oldY + differenceY / 2, oldWidth -
differenceX, oldHeight - differenceY);
}
super.draw(batch);
if(centerFramesEnabled) {
setOrigin(oldOriginX, oldOriginY);
setBounds(oldX, oldY, oldWidth, oldHeight);
}
}

public void flipFrames(boolean flipX, boolean flipY) {
flipFrames(flipX, flipY, false);
}

public void flipFrames(boolean flipX, boolean flipY, boolean set) {
flipFrames(0, animation.getAnimationDuration(), flipX, flipY, set);
}

public void flipFrames(float startTime, float endTime, boolean flipX, boolean flipY) {
flipFrames(startTime, endTime, flipX, flipY, false);
}

public void flipFrames(float startTime, float endTime, boolean flipX, boolean flipY,
boolean set) {
for(float t = startTime; t < endTime; t += animation.getFrameDuration()) {
TextureRegion frame = animation.getKeyFrame(t);
frame.flip(flipX && (set ? !frame.isFlipX() : true), flipY && (set ? !
frame.isFlipY() : true));
}
}

public void play() {
playing = true;
}

public void pause() {
playing = false;
}

public void stop() {
playing = false;
time = 0;
}

public void setTime(float time) {
this.time = time;
}

public float getTime() {
return time;
}

public Animation getAnimation() {
return animation;
}

public void setAnimation(Animation animation) {
this.animation = animation;
}

public boolean isPlaying() {
return playing;
}

public void setPlaying(boolean playing) {
this.playing = playing;
}

public boolean isAnimationFinished() {
return animation.isAnimationFinished(time);
}

public boolean isAutoUpdate() {
return autoUpdate;
}

public void setAutoUpdate(boolean autoUpdate) {
this.autoUpdate = autoUpdate;
}

public boolean isKeepSize() {
return keepSize;
}

public void setKeepSize(boolean keepSize) {
this.keepSize = keepSize;
}

public boolean isCenterFrames() {
return centerFrames;
}

public void setCenterFrames(boolean centerFrames) {
this.centerFrames = centerFrames;
}
}
