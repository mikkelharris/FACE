 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prog24178.project;

import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;

/**
 * Name: Mikkel Harris 
 * File: FACECaseDetails.java 
 * Other Files in this Project: 
 *	FACECaseSummary.java 
 *	FACEBoneDetails.java
 *	BoneInfo.java
 *	CaseInfo.java
 * Main class: FACEStart.java 
 */
public class FACECaseDetails extends javax.swing.JFrame implements ActionListener,
	WindowListener, KeyListener
{
    // Create printwriter for the case.dat file
    private PrintWriter caseLog;
    // Create printwrite for the bones.dat file
    private PrintWriter boneLog;
    // Create caseInfo object to be used when creating cases
    private CaseInfo caseInfo;
    // Create new arrayList to hold bones created from the template
    private ArrayList<BoneInfo> boneArrayList = new ArrayList<BoneInfo>();

    /**
     * Default constructor that connects to data files and sets GUI.
     * Creates new form FACECaseDetails
     */
    public FACECaseDetails()
    {
	initComponents();
	// Add Action Listeners
	btnCreateCase.addActionListener(this);
	btnBack.addActionListener(this);
	ddlDay.addActionListener(this);
	ddlMonth.addActionListener(this);
	ddlYear.addActionListener(this);
	// Add Key Listeners
	txtLocation.addKeyListener(this);
	// Add Window Listeners
	this.addWindowListener(this);
	// Set window location
	this.setLocationRelativeTo(null);
	// Add Year to index 0
	ddlYear.addItem((Object) "Year");
	// Add years to ddlYear
	for (int i = 1950; i <= 2100; i++)
	{
	    ddlYear.addItem((Object) i);
	}
	// Try connecting to case.dat
	try
	{
	    caseLog = new PrintWriter(new BufferedWriter(new 
		    FileWriter("data/case.dat", true)));

	} catch (IOException ex)
	{
	    // display an error dialog
	    JOptionPane.showMessageDialog(this, "Error accessing case.dat:\n"
		    + ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
	}
	// Try connecting to bones.dat
	try
	{
	    boneLog = new PrintWriter(new BufferedWriter(new 
		    FileWriter("data/bones.dat", true)));

	} catch (IOException ex)
	{
	    // display an error dialog
	    JOptionPane.showMessageDialog(this, "Error accessing bones.dat:\n"
		    + ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
	}
    }
    /**
     * Combines all of the fields to create a caseNumber
     * @return the caseNumber
     */
    private String createCaseNumber()
    {
	return (String)ddlDay.getSelectedItem() + (String)ddlMonth.getSelectedItem() 
		+ ddlYear.getSelectedItem().toString() + txtLocation.getText().trim();
    }
    /**
     * Reads the arrayList and prints the bone objects to bone.dat
     * @param boneArrayList the arrayList created from the template file
     */
    private void printBonesArrayList(ArrayList boneArrayList)
    {
	// Loop through the arrayList adding each object to the bone.dat file
	for (int i = 0; i < boneArrayList.size(); i++)
	{
	    BoneInfo bone = (BoneInfo) boneArrayList.get(i);
	    boneLog.print(bone.toFileString());
	}
    }
    /**
     * Creates bone objects for the new case from the template file
     * @param caseNumber uses the case number created by createCaseNumber()
     * @return the arrayList of bone objects with the new caseNumber
     */
    private ArrayList createBones(String caseNumber)
    {
	// Try scanning boneTemplate.dat
	try
	{
	    Scanner fileIn = new Scanner(new File("data/boneTemplate.dat"));
	    // Use "endOfLine" as the delimiter
	    fileIn.useDelimiter("endOfLine\n");
	    while (fileIn.hasNext())
	    {
		String record = fileIn.next();
		// Split record into String Arrays
		String[] fields = record.split("\\s*\\|\\s*");
		// Create bone object from the array
		BoneInfo bone = new BoneInfo(fields[0], fields[1], fields[2],
			fields[3], Boolean.parseBoolean(fields[4]));
		// Add bone object to arrayList
		boneArrayList.add(bone);
	    }
	    fileIn.close();
	    fileIn = null;
	    // Loop through arraylist changing template caseNumer to the new caseNumber
	    for (int i = 0; i < boneArrayList.size(); i++)
	    {
		boneArrayList.get(i).setCaseNumber(caseNumber);
	    }
	// Catch exception and display dialog    
	} catch (Exception ex)
	{
	    JOptionPane.showMessageDialog(this, "Error creating bone objects:\n"
		    + ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
	}
	return boneArrayList;
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
	if (source == btnCreateCase)
	{
	    // Try to create a case if the user has selected a year
	    try
	    {
		// Check to see if the user has selected a day
		if (ddlDay.getSelectedIndex() == 0)
		{
		    JOptionPane.showMessageDialog(this, "Please select a Day\n",
			    "Error", JOptionPane.ERROR_MESSAGE);
		} else
		{
		    // Check to see if the user has selected a month
		    if (ddlMonth.getSelectedIndex() == 0)
		    {
			JOptionPane.showMessageDialog(this, "Please select a Month\n",
				"Error", JOptionPane.ERROR_MESSAGE);
		    } else
		    {
			// Check to see if the location field is blank
			if ("".equals(txtLocation.getText()))
			{
			    JOptionPane.showMessageDialog(this, 
				    "Please enter a Location\n", "Error", 
				    JOptionPane.ERROR_MESSAGE);
			} else
			{
			    String caseNum = createCaseNumber();
			    // Create caseInfo objects
			    caseInfo = new CaseInfo(createCaseNumber(), 
				    Integer.parseInt(ddlDay.getSelectedItem().toString()),
				    ddlMonth.getSelectedItem().toString(),
				    Integer.parseInt(ddlYear.getSelectedItem().toString()), 
				    txtLocation.getText().trim());
			    // Print object to case.dat
			    caseLog.print(caseInfo.toFileString());
			    // create bones and print to bones.dat
			    printBonesArrayList(createBones(caseNum));
			    // Close printwriters
			    caseLog.close();
			    caseLog = null;
			    boneLog.close();
			    boneLog = null;
			    // Create an instance of FACECaseSummary and dispose of current window
			    FACECaseSummary faceCaseSummary = new FACECaseSummary(caseNum);
			    faceCaseSummary.pack();
			    faceCaseSummary.setVisible(true);
			    this.dispose();
			}
		    }
		}
	    // Catch exception and display error dialog
	    } catch (NumberFormatException ex)
	    {
		JOptionPane.showMessageDialog(this, "Please select a Year\n",
			"Error", JOptionPane.ERROR_MESSAGE);
	    }
	} else if (source == btnBack)
	{
	    // Create an instance of FACEStart and dispose of current window
	    FACEStart faceStart = new FACEStart();
	    faceStart.pack();
	    faceStart.setVisible(true);
	    this.dispose();
	}
    }

    /**
     * Called when the user closes the window.
     * @param event the event created from closing the window
     */
    @Override
    public void windowClosing(WindowEvent event)
    {
	// Display message dialog asking if user wants to exit
	int exit = JOptionPane.showConfirmDialog(this, 
		"Do you want to cancel creating a case?", "Cancel Case Creation", 
		JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
	if (exit == JOptionPane.YES_OPTION)
	{
	    // Create an instance of FACEStart and dispose of current window
	    FACEStart faceStart = new FACEStart();
	    faceStart.pack();
	    faceStart.setVisible(true);
	    this.dispose();
	}
    }

    /**
     * Called when a key has been released.
     * @param event created from a keystroke
     */
    @Override
    public void keyReleased(KeyEvent event)
    {
    }

    /**
     * Called when a key has been pressed.
     * @param event created from a keystroke
     */
    @Override
    public void keyPressed(KeyEvent event)
    {
    }

    /**
     * Called when a key has been typed.
     * @param event created from a keystroke
     */
    @Override
    public void keyTyped(KeyEvent event)
    {
	
	if (event.getSource() == txtLocation)
	{
	    if (event.getKeyChar() == KeyEvent.VK_ENTER)
	    {
		// Try to create a case if the user has selected a year
	    try
	    {
		// Check to see if the user has selected a day
		if (ddlDay.getSelectedIndex() == 0)
		{
		    JOptionPane.showMessageDialog(this, "Please select a Day\n",
			    "Error", JOptionPane.ERROR_MESSAGE);
		} else
		{
		    // Check to see if the user has selected a month
		    if (ddlMonth.getSelectedIndex() == 0)
		    {
			JOptionPane.showMessageDialog(this, "Please select a Month\n",
				"Error", JOptionPane.ERROR_MESSAGE);
		    } else
		    {
			// Check to see if the location field is blank
			if ("".equals(txtLocation.getText()))
			{
			    JOptionPane.showMessageDialog(this, 
				    "Please enter a Location\n", "Error", 
				    JOptionPane.ERROR_MESSAGE);
			} else
			{
			    String caseNum = createCaseNumber();
			    // Create caseInfo objects
			    caseInfo = new CaseInfo(createCaseNumber(), 
				    Integer.parseInt(ddlDay.getSelectedItem().toString()),
				    ddlMonth.getSelectedItem().toString(),
				    Integer.parseInt(ddlYear.getSelectedItem().toString()), 
				    txtLocation.getText().trim());
			    // Print object to case.dat
			    caseLog.print(caseInfo.toFileString());
			    // create bones and print to bones.dat
			    printBonesArrayList(createBones(caseNum));
			    // Close printwriters
			    caseLog.close();
			    caseLog = null;
			    boneLog.close();
			    boneLog = null;
			    // Create an instance of FACECaseSummary and dispose of current window
			    FACECaseSummary faceCaseSummary = new FACECaseSummary(caseNum);
			    faceCaseSummary.pack();
			    faceCaseSummary.setVisible(true);
			    this.dispose();
			}
		    }
		}
	    // Catch exception and display error dialog
	    } catch (NumberFormatException ex)
	    {
		JOptionPane.showMessageDialog(this, "Please select a Year\n",
			"Error", JOptionPane.ERROR_MESSAGE);
	    }
	    }
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

        jScrollBar1 = new javax.swing.JScrollBar();
        jPanel1 = new javax.swing.JPanel();
        lblDate = new javax.swing.JLabel();
        lblLocation = new javax.swing.JLabel();
        txtLocation = new javax.swing.JTextField();
        btnBack = new javax.swing.JButton();
        btnCreateCase = new javax.swing.JButton();
        ddlDay = new javax.swing.JComboBox();
        ddlMonth = new javax.swing.JComboBox();
        ddlYear = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Forensic Anthropology Case Evidence  (FACE) ");
        setSize(new java.awt.Dimension(600, 450));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Case Details", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        lblDate.setText("Date:");

        lblLocation.setText("Location:");

        txtLocation.setToolTipText("Enter the location");

        btnBack.setMnemonic('B');
        btnBack.setText("Back");
        btnBack.setToolTipText("Return to start screen");

        btnCreateCase.setMnemonic('C');
        btnCreateCase.setText("Create Case");
        btnCreateCase.setToolTipText("Create a new case with this case number.");

        ddlDay.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Day", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        ddlDay.setToolTipText("Choose the day");

        ddlMonth.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Month", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
        ddlMonth.setToolTipText("Choose the month");

        ddlYear.setToolTipText("Choose the year");

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(lblLocation)
                    .add(lblDate))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(btnBack, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 105, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(btnCreateCase, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 109, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                            .add(txtLocation)
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(ddlDay, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 86, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(ddlMonth, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 92, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(ddlYear, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 80, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                        .add(0, 0, Short.MAX_VALUE))))
        );

        jPanel1Layout.linkSize(new java.awt.Component[] {btnBack, btnCreateCase}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

        jPanel1Layout.linkSize(new java.awt.Component[] {ddlDay, ddlMonth, ddlYear}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(lblDate)
                    .add(ddlDay, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(ddlMonth, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(ddlYear, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(txtLocation, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(lblLocation))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(btnBack)
                    .add(btnCreateCase))
                .add(22, 22, 22))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnCreateCase;
    private javax.swing.JComboBox ddlDay;
    private javax.swing.JComboBox ddlMonth;
    private javax.swing.JComboBox ddlYear;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollBar jScrollBar1;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblLocation;
    private javax.swing.JTextField txtLocation;
    // End of variables declaration//GEN-END:variables
}
