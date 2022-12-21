package com.mygdx.gametanks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

public class Startmenu implements Screen {
    final bomb game;
    Texture menuImage;
    Music gameMusic;
    Texture newgame;
    Texture loadgame;
    Texture exitim;
    Rectangle newtext;
    Rectangle loadtext;
    Rectangle exittext;


    OrthographicCamera camera;
    private TextureRegion menutexture;

    public Startmenu(final bomb game) {
        this.game = game;
        menuImage = new Texture(Gdx.files.internal("backgroundselection1.jpg"));
        menutexture = new TextureRegion(menuImage, 0, 0, 1920, 1200);
        newgame = new Texture(Gdx.files.internal("newgame.png"));
        loadgame = new Texture(Gdx.files.internal("loadgame.png"));
        exitim = new Texture(Gdx.files.internal("exit.png"));
        //gameMusic = Gdx.audio.newMusic(Gdx.files.internal("war.mp3"));
        //gameMusic.setLooping(true);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        newtext = new Rectangle();
        loadtext = new Rectangle();
        exittext = new Rectangle();
        newtext.x = 250;
        newtext.y = 300;

        // the bottom screen edge
        newtext.width = 288;
        newtext.height = 158;
        loadtext.x = 250;
        loadtext.y = 200;

        // the bottom screen edge
        loadtext.width = 288;
        loadtext.height = 158;

        exittext.x = 250;
        exittext.y = 100;

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
        game.batch.draw(menutexture, 0, 0, 800, 480);
        game.batch.draw(newgame, newtext.x, newtext.y, newtext.width, newtext.height);
        game.batch.draw(loadgame, loadtext.x, loadtext.y, loadtext.width, loadtext.height);

        game.batch.draw(exitim, exittext.x, exittext.y, exittext.width, exittext.height);
        game.font.draw(game.batch, "Escape : EXIT", 700,20 );
        game.font.draw(game.batch, "N : New Game", 700,60 );
        game.font.draw(game.batch, "L : Load Game", 700,40 );

        game.batch.end();
            if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
                //System.exit(0);
                game.setScreen(new MainMenu(game));
                dispose();
            }
            if (Gdx.input.isKeyPressed(Input.Keys.N)) {
                game.setScreen(new TankSelect(game));
                dispose();
            }
            if (Gdx.input.isKeyPressed(Input.Keys.L)) {
                game.setScreen(new SavedGame1(game));
                dispose();
            }


        /*if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
        game.setScreen(new TankSelect(game));
            dispose();
        }*/

        }

        @Override
        public void resize ( int width, int height){

        }

        @Override
        public void pause () {

        }

        @Override
        public void resume () {

        }

        @Override
        public void hide () {

        }

        @Override
        public void dispose () {
            menuImage.dispose();
            newgame.dispose();
            loadgame.dispose();
            exitim.dispose();


        }
    }
