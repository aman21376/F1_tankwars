package com.mygdx.gametanks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Iterator;

public class GameScreen implements Screen {
    final bomb game;
    Texture tank1Image;
    Texture canimage1;
    Texture canimage2;
    Rectangle healthkit;
    private Texture healthkitim;

    Texture tank2Image;
    private Texture backgroundImage;
    private TextureRegion backgroundTexture;
    Sound bombSound;
    Music gameMusic;
    OrthographicCamera camera;
    Rectangle tank1;
    Rectangle tank2;
    private Texture hillimage;
    private TextureRegion hilltexture;
    private Texture bombim;
    int Healthp2=100;
    int Healthp1=100;
    int fuelp2=100;
    int fuelp1=100;
    Rectangle hill1;
    Rectangle can1;
    Rectangle can2;
    Array<Rectangle> bombs1;
    Array<Rectangle>  bombs2;


    public GameScreen(final bomb game) {
        this.game = game;


        //loading the tank images
        tank1Image = new Texture(Gdx.files.internal("Tank11.png"));
        tank2Image = new Texture(Gdx.files.internal("Tank2.png"));
        canimage1 = new Texture(Gdx.files.internal("jerrycan.png"));
        canimage2 = new Texture(Gdx.files.internal("jerrycan.png"));

        Sprite sprite = new Sprite(tank2Image);
        sprite.flip(false, false);
        //loading the background image
        backgroundImage = new Texture(Gdx.files.internal("mybackground1.jpg"));
        backgroundTexture = new TextureRegion(backgroundImage, 0, 0, 1140, 507);
        //loading the hill image
        hillimage = new Texture(Gdx.files.internal("hillsss.png"));
        bombim=new Texture(Gdx.files.internal("bombimage.png"));

        // load the drop sound effect and the rain background "music"
        bombSound = Gdx.audio.newSound(Gdx.files.internal("bomb.wav"));
        gameMusic = Gdx.audio.newMusic(Gdx.files.internal("war1.mp3"));
        gameMusic.setLooping(true);

        // create the camera and the SpriteBatch
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        can1=new Rectangle();
        can1.x=10;
        can1.y=390;
        can1.width=70;
        can1.height=70;
        can2=new Rectangle();
        can2.x=750;
        can2.y=390;
        can2.width=70;
        can2.height=70;
        // create a Rectangle to logically represent the Tank1
        tank1 = new Rectangle();
        tank1.x = 640  - 64 ; // center the tank horizontally
        tank1.y = 125; // bottom left corner of the bucket is 20 pixels above

        //  bottom screen edge for tank 1
        tank1.width = 110;
        tank1.height = 110;
        // create a Rectangle to logically represent the Tank2
        tank2 = new Rectangle();
        tank2.x = 120-64  ; // center the tank horizontally
        tank2.y = 118; // bottom left corner of the tank is 20 pixels above
        // the bottom screen edge for tank 2
        tank2.width = 100;
        tank2.height = 100;

        hill1=new Rectangle();
        hill1.x=0;
        hill1.y=0;
        hill1.width=800;
        hill1.height=400;
        bombs1=new Array<Rectangle>();
        bombs2=new Array<Rectangle>();
        spawnbombs1();
        spawnbombs2();





    }
    public void spawnbombs1(){
        Rectangle bomb1=new Rectangle();
        bomb1.x=tank1.x;
        bomb1.y=tank1.y;
        bomb1.width=32;
        bomb1.height=32;
        bombs1.add(bomb1);

    }
    public void spawnbombs2(){
        Rectangle bomb2=new Rectangle();
        bomb2.x=tank2.x;
        bomb2.y=tank2.y;
        bomb2.width=32;
        bomb2.height=32;
        bombs2.add(bomb2);

    }


