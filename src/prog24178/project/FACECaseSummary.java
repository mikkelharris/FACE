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
public class FACECaseSummary extends javax.swing.JFrame implements ActionListener, WindowListener
{
    // private PrintWriter caseLog;
    private PrintWriter boneFileTemp;
    private PrintWriter boneFileOverwrite;
    private PrintWriter boneFileAppend;
    
    ArrayList<BoneInfo> boneArray = new ArrayList<BoneInfo>(210);
    ArrayList<BoneInfo> completeBoneArray = new ArrayList<BoneInfo>();
    ArrayList<BoneInfo> boneArrayListTemp = new ArrayList<BoneInfo>();
    /**
     * Creates new form FACE1
     */
    
    public FACECaseSummary(String caseNum)
    {
	initComponents();
	btnEdit.addActionListener(this);
	btnExitCase.addActionListener(this);
	this.addWindowListener(this);
	
	lblCaseNumber.setText(caseNum);
	txtRemainsList.setText(String.format("%-30s %-25s\n\n", "Bone","Details"));
	
	createArray("data/bones.dat", caseNum);
	for (int i = 0; i < boneArray.size(); i++)
	{
	    BoneInfo bone = (BoneInfo)boneArray.get(i);
	    if (bone.isFoundStatus())
	    {
		txtRemainsList.append(String.format("%-30s %-25s\n",bone.getBoneName(), bone.getCondition()));
	    }
	    
	    
	}
	
	overWriteFile("data/bones.dat");
	txtRemainsList.setEditable(false);
	
    }
    public FACECaseSummary()
    {
	initComponents();
	btnEdit.addActionListener(this);
	btnExitCase.addActionListener(this);
	this.addWindowListener(this);
    }

    @Override
   
    public void actionPerformed(ActionEvent event)
    {
	Object source = event.getSource();
	
	if (source == btnEdit)
	{
	    FACEBoneDetails faceBoneDetails = new FACEBoneDetails(lblCaseNumber.getText(), boneArray);
	    faceBoneDetails.pack();
	    faceBoneDetails.setVisible(true);
	    this.dispose();
	}
	else if (source == btnExitCase)
	{
	    updateFile("data/bones.dat");
	    FACEStart faceStart = new FACEStart();
	    faceStart.pack();
	    faceStart.setVisible(true);
	    this.dispose();
	}
    }
    
    private void createArray(String casefile, String caseNum)
    {
	try
	{
	    boneFileTemp = new PrintWriter(new BufferedWriter(new FileWriter("data/bonesTemp.dat", false)));

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
	    Scanner fileIn = new Scanner(new File(casefile));
	    fileIn.useDelimiter(System.getProperty("line.separator"));
	    
	    while (fileIn.hasNext())
	    {
		String record = fileIn.next();
		String[] fields = record.split("\\s*\\|\\s*");
		BoneInfo bone = new BoneInfo(fields[0], fields[1], fields[2], 
			fields[3], Boolean.parseBoolean(fields[4]));
		if (fields[0].trim().equals(caseNum))
		{
		    boneArray.add(bone);
		    boneFileTemp.print(bone.toFileString()); 
		}
		else
		{
		    completeBoneArray.add(bone);
		}
	    }
	}
	catch (IOException ex){}
	boneFileTemp.close();
    }
    
    public void updateFile(String casesString)
    {
	try
	{
	    boneFileAppend = new PrintWriter(new BufferedWriter(new FileWriter("data/bones.dat", true)));

	} 
	catch (IOException ex)
	{
	    // display an error dialog
	    JOptionPane.showMessageDialog(null,
		    "Error accessing data file:\n"
		    + ex.toString(), "Error",
		    JOptionPane.ERROR_MESSAGE);
	}
	try
	{
	    Scanner fileIn = new Scanner(new File("data/bonesTemp.dat"));
	    fileIn.useDelimiter(System.getProperty("line.separator"));
	    
	    while (fileIn.hasNext())
	    {
		String record = fileIn.next();
		String[] fields = record.split("\\s*\\|\\s*");
		BoneInfo bone = new BoneInfo(fields[0], fields[1], fields[2], 
			fields[3], Boolean.parseBoolean(fields[4]));
		boneArrayListTemp.add(bone);
	    }
	}
	catch (IOException ex)
	{
	    
	}
	for (int i = 0; i < boneArrayListTemp.size(); i++)
	{
	    BoneInfo bone = (BoneInfo)boneArrayListTemp.get(i);
	    boneFileAppend.print(bone.toFileString());
	}
	boneFileAppend.close();
    }
    
    public void overWriteFile(String casefile)
    {
	try
	{
	    boneFileOverwrite = new PrintWriter(new BufferedWriter(new FileWriter("data/bones.dat", false)));
	} 
	catch (IOException e)
	{
	}
	for (int i = 0; i < completeBoneArray.size(); i++)
	{
	    boneFileOverwrite.print(completeBoneArray.get(i).toFileString());
	}
	boneFileOverwrite.close();
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
     * : Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollBar1 = new javax.swing.JScrollBar();
        pnlDisplay = new javax.swing.JPanel();
        scrRemainsList = new javax.swing.JScrollPane();
        txtRemainsList = new javax.swing.JTextArea();
        pnlCaseNumber = new javax.swing.JPanel();
        lblCaseNumber = new javax.swing.JLabel();
        pnlButtons = new javax.swing.JPanel();
        btnExitCase = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Forensic Anthropology Case Evidence  (FACE) ");
        setSize(new java.awt.Dimension(600, 450));

        scrRemainsList.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        txtRemainsList.setColumns(20);
        txtRemainsList.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtRemainsList.setRows(5);
        txtRemainsList.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Remains Recovered", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        scrRemainsList.setViewportView(txtRemainsList);

        org.jdesktop.layout.GroupLayout pnlDisplayLayout = new org.jdesktop.layout.GroupLayout(pnlDisplay);
        pnlDisplay.setLayout(pnlDisplayLayout);
        pnlDisplayLayout.setHorizontalGroup(
            pnlDisplayLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(pnlDisplayLayout.createSequentialGroup()
                .addContainerGap()
                .add(scrRemainsList, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 629, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlDisplayLayout.setVerticalGroup(
            pnlDisplayLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(pnlDisplayLayout.createSequentialGroup()
                .add(scrRemainsList, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                .add(8, 8, 8))
        );

        getContentPane().add(pnlDisplay, java.awt.BorderLayout.CENTER);

        lblCaseNumber.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCaseNumber.setText(" ");
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

        btnExitCase.setText("Exit Case");

        btnEdit.setText("Edit");

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
    private javax.swing.JScrollPane scrRemainsList;
    private javax.swing.JTextArea txtRemainsList;
    // End of variables declaration//GEN-END:variables
}
