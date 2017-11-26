package net.mnc.doormania;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import net.mnc.doormania.Box2D.LevelManager;
import net.mnc.doormania.Box2D.User;
import net.mnc.doormania.GUI.GUI;
import net.mnc.doormania.GUI.GUIElement;
import net.mnc.doormania.Utility.AnimationHandler;
import net.mnc.doormania.Utility.AssetLoader;
import net.mnc.doormania.Utility.MyContactListener;
import net.mnc.doormania.Utility.MyInputHandler;
import net.mnc.doormania.Utility.MyInputHandler1;
import net.mnc.doormania.Utility.Timer;

public class Launcher extends ApplicationAdapter
{

	@Override
	public void create ()
	{
        AssetLoader.load();
		batch = new SpriteBatch();
		screen = new GUI(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        timer = new Timer(30, 0);
        hpBar = new GUIElement(screen.left() + 40, (screen.top() - 30),screen.getWidthSize(), screen.getHeightSize() - 130,
                AssetLoader.hpbar);

        levelManager = new LevelManager(-10, -10);
        levelManager.loadLevel("Levels/dracula.tmx");
        levelManager.addLayer("floors");
        levelManager.getFloorManager().setFloors(41, 48, true, true, true, true, false);
        levelManager.addCoins();
        levelManager.getDoorManager().setNumOfDoors(6);
        levelManager.addLayer("doors");
        levelManager.getDoorManager().doorLink(2, 3);
        levelManager.getDoorManager().doorLink(5, 1);
        levelManager.getDoorManager().doorLink(4, 6);

        b2D = new Box2DDebugRenderer();
        tmr = new OrthogonalTiledMapRenderer(levelManager.getTiledMap(), batch);
        userAnim = new AnimationHandler(AssetLoader.userAnim, 10);
        user = new User(10, 10, 10);
        user.initCharacter(screen.left() + 40, screen.bottom() + 90,
                levelManager.getWorld(), userAnim.getShownAnimation(0,0));
        myInputHandler = new MyInputHandler();
        myContactListener = new MyContactListener();

        myContactListener.setCoinTotal(levelManager.getCoinCounter());
        Gdx.input.setInputProcessor(myInputHandler);
        levelManager.getWorld().setContactListener(myContactListener);


    }

    @Override
    public void render ()
	{

        delta = Gdx.graphics.getDeltaTime();
        batch.setProjectionMatrix(screen.getCam().projection);

        userX = GUI.RWC_X(user.returnX());
        float userY = GUI.RWC_Y(user.returnY());


        // input information
        //myInputHandler.manageMovement(levelManager.getFloorManager().isOnFloor(), user.returnX(), user.returnY(), delta);
        myInputHandler.manageMovement(levelManager.getFloorManager().isOnFloor(), userX, userY, delta);
        user.manageMovement(myInputHandler.getVector2());

        if(myContactListener.isCoinCollected())
            levelManager.getWorld().destroyBody(myContactListener.getRemoveCoin());


        levelManager.getWorld().step(delta, 6, 2);
        levelManager.getFloorManager().changeFloors(userY, 0);
        levelManager.getDoorManager().enterDoor(userX, userY, user,
                Gdx.input.isKeyJustPressed(Input.Keys.Z), levelManager.getWorld(), userAnim.getShownAnimation(0, 0),
                myContactListener.isDoorTouchedUser()
        );


        mousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        screen.getCam().setToOrtho(false);

        // camera follows the player
        screen.getCam().zoom = .4f;
        screen.getCam().position.set(user.returnX(), user.returnY(), 0);
        screen.getCam().update();


        tmr.setView(screen.getCam());
        tmr.render();


        batch.begin();
        timer.runTimer(Gdx.graphics.getDeltaTime());
        AssetLoader.font.draw(batch, String.valueOf((int) timer.getTime()), screen.middleWidth() - 30, screen.top() - 10);
        AssetLoader.font.draw(batch, "Coins : ", (screen.right() - 280), screen.top() - 10);
        AssetLoader.font.draw(batch, String.valueOf(myContactListener.getCoinTotal()) + "/" + String.valueOf(levelManager.getCoinCounter()),
                screen.right() - 160, screen.top() - 10);

        if(Gdx.input.isKeyJustPressed(Input.Keys.A) && c < 3)
            c++;

        hpBar.changeTex(c);
        hpBar.getSprite().draw(batch);

        //animation for user
        userAnim.setPosition(user.returnX(), user.returnY());


        userAnim.getShownAnimation(myInputHandler.getAction(), myInputHandler.getDirection()).draw(batch);
        batch.end();

        b2D.render(levelManager.getWorld(), screen.getCam().combined);


        //System.out.println("current action : " + myInputHandler.getAction());
       /* System.out.println("current action : " + handler.getAction());
        System.out.println("y coordinate : " + user.returnY());
        System.out.println("user floor : " + levelManager.getFloorManager().getUserFloor());
        System.out.println("userY : " + userY);
        System.out.println("userX : " + userX);
        */
        //System.out.println("current action : " + handler.getAction());


	}

    SpriteBatch batch;
    GUI screen;
    Vector3 mousePos;
    Timer timer;
    GUIElement hpBar;
    LevelManager levelManager;
    int c = 0;
    float delta;
    private Box2DDebugRenderer b2D;
    private OrthogonalTiledMapRenderer tmr;
    private MyContactListener myContactListener;
    private MyInputHandler myInputHandler;
    private AnimationHandler userAnim;
    private float userX;
    User user;

}
