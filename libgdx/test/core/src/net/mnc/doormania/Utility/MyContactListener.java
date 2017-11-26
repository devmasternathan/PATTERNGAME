package net.mnc.doormania.Utility;


import com.badlogic.gdx.physics.box2d.*;

public class MyContactListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        /**
         * fixtureA : Ai, user
         * fixtureB : coin , floor, door
         */
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        //System.out.println("fixtureA :" + fixtureA.getUserData());
        //System.out.println("fixtureB :" + fixtureB.getUserData());

        if (fixtureA.getUserData() != null && fixtureB.getUserData() == "door" && fixtureA.getUserData() == "user"
                || fixtureA.getUserData() == "door" && fixtureB.getUserData() == "user")
            doorTouchedUser = true;


        if (fixtureA.getUserData() != null && fixtureB.getUserData() == "coin" && fixtureA.getUserData() == "user"
                || fixtureA.getUserData() == "coin" && fixtureB.getUserData() == "user"){
            coinCollected = true;
            removeCoin = fixtureB.getBody();
            coinTotal--;
        }

        if (fixtureA.getUserData() != null && fixtureB.getUserData() == "door" && fixtureA.getUserData() == "ai"
                || fixtureA.getUserData() == "door" && fixtureB.getUserData() == "ai")
            doorTouchedAi = true;

    }

    @Override
    public void endContact(Contact contact) {
        /**
         * fixtureA : Ai, user
         * fixtureB : coin , floor, door
         */
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        if (fixtureA.getUserData() != null && fixtureA.getUserData() == "door" && fixtureA.getUserData() == "user")
            doorTouchedUser = false;

        if (fixtureA.getUserData() != null && fixtureB.getUserData() == "coin" && fixtureA.getUserData() == "user")
            coinCollected = false;

        if (fixtureA.getUserData() != null && fixtureB.getUserData() == "door" && fixtureA.getUserData() == "ai"
                || fixtureA.getUserData() == "door" && fixtureB.getUserData() == "ai")
            doorTouchedAi = false;

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

    public boolean isDoorTouchedUser() {
        return doorTouchedUser;
    }

    public Body getRemoveCoin() {
        return removeCoin;
    }

    public boolean isCoinCollected() {
        return coinCollected;
    }

    public int getCoinTotal() {
        return coinTotal;
    }

    public void setCoinTotal(int coinTotal) {
        this.coinTotal = coinTotal;
    }

    public boolean isDoorTouchedAi() {
        return doorTouchedAi;
    }

    private Body removeCoin;
    boolean doorTouchedUser = false;
    boolean coinCollected = false;
    boolean doorTouchedAi = false;
    int coinTotal;
}
