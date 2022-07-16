import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutionException;

import static java.lang.Thread.sleep;

// async call for doing something and when finished, get the result back within one SwingWorker thread

/** Test SwingWorker on the counter application with a compute-intensive task */
// A instance of SwingWorker is designed to run only once. You cannot restart the instance. You need to create a new instance for run the task again.
@SuppressWarnings("serial")
public class ThreadAsyncSwingWorkerCounter extends JPanel {
    // For counter
    private JTextField tfCount;
    private int count = 0;
    // For SwingWorker
    JButton btnStartWorker;   // to start the worker
    private JLabel lblWorker; // for displaying the result

    /** Constructor to setup the GUI components */
    public ThreadAsyncSwingWorkerCounter() {
        setLayout(new FlowLayout());

        add(new JLabel("Counter"));
        tfCount = new JTextField("0", 10);
        tfCount.setEditable(false);
        add(tfCount);

        JButton btnCount = new JButton("Count");
        add(btnCount);
        btnCount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ++count;
                tfCount.setText(count + "");
            }
        });

        /** Create a SwingWorker instance to run a compute-intensive task
         Final result is String, no intermediate result (Void) */
        final SwingWorker<String, Void> worker = new SwingWorker<String, Void>() {
            /** Schedule a compute-intensive task in a background thread */
            @Override
            protected String doInBackground() throws Exception {        // 1 define the worker
                // Sum from 1 to a large n
                long sum = 0;
                for (int iTimes = 0; iTimes < 15; iTimes++) {
                    for (int number = 1; number < 1000000000; ++number) {
                        // need to sleep(sometime) to release , I think

                        sum += number;
                    }
                    sleep(10);  // release resource, otherwise, still hung over
                }
                return sum + "";
            }

            /** Run in event-dispatching thread after doInBackground() completes */
            @Override
            protected void done() {                                       // 2 define the getback
                try {
                    // Use get() to get the result of doInBackground()
                    String result = get();
                    // Display the result in the label (run in EDT)
                    lblWorker.setText("Result is " + result);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        };

        btnStartWorker = new JButton("Start Worker");
        add(btnStartWorker);
        btnStartWorker.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                worker.execute();                 // start the worker thread   3
                lblWorker.setText("  Running...");
                btnStartWorker.setEnabled(false); // Each instance of SwingWorker run once
            }
        });
        lblWorker = new JLabel("  Not started...");
        add(lblWorker);

    }

    /** The entry main() method */
    public static void main(String[] args) {
        // Run the GUI construction in the Event-Dispatching thread for thread-safety
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("SwingWorker Test");
                frame.setContentPane(new ThreadAsyncSwingWorkerCounter());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(300, 150);
                frame.setVisible(true);
            }
        });
    }
}
