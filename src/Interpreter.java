import java.awt.*;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by usr on 12/9/2016.
 *
 */
public class Interpreter {
    private Color[][] Canvas;
    private Point pointer;
    private LinkedList<String> stack;

    public Interpreter(){
        Canvas=new Color[0xff][0xff];
        Arrays.stream(Canvas).forEach(colors -> Arrays.stream(colors).forEach(color -> color=new Color(0,0,0)));// yeah i could have used for loops
        pointer=new Point(0,0);
        stack=new LinkedList<>();


    }
    public void interpret(String exprsn){//todo: actually write it

    }
}
