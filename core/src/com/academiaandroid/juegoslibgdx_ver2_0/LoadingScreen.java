package com.academiaandroid.juegoslibgdx_ver2_0;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class LoadingScreen extends BaseScreen {

    private Stage stage;
    private Skin skin;
    private Label loading;
    private Texture texture, texture2, texture3, texture4, text;
    private TextureRegion textRegion,carga1region,carga2region,carga3region;
    private TextureRegionDrawable drawtext, drawcarga1,drawcarga2,drawcarga3;
    private SpriteBatch batch;
    private ImageButton tex,carga1,carga2,carga3;

    public LoadingScreen(Principal principal) {
        super(principal);


        stage = new Stage(new FitViewport(640, 360));
        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

        text = new Texture(Gdx.files.internal("imagenes/text.png"));
        texture2 = new Texture(Gdx.files.internal("imagenes/carga_1.png"));
        texture3 = new Texture(Gdx.files.internal("imagenes/carga_2.png"));
        texture4 = new Texture(Gdx.files.internal("imagenes/carga_3.png"));

        /*
        FONDO DE PANTALLA
         */
        textRegion = new TextureRegion(text);
        drawtext = new TextureRegionDrawable(textRegion);
        tex = new ImageButton(drawtext); //Set the button up

        tex.setSize(150,150);
        tex.setPosition(250,100);

        /*
        BARRA CARGA 1
         */
        carga1region = new TextureRegion(texture2);
        drawcarga1 = new TextureRegionDrawable(carga1region);
        carga1 = new ImageButton(drawcarga1); //Set the button up

        carga1.setSize(250,50);
        carga1.setPosition(200,50);

        /*
        BARRA CARGA 2
         */
        carga2region = new TextureRegion(texture3);
        drawcarga2 = new TextureRegionDrawable(carga2region);
        carga2 = new ImageButton(drawcarga2); //Set the button up

        carga2.setSize(250,50);
        carga2.setPosition(200,50);

        /*
        BARRA CARGA 3
         */

        carga3region = new TextureRegion(texture4);
        drawcarga3 = new TextureRegionDrawable(carga3region);
        carga3 = new ImageButton(drawcarga3); //Set the button up

        carga3.setSize(250,50);
        carga3.setPosition(200,50);


       // loading = new Label("Loading...", skin);
      //  loading.setPosition(320, 180);


        stage.addActor(tex);


        batch = new SpriteBatch();
    }


    @Override
    public void show(){

        texture = new Texture(Gdx.files.internal("imagenes/bg_carga.png"));
        texture2 = new Texture(Gdx.files.internal("imagenes/carga_1.png"));
        texture3 = new Texture(Gdx.files.internal("imagenes/carga_2.png"));
        texture4 = new Texture(Gdx.files.internal("imagenes/carga_3.png"));




    }

    @Override
    public void hide() {

        stage.clear();
        stage.dispose();
    }





    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);




            if (principal.getManager().update()) {
                // I'll notify the game that all the assets are loaded so that it can load the
                // remaining set of screens and enter the main menu. This avoids Exceptions because
                // screens cannot be loaded until all the assets are loaded.
                principal.finishLoading();
            } else {
                // getProgress() returns the progress of the load in a range of [0,1]. We multiply
                // this progress per * 100 so that we can display it as a percentage.
                int progress = (int) (principal.getManager().getProgress() * 100);
               // loading.setText("Loading... " + progress + "%");
            }


            batch.begin();

            batch.draw(texture, 0, 0,texture.getWidth(),texture.getHeight()); //--> fondo de pantalla

            batch.end();

            if(principal.getManager().getProgress() * 100  >0 && principal.getManager().getProgress() * 100 <50){
                batch.begin();
              //  batch.draw(texture2, 950  , 400 - texture2.getHeight());
                stage.addActor(carga1);
                batch.end();
            }

            if(principal.getManager().getProgress() * 100  == 50){
                batch.begin();
              //  batch.draw(texture3, 950 , 400 - texture3.getHeight());
                stage.addActor(carga2);
                batch.end();

            }

            if(principal.getManager().getProgress() * 100  > 50){
                batch.begin();
              //  batch.draw(texture4, 950 , 400 - texture4.getHeight());
                stage.addActor(carga3);
                batch.end();
            }

            stage.act();
            stage.draw();
        }





    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}
