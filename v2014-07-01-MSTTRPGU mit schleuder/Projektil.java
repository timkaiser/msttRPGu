import java.awt.image.BufferedImage;
public class Projektil extends Einheit{
    private int xBeschleunigung,yBeschleunigung;
    public Projektil(Animation ani1,Animation ani2,Animation ani3, Animation ani4,BufferedImage maskenImage,int x,int y,int width,int height,int xBeschleunigung,int yBeschleunigung){
        super(ani1,
                ani2,
                ani3,
                ani4,
                maskenImage,x,y,width,height);
                
        this.xBeschleunigung = xBeschleunigung;
        this.yBeschleunigung = yBeschleunigung;
        beschleunigen(xBeschleunigung,yBeschleunigung);
    }
    
    

}