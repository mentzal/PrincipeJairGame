package com.academiaandroid.juegoslibgdx_ver2_0.entidades;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

import static com.academiaandroid.juegoslibgdx_ver2_0.Constantes.PIXELS_IN_METER;

public class Nube extends Actor {



    private World world;

    private Body body;

    private Fixture fixture;

    private Texture texture;

    private boolean vivoNube = true;



public Nube(World world, Texture texture, Vector2 position){

    this.world = world;
    this.texture = texture;

    /*
    Coloco la nube (suelo) en la posicion que corrsponde
     */

    BodyDef def = new BodyDef();
    def.position.set(position);
    def.type = BodyDef.BodyType.StaticBody; // no es afectado por las colisiones pero podemos moverlo //
    body = world.createBody(def);


    //le damos forma a la caja //

    PolygonShape formaNube = new PolygonShape();
    formaNube.setAsBox(0.5f,0.5f);
    fixture = body.createFixture(formaNube, 1);
    fixture.setUserData("nube");
    formaNube.dispose();

    setSize(PIXELS_IN_METER, PIXELS_IN_METER); //tamaño de la figura //



}

    @Override
    public void draw(Batch batch, float parentAlpha) {
        /*
        video 32 minuto: 3:58
         */
        setPosition((body.getPosition().x -0.5f) * PIXELS_IN_METER,
                (body.getPosition().y  ) * PIXELS_IN_METER ); // estas posiciones no se pueden cambiar // --> se descuadra la caja del contenido y las colisiones fallan
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }


    @Override
    public void act(float delta) {
/*
        if(vivoNube){


           float speedY = body.getLinearVelocity().y;
                          body.setLinearVelocity(SPEED_NUBE, speedY);
        }
   */

    }

    public Body getBody() {
        return body;
    }

    public void detach(){

        body.destroyFixture(fixture);
        world.destroyBody(body);
    }

    public boolean isAliveNube() {
        return vivoNube;
    }

    public void setAlive(boolean vivoNube) {
        this.vivoNube = vivoNube;
    }





}
