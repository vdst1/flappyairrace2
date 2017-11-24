package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.particles.influencers.ColorInfluencer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

import javax.swing.DebugGraphics;

import sun.security.util.Debug;

/**
 * Created by vitor on 20/10/2017.
 */

public class Obstacle {
    public static final int OBSTACLE_WIDTH = 52;

    private static final int FLUCTUATION = 130;
    private static final int OBSTACLE_GAP = 100;
    private static final int LOWEST_OPENING = 120;
    private Texture topObstacle, bottomObstacle;
    private Vector2 posTopObstacle, posBotObstacle;
    private Rectangle boundsTop, boundsBot;
    private Random rand;
    private Sensor pointsSensor;

    public Obstacle(float x) {
        topObstacle = new Texture("topobstacle.png");
        bottomObstacle = new Texture("bottomobstacle.png");
        rand = new Random();

        posTopObstacle = new Vector2(x, rand.nextInt(FLUCTUATION) + OBSTACLE_GAP + LOWEST_OPENING);
        posBotObstacle = new Vector2(x, posTopObstacle.y - OBSTACLE_GAP - bottomObstacle.getHeight());


        boundsTop = new Rectangle(posTopObstacle.x, posTopObstacle.y, topObstacle.getWidth(), topObstacle.getHeight());
        boundsBot = new Rectangle(posBotObstacle.x, posBotObstacle.y, bottomObstacle.getWidth(),bottomObstacle.getHeight());

        pointsSensor = new Sensor(this);

    }

    public Texture getTopObstacle() {
        return topObstacle;

    }



    public Texture getBottomObstacle() {
        return bottomObstacle;
    }

    public Vector2 getPosTopObstacle() {
        return posTopObstacle;
    }

    public Vector2 getPosBotObstacle() {
        return posBotObstacle;
    }

    public void reposition(float x){
        posTopObstacle.set(x, rand.nextInt(FLUCTUATION) + OBSTACLE_GAP + LOWEST_OPENING);
        posBotObstacle.set(x, posTopObstacle.y - OBSTACLE_GAP - bottomObstacle.getHeight());
        boundsTop.setPosition(posTopObstacle.x, posTopObstacle.y);
        boundsBot.setPosition(posBotObstacle.x, posBotObstacle.y);
        pointsSensor = new Sensor(this);
    }

    public boolean collides(Rectangle player){
        return player.overlaps(boundsTop) || player.overlaps(boundsBot);
    }

    public boolean sensorCollides(Rectangle player){
        return pointsSensor.collides(player);
    }

    public void dispose(){
        topObstacle.dispose();
        bottomObstacle.dispose();
    }
}
