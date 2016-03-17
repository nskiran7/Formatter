/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package formatter;

import java.awt.event.ActionEvent;
import junit.framework.TestCase;

/**
 *
 * @author HP 15
 */
public class FormatsIT extends TestCase {
    
    public FormatsIT(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of actionPerformed method, of class Formats.
     */
    public void testActionPerformed() {
        System.out.println("actionPerformed");
        ActionEvent ae = null;
        Formats instance = new Formats();
        instance.actionPerformed(ae);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
