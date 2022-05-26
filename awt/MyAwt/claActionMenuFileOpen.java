// PasteAction.java
// A simple Action that pastes text into a PageFrame object.
//

import javax.swing.*;
import java.awt.event.ActionEvent;

import static javax.swing.JFileChooser.CANCEL_OPTION;
import static javax.swing.JFileChooser.ERROR_OPTION;

public class claActionMenuFileOpen extends AbstractAction {
  JMyFrame jmf;           // we need MyFrame, then we can access all the component in the myframe.

  public claActionMenuFileOpen(JMyFrame jmfIn) {    // through this way, get the myframe.
    super("Open", new ImageIcon("icons/open.gif"));
    jmf = jmfIn;
  }

  public void actionPerformed(ActionEvent ae) {
    System.out.println("menu item clicked");

    JMenuItem sourceCell = (JMenuItem) ae.getSource();
    System.out.println(" MenuItem clicked:"+sourceCell.getActionCommand());

    String[] strFileNameChoose = { "resources\\index.html"};
    String strFileFullNameChoose = "src\\index.html";
    String[] txt = new String[] { "txt", "ini", "bat"};
    String[] audios = new String[] { "au", "aiff", "wav"} ;
    JFileChooser jfc = new JFileChooser();
    jfc.addChoosableFileFilter(new SimpleFileFilter( txt,"text ( *.txt, *.ini, *.bat"));
    jfc.addChoosableFileFilter(new SimpleFileFilter(audios, "audios (*.au) "));

    int option = jfc.showOpenDialog(jmf);
    if ( option == JFileChooser.APPROVE_OPTION) {
      if( jfc.getSelectedFile() !=null ) {
        strFileNameChoose[0] = jfc.getSelectedFile().getName();
        strFileFullNameChoose = jfc.getSelectedFile().getAbsolutePath();
        System.out.println(jfc.getSelectedFile().getAbsolutePath());
      }
      else
        return ;
    }
    else if (option == CANCEL_OPTION) {
      JOptionPane.showMessageDialog(jmf,
              "User cancelled operation. No file was chosen.");
      return;

    } else if (option == ERROR_OPTION) {
      JOptionPane.showMessageDialog(jmf,
              "An error occurred. No file was chosen.");
      return;
    } else {
      JOptionPane.showMessageDialog(jmf, "Unknown operation occurred.");
      return;
    }


    jmf.jltInFrame = new JList( strFileNameChoose);
    jmf.jltInFrame.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    jmf.jspCentre.setLeftComponent(jmf.jltInFrame);

    jmf.ta = new JTextArea();
    jmf.setTaContent(strFileFullNameChoose);
    JScrollPane jsp = new JScrollPane(jmf.ta);
    jmf.jspCentre.setRightComponent(jsp);

    jmf.jlStatusBar.setText("Filename:" + strFileNameChoose[0]);

  }


}
