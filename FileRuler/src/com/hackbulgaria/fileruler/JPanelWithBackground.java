package com.hackbulgaria.fileruler;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class JPanelWithBackground extends JPanel {

   
        private static final long serialVersionUID = 1L;
        java.awt.Image imageOrg = null;

        public JPanelWithBackground(java.awt.Image image2) {
            imageOrg = image2;
            setOpaque(false);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(imageOrg.getWidth(this), imageOrg.getHeight(this));
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (imageOrg != null) {
                System.err.println("painting");
                g.drawImage(imageOrg, 0, 0, getWidth(), getHeight(), this);
            }
        }
}
