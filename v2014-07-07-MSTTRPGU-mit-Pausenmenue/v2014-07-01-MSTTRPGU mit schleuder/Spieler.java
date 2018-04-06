import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.*;
import java.util.*;

public class Spieler extends Einheit{

    private double xVerschiebung, yVerschiebung;
    private GUITextFeld text;
    private boolean angriff;
    private Items itemSlot,angriffSlot;
    private Spielfeld container;
    private ArrayList<Respawnpunkt> respawnpunkte;
    private Respawnpunkt respawnpunkt;

    public Spieler(double x, double y,Spielfeld feld){
        super(new Animation(Bilder.getSpielerbild(2),19,150),
            new Animation(Bilder.getSpielerbild(1),19,150),
            new Animation(Bilder.getSpielerbild(3),19,150),
            new Animation(Bilder.getSpielerbild(0),19,150),
            Bilder.getSpielermaske(),x,y,19,33);               
        text =null;
        container = feld;
        angriffSlot = new Schwert("schwert",this);
        itemSlot = new Schleuder("schleuder",this,1000);
    }

    public void verschiebungBerechnen(JPanel panel, double zoomfaktor){
        int spielfeldBreite = (int)(panel.getWidth()/zoomfaktor);
        int spielfeldHoehe = (int)(panel.getHeight()/zoomfaktor);

        if(x-xVerschiebung<((spielfeldBreite/2-breite/2)*0.8)){
            xVerschiebung = (int)(x-((spielfeldBreite/2-breite/2)*0.8));            
        }else if(x-xVerschiebung>((spielfeldBreite/2-breite/2)*1.2)){
            xVerschiebung = (int)(x-((spielfeldBreite/2-breite/2)*1.2));
        }
        if(y-yVerschiebung<((spielfeldHoehe/2-hoehe/2)*0.8)){
            yVerschiebung = (int)(y-((spielfeldHoehe/2-hoehe/2)*0.8));
        }else if(y-yVerschiebung>((spielfeldHoehe/2-hoehe/2)*1.2)){
            yVerschiebung = (int)(y-((spielfeldHoehe/2-hoehe/2)*1.2));
        }
    }

    public void textWeiter(){
        if(text != null){
            text.weiter();
        }
    }

    public void setRespawnpunkte(ArrayList<Respawnpunkt> punkte){
        respawnpunkte=punkte;
        if(respawnpunkte.size()>0){
            respawnpunkt = respawnpunkte.get(0);
        }else{
            respawnpunkt = new Respawnpunkt(0,0);
        }
    }

    public void respawnpunkteErmitteln(){
        for(int i=0;i<respawnpunkte.size();i++){
            if(respawnpunkte.get(i).getEntfernung((int)x, (int)y)<64){
                respawnpunkt = respawnpunkte.get(i);
            }
        }
    }

    public void sterben(){
        momentanLeben=maximalLeben;
        x = respawnpunkt.getX();
        y = respawnpunkt.getY();
    }

    public double getXVerschiebung(){
        return xVerschiebung;
    }

    public double getYVerschiebung(){
        return yVerschiebung;
    }

    public void zeichnen(Graphics g, double zoomfaktor, double xVerschiebung, double yVerschiebung, JPanel panel){
        super.zeichnen(g, zoomfaktor, this.xVerschiebung, this.yVerschiebung, panel);
        if(text!=null){
            text.berechnen(panel.getWidth(),panel.getHeight());
            text.zeichnen(g,panel);
        }
        if(angriff){
            angriffSlot.zeichnen(g,zoomfaktor,panel);

        }
        angriffSlot.aktivieren(angriff);
        itemSlot.aktivieren(false);
    }

    public void setText(GUITextFeld text){
        this.text = text;
    }

    public void setAngriff(boolean a){
        angriff = a;
    }

    public void itemAktivieren(){
        if(itemSlot != null){
            itemSlot.aktivieren(true);
        }
    }

    public Spielfeld getContainer(){
        return container;
    }

    public Items getItemItem(){
        return itemSlot;
    }

    public Items getAngriffItem(){
        return angriffSlot;
    }
}