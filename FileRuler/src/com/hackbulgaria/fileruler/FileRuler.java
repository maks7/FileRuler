package com.hackbulgaria.fileruler;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class FileRuler extends JFrame {

    FileRuler() {
        initUI();
    }
    
private void initUI() {
        
        setTitle("FileRuler");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }


    public static void main(String[] args) {

        
        
        EventQueue.invokeLater(new Runnable() {
            
            @Override
            public void run() {
                FileRuler ex = new FileRuler();
                ex.setVisible(true);
            }
        });
        
    }

}
