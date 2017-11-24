package com.mygdx.game.sprites;

import com.badlogic.gdx.math.Rectangle;

/**
 * Created by victo on 09/11/2017.
 */

public class Sensor {

    private Rectangle areaWhereYouGetPoints;
    private boolean flag = false;
    public Sensor(Obstacle Obstacle){ //TODO: podes somar a largura dos obstáculos ao rectangulo de pontos

        float bottomY = Obstacle.getPosBotObstacle().y+Obstacle.getBottomObstacle().getHeight();
        float topY = Obstacle.getPosTopObstacle().y;

        areaWhereYouGetPoints = new Rectangle((Obstacle.getPosBotObstacle().x+ Obstacle.getBottomObstacle().getWidth()-1), bottomY,
                1, topY-bottomY);

    }


    public boolean collides(Rectangle player){


       if(flag)
            return false;

       flag = player.overlaps(areaWhereYouGetPoints);


       return flag;

    }
}
