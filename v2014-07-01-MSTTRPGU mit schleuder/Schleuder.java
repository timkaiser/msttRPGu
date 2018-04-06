import java.awt.*;
import javax.swing.*;

public class Schleuder extends Items{
    
    public Schleuder(String name,Spieler spieler){
        super(null,name,spieler);
    }

    public void aktivieren(){
        int xBeschleunigung = 0,yBeschleunigung = 0;
        int geschwindigkeit = 20;
        if(besitzer.getRechts()){
            xBeschleunigung = geschwindigkeit;
            yBeschleunigung = 0;
        }
        else if(besitzer.getLinks()){
            xBeschleunigung = -geschwindigkeit;
            yBeschleunigung = 0;
        }
        else if(besitzer.getOben()){
            xBeschleunigung = 0;
            yBeschleunigung = -geschwindigkeit;
        }
        else if(besitzer.getUnten()){
            xBeschleunigung = 0;
            yBeschleunigung = geschwindigkeit;
        }
        besitzer.getContainer().projektilHinzufuegen(new Stein((int)(besitzer.getX()+(besitzer.getBreite()*0.5)),(int)(besitzer.getY()+(besitzer.getHoehe()*0.5)),xBeschleunigung,yBeschleunigung));
    }

    public void zeichnen(Graphics g,double zoomfaktor,JPanel panel){
        
    }
}