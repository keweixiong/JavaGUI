import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.*;
import java.net.Socket;


public class JFContact extends JFrame{
    public static int FRAME_WIDTH = 1200;
    public static int FRAME_HEIGHT = 800;

    public static String MN_CONTACT = "Contact";
    public static String MN_HELP = "Help";
    public static String MN_STYLE ="Style";


    public static String MI_I = "Insert";
    public static String MI_D = "Delete";
    public static String MI_U = "Update";
    public static String MI_F = "Find";

    public static String MI_ABOUT = "About";


    public static String MI_STYLE_METAL = "Metal";
    public static String MI_STYLE_MOTIF = "Motif";
    public static String MI_STYLE_WINDOWS = "Windows";


     claActionListenerMi lsnMi = new claActionListenerMi();   // general MenuItem action

    // 3 parts
    JToolBar jtbTop = new JToolBar();
    JSplitPane jspCentre = new JSplitPane();
    JPanel jpBottom = new JPanel();

    JLabel jlStatusBar = new JLabel();

    JLabel jlName = new JLabel("Name");
    JLabel jlMobileNo = new JLabel("MobileNo");
    JLabel jlEmail = new JLabel("Email");
    JLabel jlAddress = new JLabel("Address");

    JTextField jtName = new JTextField("",20);
    JTextField jtMobileNo = new JTextField("",11);
    JTextField jtEmail = new JTextField("",40);
    JTextField jtAddress = new JTextField("",40);



    JButton jbtnSummit = new JButton("Summit");

    char cAction = '?';
    public static void main(String [] args)   {
        SwingUtilities.invokeLater((new Runnable() {
            @Override
            public void run() {
                new JFContact();
            }

        }));

    }

    public JFContact()   {
        super();

        init();

        setTitle("JFContact");
        setDefaultCloseOperation((JFrame.EXIT_ON_CLOSE));

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

    }


    public void init1() {


    }

    public void initMenu() {
        JMenuBar mb1 = new JMenuBar();

        JMenu mn1 = new JMenu(MN_CONTACT);
         JMenu mn3 = new JMenu(MN_HELP);
         JMenu mn5 = new JMenu(MN_STYLE);

        JMenuItem mi11 = new JMenuItem(MI_I);
        JMenuItem mi12 = new JMenuItem(MI_D);
        JMenuItem mi13 = new JMenuItem(MI_U);
        JMenuItem mi14 = new JMenuItem(MI_F);

         JMenuItem mi31 = new JMenuItem(MI_ABOUT);


        JMenuItem mi51 = new JMenuItem( MI_STYLE_METAL);
        JMenuItem mi52 = new JMenuItem( MI_STYLE_MOTIF);
        JMenuItem mi53 = new JMenuItem( MI_STYLE_WINDOWS);


        // Define the KEY here
        KeyStroke altI = KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.ALT_DOWN_MASK);
        KeyStroke altD = KeyStroke.getKeyStroke(KeyEvent.VK_D,InputEvent.ALT_DOWN_MASK);
        KeyStroke altU = KeyStroke.getKeyStroke(KeyEvent.VK_U,InputEvent.ALT_DOWN_MASK);
        KeyStroke altF = KeyStroke.getKeyStroke(KeyEvent.VK_F,InputEvent.ALT_DOWN_MASK);
        KeyStroke altA = KeyStroke.getKeyStroke(KeyEvent.VK_A,InputEvent.ALT_DOWN_MASK);

        mi11.setAccelerator(altI);
        mi12.setAccelerator(altD);
        mi13.setAccelerator(altU);
        mi14.setAccelerator(altF);

        mi31.setAccelerator(altA);

        mi11.addActionListener(new claActionContactInsert(this));// way1, MenuItem add action
        mi12.addActionListener(new claActionContactDelete(this));
        mi13.addActionListener(new claActionContactUpdate(this));
        mi14.addActionListener(new claActionContactFind(this));
        // Assembly
        mn1.add(mi11);
        mn1.add(mi12);
        mn1.addSeparator();
        mn1.add(mi13);
        mn1.add(mi14);
        mn1.add(new ExitAction());     // way2 , add action directly to Menu

         mn3.add(mi31);
         mn5.add(mi51);mn5.add(mi52); mn5.add(mi53);

        // Assembly Menubar
        mb1.add(mn1);  mb1.add(mn3);   mb1.add(mn5);
        // mount the Menubar to Frame
        this.setJMenuBar(mb1);

        // add the listener with listener or Action
        lsnMi = new claActionListenerMi();    // general MenuItem action
        mi31.addActionListener(lsnMi);

