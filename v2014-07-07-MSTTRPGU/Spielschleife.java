import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import java.util.*;
import java.awt.Graphics;

public class Spielschleife implements Runnable{
    public static boolean laeuft=true;

    private Spielfeld spielfeld;
    private Steuerung steuerung;

    private List<Hindernis> hindernisse;
    private List<Einheit> einheiten;
    Thread gameloop;
    public Spielschleife(Spielfeld feld, Spieler spieler){
        spielfeld = feld;

        steuerung = new Steuerung(spieler, this);
        feld.addKeyListener(steuerung);

        gameloop = new Thread (this);
        gameloop.start();
    }

    public void run(){
        Date schlafzeit=new Date();
        while(true){
            if(laeuft){
                spielfeld.weltAusschnittErmitteln();
                spielfeld.einheitenPostionBerechnen();
                spielfeld.zeichenreihenfolgeFestlegen();
                steuerung.indirektesInputUeberpruefung();
            }
            if(Fenster.getPauseAn()==false){
                spielfeld.repaint();
            }

            try {
                long t=(schlafzeit.getTime()+   25   )-(new Date()).getTime();

                if(t>0){ Thread.sleep(25); };
                schlafzeit=new Date();
            } catch (InterruptedException e) {}
        }
    }

    public static void spielPausieren(){
        if(laeuft==true){
            pausieren();
        }
        else{
            fortsetzen();
        }
    }

    public static boolean getLaeuft(){
        return laeuft;
    }

    public static void pausieren(){
        laeuft =false;
        Animation.pausiert=true;
    }

    public static void fortsetzen(){
        laeuft = true;
        Animation.pausiert=false;
    }

}