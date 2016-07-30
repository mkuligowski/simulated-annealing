package com.mkuligowski.sa.cristalization;

import java.awt.*;
import javax.swing.*;
public class CrystalizationSystemDrawer
{
    public static void draw (int[][] array)
    {

        JFrame checkerBoard = new JFrame();
        checkerBoard.setSize(1500,1500);
        checkerBoard.setTitle("CrystalizationSystemDrawer");
        checkerBoard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int row = array.length;
        int col = array.length;


        Container pane = checkerBoard.getContentPane();
        pane.setLayout(new GridLayout(row,col));

        Color checker;



        for(int x =0;x<array.length;x++){
            for(int y =0;y<array.length;y++){
                checker = array[x][y]== 1 ?Color.black:Color.LIGHT_GRAY;
                JPanel panel = new JPanel();
                panel.setPreferredSize(new Dimension(1500/row, 1500/col));
                panel.setBackground(checker);
                pane.add(panel);
            }
        }
        checkerBoard.setVisible(true);
    }
}