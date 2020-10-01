package com.academiaandroid.juegoslibgdx_ver2_0;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;


public class Pad extends Touchpad{


    private static Touchpad.TouchpadStyle touchpadStyle;
    private static Skin touchpadSkin;
    private static Drawable touchBackground;
    private static Drawable touchKnob;



    public Pad(  float x, float y) {



        super(10, getTouchpadStyle());



        setBounds(15, 15, 80, 80);

        setPosition(x,y);



    }


    public void derecha(){

        setPosition(getX()+0.8f, getY());

    }


    public void  izquierda(){

        setPosition(getX()-0.8f, getY());

    }



    @Override
    public void act(float delta) {
        super.act(delta);


    }


    private static Touchpad.TouchpadStyle getTouchpadStyle() {

        touchpadSkin = new Skin();

        touchpadSkin.add("touchBackground", new Texture("imagenes/touchBackground3.png"));

        touchpadSkin.add("touchKnob", new Texture("imagenes/touchKnob3.png"));

        touchpadStyle = new Touchpad.TouchpadStyle();

        touchBackground = touchpadSkin.getDrawable("touchBackground");
        touchKnob = touchpadSkin.getDrawable("touchKnob");

        touchpadStyle.background = touchBackground;
        touchpadStyle.knob = touchKnob;


        return touchpadStyle;
    }



}
