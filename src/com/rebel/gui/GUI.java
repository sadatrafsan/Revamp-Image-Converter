package com.rebel.gui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

class CustomFilter extends javax.swing.filechooser.FileFilter {

    @Override
    public boolean accept(File file){
    
        return file.isDirectory() || 
               file.getAbsolutePath().endsWith(".jpg") ||
               file.getAbsolutePath().endsWith(".png") ||
               file.getAbsolutePath().endsWith(".gif");
    }
    
    @Override
    public String getDescription(){
    
        return "*.jpg, png, gif";
    }
}

public class GUI extends javax.swing.JFrame {
    
    private File file;
    private String name,path,format;

    public GUI() {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/com/rebel/icon/icon.png")).getImage());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fileChooser = new javax.swing.JFileChooser();
        textField = new javax.swing.JTextField();
        browseButton = new javax.swing.JButton();
        pathLabel = new javax.swing.JLabel();
        formatComboBox = new javax.swing.JComboBox();
        convertButton = new javax.swing.JButton();

        fileChooser.setCurrentDirectory(new java.io.File("C:\\"));
            fileChooser.setDialogTitle("Open File");
            fileChooser.setFileFilter(new CustomFilter());

            setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
            setTitle("Revamp");
            setResizable(false);

            browseButton.setText("Browse");
            browseButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    browseButtonActionPerformed(evt);
                }
            });

            pathLabel.setText("File");

            formatComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "gif", "jpg", "png" }));

            convertButton.setText("Convert");
            convertButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    convertButtonActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(pathLabel)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(textField, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(browseButton)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(formatComboBox, 0, 56, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(convertButton)))
                    .addContainerGap())
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(pathLabel)
                        .addComponent(textField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(browseButton)
                        .addComponent(formatComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(convertButton)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            pack();
            setLocationRelativeTo(null);
        }// </editor-fold>//GEN-END:initComponents

    private void browseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseButtonActionPerformed
        
        if(fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
            file = fileChooser.getSelectedFile();
            name = file.getName();
            name = name.substring(0, name.lastIndexOf("."));
            String absolutePath = file.getAbsolutePath();
            path = absolutePath.substring(0,absolutePath.lastIndexOf(File.separator));
            textField.setText(absolutePath);
        }
        else{
            System.out.println("File Access canceled by user!");
        }
    }//GEN-LAST:event_browseButtonActionPerformed

    private void convertButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_convertButtonActionPerformed
        
        format = this.formatComboBox.getSelectedItem().toString();
        System.out.print(path + "\\" + name + "." + format);
        
        try {
            
            try {
                BufferedImage buffer = ImageIO.read(file);
                File output = new File(path + "\\" + name + "." + format);        
                boolean result = ImageIO.write(buffer,format,output);
                if(result == true){
                    JOptionPane.showMessageDialog(null, "Image Converted!");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Error!");
                }
                
            } catch (IOException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }        
        
    }//GEN-LAST:event_convertButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton browseButton;
    private javax.swing.JButton convertButton;
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JComboBox formatComboBox;
    private javax.swing.JLabel pathLabel;
    private javax.swing.JTextField textField;
    // End of variables declaration//GEN-END:variables
}
