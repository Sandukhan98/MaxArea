package com.sandukhan.MaxArea;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.net.URL;
import java.util.Stack;
public class MaxArea {

    public static  void createGarphicMap(){
        JFrame frame = new JFrame("Display Image");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        JButton browse = new JButton("browse");
        JLayeredPane mainPane = new JLayeredPane();
        browse.setLocation(5,5);
        browse.setSize(100, 30);
        browse.setVisible(true);
        mainPane.add(browse);
        final ImageIcon[] imgIc = {null};
        JLayeredPane content = new JLayeredPane();
        browse.addMouseListener(new MouseAdapter() {
                                    @Override
                                    public void mouseClicked(MouseEvent e) {
                                        JFileChooser chooser = new JFileChooser(new File(System.getProperty("user.home"))); //Downloads Directory as default
                                        chooser.setDialogTitle("Select Location");
                                        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                                        chooser.setAcceptAllFileFilterUsed(false);
                                        if (chooser.showOpenDialog(browse) == JFileChooser.APPROVE_OPTION)
                                        {
                                            JLabel img = new JLabel();
                                            imgIc[0] = new ImageIcon(chooser.getSelectedFile().getPath());
                                            img.setIcon(imgIc[0]);
                                            img.setLocation(0, 0);
                                            img.setOpaque(true);
                                            img.setHorizontalAlignment(SwingConstants.CENTER);
                                            //setting background with transparency value to see though the img
//                            img.setBackground(new Color(50, 210, 250, 200));
                                            //just set the size for now
                                            img.setSize(imgIc[0].getIconWidth(), imgIc[0].getIconHeight());
                                            img.setBorder(new LineBorder(Color.black));
                                            img.setVisible(true);
                                            System.out.println(chooser.getSelectedFile().getPath());
                                            content.add(img, JLayeredPane.DEFAULT_LAYER);
                                            content.setPreferredSize(new Dimension(imgIc[0].getIconWidth(),imgIc[0].getIconHeight()));
                                            content.setSize(content.getPreferredSize());
                                            System.out.println("img => " + content.getPreferredSize());
                                        }
                                    }

                                }

        );

        JLabel gridHeight = new JLabel("height : ");
        gridHeight.setLocation(120, 5);
        gridHeight.setOpaque(true);
        gridHeight.setHorizontalAlignment(SwingConstants.CENTER);
        gridHeight.setVerticalAlignment(SwingConstants.CENTER);
        gridHeight.setSize(70, 30);
        gridHeight.setVisible(true);
        mainPane.add(gridHeight);


        JLabel gridWidth = new JLabel("width : ");
        gridWidth.setLocation(250, 5);
        gridWidth.setOpaque(true);
        gridWidth.setHorizontalAlignment(SwingConstants.CENTER);
        gridWidth.setVerticalAlignment(SwingConstants.CENTER);
        gridWidth.setSize(70, 30);
        gridWidth.setVisible(true);
        mainPane.add(gridWidth);

        JTextField heightTxt = new JTextField(5);
        heightTxt.setOpaque(true);
        heightTxt.setLocation(180, 5);
        heightTxt.setHorizontalAlignment(SwingConstants.CENTER);
        heightTxt.setSize(70, 30);
        heightTxt.setVisible(true);
        mainPane.add(heightTxt);

        JTextField widthTxt = new JTextField(5);
        widthTxt.setOpaque(true);
        widthTxt.setLocation(310, 5);
        widthTxt.setHorizontalAlignment(SwingConstants.CENTER);
        widthTxt.setSize(70, 30);
        widthTxt.setVisible(true);
        mainPane.add(widthTxt);

        JButton generateGrid = new JButton("generate grid");
        generateGrid.setLocation(400,5);
        generateGrid.setSize(150, 30);
        generateGrid.setVisible(true);
        mainPane.add(generateGrid);
        final int[] h = {-1};
        final int[] w = {-1};
        final Grid[] grid = {null};
        generateGrid.addMouseListener(new MouseAdapter() {
                                    @Override
                                    public void mouseClicked(MouseEvent e) {

                                        if(imgIc[0] == null){
                                            JOptionPane.showMessageDialog(new JFrame(), "select an image", "error",
                                                    JOptionPane.ERROR_MESSAGE);
                                        }
                                        else
                                            if(heightTxt.getText().isEmpty()){
                                            JOptionPane.showMessageDialog(new JFrame(), "set height", "error",
                                                    JOptionPane.ERROR_MESSAGE);
                                        }
                                        else if(widthTxt.getText().isEmpty()){
                                            JOptionPane.showMessageDialog(new JFrame(), "set width", "error",
                                                    JOptionPane.ERROR_MESSAGE);
                                        }
                                        else{
                                            h[0] = Integer.parseInt(heightTxt.getText());
                                            w[0] = Integer.parseInt(widthTxt.getText());
                                            int sizeX = imgIc[0].getIconWidth() / w[0];
                                            int sizeY = imgIc[0].getIconHeight() / h[0];
                                                System.out.println("h => " + sizeX + " w => " +sizeY);
                                            if(grid[0] != null){
                                                content.remove(grid[0]);
                                            }
                                            grid[0] = new Grid(sizeX, sizeY, h[0], w[0], content.getPreferredSize());
                                            content.add(grid[0], JLayeredPane.POPUP_LAYER);
                                        }
                                    }

                                }

        );

        JButton getMax = new JButton("get max area");
        getMax.setLocation(560,5);
        getMax.setSize(150, 30);
        getMax.setVisible(true);
        mainPane.add(getMax);
        getMax.addMouseListener(new MouseAdapter() {
                                    @Override
                                    public void mouseClicked(MouseEvent e) {

                                        if(grid[0] == null){
                                            JOptionPane.showMessageDialog(new JFrame(), "generate a grid", "error",
                                                    JOptionPane.ERROR_MESSAGE);
                                        }
                                        else{
                                            grid[0].getMaxArea();
                                        }
                                    }

                                }

        );
        //adding an initially invisible JLabel in an upper layer
//        JLabel label = new MyImage(0,0);
//        layeredPane.add(label, JLayeredPane.POPUP_LAYER);//depth 300
        JScrollPane scroll = new JScrollPane(content);
        scroll.setLocation(40, 40);
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                scroll.setSize(new Dimension(frame.getWidth() - 50, frame.getHeight() - 50));
            }
        });

        System.out.println("screen => " + scroll.getSize());
        mainPane.add(scroll);
        frame.add(mainPane);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // Driver program to test above function
    public static void main(String[] args)
    {
//        int hist[] = { 6, 2, 5, 4, 3, 1, 6 };
//        System.out.println("Maximum area is " + getMaxArea(hist, hist.length)[2]);

//        int map[][] = {
//                {1, 0, 0, 1, 0, 1},
//                {1, 0, 1, 1, 1, 1},
//                {0, 1, 1, 1, 1, 1},
//                {0, 0, 1, 1, 1, 1}};
//        int res[] = getMaxAreaMap(map);
//        System.out.println("max_area = " + res[0] + " sc = " + res[1] + " ec = " + res[2] + " sr = " + res[3] + " er = " + res[4]);
        createGarphicMap();
    }
}