    @Override
    public void render(float delta) {
        // cleared the screen
        ScreenUtils.clear(0, 0, 0.2f, 1);

        // updated the camera
        camera.update();


        game.batch.setProjectionMatrix(camera.combined);

        // starts a new batch to create textures,tanks and other important text

        game.batch.begin();
        game.batch.draw(backgroundTexture, 0, 0, 800, 480);
        game.font.draw(game.batch, "  Tank1 health: " + Healthp1, 670, 480);
        game.font.draw(game.batch, "Tank2 health: " + Healthp2, 0, 480);
        game.font.draw(game.batch, "  Fuel 1: " + fuelp1, 720, 380);
        game.font.draw(game.batch, "Fuel 2: " + fuelp2, 0, 380);

        game.batch.draw(tank1Image, tank1.x, tank1.y, tank1.width, tank1.height);
        game.batch.draw(tank2Image, tank2.x, tank2.y, tank2.width, tank2.height);
        game.batch.draw(hillimage, hill1.x, hill1.y, hill1.width, hill1.height);
        game.batch.draw(canimage1, can1.x, can1.y, can1.width, can1.height);
        game.batch.draw(canimage2, can2.x, can2.y, can2.width, can2.height);
        for (Rectangle bomb1 : bombs1) {
            game.batch.draw(bombim, bomb1.x, bomb1.y);
        }
        for (Rectangle bomb2 : bombs2) {
            game.batch.draw(bombim, bomb2.x, bomb2.y);
        }
        game.batch.end();

        // process user input
//        if (Gdx.input.isTouched()) {
//            Vector3 touchPos = new Vector3();
//            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
//            camera.unproject(touchPos);
//            tank1.x = touchPos.x - 64 / 2;
//        }
        if (fuelp1>0) {
            if (Gdx.input.isKeyPressed(Keys.LEFT)) {
                tank1.x -= 120 * Gdx.graphics.getDeltaTime();
                fuelp1-=0.01;
            }


            if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
                tank1.x += 120 * Gdx.graphics.getDeltaTime();
                fuelp1-=0.01;
            }
        }
        if(fuelp2>0) {
            if (Gdx.input.isKeyPressed(Keys.A)) {
                tank2.x -= 120 * Gdx.graphics.getDeltaTime();
                fuelp2 -= 0.01;
            }
            //Right movement of tank2
            if (Gdx.input.isKeyPressed(Keys.D)) {
                tank2.x += 120 * Gdx.graphics.getDeltaTime();
                fuelp2 -= 0.01;


            }
        }
        if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
            game.setScreen(new pause(game));
            dispose();

        }
        if (Gdx.input.isKeyPressed(Keys.SPACE)) {
            spawnbombs1();

        }
        if (Gdx.input.isKeyPressed(Keys.ENTER)) {
            spawnbombs2();

        }
        Iterator<Rectangle> iter = bombs1.iterator();

        while (iter.hasNext()) {
            int speed = 250;
            Rectangle bomb1 = iter.next();
            if (Gdx.input.isKeyPressed(Keys.Z)) {
                bomb1.y += speed * Gdx.graphics.getDeltaTime();
                bomb1.x -= speed * Gdx.graphics.getDeltaTime();


            }
            if (Gdx.input.isKeyPressed(Keys.X)) {
                bomb1.y -= speed * Gdx.graphics.getDeltaTime();
                bomb1.x -= speed * Gdx.graphics.getDeltaTime();


            }
//                if (bomb1.x>0 && bomb1.x<400){
//                    bomb1.y += speed * Gdx.graphics.getDeltaTime();
            bomb1.x -= speed * Gdx.graphics.getDeltaTime();

//                if (bomb1.x>=400 && bomb1.x<800){
//                    bomb1.y -= speed * Gdx.graphics.getDeltaTime();
//                    bomb1.x -= speed * Gdx.graphics.getDeltaTime();
//                }
//            bomb.y += 200 * Gdx.graphics.getDeltaTime();
//            bomb.x += 200 * Gdx.graphics.getDeltaTime();

            if (bomb1.overlaps(tank2)) {
                bombSound.play();
                Healthp2-=8;
                fuelp1+=10;
                iter.remove();
            }
            if (bomb1.y + 64 < 0)
                iter.remove();
        }
        Iterator<Rectangle> iter2 = bombs2.iterator();
        while (iter2.hasNext()) {
            int speed = 250;
            Rectangle bomb2 = iter2.next();
            if (Gdx.input.isKeyPressed(Keys.UP)) {
                bomb2.y += speed * Gdx.graphics.getDeltaTime();
                bomb2.x += speed * Gdx.graphics.getDeltaTime();


            }
            if (Gdx.input.isKeyPressed(Keys.DOWN)) {
                bomb2.y -= speed * Gdx.graphics.getDeltaTime();
                bomb2.x += speed * Gdx.graphics.getDeltaTime();


            }


//                bomb2.y += speed * Gdx.graphics.getDeltaTime();
            bomb2.x += speed * Gdx.graphics.getDeltaTime();

//            if (bomb2.x>=400){
//                bomb2.y -= speed * Gdx.graphics.getDeltaTime();
//                bomb2.x -= speed * Gdx.graphics.getDeltaTime();
//            }
//            bomb.y += 200 * Gdx.graphics.getDeltaTime();
//            bomb.x += 200 * Gdx.graphics.getDeltaTime();

            if (bomb2.overlaps(tank1)) {
                bombSound.play();
                Healthp1-=8;
                fuelp2+=10;
                iter2.remove();
            }
            if (bomb2.y + 64 < 0)
                iter2.remove();
        }
        if(fuelp2==0 && fuelp1==0){
            fuelp2=80;
            fuelp1=80;
        }

        // confirming tank1 are in the screen
        if (tank1.x < 0)
            tank1.x = 0;
        if (tank1.x > 800 - 110)
            tank1.x = 800 - 110;

        //backtracking
        if (tank1.x <= 800 - 110 && tank1.x > 800 - 130) {
           tank1.y=130;
        }
        if (tank1.x <= 800 - 130 && tank1.x >800 - 150) {
           tank1.y=120;
        }
        if (tank1.x <= 800 - 150 && tank1.x > 800 - 160) {
            tank1.y=119;
        }
        if (tank1.x <= 800 - 160 && tank1.x > 800 - 180) {
            tank1.y=122;
        }
        if (tank1.x <= 800 - 180 && tank1.x > 800 - 190) {
            tank1.y=128;
        }
        if (tank1.x <= 800 - 190 && tank1.x > 800 - 200) {
            tank1.y=135;
        }
        if (tank1.x <= 800 - 200 && tank1.x > 800 - 230) {
            tank1.y=150;
        }
        if (tank1.x <= 800 - 230 && tank1.x > 800 - 250) {
            tank1.y=158;
        }
        if (tank1.x <= 800 - 250 && tank1.x > 800 - 350) {
            tank1.y=163;
        }
        if (tank1.x <= 800 - 350 && tank1.x > 800 - 360) {
            tank1.y=158;
        }
        if (tank1.x <= 800 - 360 && tank1.x > 800 - 370) {
            tank1.y=155;
        }
        if (tank1.x <= 800 - 370 && tank1.x > 800 - 390) {
            tank1.y=152;
        }
        if (tank1.x <= 800 - 390 && tank1.x > 800 - 410) {
            tank1.y=145;
        }
        if (tank1.x <= 800 - 410 && tank1.x > 800 - 430) {
            tank1.y=139;
        }
        if (tank1.x <= 800 - 410 && tank1.x > 800 - 450) {
            tank1.y=132;
        }
        if (tank1.x <= 800 - 470 && tank1.x > 800 - 490) {
            tank1.y=125;
        }
        if (tank1.x <= 800 - 490 && tank1.x > 800 - 505) {
            tank1.y=132;
        }
        if (tank1.x <= 800 - 505 && tank1.x > 800 - 650) {
            tank1.y=128;
        }
        //Left side...tank1 going down
        if (tank1.x <= 800 - 650 && tank1.x > 800 - 670) {
            tank1.y=125;
        }
        if (tank1.x <= 800 - 670 && tank1.x > 800 - 680) {
            tank1.y=120;
        }
        if (tank1.x <= 800 - 680 && tank1.x > 800 - 690) {
            tank1.y=118;
        }
        if (tank1.x <= 800 - 690 && tank1.x > 800 - 720) {
            tank1.y=112;
        }
        if (tank1.x <= 800 - 720 && tank1.x > 800 - 750) {
            tank1.y=108;
        }
        if (tank1.x <= 800 - 750 && tank1.x > 800 - 780) {
            tank1.y=106;
        }

        // confirming tank1 are in the screen
        if (tank2.x < 0)
            tank2.x = 0;

        if (tank2.x > 800 - 64)
            tank2.x = 800 - 64;

        if (tank2.x >= 0 && tank2.x < 70) {
            if (tank2.y > 102) {
                tank2.y = 122;
            }
        }
        if (tank2.x >= 70 && tank2.x < 100) {
            tank2.y = 130;
        }
        if (tank2.x >= 100 && tank2.x < 130) {
            tank2.y = 140;
        }
        if (tank2.x >= 130 && tank2.x < 400) {
            tank2.y = 145;
        }
        if (tank2.x >= 240 && tank2.x < 290) {
            tank2.y = 160;
        }
        if (tank2.x >= 300 && tank2.x < 360) {
            tank2.y = 150;
        }
        if (tank2.x >= 360 && tank2.x < 370) {
            tank2.y = 156;
        }
        if (tank2.x >= 370 && tank2.x < 420) {
            tank2.y = 162;
        }
        if (tank2.x >= 420 && tank2.x < 440) {
            tank2.y = 168;
        }

        if (tank2.x >= 440&& tank2.x < 460) {
            tank2.y = 175;
        }
        if (tank2.x >= 460 && tank2.x < 480) {
            tank2.y = 182;
        }
        if (tank2.x >= 550 && tank2.x < 580) {
            tank2.y = 175;
        }
        if (tank2.x >= 580 && tank2.x < 620) {
            tank2.y = 170;
        }
        if (tank2.x >= 620 && tank2.x < 680) {
            tank2.y = 150;
        }
        if(Healthp1<=0 || Healthp2<=0){
            if (Healthp2<=0) {
                game.setScreen(new End(game));
                dispose();
            }
            if (Healthp1<=0) {
                game.setScreen(new Endone(game));
                dispose();
            }

        }
    }






    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
        //starting the music
        gameMusic.play();
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        //ending all the used things
        tank2Image.dispose();
        tank1Image.dispose();
        bombSound.dispose();
        hillimage.dispose();
        canimage1.dispose();
        canimage2.dispose();
        bombSound.dispose();
        gameMusic.dispose();
    }

}
