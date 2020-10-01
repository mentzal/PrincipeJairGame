
package com.academiaandroid.juegoslibgdx_ver2_0;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class MonedaOro implements ApplicationListener {

    private static final int        FRAME_COLS = 8;
    private static final int        FRAME_ROWS = 1;


     Animation walkAnimator;
     Texture walkSheet;
     TextureRegion[] walkFrames;
     SpriteBatch spriteBatch;
     TextureRegion  currentFrame;


    float stateTime;

    public MonedaOro(float stateTime, TextureRegion[] walkFrames) {

        this.stateTime = stateTime;
        this.walkFrames = walkFrames;

    }


    @Override
    public void create() {



        walkSheet = new Texture(Gdx.files.internal("imagenes/coin_gold.png")); // #9
        TextureRegion[][] tmp = TextureRegion.split(walkSheet, walkSheet.getWidth()/FRAME_COLS, walkSheet.getHeight()/FRAME_ROWS);              // #10
        walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                walkFrames[index++] = tmp[i][j];
            }
        }
        walkAnimator = new Animation(0.025f, walkFrames);
        spriteBatch = new SpriteBatch();
        stateTime = 0f;


    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        stateTime += Gdx.graphics.getDeltaTime();           // #15
        currentFrame = walkAnimator.getKeyFrame(stateTime, true);  // #16
        spriteBatch.begin();
        spriteBatch.draw(currentFrame, 32, 32);             // #17
        spriteBatch.end();

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

        spriteBatch.dispose();
        walkSheet.dispose();
        

    }

}
