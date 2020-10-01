package com.academiaandroid.juegoslibgdx_ver2_0.entidades;


import com.academiaandroid.juegoslibgdx_ver2_0.PantallaUno;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.academiaandroid.juegoslibgdx_ver2_0.Constantes;
import com.academiaandroid.juegoslibgdx_ver2_0.PantallaUno;

import static com.academiaandroid.juegoslibgdx_ver2_0.Constantes.PIXELS_IN_METER;


public class Jugador extends Actor  {

    private World world;

    private Body body;

    private Fixture fixture;

    private boolean vivo = true;

    private boolean salto = false;

    private boolean debeSaltar = false;

    private boolean andandoDerecha = false;

    private boolean andandoIzquierda = false;

    private TextureRegion currentFrame;

    private TextureRegion[] regionDerecha,regionIzquierda, regionParado, regionSalto ;
    private Animation animacionDerecha, animacionIzquierda, animacionParado, animacionSalto ;
    private float stateTime;
    private TextureRegion[][] tmpParado,tmpDerecha, tmpIzquierda , tmpSalto;

    private Vector2 velocity;


    private boolean mustJump = false;

    private PantallaUno pantallaUno;


    public Jugador(World world,  Vector2 position){


        this.world = world;


        BodyDef def = new BodyDef();
        def.position.set(position);
        def.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(def);

        PolygonShape player = new PolygonShape();
        player.setAsBox(0.5f,0.5f);
        fixture = body.createFixture(player, 1);
        fixture.setUserData("player");

        player.dispose();

        setSize(PIXELS_IN_METER, PIXELS_IN_METER);



                                            /*
                                            TEXTURA SALTO
                                             */

        Texture salto = new Texture(Gdx.files.internal("imagenes/salto2.png"));

        tmpSalto = TextureRegion.split(salto, salto.getWidth()/2, salto.getHeight());



        regionSalto = new TextureRegion[2];
        regionSalto[0] = tmpSalto[0][0];
        regionSalto[1] = tmpSalto[0][1];




        animacionSalto= new Animation(0.4f, regionSalto);


                                        /*
                                        TEXTURA DERECHA
                                         */

        Texture derecha = new Texture(Gdx.files.internal("imagenes/Derecha2.png"));

        tmpDerecha = TextureRegion.split(derecha, derecha.getWidth()/3, derecha.getHeight());



        regionDerecha = new TextureRegion[3];
        regionDerecha[0] = tmpDerecha[0][0];
        regionDerecha[1] = tmpDerecha[0][1];
        regionDerecha[2] = tmpDerecha[0][2];




        animacionDerecha= new Animation(0.4f, regionDerecha);


                                        /*
                                        TEXTURA IZQUIERDA
                                         */


        Texture izquierda = new Texture(Gdx.files.internal("imagenes/Izquierda2.png"));

        tmpIzquierda = TextureRegion.split(izquierda, izquierda.getWidth()/3, izquierda.getHeight());



        regionIzquierda = new TextureRegion[3];
        regionIzquierda[0] = tmpIzquierda[0][0];
        regionIzquierda[1] = tmpIzquierda[0][1];
        regionIzquierda[2] = tmpIzquierda[0][2];




        animacionIzquierda= new Animation(0.4f, regionIzquierda);




                                    /*
                                    TEXTURA PARADO
                                     */

        Texture Parado = new Texture(Gdx.files.internal("imagenes/MagoStatico2.png"));

            tmpParado = TextureRegion.split(Parado, Parado.getWidth() / //--> Split de la textura de moneda de oro
                    2, Parado.getHeight() );



        regionParado = new TextureRegion[2];
        regionParado[0] = tmpParado[0][0];
        regionParado[1] = tmpParado[0][1];





        animacionParado= new Animation(0.4f, regionParado);




        velocity = new Vector2(0,0);
        currentFrame = regionParado[0];

        stateTime = 0f;

        }



    public void jump(){

        if(!salto && vivo){

            Vector2 position = body.getPosition();

           // body.applyLinearImpulse(0, Constantes.IMPULSE_JUMP*10,position.x, position.y, true);
            body.setLinearVelocity(new Vector2(0, 150));

            salto = true;
        }


/*

            if(isAndandoDerecha() == true || isAndandoIzquierda() == true){



                body.setLinearVelocity(new Vector2(0, 12));
        }

            else {
                body.setLinearVelocity(new Vector2(0, 12));
            }



//          body.applyAngularImpulse(12, true);



        }

        */
    }



    public void parado(){



        currentFrame = animacionParado.getKeyFrame(stateTime,true);
        body.setLinearVelocity(0,0);



    }


    public void derecha(){


        body.setLinearVelocity(3,0);

        /*


        if(salto ){

            body.setLinearVelocity(2, -2);
        }

        else{

            body.setLinearVelocity(2, 0);
        }




*/

        velocity.x = 1;


        this.andandoDerecha = true;


        if(velocity.x> 0 & velocity.y == 0) {

            currentFrame = animacionDerecha.getKeyFrame(stateTime,true);
        }

    }


    public void izquierda(){
        body.setLinearVelocity(-3, 0);


/*
        if(salto){

            body.setLinearVelocity(-2,-2);
        }

        else{
            body.setLinearVelocity(-2, 0);
        }

*/
        velocity.x = -1;

        this.andandoIzquierda = true;
        if(velocity.x<0 && velocity.y == 0)   {

            currentFrame = animacionIzquierda.getKeyFrame(stateTime,true);
        }


    }

    public void salto(){



            currentFrame = animacionSalto.getKeyFrame(stateTime,true);




    }


    public boolean isAndandoIzquierda() {
        return andandoIzquierda;
    }

    public void setAndandoIzquierda(boolean andandoIzquierda) {
        this.andandoIzquierda = andandoIzquierda;
    }

    public boolean isAndandoDerecha() {
        return andandoDerecha;
    }

    public void setAndandoDerecha(boolean andando) {
        this.andandoDerecha = andando;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {


        if(Gdx.input.isKeyPressed(Input.Keys.W)){

            derecha();
        }

        if(Gdx.input.isKeyPressed(Input.Keys.Q)){

            izquierda();
        }


        setPosition((body.getPosition().x -0.5f) * PIXELS_IN_METER,
                     (body.getPosition().y ) * PIXELS_IN_METER ); // obligatorio para la correcta posicion sino fallan las colisiones //

        velocity.x = 0;
        velocity.y = 0;



 //Movemos nuestro Sprite
        setX(getX() + velocity.x);
        setY(getY() + velocity.y);


        stateTime += Gdx.graphics.getDeltaTime();


       batch.draw(currentFrame, getX(), getY(), getWidth(), getHeight());



    }



    @Override
    public void act(float delta) {



        body.applyForceToCenter(0, -Constantes.IMPULSE_JUMP * 10.80f,true);


        if( mustJump){

            mustJump = false;

            jump();

        }


            /*
            aplicamos una fuerza hacia abajo para que no salte tanto
             */

        if(isJumping() ){





          //body.applyForceToCenter(0, -Constantes.IMPULSE_JUMP * 3.80f,true);
        }


    }


    public boolean isMustJump() {
        return mustJump;
    }



    public void setMustJump(boolean mustJump) {
        this.mustJump = mustJump;
    }



    public boolean isJumping() {
        return salto;
    }

    public void setJumping(boolean jumping) {
        this.salto = jumping;
    }



    public void detach(){

        body.destroyFixture(fixture);
        world.destroyBody(body);
    }

    public boolean isAlive() {
        return vivo;
    }


}
