// CutAction.java
// A simple Action that copies text from a PageFrame object.
//

import javax.swing.*;
import java.awt.event.ActionEvent;

public class claActionCut extends AbstractAction {
  JMyFrame jmf;

  public claActionCut(JMyFrame jmfIn) {
    super("", new ImageIcon("icons/cut.gif"));
    jmf = jmfIn;
  }

  public void actionPerformed(ActionEvent ae) {
    if ( jmf.ta == null )  return;
    jmf.ta.cut();
  }
}
