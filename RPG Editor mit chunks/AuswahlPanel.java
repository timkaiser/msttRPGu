import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
public class AuswahlPanel extends JPanel{
    private String namen[];
    private AuswahlMenue auswahlMenue;
    private KnopfPanel knopfPanel[];
    private JScrollPane knopfPane[];
    private char code;
    public AuswahlPanel(int x,int y,int width,int height){
        setLayout(null);
        setBounds(x,y,width,height);
        setPreferredSize(new Dimension(width,height));

        setBackground(Color.RED);

        namen = Bilder.getOrdner();

        auswahlMenue = new AuswahlMenue(0,0,width,25,this,namen); //new AuswahlMenue(xPosition,yPosition,breite,hoehe,feld mit den Namen,AuswahlPanel);
        add(auswahlMenue);

        knopfPane = new JScrollPane[namen.length];
        knopfPanel = new KnopfPanel[knopfPane.length];

        for(int i = 0; i< knopfPanel.length;i++){
            knopfPanel[i] = new KnopfPanel(0,0,width,groesseAnpassen(i),namen[i]);
            knopfPanel[i].setVisible(true);
        }

        for(int i = 0; i<knopfPane.length;i++){
            knopfPane[i] = new JScrollPane(knopfPanel[i]);
            knopfPane[i].setBounds(0,25,width,height-25);
            knopfPane[i].setVisible(true);
            add(knopfPane[i]);
        }

        updatePanel();
    }

    public void updatePanel(){
        for(int i = 0; i < namen.length;i++){
            if(auswahlMenue.getFocus().equals(namen[i])){
                knopfPane[i].setVisible(true);
            }
            else{
                knopfPane[i].setVisible(false);
            }
        }
        repaint();
    }

    public int groesseAnpassen(int index){
        /*
        String name = auswahlMenue.getFocus();
        char code = name.charAt(0);

        char[] array = Bilder.getSpeicherCode();
        int index = 0;
        for(int i = 0; i < array.length;i++){
        if(array[i] == code){
        index = i;
        }
        }
        BufferedImage[][][] image = Bilder.getSprites();
         */
        int x = Bilder.getHintergrundBilderHoeheAnzahl(index)*69;
        System.out.println(x);
        return x;

    }

    public void addKnopf(int i,Knopf knopf){
        knopfPanel[i].addKnopf(knopf);
    }

}