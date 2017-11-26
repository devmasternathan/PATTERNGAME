package net.mnc.doormania.Utility;

/**
 * <h1> Timer Class </h1>
 * <p> this is a class that will keep time </p>
 */
public class Timer {

    /**
     * <p> constructor </p>
     * @param startTime this is where the time starts.
     * @param endTime this is where the time ends.
     */
    public Timer(float startTime, float endTime)
    {
        this.startTime = startTime;
        this.endTime = endTime;
        time = startTime;

        if(startTime < endTime)
            countingUp = true;

        if(startTime > endTime)
            countingDown = true;
    }

    /**
     * <p> runs timer until finish </p>
     * @param delta what moves the time. Comes from Gdx.graphics.getDelta().
     */
    public void runTimer(float delta) {
        // Allows the time to keep counting.
        if (going) {
         // Allows the time to count up.
            if (countingUp) {
                time += delta;
                if (endTime < time)
                {
                    going = false;
                    countingUp = false;
                }
            }
        // Allows the time to count down.
            if (countingDown) {
                time -= delta;
                if (endTime + .5 > time)
                {
                    going = false;
                    countingDown = false;
                }
            }
        }
    }


    /**
     * <p> output false if the timer stopped. </p>
     */
    public boolean timerStopped(){
        return going;
    }

    public float getTime()
    {
        return time;
    }

    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();

        result.append(Float.toString(time).substring(0,2) +
                ":" + Float.toString(time).substring(3,4)); // messes up sometimes

        return result.toString();
    }

    float time;
    float endTime;
    float startTime;
    boolean countingUp;
    boolean countingDown;
    boolean going = true;
}
