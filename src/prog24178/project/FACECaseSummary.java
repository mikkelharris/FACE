 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prog24178.project;

import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

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
    private ArrayList<BoneInfo> boneArray = new ArrayList<BoneInfo>(210);
    private ArrayList<BoneInfo> completeBoneArray = new ArrayList<BoneInfo>();
    private ArrayList<BoneInfo> boneArrayListTemp = new ArrayList<BoneInfo>();
    DefaultTableModel remainsModel = new DefaultTableModel();

    /**
     * Creates new form FACE1
     * @param caseNum 
     */
    public FACECaseSummary(String caseNum)
    {
	initComponents();
	btnEdit.addActionListener(this);
	btnExitCase.addActionListener(this);
	this.addWindowListener(this);
	this.setLocationRelativeTo(null);

	lblCaseNumber.setText(caseNum);
	tblRemains.setModel(remainsModel);

	remainsModel.setColumnIdentifiers(new String[] {"Bone", "Condition"});
	int width1 = 175;
	int width2 = (tblRemains.getWidth() - width1);
	TableColumn col1 = tblRemains.getColumnModel().getColumn(0);
	TableColumn col2 = tblRemains.getColumnModel().getColumn(1);
	col1.setPreferredWidth(width1);
	col2.setPreferredWidth(width2);

	createArray("data/bones.dat", caseNum);
	for (int i = 0; i < boneArray.size(); i++)
	{
	    BoneInfo bone = (BoneInfo) boneArray.get(i);
	    if (bone.isFoundStatus())
	    {
		remainsModel.addRow(new String[] {bone.getBoneName(), bone.getCondition()});
	    }
	}

	overWriteFile("data/bones.dat");
    }

    private void createArray(String casefile, String caseNum)
    {
	try
	{
	    boneFileTemp = new PrintWriter(new BufferedWriter(new FileWriter("data/bonesTemp.dat", false)));

	} catch (Exception ex)
	{
	    // display an error dialog
	    JOptionPane.showMessageDialog(this,
		    "Error accessing bonesTemp.dat:\n"
		    + ex.toString(), "Error",
		    JOptionPane.ERROR_MESSAGE);
	}
	try
	{
	    Scanner fileIn = new Scanner(new File(casefile));
	    fileIn.useDelimiter("endOfLine\n");
	    while (fileIn.hasNext())
	    {

		String record = fileIn.next();
		String[] fields = record.split("\\s*\\|\\s*");
		BoneInfo bone = new BoneInfo(fields[0], fields[1], fields[2],
			fields[3], Boolean.parseBoolean(fields[4]));
		if (fields[0].equals(caseNum))
		{
		    boneArray.add(bone);
		    boneFileTemp.print(bone.toFileString());
		} else
		{
		    completeBoneArray.add(bone);
		}
	    }
	    fileIn.close();
	    fileIn = null;
	} catch (Exception ex)
	{
	    JOptionPane.showMessageDialog(this,
		    "Error extracting bones from bones.dat:\n"
		    + ex.toString(), "Error",
		    JOptionPane.ERROR_MESSAGE);
	}
	boneFileTemp.close();
    }

    private void updateFile(String casesString)
    {
	try
	{
	    boneFileAppend = new PrintWriter(new BufferedWriter(new FileWriter("data/bones.dat", true)));

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
	    Scanner fileIn = new Scanner(new File("data/bonesTemp.dat"));
	    fileIn.useDelimiter("endOfLine\n");

	    while (fileIn.hasNext())
	    {
		String record = fileIn.next();
		String[] fields = record.split("\\s*\\|\\s*");
		BoneInfo bone = new BoneInfo(fields[0], fields[1], fields[2],
			fields[3], Boolean.parseBoolean(fields[4]));
		boneArrayListTemp.add(bone);
	    }
	} catch (Exception ex)
	{
	    JOptionPane.showMessageDialog(this,
		    "Error creating temp file:\n"
		    + ex.toString(), "Error",
		    JOptionPane.ERROR_MESSAGE);
	}
	for (int i = 0; i < boneArrayListTemp.size(); i++)
	{
	    BoneInfo bone = (BoneInfo) boneArrayListTemp.get(i);
	    boneFileAppend.print(bone.toFileString());
	}
	boneFileAppend.close();
    }

    private void overWriteFile(String casefile)
    {
	try
	{
	    boneFileOverwrite = new PrintWriter(new BufferedWriter(new FileWriter("data/bones.dat", false)));
	} catch (Exception ex)
	{
	    JOptionPane.showMessageDialog(this,
		    "Error overwriting bones.dat:\n"
		    + ex.toString(), "Error",
		    JOptionPane.ERROR_MESSAGE);
	}
	for (int i = 0; i < completeBoneArray.size(); i++)
	{
	    boneFileOverwrite.print(completeBoneArray.get(i).toFileString());
	}
	boneFileOverwrite.close();
    }

    /**
     * 
     * @param event
     */
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
	} else if (source == btnExitCase)
	{
	    updateFile("data/bones.dat");
	    FACEStart faceStart = new FACEStart();
	    faceStart.pack();
	    faceStart.setVisible(true);
	    this.dispose();
	}
    }

    /**
     * 
     * @param event
     */
    @Override
    public void windowClosing(WindowEvent event)
    {
	int exit = JOptionPane.showConfirmDialog(this, "Are you sure you wnat to exit the case?",
		"Exit Case", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
	if (exit == JOptionPane.YES_OPTION)
	{
	    updateFile("data/bones.dat");
	    FACEStart faceStart = new FACEStart();
	    faceStart.pack();
	    faceStart.setVisible(true);
	    this.dispose();
	}
    }

    /**
     * 
     * @param event
     */
    @Override
    public void windowDeactivated(WindowEvent event)
    {
    }

    /**
     * 
     * @param event
     */
    @Override
    public void windowActivated(WindowEvent event)
    {
    }

    /**
     * 
     * @param event
     */
    @Override
    public void windowDeiconified(WindowEvent event)
    {
    }

    /**
     * 
     * @param event
     */
    @Override
    public void windowIconified(WindowEvent event)
    {
    }

    /**
     * 
     * @param event
     */
    @Override
    public void windowClosed(WindowEvent event)
    {
    }

    /**
     * 
     * @param event
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
