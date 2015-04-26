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
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import org.apache.commons.io.FileUtils;

import com.hackbulgaria.fileruler.CrawlFiles;
import com.hackbulgaria.fileruler.DoSearch;
import com.hackbulgaria.fileruler.HDDCrawler;
import com.hackbulgaria.fileruler.Movie;
import com.hackbulgaria.fileruler.MovieFactory;
import com.hackbulgaria.fileruler.MoviesCollecion;
import com.hackbulgaria.fileruler.Test_sw;


public class FileRuler extends JFrame {

    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        File requests = new File("index-directory");
        if (requests.exists()) {
            try {
                FileUtils.deleteDirectory(requests);
            } catch (IOException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }

        // Change the root directory depends on the os
        String rootDir;
        if (new String(System.getProperty("os.name")).toLowerCase().indexOf("win") >= 0) {
            rootDir = "D:\\";
        } else {
            rootDir = "/";
        }

        new CrawlFiles(Paths.get(rootDir)).crawl();

        for (String string : HDDCrawler.listOfMovies) {
            System.out.println(string);
        }

        for (String string : HDDCrawler.listOfImages) {
            System.out.println(string);
        }

        new MovieFactory(rootDir).generateAllMovies();

        for (Movie m : MoviesCollecion.movieCollection) {
            System.out.println(m.getName() + " " + m.getYearOfRelease() + " [ " + m.getActors() + " ]");
        }

        String searchPhrase = "Vin Diesel";
        DoSearch search = new DoSearch(searchPhrase);
        ArrayList<Path> results = search.search(MovieFactory.movieCollection);

        for (Path res : results) {
            System.out.println(res);
        }

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
        
        //setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
        setBounds(200, 200, 650, 400);
        
        //contentPane = new JPanel();
        contentPane = new ContentPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
      //  contentPane.add();
        contentPane.setBackground(new Color(153, 204, 255));;
        
        //setSize(500, 300);
        
//        Image bgimage = null;
//        MediaTracker mt = new MediaTracker(this);
//        bgimage = Toolkit.getDefaultToolkit().getImage(".\\res\backgr_images2.jpg");
//        mt.addImage(bgimage, 0);
        

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
        
        // Buttons click events
        ActionListener btnActionListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                
                
              System.out.println("Scan was selected.");
            }
          };
          btnNewButton.addActionListener(btnActionListener);  
          
          ActionListener btn1ActionListener = new ActionListener() {
              public void actionPerformed(ActionEvent actionEvent) {
                  
                Test_sw test = new Test_sw();
                test.setVisible(true);
                    
                System.out.println("Find was selected.");
              }
            };
            btnNewButton_1.addActionListener(btn1ActionListener);  
          
    }
    

}
