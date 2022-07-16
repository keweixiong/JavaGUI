// PasteAction.java
// A simple Action that pastes text into a PageFrame object.
//

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;

public class claMenuActionList  extends AbstractAction {
  JMyFrame jmf;           // we need MyFrame, then we can access all the component in the myframe.
  String[] pages = {"index.html", "page1.html", "page2.html"};
  JList jltJComponent = new JList( pages);

  public claMenuActionList(JMyFrame jmfIn) {    // through this way, get the myframe.
    super();
    this.jmf = jmfIn;
  }

  public void actionPerformed(ActionEvent ae) {
    System.out.println("menu item clicked");




    //Java：xxx is not an enclosing class
    //1. 错误原因
    //该错误一般出现在对内部类进行实例化时，例如
    //
    // public class A{
    //      public class B{ }
    // }
    //此时B是A的内部类，如果我们要使用如下语句实例化一个B类的对象：
    //
    //A.B b = new A.B()
    //则会报错：B is not an enclosing class
    //
    //2. 解决办法
    //方法一：若要创建内部类的实例，首先要创建外部类的实例；
    //
    //A a = new A();
    //A.B b = a.new B();
    //方法二：将内部类的方法都设置为static方法

   // manager.lsnList = new JMyFrame.claActionListenerList();   err: not an enclosed class
   // this.lsnList = manager.new claActionListenerList();   // ！！！
    // but now, we have move the class from JMyFrame to current class, no need anymore


    jmf.jltInFrame.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    claActionListenerList lsnList = new claActionListenerList();
    jltJComponent.addListSelectionListener(lsnList);

    jmf.jspCentre.setLeftComponent(jltJComponent);

    jmf.ta = new JTextArea();
    JScrollPane jsp = new JScrollPane(jmf.ta);

    jmf.jspCentre.setRightComponent(jsp);
  }

  public  class claActionListenerList  implements ListSelectionListener {

    public void valueChanged(ListSelectionEvent lse) {
      System.out.println("list valueChanged ");
      // We know this is the list, so pop up the page.
      if (!lse.getValueIsAdjusting()) {
        jmf.setTaContent((String)(jltJComponent.getSelectedValue()));    // parent
      }
    }

  }
}
