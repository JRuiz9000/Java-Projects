// http://storm.cis.fordham.edu/ruiz/java/Homework3/HCIButtonProg.html
// Juan Ruiz- Java Programming, Tuesday & Friday, 1PM - 2:15 PM
// This program uses HCI Elements (awt functions) in order to create control elements 
// such as buttons and other layouts
// Refers To Chapter 11 Of TextBook
// This program takes input data in the form of numbers and then outputs them.
// This they are displayed first in the original order, then they are sorted in various ways
// By using a Bubble Sort Method. The program utilizes Min/Max Functions and Avg as an added //bonus
// and should handle exceptions in addition to other elements
// Also clears changes at will 
// Note: To Sort: Input each value separated by a comma, choose sorting option, then click //Go/Start.
// Note: To Find Min, Max, Avg: Input each number individually using the "Input Number" Button, //pick the appropriate option, then click Go/Start.
// Note: It is best to find the min/max/avg first before sorting
package HCIButtonProg;
import java.awt.Graphics;
import java.applet.Applet;
import java.awt.Font;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

public class HCIButtonProg extends Applet
   implements ActionListener
{
   private Button b1, b2, b3;
   private TextField txf1, txf2;
   private CheckboxGroup CbG,ACbG ;
   private Checkbox Cb1, Cb2, Cb3, Cb4, Cb5;
   
   int array[] = new int[10];
   int min, max;
   int count = 0;
   double average;
   Label SortingOpt, SortingOpt2, SortingOpt3; 
   
 //The Applet Is Initialized And The Elements Are Put In Place
 @Override
 public void init()
 {
   setBackground(Color.yellow); //Changes color 
   setLayout(new FlowLayout(FlowLayout.CENTER, 40, 40)); //Refers to placing elements using    //LayoutManager, BorderLayout, CardLayout. 
   Panel pbfc = new Panel(); //Panel That Is Unlabelled
   pbfc.setBackground(Color.red);
   pbfc.setLayout(new FlowLayout(FlowLayout.CENTER, 38, 37));
   Label lbfc = new Label("Juan Ruiz: HCI/Bubble Sort: Please input each number individually to be sorted. "); //Refers to Min, Max, Avg
   lbfc.setFont(new Font("Courier", Font.BOLD + Font.ITALIC, 16)); //Can change font style and size
   
   pbfc.add(lbfc);
   add(pbfc);
   
   Panel pflc1 = new Panel();
   b1 = new Button("Input Number");
   b1.setEnabled(true);
   b1.addActionListener(this);
   pflc1.add(b1);
   txf1 = new TextField(10); //can change number of columns in the text field
   txf1.setText(""); //This sets the text that will be placed, can also use getText
   txf1.setEditable(true); //Can be Edited
   txf1.addActionListener(this); //Sets the Interface For this text
   pflc1.add(txf1);
   add(pflc1);

   Panel pflc2 = new Panel();
   setLayout(new FlowLayout(FlowLayout.CENTER));
   b2 = new Button("Go/ Start");
   b2.setEnabled(true);
   b2.addActionListener(this);
   b3 = new Button("Clear/ Repeat");
   b3.setEnabled(true);
   b3.addActionListener(this);
   pflc2.add(b2);
   pflc2.add(b3);
   add(pflc2);
   
   Panel pflc3 = new Panel();
   Label lb2 = new Label("The Task Is : ", Label.RIGHT); //"The Answer Is : " 
   pflc3.add(lb2);
   txf2 = new TextField("", 10); 
   txf2.setEditable(true); 
   //pflc3.add(txf2); 
   add(pflc3); 
   
   Panel pbl = new Panel();
   pbl.setBackground(Color.green);
   CbG = new CheckboxGroup();
   Cb1 = new Checkbox("Min", CbG, true);
   pbl.add(Cb1);
   Cb2 = new Checkbox("Max", CbG, false);
   pbl.add(Cb2);
   Cb3 = new Checkbox("Average", CbG, false);
   pbl.add(Cb3);
   add(pbl); 

   Panel pbl2 = new Panel();
   pbl2.setBackground(Color.orange);
   ACbG = new CheckboxGroup();
   Cb4 = new Checkbox("Descending Order Sort", ACbG, true); 
   pbl2.add(Cb4);
   Cb5 = new Checkbox("Ascending Order Sort", ACbG, false);
   pbl2.add(Cb5);
   add(pbl2); 
   
   Panel pbl3 = new Panel();
   pbl3.setBackground(Color.magenta);
   SortingOpt = new Label("Sorted Data(Ascending Order or Descending Order)");
   pbl3.setLayout(new FlowLayout(FlowLayout.CENTER,15,15));
   pbl3.add(SortingOpt);
   add(pbl3); 
}

//Handles the Exception In Order To Deal With the Events
@Override
public void actionPerformed(ActionEvent ae)
   throws IllegalArgumentException{
      if(ae.getSource() == b1){
         int info;
         try{
            info = Integer.parseInt(txf1.getText());
            array[count] = info;
            if(array[count] > 100 || array[count] < 0)
               throw new IllegalArgumentException();
         }
         catch(NumberFormatException NFE)
         {
            showStatus(NFE.toString() + "is not considered an integer");
            count --;
         }
         catch(IllegalArgumentException TAE)
         {
            showStatus("Invalid Input, please insert an integer that is between 0 and 100");
            count --;
         }

        //       array[count] = info;

         txf1.setEditable(true);
         txf2.setText("");
         b2.setEnabled(true);
         b3.setEnabled(true);
         count++;
         repaint();
      }

         if(ae.getSource() == b2){
         b1.setEnabled(false);
         b3.setEnabled(true);
         if(Cb1.getState())
         {
            min = getMin(array, count);
            txf2.setText(Integer.toString(min));
         }
         else if(Cb2.getState())
         {
           max = getMax(array, count);
            txf2.setText(Integer.toString(max));
         }
         else if(Cb3.getState())
         {
            average = getAverage(array, count);
            txf2.setText(Double.toString(average));
         }
        
         repaint();
         
         }
         
         if(ae.getSource() == b3){
         b1.setEnabled(true);
         b2.setEnabled(true);
         txf1.setText("");
         txf2.setText("");
         count = 0;
         Cb1.setState(false);
         Cb2.setState(false);
         Cb3.setState(false);
         repaint(); 
      }
         StringSPL(); //Function Call
         Values(); //Function Call
   }
   
@Override
 public void paint(Graphics g)
 {
      Color TextColor = Color.blue;
      g.setColor(TextColor);
      g.setFont(new Font("Courier", Font.BOLD, 16));
      g.drawString("Displays Input Data: ", 25, 140);
      for(int i =0; i < count; i++)
      g.drawString(String.valueOf(array[i]), 60+i*30, 165);
      g.drawString("The count = " + String.valueOf(count), 25, 200);
      g.drawString("The min = " + String.valueOf(min), 25, 225);
      g.drawString("The max = " + String.valueOf(max), 25, 250);
      g.drawString("The average = " + String.valueOf(average), 25, 275); //Added Extra
      
   }
 
  //This sorting method is used to sort the values in ascending order
  void BubbleSort(int [] array)
  {
         for (int i = (array.length - 1); i >= 0; i--)
         {
            for(int x = 1; x <= i; x++)
            {
               if(array[x-1] > array[x])
               {
                  int temp = array[x-1];
                  array[x-1] = array[x];
                  array[x] = temp;
               }
            }
         }
  }


  //This sorting method is used to sort the values in descending order
  public void BubbleSortDO(int a[])
  {
     int x = a.length;
     int temp = 0;
               
     for(int i=0; i < x; i++)
     {
       for(int k=1; k < (x-i); k++)
       {
         if(a[k-1] < a[k]) //This switches / swaps the values
         { 
           temp = a[k-1];
           a[k-1] = a[k];
           a[k] = temp;
         }
       }
     }
  }
  
 public void StringSPL ()
  {
    if(Cb5.getState())
    {
    String NewString = txf1.getText();
       
    String[] NewStringArray = NewString.split(",");
    int sl = NewStringArray.length;
    int[] NewIntArray = new int[NewStringArray.length];
        
    for(int i=0; i<sl; i++)
    {
    String StringLine = NewStringArray[i];
    NewIntArray[i]= Integer.parseInt(StringLine);
    }
      // System.out.println(Arrays.toString(NewIntArray));
       BubbleSort(NewIntArray);
       SortingOpt.setText(Arrays.toString(NewIntArray));
    }
    
    else if(Cb4.getState()){
    String NewString = txf1.getText();
       
    String[] NewStringArray = NewString.split(",");
    int sl = NewStringArray.length;
    int[] NewIntArray = new int[NewStringArray.length];
        
    for(int i=0; i<sl; i++)
    {
    String StringLine = NewStringArray[i];
    NewIntArray[i]= Integer.parseInt(StringLine);
    }
      // System.out.println(Arrays.toString(intArray));
       BubbleSortDO(NewIntArray);
       SortingOpt.setText(Arrays.toString(NewIntArray));
         
     }
   }
     
  public void Values ()
  {
     String NewString = txf1.getText();
       
     String[] NewStringArray = NewString.split(",");
     int sl = NewStringArray.length;
     int[] NewIntArray = new int[NewStringArray.length];
        
     for(int i=0; i<sl; i++)
     {
       String StringLine = NewStringArray[i];
       NewIntArray[i]= Integer.parseInt(StringLine);
            
     }
        
       BubbleSortDO(NewIntArray);
       SortingOpt2.setText(String.valueOf(NewIntArray[0]));
       BubbleSort(NewIntArray);
       SortingOpt3.setText(String.valueOf(NewIntArray[0]));
       
  }
  
  
  //The method is used to implement getAverage
  double getAverage(int getAvg[], int number)
  {
     double ave = 0;
     for(int i = 0; i < number; i++)
         ave += getAvg[i];
      return(ave/number);
   }
 
 //This method is used to implement getMax
  int getMax(int getMx[], int number)
   {
      int temp = 0;
      for(int i=0; i < number; i++){
         if(getMx[i] > temp)
            temp = getMx[i];
      }  
      
      return temp;
   }

   //This method is used to implement getMin
   public int getMin(int getMn[], int number)
   {
      int temp = getMn[1];
      for(int i=0; i < number; i++)
         if(getMn[i] < temp)
            temp = getMn[i];
        return temp;
   }   
}
