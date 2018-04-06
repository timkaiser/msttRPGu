import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class ZeichenPanel extends JPanel implements MouseListener,MouseMotionListener{
    private Frame fenster;
    private Gitter[][] gitter;
    private Gitter[][][] andereGitter;
    private boolean links,rechts;
    private ScrollPane container;
    public ZeichenPanel(int x,int y,Gitter[][] gitter,Gitter[][][] andereGitter,ScrollPane container){
        int width = gitter.length*Frame.getGitterZahl();
        int height = gitter[0].length*Frame.getGitterZahl();
        setBounds(x,y,width,height);
        setPreferredSize(new Dimension(width,height));
        setBackground(Color.BLACK);
        this.gitter = gitter;
        this.andereGitter = andereGitter;
        addMouseListener(this);
        addMouseMotionListener(this);
        links = false;
        rechts = false;
        this.container = container;
    }

    public ZeichenPanel(int x,int y,Gitter[][] gitter,Gitter[][][] andereGitter){
        int width = gitter.length*Frame.getGitterZahl();
        int height = gitter[0].length*Frame.getGitterZahl();
        setBounds(x,y,width,height);
        setPreferredSize(new Dimension(width,height));
        setBackground(Color.BLACK);
        this.gitter = gitter;
        this.andereGitter = andereGitter;
        addMouseListener(this);
        addMouseMotionListener(this);
        links = false;
        rechts = false;
    }

    public void addContainer(ScrollPane container){
        this.container = container;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for(int i = 0; i< gitter.length ;i++){
            for (int j = 0; j< gitter[i].length;j++){

                /*if(i%25 == 0 || j % 15 == 0){
                g.setColor(Color.RED);
                }
                else{
                g.setColor(Color.WHITE);
                }
                 */
                g.drawRect(gitter[i][j].getX(),gitter[i][j].getY(),Frame.getGitterZahl(),Frame.getGitterZahl());

            }
        }
        g.setColor(Color.RED);
        for(int i = 0; i< gitter.length ;i++){
            for (int j = 0; j< gitter[i].length;j++){
                if(i%25 == 0){
                    g.drawLine(gitter[i][j].getX(),gitter[i][j].getY(),gitter[i][j].getX(),getHeight());
                }
                if(j%15 == 0){
                     g.drawLine(gitter[i][j].getX(),gitter[i][j].getY(),getWidth(),gitter[i][j].getY());
                }
            }
        }

        Graphics2D g2d = (Graphics2D)g;
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.50f));

        for(int i = 0; i< andereGitter.length ;i++){
            for (int j = 0; j< andereGitter[i].length;j++){
                for(int w = 0; w < andereGitter[i][j].length;w++){
                    if(andereGitter[i][j][w].getCode() != 'l' && andereGitter[i][j][w].getZahl() != -1){
                        g.drawImage(Bilder.getSprite(andereGitter[i][j][w].getCode(),andereGitter[i][j][w].getZahl()),andereGitter[i][j][w].getX(),andereGitter[i][j][w].getY(),andereGitter[i][j][w].getWidth(),andereGitter[i][j][w].getHeight(),this);
                    }
                }
            }
        }

        for(int i = 0; i< gitter.length ;i++){
            for (int j = 0; j< gitter[i].length;j++){
                if(gitter[i][j].getCode() != 'l' && gitter[i][j].getZahl() != -1){
                    g.drawImage(Bilder.getSprite(gitter[i][j].getCode(),gitter[i][j].getZahl()),gitter[i][j].getX(),gitter[i][j].getY(),gitter[i][j].getWidth(),gitter[i][j].getHeight(),this);
                }
            }
        }

        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        for(int i = 0; i< gitter.length ;i++){
            for (int j = 0; j< gitter[i].length;j++){
                if(gitter[i][j].getCode() != 'l' && gitter[i][j].getZahl() != -1){
                    g.drawImage(Bilder.getSprite(gitter[i][j].getCode(),gitter[i][j].getZahl()),gitter[i][j].getX(),gitter[i][j].getY(),gitter[i][j].getWidth(),gitter[i][j].getHeight(),this);
                }
            }
        }

        for(int i = 0; i < Frame.getZeichenPanel().length;i++){
            Frame.getZeichenPanel()[i].repaint();
        }

        container.requestFocus();
    }

    public void mouseExited(MouseEvent e){
    }

    public void mouseEntered(MouseEvent e){
    }

    public void mouseReleased(MouseEvent e){

        if(e.getButton() == 1){
            links = false;
        }
        else if(e.getButton() == 3){
            rechts = false;
        }

    }

    public void mousePressed(MouseEvent e){

        if(e.getButton() == 1){
            links = true;
            rechts = false;

        }
        else if(e.getButton() == 3){
            rechts = true;
            links = false;
        }

    }

    public void mouseClicked(MouseEvent e){
    }

    public void mouseMoved(MouseEvent e){
    }

    public void mouseDragged(MouseEvent e){

        Rectangle maus = new Rectangle(e.getX(),e.getY(),1,1);

        if(links){

            if(Frame.getMaus().getWerkzeug() != null){ 
                Frame.getMaus().getWerkzeug().ausfuehren(maus,gitter);
            }

        }
        else{
            Rectangle gitterR;
            for(int i = 0; i< gitter.length ;i++){
                for (int j = 0; j< gitter[i].length;j++){
                    gitterR = new Rectangle(gitter[i][j].getX(),gitter[i][j].getY(),gitter[i][j].getWidth(),gitter[i][j].getHeight());
                    if(maus.intersects(gitterR)){
                        gitter[i][j].resette();
                    }
                }
            }
        }
        repaint();
    }

}