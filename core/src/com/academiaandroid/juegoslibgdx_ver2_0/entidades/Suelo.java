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

public class Suelo extends Actor {

    private Texture floor;

    private World world;

    private Body body, leftBody;

    private Fixture fixture, leftFixture;

    private PantallaUno pantallaUno;


    /*
float x--> posicion del vorde izquierdo
float whith -->el ancho del suelo
float y --> vorde superior

 */
    public Suelo(World world, Texture floor, float x,float width, float y ){


        this.world = world;
        this.floor = floor;



         /*
    Coloco el suelo en la posicion que corrsponde
     */

       /*
        definicion de la Leftfixture(trazo izquierdo del escalon)
         */

        BodyDef leftDef = new BodyDef();
        leftDef.position.set(x + width / 2, y -1f);
        leftBody = world.createBody(leftDef);

        /*
        creamos la fixture
         */
        PolygonShape leftBox = new PolygonShape();
        leftBox.setAsBox(width /2, 0.5f);
        leftFixture = leftBody.createFixture(leftBox, 1);
        leftFixture.setUserData("floor"); //para que se comporte como un pincho //
        leftBox.dispose();



        //PARTE IZQUIERDA DEL SUELO //


        BodyDef def = new BodyDef();
        def.position.set(x, y -1f);
        body = world.createBody(def);



        PolygonShape box = new PolygonShape();
        box.setAsBox(0.02f, 0.45f);
        fixture = body.createFixture(box, 1);
        fixture.setUserData("colisionSuelo");
        box.dispose();

        //PARTE DERECHA DEL SEULO //


        BodyDef def2 = new BodyDef();
        def2.position.set(x+width, y -1f);
        body = world.createBody(def2);



        PolygonShape box2 = new PolygonShape();
        box2.setAsBox(0.02f, 0.45f);
        fixture = body.createFixture(box2, 1);
        fixture.setUserData("colisionSuelo2");
        box2.dispose();






        setSize(width* PIXELS_IN_METER , PIXELS_IN_METER);
        setPosition(x *PIXELS_IN_METER, (y-1) * PIXELS_IN_METER);
    }



    /*
destruye el suelo
 */
    public void detach(){

        body.destroyFixture(fixture);
        world.destroyBody(body);
    }

    /*
    dibuja el suelo
     */
    public void draw(Batch batch, float parentAlpha) {
        // Render both textures.
        batch.draw(floor, getX(), getY(), getWidth(), getHeight()); // --> Posicion de la textura del suelo ---!!!MEJORAR !!--

    }



}
