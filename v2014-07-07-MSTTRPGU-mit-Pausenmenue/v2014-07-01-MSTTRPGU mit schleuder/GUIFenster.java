import java.awt.*;
import javax.swing.*;
public class GUIFenster extends SpielerGUI{

    public GUIFenster(double x,double y,double width,double height){
        super(x,y,width,height);
    }

    public void zeichnen(Graphics g,JPanel panel){
        int abstand = (int)((getHeight()/getHeightProzent())*0.01);
        rechteck(g,getX(),getY()-getHeight(),getWidth(),getHeight(),new Color(255,215,000),new Color(176,226,255,200),abstand,5);
    }

    public void rechteck(Graphics g,int x,int y,int width,int height,Color farbeRand,Color farbeInnen,int abstand,int counter){
        g.setColor(farbeRand);
        rechteckHorizontal(g,x,y,width,abstand,farbeRand);
        rechteckHorizontal(g,x,(y+height)-abstand,width,abstand,farbeRand);
        rechteckVertical(g,x,y,abstand,height,farbeRand);
        rechteckVertical(g,(x+width)-abstand,y,abstand,height,farbeRand);
        g.setColor(farbeInnen);
        
        g.fillRect((int)(x+abstand),(int)((y)+abstand),(int)(width-abstand*2),(int)(height-abstand*2));

    }

    public void rechteckHorizontal(Graphics g,int x,int y,int width,int height,Color farbe){
        Color color = farbe;
        /*
        for(int i = 0 ; i < width/5;i++){
        for(int j = 0; i < height/5;j++){
        g.setColor(farbe);
        g.fillRect(x+i,y,width-1,height);
        farbe.brighter();
        }
        }
         */
    }

    public void rechteckVertical(Graphics g,int x,int y,int width,int height,Color farbe){
        g.fillRoundRect(x,y,width,height,width,height/width);
    }
}