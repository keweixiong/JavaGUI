import javax.swing.*;
import javax.swing.text.DefaultEditorKit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.FileReader;
import java.io.IOException;



public class JMyFrame extends JFrame{
    public static int FRAME_WIDTH = 1200;
    public static int FRAME_HEIGHT = 800;

    public static String MN_FILE = "File";
    public static String MN_HELP = "Help";
    public static String MN_OPTION = "Option";
    public static String MN_STYLE ="Style";
    public static String MN_RESOURCE = "Resource";
    public static String MN_JCOMPONENT = "JCOMPONENT";

    public static String MI_OPEN = "Open";
    public static String MI_SAVE = "Save";

    public static String MI_COPY = "Copy";

    public static String MI_SETUP = "Setup";
    public static String MI_ABOUT = "About";

    public static String MI_JLIST = "Jlist";

    public static String MI_STYLE_METAL = "Metal";
    public static String MI_STYLE_MOTIF = "Motif";
    public static String MI_STYLE_WINDOWS = "Windows";


    public static String MI_RESOURCE_CPU = "CPU";
    public static String MI_RESOURCE_MEM = "MEM";
    public static String MI_RESOURCE_DISK = "DISK";
    public static String MI_RESOURCE_RUNTIME = "RUNTIME";
    public static String MI_RESOURCE_SYS = "SYS";


    // all the var needed to be communicated should be put here, instead of init()
    // coz the var in init() can't be accessed by other classes.
    // there r all public components, specific component should be defined in self scope.
    claActionListenerBtn lsnBtn = new claActionListenerBtn();
    claActionListenerMi lsnMi = new claActionListenerMi();


    JToolBar jtbTop = new JToolBar();
    JSplitPane jspCentre = new JSplitPane();
    JPanel jpBottom = new JPanel();

    public  JList jltInFrame = new JList();
    public  JTextArea ta = new JTextArea();


    JLabel jlStatusBar = new JLabel();

    int iDelay = 1000;
    Timer tm1 =new Timer(iDelay,null);
    JButton btnClock = new JButton("0");
    String strTmp;

    claActionFileSave actionFileSave = new claActionFileSave( this);  // both menu and toolbar will use it.
    claActionMenuFileOpen actionFileOpen = new claActionMenuFileOpen( this);  // both menu and toolbar will use it.

    public static void main(String [] args)   {
        SwingUtilities.invokeLater((new Runnable() {
            @Override
            public void run() {
                new JMyFrame();
            }

        }));

    }

    public JMyFrame()   {
        super();

        init();

        setTitle("JMyFrame");
        setDefaultCloseOperation((JFrame.EXIT_ON_CLOSE));
        //addWindowListener(new WindowAdapter( ) {
        //            public void windowClosing(WindowEvent e) {
        //                System.exit(0);
        //            }
        //        });
        setSize(FRAME_WIDTH,FRAME_HEIGHT);
        setVisible(true);
    }

    public void init() {

        System.out.println("begin init()");

        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        initMenu();

        initToolBar();
        initCentre();
        initBottomText();

        init1();

        // put them together
        cp.add(jtbTop, BorderLayout.NORTH);
        cp.add(jspCentre, BorderLayout.CENTER);
        cp.add(jpBottom, BorderLayout.SOUTH);

        // other test:
        claListenerTimer listenerTimer = new claListenerTimer();
        tm1.addActionListener(listenerTimer);
        tm1.start();





    }


    public void init1() {
        claContact contactOne = new claContact("name88", "13800001233","name1@abc.com","address");

         System.out.println(" ---------- random --");
       contactOne.insert();
//      contactOne.delete( );
        contactOne.update();
        contactOne.locate();


    }

    public void initMenu() {
        JMenuBar mb1 = new JMenuBar();

        JMenu mn1 = new JMenu(MN_FILE);
        JMenu mn2 = new JMenu(MN_OPTION);
        JMenu mn3 = new JMenu(MN_HELP);
        JMenu mn4 = new JMenu(MN_JCOMPONENT);
        JMenu mn5 = new JMenu(MN_STYLE);
        JMenu mn6 = new JMenu(MN_RESOURCE);

        JMenuItem mi11 = new JMenuItem(MI_OPEN);
        JMenuItem mi12 = new JMenuItem(MI_SAVE);

        JMenuItem mi13 = new JMenuItem(MI_COPY);

        JMenuItem mi21 = new JMenuItem(MI_SETUP);
        JMenuItem mi31 = new JMenuItem(MI_ABOUT);

        JMenuItem mi41 = new JMenuItem(MI_JLIST);

        JMenuItem mi51 = new JMenuItem( MI_STYLE_METAL);
        JMenuItem mi52 = new JMenuItem( MI_STYLE_MOTIF);
        JMenuItem mi53 = new JMenuItem( MI_STYLE_WINDOWS);

        JMenuItem mi61 = new JMenuItem( MI_RESOURCE_CPU);
        JMenuItem mi62 = new JMenuItem( MI_RESOURCE_MEM);
        JMenuItem mi63 = new JMenuItem( MI_RESOURCE_DISK);
        JMenuItem mi64 = new JMenuItem( MI_RESOURCE_RUNTIME);
        JMenuItem mi65 = new JMenuItem( MI_RESOURCE_SYS);

        // Define the KEY here
        KeyStroke altO = KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.ALT_DOWN_MASK);
        KeyStroke altA = KeyStroke.getKeyStroke(KeyEvent.VK_A,InputEvent.ALT_DOWN_MASK);
        KeyStroke altS = KeyStroke.getKeyStroke(KeyEvent.VK_S,InputEvent.ALT_DOWN_MASK);

