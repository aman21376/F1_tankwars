package com.mygdx.gametanks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;


public class MainMenu implements Screen {

    final bomb game;
    Music gameMusic;
    private Texture backgroundImage;
    private Texture tankvstanks;
    private TextureRegion backgroundTexture;
    private Texture clickany;
    OrthographicCamera camera;
    Rectangle tanktext;
    Rectangle clicktext;

    public MainMenu(final bomb game) {
        this.game = game;
        backgroundImage = new Texture(Gdx.files.internal("hometanks.jpg"));
        backgroundTexture = new TextureRegion(backgroundImage, 0, 0, 1920, 1080);
        tankvstanks = new Texture(Gdx.files.internal("tanksvstanks.png"));
        clickany = new Texture(Gdx.files.internal("clickany.png"));
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        gameMusic = Gdx.audio.newMusic(Gdx.files.internal("war.mp3"));
        gameMusic.setLooping(true);

        tanktext = new Rectangle();
        tanktext.x = 250 ;
        tanktext.y = 240;

        // the bottom screen edge
        tanktext.width = 288;
        tanktext.height = 158;

        clicktext = new Rectangle();
        clicktext.x = 250 ;
        clicktext.y = 70;

        clicktext.width = 308;
        clicktext.height = 68;
    }

    @Override
    public void show() {
        gameMusic.play();

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 0);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(backgroundTexture, 0,0, 800, 480);
        game.batch.draw(tankvstanks, tanktext.x, tanktext.y, tanktext.width, tanktext.height);
        game.batch.draw(clickany, clicktext.x, clicktext.y, clicktext.width, clicktext.height);
        game.batch.end();

        if (Gdx.input.isTouched())  {
            game.setScreen(new Startmenu(game));
            dispose();
        }
        if(Gdx.input.isTouched(Keys.ENTER)){
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
        tankvstanks.dispose();
        backgroundImage.dispose();
        clickany.dispose();
        gameMusic.dispose();

    }

}
