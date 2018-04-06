import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.*;
import java.util.*;

abstract class Gegner extends NPC{

    protected double startX, startY, schaden;

    public Gegner(Animation animationL,Animation animationR,Animation animationO,Animation animationU, BufferedImage maske, double x, double y, int breite, int hoehe){
        super(animationL,animationR,animationO,animationU,maske,x,y,breite,hoehe);
        startX=x;
        startY=y;
        schaden=10;
 
    }

    public void wirdAngegriffen(double x,double y,int schaden){
        if(amLeben){
            kriegtSchaden(schaden,x,y,10);
        }
    }

    protected void aufStartZuruecksetzen(){
        x = startX;
        y = startY;
    }

    protected boolean spielerInRadius(int r){
        return punktInRadius(spieler.getX(),spieler.getY(),r);
    }

    protected boolean punktInRadius(double xp, double yp, int r){
        int abstand = (int)Math.sqrt((Math.pow(xp-x, 2.0)+Math.pow(yp-y, 2.0)));
        if(abstand<r){
            return true;
        }else{
            return false;
        }
    }

    public static void setSpieler(Spieler sp){
        spieler = sp;
    }

    protected void sterben(){
        amLeben = false;
    }

    public void kollidiertMitSpieler(){
        if(amLeben){
            if(spieler.getKollisionsmaske().kollidiert(kollisionsmaske)){
                spieler.kriegtSchaden(schaden,this.getXMittelpunkt(),this.getYMittelpunkt(),10);
                horizontaleGeschwindigkeit=0;
                vertikaleGeschwindigkeit=0;
            }
        }
    }

    public void positionBerechnen(ArrayList<Hindernis> hindernisse){
        if(amLeben){
            ki(hindernisse);
            super.positionBerechnen(hindernisse);
        }
    }

    abstract void ki(ArrayList<Hindernis> hindernisse);

    public void kriegtSchaden(double schaden, double quelleX, double quelleY, double f){
        if(amLeben){
            super.kriegtSchaden(schaden, quelleX, quelleY, f);
        }
    }

    public void kriegtSchaden(double schaden){
        if(amLeben){
            super.kriegtSchaden(schaden);
        }
    }

    public void zeichnen(Graphics g, double zoomfaktor, double xVerschiebung, double yVerschiebung, JPanel panel){
        if(amLeben){
            super.zeichnen(g, zoomfaktor, xVerschiebung, yVerschiebung, panel)  ;          
        }
    }
}