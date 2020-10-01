package com.academiaandroid.juegoslibgdx_ver2_0;


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

public class BotonSalto extends Actor {

    private Texture texture;

    private World world;

    private Body body;

    private Fixture fixture;


    private float stateTime;





public BotonSalto (World world, Texture texture, Vector2 position){



    this.world = world;
    this.texture = texture;

    BodyDef def = new BodyDef();
    def.position.set(position);
    def.type = BodyDef.BodyType.DynamicBody;
    body = world.createBody(def);

    PolygonShape salto = new PolygonShape();
    salto.setAsBox(0.5f,0.5f);
    fixture = body.createFixture(salto, 1);
    fixture.setUserData("botonSalto");
    salto.dispose();

    setSize(PIXELS_IN_METER, PIXELS_IN_METER);

    stateTime = 0f;

}
    public void derecha(){


        body.setLinearVelocity(2, 0);


    }



    @Override
    public void act(float delta) {

        body.applyForceToCenter(0, -Constantes.IMPULSE_JUMP * 1.80f,true);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        setPosition((body.getPosition().x -0.5f)*PIXELS_IN_METER,
                (body.getPosition().y  )*PIXELS_IN_METER);


        stateTime += Gdx.graphics.getDeltaTime();


        batch.draw(texture, getX(), getY(), getWidth(), getHeight());




    }


    public Body getBody() {
        return body;
    }

    public void detach(){

        body.destroyFixture(fixture);
        world.destroyBody(body);
    }



}
