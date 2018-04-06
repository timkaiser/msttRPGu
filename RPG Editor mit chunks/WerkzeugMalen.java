import java.awt.*;

public class WerkzeugMalen extends Werkzeug{

    public void ausfuehren(Rectangle maus,Gitter[][] gitter){
        Rectangle gitterR;
        for(int i = 0; i< gitter.length ;i++){
            for (int j = 0; j< gitter[i].length;j++){
                gitterR = new Rectangle(gitter[i][j].getX(),gitter[i][j].getY(),gitter[i][j].getWidth(),gitter[i][j].getHeight());
                if(maus.intersects(gitterR)){
                    gitter[i][j].bemale(Frame.getMaus());
                }
            }
        }
    }

}