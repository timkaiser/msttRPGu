import java.awt.image.BufferedImage;
import java.awt.*;
import javax.swing.*;
public abstract class Items{
    protected BufferedImage image;
    protected String name;
    protected Spieler besitzer;
    public Items(BufferedImage image,String name,Spieler spieler){
        this.image=image;
        this.name = name;
        besitzer = spieler;
    }

    public abstract void aktivieren(boolean aktivieren);
    
    public abstract void zeichnen(Graphics g,double zoomfaktor,JPanel panel);
    
    public BufferedImage getImage(){
        return image;
    }
    
    public Spieler getBesitzer(){
        return besitzer;
    }
    
    public void setBufferedImage(BufferedImage image){
        this.image = image;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
}