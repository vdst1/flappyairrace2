package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static com.mygdx.game.flappyairrace2.HEIGHT;
import static com.mygdx.game.flappyairrace2.WIDTH;

/**
 * Created by vitor on 11/10/2017.
 */

public class MenuState extends State {
    private Texture background;
    private Texture playBtn;
    private Texture playBtn2;
    public MenuState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, WIDTH / 2, HEIGHT/2);
        background = new Texture("bg.png");
        playBtn = new Texture("playbtn.png");
        playBtn2 = new Texture("playbtn2.png");
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
             gsm.set(new PlayState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, 0,0);
        sb.draw(playBtn, cam.position.x - playBtn.getWidth()/2,cam.position.y - playBtn.getHeight()/2);
        sb.draw(playBtn2,cam.position.x - playBtn2.getWidth()/2,cam.position.y - playBtn.getHeight()/6);
        sb.end();

    }
    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
        playBtn2.dispose();
    }
}
