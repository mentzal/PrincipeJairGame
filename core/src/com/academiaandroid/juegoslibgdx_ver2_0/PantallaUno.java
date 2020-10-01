package com.academiaandroid.juegoslibgdx_ver2_0;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.academiaandroid.juegoslibgdx_ver2_0.entidades.Bloque;
import com.academiaandroid.juegoslibgdx_ver2_0.entidades.BotonDerecha;
import com.academiaandroid.juegoslibgdx_ver2_0.entidades.BronzeCoin;
import com.academiaandroid.juegoslibgdx_ver2_0.entidades.CajaSalida;
import com.academiaandroid.juegoslibgdx_ver2_0.entidades.GoldCoin;
import com.academiaandroid.juegoslibgdx_ver2_0.entidades.Jugador;
import com.academiaandroid.juegoslibgdx_ver2_0.entidades.Llave;
import com.academiaandroid.juegoslibgdx_ver2_0.entidades.Suelo;

import java.util.ArrayList;
import java.util.List;


public class PantallaUno extends BaseScreen  {

    public static final AssetManager manager = new AssetManager();
    private com.badlogic.gdx.scenes.scene2d.Stage stage; //parte de scene2d//
    private World world; //parte de box2d//
    private Jugador jugador;
    private Vector3 position;
    private SpriteBatch batch, batch2;
    private Bloque bloque;
    private Texture explosionTexture, texture, texture2moneda;
    private TextureRegion explosionTextureRegion, textureMoneda2;
    private static int FRAME_COLS = 8;
    private static int FRAME_ROWS = 1;
    private TextureRegion[] moneda1, moneda2;
    private TextureRegion currentFrame, currentFrame2;
    private Animation explosionAnimation, animationMoneda2;
    private float stateTime = 1f;
    private MonedaOro monedaOro;
    private MonedaPlata monedaPlata;
    private GoldCoin monedaOro2, chupete2;
    private BronzeCoin monedaBronze;
    private Texture tex;
    private boolean colision = false;
    private  boolean colisionBronze = false;
    private  boolean colisionLLave = false;
    private boolean dibujaMoneda = false;
    private  boolean dibujaMonedaBronze = false;
    private boolean dubujarLlave = false;
    private int score, scoreBronze;
    private boolean tieneLlave = false;
    private String yourScoreName, bronzeScore;
    public Music backgroundMusic;
    private Llave llave;
    private Suelo suelo;
    private Texture  textura_fondo;
    private BotonDerecha botonDerecha;
    public boolean colisionIzquierda = false ;
    private Sprite sprite_fondo;
    public Pad asMove;


    private List<Suelo> floorList = new ArrayList<Suelo>();
    private List<CajaSalida> cajaList = new ArrayList<CajaSalida>();


    BitmapFont yourBitmapFontName, MapaBitsBronze;

    Sound soundMoneda = Gdx.audio.newSound(Gdx.files.internal("audios/Pickup_Coin16.ogg")); ///--> sonido coger moneda--- se activa en la colision.
    Sound soundSalto = Gdx.audio.newSound(Gdx.files.internal("audios/Jump.ogg"));


    public PantallaUno(final Principal principal) {
        super(principal);

        /*
        ARCHIVO DE MUSICA DE FONDO
         */

        backgroundMusic = principal.getManager().get("audios/wind1.ogg");

        manager.finishLoading();

        stage = new com.badlogic.gdx.scenes.scene2d.Stage(new FitViewport(640, 360));
        position = new Vector3(stage.getCamera().position);

        world = new World(new Vector2(0, -15),true); // gravedad de la x y gravedad de la y //
        textura_fondo = new Texture(Gdx.files.internal("imagenes/bg.png"));
        int w;
        int h;
        w = Gdx.graphics.getWidth();
        h = Gdx.graphics.getHeight();
        sprite_fondo = new Sprite(textura_fondo,0,0, w,h);
        sprite_fondo.setPosition(0,0);
        batch = new SpriteBatch();



                                                /*
                                       ANIMACION MONEDA DE ORO (top)

                                                */



        explosionTexture = new Texture(Gdx.files.internal("imagenes/coin_gold.png"));
        explosionTextureRegion = new TextureRegion(explosionTexture, 0, 0, 32, 32);

        TextureRegion[][] tmp = TextureRegion.split(explosionTexture, explosionTexture.getWidth()/FRAME_COLS, explosionTexture.getHeight()/FRAME_ROWS);
        moneda1 =  new TextureRegion[FRAME_COLS*FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                moneda1[index++] = tmp[i][j];
            }
        }