        mi11.setAccelerator(altO);
        mi31.setAccelerator(altA);



        // Assembly
        mn1.add(mi11); //mn1.add(mi12);


        mn1.add(actionFileSave);
        mn1.addSeparator();
        mn1.add(mi13);
        mn1.add(ta.getActionMap().get(DefaultEditorKit.cutAction));
        mn1.add(ta.getActionMap().get(DefaultEditorKit.pasteAction));
        mn1.add(ta.getActionMap().get(DefaultEditorKit.selectAllAction));
        mn1.add(new ExitAction());

        mn2.add(mi21);
        mn3.add(mi31);
        mn4.add(mi41);
        mn5.add(mi51);mn5.add(mi52); mn5.add(mi53);
        mn6.add(mi61);mn6.add(mi62);mn6.add(mi63); mn6.add(mi64); mn6.add(mi65);

        // Assembly Menubar
        mb1.add(mn1); mb1.add(mn2);mb1.add(mn3);mb1.add(mn4);mb1.add(mn6); mb1.add(mn5);
        // mount the Menubar to Frame
        this.setJMenuBar(mb1);



        claActionMenuFileOpen actMenuFileOpen = new claActionMenuFileOpen(this);
        mi11.addActionListener(actMenuFileOpen);


        claActionCopy actMenuFileCopy = new claActionCopy(this);
        mi13.addActionListener(actMenuFileCopy);
        // we have not add listener to mi12, coz we do it another way by adding Action to Menu.

        // add the listener with listener or Action
        lsnMi = new claActionListenerMi();
        mi21.addActionListener(lsnMi);
        mi31.addActionListener(lsnMi);

        claMenuActionList actMenuJComponentJlist = new claMenuActionList( this);
        mi41.addActionListener(actMenuJComponentJlist);

        claActionMenuStyle actionMenuStyle = new claActionMenuStyle(this);
        mi51.addActionListener(actionMenuStyle);
        mi52.addActionListener(actionMenuStyle);
        mi53.addActionListener(actionMenuStyle);

        claActionMenuResource actionMenuResource = new claActionMenuResource(this);
        mi61.addActionListener(actionMenuResource);
        mi62.addActionListener(actionMenuResource);
        mi63.addActionListener(actionMenuResource);
        mi64.addActionListener(actionMenuResource);
        mi65.addActionListener(actionMenuResource);
    }

    public void initToolBar() {

        jtbTop = new JToolBar("jtoolbar");

        jtbTop.add(actionFileOpen).setText("Open");   // coz the Action is actually BUTTON
        jtbTop.add(actionFileSave).setText("Save");   // coz the Action is actually BUTTON


        jtbTop.add(new JToolBar.Separator());          // or jtbTop.addSeparator();

        jtbTop.add(new claActionCut(this)).setText("Cut");
        jtbTop.add(new claActionCopy(this)).setText("Copy");
        jtbTop.add(new claActionPaste(this)).setText("Paste");

    }

    public void initCentre() {
        jspCentre = new JSplitPane();
        jspCentre.setLeftComponent(null);

    }
    public void initBottomText() {

        jpBottom = new JPanel();
        jpBottom.setLayout(new GridLayout(1,4,2,2));

        // this botton is not used by anywhere else,so just created the instance here
        // if we need to operated by any menu or control button, then the button should be defined as
        // one of the property of the MyFrame. just like jl1, btnClock
        JButton btnVoid = new JButton(" void ");
        lsnBtn = new claActionListenerBtn();
        btnVoid.addActionListener(lsnBtn);

        jlStatusBar = new JLabel("status bar");

        jpBottom.add(jlStatusBar);
        jpBottom.add(btnVoid);
        jpBottom.add(btnClock);
    }

    public void workOntime() {
        int i = Integer.parseInt(btnClock.getText());
        btnClock.setText(String.valueOf(i = i+iDelay/1000));
    }
    public void setTaContent(String filenameIn) {

        String t1 = Thread.currentThread().getContextClassLoader().getResource("templates/").getPath(); // getPath can make URL - > normal pathname+filename
        String filename = t1+filenameIn;
        System.out.println("loading file: "+filename);

        FileReader fr = null;
        try {
            fr = new FileReader(filename);
            ta.read(fr, null);
        }
        catch (Exception e) {
            System.err.println("Could not load page: " + filename);
            JOptionPane.showMessageDialog(JMyFrame.this,
                    "File Not Found", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        finally {
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException x) {}
            }
        }


    }


    private class claActionListenerBtn implements ActionListener {  // here is implements instead of extend
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton sourceCell = (JButton) e.getSource();
            System.out.print(sourceCell.getActionCommand()+" ");

            workOntime();

        }
    }
    private class claActionListenerMi implements ActionListener {  // here is implements instead of extend
        @Override
        public void actionPerformed(ActionEvent e) {
            JMenuItem sourceCell = (JMenuItem) e.getSource();
            System.out.println(" MenuItem clicked:"+sourceCell.getActionCommand());
            if (sourceCell.getActionCommand() == MI_ABOUT)      {
                JOptionPane.showMessageDialog(getRootPane()," about messages", " my title", JOptionPane.INFORMATION_MESSAGE);
            }
            else if ( sourceCell.getActionCommand() == MI_RESOURCE_MEM) {

            }

        }
    }
    private class claListenerTimer  implements ActionListener{
        public void actionPerformed(ActionEvent e) {
 //         if ( e.getSource() == tm1 ) {

            workOntime();
            //        }
        }
    }
    // A very simple exit action
    public class ExitAction extends AbstractAction {
        public ExitAction() { super("Exit"); }
        public void actionPerformed(ActionEvent ev) { System.exit(0); }
    }

}
