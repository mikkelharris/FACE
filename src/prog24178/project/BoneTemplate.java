/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prog24178.project;

import java.awt.event.*;
import java.io.*;
import javax.swing.JOptionPane;

/**
 *
 * @author mikkelharris
 */
public class BoneTemplate extends javax.swing.JFrame implements ActionListener
{
    private  PrintWriter boneTemplate;
    BoneInfo boneCase;
    /**
     * Creates new form BoneTemplate
     */
    public BoneTemplate()
    {
	initComponents();
	ddlBodyRegion.addActionListener(this);
	btnAdd.addActionListener(this);
	btnDone.addActionListener(this);
	
	try
	{
	    boneTemplate = new PrintWriter(new BufferedWriter(new FileWriter("data/boneTemplate.dat", true)));

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
    public  void actionPerformed(ActionEvent event)
    {
	Object source = event.getSource();
	
	if (source == btnAdd)
	{
	   createBoneTemplate(txtCaseNum.getText(), (String)ddlBodyRegion.getSelectedItem(), txtBoneName.getText(), txtCondition.getText(), chkIsFound.isSelected());
	   boneTemplate.print(boneCase.toFileString());
	   resetForm();
	   
	   
	}
	if (source == btnDone)
	{
	    boneTemplate.close();
	}
    }
    
    private Object createBoneTemplate(String caseNumber, String bodyRegion, String boneName, String condition, boolean foundStatus)
    {
	boneCase = new BoneInfo(caseNumber, bodyRegion, boneName, condition, foundStatus);
	return boneCase;
    }
    private void resetForm()
    {
	txtBoneName.setText("");
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ddlBodyRegion = new javax.swing.JComboBox();
        lblCaseNum = new javax.swing.JLabel();
        txtCaseNum = new javax.swing.JTextField();
        lblBoneName = new javax.swing.JLabel();
        txtBoneName = new javax.swing.JTextField();
        txtCondition = new javax.swing.JTextField();
        lblCondition = new javax.swing.JLabel();
        chkIsFound = new javax.swing.JCheckBox();
        btnAdd = new javax.swing.JButton();
        btnDone = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        ddlBodyRegion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Head", "Torso", "Spine", "Pelvis", "Right Arm", "Left Arm", "Right Hand", "Left Hand", "Right Leg", "Left Leg", "Right Foot", "Left Foot", " " }));

        lblCaseNum.setText("caseNum:");

        txtCaseNum.setText("Template");
        txtCaseNum.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtCaseNum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCaseNumActionPerformed(evt);
            }
        });

        lblBoneName.setText("boneName:");

        txtBoneName.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtCondition.setText("Please enter the condition of the bone.");
        txtCondition.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblCondition.setText("condition:");

        chkIsFound.setText("isFound");

        btnAdd.setText("Add");

        btnDone.setText("Done");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(chkIsFound)
                        .addContainerGap())
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createSequentialGroup()
                                .add(lblCaseNum)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(txtCaseNum, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(layout.createSequentialGroup()
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(lblBoneName)
                                    .add(lblCondition))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                    .add(txtCondition)
                                    .add(txtBoneName))))
                        .add(0, 0, Short.MAX_VALUE))))
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(44, 44, 44)
                        .add(btnAdd)
                        .add(27, 27, 27)
                        .add(btnDone))
                    .add(ddlBodyRegion, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(lblCaseNum)
                    .add(txtCaseNum, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(ddlBodyRegion, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(2, 2, 2)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(lblBoneName)
                    .add(txtBoneName, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(txtCondition, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(lblCondition))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(chkIsFound)
                .add(18, 18, 18)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(btnAdd)
                    .add(btnDone))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCaseNumActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_txtCaseNumActionPerformed
    {//GEN-HEADEREND:event_txtCaseNumActionPerformed
	// TODO add your handling code here:
    }//GEN-LAST:event_txtCaseNumActionPerformed

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
	    java.util.logging.Logger.getLogger(BoneTemplate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (InstantiationException ex)
	{
	    java.util.logging.Logger.getLogger(BoneTemplate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (IllegalAccessException ex)
	{
	    java.util.logging.Logger.getLogger(BoneTemplate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (javax.swing.UnsupportedLookAndFeelException ex)
	{
	    java.util.logging.Logger.getLogger(BoneTemplate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	}
	//</editor-fold>

	/*
	 * Create and display the form
	 */
	java.awt.EventQueue.invokeLater(new Runnable()
	{

	    public void run()
	    {
		new BoneTemplate().setVisible(true);
	    }
	});
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDone;
    private javax.swing.JCheckBox chkIsFound;
    private javax.swing.JComboBox ddlBodyRegion;
    private javax.swing.JLabel lblBoneName;
    private javax.swing.JLabel lblCaseNum;
    private javax.swing.JLabel lblCondition;
    private javax.swing.JTextField txtBoneName;
    private javax.swing.JTextField txtCaseNum;
    private javax.swing.JTextField txtCondition;
    // End of variables declaration//GEN-END:variables
}
