package com.example.city_clean.actores;

import android.graphics.Bitmap;

import com.example.city_clean.codeUtils.RecursosCodigo;

public class ActorSiegfried extends EsquemaActor {

    /**
     *  String SIEGFRIED_ACHIVEMENT_PATH ="siegfried/achivement/achivement.png";
     *     public static final String SIEGFRIED_DYING_PATH ="siegfried/dying/dying.png";
     *     public static final String SIEGFRIED_HITTING_PATH ="siegfried/hitting/64x64.png";
     *     public static final String SIEGFRIED_HURT_PATH ="siegfried/hurt/hurt.png";
     *     public static final String SIEGFRIED_JUMPING_PATH ="siegfried/jumping/jumping.png";
     *     public static final String SIEGFRIED_STANDING_PATH ="siegfried/static/standing.png";
     *     public static final String SIEGFRIED_WALKING_PATH ="siegfried/running/running.png";
     */

    public Bitmap[] arrSieffriedDying;
    public Bitmap[] arrSieffriedHitting;
    public Bitmap[] arrSieffriedHurt;
    public Bitmap[] arrSieffriedJumping;
    public Bitmap[] arrSieffriedStanding;
    public Bitmap[] arrSieffriedWalkinf;

    public ActorSiegfried(){
        this.arrSieffriedDying = RecursosCodigo.construyeArray()
    }


}
