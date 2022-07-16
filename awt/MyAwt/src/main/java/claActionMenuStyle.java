// PasteAction.java
// A simple Action that pastes text into a PageFrame object.
//

import javax.swing.*;
import java.awt.event.ActionEvent;

public class claActionMenuStyle extends AbstractAction {
  JMyFrame jmf;           // we need MyFrame, then we can access all the component in the myframe.

  public claActionMenuStyle(JMyFrame jmfIn) {    // through this way, get the myframe.
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
