import javax.swing.*;
import java.awt.*;
import java.util.*;
public class Toolbar extends JPanel{
    private Frame container;
    private java.util.List<WerkzeugKnopf> knoepfe;
    public Toolbar(int x,int y,int width,int height,Frame container){
        setBounds(x,y,width,height);
        setBackground(Color.WHITE);
        knoepfe = new ArrayList<WerkzeugKnopf>();
        
        setVisible(true);
    }

    public void addKnopf(WerkzeugKnopf k){

        k.setBounds(knoepfe.size()*20,5,16,16);
        knoepfe.add(k);
        k.setVisible(true);
        add(k);

        repaint();
    }
}