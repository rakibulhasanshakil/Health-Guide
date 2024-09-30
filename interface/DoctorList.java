
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.logging.*;
import java.util.logging.Level.*;

public class DoctorList extends JFrame {

    public DoctorList() {
        initComponents();
    }

    @SuppressWarnings("unchecked")

    private void initComponents() {

        DoctorList = new JLabel();
        jSeparator = new JSeparator();
        Back = new JButton();
        Forward = new JButton();
        jScrollPane1 = new JScrollPane();
        jTable = new JTable();
        jButton1 = new JButton();
        BackgroundImage = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(null);

        DoctorList.setFont(new Font("Arial", 1, 24)); 
        DoctorList.setForeground(new Color(255, 255, 255));
        DoctorList.setText("Doctor List");
        getContentPane().add(DoctorList);
        DoctorList.setBounds(296, 10, 140, 49);
        getContentPane().add(jSeparator);
        jSeparator.setBounds(244, 51, 240, 10);

        Back.setFont(new Font("Segoe UI Semilight", 1, 24)); 
        Back.setText("<-");
        Back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                BackActionPerformed(evt);
            }
        });
        getContentPane().add(Back);
        Back.setBounds(30, 20, 67, 43);

        Forward.setFont(new Font("Segoe UI Semilight", 1, 24)); 
        Forward.setText("->");
        Forward.setEnabled(false);
        getContentPane().add(Forward);
        Forward.setBounds(100, 20, 67, 43);

        jTable.setModel(new DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Doctor Name", "Password", "Email", "Profession", "Create Time"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(30, 100, 680, 590);

        jButton1.setText("Refrash");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(640, 20, 72, 50);

        BackgroundImage.setIcon(new ImageIcon(getClass().getResource("/icon/blue.png"))); 
        BackgroundImage.setText("BackgroundImage");
        getContentPane().add(BackgroundImage);
        BackgroundImage.setBounds(0, 0, 740, 740);

        setSize(new java.awt.Dimension(750, 750));
        setLocationRelativeTo(null);
    }

    private void BackActionPerformed(ActionEvent evt) {                                     

        AdminPortal ap = new AdminPortal();
        ap.setVisible(true);
        setVisible(false);
    }                                    

    private void jButton1ActionPerformed(ActionEvent evt) {                                         
        String filePath = "Data/DoctorSignup.txt";
        File file = new File(filePath);

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            String firstLine = br.readLine().trim();
            String[] columnsName = firstLine.split(",");
            DefaultTableModel model = (DefaultTableModel)jTable.getModel();
            model.setRowCount(0);
            model.setColumnIdentifiers(columnsName);

            Object[] tableLines = br.lines().toArray();

            for(int i = 0; i < tableLines.length; i++)
            {
                String line = tableLines[i].toString().trim();
                String[] dataRow = line.split("/");
                model.addRow(dataRow);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Doctor List not Found", "Error", JOptionPane.INFORMATION_MESSAGE);
        }
    }                                        

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DoctorList().setVisible(true);
            }
        });
    }

    private JButton Back;
    private JLabel BackgroundImage;
    private JLabel DoctorList;
    private JButton Forward;
    private JButton jButton1;
    private JScrollPane jScrollPane1;
    private JSeparator jSeparator;
    private JTable jTable;

}