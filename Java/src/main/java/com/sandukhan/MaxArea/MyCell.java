package com.sandukhan.MaxArea;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

public class MyCell extends JLabel {
    int x = 0;
    int y = 0;
    Map map;
    boolean isSelected = false;
    public MyCell(int sizeX, int sizeY, int x, int y, Map m){
        super();
        this.x = x;
        this.y = y;
        this.map = m;
        this.setOpaque(true);
        this.setHorizontalAlignment(SwingConstants.CENTER);
        //setting background with transparency value to see though the this
        this.setBackground(new Color(0, 0, 0, 0));
        //just set the size for now
        this.setSize(sizeX, sizeY);
        this.setLocation(sizeX * x, sizeY * y);
        System.out.println("img in location => " + this.getLocation());
        this.setBorder(new LineBorder(Color.black));
        this.setVisible(true);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                clicked();
            }});
    }

    void clicked(){
        if(isSelected){
            changeColor(new Color(0, 0, 0, 0));
        }
        else {
            changeColor(new Color(51, 255, 51, 100));
        }
        isSelected = !isSelected;
        map.select(x, y);
    }

    public void changeColor(Color c){
        this.setOpaque(true);
        this.setBackground(c);
        SwingUtilities.updateComponentTreeUI(this.getParent().getParent());
    }
}
