package net.mnc.doormania.Box2D;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by nathanblanchard on 4/26/2015.
 * <p> adds the level elements like the floor,
 *  the doors ect...</p>
 */
public class LevelManager
{

    /**
     * <p> constructor of the level </p>
     */
    public LevelManager( int shiftLeft, int shiftDown)
    {
        world = new World(new Vector2(0, -98f), false);
        tiledMap = new TiledMap();
        doorManager = new Door();
        floorManager = new Floor();
        coinManager = new Coins();
        this.shiftLeft = shiftLeft;
        this.shiftDown = shiftDown;
    }

    /**
     * <p> load level from tmx files. </p>
     * @param levelName used to load level from tmx file.
     */
    public void loadLevel(String levelName)
    {
        tiledMap = new TmxMapLoader().load(levelName);
    }

    /**
     * <p> add coins to the level. </p>
     */
    public void addCoins()
    {
        MapLayer mapLayer;
        mapLayer = tiledMap.getLayers().get("coins");

        for(MapObject mo : mapLayer.getObjects()){
            float x = Float.parseFloat(mo.getProperties().get("x").toString());
            float y = Float.parseFloat(mo.getProperties().get("y").toString());
            coinManager.initCoins(x - shiftLeft,
                    y - shiftDown, 10, world, new Sprite(new Texture("badlogic.jpg")));
            coinCounter++;
        }
        Gdx.app.log("coin total", String.valueOf(coinCounter));
    }

    /**
     * <p> instance of a TileMap</p>
     * @return tileMap instance.
     */
    public TiledMap getTiledMap()
    {
        return tiledMap;
    }

    /**
     * <p> instance of a Coins</p>
     * @return coins instance.
     */
    public Coins getCoinManager() {
        return coinManager;
    }

    /**
     * <p> instance of a Door</p>
     * @return door instance.
     */
    public Door getDoorManager() {
        return doorManager;
    }

    /**
     * <p> instance of a Floor</p>
     * @return floor instance.
     */
    public Floor getFloorManager() {
        return floorManager;
    }

    /**
     * <p> get total number of coins</p>
     * @return total number of coins.
     */
    public int getCoinCounter() {
        return coinCounter;
    }

    public int getDoorCounter() {
        return doorCounter;
    }

    /**
     * <p> add door layer to the level.</p>
     * @param name of the layer in the tmx file.
     */
    public void addLayer(String name)
    {
        layer = (TiledMapTileLayer)
                tiledMap.getLayers().get(name);


        float tileSizeWidth = layer.getTileWidth();
        float tileSizeHeight = layer.getTileHeight();
        for(int row = 0; row < layer.getHeight();row++) {
            for (int col = 0; col < layer.getWidth(); col++) {
                TiledMapTileLayer.Cell cell = layer.getCell(col, row);
                if (cell == null) continue;
                if (cell.getTile() == null) continue;

                if(name.contains("floor")) {
                    //floor
                    floorManager.initFloorBody((col * tileSizeWidth) - shiftLeft,
                            (row * tileSizeHeight) - shiftDown,
                            5, world, new Sprite(new Texture("badlogic.jpg")));
                    floorManager.body.setUserData("floor");
                }
                if(name.contains("doors")) {
                    //door
                    doorManager.initDoor((col * tileSizeWidth) -  shiftLeft,
                            (row * tileSizeHeight) - shiftDown,
                            5, world, new Sprite(new Texture("badlogic.jpg")), doorCounter, floorManager);
                    floorManager.body.setUserData("doors");
                    doorCounter++;
                }
            }
        }

    }

    public World getWorld() {
        return world;
    }

    int coinCounter = 0;
    int doorCounter = 0;
    int shiftLeft = 0;
    int shiftDown = 0;
    private World world;
    TiledMapTileLayer layer;
    private Door doorManager;
    private TiledMap tiledMap;
    private Floor floorManager;
    private Coins coinManager;
}
