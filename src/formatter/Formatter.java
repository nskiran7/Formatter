/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package formatter;

import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.util.StringTokenizer;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.text.JTextComponent;

/**
 *
 * @author Sai kiran
 */
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
       if(count==10)
         {
          B.append("\n");
          
          count=0;
         }
         }
         
         String delims="\n";
         String delims1=" ";
        // int count=0,
         int MAX=10;
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
        textComp.setText("\n");
            B.append("\n");
        count=0;
        temp=wd.nextToken();
        count+=temp.length();
       textComp.setText(temp+" ");
        B.append(temp+" ");
        }
        }
        B.append("\n");
        textComp.setText("\n");
         
        }
        textComp.setText(B.toString());
    }
    
//To change body of generated methods, choose Tools | Templates.

    private static class textComp {

        private static void setText(String toString) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        private static String getText() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        public textComp() {
        }
    }
    }
        