        claActionMenuStyle actionMenuStyle = new claActionMenuStyle(this);// specific MenuItem action
        mi51.addActionListener(actionMenuStyle);
        mi52.addActionListener(actionMenuStyle);
        mi53.addActionListener(actionMenuStyle);

    }

    public void initToolBar() {

        jtbTop = new JToolBar("jtoolbar");

        jtbTop.add(new claActionContactInsert(this)).setText("Insert");
        jtbTop.add(new claActionContactDelete(this)).setText("Delete");
        jtbTop.add(new claActionContactUpdate(this)).setText("Update");
        jtbTop.add(new JToolBar.Separator());          // or jtbTop.addSeparator();
        jtbTop.add(new claActionContactFind(this)).setText("Find");


    }

    public void initCentre() {
        jspCentre = new JSplitPane();
        jspCentre.setLeftComponent(null);
        JPanel jpHolder = new JPanel();
        jspCentre.setRightComponent(jpHolder);

        jtName.setHorizontalAlignment(JTextField.LEFT);
        jtMobileNo.setHorizontalAlignment(JTextField.LEFT);
        jtEmail.setHorizontalAlignment(JTextField.LEFT);
        jtAddress.setHorizontalAlignment(JTextField.LEFT);

        jpHolder.add(jlName); jpHolder.add(jtName);
        jpHolder.add(jlMobileNo);jpHolder.add((jtMobileNo));
        jpHolder.add(jlEmail);jpHolder.add(jtEmail);
        jpHolder.add(jlAddress);jpHolder.add(jtAddress);

        jbtnSummit.setVisible(false);
        jpHolder.add(jbtnSummit);
        claActionListenerSummit lsnActionSummit = new claActionListenerSummit();
        jbtnSummit.addActionListener(lsnActionSummit);
    }
    public void initBottomText() {

        jpBottom = new JPanel();
        jpBottom.setLayout(new GridLayout(1,4,2,2));


        jlStatusBar = new JLabel("status bar");
        jpBottom.add(jlStatusBar);
      }

    private class claActionListenerSummit implements ActionListener {  // here is implements instead of extend
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton sourceCell = (JButton) e.getSource();
            System.out.print(sourceCell.getActionCommand()+" ");
            sourceCell.setVisible(false);

            jlName.setVisible(true);
            jlEmail.setVisible(true);
            jlAddress.setVisible(true);

            jtName.setVisible(true);
            jtEmail.setVisible(true);
            jtAddress.setVisible(true);


            claContact contactOne = new claContact(jtName.getText().trim(),
                    jtMobileNo.getText().trim(),
                    jtEmail.getText().trim(),
                    jtAddress.getText().trim());

            System.out.println(" ---------- summit --");
            /*
            if (cAction == 'I') {
                contactOne.insert();
            } else if ( cAction == 'D') {
                contactOne.delete( );
            } else if ( cAction == 'U') {
                contactOne.update();
            } else if ( cAction == 'F') {
                contactOne.find();
            }else {
                System.out.println("action not know, maybe err");
                jlStatusBar.setText("action not know, may");
            }


             */
            Socket socket = null;
            BufferedReader socketReader = null;
            BufferedWriter socketWriter = null;
            try {
                // Create a socket that will connect to localhost at port 12900.
                // Note that the server
                // must also be running at localhost and 12900.
                socket = new Socket("localhost", 12900);

                System.out.println("Started client socket at "
                        + socket.getLocalSocketAddress());

                // Create a buffered reader and writer using the socket's
                // input and output streams
                socketReader = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                socketWriter = new BufferedWriter(
                        new OutputStreamWriter(socket.getOutputStream()));

                // Create a buffered reader for user's input
                BufferedReader consoleReader
                        = new BufferedReader(new InputStreamReader(System.in));

                String promptMsg = "Please continue:";
                String outMsg = null;

                //System.out.print(promptMsg);

                    // Add a new line to the message to the server,
                    // because the server reads one line at a time.

                System.out.println("contact.toString()=" + contactOne.toString());
                    JSONObject json = new JSONObject();

                    json.put("Action",cAction);
                    json.put("MobileNo",contactOne.MobileNo);

                    if ( cAction != 'F')
                    {
                        json.put("Name",contactOne.Name);
                        json.put("Email", contactOne.Email);
                        json.put("Address", contactOne.Address);
                    }

                    outMsg = json.toString();
                    socketWriter.write(outMsg);
                    System.out.println("to Server: " + outMsg);

                    socketWriter.write("\n");
                    socketWriter.flush();

                    // Read and display the message from the server
                    String inMsg = socketReader.readLine();
                    json = JSON.parseObject(inMsg);
                    System.out.println("from Server: " + inMsg);
                    String strRc =  json.getString("ReturnCode").trim();
                    String strRm = json.getString("ReturnMesg").trim();
                    if ( (cAction == 'F') && ( strRc.equals("0000"))) {


                        jtName.setText(((String) json.get("data.Name")).trim());
                        jtMobileNo.setText(((String) json.get("data.MobileNo")).trim());
                        jtEmail.setText(((String) json.get("data.Email")).trim());
                        jtAddress.setText(((String) json.get("data.Address")).trim());
                        System.out.println("from server, json: Name="+json.get("data.Name") +
                                "MobileNo="+json.get("data.MobileNo") +
                                "Email" + json.get("data.Email") +
                                "Address = "+json.get("data.Address"));
                    }
                    else {
                        jtName.setText("");
                        jtEmail.setText("");
                        jtAddress.setText("");
                }
                    jlStatusBar.setText( (String) json.get("ReturnCode") +":"+ (String) json.get("ReturnMesg"));

                    System.out.println(); // Print a blank line
                    System.out.print(promptMsg);

            } catch (IOException e2) {
                e2.printStackTrace();
            } finally {
                // Finally close the socket
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
            }

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


        }
    }

    // A very simple exit action
    public class ExitAction extends AbstractAction {
        public ExitAction() { super("Exit"); }
        public void actionPerformed(ActionEvent ev) { System.exit(0); }
    }


    public class claActionMenuStyle extends AbstractAction {
        JFContact jmf;           // we need MyFrame, then we can access all the component in the myframe.

        public claActionMenuStyle(JFContact jmfIn) {    // through this way, get the myframe.
            jmf = jmfIn;
        }

        public void actionPerformed(ActionEvent e) {
            System.out.println("menu item clicked");

            JMenuItem sourceCell = (JMenuItem) e.getSource();
            System.out.println(" MenuItem clicked:"+sourceCell.getActionCommand());
            String lnfName = null;

//    if (e.getActionCommand( ).equals("Mac")) {
//      lnfName = "com.apple.mrj.swing.MacLookAndFeel";
            if (e.getActionCommand( ).equals("Metal")) {
                lnfName = "javax.swing.plaf.metal.MetalLookAndFeel";
            } else if (e.getActionCommand( ).equals("Motif")) {
                lnfName = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
            } else if (e.getActionCommand( ).equals("Windows")) {
                lnfName = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
            } else {
                System.err.println("Unrecognized L&F request action: " +
                        e.getActionCommand( ));
                return;
            }

            try {
                UIManager.setLookAndFeel(lnfName);
                SwingUtilities.updateComponentTreeUI(jmf);
            }
            catch (UnsupportedLookAndFeelException ex1) {
                System.err.println("Unsupported LookAndFeel: " + lnfName);
            }
            catch (ClassNotFoundException ex2) {
                System.err.println("LookAndFeel class not found: " + lnfName);
            }
            catch (InstantiationException ex3) {
                System.err.println("Could not load LookAndFeel: " + lnfName);
            }
            catch (IllegalAccessException ex4) {
                System.err.println("Cannot use LookAndFeel: " + lnfName);
            }
        }

    }
    public class claActionMenuFileOpen extends AbstractAction {
        JFContact jmf;           // we need MyFrame, then we can access all the component in the myframe.

        public claActionMenuFileOpen(JFContact jmfIn) {    // through this way, get the myframe.
            super("Open", new ImageIcon("icons/open.gif"));
            jmf = jmfIn;
        }

        public void actionPerformed(ActionEvent ae) {
            System.out.println("menu item clicked");

            JMenuItem sourceCell = (JMenuItem) ae.getSource();
            System.out.println(" MenuItem clicked:"+sourceCell.getActionCommand());


        }


    }
    public class claActionContactInsert extends AbstractAction {
        JFContact jmf;
        public claActionContactInsert(JFContact jmfIn) {
            super("", new ImageIcon("icons/cut.gif"));
            jmf = jmfIn;
        }
        public void actionPerformed(ActionEvent ae) {
            cAction = 'I';
            jbtnSummit.setText("Summit Insert");
            jbtnSummit.setVisible(true);
            jlStatusBar.setText("");
        }
    }
    public class claActionContactDelete extends AbstractAction {
        JFContact jmf;
        public claActionContactDelete(JFContact jmfIn) {
            super("", new ImageIcon("icons/cut.gif"));
            jmf = jmfIn;
        }
        public void actionPerformed(ActionEvent ae) {
            cAction = 'D';
            jbtnSummit.setText("Summit Delete");
            jbtnSummit.setVisible(true);
            jlStatusBar.setText("");
        }
    }
    public class claActionContactUpdate extends AbstractAction {
        JFContact jmf;
        public claActionContactUpdate(JFContact jmfIn) {
            super("", new ImageIcon("icons/cut.gif"));
            jmf = jmfIn;
        }
        public void actionPerformed(ActionEvent ae) {
            cAction = 'U';
            jbtnSummit.setText("Summit Update");
            jbtnSummit.setVisible(true);
            jlStatusBar.setText("");
        }
    }
    public class claActionContactFind extends AbstractAction {
        JFContact jmf;
        public claActionContactFind(JFContact jmfIn) {
            super("", new ImageIcon("icons/cut.gif"));
            jmf = jmfIn;
        }
        public void actionPerformed(ActionEvent ae) {
            cAction = 'F';
            jbtnSummit.setText("Summit Find");
            jlStatusBar.setText("");

            jbtnSummit.setVisible(true);

            jlName.setVisible(false);
            jlEmail.setVisible(false);
            jlAddress.setVisible(false);
            jtName.setVisible(false);
            jtEmail.setVisible(false);
            jtAddress.setVisible(false);

        }
    }
}
