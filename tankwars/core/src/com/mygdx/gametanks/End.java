package com.mygdx.gametanks;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

public class End implements Screen {
    final bomb game;
    Music gameMusic;


    Texture exitim;

    Texture win1im;

    Rectangle exittext;
    Rectangle win1text;
    Texture menuImage;
    OrthographicCamera camera;
    private TextureRegion menutexture;
    public End(final bomb game){
        this.game = game;
        menuImage = new Texture(Gdx.files.internal("backgroundselection1.jpg"));
        menutexture = new TextureRegion(menuImage, 0, 0, 1920, 1200);
        exitim = new Texture(Gdx.files.internal("exit.png"));
        win1im = new Texture(Gdx.files.internal("player1won.png"));
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        win1text=new Rectangle();
        win1text.x = 200;
        win1text.y = 300;

        // the bottom screen edge
        win1text.width = 488;
        win1text.height = 158;


        exittext=new Rectangle();
        exittext.x = 250;
        exittext.y = 0;

        // the bottom screen edge
        exittext.width = 288;
        exittext.height = 158;


    }
    @Override
    public void show() {
        //gameMusic.play();

    }



    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 0);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(menutexture, 0,0, 800, 480);

        game.batch.draw(win1im, win1text.x, win1text.y, win1text.width, win1text.height);
        game.batch.draw(exitim, exittext.x, exittext.y, exittext.width, exittext.height);
        game.font.draw(game.batch, "E : EXIT", 700,20 );

        game.batch.end();

        if (Gdx.input.isKeyPressed(Input.Keys.E)) {
            game.setScreen(new MainMenu(game));
            dispose();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            game.setScreen(new MainMenu(game));
            dispose();
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        menuImage.dispose();
        win1im.dispose();
        exitim.dispose();


    }
}
