package com.hackbulgaria.fileruler;

import java.awt.EventQueue;
import java.io.IOException;

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

    public static void main(String[] args) throws IOException {

        EventQueue.invokeLater(new Runnable() {

            public void run() {
                FileRuler ex = new FileRuler();
                ex.setVisible(true);
            }
        });
    }
}