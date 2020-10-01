package com.academiaandroid.juegoslibgdx_ver2_0.entidades;


import com.badlogic.gdx.Gdx;
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

public class BotonDerecha extends Actor {


    private World world;

    private Body body;

    private Fixture fixture;


    private Texture texture;

    private float stateTime;

    public boolean derecha = true;


    public BotonDerecha (World world, Texture texture,  Vector2 position){

        this.world = world;
        this.texture = texture;



        BodyDef def = new BodyDef();
        def.position.set(position);
        def.type = BodyDef.BodyType.KinematicBody;
        body = world.createBody(def);

        PolygonShape player = new PolygonShape();
        player.setAsBox(0.5f,0.5f);
        fixture = body.createFixture(player, 1);
        fixture.setUserData("BotonDerecha");

        player.dispose();

        setSize(PIXELS_IN_METER, PIXELS_IN_METER);



        stateTime = 0f;



    }

    public Body getBody() {
        return body;
    }

    public boolean isDerecha() {
        return derecha;
    }

    public void setDerecha(boolean derecha) {
        this.derecha = derecha;
    }

    public void derecha() {


        body.setLinearVelocity(1f, 0);

        this.derecha = false;
    }

    public void  izquierda(){

        body.setLinearVelocity(-1f, 0);
    }



    public void parado(){

        body.setLinearVelocity(0,0);
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {

        setPosition((body.getPosition().x -0.5f) * PIXELS_IN_METER,
                (body.getPosition().y ) * PIXELS_IN_METER );

        stateTime += Gdx.graphics.getDeltaTime();


        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }
}
