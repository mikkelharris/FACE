/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prog24178.project;

import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author mikkelharris
 */
public class FACEBoneDetails extends javax.swing.JFrame implements ActionListener, WindowListener, ListSelectionListener
{
    private DefaultListModel foundList;
    private DefaultListModel notFoundList;
    private BoneInfo bone;
    private ArrayList<BoneInfo> boneArrayListTemp;
    private PrintWriter boneFile;

    /**
     * Creates new form FACEBoneDetails
     */
    public FACEBoneDetails(String caseNum, ArrayList boneArray)
    {
	initComponents();
	btnFBack.addActionListener(this);
	btnFUpdate.addActionListener(this);
	btnNFUpdate.addActionListener(this);
	btnExit.addActionListener(this);
	lstFRegion.addListSelectionListener(this);
	lstNFRegion.addListSelectionListener(this);
	this.addWindowListener(this);
	
	lblCaseNum.setText(caseNum);
	boneArrayListTemp = boneArray;
	
	foundList = new DefaultListModel();
	notFoundList = new DefaultListModel();
	lstFRegion.setModel(foundList);
	lstNFRegion.setModel(notFoundList);
	
	createModel(foundList, boneArrayListTemp, true);
	createModel(notFoundList, boneArrayListTemp, false);
	
	try
	{
	    boneFile = new PrintWriter(new BufferedWriter(new FileWriter("data/bones.dat", true)));

	} 
	catch (IOException ex)
	{
	    // display an error dialog
	    JOptionPane.showMessageDialog(null,
		    "Error accessing data file:\n"
		    + ex.toString(), "Error",
		    JOptionPane.ERROR_MESSAGE);
	}
    }
    public FACEBoneDetails(){}
    
    private void createModel(DefaultListModel listModel, ArrayList arrayList, boolean status)
    {
	for (int i = 0; i < arrayList.size(); i++)
	{
	    bone = (BoneInfo)arrayList.get(i);
	    if (status == bone.isFoundStatus())
	    {
		listModel.addElement(bone.getBoneName());
	    }
	    
	}
    }
    public void clearModel(DefaultListModel listModel)
    {
	listModel.clear();
    }
    
    public void updateFile(String casesString)
    {
	
	for (int i = 0; i < boneArrayListTemp.size(); i++)
	{
	    BoneInfo bone = (BoneInfo)boneArrayListTemp.get(i);
	    boneFile.print(bone.toFileString());
	}
	boneFile.close();
    }
    
