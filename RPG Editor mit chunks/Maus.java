public class Maus{
    private char code;
    private int zahl;
    private Werkzeug werkzeug;
    public Maus(){
        code = 'l';
        zahl = -1;
        werkzeug = new WerkzeugMalen();
    }

    public char getCode(){
        return code;
    }

    public void setCode(char code){
        this.code = code;
    }

    public int getZahl(){
        return zahl;
    }

    public void setZahl(int zahl){
        this.zahl = zahl;
    }

    public Werkzeug getWerkzeug(){
        return werkzeug;
    }

    public void setWerkzeug(Werkzeug werkzeug){
        this.werkzeug = werkzeug;
    }

}