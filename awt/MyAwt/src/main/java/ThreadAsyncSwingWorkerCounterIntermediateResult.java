import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutionException;

// async call for doing something and during the process, continuously get the intermdediate result back within one SwingWorker thread

/** Test SwingWorker on the counter application with a compute-intensive task */
@SuppressWarnings("serial")
public class ThreadAsyncSwingWorkerCounterIntermediateResult extends JPanel {
    // For counter
    private JTextField tfCount;
    private int count = 0;
    // For SwingWorker
    JButton btnStartWorker;   // to start the worker
    private JLabel lblWorker; // for displaying the result

    /** Constructor to setup the GUI components */
    public ThreadAsyncSwingWorkerCounterIntermediateResult() {
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

        /** Create a SwingWorker instance to run a compute-intensive task */
        final SwingWorker<String, String> worker = new SwingWorker<String, String>() {

            /** Schedule a compute-intensive task in a background thread */
            @Override
            protected String doInBackground() throws Exception {
                long sum = 0;
                for (int iTimes = 0; iTimes < 15; iTimes++) {
                    for (int number = 0; number < 1000000000; ++number) {
                        sum += number;
                        publish(sum + ""); // Send "every" intermediate result to process()
                        // You might not publish every intermediate result
                    }
                }
                return sum + "";
            }

            /** Run in event-dispatching thread after doInBackground() completes */
            @Override
            protected void done() {
                try {
                    // Use get() to get the result of doInBackground()
                    String finalResult = get();
                    // Display the result in the label (run in EDT)
                    lblWorker.setText("Final Result is " + finalResult);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }

            /** Run in event-dispatching thread to process intermediate results
             send from publish(). */
            @Override
            protected void process(java.util.List<String> chunks) {
                // Get the latest result from the list
                String latestResult = chunks.get(chunks.size() - 1);// we only get the top one, ignore the older ones
                lblWorker.setText("Result is " + latestResult);
            }
        };

        btnStartWorker = new JButton("Start Worker");
        add(btnStartWorker);
        btnStartWorker.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                worker.execute();  // start the worker thread
                lblWorker.setText("  Running...");
                btnStartWorker.setEnabled(false);  // SwingWorker can only run once
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
                frame.setContentPane(new ThreadAsyncSwingWorkerCounterIntermediateResult());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(300, 150);
                frame.setVisible(true);
            }
        });
    }
}