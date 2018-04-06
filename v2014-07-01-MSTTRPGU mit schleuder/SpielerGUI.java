import java.awt.*;
import javax.swing.*;
public abstract class SpielerGUI{
    protected int x,y,width,height;
    protected double xProzent,yProzent,widthProzent,heightProzent;
    public SpielerGUI(double xProzent,double yProzent,double widthProzent,double heightProzent){
        this.xProzent = xProzent;
        this.yProzent = yProzent;
        this.widthProzent = widthProzent;
        this.heightProzent = heightProzent;

    } 

    public void berechnen(int containerWidth,int containerHeight){
        x = (int)(containerWidth*xProzent);
        y = (int)(containerHeight*yProzent);
        width = (int)(containerWidth*widthProzent);
        height = (int)(containerHeight*heightProzent);
    }

    public abstract void zeichnen(Graphics g,JPanel panel);

    public double getXProzent(){
        return xProzent;
    }

    public double getYProzent(){
        return yProzent;
    }

    public double getWidthProzent(){
        return widthProzent;
    }

    public double getHeightProzent(){
        return heightProzent;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void setX(int x){
        this.x = x;
    }
    
    public void setY(int y){
        this.y = y;
    }
    
    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public void setWidth(int width){
        this.width = width;
    }

    public void setHeight(int height){
        this.height = height;
    }

}