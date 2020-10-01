package com.academiaandroid.juegoslibgdx_ver2_0;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;


public class Principal extends Game {


    private AssetManager manager;

    public BaseScreen LoadingScreen, pantalla1;


    public MenuPrincipal menuPrincipal;




    @Override
    public void create() {


        /*
        Cargar las imagenes y los audios del juego desde aqui. --> eliminar los otros managers.
         */

        manager = new AssetManager();
        manager.load("imagenes/coin_bronze.png", Texture.class);
        manager.load("imagenes/coin_bronze.png", Texture.class);
        manager.load("imagenes/nube.png", Texture.class);
        manager.load("imagenes/background.png", Texture.class);
        manager.load("imagenes/BotonSalto.png", Texture.class);
        manager.load("imagenes/knob.png", Texture.class);
        manager.load("imagenes/player.png", Texture.class);
        manager.load("imagenes/touchKnob.png", Texture.class);
        manager.load("imagenes/leader.png", Texture.class);
        manager.load("imagenes/prize.png", Texture.class);
        manager.load("imagenes/about.png", Texture.class);
        manager.load("imagenes/nextpngcom.png", Texture.class);
        manager.load("imagenes/bg_marron.png", Texture.class);
        manager.load("imagenes/JAIR.png",Texture.class);
        manager.load("imagenes/bg.png",Texture.class);
        manager.load("imagenes/bg.png", Texture.class);
        manager.load("imagenes/ok.png", Texture.class);
        manager.load("audios/Pickup_Coin16.ogg", Sound.class);
        manager.load("audios/wind1.ogg", Music.class);



        LoadingScreen = new LoadingScreen(this);
        setScreen(LoadingScreen);


    }

    public void finishLoading() {

        menuPrincipal = new MenuPrincipal(this);
        pantalla1 = new PantallaUno(this);
        setScreen(menuPrincipal);
    }

    public AssetManager getManager() {
        return manager;
    }
}
