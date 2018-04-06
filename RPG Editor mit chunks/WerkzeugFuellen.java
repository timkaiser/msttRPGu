import java.awt.*;

public class WerkzeugFuellen extends Werkzeug{

    public void ausfuehren(Rectangle maus,Gitter[][] gitter){
        Rectangle gitterR;
        for(int i = 0; i< gitter.length ;i++){
            for (int j = 0; j< gitter[i].length;j++){
                gitterR = new Rectangle(gitter[i][j].getX(),gitter[i][j].getY(),gitter[i][j].getWidth(),gitter[i][j].getHeight());
                if(maus.intersects(gitterR)){
                    ausfuehrenR(gitter,gitter[i][j],Frame.getMaus().getCode(),Frame.getMaus().getZahl(),new Gitter(gitter[i][j].getX(),gitter[i][j].getY(),gitter[i][j].getWidth(),gitter[i][j].getHeight(),gitter[i][j].getCode(),gitter[i][j].getZahl()));
                }
            }
        }
    }

    public void ausfuehrenR(Gitter[][] gitter, Gitter vorher,char code,int zahl,Gitter ursprung){
        int x = vorher.getX()/Frame.getGitterZahl();
        int y = vorher.getY()/Frame.getGitterZahl();
        vorher.bemale(code,zahl);
        if(x+1 < gitter.length){
            if(gitter[x+1][y].getCode() == ursprung.getCode() && gitter[x+1][y].getZahl() == ursprung.getZahl()){
                ausfuehrenR(gitter,gitter[x+1][y],code,zahl,ursprung);
            }
        }
        if(y+1 < gitter[0].length){
            if(gitter[x][y+1].getCode() == ursprung.getCode() && gitter[x][y+1].getZahl() == ursprung.getZahl()){
                ausfuehrenR(gitter,gitter[x][y+1],code,zahl,ursprung);
            }
        }
        if(x-1 >=0){
            if(gitter[x-1][y].getCode() == ursprung.getCode() && gitter[x-1][y].getZahl() == ursprung.getZahl()){
                ausfuehrenR(gitter,gitter[x-1][y],code,zahl,ursprung);
            }
        }
        if(y-1 >=0){
            if(gitter[x][y-1].getCode() == ursprung.getCode() && gitter[x][y-1].getZahl() == ursprung.getZahl()){
                ausfuehrenR(gitter,gitter[x][y-1],code,zahl,ursprung);
            }
        }

    }
}