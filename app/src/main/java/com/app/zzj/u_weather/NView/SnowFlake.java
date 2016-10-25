package com.app.zzj.u_weather.NView;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;

import java.util.Random;


/**
 * Created by sedwt on 2016/10/25.
 */
public class SnowFlake {
    private static final float ANGE_RANGE = 0.1f;
    private static final float HALF_ANGLE_RANGE = ANGE_RANGE / 2f;
    private static final float HALF_PI = (float) Math.PI / 2f;
    private static final float ANGLE_SEED = 25f;
    private static final float ANGLE_DIVISOR = 10000f;
    private static final int INCREMENT_LOWER = 2;
    private static final int INCREMENT_UPPER = 4;
    private static final int FLAKE_SIZE_LOWER = 7;
    private static final int FLAKE_SIZE_UPPER = 20;

    private final Random random;
    private final Point position;
    private float angle;
    private final float increment;
    private final float flakeSize;
    private final Paint paint;

    public static SnowFlake create(int width, int height, Paint paint) {
        Random random = new Random();
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        Point position = new Point(x, y);
        float angle = random.nextFloat() * ANGE_RANGE + HALF_PI - HALF_ANGLE_RANGE;
        float increment = random.nextFloat() * INCREMENT_LOWER + INCREMENT_UPPER - INCREMENT_LOWER;
        float flakeSize = random.nextFloat() *FLAKE_SIZE_LOWER + FLAKE_SIZE_UPPER - FLAKE_SIZE_LOWER;
        return new SnowFlake(random, position, angle, increment, flakeSize, paint);
    }

    SnowFlake(Random random, Point position, float angle, float increment, float flakeSize, Paint paint) {
        this.random = random;
        this.position = position;
        this.angle = angle;
        this.increment = increment;
        this.flakeSize = flakeSize;
        this.paint = paint;
    }

    private void move(int width, int height) {
        double x = position.x;
        double y = position.y + increment * Math.sin(angle);

        //angle += random.getRandom(-ANGLE_SEED, ANGLE_SEED) / ANGLE_DIVISOR;
        angle += (random.nextFloat()-0.5)*2*ANGLE_SEED / ANGLE_DIVISOR;

        //position.set((int) x, (int) y);//0.1转为了1，-0.1却转成了0，这造成了雪花单向飘动。
        Log.d("zzj","move:"+angle+","+increment * Math.cos(angle)+","+position.x+","+x);
        position.set((int) x, (int) y);

        if (!isInside(width, height)) {
            reset(width);
        }
    }

    private boolean isInside(int width, int height) {
        int x = position.x;
        int y = position.y;
        return x >= -2*flakeSize && x + 2*flakeSize <= width && y >= -flakeSize - 1 && y <= height;
        //return x > flakeSize  && x + flakeSize < width && y - flakeSize < height && y > flakeSize;
    }

    private void reset(int width) {
        position.x = random.nextInt(width);
        position.y = (int) (-flakeSize - 1);
        angle = random.nextFloat() * ANGE_RANGE + HALF_PI - HALF_ANGLE_RANGE;
    }

    public void draw(Canvas canvas) {
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        move(width, height);
        canvas.drawCircle(position.x, position.y, flakeSize, paint);
    }
}
