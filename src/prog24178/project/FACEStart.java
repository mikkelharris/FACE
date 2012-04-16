package prog24178.project;

import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;

/**
 * Name: Mikkel Harris 
 * Assignment: FACE - Final Project
 * Program: Computer Systems Technology - Systems Analyst
 * 
 * Description: This program is intended for use by forensic anthropologists to 
 * document their initial findings. It will assist them in the creation of case 
 * files to document the remains that they find.
 */
public class FACEStart extends javax.swing.JFrame implements ActionListener,
	WindowListener
{
    // Create new arrayList to hold caseNumbers
    private ArrayList<CaseInfo> caseArray = new ArrayList<CaseInfo>();

    /**
     * Default Constructor that populates the retrieve case comboBox and sets
     * all listeners.
     * Creates new form FACEStart
     */
    public FACEStart()
    {
	initComponents();
	// Add Action Listeners
	btnCreate.addActionListener(this);
	ddlRetrieve.addActionListener(this);
	// Add Window Listener
	this.addWindowListener(this);
	this.setLocationRelativeTo(null);
	// Populate ddlRetrieve
	createListArray("data/case.dat");
    }

    /**
     * Reads case.dat and creates an arrayList from the contents, then it adds
     * those contents to the comboBox.
     * @param casefile the data file used to create the array
     */
    private void createListArray(String casefile)
    {
	// Try to read casefile
	try
	{
	    // Create new scanner to read the case.dat file
	    Scanner fileIn = new Scanner(new File(casefile));
	    // Loop through case.dat as long as there is a new line
	    while (fileIn.hasNext())
	    {
		String record = fileIn.nextLine();
		// Create an array of fields using | as a delimiter
		String[] fields = record.split("\\s*\\|\\s*");
		// Create caseInfo opjects and add them to caseArray
		CaseInfo caseInfo = new CaseInfo(fields[0], Integer.parseInt(fields[1]), 
			fields[2], Integer.parseInt(fields[3]), fields[4]);
		caseArray.add(caseInfo);
	    }
	    fileIn.close();
	    fileIn = null;
	// Display warning if reading of case.dat is unsuccessful   
	} catch (IOException ex)
	{
	    JOptionPane.showMessageDialog(this, "There was an error reading case.dat\n" + ex.toString(),
		    "Error", JOptionPane.ERROR_MESSAGE);
	}catch (Exception ex)
	{
	    JOptionPane.showMessageDialog(this, "There was an error creating the arrayList\n" + ex.toString(),
		    "Error", JOptionPane.ERROR_MESSAGE);
	}
	// Loop through the caseArray adding the caseNumbers to ddlRetrieve
	for (int i = 0; i <= caseArray.size() - 1; i++)
	{
	    ddlRetrieve.addItem(caseArray.get(i).getCaseNum());
	}
    }

    /**
     * Receives the event and performs actions depending on where the event originated.
     * @param event the event created from the GUI
     */
    @Override
    public void actionPerformed(ActionEvent event)
    {
	// Create object for the source of the event
	Object source = event.getSource();
	// Check for source of event
	if (source == btnCreate)
	{
	    // Create an instance of FACECaseDetails and dispose of current window
	    FACECaseDetails faceCaseDetails = new FACECaseDetails();
	    faceCaseDetails.pack();
	    faceCaseDetails.setVisible(true);
	    this.dispose();
	} else if (source == ddlRetrieve)
	{
	    // Check to make sure a case is selected
	    if (ddlRetrieve.getSelectedIndex() > 0)
	    {
		// Create an instance of FACECaseSummary and dispose of current window
		FACECaseSummary faceSummary = new FACECaseSummary((String) 
			ddlRetrieve.getSelectedItem());
		faceSummary.pack();
		faceSummary.setVisible(true);
		this.dispose();
	    }
	}
    }

    /**
     * Called when the user closes the window.
     * @param event the event created from closing the window
     */
    @Override
    public void windowClosing(WindowEvent event)
    {
	// Confirm that user wants to exit program when they click the close button
	int exit = JOptionPane.showConfirmDialog(this, "Do you want to close FACE?",
		"Close FACE", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
	if (exit == JOptionPane.YES_OPTION)
	{
	    System.exit(0);
	}
    }

    /**
     * Called when the window is not active.
     * @param event the event created from closing the window
     */
    @Override
    public void windowDeactivated(WindowEvent event)
    {
    }

    /**
     * Called when the window is activated.
     * @param event the event created from closing the window
     */
    @Override
    public void windowActivated(WindowEvent event)
    {
    }

    /**
     * Called when the window is brought back from being minimized.
     * @param event the event created from closing the window
     */
    @Override
    public void windowDeiconified(WindowEvent event)
    {
    }

    /**
     * Called when the window is minimized.
     * @param event the event created from closing the window
     */
    @Override
    public void windowIconified(WindowEvent event)
    {
    }

    /**
     * Called when the window is disposed.
     * @param event the event created from closing the window
     */
    @Override
    public void windowClosed(WindowEvent event)
    {
    }

    /**
     * Called when the window is first opened.
     * @param event the event created from closing the window
     */
    @Override
    public void windowOpened(WindowEvent event)
    {
    }

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

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("FACE");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Forensic Anthropology Case Evidence", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        btnCreate.setText("Create Case");
        btnCreate.setToolTipText("Create a new case");
        btnCreate.setActionCommand("=Create Case=");

        ddlRetrieve.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Retrieve Case" }));
        ddlRetrieve.setToolTipText("Retrieve an existing case");

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, btnCreate, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 236, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, ddlRetrieve, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 236, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(21, 21, 21)
                .add(btnCreate)
                .add(18, 18, 18)
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
     * Main method for the FACE program.
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
    // End of variables declaration//GEN-END:variables
}
