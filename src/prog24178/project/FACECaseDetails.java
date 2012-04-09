 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prog24178.project;

import java.awt.Window;
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
    CaseInfo caseInfo;
    BoneInfo boneInfo;
    private ArrayList boneArrayList = new ArrayList();
    
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
	
	this.addWindowListener(this);
	
	try
	{
	    caseLog = new PrintWriter(new BufferedWriter(new FileWriter("case.dat", true)));

	} catch (IOException ex)
	{
	    // display an error dialog
	    JOptionPane.showMessageDialog(null,
		    "Error accessing data file:\n"
		    + ex.toString(), "Error",
		    JOptionPane.ERROR_MESSAGE);
	}
	try
	{
	    boneLog = new PrintWriter(new BufferedWriter(new FileWriter("bones.dat", true)));

	} catch (IOException ex)
	{
	    // display an error dialog
	    JOptionPane.showMessageDialog(null,
		    "Error accessing data file:\n"
		    + ex.toString(), "Error",
		    JOptionPane.ERROR_MESSAGE);
	}
    }

    @Override
    public void actionPerformed(ActionEvent event)
    {
	Object source = event.getSource();
	
	
	Scanner caseIn;
	
	if (source == btnCreateCase)
	{
	    
	    String caseNum = createCaseNumber();
	    createCase(createCaseNumber(), (String)ddlDay.getSelectedItem(), 
		    (String)ddlMonth.getSelectedItem(), 
		    (String)ddlYear.getSelectedItem(), txtLocation.getText());
	    caseLog.print(caseInfo.toFileString());
	    
	    printBonesArrayList(createBones(caseNum));
	    
	    caseLog.close();
	    boneLog.close();
	    
	    FACECaseSummary faceCaseSummary = new FACECaseSummary(caseNum);
	    faceCaseSummary.pack();
	    faceCaseSummary.setVisible(true);
	    this.setVisible(false);
	    
	}
	else if (source == btnBack)
	{
	    FACEStart faceStart = new FACEStart();
	    faceStart.pack();
	    faceStart.setVisible(true);
	    this.setVisible(false);
	}
    }
    
    private String createCaseNumber()
    {
	return (String)ddlDay.getSelectedItem() + (String)ddlMonth.getSelectedItem() + (String)ddlYear.getSelectedItem() + txtLocation.getText();
    }
    private Object createCase(String caseNumber, String caseDateDay, String caseDateMonth, String caseDateYear, String caseLocation)
    {
	caseInfo = new CaseInfo(caseNumber, caseDateDay, caseDateMonth, caseDateYear, caseLocation);
	return caseInfo;
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
	    Scanner fileIn = new Scanner(new File("boneTemplate.dat"));
	    fileIn.useDelimiter(System.getProperty("line.separator"));
	    
	    while (fileIn.hasNext())
	    {
		String record = fileIn.next();
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
	catch (IOException ex){}
	/*
	
	BoneInfo frontal = new BoneInfo(caseNumber, "Head", "Frontal", "Please Enter Details", false);
	boneArrayList.add(frontal);
	BoneInfo parietalLeft = new BoneInfo(caseNumber, "Head", "Parietal Left", "Please Enter Details", false);
	boneArrayList.add(parietalLeft);
	BoneInfo parietalRight = new BoneInfo(caseNumber, "Head", "Parietal Right", "Please Enter Details", false);
	boneArrayList.add(parietalRight);
	BoneInfo temporalLeft = new BoneInfo(caseNumber, "Head", "Temporal Left", "Please Enter Details", false);
	boneArrayList.add(temporalLeft);
	BoneInfo temporalRight = new BoneInfo(caseNumber, "Head", "Temporal Right", "Please Enter Details", false);
	boneArrayList.add(temporalRight);
	BoneInfo occipital = new BoneInfo(caseNumber, "Head", "Occipital", "Please Enter Details", false);
	boneArrayList.add(occipital);
	BoneInfo sphenoid = new BoneInfo(caseNumber, "Head", "Sphenoid", "Please Enter Details", false);
	boneArrayList.add(sphenoid);
	BoneInfo ethmoid = new BoneInfo(caseNumber, "Head", "Ethmoid", "Please Enter Details", false);
	boneArrayList.add(ethmoid);
	*/
	return boneArrayList;
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

        jScrollBar1 = new javax.swing.JScrollBar();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblDate = new javax.swing.JLabel();
        lblLocation = new javax.swing.JLabel();
        txtLocation = new javax.swing.JTextField();
        btnBack = new javax.swing.JButton();
        btnCreateCase = new javax.swing.JButton();
        ddlDay = new javax.swing.JComboBox();
        ddlMonth = new javax.swing.JComboBox();
        ddlYear = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Forensic Anthropology Case Evidence  (FACE) ");
        setSize(new java.awt.Dimension(600, 450));

        jLabel1.setText("Case Details");

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jLabel1)
                .add(137, 137, 137))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .add(jLabel1)
                .add(0, 8, Short.MAX_VALUE))
        );

        lblDate.setText("Date:");

        lblLocation.setText("Location:");

        btnBack.setText("Back");

        btnCreateCase.setText("Create Case");

        ddlDay.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Day", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        ddlMonth.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Month", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));

        ddlYear.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Year", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020" }));

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
            .add(jPanel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 103, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
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
	    java.util.logging.Logger.getLogger(FACECaseDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (InstantiationException ex)
	{
	    java.util.logging.Logger.getLogger(FACECaseDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (IllegalAccessException ex)
	{
	    java.util.logging.Logger.getLogger(FACECaseDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (javax.swing.UnsupportedLookAndFeelException ex)
	{
	    java.util.logging.Logger.getLogger(FACECaseDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	}
	//</editor-fold>

	/*
	 * Create and display the form
	 */
	java.awt.EventQueue.invokeLater(new Runnable()
	{

	    public void run()
	    {
		new FACECaseDetails().setVisible(true);
	    }
	});
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnCreateCase;
    private javax.swing.JComboBox ddlDay;
    private javax.swing.JComboBox ddlMonth;
    private javax.swing.JComboBox ddlYear;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollBar jScrollBar1;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblLocation;
    private javax.swing.JTextField txtLocation;
    // End of variables declaration//GEN-END:variables
}
