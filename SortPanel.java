
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

import javax.swing.*;
import java.awt.*;
import java.awt.Graphics.*;
import javax.swing.event.*;
import javax.swing.SwingUtilities.*;
import java.lang.Runnable;
import java.util.Random;
import java.lang.Object;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.ArrayList;

public class SortPanel extends JPanel
{
  private SortAnimationPanel animationPanel = new SortAnimationPanel();
  private JButton populateArr = new JButton("Populate Array");
  private JButton sortBut = new JButton("Sort");
  private JButton stopBut = new JButton("Stop");
  private JButton PauseBut = new JButton("Pause");
  private JButton ResumeBut = new JButton("Resume");
  
  private String[] sortingMethod = { "Selection", "Quick", "Bubble", "Shell", "Insertion"};
  private JComboBox sortTypes = new JComboBox<>(sortingMethod);
  private String[] initOrder = { "Random", "Ascending", "Descending"};
  private JComboBox Order = new JComboBox<>(initOrder);
  private int[] intArr = new int[525];
  int speed = 500;
  private String[] speedArr = { "Slow", "Medium", "Fast"};
  private JComboBox speeds = new JComboBox<>(speedArr);

  public SortPanel()
  {
    final SortAnimationPanel topPanel = new SortAnimationPanel();
    this.add(topPanel);
    JPanel operatePanel = new JPanel();
    operatePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
    operatePanel.add(new JLabel("Initial: "));
    operatePanel.add(Order);
    operatePanel.add(populateArr);
    operatePanel.add(sortBut);
    operatePanel.add(sortTypes);
    operatePanel.add(speeds);
    operatePanel.add(stopBut);
    operatePanel.add(PauseBut);

    sortBut.setEnabled(false);
    stopBut.setEnabled(false);
    this.add(operatePanel);
    operatePanel.setVisible(true);
    PauseBut.setEnabled(false);
  
    populateArr.addActionListener(new ActionListener()
                                    {
      public void actionPerformed(ActionEvent e)
      {
        Random randorder = new Random();
        for(int i=0; i< intArr.length; i++)
        { 
          intArr[i] = randorder.nextInt((450 - 1) + 1) + 1;
        }
        String orderValue = Order.getSelectedItem().toString();
        if(orderValue.equals("Ascending"))
        {
          Arrays.sort(intArr);
          repaint();
        }
        if(orderValue.equals("Descending"))
        {
          int t;
          int start = 0;
          int end = intArr.length-1;
          Arrays.sort(intArr);
          while(start < end)
          {
            t = intArr[start];
            intArr[start] = intArr[end];
            intArr[end] = t;
            start++;
            end--;
          }
          repaint();
        }
        if(orderValue.equals("Random"))
        {
          repaint();
        }
        sortBut.setEnabled(true);
        populateArr.setEnabled(false);
      }
    });
    sortBut.addActionListener(new ActionListener()
                                {
      public void actionPerformed(ActionEvent f)
      {
        Thread t1 = new Thread(topPanel);
        t1.start();
        
        PauseBut.setEnabled(true);
      }
    });
    stopBut.addActionListener(new ActionListener()
                                {
      public void actionPerformed(ActionEvent f)
      {
        
        Thread t1 = new Thread(topPanel);
        try {
          t1.sleep(20000);
          
        } catch (InterruptedException ex) {
          ex.printStackTrace();
        }
        t1.interrupt();
        
        populateArr.setEnabled(true);
      }
    });
    PauseBut.addActionListener(new ActionListener()
                                 {
      public void actionPerformed(ActionEvent f)
      {
        Thread t1 = new Thread(topPanel);
        try {
          t1.sleep(5000);
        } catch (InterruptedException ex) {
          ex.printStackTrace();
        }
        t1.suspend();  
        //PauseBut.setEnabled(false);
      }
      });
  }
  
  private class SortAnimationPanel extends JPanel implements Runnable
  {
    public int number;
    public int[] numbers;
    
