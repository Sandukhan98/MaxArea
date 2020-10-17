package com.sandukhan.MaxArea;

import javax.swing.*;
import java.awt.*;

public class Grid extends JLayeredPane {
    //size = cell size in grid
    Map map;
    MyCell cells[][];
    public Grid(int sizeX, int sizeY, int height, int width, Dimension dim){
        this.setVisible(true);
//        this.setLocation(0,0);
        this.setPreferredSize(new Dimension(sizeX + dim.width * 10, sizeY + dim.height * 10));
        this.setSize(this.getPreferredSize());
        map = new Map(width + 1 , height +1);
        cells = new MyCell[width + 1][height+1];
        System.out.println("gridh => " + height + " w => " +width);
        for (int i = 0; i < width + 1 ; i++) {
            for (int j = 0; j <height +1; j++) {
                cells[i][j] = new MyCell(sizeX, sizeY, i, j, map);
                this.add(cells[i][j]);
            }
        }
    }

    public void getMaxArea(){
        int res[] = map.getMaxArea();
        for (int e :
                res) {
            System.out.println("res => " + e);
        }
        for (int i = res[1]; i <= res[2] ; i++) {
            for (int j = res[3]; j <= res[4]; j++) {
                cells[i][j].changeColor(new Color(255, 0, 0, 100));
            }
        }
    }
}
