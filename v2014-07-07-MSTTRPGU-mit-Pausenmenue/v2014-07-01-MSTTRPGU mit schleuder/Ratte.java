import java.util.*;

public class Ratte extends Gegner{
    String richtung = "sued";
    boolean zurueck = false;
    public Ratte(double x, double y){
        super(new Animation(Bilder.getRattebild(3),20,100000),
            new Animation(Bilder.getRattebild(0),20,100000),
            new Animation(Bilder.getRattebild(2),20,100000),
            new Animation(Bilder.getRattebild(1),20,100000),
            Bilder.getSpinnemaske(),x,y,20,20);
        maximalGeschwindigkeit = 2.0;
        
        maximalLeben = 20;
        momentanLeben = 20;
    }

    protected void ki(ArrayList<Hindernis> hindernisse){
        if(!zurueck&&punktInRadius(startX,startY,256)){
            if((horizontaleGeschwindigkeit==0&&vertikaleGeschwindigkeit==0)||(int)(Math.random()*30)==1){
                richtungswechsel();
            }
            bewegeInRichtung();
        }else{
            zurueckZumStart();
        }
    }

    private void bewegeInRichtung(){
        switch (richtung){
            case "sued":
            beschleunigen(0,1.1);
            break;
            case "nord":
            beschleunigen(0,-1.1);
            break;
            case "ost":
            beschleunigen(1.1,0);
            break;
            case "west":
            beschleunigen(-1.1,0);
            break;
        }
    }

    private void richtungswechsel(){
        switch ((int)(Math.random()*4)){
            case 0:
            richtung = "sued";
            break;
            case 1:
            richtung = "nord";
            break;
            case 2:
            richtung = "ost";
            break;
            case 3:
            richtung = "west";
            break;
        }
    }

    private void zurueckZumStart(){
        if(punktInRadius(startX,startY,32)){
            zurueck = false;
        }else{
            zurueck = true;
        }
        if(startX>x+2){
            beschleunigen(1.5,0);
        }else if(startX<x-2){
            beschleunigen(-1.5,0);
        }else if(startY>y+2){
            beschleunigen(0,1.5);
        }else if(startY<y-2){
            beschleunigen(0,-1.5);
        }
    }
}
