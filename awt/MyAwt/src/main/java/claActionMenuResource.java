// PasteAction.java
// A simple Action that pastes text into a PageFrame object.
//

import com.sun.management.OperatingSystemMXBean;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;

public class claActionMenuResource extends AbstractAction {
  JMyFrame jmf;           // we need MyFrame, then we can access all the component in the myframe.

  public claActionMenuResource(JMyFrame jmfIn) {    // through this way, get the myframe.
    super();
    jmf = jmfIn;
  }

  public void actionPerformed(ActionEvent ae) {

    jmf.ta = new JTextArea("RESOURCE");

    JMenuItem sourceCell = (JMenuItem) ae.getSource();
    System.out.println(" MenuItem clicked:"+sourceCell.getActionCommand());
    if (sourceCell.getActionCommand() == jmf.MI_RESOURCE_CPU) {

      OperatingSystemMXBean osmxb  =( OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
      double processCpuLoad = osmxb.getProcessCpuLoad();
      double systemCpuLoad = osmxb.getSystemCpuLoad();
      long processCpuTime = osmxb.getProcessCpuTime();
      System.out.println("getProcessCpuLoad:"+processCpuLoad);
      System.out.println("\ngetSystemCpuLoad:"+systemCpuLoad);
      System.out.println("\ngetProcessCpuTime:"+processCpuTime);

      jmf.ta.setText("getProcessCpuLoad:"+processCpuLoad + "\ngetSystemCpuLoad:"+systemCpuLoad +  "\ngetProcessCpuTime:"+processCpuTime );
      jmf.jspCentre.setRightComponent(jmf.ta);
      jmf.jspCentre.setLeftComponent(null);  // clear the left side of jsplitepanel
    }
    else if  (sourceCell.getActionCommand() == jmf.MI_RESOURCE_MEM) {
      // osmxb
      OperatingSystemMXBean osmxb  =( OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

      System.out.println("strtmp = " + jmf.strTmp);
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      long physicalFree = osmxb.getFreePhysicalMemorySize()/1024/1024; // byteToMb;
      long physicalTotal = osmxb.getTotalPhysicalMemorySize() /1024/1024; // byteToMb;
      long physicalUse = physicalTotal - physicalFree ;

      jmf.ta.setText("MEM FREE:" + physicalFree + "\nMEM Used" + physicalUse + "\nMEM total:" + physicalTotal);
      jmf.jspCentre.setRightComponent(jmf.ta);                jmf.jspCentre.setLeftComponent(null);

    }
    else if ( sourceCell.getActionCommand() == jmf.MI_RESOURCE_DISK){
      File[] disks = File.listRoots();

      jmf.ta.setText("");
      for(File file : disks)
      {
        jmf.ta.setText(   jmf.ta.getText() +
                "\n" +   file.getPath() + "    " +
         "\n??????????????? = " + file.getFreeSpace() / 1024 / 1024 / 1024+ "G" +
        "\n???????????? = " + file.getUsableSpace() / 1024 / 1024 / 1024+ "G" +
                "\n????????? = " + file.getTotalSpace() / 1024 / 1024 / 1024+ "G" + "    ");
       }
    }
    else if (sourceCell.getActionCommand() == jmf.MI_RESOURCE_SYS) {
      //  System
      jmf.ta.setText(System.getProperty("os.name") +
       "\ngetenv: " + System.getenv().toString() +
       "\ngetproperties: " + System.getProperties().toString() ) ;

      // thread
      // ??????????????????
      ThreadGroup parentThread;
      int totalThread = 0;
      for (parentThread = Thread.currentThread().getThreadGroup(); parentThread
              .getParent() != null; parentThread = parentThread.getParent()) {
        totalThread = parentThread.activeCount();
      }
      jmf.ta.setText( jmf.ta.getText()+
              "\n????????????:" + totalThread);
    }
    else if ( sourceCell.getActionCommand() == jmf.MI_RESOURCE_RUNTIME){
      //   Runtime
      Runtime rt = Runtime.getRuntime();
      System.out.println(rt.toString());
      try {
        Process p = rt.exec("notepad");

      } catch (IOException e )
      { System.out.println("exec error");}
      System.out.println("exec after");

      // ??????????????????????????????
      long vmFree = 0;
      long vmUse = 0;
      long vmTotal = 0;
      long vmMax = 0;
      int byteToMb = 1024 * 1024;
      vmTotal = rt.totalMemory() / byteToMb;
      vmFree = rt.freeMemory() / byteToMb;
      vmMax = rt.maxMemory() / byteToMb;
      vmUse = vmTotal - vmFree;
      jmf.ta.setText("JVM???????????????????????????" + vmUse + " MB" +
       "\nJVM???????????????????????????" + vmFree + " MB" +
       "\nJVM?????????????????????" + vmTotal + " MB" +
       "\nJVM?????????????????????" + vmMax + " MB");
    }

    jmf.jspCentre.setRightComponent(jmf.ta);
    jmf.jspCentre.setLeftComponent(null);  // clear the left side of jsplitepanel


  }

}
