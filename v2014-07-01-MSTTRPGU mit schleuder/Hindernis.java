import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.awt.Color;
import java.awt.Rectangle;

abstract class Hindernis extends Zeichenobjekt{    

    public Hindernis(BufferedImage bild, BufferedImage maske, double x, double y, int breite, int hoehe, int ebene){
        super(bild,maske,x,y,breite,hoehe,ebene);
    }

    abstract boolean kollidiertMit(Einheit einheit, double vHorizontal, double vVertikal);

    /* public void zeichnen(Graphics g, double zoomfaktor, double xVerschiebung, double yVerschiebung, JPanel panel){
    super.zeichnen(g, zoomfaktor, xVerschiebung, yVerschiebung, panel); 

    g.drawRect((int)((x-xVerschiebung)*zoomfaktor),(int)((y-yVerschiebung)*zoomfaktor),(int)(breite*zoomfaktor),(int)(hoehe*zoomfaktor));
    }*/
}
