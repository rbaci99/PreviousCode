package project;

import java.util.*;
import java.lang.Exception;

	import java.util.Scanner;
//this is current
	public class Project {

	    public static void main(String[] args) {

	        Scanner stdin = new Scanner(System.in);
	        System.out.println("Connect 4" + "\n");
	        String columnSt;
                int column=0;
	        int turncounter=0;
                int totalturncounter=0;
                int[] count = new int[7];
	        boolean error = true;
	        Board board1 = new Board();
	        board1.print();
	        System.out.println();
	
	       while (board1.win() != true && totalturncounter<=41 ) {
	            
	            try {
	               if(turncounter%2 ==0) {
	                   System.out.print("Red choose a column 0-6: ");

	                columnSt = stdin.nextLine();
                        try{
                        column = Integer.parseInt(columnSt);
                        }catch(NumberFormatException x){
                            throw new InputMismatchException("Wrong Format");
                        }
                        if(count[column]<6){
	                board1.placeR(column);
                        count[column]+=1;
                        }else{
                            System.out.println("Column is full");
                           
                            throw new Exception("Full");
                        }
	                board1.print();
	                turncounter++;
                        totalturncounter++;
                        
	            }else{
	                System.out.print("Yellow choose a column 0-6: ");

	                columnSt = stdin.nextLine();
                         try{
                        column = Integer.parseInt(columnSt);
                        }catch(NumberFormatException  x){
                            throw new InputMismatchException("Wrong Format");
                        }
                        if(count[column]<6){
	                board1.placeY(column);
                        count[column]+=1;
                        }else{
                            System.out.println("Column is full");
                           
                            throw new Exception("Full");
                        }
	                
	                board1.print();
	                turncounter++;
                        totalturncounter++;
	                    }     

	            }  
                    catch (IndexOutOfBoundsException e) {
	                System.out.println("Invalid index, re-enter a number between 0-6");
	              
                    }catch (InputMismatchException x) {
	                System.out.println("Input must be in integer format, re-enter input in integer form (between 0 and 6)");
	               
                    }catch (Exception ex) {
	                System.out.println("Invalid, re-enter input");
	            
	               
	        
	            }
	        }
               if(board1.win()==true&& turncounter%2!=0){
                   System.out.println("GAME OVER!! \nRED WINS!");
               }
              else if(board1.win()==true&& turncounter%2==0){
                   System.out.println("GAME OVER!! \nYELLOW WINS!");
               }else{
                  System.out.println("DRAW!");
              }
              
	}
	   }
	    


	    class Board {

	        String[][] Board = new String[6][7];

	        public Board() {
	            for (int i = 0; i < 6; i++) {
	                for (int j = 0; j < 7; j++) {
	                    this.Board[i][j] = (" ");

	                }

	            }

	        }

	        public void placeR(int a) {
                    if(a<0 || a>6){
                        throw new IndexOutOfBoundsException("help");
                    }
	            
	                if (Board[5][a].equalsIgnoreCase(" ")) {
	                    Board[5][a] = "R";
	                } else if (!(Board[0][a].equalsIgnoreCase(" "))) {
	                    System.out.println("Invalid Input.Enter new input");
	                } else {
	                    for (int k = 0; k < 6; k++) {

	                        if (!(Board[k][a].equals(" "))) {

	                            Board[k - 1][a] = "R";
	                            break;

	                        }

	                    }

	                }
	           
	        }

	        public void placeY(int a) {

	            if(a<0 || a>6){
                        throw new IndexOutOfBoundsException("help");
                    }
  
                    
	                if (Board[5][a].equals(" ")) {
	                    Board[5][a] = "Y";
	                } else if (!(Board[0][a].equalsIgnoreCase(" "))) {
	                    System.out.println("Invalid Input.Enter new input");
	                } else {
	                    for (int k = 0; k < 6; k++) {

	                        if (!(Board[k][a].equals(" "))) {

	                            Board[k - 1][a] = "Y";
	                            break;

	                        }

	                    }

	                }
	          

	        }

	        public void print() {
	            for (int i = 0; i < 6; i++) {
	                System.out.print("|");
	                for (int j = 0; j < 7; j++) {
	                    System.out.print(this.Board[i][j] + "|");
	                }

	                System.out.println();
	            }
	            

	        }

	        public boolean win() {
	            boolean win = false;
	            boolean red = false;
	            boolean yellow = false;
	            for (int i = 0; i < 6; i++) {
	                for (int j = 0; j < 4; j++) {
	                    if (Board[i][j].equalsIgnoreCase(Board[i][j + 1]) && Board[i][j].equalsIgnoreCase(Board[i][j + 2]) && Board[i][j].equalsIgnoreCase(Board[i][j + 3]) && !(Board[i][j].equalsIgnoreCase(" "))) {
	                        win = true;
	                    }


	                }
	            }
	     
	            for (int i = 0; i < 3; i++) {
	                for (int j = 0; j < 7; j++) {
	                    if (Board[i][j].equalsIgnoreCase(Board[i + 1][j]) && Board[i][j].equalsIgnoreCase(Board[i + 2][j]) && Board[i][j].equalsIgnoreCase(Board[i + 3][j]) && !(Board[i][j].equalsIgnoreCase(" "))) {
	                        win = true;
	                    }
	                  

	                }
	            }
	            for (int i = 0; i < 3; i++) {
	                for (int j = 0; j < 4; j++) {
	                    if (Board[i][j].equalsIgnoreCase(Board[i + 1][j + 1]) && Board[i][j].equalsIgnoreCase(Board[i + 2][j + 2]) && Board[i][j].equalsIgnoreCase(Board[i + 3][j + 3]) && !(Board[i][j].equalsIgnoreCase(" "))) {
	                        win = true;
	                    }
	                   
	                }

	            }
	             for(int i =0;i<3;i++){
	        for(int j =6;j>2;j--){
	          if (Board[i][j].equalsIgnoreCase(Board[i + 1][j-1]) && Board[i][j].equalsIgnoreCase(Board[i + 2][j-2]) && Board[i][j].equalsIgnoreCase(Board[i + 3][j-3]) && !(Board[i][j].equalsIgnoreCase(" "))) {
	                    win = true;
	                }
	               
	     }
	       
	    }
	            return win;

	        }

	    
	    }
