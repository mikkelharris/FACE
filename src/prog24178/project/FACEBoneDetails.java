package prog24178.project;

import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.*;

/**
 * Name: Mikkel Harris 
 * File: FACEBoneDetails.java
 * Other Files in this Project:
 *	FACECaseDetails.java 
 *	FACECaseSummary.java 
 *	BoneInfo.java
 *	CaseInfo.java
 * Main class: FACEStart.java 
 */
public class FACEBoneDetails extends javax.swing.JFrame implements 
	ActionListener, WindowListener, ListSelectionListener, FocusListener
{
    private DefaultListModel foundList;
    private DefaultListModel notFoundList;
    private BoneInfo bone;
    private ArrayList<BoneInfo> boneArrayListTemp;
    private PrintWriter boneFile;

    /**
     * Constructor that creates the listModels and sets GUI.
     * Creates new form FACEBoneDetails
     * @param caseNum
     * @param boneArray  
     */
    public FACEBoneDetails(String caseNum, ArrayList<BoneInfo> boneArray)
    {
	initComponents();
	// Add action listeners
	btnFBack.addActionListener(this);
	btnFUpdate.addActionListener(this);
	btnNFUpdate.addActionListener(this);
	ddlFRegion.addActionListener(this);
	ddlNFRegion.addActionListener(this);
	// Add list selection listeners
	lstFRegion.addListSelectionListener(this);
	lstNFRegion.addListSelectionListener(this);
	// Add focus listeners
	txtFDetails.addFocusListener(this);
	txtNFDetails.addFocusListener(this);
	// Add window listener
	this.addWindowListener(this);
	// Set window location
	this.setLocationRelativeTo(null);
	
	lblCaseNum.setText(caseNum);
	boneArrayListTemp = boneArray;
	// Set listModels
	foundList = new DefaultListModel();
	notFoundList = new DefaultListModel();
	lstFRegion.setModel(foundList);
	lstNFRegion.setModel(notFoundList);
	// Fill lists with array information
	createModel(foundList, boneArrayListTemp, true, ddlFRegion);
	createModel(notFoundList, boneArrayListTemp, false, ddlNFRegion);
	// Try to access bones file
	try
	{
	    boneFile = new PrintWriter(new BufferedWriter(new 
		    FileWriter("data/bones.dat", true)));
	} 
	catch (IOException ex)
	{
	    // display an error dialog
	    JOptionPane.showMessageDialog(this,"Error accessing bones.dat:\n"
		    + ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
	}
    }
    /**
     * Adds items to the listModel if it matches
     * @param listModel the listModel being added to
     * @param arrayList the arrayList to get the information from
     * @param status one of the parameters to search the arrayList with
     * @param comboBox one of the parameters to search the arrayList with
     */
    private void createModel(DefaultListModel listModel, ArrayList arrayList, 
	    boolean status, JComboBox comboBox)
    {
	// Loop through the arrayList
	for (int i = 0; i < arrayList.size(); i++)
	{
	    // Create bone objects
	    bone = (BoneInfo)arrayList.get(i);
	    // If the comboBox selection is 0 add all arrayList objects to the listModel
	    if (comboBox.getSelectedIndex() == 0)
	    {
		if (status == bone.isFoundStatus())
		{
		    listModel.addElement(bone.getBoneName());
		}
	    }
	    // Otherwise add only the objects that match the comboBox selection
	    else 
	    {
		if (status == bone.isFoundStatus())
		{
		    if (((String)comboBox.getSelectedItem()).equals(bone.getBodyRegion()))
		    {
		      listModel.addElement(bone.getBoneName());
		    }
		}
	    }  
	}
    }
    /**
     * Update the bones file with the arrayList information
     */
    private void updateFile()
    {
	for (int i = 0; i < boneArrayListTemp.size(); i++)
	{
	    BoneInfo bone = (BoneInfo)boneArrayListTemp.get(i);
	    boneFile.print(bone.toFileString());
	}
	boneFile.close();
    }
    /**
     * Called when the list element that is selected is changed update the bone details field
     * @param event caused when the user changes their selection
     */
    @Override
    public void valueChanged(ListSelectionEvent event)
    {
	// Create object for the source of the event
	Object source = event.getSource();
	// Check for source of event
	if (source == lstFRegion)
	{
	    // Check if selection is greater than 0
	    if (lstFRegion.getSelectedIndex() >= 0)
	    {
		// Reset the checkBox to selected
		chkFFound.setSelected(true);
		// Loop through the arrayList retrieving the bone condition and setting the bone details field
		for (int i = 0; i < boneArrayListTemp.size(); i++)
		{
		    bone = (BoneInfo)boneArrayListTemp.get(i);
		    if (bone.getBoneName().equals(lstFRegion.getModel().getElementAt(lstFRegion.getSelectedIndex())))
		    {
			txtFDetails.setText(bone.getCondition());
		    }   
		}
	    }
	    // If nothing selected clear text field
	    else 
	    {
		txtFDetails.setText("");
	    }
	}
	else if (source == lstNFRegion)
	{
	    // Check if selections is greater than 0
	    if (lstNFRegion.getSelectedIndex() >= 0)
	    {
		// Reset the checkBox to unselected
		chkNFFound.setSelected(false);
		// Loop through the arrayList retrieving the bone condition and setting the bone details field
		for (int i = 0; i < boneArrayListTemp.size(); i++)
		{
		    bone = (BoneInfo)boneArrayListTemp.get(i);
		    if (bone.getBoneName().equals(lstNFRegion.getModel().getElementAt(lstNFRegion.getSelectedIndex())))
		    {
			txtNFDetails.setText(bone.getCondition());
		    }   
		}
	    }
	    // If nothing selected clear text field
	    else 
	    {
		txtFDetails.setText("");
	    }
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
	if (source == btnFBack)
	{
	    // Update file and create an instance of FACESummary and dispose of current window
	    updateFile();
	    FACECaseSummary faceCaseSummary = new FACECaseSummary(lblCaseNum.getText());
	    faceCaseSummary.pack();
	    faceCaseSummary.setVisible(true);
	    this.dispose();
	}
	else if (source == btnFUpdate)
	{
	    // Loop through the arrayList creating bone objects
	    for (int i = 0; i < boneArrayListTemp.size(); i++)
	    {
		bone = (BoneInfo)boneArrayListTemp.get(i);
		// Check if the boneName is the same as the selected list item
		if (bone.getBoneName().equals(lstFRegion.getModel().getElementAt(lstFRegion.getSelectedIndex())))
		{
		    // Update the arrayList element with the new data
		    bone.setCondition(txtFDetails.getText());
		    bone.setFoundStatus(chkFFound.isSelected());
		}   
	    }
	    // Clear the and update the listModels with the new data reseting GUI elements
	    foundList.clear();
	    notFoundList.clear();
	    createModel(foundList, boneArrayListTemp, true, ddlFRegion);
	    createModel(notFoundList, boneArrayListTemp, false, ddlNFRegion);
	    txtFDetails.setText("");
	    chkFFound.setSelected(false);
	    
	}
	else if (source == btnNFUpdate)
	{
	    // Loop through the arrayList creating bone objects
	    for (int i = 0; i < boneArrayListTemp.size(); i++)
	    {
		bone = (BoneInfo)boneArrayListTemp.get(i);
		// Check if the boneName is the same as the selected list item
		if (bone.getBoneName().equals(lstNFRegion.getModel().getElementAt(lstNFRegion.getSelectedIndex())))
		{
		    // Update the arrayList element with the new data
		    bone.setCondition(txtNFDetails.getText());
		    bone.setFoundStatus(chkNFFound.isSelected());
		}   
	    }
	    // Clear the and update the listModels with the new data reseting GUI elements
	    foundList.clear();
	    notFoundList.clear();
	    createModel(foundList, boneArrayListTemp, true, ddlFRegion);
	    createModel(notFoundList, boneArrayListTemp, false, ddlNFRegion);
	    txtNFDetails.setText("");
	    chkNFFound.setSelected(false);
	    
	}
	else if (source == ddlFRegion)
	{
	    // Update the listModel
	    foundList.clear();
	    createModel(foundList, boneArrayListTemp, true, ddlFRegion);
	}
	else if (source == ddlNFRegion)
	{
	    // Update the listModel
	    notFoundList.clear();
	    createModel(notFoundList, boneArrayListTemp, false, ddlNFRegion);
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
	int exit = JOptionPane.showConfirmDialog(this, "Are you sure you wnat to exit the case?", 
		"Exit Case", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
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
     * Called when  an element of the GUI gains focus
     * @param event the event created when and element gains focus
     */
    @Override
    public void focusGained(FocusEvent event)
    {
	// Create object for the source of the event
	Object source = event.getSource();
	// Select all the text of the area that gained focus
	if (source == txtFDetails)
	{
	    txtFDetails.selectAll();
	}
	if (source == txtNFDetails)
	{
	    txtNFDetails.selectAll();
	}
    }
    /**
     * Called when the element of the GUI loses focus
     * @param event
     */
    @Override
    public void focusLost(FocusEvent event){}
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

        pnlLabel = new javax.swing.JPanel();
        lblCaseNum = new javax.swing.JLabel();
        pnlNF = new javax.swing.JPanel();
        pnlNFRegion = new javax.swing.JPanel();
        ddlNFRegion = new javax.swing.JComboBox();
        scrNFRegion = new javax.swing.JScrollPane();
        lstNFRegion = new javax.swing.JList();
        lblNFRegion = new javax.swing.JLabel();
        pnlNFButtons = new javax.swing.JPanel();
        btnNFUpdate = new javax.swing.JButton();
        pnlNFDetails = new javax.swing.JPanel();
        scrNFDetails = new javax.swing.JScrollPane();
        txtNFDetails = new javax.swing.JTextArea();
        chkNFFound = new javax.swing.JCheckBox();
        pnlFound = new javax.swing.JPanel();
        pnlFButtons = new javax.swing.JPanel();
        btnFUpdate = new javax.swing.JButton();
        btnFBack = new javax.swing.JButton();
        pnlFRegion = new javax.swing.JPanel();
        ddlFRegion = new javax.swing.JComboBox();
        scrFRegion = new javax.swing.JScrollPane();
        lstFRegion = new javax.swing.JList();
        lblFRegion = new javax.swing.JLabel();
        pnlFDetails = new javax.swing.JPanel();
        scrFDetails = new javax.swing.JScrollPane();
        txtFDetails = new javax.swing.JTextArea();
        chkFFound = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Bone Details");

        pnlLabel.setLayout(new java.awt.BorderLayout());

        lblCaseNum.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCaseNum.setToolTipText("Current case number");
        lblCaseNum.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Case Number", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        pnlLabel.add(lblCaseNum, java.awt.BorderLayout.CENTER);

        pnlNF.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Not Found", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        pnlNF.setLayout(new java.awt.BorderLayout());

        ddlNFRegion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Full Body", "Head", "Torso", "Spine", "Pelvis", "Right Arm", "Left Arm", "Right Hand", "Left Hand", "Right Leg", "Left Leg", "Right Foot", "Left Foot", " " }));
        ddlNFRegion.setToolTipText("Select the region of the body you want to focus on");
        ddlNFRegion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ddlNFRegionActionPerformed(evt);
            }
        });

        lstNFRegion.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lstNFRegion.setToolTipText("List of bones not found ");
        scrNFRegion.setViewportView(lstNFRegion);

        lblNFRegion.setText("Body Region");

        org.jdesktop.layout.GroupLayout pnlNFRegionLayout = new org.jdesktop.layout.GroupLayout(pnlNFRegion);
        pnlNFRegion.setLayout(pnlNFRegionLayout);
        pnlNFRegionLayout.setHorizontalGroup(
            pnlNFRegionLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(pnlNFRegionLayout.createSequentialGroup()
                .add(pnlNFRegionLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(ddlNFRegion, 0, 206, Short.MAX_VALUE)
                    .add(pnlNFRegionLayout.createSequentialGroup()
                        .addContainerGap()
                        .add(lblNFRegion)
                        .add(0, 0, Short.MAX_VALUE))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, pnlNFRegionLayout.createSequentialGroup()
                        .add(0, 0, Short.MAX_VALUE)
                        .add(scrNFRegion, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 200, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlNFRegionLayout.setVerticalGroup(
            pnlNFRegionLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(pnlNFRegionLayout.createSequentialGroup()
                .addContainerGap()
                .add(lblNFRegion)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(ddlNFRegion, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 24, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(scrNFRegion, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE))
        );

        pnlNF.add(pnlNFRegion, java.awt.BorderLayout.LINE_START);

        btnNFUpdate.setMnemonic('U');
        btnNFUpdate.setText("Update");
        btnNFUpdate.setToolTipText("Update not found list");

        org.jdesktop.layout.GroupLayout pnlNFButtonsLayout = new org.jdesktop.layout.GroupLayout(pnlNFButtons);
        pnlNFButtons.setLayout(pnlNFButtonsLayout);
        pnlNFButtonsLayout.setHorizontalGroup(
            pnlNFButtonsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(pnlNFButtonsLayout.createSequentialGroup()
                .add(206, 206, 206)
                .add(btnNFUpdate)
                .addContainerGap(210, Short.MAX_VALUE))
        );
        pnlNFButtonsLayout.setVerticalGroup(
            pnlNFButtonsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, pnlNFButtonsLayout.createSequentialGroup()
                .add(0, 0, Short.MAX_VALUE)
                .add(btnNFUpdate))
        );

        pnlNF.add(pnlNFButtons, java.awt.BorderLayout.PAGE_END);

        scrNFDetails.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        txtNFDetails.setColumns(20);
        txtNFDetails.setRows(5);
        txtNFDetails.setToolTipText("Observations of the bones");
        txtNFDetails.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bone Details", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        scrNFDetails.setViewportView(txtNFDetails);

        chkNFFound.setText("Bone Found");
        chkNFFound.setToolTipText("Check here to list the bone as found");

        org.jdesktop.layout.GroupLayout pnlNFDetailsLayout = new org.jdesktop.layout.GroupLayout(pnlNFDetails);
        pnlNFDetails.setLayout(pnlNFDetailsLayout);
        pnlNFDetailsLayout.setHorizontalGroup(
            pnlNFDetailsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(pnlNFDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .add(chkNFFound)
                .addContainerGap())
            .add(scrNFDetails, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
        );
        pnlNFDetailsLayout.setVerticalGroup(
            pnlNFDetailsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, pnlNFDetailsLayout.createSequentialGroup()
                .add(20, 20, 20)
                .add(chkNFFound)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(scrNFDetails, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE))
        );

        pnlNF.add(pnlNFDetails, java.awt.BorderLayout.CENTER);

        pnlFound.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Found", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        pnlFound.setLayout(new java.awt.BorderLayout());

        btnFUpdate.setMnemonic('d');
        btnFUpdate.setText("Update");
        btnFUpdate.setToolTipText("Update found list");

        btnFBack.setMnemonic('B');
        btnFBack.setText("Back");
        btnFBack.setToolTipText("Return to case summary");

        org.jdesktop.layout.GroupLayout pnlFButtonsLayout = new org.jdesktop.layout.GroupLayout(pnlFButtons);
        pnlFButtons.setLayout(pnlFButtonsLayout);
        pnlFButtonsLayout.setHorizontalGroup(
            pnlFButtonsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(pnlFButtonsLayout.createSequentialGroup()
                .addContainerGap(203, Short.MAX_VALUE)
                .add(btnFUpdate)
                .add(144, 144, 144)
                .add(btnFBack, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 88, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlFButtonsLayout.setVerticalGroup(
            pnlFButtonsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, pnlFButtonsLayout.createSequentialGroup()
                .add(0, 0, Short.MAX_VALUE)
                .add(pnlFButtonsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(btnFUpdate)
                    .add(btnFBack)))
        );

        pnlFound.add(pnlFButtons, java.awt.BorderLayout.PAGE_END);

        ddlFRegion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Full Body", "Head", "Torso", "Spine", "Pelvis", "Right Arm", "Left Arm", "Right Hand", "Left Hand", "Right Leg", "Left Leg", "Right Foot", "Left Foot", " " }));
        ddlFRegion.setToolTipText("Select the region of the body you want to focus on");

        lstFRegion.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lstFRegion.setToolTipText("List of bones found");
        scrFRegion.setViewportView(lstFRegion);

        lblFRegion.setText("Body Region");

        org.jdesktop.layout.GroupLayout pnlFRegionLayout = new org.jdesktop.layout.GroupLayout(pnlFRegion);
        pnlFRegion.setLayout(pnlFRegionLayout);
        pnlFRegionLayout.setHorizontalGroup(
            pnlFRegionLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(pnlFRegionLayout.createSequentialGroup()
                .addContainerGap()
                .add(lblFRegion)
                .addContainerGap())
            .add(ddlFRegion, 0, 202, Short.MAX_VALUE)
            .add(pnlFRegionLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(pnlFRegionLayout.createSequentialGroup()
                    .addContainerGap()
                    .add(scrFRegion, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 196, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
        );
        pnlFRegionLayout.setVerticalGroup(
            pnlFRegionLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(pnlFRegionLayout.createSequentialGroup()
                .addContainerGap()
                .add(lblFRegion)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(ddlFRegion, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(315, Short.MAX_VALUE))
            .add(pnlFRegionLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(org.jdesktop.layout.GroupLayout.TRAILING, pnlFRegionLayout.createSequentialGroup()
                    .add(60, 60, 60)
                    .add(scrFRegion, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)))
        );

        pnlFound.add(pnlFRegion, java.awt.BorderLayout.LINE_START);

        scrFDetails.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        txtFDetails.setColumns(20);
        txtFDetails.setRows(5);
        txtFDetails.setToolTipText("Observations of the bones");
        txtFDetails.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bone Details", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        scrFDetails.setViewportView(txtFDetails);

        chkFFound.setText("Bone Found");
        chkFFound.setToolTipText("Uncheck here to list the bone as not found");

        org.jdesktop.layout.GroupLayout pnlFDetailsLayout = new org.jdesktop.layout.GroupLayout(pnlFDetails);
        pnlFDetails.setLayout(pnlFDetailsLayout);
        pnlFDetailsLayout.setHorizontalGroup(
            pnlFDetailsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(pnlFDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .add(pnlFDetailsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(pnlFDetailsLayout.createSequentialGroup()
                        .add(chkFFound)
                        .add(0, 0, Short.MAX_VALUE))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, scrFDetails, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlFDetailsLayout.setVerticalGroup(
            pnlFDetailsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, pnlFDetailsLayout.createSequentialGroup()
                .add(22, 22, 22)
                .add(chkFFound)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(scrFDetails, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE))
        );

        pnlFound.add(pnlFDetails, java.awt.BorderLayout.CENTER);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(pnlLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .add(layout.createSequentialGroup()
                .add(pnlNF, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(pnlFound, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(pnlLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 57, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(pnlNF, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(pnlFound, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ddlNFRegionActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_ddlNFRegionActionPerformed
    {//GEN-HEADEREND:event_ddlNFRegionActionPerformed
	// TODO add your handling code here:
    }//GEN-LAST:event_ddlNFRegionActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFBack;
    private javax.swing.JButton btnFUpdate;
    private javax.swing.JButton btnNFUpdate;
    private javax.swing.JCheckBox chkFFound;
    private javax.swing.JCheckBox chkNFFound;
    private javax.swing.JComboBox ddlFRegion;
    private javax.swing.JComboBox ddlNFRegion;
    private javax.swing.JLabel lblCaseNum;
    private javax.swing.JLabel lblFRegion;
    private javax.swing.JLabel lblNFRegion;
    private javax.swing.JList lstFRegion;
    private javax.swing.JList lstNFRegion;
    private javax.swing.JPanel pnlFButtons;
    private javax.swing.JPanel pnlFDetails;
    private javax.swing.JPanel pnlFRegion;
    private javax.swing.JPanel pnlFound;
    private javax.swing.JPanel pnlLabel;
    private javax.swing.JPanel pnlNF;
    private javax.swing.JPanel pnlNFButtons;
    private javax.swing.JPanel pnlNFDetails;
    private javax.swing.JPanel pnlNFRegion;
    private javax.swing.JScrollPane scrFDetails;
    private javax.swing.JScrollPane scrFRegion;
    private javax.swing.JScrollPane scrNFDetails;
    private javax.swing.JScrollPane scrNFRegion;
    private javax.swing.JTextArea txtFDetails;
    private javax.swing.JTextArea txtNFDetails;
    // End of variables declaration//GEN-END:variables
}
