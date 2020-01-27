
/************************************************************
 *                                                          *
 *  CSCI 470/502          Assignment 9           Fall 2018  *                                             
 *                                                          *
 *  Programmer: 1.Sindhusha Devi Parimi                     *
 *                                                          *
 *  Date Due:   12/10/2018 01:59 PM                         *                          
 *                                                          *
 *  Purpose:   To create Java Application using multi-      *
 *             threading and graphics to display an         *
 *             animated version of the various sorting      *
 *             Algorithms.                                  *
 *  Extra Credit: Implemented 1,2,4,5,6 points              *
 ***********************************************************/ 

import javax.swing.JFrame;
import java.awt.GridLayout;

public class SortAnimationApp extends JFrame
{
  SortPanel pane1 = new SortPanel();
  SortPanel pane2 = new SortPanel();

  public static void main( String[] args )
  {
    SortAnimationApp sortAnim = new SortAnimationApp();
    sortAnim.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    sortAnim.setSize(1300,650);
    sortAnim.setLayout(new GridLayout(1,2));
    sortAnim.setTitle("Sorting Animation");
    sortAnim.setVisible(true);
    sortAnim.setResizable(true);
  }
  public SortAnimationApp()
  {
    this.add(pane1);
    this.add(pane2);
  }
}

