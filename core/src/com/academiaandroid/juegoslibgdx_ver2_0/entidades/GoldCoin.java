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

public class GoldCoin extends Actor  {



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



    public GoldCoin(World world, Texture texture , Vector2 position ){


        this.world = world;
        this.texture = texture;



        BodyDef def = new BodyDef();
        def.position.set(position);
        def.type = BodyDef.BodyType.KinematicBody;
        body = world.createBody(def);

        PolygonShape monedaOro = new PolygonShape();
        monedaOro.setAsBox(0.5f,0.5f);
        fixture = body.createFixture(monedaOro, 0);
        fixture.setUserData("monedaOro");
        monedaOro.dispose();

        setSize(PIXELS_IN_METER, PIXELS_IN_METER);


        Texture spriteOro = new Texture(Gdx.files.internal("imagenes/coin_gold.png"));



        tmp = TextureRegion.split(spriteOro, spriteOro.getWidth() / //--> Split de la textura de moneda de oro
                8, spriteOro.getHeight() );



        monedaOroRegion = new TextureRegion[8];
        monedaOroRegion[0] = tmp[0][0];
        monedaOroRegion[1] = tmp[0][1];
        monedaOroRegion[2] = tmp[0][2];
        monedaOroRegion[3] = tmp[0][3];
        monedaOroRegion[4] = tmp[0][4];
        monedaOroRegion[5] = tmp[0][5];
        monedaOroRegion[6] = tmp[0][6];
        monedaOroRegion[7] = tmp[0][7];


        monedaDeOro = new Animation(0.4f, monedaOroRegion);

        stateTime = 0f;

    }





    @Override
    public void draw(Batch batch, float parentAlpha) {

        setPosition((body.getPosition().x -0.5f)*PIXELS_IN_METER,
                (body.getPosition().y  )*PIXELS_IN_METER);



        stateTime += Gdx.graphics.getDeltaTime();



            batch.draw(monedaDeOro.getKeyFrame(stateTime, true), getX(), getY(), getWidth()/1.5f,getHeight()/1.5f);


    }


    public Body getBody() {
        return body;
    }


}