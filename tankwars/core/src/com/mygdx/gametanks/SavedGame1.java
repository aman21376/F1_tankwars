package com.mygdx.gametanks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.math.Rectangle;

public class SavedGame1 implements Screen {
    final bomb game;
    Music gameMusic;
    Texture menuImage;
    Texture savedim;
    Rectangle savedtext;
    OrthographicCamera camera;

    private TextureRegion menutexture;
    private Stage stage;
    public SavedGame1(final bomb game){
        this.game = game;
        menuImage = new Texture(Gdx.files.internal("backgroundselection1.jpg"));

        menutexture = new TextureRegion(menuImage, 0, 0, 1920, 1200);
        savedim = new Texture(Gdx.files.internal("savedgames.png"));
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        savedtext=new Rectangle();
        //gameMusic = Gdx.audio.newMusic(Gdx.files.internal("war.mp3"));
        //gameMusic.setLooping(true);
        savedtext.x = 200 ; // center the bucket horizontally
        savedtext.y = 300; // bottom left corner of the bucket is 20 pixels above
        // the bottom screen edge
        savedtext.width = 408;
        savedtext.height = 128;
    }
//    void create(){
//        stage=new Stage(new ScreenViewport());
//        int Help_Guides = 12;
//        int rh=Gdx.graphics.getWidth();
//        int cg=Gdx.graphics.getWidth();
//        addBackgroundGuide(Help_Guides);
//
//
//
//    }


//    public SavedGame(Drop game) {
//        this.game = game;
//    }

    @Override
    public void show() {
        //gameMusic.play();


    }



    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 0);
//        Label.LabelStyle label1Style=new Label.LabelStyle();
//        BitmapFont myfont=new BitmapFont(Gdx.files.internal("texttype1.png"));
//        label1Style.font = myfont;
//        label1Style.fontColor = Color.RED;
//        Label label1=new Label("TEST FONT ",label1Style);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(menutexture, 0,0, 800, 480);
        game.batch.draw(savedim, savedtext.x, savedtext.y, savedtext.width, savedtext.height);
        game.font.draw(game.batch, "Game 1", 100, 300);
        game.font.draw(game.batch, "Game 2", 100, 150);
        game.font.draw(game.batch, "Game 3", 300, 300);
        game.font.draw(game.batch, "Game 4", 300, 150);
        game.font.draw(game.batch, "NUM-KEYS:SELECT", 700, 40);
        game.font.draw(game.batch, "Escape:Back", 700, 20);
        game.batch.end();

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            game.setScreen(new MainMenu(game));
            dispose();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.NUM_1)) {
            game.setScreen(new GameScreen(game));
            dispose();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.NUM_2)) {
            game.setScreen(new GameScreen(game));
            dispose();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.NUM_3)) {
            game.setScreen(new GameScreen(game));
            dispose();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.NUM_4)) {
            game.setScreen(new GameScreen(game));
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
        //stage.dispose();
        savedim.dispose();


    }
}
