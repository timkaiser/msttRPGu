import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.*;

public class Zeichenobjekt{
    protected BufferedImage bild;
    protected Kollisionsmaske kollisionsmaske;
    protected double x,y;
    protected int breite, hoehe, ebene;

    protected final boolean hitboxen = false;

    public Zeichenobjekt(BufferedImage bild, BufferedImage maske, double x, double y, int breite, int hoehe,int ebene){
        this.bild = bild;
        this.x = x;
        this.y = y;
        this.breite = breite;
        this.hoehe = hoehe;
        this.ebene = ebene;
        kollisionsmaske = new Kollisionsmaske(x,y,maske);
    }

    public void zeichnen(Graphics g, double zoomfaktor, double xVerschiebung, double yVerschiebung, JPanel panel){

        double xTmp = (x-xVerschiebung)*zoomfaktor;
        double yTmp = (y-yVerschiebung)*zoomfaktor;
        int bTmp = (int)(((x-xVerschiebung+breite)*zoomfaktor)-(int)xTmp);
        int hTmp = (int)(((y-yVerschiebung+hoehe)*zoomfaktor)-(int)yTmp);

        if(hitboxen)
            g.drawImage(kollisionsmaske.getMaske(),(int)(xTmp), (int)(yTmp),bTmp,hTmp, panel);
        else
            g.drawImage(bild,(int)xTmp,(int)yTmp,bTmp,hTmp, panel);

        //g.drawRect((int)((x-xVerschiebung)*zoomfaktor),(int)((y-yVerschiebung)*zoomfaktor),(int)(breite*zoomfaktor),(int)(hoehe*zoomfaktor));
    }
    
    public boolean grobeKollsion(Rectangle r){
        return new Rectangle((int)x,(int)y,breite,hoehe).intersects(r);
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public int getHoehe(){
        return hoehe;
    }

    public int getBreite(){
        return breite;
    }

    public int getEbene(){
        return ebene;
    }

    public Kollisionsmaske getKollisionsmaske(){
        return kollisionsmaske;
    }
}
