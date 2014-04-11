package in.championswimmer.sfg.lib;

import android.view.MotionEvent;

/**
 * Created by championswimmer on 12/4/14.
 */
public class GestureAnalyser {

    public static final int SWIPE_1_UP = 11;
    public static final int SWIPE_1_DOWN = 12;
    public static final int SWIPE_1_LEFT = 13;
    public static final int SWIPE_1_RIGHT = 14;
    public static final int SWIPE_2_UP = 21;
    public static final int SWIPE_2_DOWN = 22;
    public static final int SWIPE_2_LEFT = 23;
    public static final int SWIPE_2_RIGHT = 24;

    private float[] initialX = new float[2];
    private float[] initialY = new float[2];
    private float[] finalX = new float[2];
    private float[] finalY = new float[2];
    private float[] delX = new float[2];
    private float[] delY = new float[3];

    private int numFingers = 0;

    public GestureAnalyser () {
    }

    public void trackGesture(MotionEvent ev, int n) {
        for (int i = 0; i < n; i++) {
            initialX[i] = ev.getX(i);
            initialY[i] = ev.getY(i);
        }
        numFingers = n;
    }

    public int getGesture (MotionEvent ev, int n) {
        for (int i = 0; i < n; i++) {
            finalX[i] = ev.getX(i);
            finalY[i] = ev.getY(i);
            delX[i] = finalX[i] - initialX[i];
            delY[i] = finalY[i] - initialY[i];
        }
        numFingers = n;
        return calcGesture();
    }

    private int calcGesture () {
        if (numFingers < 2) {
            if ( ( -(delY[0]) ) > ( 2 * (Math.abs(delX[0]) ) ) ) {
                return SWIPE_1_UP;
            }

            if ( ( (delY[0]) ) > ( 2 * (Math.abs(delX[0]) ) ) ) {
                return SWIPE_1_DOWN;
            }

            if ( ( -(delX[0]) ) > ( 2 * (Math.abs(delY[0]) ) ) ) {
                return SWIPE_1_LEFT;
            }

            if ( ( (delX[0]) ) > ( 2 * (Math.abs(delY[0]) ) ) ) {
                return SWIPE_1_RIGHT;
            }
        }
        return 0;
    }




}