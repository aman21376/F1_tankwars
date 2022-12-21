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

public class pause implements Screen {
    final bomb game;
    Music gameMusic;
    Texture savegame;
    Rectangle savegametext;
    Texture exitim;
    Texture resumeim;
    Texture pauseim;
    Rectangle resumetext;
    Rectangle exittext;
    Rectangle pausetext;
    Texture menuImage;
    OrthographicCamera camera;
    private TextureRegion menutexture;
    public pause(final bomb game){
        this.game = game;
        menuImage = new Texture(Gdx.files.internal("backgroundselection1.jpg"));
        menutexture = new TextureRegion(menuImage, 0, 0, 1920, 1200);
        exitim = new Texture(Gdx.files.internal("exit.png"));
        resumeim = new Texture(Gdx.files.internal("resume.png"));
        pauseim = new Texture(Gdx.files.internal("pause.png"));
        savegame=new Texture(Gdx.files.internal("savegame.png"));
        //gameMusic = Gdx.audio.newMusic(Gdx.files.internal("war.mp3"));
        //gameMusic.setLooping(true);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        pausetext=new Rectangle();
        pausetext.x = 200;
        pausetext.y = 350;

        // the bottom screen edge
        pausetext.width = 488;
        pausetext.height = 158;

        resumetext=new Rectangle();
        resumetext.x = 250;
        resumetext.y = 250;

        // the bottom screen edge
        resumetext.width = 288;
        resumetext.height = 158;
        exittext=new Rectangle();
        exittext.x = 250;
        exittext.y = 0;

        // the bottom screen edge
        exittext.width = 288;
        exittext.height = 158;

        savegametext=new Rectangle();
        savegametext.x = 250;
        savegametext.y = 150;

        savegametext.width = 288;
        savegametext.height = 158;
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
        game.batch.draw(pauseim, pausetext.x, pausetext.y, pausetext.width, pausetext.height);

        game.batch.draw(resumeim, resumetext.x, resumetext.y, resumetext.width, resumetext.height);
        game.batch.draw(savegame, savegametext.x, savegametext.y, savegametext.width, savegametext.height);
        game.batch.draw(exitim, exittext.x, exittext.y, exittext.width, exittext.height);
        game.font.draw(game.batch, "R : RESUME", 700, 10);
        game.font.draw(game.batch, "E : EXIT", 700,20 );

        game.batch.end();
        if (Gdx.input.isKeyPressed(Input.Keys.R)) {
            game.setScreen(new GameScreen(game));
            dispose();
        }
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
        exitim.dispose();
        resumeim.dispose();
        pauseim.dispose();
        resumeim.dispose();

    }
}
