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

public class Endone implements Screen {
    final bomb game;
    Music gameMusic;


    Texture exitim;

    Texture win2im;

    Rectangle exittext;
    Rectangle win2text;
    Texture menuImage;
    OrthographicCamera camera;
    private TextureRegion menutexture;
    public Endone(final bomb game){
        this.game = game;
        menuImage = new Texture(Gdx.files.internal("backgroundselection1.jpg"));
        menutexture = new TextureRegion(menuImage, 0, 0, 1920, 1200);
        win2im=new Texture(Gdx.files.internal("player2won.png"));
        exitim = new Texture(Gdx.files.internal("exit.png"));



        //gameMusic = Gdx.audio.newMusic(Gdx.files.internal("war.mp3"));
        //gameMusic.setLooping(true);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        win2text=new Rectangle();
        win2text.x = 250;
        win2text.y = 300;

        // the bottom screen edge
        win2text.width = 300;
        win2text.height = 158;


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

        game.batch.draw(win2im, win2text.x, win2text.y, win2text.width, win2text.height);
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
        win2im.dispose();
        menuImage.dispose();
        exitim.dispose();


    }
}
