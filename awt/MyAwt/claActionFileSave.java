import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


// An action that saves the document to a file
class claActionFileSave extends AbstractAction {
    JMyFrame jmf;
    public claActionFileSave(JMyFrame jmfIn) {
        super("Save", new ImageIcon("icons/save.gif"));
        jmf = jmfIn;
    }

    // Query user for a filename and attempt to open and write the text
    // component’s content to the file.
    public void actionPerformed(ActionEvent ev) {

        if ( jmf.ta == null) return;


        JFileChooser chooser = new JFileChooser();
        if (chooser.showSaveDialog(jmf) !=
                JFileChooser.APPROVE_OPTION)
            return;
        File file = chooser.getSelectedFile();
        if (file == null)
            return;

        FileWriter writer = null;
        try {
            writer = new FileWriter(file);
            jmf.ta.write(writer);
        }
        catch (IOException ex) {
            JOptionPane.showMessageDialog(jmf,
                    "File Not Saved", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException x) {}
            }
        }
    }
}