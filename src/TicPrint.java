
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author pkabariy
 */
public class TicPrint extends TicTacToe{
   public int column;
    TicPrint()
    {
        this.column=column;
    }
     // print moves
   public void printSymbol(int column, char value)
   {
      System.out.printf("|   %c   ", value);

	  if (column == 2)
         System.out.println("|");
   } 
   
   
}