        explosionAnimation = new Animation(0.1f, moneda1);
        explosionAnimation.setPlayMode(Animation.PlayMode.LOOP);


        world.setContactListener(new ContactListener() {


                                     /*
            Gestion de colisiones con los objetos dentro del mundo
                                        */



            private  boolean hanColisionado(Contact contact, Object userA, Object userB){

                return (contact.getFixtureA().getUserData().equals(userA) && contact.getFixtureB().getUserData().equals(userB)) ||
                        (contact.getFixtureA().getUserData().equals(userB) && contact.getFixtureB().getUserData().equals(userA));
            }


            @Override
            public void beginContact(Contact contact) {

                if (hanColisionado(contact, "player", "floor")) {

                    botonDerecha.parado();
                    jugador.setJumping(false);


                }

                if (hanColisionado(contact, "player", "bloque")) {

                    backgroundMusic.stop();

                    stage.addAction(
                            Actions.sequence(
                                    // Actions.delay(0.2f),
                                    Actions.run(new Runnable() {

                                        @Override
                                        public void run() {
                                            principal.setScreen(new MenuPrincipal(principal)); //--> iniciamos la pantalla que queramos cuando colisione
                                        }
                                    })
                            )
                    );
                }

                if(hanColisionado(contact, "player", "monedaOro")){


                    colision = true; // --> contador de colision a true

                    soundMoneda.play(1.0f);

                    score++;
                    yourScoreName = "X: " + score;


                }

                if(hanColisionado(contact, "player", "colisionSuelo")){

                    colisionIzquierda = true;
                    jugador.setJumping(false);


                }



                if(hanColisionado(contact,"player", "colisionSuelo2")){

                    colisionIzquierda = true;
                    jugador.setJumping(false);


                }


                if(hanColisionado(contact,"player", "llave")){

                    colisionLLave = true;
                    tieneLlave = true;

                }

                /*
                Si colisiona con el bloque final y tiene la llave pasa a otra pantalla
                 */

                if(hanColisionado( contact, "player", "bloqueFinal") && tieneLlave == true){


                    stage.addAction(
                            Actions.sequence(
                                    // Actions.delay(0.2f),
                                    Actions.run(new Runnable() {

                                        @Override
                                        public void run() {
                                            principal.setScreen(new MenuPrincipal(principal)); //--> iniciamos la pantalla que queramos cuando colisione
                                        }
                                    })
                            )
                    );


                }


            }

            /*
            cuando termina de hacer contacto ponemos las variables
            a false para que los botones se puedan volver a mover.
             */

            @Override
            public void endContact(Contact contact) {

                colisionIzquierda = false;

                if(jugador.isJumping() == true){

                    jugador.setJumping(true);

                }

            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {


            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {

            }
        });

    }


    @Override
    public void show() {


        Gdx.input.setInputProcessor(stage);


        /*
        reproduce la musica
         */

        backgroundMusic.setVolume(0.75f);
        backgroundMusic.play();

        score = 0;
        yourScoreName = "X";
        yourBitmapFontName = new BitmapFont();


        Texture botonTexture = new Texture(Gdx.files.internal("imagenes/BotonSalto2.png"));

        Texture monedaOroTexture = new Texture(Gdx.files.internal("imagenes/moneda.png"));

        Texture sueloTexture = new Texture(Gdx.files.internal("imagenes/darkgray2.png"));

        Texture sueloTextureVerde = new Texture(Gdx.files.internal("imagenes/green2.png"));

        Texture florrTexture = new Texture(Gdx.files.internal("imagenes/SueloFloor2.png"));

        Texture florrTextureDeDos = new Texture(Gdx.files.internal("imagenes/floor2.png"));

        Texture llaveTexture = new Texture(Gdx.files.internal("imagenes/luck.png"));



                                                /*
                                                SUELO -->MEDIDAS EN METROS
                                                 */

        floorList.add(new Suelo(world, florrTexture, 2.5f, 10, 0.8f));

        floorList.add(new Suelo(world, florrTextureDeDos, 1.5f, 1, 0.3f));

        floorList.add(new Suelo(world, florrTextureDeDos, 0.3f, 1, 1.5f));

        floorList.add(new Suelo(world, sueloTexture, 5f, 1, 3.4f));

        floorList.add(new Suelo(world, sueloTexture,7.5f, 1, 2f ));

        floorList.add(new Suelo(world, sueloTexture,8.5f, 1f, 2.5f ));

        floorList.add(new Suelo(world, sueloTexture,4f, 1f, 4.5f));

        floorList.add(new Suelo(world, sueloTexture,3f, 1f, 5.5f ));

        cajaList.add( new CajaSalida(world, sueloTextureVerde,5f,1f, 7f ));


        for(Suelo floor : floorList){

            stage.addActor(floor);
        }

        for (CajaSalida caja : cajaList){

            stage.addActor(caja);
        }



        /*
        FONDO DE PANTALLA
         */



       texture = new Texture(Gdx.files.internal("imagenes/background.png"));

       // stage.setDebugAll(true); //-->PARA VER LOS RECUADROS DE CADA CAJA

        jugador = new Jugador(world, new Vector2(4f,1f));

        llave = new Llave(world,llaveTexture, new Vector2(1.5f, 5.5f));

        asMove = new Pad(stage.getWidth()/50,stage.getHeight()/50);

        monedaOro = new MonedaOro(0f, moneda1);

        monedaOro2 = new GoldCoin(world, monedaOroTexture, new Vector2(6,  4 )); // posicion del chupete
        chupete2 = new GoldCoin(world, monedaOroTexture, new Vector2(5,  5 )); // posicion del chupete



        botonDerecha = new BotonDerecha(world, botonTexture, new Vector2(stage.getWidth()/50,1));
        botonDerecha.addListener(new InputListener(){



            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                    jugador.jump();
                    jugador.setMustJump(true);
                    jugador.salto();
                    soundSalto.play(1.0f);

            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {


                return  true;
            }
        });




        stage.getCamera().position.set(position);
        stage.getCamera().update();
        stage.addActor(asMove);
        stage.addActor(jugador);
        stage.addActor(monedaOro2);
        stage.addActor(chupete2);
        stage.addActor(botonDerecha);
        stage.addActor(llave);



        timer = 1 +(float) Math.random();



    }

    @Override
    public void hide() {

        stage.clear();
        jugador.detach();
        bloque.clear();
        asMove.clear();

        Gdx.input.setInputProcessor(null);

    }


    private  float timer;

    private float timerBloque;

    @Override
    public void render(float delta) {

        //Gdx.gl.glClearColor(0.4f,0.5f,0.8f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        sprite_fondo.draw(batch);
        batch.end();


                     /*
        Si el jugador se cae al vacío
                         */

        if( jugador.getY()< -30){

            stage.addAction(
                    Actions.sequence(
                            // Actions.delay(0.2f),
                            Actions.run(new Runnable() {

                                @Override
                                public void run() {
                                    principal.setScreen(new MenuPrincipal(principal)); //--> iniciamos la pantalla que queramos cuando se haya caido
                                }
                            })
                    )
            );
        }



        /*
        Si anda hacia la derecha--
         */


    if(jugador.isAndandoDerecha() == true){

            /*
            Si colisiona con al lado izquierdo de un bloque el jugador se para y
            los botones tambien
             */

        if(colisionIzquierda == true ){


            botonDerecha.parado();

        }




        else{
            
            stage.getCamera().translate(Constantes.SPEED_JUGADOR*delta*Constantes.PIXELS_IN_METER,0,0);
            botonDerecha.derecha();
            asMove.derecha();


        }
        jugador.setAndandoDerecha(false);



    }

        /*
        Si anda hacia la izquierda
         */

        if(jugador.isAndandoIzquierda() == true){

            if(colisionIzquierda == true ){


                botonDerecha.parado();

            }

            else {

                stage.getCamera().translate(-Constantes.SPEED_JUGADOR*delta*Constantes.PIXELS_IN_METER,0,0);
                botonDerecha.izquierda();
                asMove.izquierda();

            }


            jugador.setAndandoIzquierda(false);
        }



/*
Si tocamos el touch ejecutamos una accion
 */

        timer -=delta;
        timerBloque -=delta; // tiempo en el que salen los bloques

        stateTime+=delta;


        if(timerBloque <0){

            AssetManager manager = new AssetManager();
            manager.load("imagenes/ground_cave.png", Texture.class);

            manager.finishLoading();

            tex =manager.get("imagenes/ground_cave.png", Texture.class);

            bloque = new Bloque(world, tex, stage.getWidth()/50  , 2, stage.getHeight()/50 * (float)Math.random()); //propiedades del cuadro pra las colisiones // --->pasar de metros a pixeles //

            stage.addActor(bloque);


            /*
            si el bloque pasa a x negativo es decir sale de la pantalla
            el bloque se destruye para no consumir recursos
             */
            if(bloque.getX() <0){


                bloque.remove();
            }

            timerBloque = 3 +(float) Math.random();
        }


        if(timer<0){

            /*
            CADA DOS SEGUNDOS SE CREA LA MONEDA SI HEMOS COGIDO LA ANTERIOR
             */

            if(dibujaMonedaBronze == true){

                Texture monedaBronzeTexture = new Texture(Gdx.files.internal("imagenes/moneda.png"));

                monedaBronze = new BronzeCoin(world,monedaBronzeTexture, new Vector2(stage.getWidth()/50 * (float) Math.random(),
                        stage.getHeight()/50 * (float) Math.random()));



                stage.addActor(monedaBronze);

                dibujaMonedaBronze = false;

            }

            if(dibujaMoneda == true){

                Texture monedaOroTexture = new Texture(Gdx.files.internal("imagenes/moneda.png"));

                monedaOro2 = new GoldCoin(world, monedaOroTexture, new Vector2(stage.getWidth()/50 * (float) Math.random(),
                                                                                stage.getHeight()/50 * (float) Math.random())) ;



                stage.addActor(monedaOro2);

                dibujaMoneda = false;

            }

                timer = 1 +(float)Math.random();

        }

                        /*

                        si ha habido colision --> contectListeer
                        y el mundo no está bloqueado
                        y en la clase monedaoro2 el destroy está a true
                        se elimina la moneda
                         */


        if( monedaOro2.destroy == true && !world.isLocked() && colision == true){


            monedaOro2.destroy = false;
            colision = false;
            monedaOro2.getBody().setActive(false);
            monedaOro2.remove();

         //   dibujaMoneda = true;

        }
        if( chupete2.destroy == true && !world.isLocked() && colision == true){


            chupete2.destroy = false;
            colision = false;
            chupete2.getBody().setActive(false);
            chupete2.remove();

            //   dibujaMoneda = true;

        }


        if(llave.destroy == true && !world.isLocked() && colisionLLave == true){


            llave.destroy = false;
            colisionLLave = false;
            llave.getBody().setActive(false);
            llave.remove();

            dubujarLlave = true;

        }



        stage.act(); //pintamos los actores //
        world.step(delta, 8, 6);



        currentFrame = explosionAnimation.getKeyFrame(stateTime);



        batch.begin();


      //  batch.draw(texture, 0, 0); //--> fondo de pantalla


     //   batch.draw(currentFrame, 1015, 670); //moneda del marcador

        /*
        pintamos el marcador
         */

     //   yourBitmapFontName.setColor(1.0f, 1.0f, 1.0f, 1.0f);
     //   yourBitmapFontName.draw(batch, yourScoreName, stage.getWidth()/2, stage.getHeight() / 2);


        batch.end();


/*
         !!!!!!!!!       PARA VER LAS COORDENADAS              !!!!

        if(asMove.isTouched()){

            System.out.println(asMove.getKnobPercentX());
            System.out.println(asMove.getKnobPercentY());
        }
        */
                                            /*
                                            RIGHT
                                             */
        if(asMove.isTouched() && asMove.getKnobPercentX()>0
            ){

                jugador.derecha();

        }

                                            /*
                                        LEFT
                                         */

        if(asMove.isTouched() && asMove.getKnobPercentX()<0
           ){

                    jugador.izquierda();
        }

    /*
    Corrige el error de una coordenada que no esta establecida y que hacia
    que el boton se moviense sin tener que hacerlo
     */

        else{
            botonDerecha.parado();
        }

                                        /*
                                        STOP
                                         */


          if(!asMove.isTouched()){

             jugador.parado();
              botonDerecha.parado();

            }

        stage.draw();

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        batch2.dispose();
        stage.dispose();
        world.dispose();
        batch.dispose();
        manager.dispose();
        textura_fondo.dispose();

    }
}
