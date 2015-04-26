package com.hackbulgaria.fileruler;

import java.awt.Graphics;
import java.awt.MediaTracker;
import java.awt.Toolkit;

import javax.swing.JPanel;

class ContentPanel extends JPanel {
    java.awt.Image bgimage = null;

    ContentPanel() {
        MediaTracker mt = new MediaTracker(this);
        bgimage = Toolkit.getDefaultToolkit().getImage(".\\res\backgr_images2.jpg");
        mt.addImage(bgimage, 0);
        try {
            mt.waitForAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int imwidth = bgimage.getWidth(null);
        int imheight = bgimage.getHeight(null);
        g.drawImage(bgimage, 0, 0, null);
    }
}