package com.mygdx.game.Scenes;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.sprites.Plane;
import com.mygdx.game.states.PlayState;

import static com.mygdx.game.flappyairrace2.HEIGHT;
import static com.mygdx.game.flappyairrace2.WIDTH;

import java.awt.Label;

/**
 * Created by victo on 25/11/2017.
 */

public class HUD {
    public Stage stage;
    private Viewport viewport;

    private Integer points;

    Label scoreLabel;

    public HUD(SpriteBatch sb){

        points = 0;

        viewport = new FillViewport(WIDTH, HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);


        scoreLabel = new Label(PlayState.getPoints().getPlane().toString(),new Label.LabelStyle(new BitmapFont()));// nao sei o que fazer aqui, o que supostamente fazes aqui???
        scorePlayerLabel = new Label(GameConfig.getInstance().getScore().getPlayer1().toString(), new Label.LabelStyle(new BitmapFont(), GameConfig.getInstance().getColors().getColor1()));

        table.add(scoreLabel).expandX().padTop(10);

        stage.addActor(table);

    }
    public void update(float delta){



        scoreLabel.setText(PlayState.getPoints().getPlayer().toString());



       /* if(NetworkManager.getInstance().isConnected()){

            scoreOpponentLabel.setText(GameModel.getInstance().getScore().getPlayer2().toString());

            scorePlayerLabel.setText(GameModel.getInstance().getScore().getPlayer1().toString());

        }*/

    }



    @Override

    public void dispose() {

        stage.dispose();

    }
}
