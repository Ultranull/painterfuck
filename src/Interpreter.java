import java.awt.*;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by usr on 12/9/2016.
 *
 */
public class Interpreter {
    private mColor[][] Canvas;
    private Point pointer;
    private LinkedList<String> stack;
    private boolean rm   = true;
    private boolean gm   = false;
    private boolean bm   = false;
    public int LENGTH    = 0xff;
    private int loop     = 0;
    private int max      = 0xff-1;

    public Interpreter(){
        Canvas=new mColor[LENGTH][LENGTH];
        for(int r=0;r<LENGTH;r++)
            for(int c=0;c<LENGTH;c++)
                Canvas[r][c]=new mColor(0,0,0);
        pointer=new Point(0,0);
        stack=new LinkedList<>();

    }
    public void interpret(String code){
        for(int i=0;i<code.length();i++){
            mColor c=Canvas[pointer.x][pointer.y];

            System.out.print(c.red + ",");

            if(code.charAt(i)=='!') {
                break;
            }
            else if(code.charAt(i)=='r') {
                rm=true;
                gm=false;
                bm=false;
                continue;
            }
            else if(code.charAt(i)=='g') {
                rm=false;
                gm=true;
                bm=false;
                continue;
            }
            else if(code.charAt(i)=='b') {
                rm=false;
                gm=false;
                bm=true;
                continue;
            }
            else if(code.charAt(i)=='>') {
                pointer.x+=1;
                if(pointer.x>=LENGTH){
                    pointer.x=0;
                }
                continue;
            }
            else if(code.charAt(i)=='<') {
                pointer.x-=1;
                if(pointer.x<=0){
                    pointer.x=LENGTH-1;
                }
                continue;
            }
            else if(code.charAt(i)=='V') {
                pointer.y-=1;
                if(pointer.y<=0){
                    pointer.y=LENGTH-1;
                }
                continue;
            }
            else if(code.charAt(i)=='^') {
                pointer.y+=1;
                if(pointer.y>=LENGTH){
                    pointer.y=0;
                }
                continue;
            }
            else if(code.charAt(i)=='+') {
                c.inccurrent(rm,gm,bm);
            }
            else if(code.charAt(i)=='-') {
                c.deccurrent(rm,gm,bm);
            }
            else if(code.charAt(i) == '[') {
                if(c.getcurrent(rm,gm,bm) == 0) {
                    i++;
                    while(loop > 0 || code.charAt(i) != ']') {
                        if(code.charAt(i) == '[')
                            loop++;
                        if(code.charAt(i) == ']')
                            loop--;
                        i++;
                    }
                }
            }
            else if(code.charAt(i)== ']') {
                if(c.getcurrent(rm,gm,bm) != 0) {
                    i--;
                    while(loop > 0 || code.charAt(i)!= '[') {
                        if(code.charAt(i) == ']')
                            loop++;
                        if(code.charAt(i)== '[')
                            loop--;
                        i--;
                    }
                    i--;
                }
            }
        }
    }
    public Color[][] getCanvas(){
        Color[][] co=new Color[LENGTH][LENGTH];
        for(int r=0;r<LENGTH;r++)
            for(int c=0;c<LENGTH;c++)
                co[r][c]=Canvas[r][c].getColor();
        return co;
    }
    private class mColor{
        int red;
        int green;
        int blue;
        public mColor(int r,int g,int b){
            red=r;
            green=g;
            blue=b;
        }
        public int getcurrent(boolean r,boolean g,boolean b){
            return r?red:g?green:b?blue:-1;
        }
        public void inccurrent(boolean r,boolean g,boolean b){
            if(r)red  =red  >0?0:red  +1;
            if(g)green=green>0?0:green+1;
            if(b)blue =blue >0?0:blue +1;
        }
        public void deccurrent(boolean r,boolean g,boolean b){
            if(r)red  =red  <=0?max:red  -1;
            if(g)green=green<=0?max:green-1;
            if(b)blue =blue <=0?max:blue -1;
        }
        public void setred(){
            red=0xff;
        }
        public Color getColor(){
            return new Color(red,green,blue);
        }
    }
}
