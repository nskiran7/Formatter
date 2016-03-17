/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package formatter;

/**
 *
 * @author Sai kiran
 */
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Hashtable;
import java.util.StringTokenizer;
import javax.swing.*;
import javax.swing.text.*;

/**
 *
 * @author Karthik Sharma
 */
public class Sample extends JFrame {

  private Action openAction = new OpenAction();
  private  Action saveAction = new SaveAction();
   private Action formats=new Formats();
  private JTextComponent textComp;
  private Hashtable actionHash = new Hashtable();

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
    Sample editor = new Sample();
    editor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    editor.setVisible(true);
  }

  // Create an editor.

    /**
     *
     */
      public Sample() {
    super("Swing Editor");
    textComp = createTextComponent();
    makeActionsPretty();

    Container content = getContentPane();
    content.add(textComp, BorderLayout.CENTER);
    content.add(createToolBar(), BorderLayout.NORTH);
    setJMenuBar(createMenuBar());
    setSize(320, 240);
  }

  // Create the JTextComponent subclass.

    /**
     *
     * @return
     */
      protected JTextComponent createTextComponent() {
    JTextArea ta = new JTextArea();
    ta.setLineWrap(true);
    return ta;
  }

  // Add icons and friendly names to actions we care about.

    /**
     *
     */
      protected void makeActionsPretty() {
    Action a;
    a = textComp.getActionMap().get(DefaultEditorKit.cutAction);
    a.putValue(Action.SMALL_ICON, new ImageIcon("icons/cut.gif"));
    a.putValue(Action.NAME, "Cut");

    a = textComp.getActionMap().get(DefaultEditorKit.copyAction);
    a.putValue(Action.SMALL_ICON, new ImageIcon("icons/copy.gif"));
    a.putValue(Action.NAME, "Copy");

    a = textComp.getActionMap().get(DefaultEditorKit.pasteAction);
    a.putValue(Action.SMALL_ICON, new ImageIcon("icons/paste.gif"));
    a.putValue(Action.NAME, "Paste");

    a = textComp.getActionMap().get(DefaultEditorKit.selectAllAction);
    a.putValue(Action.NAME, "Select All");
  }

  // Create a simple JToolBar with some buttons.

    /**
     *
     * @return
     */
      protected JToolBar createToolBar() {
    JToolBar bar = new JToolBar();

    // Add simple actions for opening & saving.
    bar.add(getOpenAction()).setText("");
    bar.add(getSaveAction()).setText("");
    bar.addSeparator();

    // Add cut/copy/paste buttons.
    bar.add(textComp.getActionMap().get(DefaultEditorKit.cutAction)).setText("");
    bar.add(textComp.getActionMap().get(
              DefaultEditorKit.copyAction)).setText("");
    bar.add(textComp.getActionMap().get(
              DefaultEditorKit.pasteAction)).setText("");
    return bar;
  }

  // Create a JMenuBar with file & edit menus.

    /**
     *
     * @return
     */
      protected JMenuBar createMenuBar() {
    JMenuBar menubar = new JMenuBar();
    JMenu file = new JMenu("File");
    JMenu edit = new JMenu("Edit");
    JMenu format=new JMenu("Format");
    menubar.add(file);
    menubar.add(edit);
    menubar.add(format);

    file.add(getOpenAction());
    file.add(getSaveAction());
    file.add(new ExitAction());
    edit.add(textComp.getActionMap().get(DefaultEditorKit.cutAction));
    edit.add(textComp.getActionMap().get(DefaultEditorKit.copyAction));
    edit.add(textComp.getActionMap().get(DefaultEditorKit.pasteAction));
    edit.add(textComp.getActionMap().get(DefaultEditorKit.selectAllAction));
    //format.add(textComp.getFormat());
   format.add(getFormatAction(textComp));
    return menubar;
  }

  // Subclass can override to use a different open action.

    /**
     *
     * @return
     */
      protected Action getOpenAction() { return openAction; }

  // Subclass can override to use a different save action.

    /**
     *
     * @return
     */
      protected Action getSaveAction() { return saveAction; }

    /**
     *
     * @return
     */
    protected JTextComponent getTextComponent() { return textComp; }

    protected Action getFormatAction(JTextComponent J) {
        return formats; //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @return
     */
    

   



  // A very simple exit action

    /**
     *
     */
      public class ExitAction extends AbstractAction {

      /**
       *
       */
      public ExitAction() { super("Exit"); }

      /**
       *
       * @param ev
       */
      public void actionPerformed(ActionEvent ev) { System.exit(0); }
  }

  // An action that opens an existing file
  class OpenAction extends AbstractAction {
    public OpenAction() { 
      super("Open", new ImageIcon("icons/open.gif")); 
    }

    // Query user for a filename and attempt to open and read the file into the
    // text component.
    public void actionPerformed(ActionEvent ev) {
      JFileChooser chooser = new JFileChooser();
      if (chooser.showOpenDialog(Sample.this) !=
          JFileChooser.APPROVE_OPTION)
        return;
      File file = chooser.getSelectedFile();
      if (file == null)
        return;

      FileReader reader = null;
      try {
        reader = new FileReader(file);
        textComp.read(reader, null);
      }
      catch (IOException ex) {
        JOptionPane.showMessageDialog(Sample.this,
        "File Not Found", "ERROR", JOptionPane.ERROR_MESSAGE);
      }
      finally {
        if (reader != null) {
          try {
            reader.close();
          } catch (IOException x) {}
        }
      }
    }
  }

  //Action for formatter
  
  class Formats extends AbstractAction{

    public Formats()
    {
        super("Format",new ImageIcon("icons/open.gif"));
    }
  

    @Override
    public void actionPerformed(ActionEvent ae) {
         
         
         String A=new String(textComp.getText());
         StringBuffer B = new StringBuffer("");
   
        int count=0;
         for(int i=0;i<A.length();i++)
         {
        count++;
        
         B.append(A.charAt(i));
       if(count==72)
         {
          B.append("\n");
          
          count=0;
         }
         }
         
         String delims="\n";
         String delims1=" ";
        // int count=0,
         int MAX=72;
         String templine;
         String temp;
         
         StringTokenizer st,wd;
//Tokens seperated by newline character
         st=new StringTokenizer(A,delims);
        while(st.hasMoreTokens())
        {
          templine=st.nextToken();
            //Word Tokens seperated by spaces
       wd=new StringTokenizer(templine,delims1); 
        while(wd.hasMoreTokens()){
        //if the length of the line doesnot excede the limit provided
        if(count<MAX)
        {
            temp=wd.nextToken();
        count+=temp.length();
        //If the length of the line doesnt exceed the limit after adding the word
        if(count<=MAX)
        {
           // textComp.setText(temp+" ");
           // B.append(temp);
         
        }
        //If the length of the line exceeds the limit after the addition of the word the print the word till where it fits
        else
        
        {
       // textComp.setText(temp+"\n");
            B.append(temp+"\n");
       count=temp.length();
        }
        
        }
        else
        {
        //textComp.setText("\n");
            B.append("\n");
        count=0;
        temp=wd.nextToken();
        count+=temp.length();
       // textComp.setText(temp+" ");
        B.append(temp+" ");
        }
        }
        B.append("\n");
       // textComp.setText("\n");
         
        }
        textComp.setText(B.toString());
    }
    
//To change body of generated methods, choose Tools | Templates.
    }
        

 

  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  // An action that saves the document to a file
  class SaveAction extends AbstractAction {
    public SaveAction() {
      super("Save", new ImageIcon("icons/save.gif"));
    }

    // Query user for a filename and attempt to open and write the text
    // componentÃ¢â‚¬â„¢s content to the file.
    public void actionPerformed(ActionEvent ev) {
      JFileChooser chooser = new JFileChooser();
      if (chooser.showSaveDialog(Sample.this) !=
          JFileChooser.APPROVE_OPTION)
        return;
      File file = chooser.getSelectedFile();
      if (file == null)
        return;

      FileWriter writer = null;
      try {
        writer = new FileWriter(file);
        textComp.write(writer);
      }
      catch (IOException ex) {
        JOptionPane.showMessageDialog(Sample.this,
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
}  
