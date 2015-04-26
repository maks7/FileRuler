package com.hackbulgaria.fileruler;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;


public class FileRuler extends JFrame {

    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FileRuler frame = new FileRuler();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public FileRuler() {
        setTitle("FileRuler");
        setForeground(Color.YELLOW);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(200, 200, 800, 600);
        //this.getContentPane().setBackground(new Color(145, 104, 23));
        
        contentPane = new JPanel();

        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        this.getContentPane().setBackground(new Color(111, 179, 154));
       

        JLabel lblSearch = new JLabel("Enter name:");
        lblSearch.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblSearch.setBounds(40, 25, 110, 30);
        contentPane.add(lblSearch);

        JTextPane textPane = new JTextPane();
        textPane.setForeground(Color.BLACK);
        textPane.setBounds(40, 55, 456, 20);
        contentPane.add(textPane);

        JButton btnNewButton = new JButton("Scan");
        btnNewButton.setBounds(530, 11, 90, 40);
        btnNewButton.setFont(new Font("Sans Serif", Font.PLAIN, 20));
        contentPane.add(btnNewButton);

        JButton btnNewButton_1 = new JButton("Find");
        btnNewButton_1.setBounds(530, 51, 90, 40);
        btnNewButton_1.setFont(new Font("Sans Serif", Font.PLAIN, 20));
        contentPane.add(btnNewButton_1);

        JLabel lblActors = new JLabel("Actors:");
        lblActors.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblActors.setBounds(95, 110, 30, 20);
        contentPane.add(lblActors);
        
        
        JList<String> list = new JList();
        list.setBounds(40, 130, 400, 300);
        contentPane.add(list);

        JButton btnEdit = new JButton("Edit");
        btnEdit.setBounds(530, 130, 90, 40);
        btnEdit.setFont(new Font("Sans Serif", Font.PLAIN, 20));
        contentPane.add(btnEdit);

        JComboBox<String> comboBox = new JComboBox<String>();
        comboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "Films", "Images" }));
        comboBox.setBounds(40, 85, 80, 20);
        contentPane.add(comboBox);
        
        
        // add the listener to the jbutton to handle the "pressed" event
        btnNewButton.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
          {
            
            System.out.println("Scan Buton pressed");
          }
        });
        
        btnNewButton_1.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
          {
            
            System.out.println("Find Buton pressed");
          }
        });

        
        
    }
}
