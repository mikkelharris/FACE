/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prog24178.project;

import java.awt.event.*;
import java.io.*;
import java.util.*;

/**
 *
 * @author mikkelharris
 */
public class FACEStart extends javax.swing.JFrame implements ActionListener, WindowListener
{
    ArrayList caseArray = new ArrayList(50);
    

    /**
     * Creates new form FACEStart
     */
    public FACEStart()
    {
	initComponents();
	btnCreate.addActionListener(this);
	ddlRetrieve.addActionListener(this);
	createArray("case.dat");
    }
    @Override
    public void actionPerformed(ActionEvent event)
    {
	Object source = event.getSource();
	
	
	
	if (source == btnCreate)
	{
	    FACECaseDetails faceCaseDetails = new FACECaseDetails();
	    faceCaseDetails.pack();
	    faceCaseDetails.setVisible(true);
	    this.setVisible(false);    
	}
	else if (source == ddlRetrieve)
	{
	    if (ddlRetrieve.getSelectedIndex() > 0)
	    {
		FACECaseSummary faceSummary = new FACECaseSummary((String)ddlRetrieve.getSelectedItem());
		faceSummary.pack();
		faceSummary.setVisible(true);
		this.setVisible(false);
	    }
	    
	}
    }
    private void createArray(String casefile)
    {
	try
	{
	    Scanner fileIn = new Scanner(new File(casefile));
	    fileIn.useDelimiter(System.getProperty("line.separator"));
	    
	    while (fileIn.hasNext())
	    {
		String record = fileIn.next();
		
		Scanner recordIn = new Scanner(record);
		recordIn.useDelimiter("\\s*\\|\\s*");
		String caseNum = recordIn.next();
		String caseDateDay = recordIn.next();
		String caseDateMonth = recordIn.next();
		String caseDateYear = recordIn.next();
		String caseLocation = recordIn.next();
		
		caseArray.add(caseNum);

	    }
	    fileIn.close();
	    fileIn = null;
	}
	catch (IOException ex)
	{
	    
	}
	for (int i = 0; i <= caseArray.size() - 1; i++)
	{
	    ddlRetrieve.addItem(caseArray.get(i));
	}
	
    }
    
    @Override
    public void windowDeactivated(WindowEvent event){}
    @Override
    public void windowActivated(WindowEvent event){}
    @Override
    public void windowDeiconified(WindowEvent event){}
    @Override
    public void windowIconified(WindowEvent event){}
    @Override
    public void windowClosed(WindowEvent event){}
    @Override
    public void windowClosing(WindowEvent event){}
    @Override
    public void windowOpened(WindowEvent event){}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnCreate = new javax.swing.JButton();
        ddlRetrieve = new javax.swing.JComboBox();
        lblName = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("FACE");

        btnCreate.setText("Create Case");
        btnCreate.setActionCommand("=Create Case=");

        ddlRetrieve.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Retrieve Case" }));

        lblName.setText("Forensic Anthropology Case Evidence");

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                    .add(btnCreate, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(lblName, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(ddlRetrieve, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(lblName, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(28, 28, 28)
                .add(btnCreate)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(ddlRetrieve, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
	/*
	 * Set the Nimbus look and feel
	 */
	//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
	 * If Nimbus (introduced in Java SE 6) is not available, stay with the
	 * default look and feel. For details see
	 * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
	 */
	try
	{
	    for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
	    {
		if ("Nimbus".equals(info.getName()))
		{
		    javax.swing.UIManager.setLookAndFeel(info.getClassName());
		    break;
		}
	    }
	} catch (ClassNotFoundException ex)
	{
	    java.util.logging.Logger.getLogger(FACEStart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (InstantiationException ex)
	{
	    java.util.logging.Logger.getLogger(FACEStart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (IllegalAccessException ex)
	{
	    java.util.logging.Logger.getLogger(FACEStart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (javax.swing.UnsupportedLookAndFeelException ex)
	{
	    java.util.logging.Logger.getLogger(FACEStart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	}
	//</editor-fold>

	/*
	 * Create and display the form
	 */
	java.awt.EventQueue.invokeLater(new Runnable()
	{

	    public void run()
	    {
		new FACEStart().setVisible(true);
	    }
	});
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreate;
    private javax.swing.JComboBox ddlRetrieve;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblName;
    // End of variables declaration//GEN-END:variables
}