package com.mygdx.gametanks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.audio.Music;

public class TankSelect implements Screen {
    final bomb game;
    private Texture backgroundImage;
    private TextureRegion backgroundTexture;
    private Texture tank1Image;
    private Texture tank2Image;
    private Texture tank3Image;
    private Texture tankseimage;
    Music gameMusic;
    Rectangle tanktext;
    Rectangle tank1;
    Rectangle tank2;
    Rectangle tank3;
    OrthographicCamera camera;
    public TankSelect(final bomb game) {
        this.game = game;
        backgroundImage = new Texture(Gdx.files.internal("backgroundselection1.jpg"));
        tankseimage = new Texture(Gdx.files.internal("tankse.png"));
        backgroundTexture = new TextureRegion(backgroundImage, 0, 0, 1920, 1200);
        tank1Image = new Texture(Gdx.files.internal("Tank1.png"));
        tank2Image = new Texture(Gdx.files.internal("Tank2.png"));
        //gameMusic = Gdx.audio.newMusic(Gdx.files.internal("war.mp3"));
        //gameMusic.setLooping(true);
        tank3Image = new Texture(Gdx.files.internal("Tank3.png"));

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        tank1 = new Rectangle();
        tank1.x = 100  -64 ;
        tank1.y = 140;

        tank1.width = 128;
        tank1.height = 128;
        tanktext = new Rectangle();
        tanktext.x = 250 ;
        tanktext.y = 300;

        tanktext.width = 328;
        tanktext.height = 128;

        tank2 = new Rectangle();
        tank2.x = 400  - 64 ;
        tank2.y = 140;

        tank2.width = 128;
        tank2.height = 128;

        tank3 = new Rectangle();
        tank3.x = 700  - 64 ;
        tank3.y = 140;
        // the bottom screen edge
        tank3.width = 128;
        tank3.height = 128;


    }


    @Override
    public void show() {
        //gameMusic.play();

    }
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 0);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(backgroundTexture, 0,0, 800, 480);

        game.batch.draw(tankseimage, tanktext.x, tanktext.y, tanktext.width, tanktext.height);
        game.font.draw(game.batch, "Tank1", 100, 300);
        game.batch.draw(tank1Image, tank1.x, tank1.y, tank1.width, tank1.height);
        game.font.draw(game.batch, "Tank2", 400, 300);
        game.batch.draw(tank2Image, tank2.x, tank2.y, tank2.width, tank2.height);
        game.font.draw(game.batch, "Tank3", 700, 300);
        game.batch.draw(tank3Image, tank3.x, tank3.y, tank3.width, tank3.height);
        game.batch.end();


        if (tank1.x < 0)
            tank1.x = 0;
        if (tank1.x > 800 - 64)
            tank1.x = 800 - 64;

        if (tank2.x < 0)
            tank2.x = 0;

        if (tank2.x > 800 - 64)
            tank2.x = 800 - 64;

        if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            game.setScreen(new GameScreen(game));
            dispose();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            game.setScreen(new Startmenu(game));
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
        backgroundImage.dispose();
        tank2Image.dispose();
        tank3Image.dispose();
        tank3Image.dispose();
        tankseimage.dispose();

    }

    }

