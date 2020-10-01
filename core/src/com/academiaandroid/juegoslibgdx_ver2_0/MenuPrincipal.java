package com.academiaandroid.juegoslibgdx_ver2_0;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class MenuPrincipal extends BaseScreen  {

    private SpriteBatch batch;

    /** The stage where all the buttons are added. */
    private Stage stage;

    /** The skin that we use to set the style of the buttons. */
    private Skin skin;

    /** The logo image you see on top of the screen. */
    private Image logo,fondo,bgmarron;


    private Texture Fondotexture, botonplay,botonconfig,leader,prize,about,ok;


    private TextureRegion myTextureRegion, mytextureregionconfig,
                            regionleader,regionprize,regonabout,regionok;

    private TextureRegionDrawable myTexRegionDrawable, mytexturedrawableconfig,
                                    drawleader,drawprize,drawbout,drawok;


    private ImageButton buttonplay, config, lead,priz,abou,oki;



    /** The play button you use to jump to the game screen. */
    private TextButton play, salir, opciones;





    public MenuPrincipal(final Principal principal) {
        super(principal);
        /*
        Texturas de los botones
         */

        botonplay = new Texture(Gdx.files.internal("imagenes/play.png"));
        botonconfig = new Texture(Gdx.files.internal("imagenes/settings.png"));
        leader = new Texture(Gdx.files.internal("imagenes/leader.png"));
        prize = new Texture(Gdx.files.internal("imagenes/prize.png"));
        about = new Texture(Gdx.files.internal("imagenes/about.png"));
        ok = new Texture(Gdx.files.internal("imagenes/ok.png"));


        regionok = new TextureRegion(ok);
        drawok = new TextureRegionDrawable(regionok);
        oki = new ImageButton(drawok); //Set the button up


        regonabout = new TextureRegion(about);
        drawbout = new TextureRegionDrawable(regonabout);
        abou = new ImageButton(drawbout); //Set the button up


        regionprize = new TextureRegion(prize);
        drawprize = new TextureRegionDrawable(regionprize);
        priz = new ImageButton(drawprize); //Set the button up

        myTextureRegion = new TextureRegion(botonplay);
        myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        buttonplay = new ImageButton(myTexRegionDrawable); //Set the button up


        mytextureregionconfig = new TextureRegion(botonconfig);
        mytexturedrawableconfig = new TextureRegionDrawable(mytextureregionconfig);
        config = new ImageButton(mytexturedrawableconfig);

        regionleader = new TextureRegion(leader);
        drawleader = new TextureRegionDrawable(regionleader);
        lead = new ImageButton(drawleader);



        stage = new com.badlogic.gdx.scenes.scene2d.Stage(new FitViewport(640, 360));


        skin = new Skin(Gdx.files.internal("skin/SkinAzul.json"));

        play = new TextButton("Play", skin);
        salir = new TextButton("Exit", skin);
        opciones = new TextButton("User Panel", skin);
        // Load the skin file. The skin file contains information about the skins. It can be
        // passed to any widget in Scene2D UI to set the style. It just works, amazing.
        // skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        // Also, create an image. Images are actors that only display some texture. Useful if you
        // want to display a texture in a Scene2D based screen but you don't want to rewrite code.
        logo = new Image(principal.getManager().get("imagenes/JAIR.png", Texture.class));
        bgmarron = new Image(principal.getManager().get("imagenes/bg_marron.png", Texture.class));

        // For instance, here you see that I create a new button by telling the label of the
        // button as well as the skin file. The background image for the button is in the skin
        // file.


        // Also, create an image. Images are actors that only display some texture. Useful if you
        // want to display a texture in a Scene2D based screen but you don't want to rewrite code.


        // Add capture listeners. Capture listeners have one method, changed, that is executed
        // when the button is pressed or when the user interacts somehow with the widget. They are
        // cool because they let you execute some code when you press them.

        bgmarron.setVisible(false);
        oki.setVisible(false);
                                /*
                    Acciones de los botones
                                 */

        buttonplay.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // Take me to the game screen!
                principal.setScreen(new PantallaUno(principal));
            }
        });


        salir.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

            Gdx.app.exit();
            }
        });


        config.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // Take me to the game screen!
                principal.setScreen(new PantallaUno(principal));
            }
        });


        abou.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // Take me to the game screen!
               bgmarron.setVisible(true);
               oki.setVisible(true);
            }
        });

        oki.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // Take me to the game screen!
                bgmarron.setVisible(false);
                oki.setVisible(false);
            }
        });



        // Now I position things on screen. Sorry for making this the hardest part of this screen.
        // I position things on the screen so that they look centered. This is why I make the
        // buttons the same size.
       //logo.setPosition(stage.getWidth() - logo.getWidth() / 2, stage.getHeight() - logo.getHeight());
        logo.setPosition(315 - logo.getWidth() / 2, 510 - logo.getHeight());
        bgmarron.setPosition(340 - logo.getWidth() / 2, 535 - logo.getHeight());
        oki.setPosition(285,20);
        buttonplay.setPosition(270, 20);
        config.setPosition(550,290);
        lead.setPosition(25,290);
        priz.setPosition(25,25);
        abou.setPosition(550,25);

        buttonplay.setSize(100,100);
        config.setSize(60,60);
        play.setSize(200, 80);
        salir.setSize(200, 80);
        salir.setSize(200, 80);
        opciones.setSize(200,80);
        lead.setSize(60,60);
        priz.setSize(60,60);
        abou.setSize(60,60);
        bgmarron.setSize(350,350);
        oki.setSize(60,60);

        play.setPosition(200, 190);
        opciones.setPosition(200, 100);
        salir.setPosition(200, 10);



        // Do not forget to add actors to the stage or we wouldn't see anything.
       // stage.addActor(play);
        stage.addActor(logo);
        stage.addActor(buttonplay);
        stage.addActor(config);
        stage.addActor(lead);
        stage.addActor(priz);
        stage.addActor(abou);
        stage.addActor(bgmarron);
        stage.addActor(oki);

      //  stage.addActor(salir);
      //  stage.addActor(opciones);

        batch = new SpriteBatch();

    }

    @Override
    public void show() {

        Gdx.input.setInputProcessor(stage);
        Fondotexture = new Texture(Gdx.files.internal("imagenes/bg2.png"));
       // texture = new Texture(Gdx.files.internal("imagenes/loadingEmpty.png"));



    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
      stage.dispose();
      skin.dispose();

    }

    @Override
    public void render(float delta) {
        //batch.begin();

        //batch.draw(texture, 0, 0); //--> fondo de pantalla
       // Gdx.gl.glClearColor(0.6f, 0.7f, 0.7f, 0.002f); //dividir entre 255 el valor del color rgba // -->buscar editor rgba
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        batch.draw(Fondotexture, 0, 0,Fondotexture.getWidth(),Fondotexture.getHeight()); //--> fondo de pantalla
        batch.end();
        stage.act();
        stage.draw();



    }
}