    @Override
    public void valueChanged(ListSelectionEvent event)
    {
	Object source = event.getSource();
	
	if (source == lstFRegion)
	{
	    if (lstFRegion.getSelectedIndex() >= 0)
	    {
		chkFFound.setSelected(true);
		for (int i = 0; i < boneArrayListTemp.size(); i++)
		{
		    bone = (BoneInfo)boneArrayListTemp.get(i);
		    if (bone.getBoneName().equals(lstFRegion.getModel().getElementAt(lstFRegion.getSelectedIndex())))
		    {
			txtFDetails.setText(bone.getCondition());
		    }   
		}
	    }
	}
	else if (source == lstNFRegion)
	{
	    if (lstNFRegion.getSelectedIndex() >= 0)
	    {
		chkNFFound.setSelected(false);
		for (int i = 0; i < boneArrayListTemp.size(); i++)
		{
		    bone = (BoneInfo)boneArrayListTemp.get(i);
		    if (bone.getBoneName().equals(lstNFRegion.getModel().getElementAt(lstNFRegion.getSelectedIndex())))
		    {
			txtNFDetails.setText(bone.getCondition());
		    }   
		}
	    }
	}
    }
    @Override
    public void actionPerformed(ActionEvent event)
    {
	Object source = event.getSource();
	
	if (source == btnExit)
	{
	    updateFile("data/bones.dat");
	    FACEStart faceStart = new FACEStart();
	    faceStart.pack();
	    faceStart.setVisible(true);
	    this.dispose();
	}
	else if (source == btnFBack)
	{
	    updateFile("data/bones.dat");
	    FACECaseSummary faceCaseSummary = new FACECaseSummary(lblCaseNum.getText());
	    faceCaseSummary.pack();
	    faceCaseSummary.setVisible(true);
	    this.dispose();
	}
	else if (source == btnFUpdate)
	{
	    for (int i = 0; i < boneArrayListTemp.size(); i++)
	    {
		bone = (BoneInfo)boneArrayListTemp.get(i);
		if (bone.getBoneName().equals(lstFRegion.getModel().getElementAt(lstFRegion.getSelectedIndex())))
		{
		    bone.setCondition(txtFDetails.getText());
		    bone.setFoundStatus(chkFFound.isSelected());
		}   
	    }
	    foundList.clear();
	    notFoundList.clear();
	    createModel(foundList, boneArrayListTemp, true);
	    createModel(notFoundList, boneArrayListTemp, false);
	    txtFDetails.setText("");
	    chkFFound.setSelected(false);
	}
	else if (source == btnNFUpdate)
	{
	    for (int i = 0; i < boneArrayListTemp.size(); i++)
	    {
		bone = (BoneInfo)boneArrayListTemp.get(i);
		if (bone.getBoneName().equals(lstNFRegion.getModel().getElementAt(lstNFRegion.getSelectedIndex())))
		{
		    bone.setCondition(txtNFDetails.getText());
		    bone.setFoundStatus(chkNFFound.isSelected());
		}   
	    }
	    foundList.clear();
	    notFoundList.clear();
	    createModel(foundList, boneArrayListTemp, true);
	    createModel(notFoundList, boneArrayListTemp, false);
	    txtNFDetails.setText("");
	    chkNFFound.setSelected(false);
	}
    }
   
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
    public void windowClosing(WindowEvent event)
    {
	int exit = JOptionPane.showConfirmDialog(null, "Are you sure you wnat to exit the case?", "Exit Case", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
	if (exit == JOptionPane.YES_OPTION)
	{
	    updateFile("data/bones.dat");
	    FACEStart faceStart = new FACEStart();
	    faceStart.pack();
	    faceStart.setVisible(true);
	    this.dispose();
	}
    }
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

        pnlLabel = new javax.swing.JPanel();
        lblCaseNum = new javax.swing.JLabel();
        pnlNF = new javax.swing.JPanel();
        pnlNFRegion = new javax.swing.JPanel();
        ddlNFRegion = new javax.swing.JComboBox();
        scrNFRegion = new javax.swing.JScrollPane();
        lstNFRegion = new javax.swing.JList();
        jLabel2 = new javax.swing.JLabel();
        pnlNFButtons = new javax.swing.JPanel();
        btnNFUpdate = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
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
        jLabel3 = new javax.swing.JLabel();
        pnlFDetails = new javax.swing.JPanel();
        scrFDetails = new javax.swing.JScrollPane();
        txtFDetails = new javax.swing.JTextArea();
        chkFFound = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        pnlLabel.setLayout(new java.awt.BorderLayout());

        lblCaseNum.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCaseNum.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Case Number", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        pnlLabel.add(lblCaseNum, java.awt.BorderLayout.CENTER);

        pnlNF.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Not Found", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        pnlNF.setLayout(new java.awt.BorderLayout());

        ddlNFRegion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        scrNFRegion.setViewportView(lstNFRegion);

        jLabel2.setText("Body Region");

        org.jdesktop.layout.GroupLayout pnlNFRegionLayout = new org.jdesktop.layout.GroupLayout(pnlNFRegion);
        pnlNFRegion.setLayout(pnlNFRegionLayout);
        pnlNFRegionLayout.setHorizontalGroup(
            pnlNFRegionLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(pnlNFRegionLayout.createSequentialGroup()
                .add(pnlNFRegionLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(pnlNFRegionLayout.createSequentialGroup()
                        .add(6, 6, 6)
                        .add(scrNFRegion, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .add(pnlNFRegionLayout.createSequentialGroup()
                        .addContainerGap()
                        .add(jLabel2)
                        .add(0, 0, Short.MAX_VALUE))
                    .add(ddlNFRegion, 0, 114, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlNFRegionLayout.setVerticalGroup(
            pnlNFRegionLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(pnlNFRegionLayout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel2)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(ddlNFRegion, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 24, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(scrNFRegion, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE))
        );

        pnlNF.add(pnlNFRegion, java.awt.BorderLayout.LINE_START);

        btnNFUpdate.setText("Update");

        btnExit.setText("Exit");

        org.jdesktop.layout.GroupLayout pnlNFButtonsLayout = new org.jdesktop.layout.GroupLayout(pnlNFButtons);
        pnlNFButtons.setLayout(pnlNFButtonsLayout);
        pnlNFButtonsLayout.setHorizontalGroup(
            pnlNFButtonsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(pnlNFButtonsLayout.createSequentialGroup()
                .add(131, 131, 131)
                .add(btnNFUpdate)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 94, Short.MAX_VALUE)
                .add(btnExit)
                .addContainerGap())
        );
        pnlNFButtonsLayout.setVerticalGroup(
            pnlNFButtonsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, pnlNFButtonsLayout.createSequentialGroup()
                .add(0, 0, Short.MAX_VALUE)
                .add(pnlNFButtonsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(btnNFUpdate)
                    .add(btnExit)))
        );

        pnlNF.add(pnlNFButtons, java.awt.BorderLayout.PAGE_END);

        scrNFDetails.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        txtNFDetails.setColumns(20);
        txtNFDetails.setRows(5);
        txtNFDetails.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bone Details", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        scrNFDetails.setViewportView(txtNFDetails);

        chkNFFound.setText("Bone Found");

        org.jdesktop.layout.GroupLayout pnlNFDetailsLayout = new org.jdesktop.layout.GroupLayout(pnlNFDetails);
        pnlNFDetails.setLayout(pnlNFDetailsLayout);
        pnlNFDetailsLayout.setHorizontalGroup(
            pnlNFDetailsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(pnlNFDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .add(chkNFFound)
                .addContainerGap())
            .add(scrNFDetails, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
        );
        pnlNFDetailsLayout.setVerticalGroup(
            pnlNFDetailsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, pnlNFDetailsLayout.createSequentialGroup()
                .add(20, 20, 20)
                .add(chkNFFound)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(scrNFDetails, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE))
        );

        pnlNF.add(pnlNFDetails, java.awt.BorderLayout.CENTER);

        pnlFound.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Found", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        pnlFound.setLayout(new java.awt.BorderLayout());

        btnFUpdate.setText("Update");

        btnFBack.setText("Back");

        org.jdesktop.layout.GroupLayout pnlFButtonsLayout = new org.jdesktop.layout.GroupLayout(pnlFButtons);
        pnlFButtons.setLayout(pnlFButtonsLayout);
        pnlFButtonsLayout.setHorizontalGroup(
            pnlFButtonsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(pnlFButtonsLayout.createSequentialGroup()
                .add(131, 131, 131)
                .add(btnFUpdate)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 93, Short.MAX_VALUE)
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

        ddlFRegion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        scrFRegion.setViewportView(lstFRegion);

        jLabel3.setText("Body Region");

        org.jdesktop.layout.GroupLayout pnlFRegionLayout = new org.jdesktop.layout.GroupLayout(pnlFRegion);
        pnlFRegion.setLayout(pnlFRegionLayout);
        pnlFRegionLayout.setHorizontalGroup(
            pnlFRegionLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(pnlFRegionLayout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel3)
                .addContainerGap())
            .add(ddlFRegion, 0, 117, Short.MAX_VALUE)
            .add(pnlFRegionLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(pnlFRegionLayout.createSequentialGroup()
                    .addContainerGap()
                    .add(scrFRegion, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 105, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        pnlFRegionLayout.setVerticalGroup(
            pnlFRegionLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(pnlFRegionLayout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel3)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(ddlFRegion, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(199, Short.MAX_VALUE))
            .add(pnlFRegionLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(org.jdesktop.layout.GroupLayout.TRAILING, pnlFRegionLayout.createSequentialGroup()
                    .add(0, 59, Short.MAX_VALUE)
                    .add(scrFRegion, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 195, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
        );

        pnlFound.add(pnlFRegion, java.awt.BorderLayout.LINE_START);

        scrFDetails.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        txtFDetails.setColumns(20);
        txtFDetails.setRows(5);
        txtFDetails.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bone Details", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        scrFDetails.setViewportView(txtFDetails);

        chkFFound.setText("Bone Found");

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
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, pnlFDetailsLayout.createSequentialGroup()
                        .add(0, 0, Short.MAX_VALUE)
                        .add(scrFDetails, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 277, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlFDetailsLayout.setVerticalGroup(
            pnlFDetailsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, pnlFDetailsLayout.createSequentialGroup()
                .add(22, 22, 22)
                .add(chkFFound)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(scrFDetails, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE))
        );

        pnlFound.add(pnlFDetails, java.awt.BorderLayout.CENTER);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(pnlLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .add(layout.createSequentialGroup()
                .add(pnlNF, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 406, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(pnlFound, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(pnlLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 57, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(pnlNF, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(pnlFound, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnFBack;
    private javax.swing.JButton btnFUpdate;
    private javax.swing.JButton btnNFUpdate;
    private javax.swing.JCheckBox chkFFound;
    private javax.swing.JCheckBox chkNFFound;
    private javax.swing.JComboBox ddlFRegion;
    private javax.swing.JComboBox ddlNFRegion;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lblCaseNum;
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
