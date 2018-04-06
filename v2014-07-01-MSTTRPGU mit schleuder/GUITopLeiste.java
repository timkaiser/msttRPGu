import java.awt.*;
import javax.swing.*;
public class GUITopLeiste extends SpielerGUI{
    private Spieler spieler;
    private GUILebensAnzeige leben;
    private GUIItemBox item,angriff;
    public GUITopLeiste(double x,double y,double width,double height,Spieler spieler){
        super(x,y,width,height);
        this.spieler = spieler;
        leben = new GUILebensAnzeige(0.01,0.5,0.2,0.2,spieler);
        item = new GUIItemBox(0.9,0,0,0.9);
        angriff = new GUIItemBox(0.8,0,0,0.9);
    }

    public void zeichnen(Graphics g,JPanel panel){
        g.setColor(Color.BLACK);
        g.fillRect(getX(),getY(),getWidth(),getHeight());
        leben.zeichnen(g,panel);
        item.setItem(spieler.getItemItem());
        item.zeichnen(g,panel);
        angriff.setItem(spieler.getAngriffItem());
        angriff.zeichnen(g,panel);
    }

    public void setAngriffBox(Items item){
        angriff.setItem(item);
    }
    
    public void setItemBox(Items item){
        this.item.setItem(item);
    }
    
    public Spieler getSpieler(){
        return spieler;
    }

    public void berechnen(int breite,int hoehe){
        super.berechnen(breite,hoehe);
        leben.berechnen(getWidth(),getHeight());
        item.berechnen(getWidth(),getHeight());
        angriff.berechnen(getWidth(),getHeight());
    }

}