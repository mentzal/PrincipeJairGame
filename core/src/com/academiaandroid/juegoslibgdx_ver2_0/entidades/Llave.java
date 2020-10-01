package com.academiaandroid.juegoslibgdx_ver2_0.entidades;

import com.badlogic.gdx.Gdx;
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

import static com.academiaandroid.juegoslibgdx_ver2_0.Constantes.PIXELS_IN_METER;

/**
 * Created by Pelu on 24/10/2016.
 */

public class Llave extends Actor {

    public boolean destroy = true;

    private World world;

    private Body body;

    private Fixture fixture;

    private Texture texture;


    private TextureRegion[][] tmp;

    private TextureRegion[] monedaOroRegion;

    private Animation monedaDeOro;

    private float stateTime;

    public boolean contador = false;



    public Llave(World world, Texture texture , Vector2 position ){



        this.world = world;
        this.texture = texture;



        BodyDef def = new BodyDef();
        def.position.set(position);
        def.type = BodyDef.BodyType.KinematicBody;
        body = world.createBody(def);

        PolygonShape monedaOro = new PolygonShape();
        monedaOro.setAsBox(0.1f,0.1f);
        fixture = body.createFixture(monedaOro, 0);
        fixture.setUserData("llave");
        monedaOro.dispose();

        setSize(PIXELS_IN_METER, PIXELS_IN_METER);


    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        setPosition((body.getPosition().x -0.5f) * PIXELS_IN_METER,
                (body.getPosition().y ) * PIXELS_IN_METER ); // obligatorio para la correcta posicion sino fallan las colisiones //


        stateTime += Gdx.graphics.getDeltaTime();

        batch.draw(texture, getX(), getY(), getWidth(), getHeight());

    }




    public void detach(){

        body.destroyFixture(fixture);
        world.destroyBody(body);
    }



    public Body getBody() {
        return body;
    }
}
