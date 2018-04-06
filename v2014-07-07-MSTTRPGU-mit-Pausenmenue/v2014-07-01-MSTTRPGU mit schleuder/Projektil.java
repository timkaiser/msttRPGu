import java.awt.image.BufferedImage;
import java.util.*;
public class Projektil extends Einheit{
    private int xBeschleunigung,yBeschleunigung;
    private ArrayList<Projektil> projektile;
    private int cooldown;
    private Spieler schuetze;
    public Projektil(Animation ani1,Animation ani2,Animation ani3, Animation ani4,BufferedImage maskenImage,int x,int y,int width,int height,int xBeschleunigung,int yBeschleunigung,ArrayList<Projektil> projektile,Spieler schuetze){
        super(ani1,
            ani2,
            ani3,
            ani4,
            maskenImage,x,y,width,height);

        this.projektile = projektile;
        this.xBeschleunigung = xBeschleunigung;
        this.yBeschleunigung = yBeschleunigung;
        beschleunigen(xBeschleunigung,yBeschleunigung);
        this.schuetze = schuetze;
    }

    public void positionBerechnen(ArrayList<Hindernis> hindernisse){
        super.positionBerechnen(hindernisse);
        if(horizontaleGeschwindigkeit == 0 && vertikaleGeschwindigkeit == 0){
            projektile.remove(this);
        }
        kollidiertMitEinheit();
    }

    public void kollidiertMitEinheit(){
        ArrayList<Einheit> tmp = schuetze.getContainer().getZuBerechnendeEinheiten();
        for(int i = 1 ; i < tmp.size();i++){
            if(tmp.get(i).getKollisionsmaske() != getKollisionsmaske()){
                if(getKollisionsmaske().kollidiert(tmp.get(i).getKollisionsmaske()) && tmp.get(i).amLeben()){
                    tmp.get(i).wirdAngegriffen(schuetze.getX(),schuetze.getY(),20);
                    projektile.remove(this);
                }
            }
        }

    }

}