import java.util.*;

public class Fledermaus extends Gegner{
    public Fledermaus(double x, double y){
        super(new Animation(Bilder.getFledermausbild(),12,100),
            new Animation(Bilder.getFledermausbild(),12,100),
            new Animation(Bilder.getFledermausbild(),12,100),
            new Animation(Bilder.getFledermausbild(),12,100),
            Bilder.getFledermausmaske(),x,y,12,12);
        maximalGeschwindigkeit = 10.0;
        xMitte=6;
        yMitte=6;
        maximalLeben = 40;
        momentanLeben = 40;
    }

    protected void ki(ArrayList<Hindernis> hindernisse){
        if(punktInRadius(startX,startY,480)){
            if(spielerInRadius(128)){
                aufSpielerZuBewegen();
            }else{
                if(punktInRadius(startX,startY,32)){
                    zufaelligBewegen();
                }else{
                    zurueckZumStart();
                }
            }
        }else{ 
            zurueckZumStart();
        }
    }

    private void aufSpielerZuBewegen(){
        if((horizontaleGeschwindigkeit<1.5&&horizontaleGeschwindigkeit>-1.5)&&(vertikaleGeschwindigkeit<1.5&&vertikaleGeschwindigkeit>-1.5)){
            if(spieler.getXMittelpunkt()>this.getXMittelpunkt()){
                beschleunigen(3,0);
            }else if(spieler.getXMittelpunkt()<this.getXMittelpunkt()){
                beschleunigen(-3,0);
            }        

            if(spieler.getYMittelpunkt()>this.getYMittelpunkt()){
                beschleunigen(0,3);
            }else if(spieler.getYMittelpunkt()<this.getYMittelpunkt()){
                beschleunigen(0,-3);
            }
        }
    }

    private void zufaelligBewegen(){
        int rndm = (int)(Math.random()*4);

        if(rndm == 0){               
            beschleunigen(2.0,0);
        }else  if(rndm == 1){               
            beschleunigen(-2.0,0);
        }else  if(rndm == 2){               
            beschleunigen(0,2.0);
        }else  if(rndm == 3){               
            beschleunigen(0,-2.0);
        }
    }

    private void zurueckZumStart(){
        if((horizontaleGeschwindigkeit<1.5&&horizontaleGeschwindigkeit>-1.5)&&(vertikaleGeschwindigkeit<1.5&&vertikaleGeschwindigkeit>-1.5)){
            if(startX>x){
                beschleunigen(3,0);
            }else if(startX<x){
                beschleunigen(-3,0);
            }        

            if(startY>y){
                beschleunigen(0,3);
            }else if(startY<y){
                beschleunigen(0,-3);
            }
        }
    }
}
