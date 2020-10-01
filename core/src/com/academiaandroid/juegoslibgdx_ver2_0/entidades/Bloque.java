package com.academiaandroid.juegoslibgdx_ver2_0.entidades;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.academiaandroid.juegoslibgdx_ver2_0.Constantes;

import static com.academiaandroid.juegoslibgdx_ver2_0.Constantes.PIXELS_IN_METER;


public class Bloque extends Actor {

    private Texture texture;

    private World world;

    private Body body;

    private boolean mueve = true;

    private Fixture fixture;



    public  Bloque(World world, Texture texture,  float x,float width, float y ){

        this.world = world;
        this.texture = texture;

        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.KinematicBody;
        def.position.set(x, y);
        body = world.createBody(def);

        PolygonShape formaNube = new PolygonShape();
        formaNube.setAsBox(0.5f, 0.5f);
        fixture = body.createFixture(formaNube, 0);
        fixture.setUserData("bloque");
        formaNube.dispose();


        setSize(PIXELS_IN_METER, PIXELS_IN_METER );


    }


    @Override

    public void draw(Batch batch, float parentAlpha) {


        setPosition((body.getPosition().x -0.5f) * PIXELS_IN_METER,
                (body.getPosition().y) * PIXELS_IN_METER );

        batch.draw(texture, getX(), getY(), getWidth(), getHeight());

    }


    @Override
    public void act(float delta) {

        if(mueve) {

            float sppedY = body.getLinearVelocity().y;
            body.setLinearVelocity(-Constantes.SPEED_NUBE, sppedY);
        }


    }

    public Body getBody() {
        return body;
    }

    @Override
    public void clear() {

        body.destroyFixture(fixture);
        world.destroyBody(body);
    }




    public boolean isAliveNube() {
        return mueve;
    }

    public void setAlive(boolean vivo) {
        this.mueve = vivo;
    }





}