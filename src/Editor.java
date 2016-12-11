import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.*;

public class Editor {
    private JPanel main;
    private JTabbedPane tabbedPane1;
    private JEditorPane codeInput;
    private JTable table1;
    private JMenuBar menu;

    private Interpreter inter;
    public boolean resetc=false;

    public Editor(Interpreter i){
        inter=i;
        createUIComponents();


        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        }catch (Exception e){
            e.printStackTrace();
            System.exit(0);
        }
        JFrame frame=new JFrame("editor");
        frame.setContentPane(main);
        frame.setJMenuBar(menu);
        frame.setSize(600,600);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void createUIComponents() {
        menu=new JMenuBar();
        JMenuItem save=new JMenuItem("save");
        JMenuItem load=new JMenuItem("load");
        JMenuItem reset=new JMenuItem("reset");
        JMenuItem exit=new JMenuItem("exit");
        JMenuItem run=new JMenuItem("run");
        ActionListener a=e -> {
            switch (e.getActionCommand()){
                case "save":save();break;
                case "load":load();break;
                case "reset":inter.reset();break;
                case "exit":
                    System.exit(0);
                    break;
                case "run":run();break;
            }
        };
        save.addActionListener(a);
        load.addActionListener(a);
        exit.addActionListener(a);
        run.addActionListener(a);
        reset.addActionListener(a);
        menu.add(save);
        menu.add(load);
        menu.add(reset);
        menu.add(run);
        menu.add(exit);

    }
    private void save(){try{saveFile();}catch (Exception e){e.printStackTrace();}}
    private void load(){codeInput.setText(fin());}
    public void saveFile() throws FileNotFoundException {

        JFileChooser chooser = new JFileChooser();
        chooser.showSaveDialog(null);
        String path = chooser.getSelectedFile().getAbsolutePath();
        PrintWriter file = new PrintWriter(new File(path));
        file.write(codeInput.getText());
        file.close();
    }
    public String fin() {
        String ans = "";
        try {
            BufferedReader infile = new BufferedReader(new FileReader(fileReader()));
            String s;
            while ((s = infile.readLine())!=null) {
                ans += s.replaceAll("\\s+","");
            }
            infile.close();
        }catch (Exception ignored){ans="failed";}
        return ans;
    }
    public String fileReader() {
        JFileChooser fileChooser = new JFileChooser();
        int status = fileChooser.showOpenDialog(null);
        if (status == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            return (selectedFile.getParent() + "\\" + selectedFile.getName());
        } else if (status == JFileChooser.CANCEL_OPTION)
            return "canceled";
        return "";
    }
    private void run(){
        inter.interpret(codeInput.getText());
    }

}
