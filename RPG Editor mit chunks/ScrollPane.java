import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ScrollPane extends JScrollPane implements KeyListener{
    private boolean waagerecht;
    private Gitter[][] gitter;
    private Gitter[][][] gesamtGitter;
    private ZeichenPanel[] zeichenPanel;
    public ScrollPane(JPanel p,Gitter[][] gitter,Gitter[][][] gesamtGitter,ZeichenPanel[] alleZeichenPanel){
        super(p);
        setViewportView(new ZeichenPanel(0,0,gitter,gesamtGitter,this));
        addKeyListener(this);
        waagerecht = false;
        this.gitter = gitter;
        this.gesamtGitter = gesamtGitter;
        zeichenPanel = alleZeichenPanel;
        requestFocus();
    }

    public void processMouseWheelEvent(MouseWheelEvent e){

        //Rectangle rect = getViewportBorderBounds();

        if(waagerecht){
            if(e.getWheelRotation() > 0){
                getHorizontalScrollBar().setValue(getHorizontalScrollBar().getValue()+Frame.getGitterZahl());
            }
            else{
                getHorizontalScrollBar().setValue(getHorizontalScrollBar().getValue()-Frame.getGitterZahl());
            }
        }
        else{

            super.processMouseWheelEvent(e);
        }

    }

    public void scrollWaagerecht(){
        waagerecht = true;
    }

    public void scrollSenkrecht(){
        waagerecht = false;
    }

    public boolean isWaagerecht(){
        return waagerecht;
    }

    public void keyReleased(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_SHIFT){
            waagerecht = false;
        }
    }

    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_SHIFT){
            waagerecht = true;
        }
        else if(e.getKeyCode() == KeyEvent.VK_PLUS){
            Frame.setGitterZahl(Frame.getGitterZahl() + 1);
            System.out.println(Frame.getGitterZahl());

            for(int w = 0; w < zeichenPanel.length;w++){

                Gitter[][] tmp = new Gitter[gesamtGitter[0].length][gesamtGitter[0][0].length];

                for(int i = 0;i < gesamtGitter[w].length;i++){
                    for(int j = 0; j < gesamtGitter[w][i].length;j++){
                        tmp[i][j] = new Gitter(gesamtGitter[w][i][j].getX(),gesamtGitter[w][i][j].getY(),gesamtGitter[w][i][j].getWidth(),gesamtGitter[w][i][j].getHeight(),gesamtGitter[w][i][j].getCode(),gesamtGitter[w][i][j].getZahl());
                    }
                }

                for(int i = 0;i < gesamtGitter[w].length;i++){
                    for(int j = 0; j < gesamtGitter[w][i].length;j++){
                        gesamtGitter[w][i][j] = new Gitter(i*Frame.getGitterZahl(),j*Frame.getGitterZahl(),Frame.getGitterZahl(),Frame.getGitterZahl());
                    }
                }

                for(int i = 0;i < gesamtGitter[w].length;i++){
                    for(int j = 0; j < gesamtGitter[w][i].length;j++){
                        gesamtGitter[w][i][j].setCode(tmp[i][j].getCode());
                        gesamtGitter[w][i][j].setZahl(tmp[i][j].getZahl());
                    }
                }

            }
            setViewportView(new ZeichenPanel(0,0,gitter,gesamtGitter,this));
        }
        else if(e.getKeyCode() == KeyEvent.VK_MINUS){
            Frame.setGitterZahl (Frame.getGitterZahl()- 1);
            System.out.println(Frame.getGitterZahl());

            for(int w = 0; w < zeichenPanel.length;w++){

                Gitter[][] tmp = new Gitter[gesamtGitter[0].length][gesamtGitter[0][0].length];

                for(int i = 0;i < gesamtGitter[w].length;i++){
                    for(int j = 0; j < gesamtGitter[w][i].length;j++){
                        tmp[i][j] = new Gitter(gesamtGitter[w][i][j].getX(),gesamtGitter[w][i][j].getY(),gesamtGitter[w][i][j].getWidth(),gesamtGitter[w][i][j].getHeight(),gesamtGitter[w][i][j].getCode(),gesamtGitter[w][i][j].getZahl());
                    }
                }

                for(int i = 0;i < gesamtGitter[w].length;i++){
                    for(int j = 0; j < gesamtGitter[w][i].length;j++){
                        gesamtGitter[w][i][j] = new Gitter(i*Frame.getGitterZahl(),j*Frame.getGitterZahl(),Frame.getGitterZahl(),Frame.getGitterZahl());
                    }
                }

                for(int i = 0;i < gesamtGitter[w].length;i++){
                    for(int j = 0; j < gesamtGitter[w][i].length;j++){
                        gesamtGitter[w][i][j].setCode(tmp[i][j].getCode());
                        gesamtGitter[w][i][j].setZahl(tmp[i][j].getZahl());
                    }
                }

            }
            setViewportView(new ZeichenPanel(0,0,gitter,gesamtGitter,this));
        }

        repaint();

    }

    public void keyTyped(KeyEvent e){
    }
}