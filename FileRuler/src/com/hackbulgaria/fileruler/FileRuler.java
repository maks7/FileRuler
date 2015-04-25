package com.hackbulgaria.fileruler;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
        // Windows - new CrawlFiles(Paths.get("D:\\")).crawl();
        // Linux - new CrawlFiles(Paths.get("/")).crawl();

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
        setBounds(200, 200, 650, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblSearch = new JLabel("Enter name:");
        lblSearch.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblSearch.setBounds(39, 29, 78, 14);
        contentPane.add(lblSearch);

        JTextPane textPane = new JTextPane();
        textPane.setForeground(Color.BLACK);
        textPane.setBounds(39, 54, 456, 20);
        contentPane.add(textPane);

        JButton btnNewButton = new JButton("Scan");
        btnNewButton.setBounds(526, 11, 89, 23);
        contentPane.add(btnNewButton);

        JButton btnNewButton_1 = new JButton("Find");
        btnNewButton_1.setBounds(526, 51, 89, 23);
        contentPane.add(btnNewButton_1);

        JList list = new JList();
        list.setBounds(43, 113, 452, 219);
        contentPane.add(list);

        JButton btnOpen = new JButton("Open");
        btnOpen.setBounds(526, 110, 89, 23);
        contentPane.add(btnOpen);

        JComboBox comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel(new String[] { "Films", "Images" }));
        comboBox.setBounds(39, 85, 159, 20);
        contentPane.add(comboBox);
    }
}
