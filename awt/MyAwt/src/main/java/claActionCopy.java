// CopyAction.java
// A simple Action that copies text from a PageFrame object.
//

import javax.swing.*;
import java.awt.event.ActionEvent;

public class claActionCopy extends AbstractAction {
  JMyFrame jmf;

  public claActionCopy(JMyFrame jmfIn) {
    super("Copy", new ImageIcon("icons/copy.gif"));
 //   super("Save", new ImageIcon("src\\copy.gif"));
    jmf = jmfIn;
  }

  public void actionPerformed(ActionEvent ae) {
 //   JMyFrame.ta.copy();   err,  JMyFrame is not an instance.
    if ( jmf.ta == null)  return;
    jmf.ta.copy();

   }
}
