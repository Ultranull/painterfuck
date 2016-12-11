
public class Driver {
    public static void main(String[] args){
        Interpreter inter=new Interpreter();
        new Editor(inter);
        new CanvasPanel(inter);

    }
}
