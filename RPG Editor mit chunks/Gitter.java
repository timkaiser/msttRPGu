public class Gitter{
    private int x,y,width,height;
    private int zahl;
    private char code;
    public Gitter(int x,int y,int width,int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        zahl = -1;
        code = 'l';
    }

    public Gitter(int x,int y,int width,int height,char code,int zahl){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.zahl = zahl;
        this.code = code;
    }
    
    public char getCode(){
        return code;
    }
    
    public int getZahl(){
        return zahl;
    }
    
    public void setZahl(int zahl){
        this.zahl = zahl;
    }
    
    public void setCode(char code){
        this.code = code;
    }
    
    public void bemale(Maus m){
        zahl = m.getZahl();
        code = m.getCode();
    }
    
    public void bemale(char code,int zahl){
        this.zahl = zahl;
        this.code = code;
    }
    
    public void resette(){
        zahl = 0;
        code = 'l';
    }
    
    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }
    
    public int getWidth(){
        return width;
    }
    
    public int getHeight(){
        return height;
    }
    
    public void setWidth(int width){
        this.width = width;
    }
    
    public void setHeight(int height){
        this.height = height;
    }
}