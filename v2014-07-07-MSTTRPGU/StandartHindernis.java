import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.awt.Color;
import java.awt.Rectangle;

public class StandartHindernis extends Hindernis{

    public StandartHindernis(BufferedImage bild, BufferedImage maske, double x, double y, int breite, int hoehe, int ebene){
        super(bild,maske,x,y,breite,hoehe,ebene);
    }

    public boolean kollidiertMit(Einheit einheit, double vHorizontal, double vVertikal){
        //return (new Rectangle((int)x,(int)y,breite,hoehe)).intersects(new Rectangle((int)(einheit.getX()+vHorizontal),(int)(einheit.getY()+vVertikal),einheit.getBreite(),einheit.getHoehe()));
        if(einheit.getEbene()!=ebene)
            return false;
        else
            return einheit.getKollisionsmaske().kollidiert(kollisionsmaske,vHorizontal,vVertikal);
    }
}
