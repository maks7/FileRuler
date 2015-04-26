package com.hackbulgaria.fileruler;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import org.apache.commons.io.FileUtils;

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
        setForeground(Color.WHITE);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(200, 200, 700, 500);
        // this.getContentPane().setBackground(new Color(145, 104, 23));

        contentPane = new JPanel();

        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        this.getContentPane().setBackground(new Color(111, 179, 154));

        JLabel lblSearch = new JLabel("Enter name:");
        lblSearch.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblSearch.setBounds(40, 25, 110, 30);
        contentPane.add(lblSearch);

        final JTextPane textPane = new JTextPane();
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

        final DefaultListModel listModel = new DefaultListModel();
        final JList<String> list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        list.setBounds(40, 130, 400, 300);
        contentPane.add(list);

        JButton btnEdit = new JButton("Edit");
        btnEdit.setBounds(530, 130, 90, 40);
        btnEdit.setFont(new Font("Sans Serif", Font.PLAIN, 20));
        contentPane.add(btnEdit);

        final JComboBox<String> comboBox = new JComboBox<String>();
        comboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "Films", "Images" }));
        comboBox.setBounds(40, 85, 80, 20);
        contentPane.add(comboBox);

        // Scan the entire db to collect data for movies and images
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                File requests = new File("index-directory");
                if (requests.exists()) {
                    try {
                        FileUtils.deleteDirectory(requests);
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                        ex.printStackTrace();
                    }
                }

                // Change the root directory depends on the os
                String rootDir = "test-data-films";
                // if (new
                // String(System.getProperty("os.name")).toLowerCase().indexOf("win")
                // >=
                // 0) {
                // rootDir = "D:\\";
                // } else {
                // rootDir = "/";
                // }

                new CrawlFiles(Paths.get(rootDir)).crawl();
                new MovieFactory(rootDir).generateAllMovies();
            }
        });

        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String option = (String) comboBox.getSelectedItem();

                switch (option) {
                    case "Films":
                        String content = textPane.getText();

                        if (!content.isEmpty()) {
                            DoSearch search = new DoSearch(content);
                            ArrayList<Path> results = search.search(MovieFactory.movieCollection);

                            for (Path res : results) {
                                listModel.addElement(res);
                            }
                        }

                        break;
                    case "Images":
                        String content2 = textPane.getText();

                        if (!content2.isEmpty()) {
                            DoSearch search = new DoSearch(content2);
                            ArrayList<Path> results = search.search(MovieFactory.movieCollection);

                            for (Path res : results) {
                                listModel.addElement(res);
                            }
                        }

                        break;
                    default:
                        throw new IllegalArgumentException("Invalid search file format");
                }
            }
        });

    }
}

// private JPanel contentPane;
//
// /**
// * Launch the application.
// */
// public static void main(String[] args) {
// File requests = new File("index-directory");
// if (requests.exists()) {
// try {
// FileUtils.deleteDirectory(requests);
// } catch (IOException e) {
// System.out.println(e.getMessage());
// e.printStackTrace();
// }
// }
//
// // Change the root directory depends on the os
// String rootDir = "test-data-films";
// // if (new
// // String(System.getProperty("os.name")).toLowerCase().indexOf("win") >=
// // 0) {
// // rootDir = "D:\\";
// // } else {
// // rootDir = "/";
// // }
//
// new CrawlFiles(Paths.get(rootDir)).crawl();
// //
// for (String string : HDDCrawler.listOfMovies) {
// System.out.println(string);
// }
//
// for (String string : HDDCrawler.listOfImages) {
// System.out.println(string);
// }
//
// new MovieFactory(rootDir).generateAllMovies();
//
// for (Movie m : MoviesCollecion.movieCollection) {
// System.out.println(m.getName() + " " + m.getYearOfRelease() + " [ "
// + m.getActors() + " ]");
// }
//
// DoSearch search = new DoSearch("Vin Diesel,Paul Walker");
// ArrayList<Path> results = search.search(MovieFactory.movieCollection);
//
// for (Path res : results) {
// System.out.println(res);
// }
//
// EventQueue.invokeLater(new Runnable() {
// public void run() {
// try {
// FileRuler frame = new FileRuler();
// frame.setVisible(true);
// } catch (Exception e) {
// e.printStackTrace();
// }
// }
// });
// }