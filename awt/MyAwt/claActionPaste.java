// PasteAction.java
// A simple Action that pastes text into a PageFrame object.
//

import javax.swing.*;
import java.awt.event.ActionEvent;

public class claActionPaste extends AbstractAction {
  JMyFrame jmf;

  public claActionPaste(JMyFrame jmfIn) {
    super("", new ImageIcon("icons/paste.gif"));
    jmf = jmfIn;
  }

  public void actionPerformed(ActionEvent ae) {
    if ( jmf.ta == null ) return;
     jmf.ta.paste();
  }
}
