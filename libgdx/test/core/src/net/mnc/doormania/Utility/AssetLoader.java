package net.mnc.doormania.Utility;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation;

/**
 * Created by Ernst on 7/25/2015.
 * <h1> AssetLoader </h1>
 * <p> loads asset needed in the game. </p>
 */
public class AssetLoader
{
    public static final BitmapFont font = new BitmapFont(Gdx.files.internal("Font/font.txt"),
            Gdx.files.internal("Font/font.png"), false);
    public static final Texture[] hpbar = new Texture[4];
    private static final TextureAtlas playerAtlas = new TextureAtlas(Gdx.files.internal("Animation/playerAtlas.atlas"));
    public static final TextureRegion[] playerSheet = new TextureRegion[5];
    public static Animation[] userAnim = new Animation[3];

    public static void load()
    {
        hpbar[0] = new Texture("screen/hp0.png");
        hpbar[1] = new Texture("screen/hp1.png");
        hpbar[2] = new Texture("screen/hp2.png");
        hpbar[3] = new Texture("screen/hp3.png");

        playerSheet[0] = playerAtlas.findRegion("playerStand");
        playerSheet[1] = playerAtlas.findRegion("playerJump1");
        playerSheet[2] = playerAtlas.findRegion("playerRun1");
        playerSheet[3] = playerAtlas.findRegion("playerRun2");
        playerSheet[4] = playerAtlas.findRegion("playerRun3");
        userAnim[0] = new Animation(1/3f, playerSheet[0]); // stand
        userAnim[1] =  new Animation(1/3f, playerSheet[2], playerSheet[3], playerSheet[4]); // run
        userAnim[2] = new Animation(1/3f, playerSheet[1]); // jump

    }

}
