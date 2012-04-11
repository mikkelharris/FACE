 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prog24178.project;

import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author mikkelharris
 */
public class FACECaseDetails extends javax.swing.JFrame implements ActionListener, WindowListener
{
    private PrintWriter caseLog;
    private PrintWriter boneLog;
    private CaseInfo caseInfo;
    private ArrayList<BoneInfo> boneArrayList = new ArrayList<BoneInfo>();
    
    /**
     * Creates new form FACECaseDetails
     */
    public FACECaseDetails()
    {
	initComponents();
	btnCreateCase.addActionListener(this);
	btnBack.addActionListener(this);
	ddlDay.addActionListener(this);
	ddlMonth.addActionListener(this);
	ddlYear.addActionListener(this);
	ddlYear.addItem((Object)"Year");
	this.addWindowListener(this);
	
	for (int i = 1900; i <= 2100; i++)
	{
	    ddlYear.addItem((Object)i);
	}

	try
	{
	    caseLog = new PrintWriter(new BufferedWriter(new FileWriter("data/case.dat", true)));

	} catch (Exception ex)
	{
	    // display an error dialog
	    JOptionPane.showMessageDialog(this,
		    "Error accessing data file:\n"
		    + ex.toString(), "Error",
		    JOptionPane.ERROR_MESSAGE);
	}
	try
	{
	    boneLog = new PrintWriter(new BufferedWriter(new FileWriter("data/bones.dat", true)));

	} catch (Exception ex)
	{
	    // display an error dialog
	    JOptionPane.showMessageDialog(this,
		    "Error accessing data file:\n"
		    + ex.toString(), "Error",
		    JOptionPane.ERROR_MESSAGE);
	}
    }

    
    
    
    private String createCaseNumber()
    {
	return (String)ddlDay.getSelectedItem() + (String)ddlMonth.getSelectedItem() + ddlYear.getSelectedItem().toString() + txtLocation.getText();
    }
    
    private void printBonesArrayList(ArrayList boneArrayList)
    {
	for (int i = 0; i < boneArrayList.size(); i++)
	{
	    BoneInfo bone = (BoneInfo)boneArrayList.get(i);
	    boneLog.print(bone.toFileString());
	}
    }
    private ArrayList createBones(String caseNumber)
    {
	try
	{
	    Scanner fileIn = new Scanner(new File("data/boneTemplate.dat"));
	    
	    while (fileIn.hasNext())
	    {
		String record = fileIn.nextLine();
		String[] fields = record.split("\\s*\\|\\s*");
		BoneInfo bone = new BoneInfo(fields[0], fields[1], fields[2], 
			fields[3], Boolean.parseBoolean(fields[4]));
		boneArrayList.add(bone);
		for (int i = 0; i < boneArrayList.size(); i++)
		{
		    BoneInfo boneTemp = (BoneInfo)boneArrayList.get(i);
		    boneTemp.setCaseNumber(caseNumber);
		}
	    }
	}
	catch (Exception ex)
	{
	    JOptionPane.showMessageDialog(this,
		    "Error:\n"
		    + ex.toString(), "Error",
		    JOptionPane.ERROR_MESSAGE);
	}
	return boneArrayList;
    }
    @Override
    public void actionPerformed(ActionEvent event)
    {
	Object source = event.getSource();
	
	if (source == btnCreateCase)
	{
	    try
	    {
		if (ddlDay.getSelectedIndex() == 0)
		{
		    JOptionPane.showMessageDialog(this, "Please select a Day\n", 
			    "Error", JOptionPane.ERROR_MESSAGE);
		}
		else 
		{
		    if (ddlMonth.getSelectedIndex() == 0)
		    {
			JOptionPane.showMessageDialog(this, "Please select a Month\n",
				"Error", JOptionPane.ERROR_MESSAGE);
		    }
		    else 
		    {
			if ("".equals(txtLocation.getText()))
			{
			    JOptionPane.showMessageDialog(this, "Please enter a Location\n"
			    , "Error", JOptionPane.ERROR_MESSAGE);
			}
			else 
			{
			    String caseNum = createCaseNumber();
			    caseInfo = new CaseInfo(createCaseNumber(), (String)ddlDay.getSelectedItem(), 
			    (String)ddlMonth.getSelectedItem(), 
			    Integer.parseInt(ddlYear.getSelectedItem().toString()), txtLocation.getText());
			    
			    caseLog.print(caseInfo.toFileString());
			    printBonesArrayList(createBones(caseNum));

			    caseLog.close();
			    boneLog.close();

			    FACECaseSummary faceCaseSummary = new FACECaseSummary(caseNum);
			    faceCaseSummary.pack();
			    faceCaseSummary.setVisible(true);
			    this.dispose();
			}	
		    }
		}	
	    } 
	    catch (NumberFormatException ex)
	    {
		JOptionPane.showMessageDialog(this, "Please select a Year\n",
			"Error", JOptionPane.ERROR_MESSAGE);
	    } 
	}
	else if (source == btnBack)
	{
	    FACEStart faceStart = new FACEStart();
	    faceStart.pack();
	    faceStart.setVisible(true);
	    this.dispose();
	}
    }
    @Override
    public void windowClosing(WindowEvent event)
    {
	int exit = JOptionPane.showConfirmDialog(this, "Do you want to cancel creating a case?", 
		"Cancel Case Creation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
	if (exit == JOptionPane.YES_OPTION)
	{
	    FACEStart faceStart = new FACEStart();
	    faceStart.pack();
	    faceStart.setVisible(true);
	    this.dispose();
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
    public void windowOpened(WindowEvent event){}
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

        btnBack.setText("Back");

        btnCreateCase.setText("Create Case");

        ddlDay.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Day", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        ddlMonth.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Month", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));

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
