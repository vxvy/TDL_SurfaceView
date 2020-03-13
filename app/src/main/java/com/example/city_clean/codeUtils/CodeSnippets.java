package com.example.city_clean.codeUtils;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

public class CodeSnippets {

    public Context context;
    public int pantallaSizeX;
    public int pantallaSizeY;

    public CodeSnippets(Context context){
        this.context = context;
        pantallaSizeX = context.getResources().getDisplayMetrics().widthPixels;
        pantallaSizeY = context.getResources().getDisplayMetrics().heightPixels;

    }

    public static Bitmap[][] construyeArray(Bitmap bm,
                                     int spritesHorizontal, int spritesVertical,
                                     int tamanyoHorizontal, int tamanyoVertical
    ){
        Bitmap[][] aux = new Bitmap[spritesVertical][spritesHorizontal];

        int posCorteXIni = 0;
        int posCorteYIni = 0;
        int posCorteXFin = tamanyoHorizontal;
        int posCorteYFin = tamanyoVertical;

        Log.d("ASDF0","Emppieza a cortar en : "+
                "\nposCorteXIni " + posCorteXIni +
                "\nposCorteYIni " + posCorteYIni +
                "\nposCorteXFin " + posCorteXFin +
                "\nposCorteYFin " + posCorteYFin
                );
        for(int i = 0; i < aux.length; i++){
            posCorteXIni = 0;
            posCorteXFin = tamanyoHorizontal;
            for(int j = 0; j < aux[i].length; j++){
                aux[i][j] = Bitmap.createBitmap(
                        bm,
                        posCorteXIni, posCorteYIni,
                        posCorteXFin, posCorteYFin);
                posCorteXIni += tamanyoHorizontal;
                posCorteXFin += tamanyoHorizontal;

                Log.d("ASDF1","Iteración: i = "+ i + " - j: "+ j+
                        "\nposCorteXIni " + posCorteXIni +
                        "\nposCorteYIni " + posCorteYIni +
                        "\nposCorteXFin " + posCorteXFin +
                        "\nposCorteYFin " + posCorteYFin
                );
            }
            posCorteYIni += tamanyoVertical;
            posCorteYFin += tamanyoVertical;

            Log.d("ASDF2","Iteración: i = "+ i + " - j: "+ "FIN"+
                    "\nposCorteXIni " + posCorteXIni +
                    "\nposCorteYIni " + posCorteYIni +
                    "\nposCorteXFin " + posCorteXFin +
                    "\nposCorteYFin " + posCorteYFin
            );
        }
        return aux;
    }

    public static Bitmap getBitmapFromAssets(Context context, String relativePath){
        AssetManager assetManager = context.getAssets();
        InputStream is = null;
        Bitmap btmap = null;

        try{
            is = assetManager.open(relativePath);
            btmap = BitmapFactory.decodeStream(is);
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
        return btmap;
    }
}
