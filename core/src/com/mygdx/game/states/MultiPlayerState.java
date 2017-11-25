package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.sprites.Obstacle;
import com.mygdx.game.sprites.Plane;

import io.socket.client.IO;
import io.socket.client.Socket;

import static com.mygdx.game.flappyairrace2.HEIGHT;
import static com.mygdx.game.flappyairrace2.WIDTH;




/*
 * Created by vitor on 16/10/2017.
 */


public class MultiPlayerState extends State {

    private static final int OBSTACLE_SPACING = 125;
    private static final int OBSTACLE_COUNT= 4;
    private static final int GROUND_Y_OFFSET = -30;

    private Plane airplane;
    private Texture bg;
    private Texture ground;
    private Vector2 groundPos1, groundPos2;
    private int points = 0;

    private Socket socket;

    private Array<Obstacle> obstacles;

    public MultiPlayerState(GameStateManager gsm) {
        super(gsm);
        airplane = new Plane(50,300);
        cam.setToOrtho(false, WIDTH / 2, HEIGHT/2);
        bg = new Texture("bg.png");
        ground= new Texture("ground.png");
        groundPos1= new Vector2(cam.position.x - cam.viewportWidth / 2,GROUND_Y_OFFSET);
        groundPos2= new Vector2((cam.position.x - cam.viewportWidth /2)+ground.getWidth(),GROUND_Y_OFFSET);
        cam.setToOrtho(false, WIDTH/2,HEIGHT/2);
        connectSocket();
        obstacles = new Array<Obstacle>();

        for(int i = 1; i<=OBSTACLE_COUNT; i++){
            obstacles.add(new Obstacle(i*(OBSTACLE_SPACING + Obstacle.OBSTACLE_WIDTH)));
        }
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched())
            airplane.saltar();
    }

    @Override
    public void update(float dt) {
        handleInput();
        updateGround();
        airplane.update(dt);
        cam.position.x = airplane.getPosition().x + 80;

        for(int i = 0;  i< obstacles.size; i++){
            Obstacle obstacle = obstacles.get(i);




            if(cam.position.x - (cam.viewportWidth/2) > obstacle.getPosTopObstacle().x + obstacle.getTopObstacle().getWidth()){
                obstacle.reposition(obstacle.getPosTopObstacle().x + ((Obstacle.OBSTACLE_WIDTH + OBSTACLE_SPACING)* OBSTACLE_COUNT));
            }

            if(obstacle.sensorCollides(airplane.getBounds())){
                points++;
                System.out.println(points);
            }
            if(obstacle.collides(airplane.getBounds()))
                gsm.set(new PlayState(gsm));

        }
        if(airplane.getPosition().y <= ground.getHeight() + GROUND_Y_OFFSET)
            gsm.set(new PlayState(gsm));
        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg, cam.position.x - (cam.viewportWidth / 2), 0);
        sb.draw(airplane.getTexture(),airplane.getPosition().x, airplane.getPosition().y);
        for(Obstacle obstacle : obstacles) {
            sb.draw(obstacle.getTopObstacle(), obstacle.getPosTopObstacle().x, obstacle.getPosTopObstacle().y);
            sb.draw(obstacle.getBottomObstacle(), obstacle.getPosBotObstacle().x, obstacle.getPosBotObstacle().y);
        }

        sb.draw(ground, groundPos1.x,groundPos1.y);
        sb.draw(ground, groundPos2.x,groundPos2.y);
        sb.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
        airplane.dispose();
        ground.dispose();

        for(Obstacle obstacle : obstacles)
            obstacle.dispose();

    }

    private void updateGround(){
        if(cam.position.x - (cam.viewportWidth / 2)> groundPos1.x + ground.getWidth())
            groundPos1.add(ground.getWidth() * 2,0);
        if(cam.position.x - (cam.viewportWidth / 2)> groundPos2.x + ground.getWidth())
            groundPos2.add(ground.getWidth() * 2,0);
    }

    public void connectSocket(){
        try{
            socket = IO.socket("http://localhost:8080");
            socket.connect();
        } catch(Exception e){
            System.out.println(e);
        }
    }




}