    public void run()
    {
      String orderValue = sortTypes.getSelectedItem().toString();
      String speedValue = speeds.getSelectedItem().toString();
      
      sortBut.setEnabled(false);
      stopBut.setEnabled(true);
      
      if(speedValue.equals("Slow"))
      {
        speed = 500;
      }
      if(speedValue.equals("Medium"))
      {
        speed = 100;
      }
      if(speedValue.equals("Fast"))
      {
        speed = 10;
      }
      if(orderValue.equals("Selection"))
      {
        selectionSort(intArr);
      }
      if(orderValue.equals("Quick"))
      {
        quickSort(intArr, 0, intArr.length - 1);
        populateArr.setEnabled(true);
      }
      if(orderValue.equals("Bubble"))
      {
        bubbleSort(intArr);
      }
      if(orderValue.equals("Shell"))
      {
        shellSort(intArr);
      }
      if(orderValue.equals("Insertion"))
      {
        insertionSort(intArr);
      }
    }
    public SortAnimationPanel()
    {
      this.setPreferredSize(new Dimension(600, 500));
      this.setBackground(new Color(255,255,255));
    }
    public void paintComponent( Graphics g )
    {
      if(intArr[0] !=0)
      {
        for(int i=0; i<intArr.length; i++)
        {
          g.drawLine(i,450,i,450-intArr[i]);
          g.setColor(Color.BLUE);
        }
      }
    }   
  }
  public void selectionSort(int[] intArray)
  {
    try
    {
      for (int i=0; i<intArray.length-1; i++)
      {
        for (int j=i+1; j<intArray.length; j++)
        {
          if (intArray[i] > intArray[j])
          {
            int t = intArray[i];
            intArray[i] = intArray[j];
            intArray[j] = t;
            
            repaint();
          }
        }
        Thread.sleep(speed);
      }
      populateArr.setEnabled(true);
    }
    catch (InterruptedException e)
    {
      e.printStackTrace();
    }
  }
  public void quickSort(int intArray[], int start, int end)
  {
    int i = start;
    int k = end;
    try
    {
      if (end - start >= 1)
      {
        int pivot = intArray[start];
        while (k > i)
        {
          while (intArray[i] <= pivot && i <= end && k > i)
          {
            i++;
          }
          while (intArray[k] > pivot && k >= start && k >= i)
          {
            k--;
          }
          if (k > i)
          {
            int t = intArray[i];
            intArray[i] = intArray[k];
            intArray[k] = t;
            repaint();
          }
        }
        int temp1 = intArray[start];
        intArray[start] = intArray[k];
        intArray[k] = temp1;
        repaint();
        
        quickSort(intArray, start, k - 1);
        Thread.sleep(speed);
        
        quickSort(intArray, k + 1, end);
      }
      else
      {
        return;
      }
    }
    catch (InterruptedException e)
    {
      e.printStackTrace();
    }
  }
  public void bubbleSort(int[] intArray)
  {
    try
    {
      int n = intArray.length;
      int t = 0;
      
      for(int i=0; i < n; i++)
      {
        for(int j=1; j < (n-i); j++)
        {
          if(intArray[j-1] > intArray[j])
          { 
            t = intArray[j-1];
            intArray[j-1] = intArray[j];
            intArray[j] = t;
            repaint();
          }
        }
        Thread.sleep(speed);
      } 
      populateArr.setEnabled(true);
    }
    catch (InterruptedException e)
    {
      e.printStackTrace();  
    }
    populateArr.setEnabled(true);
  }
  public void shellSort(int[] intArray)
  {
    try
    {
      int increment = intArray.length / 2;
      while (increment > 0)
      {
        for (int i = increment; i < intArray.length; i++)
        {
          int j = i;
          int t = intArray[i];
          while (j >= increment && intArray[j - increment] > t)
          {
            intArray[j] = intArray[j - increment];
            j = j - increment;
            repaint();
          }
          intArray[j] = t;
          repaint();
          Thread.sleep(speed/2);
        }
        if (increment == 2)
        {
          increment = 1;
        }
        else
        {
          increment *= (5.0 / 11);
        }
      }
      populateArr.setEnabled(true);
    }
    catch (InterruptedException e)
    {
      e.printStackTrace();
    }
  }
  public void insertionSort(int[] intArray)
  {
    try
    {
      for (int i = 1; i < intArray.length; i++)
      {
        int j = i;
        int t = intArray[i];
        while ((j > 0) && (intArray[j-1] > t)) {
          
          intArray[j] = intArray[j-1];
          j--;
          repaint();
        }
        intArray[j] = t;
        repaint();
        Thread.sleep(speed);
      } 
      populateArr.setEnabled(true);
    }
    catch (InterruptedException e)
    {
      e.printStackTrace();
    }
  }
}