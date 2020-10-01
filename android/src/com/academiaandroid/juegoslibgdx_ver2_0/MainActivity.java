package com.academiaandroid.juegoslibgdx_ver2_0;

//Ejemplo aplicación Android en el que utilizaremos el framework libGDX para
//el desarrollo de videojuegos.
//
//academiaandroid.com
//
//by José Antonio Gázquez Rodríguez

import android.os.Bundle;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

/*Clase MainActivity que hereda de la clase base AndroidApplication para
la inicialización del videojuego.*/
public class MainActivity extends AndroidApplication {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        /*Se inicializa la clase encargada de ejecutar el videojuego.*/
        initialize(new Principal(), cfg);
    }
}