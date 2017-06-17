/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQLMateDesigner.Pan.SubPan;

import com.soft9000.SQLMate.SqlColumn;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.DefaultListModel;

/**
 *
 * @author profnagy
 */
public class JpSqlColumnList extends javax.swing.JPanel {

    private DefaultListModel lbModel;
    private ActionListener edit = null;
    private ActionListener create = null;

    /**
     * Creates new form JpSqlColumnList
     */
    public JpSqlColumnList() {
        initComponents();
        lbModel = new DefaultListModel();
        this.jlstCols.setModel(lbModel);
    }

    public void set(final List<SqlColumn> data, ActionListener edit, ActionListener create) {
        if (data == null) {
            return;
        }
        this.edit = edit;
        this.create = create;

        this.lbModel.clear();
        for (SqlColumn ref : data) {
            if (ref == null) {
                continue;
            }
            lbModel.add(0, ref.getColumnName());
        }
        this.jlstCols.setModel(lbModel);
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
        jbNew = new javax.swing.JButton();
        jbEdit = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jlstCols = new javax.swing.JList();

        setBorder(javax.swing.BorderFactory.createTitledBorder(" Sql Column List"));

        jbNew.setText(" New ");
        jbNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbNewActionPerformed(evt);
            }
        });
        jPanel1.add(jbNew);

        jbEdit.setText(" Edit ");
        jbEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbEditActionPerformed(evt);
            }
        });
        jPanel1.add(jbEdit);

        jlstCols.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jlstCols.setPreferredSize(new java.awt.Dimension(52, 200));
        jScrollPane1.setViewportView(jlstCols);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jbEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbEditActionPerformed
        if (this.edit != null) {
            this.edit.actionPerformed(evt);
        }
    }//GEN-LAST:event_jbEditActionPerformed

    private void jbNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbNewActionPerformed
        if (this.create != null) {
            this.create.actionPerformed(evt);
        }
    }//GEN-LAST:event_jbNewActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbEdit;
    private javax.swing.JButton jbNew;
    private javax.swing.JList jlstCols;
    // End of variables declaration//GEN-END:variables
}
