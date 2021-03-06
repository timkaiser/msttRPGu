import java.awt.*;
import javax.swing.*;
public class GUILebensAnzeige extends SpielerGUI{
    private Spieler spieler;
    public GUILebensAnzeige(double x,double y,double width,double height,Spieler spieler){
        super(x,y,width,height);
        this.spieler = spieler;
    }

    public void zeichnen(Graphics g,JPanel panel){
        g.setColor(new Color(200,220,220));
       
        g.fillRect(getX(),getY(),getWidth(),getHeight());
        g.setColor(new Color(170,0,0));
        int abstand = (int)(getHeight()*0.1);
        double breite =((double)spieler.getMomentanLeben()/(double)spieler.getMaximalLeben()) * (getWidth()-(2*abstand));
        g.fillRect(getX()+abstand,getY()+abstand,(int)breite,getHeight()-(2*abstand));
        
        Font font = new Font("Arial",Font.BOLD,(int)(getHeightProzent()*height));
        g.setFont(font.deriveFont((float)(getHeight())));
        
        g.drawString(spieler.getMomentanLeben() + "/" + spieler.getMaximalLeben(),getX()+getWidth(),getY()+getHeight());
    }

}