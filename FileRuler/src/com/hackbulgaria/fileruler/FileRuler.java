package com.hackbulgaria.fileruler;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
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
import org.json.JSONException;
import org.json.JSONObject;

public class FileRuler extends JFrame {
    private JPanel contentPane;
    private String SELECTED = null;

    public static void main(String[] args) {
        loadLocalJSONs(Paths.get("test-data-json"));

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

    private static void loadLocalJSONs(Path p) {
        try {
            listFilesForFolder(p.toFile());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void listFilesForFolder(final File folder) throws IOException {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                String path;

                String actors;
                String scenarist;
                String director;
                String runtime;
                String ganre;
                String yearOfRelease;
                String name;
                JSONObject movieContent;
                String fileName;
                Path filePath;

                fileName = fileEntry.getName();
                fileName = fileName.substring(0, fileName.lastIndexOf('.'));

                try {

                    String content = FileUtils.readFileToString(fileEntry, Charset.forName("UTF-8")).substring(1);

                    if (content != null) {
                        filePath = fileEntry.toPath();
                        movieContent = new JSONObject(content);
                        name = movieContent.getString("Title");
                        yearOfRelease = movieContent.getString("Year");
                        actors = movieContent.getString("Actors");
                        scenarist = movieContent.getString("Writer");
                        director = movieContent.getString("Director");
                        runtime = movieContent.getString("Runtime");
                        ganre = movieContent.getString("Genre");

                        MoviesCollecion.movieCollection.add(new Movie(name, yearOfRelease, actors, scenarist, director,
                                runtime, ganre, filePath));
                    }
                } catch (JSONException e) {
                    System.out.println("json parse crashed");
                    e.printStackTrace();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
            }
        }
    }

    public FileRuler() {
        setTitle("FileRuler");
        setForeground(Color.WHITE);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(250, 150, 920, 750);
        // this.getContentPane().setBackground(new Color(145, 104, 23));

        contentPane = new JPanel();

        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        this.getContentPane().setBackground(new Color(245, 245, 245));

        JLabel logo = new JLabel("File Ruler");
        logo.setFont(new Font("Tahoma", Font.BOLD, 50));
        logo.setBounds(280, 20, 1300, 50);
        contentPane.add(logo);

        JLabel lblSearch = new JLabel("Search :");
        lblSearch.setFont(new Font("Tahoma", Font.BOLD, 30));
        lblSearch.setBounds(40, 120, 500, 30);
        contentPane.add(lblSearch);

        final JTextPane textPane = new JTextPane();
        textPane.setForeground(Color.BLACK);
        textPane.setBounds(340, 115, 450, 40);
        textPane.setFont(new Font("Consolas", Font.PLAIN, 30));
        contentPane.add(textPane);

        JButton btnFind = new JButton("Find");
        btnFind.setBounds(750, 100, 90, 70);
        btnFind.setFont(new Font("Consolas", Font.PLAIN, 25));
        contentPane.add(btnFind);

        final DefaultListModel listModel = new DefaultListModel();
        final JList<String> list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        list.setBounds(40, 200, 700, 450);
        contentPane.add(list);

        JButton btnNewButton = new JButton("Scan");
        btnNewButton.setBounds(750, 500, 110, 80);
        btnNewButton.setFont(new Font("Consolas", Font.PLAIN, 25));
        contentPane.add(btnNewButton);

        JButton btnOpen = new JButton("Open");
        btnOpen.setBounds(750, 350, 110, 80);
        btnOpen.setFont(new Font("Consolas", Font.PLAIN, 25));
        contentPane.add(btnOpen);

        JButton btnEdit = new JButton("Edit");
        btnEdit.setBounds(750, 250, 110, 80);
        btnEdit.setFont(new Font("Sans Serif", Font.PLAIN, 20));
        contentPane.add(btnEdit);

        final JComboBox<String> comboBox = new JComboBox<String>();
        comboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "  Films" }));
        comboBox.setBounds(180, 110, 150, 50);
        comboBox.setFont(new Font("Sans Serif", Font.PLAIN, 30));
        contentPane.add(comboBox);

        // Scan the entire db to collect data for movies and images
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Change the root directory depends on the os
                // String rootDir = "test-data-films";
                String rootDir;
                if (new String(System.getProperty("os.name")).toLowerCase().indexOf("win") >= 0) {
                    rootDir = "D:\\";
                } else {
                    rootDir = "/";
                }

                new CrawlFiles(Paths.get(rootDir)).crawl();
                new MovieFactory(rootDir).generateAllMovies();
            }
        });

        btnOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                DefaultListModel model = (DefaultListModel) list.getModel();
                int selectedIndex = list.getSelectedIndex();

                if (selectedIndex != -1) {
                    SELECTED = (String) model.getElementAt(selectedIndex);
                    try {
                        String content = FileUtils.readFileToString(new File(SELECTED.replace(".avi", ".txt")));
                        SELECTED = content.substring(content.lastIndexOf("|") + 1);
                        Desktop.getDesktop().open(new File(SELECTED));
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        });

        btnFind.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listModel.clear();
                File requests = new File("index-directory");
                if (requests.exists()) {
                    try {
                        FileUtils.deleteDirectory(requests);
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                        ex.printStackTrace();
                    }
                }

                String option = comboBox.getSelectedItem().toString().trim();

                switch (option) {
                    case "Films":
                        String content = textPane.getText();

                        if (!content.isEmpty()) {
                            DoSearch search = new DoSearch(content);
                            ArrayList<Path> results = search.search(MovieFactory.movieCollection);

                            for (Path res : results) {
                                list.setCellRenderer(new CellRenderer());
                                // JLabel renderer = (JLabel) new
                                // CellRenderer().getListCellRendererComponent(list,
                                // value,
                                // index, isSelected, cellHasFocus);
                                String renamed = res.toAbsolutePath().toString().replace(".txt", ".avi");
                                listModel.addElement(String.format("%s", renamed));
                            }

                            if (results.size() == 0) {
                                // Message
                            }
                        }

                        break;
                    case "Images":
                        String content2 = textPane.getText();

                        if (!content2.isEmpty()) {
                            DoSearch search = new DoSearch(content2);
                            ArrayList<Path> results = search.search(ImageFactory.imageCollection);

                            for (Path res : results) {
                                listModel.addElement(res.toAbsolutePath());
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