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

<<<<<<< HEAD
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
=======
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
		String rootDir = "test-data-films";
		// if (new
		// String(System.getProperty("os.name")).toLowerCase().indexOf("win") >=
		// 0) {
		// rootDir = "D:\\";
		// } else {
		// rootDir = "/";
		// }

		new CrawlFiles(Paths.get(rootDir)).crawl();

		for (String string : HDDCrawler.listOfMovies) {
			System.out.println(string);
		}

		for (String string : HDDCrawler.listOfImages) {
			System.out.println(string);
		}

		new MovieFactory(rootDir).generateAllMovies();

		for (Movie m : MoviesCollecion.movieCollection) {
			System.out.println(m.getName() + " " + m.getYearOfRelease() + " [ "
					+ m.getActors() + " ]");
		}

		DoSearch search = new DoSearch("Vin Diesel,Paul Walker");
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

		// setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		setBounds(200, 200, 650, 400);

		// contentPane = new JPanel();
		contentPane = new ContentPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// contentPane.add();
		contentPane.setBackground(new Color(153, 204, 255));
		;

		// setSize(500, 300);

		// Image bgimage = null;
		// MediaTracker mt = new MediaTracker(this);
		// bgimage =
		// Toolkit.getDefaultToolkit().getImage(".\\res\backgr_images2.jpg");
		// mt.addImage(bgimage, 0);

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
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Films",
				"Images" }));
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

				System.out.println("Find was selected.");
			}
		};
		btnNewButton_1.addActionListener(btn1ActionListener);

	}

}
>>>>>>> 7df88beee6cdc1f6a8cc36a26f2b31a99a37b554
