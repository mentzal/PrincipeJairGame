package com.academiaandroid.juegoslibgdx_ver2_0.entidades;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.academiaandroid.juegoslibgdx_ver2_0.PantallaUno;

import static com.academiaandroid.juegoslibgdx_ver2_0.Constantes.PIXELS_IN_METER;

/**
 * Created by Pelu on 19/03/2017.
 */

public class CajaSalida extends Actor {


    private Texture floor;

    private World world;

    private Body body, leftBody;

    private Fixture fixture, leftFixture;

    private PantallaUno pantallaUno;



    public CajaSalida(World world, Texture floor, float x,float width, float y ){

        this.world = world;
        this.floor = floor;

        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.KinematicBody;
        def.position.set(x, y);
        body = world.createBody(def);

        PolygonShape formaNube = new PolygonShape();
        formaNube.setAsBox(0.1f, 0.1f);
        fixture = body.createFixture(formaNube, 0);
        fixture.setUserData("bloqueFinal");
        formaNube.dispose();


        setSize(PIXELS_IN_METER, PIXELS_IN_METER );

    }


    public void detach(){

        body.destroyFixture(fixture);
        world.destroyBody(body);
    }

    /*
    dibuja el suelo
     */
    public void draw(Batch batch, float parentAlpha) {

        setPosition((body.getPosition().x -0.5f) * PIXELS_IN_METER,
                (body.getPosition().y) * PIXELS_IN_METER );

        // Render both textures.
        batch.draw(floor, getX(), getY(), getWidth(), getHeight()); // --> Posicion de la textura del suelo ---!!!MEJORAR !!--

    }





}




