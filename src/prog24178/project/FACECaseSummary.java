package prog24178.project;

import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;
import javax.swing.table.*;

/**
 * Name: Mikkel Harris 
 * File: FACECaseSummary.java 
 * Other Files in this Project:
 *	FACECaseDetails.java 
 *	FACEBoneDetails.java
 *	BoneInfo.java
 *	CaseInfo.java
 * Main class: FACEStart.java 
 */
public class FACECaseSummary extends javax.swing.JFrame implements 
	ActionListener, WindowListener
{
    // Create printwriter for the boneTemp.dat file
    private PrintWriter boneFileTemp;
    // Create printwriter for the bones.dat file to overwrite
    private PrintWriter boneFileOverwrite;
    // Create printwriter for the boens.dat file to append
    private PrintWriter boneFileAppend;
    // Create ArrayLists
    private ArrayList<BoneInfo> boneArray = new ArrayList<BoneInfo>(210);
    private ArrayList<BoneInfo> completeBoneArray = new ArrayList<BoneInfo>();
    private ArrayList<BoneInfo> boneArrayListTemp = new ArrayList<BoneInfo>();
    // Create Default Table Model
    DefaultTableModel remainsModel = new DefaultTableModel();

    /**
     * Constructor that creates the array and sets GUI.
     * Creates new form FACECaseSummary
     * @param caseNum 
     */
    public FACECaseSummary(String caseNum)
    {
	initComponents();
	// Add Action Listeners
	btnEdit.addActionListener(this);
	btnExitCase.addActionListener(this);
	// Add window listener
	this.addWindowListener(this);
	// Set window location
	this.setLocationRelativeTo(null);

	lblCaseNumber.setText(caseNum);
	tblRemains.setModel(remainsModel);
	// Set table column headings
	remainsModel.setColumnIdentifiers(new String[] {"Bone", "Condition"});
	// Set column width
	int width1 = 175;
	int width2 = (tblRemains.getWidth() - width1);
	TableColumn col1 = tblRemains.getColumnModel().getColumn(0);
	TableColumn col2 = tblRemains.getColumnModel().getColumn(1);
	col1.setPreferredWidth(width1);
	col2.setPreferredWidth(width2);
	// Create an array from the bones.dat file using the current caseNum
	createArray("data/bones.dat", caseNum);
	// Loop through array looking for found bones
	for (int i = 0; i < boneArray.size(); i++)
	{
	    BoneInfo bone = (BoneInfo) boneArray.get(i);
	    if (bone.isFoundStatus())
	    {
		// Add relevant bones to table
		remainsModel.addRow(new String[] {bone.getBoneName(), 
		    bone.getCondition()});
	    }
	}
	// Overwrite bones.dat without bones that are currently being used
	overWriteFile();
    }
    /**
     * Creates an array using the bones file and the caseNum and a bonesTemp file.
     * @param casefile the file being scanned
     * @param caseNum the current caseNumber
     */
    private void createArray(String casefile, String caseNum)
    {
	// Try accessing the bonesTemp file
	try
	{
	    boneFileTemp = new PrintWriter(new BufferedWriter(new 
		    FileWriter("data/bonesTemp.dat", false)));
	    Scanner fileIn = new Scanner(new File(casefile));
	    // Use "endOfLine" as the delimiter
	    fileIn.useDelimiter("endOfLine\n");
	    while (fileIn.hasNext())
	    {
		String record = fileIn.next();
		// Use | as a delimiter for record
		String[] fields = record.split("\\s*\\|\\s*");
		// Create bone objects
		BoneInfo bone = new BoneInfo(fields[0], fields[1], fields[2],
			fields[3], Boolean.parseBoolean(fields[4]));
		// if the bone object matches the current caseNumber add it to 
		// boneArray and the boneTemp file
		if (fields[0].trim().equals(caseNum))
		{
		    boneArray.add(bone);
		    boneFileTemp.print(bone.toFileString());
		// if not then add it to the bones file
		} else
		{
		    completeBoneArray.add(bone);
		}
	    }
	    boneFileTemp.close();
	    fileIn.close();
	    fileIn = null;
	} catch (IOException ex)
	{
	    // display an error dialog
	    JOptionPane.showMessageDialog(this,
		    "There was an error accessing bonesTemp.dat:\n"+ ex.toString(), 
		    "Error", JOptionPane.ERROR_MESSAGE);
	} catch (Exception ex)
	{
	    JOptionPane.showMessageDialog(this, "Error creating bones objects:\n"
		    + ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
	}
    }
    /**
     * Appends data to the bones file
     */
    private void updateFile()
    {
	// Try accessing the bones
	try
	{
	    boneFileAppend = new PrintWriter(new BufferedWriter(new 
		    FileWriter("data/bones.dat", true)));
	    // Try scanning the bonesTemp file
	    Scanner fileIn = new Scanner(new File("data/bonesTemp.dat"));
	    // Use "endOfLine" as the delimiter
	    fileIn.useDelimiter("endOfLine\n");

	    while (fileIn.hasNext())
	    {
		String record = fileIn.next();
		// Use | as a delimiter for record
		String[] fields = record.split("\\s*\\|\\s*");
		// Create bone objects
		BoneInfo bone = new BoneInfo(fields[0], fields[1], fields[2],
			fields[3], Boolean.parseBoolean(fields[4]));
		// Add bone to arrayList
		boneArrayListTemp.add(bone);
	    }
	    fileIn.close();
	    fileIn = null;

	} catch (IOException ex)
	{
	    // display an error dialog
	    JOptionPane.showMessageDialog(this, "Error accessing data file bones.dat:\n"
		    + ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
	} catch (Exception ex)
	{
	    JOptionPane.showMessageDialog(this, "Error updating bones.dat:\n"
		    + ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
	}
	// Add bone to bones file
	for (int i = 0; i < boneArrayListTemp.size(); i++)
	{
	    BoneInfo bone = (BoneInfo) boneArrayListTemp.get(i);
	    boneFileAppend.print(bone.toFileString());
	}
	boneFileAppend.close();
    }
    /**
     * Overwrite data in bones file
     */
    private void overWriteFile()
    {
	// Try accessing bones file
	try
	{
	    boneFileOverwrite = new PrintWriter(new BufferedWriter(new 
		    FileWriter("data/bones.dat", false)));
	    // Overwrite bones file with the arryList without the current case
	    for (int i = 0; i < completeBoneArray.size(); i++)
	    {
		boneFileOverwrite.print(completeBoneArray.get(i).toFileString());
	    }
	    boneFileOverwrite.close();
	} catch (IOException ex)
	{
	    JOptionPane.showMessageDialog(this, "Error accessing bones.dat:\n" 
		    + ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
	} catch (Exception ex)
	{
	    JOptionPane.showMessageDialog(this, "Error overwriting bones.dat:\n"
		    + ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
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
	if (source == btnEdit)
	{
	    // Create an instance of FACEBoneDetails and dispose of current window
	    FACEBoneDetails faceBoneDetails = new 
		    FACEBoneDetails(lblCaseNumber.getText(), boneArray);
	    faceBoneDetails.pack();
	    faceBoneDetails.setVisible(true);
	    this.dispose();
	} else if (source == btnExitCase)
	{
	    // Update file and create an instance of FACEStart and dispose of current window
	    updateFile();
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
		"Are you sure you wnat to exit the case?", "Exit Case", 
		JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
	if (exit == JOptionPane.YES_OPTION)
	{
	    // Update file and create an instance of FACEStart and dispose of current window
	    updateFile();
	    FACEStart faceStart = new FACEStart();
	    faceStart.pack();
	    faceStart.setVisible(true);
	    this.dispose();
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
     * : Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollBar1 = new javax.swing.JScrollBar();
        pnlDisplay = new javax.swing.JPanel();
        scrRemainsTable = new javax.swing.JScrollPane();
        tblRemains = new javax.swing.JTable();
        pnlCaseNumber = new javax.swing.JPanel();
        lblCaseNumber = new javax.swing.JLabel();
        pnlButtons = new javax.swing.JPanel();
        btnExitCase = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Forensic Anthropology Case Evidence  (FACE) ");
        setSize(new java.awt.Dimension(600, 450));

        pnlDisplay.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bones Recovered", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        tblRemains.setToolTipText("Current case details");
        tblRemains.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN);
        tblRemains.setAutoscrolls(false);
        tblRemains.setShowGrid(true);
        scrRemainsTable.setViewportView(tblRemains);

        org.jdesktop.layout.GroupLayout pnlDisplayLayout = new org.jdesktop.layout.GroupLayout(pnlDisplay);
        pnlDisplay.setLayout(pnlDisplayLayout);
        pnlDisplayLayout.setHorizontalGroup(
            pnlDisplayLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(pnlDisplayLayout.createSequentialGroup()
                .addContainerGap()
                .add(scrRemainsTable, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 617, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlDisplayLayout.setVerticalGroup(
            pnlDisplayLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(pnlDisplayLayout.createSequentialGroup()
                .addContainerGap()
                .add(scrRemainsTable, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(pnlDisplay, java.awt.BorderLayout.CENTER);

        lblCaseNumber.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCaseNumber.setText(" ");
        lblCaseNumber.setToolTipText("Current case number");
        lblCaseNumber.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Case Number", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        org.jdesktop.layout.GroupLayout pnlCaseNumberLayout = new org.jdesktop.layout.GroupLayout(pnlCaseNumber);
        pnlCaseNumber.setLayout(pnlCaseNumberLayout);
        pnlCaseNumberLayout.setHorizontalGroup(
            pnlCaseNumberLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, lblCaseNumber, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 641, Short.MAX_VALUE)
        );
        pnlCaseNumberLayout.setVerticalGroup(
            pnlCaseNumberLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, pnlCaseNumberLayout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(lblCaseNumber)
                .addContainerGap())
        );

        getContentPane().add(pnlCaseNumber, java.awt.BorderLayout.PAGE_START);

        btnExitCase.setMnemonic('x');
        btnExitCase.setText("Exit Case");
        btnExitCase.setToolTipText("Exit the current case and return to the start window");

        btnEdit.setMnemonic('E');
        btnEdit.setText("Edit");
        btnEdit.setToolTipText("Edit the current case");

        org.jdesktop.layout.GroupLayout pnlButtonsLayout = new org.jdesktop.layout.GroupLayout(pnlButtons);
        pnlButtons.setLayout(pnlButtonsLayout);
        pnlButtonsLayout.setHorizontalGroup(
            pnlButtonsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(pnlButtonsLayout.createSequentialGroup()
                .add(209, 209, 209)
                .add(btnEdit, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 111, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(btnExitCase)
                .addContainerGap(198, Short.MAX_VALUE))
        );

        pnlButtonsLayout.linkSize(new java.awt.Component[] {btnEdit, btnExitCase}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

        pnlButtonsLayout.setVerticalGroup(
            pnlButtonsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, pnlButtonsLayout.createSequentialGroup()
                .add(pnlButtonsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(btnEdit)
                    .add(btnExitCase, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlButtonsLayout.linkSize(new java.awt.Component[] {btnEdit, btnExitCase}, org.jdesktop.layout.GroupLayout.VERTICAL);

        getContentPane().add(pnlButtons, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnExitCase;
    private javax.swing.JScrollBar jScrollBar1;
    private javax.swing.JLabel lblCaseNumber;
    private javax.swing.JPanel pnlButtons;
    private javax.swing.JPanel pnlCaseNumber;
    private javax.swing.JPanel pnlDisplay;
    private javax.swing.JScrollPane scrRemainsTable;
    private javax.swing.JTable tblRemains;
    // End of variables declaration//GEN-END:variables
}
