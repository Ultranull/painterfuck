import java.awt.*;

 class Interpreter {
    private mColor[][] Canvas;
    private Point pointer;
    private boolean rm   = true;
    private boolean gm   = false;
    private boolean bm   = false;
    private int loop     = 0;
    private int max      = 0xff-1;
     int LENGTH    = 0xff;

     Interpreter(){
        Canvas=new mColor[LENGTH][LENGTH];
        for(int r=0;r<LENGTH;r++)
            for(int c=0;c<LENGTH;c++)
                Canvas[r][c]=new mColor(0,0,0);
        pointer=new Point(0,0);

    }
     void interpret(String code){
         label:
         for (int i = 0; i < code.length(); i++) {
             mColor c = Canvas[pointer.x][pointer.y];
             switch (code.charAt(i)) {
                 case '@':
                     break label;
                 case 'r':
                     rm = true;
                     gm = false;
                     bm = false;
                     break;
                 case 'g':
                     rm = false;
                     gm = true;
                     bm = false;
                     break;
                 case 'b':
                     rm = false;
                     gm = false;
                     bm = true;
                     break;
                 case '>':
                     pointer.x += 1;
                     if (pointer.x >= LENGTH) {
                         pointer.x = 0;
                     }
                     break;
                 case '<':
                     pointer.x -= 1;
                     if (pointer.x <= 0) {
                         pointer.x = LENGTH - 1;
                     }
                     break;
                 case '^':
                     pointer.y -= 1;
                     if (pointer.y <= 0) {
                         pointer.y = LENGTH - 1;
                     }
                     break;
                 case 'v':
                     pointer.y += 1;
                     if (pointer.y >= LENGTH) {
                         pointer.y = 0;
                     }
                     break;
                 case '+':
                     c.inccurrent(rm, gm, bm);
                     break;
                 case '-':
                     c.deccurrent(rm, gm, bm);
                     break;
                 case '[':
                     if (c.getcurrent(rm, gm, bm) == 0) {
                         i++;
                         while (loop > 0 || code.charAt(i) != ']') {
                             if (code.charAt(i) == '[')
                                 loop++;
                             if (code.charAt(i) == ']')
                                 loop--;
                             i++;
                         }
                     }
                     break;
                 case ']':
                     if (c.getcurrent(rm, gm, bm) != 0) {
                         i--;
                         while (loop > 0 || code.charAt(i) != '[') {
                             if (code.charAt(i) == ']')
                                 loop++;
                             if (code.charAt(i) == '[')
                                 loop--;
                             i--;
                         }
                         i--;
                     }
                     break;
             }
         }
    }
     Color[][] getCanvas(){
        Color[][] co=new Color[LENGTH][LENGTH];
        for(int r=0;r<LENGTH;r++)
            for(int c=0;c<LENGTH;c++)
                co[r][c]=Canvas[r][c].getColor();
        return co;
    }
     void reset(){
        for(int r=0;r<LENGTH;r++)
            for(int c=0;c<LENGTH;c++)
                Canvas[r][c]=new mColor(0,0,0);
        pointer=new Point(0,0);

    }
    private class mColor{
        int red;
        int green;
        int blue;
         mColor(int r,int g,int b){
            red=r;
            green=g;
            blue=b;
        }
         int getcurrent(boolean r,boolean g,boolean b){
            return r?red:g?green:b?blue:-1;
        }
         void inccurrent(boolean r,boolean g,boolean b){
            if(r)red  =red  >0?0:red  +1;
            if(g)green=green>0?0:green+1;
            if(b)blue =blue >0?0:blue +1;
        }
         void deccurrent(boolean r,boolean g,boolean b){
            if(r)red  =red  <=0?max:red  -1;
            if(g)green=green<=0?max:green-1;
            if(b)blue =blue <=0?max:blue -1;
        }
         Color getColor(){
            return new Color(red,green,blue);
        }
    }
}
//well howdy partner