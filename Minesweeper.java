package Game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Minesweeper implements ActionListener{
	
	
	int n;
	int noOfMines;
	
	JButton[][] buttons=new JButton[100][100];
	int info[][]=new int[100][100];
	
	/*public void set(SecondMenu sm){
		if(sm.Easy.isSelected()){
			n=15;
			noOfMines=10;
		}
		else if(sm.Medium.isSelected()){
			n=20;
			noOfMines=20;
		}
		else if(sm.Hard.isSelected()){
			n=20;
			noOfMines=30;
		}
	}*/
	
	static final int MINE=-1;
	static int noOfMoves=0;
	
	JFrame frame=new JFrame("Minesweeper");
	JButton reset=new JButton("Reset");
	
	
	Container grid=new Container();
	
	
    public Minesweeper(int nl,int m) {
		
    	noOfMines=m;
    	n=nl;
    	
    	
    	/*SecondMenu sm=new SecondMenu();
    	set(sm);*/
    	
    	//n=SecondMenu.m;
    	//noOfMines=SecondMenu.Mines;
    	
    	//System.out.println(""+noOfMines);//for debugging
    	
		frame.setSize(1300,700);
	    frame.setLayout(new BorderLayout());
	    frame.add(reset,BorderLayout.NORTH);
	    reset.addActionListener(this);
	    
	    grid.setLayout(new GridLayout(n,n));
	    for(int i=0;i<n;i++){
	    	for(int j=0;j<n;j++){
	    		buttons[i][j]=new JButton();
	    		buttons[i][j].addActionListener(this);
	    		grid.add(buttons[i][j]);
	    	}
	    }
	    frame.add(grid,BorderLayout.CENTER);
	    
	    //System.out.println(""+n);
	    
	    createMines();
	    
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setVisible(true);
	    frame.setResizable(false);
	    frame.setLocationRelativeTo(null);
		
	}
	
	
	/*public static void main(String[] args) {
		// TODO Auto-generated method stub 
		
		new  Minesweeper();
		 
	}*/
	
	
	


	public void createMines(){
		
		ArrayList<Integer> check=new ArrayList<Integer>(); 
		//System.out.println(""+n+" "+n);
		
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				check.add(i*100+j);
			}
		}
		
		//the info array is Re-declared here in order to discard any previous data from the previous game.
		//as it will initialise the info array to zero.
		info=new int[n][n];
		//NOTE:the declaration should be as shown above and not as :-
		//int[][] info=new int[n][n];
		//because whenever we use info array in the else clause of actionPerformed function,we get a value zero if we do not declare the info array as told to.
		
		
		// setting noOfmines
		for(int i=0;i<noOfMines;i++){
			//System.out.println(""+check.size());
			Random dice=new Random();
			int choice=dice.nextInt(check.size());
			info[check.get(choice)/100][check.get(choice)%100]=MINE;
			check.remove(choice);
			
		}
		
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				int numberOfBombs=0;
				if(info[i][j]!=MINE){
					if(i-1>=0 && j-1>=0 && info[i-1][j-1]==MINE){//up left
						numberOfBombs++;
						//System.out.println("1");
					}
					if(i-1>=0 && info[i-1][j]==MINE){//up
						numberOfBombs++;
						//System.out.println("2");
					}
					if(i-1>=0 && j+1<n && info[i-1][j+1]==MINE){//up right
						numberOfBombs++;
						//System.out.println("3");
					}
					if(j+1<n && info[i][j+1]==MINE){//right
						numberOfBombs++;
						//System.out.println("4");
					}
					if(j+1<n && i+1<n && info[i+1][j+1]==MINE){//down right
						numberOfBombs++;
						//System.out.println("5");
					}
                    if(i+1<n && info[i+1][j]==MINE){//down
                    	numberOfBombs++;
                    	//System.out.println("6");
					}
                    if(i+1<n && j-1>=0 && info[i+1][j-1]==MINE){//down left
                    	numberOfBombs++;
                    	//System.out.println("7");
					}
                    if(j-1>=0 && info[i][j-1]==MINE){//left
                    	numberOfBombs++;
                    	//System.out.println("8");
                    }
                    info[i][j]=numberOfBombs;
                    //System.out.println(i+" "+j+"");
				}
			}
		}
		
		
		//for debugging purpose
		/*for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				System.out.println(""+info[i][j]);
			}
		}*/
		
		
		//for debugging purpose
		/*for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				//if(event.getSource().equals(buttons[i][j])){
					buttons[i][j].setText(info[i][j]+"");
				//}
			}
		}*/	
	}
	
	
	public void OpenAdjacentBlocks(int i,int j) {
		//Whenever we click on any of the buttons on the grid with value zero,this function is called to open the blocks with non-zero value which are adjacent to the blocks with zero value.
		buttons[i][j].setText(""+info[i][j]);
		buttons[i][j].setEnabled(false);
		
	}
	
	
	public void OpenAllZeroes(int i,int j) {
		
		if(i-1>=0 && buttons[i-1][j].isEnabled() && info[i-1][j]==0){//up
			//buttons[i-1][j].setText(""+info[i-1][j]);
			buttons[i-1][j].setEnabled(false);
			OpenAllZeroes(i-1,j);
		}
		if(i-1>=0 && buttons[i-1][j].isEnabled() && info[i-1][j]!=MINE){
			OpenAdjacentBlocks(i-1,j);
		}
		
		
		if(i-1>=0 && j-1>=0 && buttons[i-1][j-1].isEnabled() && info[i-1][j-1]==0){//up-left
			//buttons[i-1][j-1].setText(""+info[i-1][j-1]);
			buttons[i-1][j-1].setEnabled(false);
			OpenAllZeroes(i-1,j-1);
		}
		if(i-1>=0 && j-1>=0 && buttons[i-1][j-1].isEnabled() && info[i-1][j-1]!=MINE){
			OpenAdjacentBlocks(i-1,j-1);
		}
		
		
		if(j-1>=0 && buttons[i][j-1].isEnabled() && info[i][j-1]==0){//left
			//buttons[i][j-1].setText(""+info[i][j-1]);
			buttons[i][j-1].setEnabled(false);
			OpenAllZeroes(i,j-1);
		}
		if(j-1>=0 && buttons[i][j-1].isEnabled() && info[i][j-1]!=MINE){
			OpenAdjacentBlocks(i,j-1);
		}
		
		
		if(i+1<n && j-1>=0 && buttons[i+1][j-1].isEnabled() && info[i+1][j-1]==0){//down-left
			//buttons[i+1][j-1].setText(""+info[i+1][j-1]);
			buttons[i+1][j-1].setEnabled(false);
			OpenAllZeroes(i+1,j-1);
		}
		if(i+1<n && j-1>=0 && buttons[i+1][j-1].isEnabled() && info[i+1][j-1]!=MINE){
			OpenAdjacentBlocks(i+1,j-1);
		}
		
		
		if(i+1<n && buttons[i+1][j].isEnabled() && info[i+1][j]==0){//down
			//buttons[i+1][j].setText(""+info[i+1][j]);
			buttons[i+1][j].setEnabled(false);
			OpenAllZeroes(i+1,j);
		}
		if(i+1<n && buttons[i+1][j].isEnabled() && info[i+1][j]!=MINE){
			OpenAdjacentBlocks(i+1,j);
		}
		
		
		if(i+1<n && j+1<n && buttons[i+1][j+1].isEnabled() && info[i+1][j+1]==0){//down-right
			//buttons[i+1][j+1].setText(""+info[i+1][j+1]);
		    buttons[i+1][j+1].setEnabled(false);
			OpenAllZeroes(i+1,j+1);
		}
		if(i+1<n && j+1<n && buttons[i+1][j+1].isEnabled() && info[i+1][j+1]!=MINE){
			OpenAdjacentBlocks(i+1,j+1);
		}
		
		
		if(j+1<n && buttons[i][j+1].isEnabled() && info[i][j+1]==0){//right
			//buttons[i][j+1].setText(""+info[i][j+1]);
			buttons[i][j+1].setEnabled(false);
			OpenAllZeroes(i,j+1);
		}
		if(j+1<n && buttons[i][j+1].isEnabled() && info[i][j+1]!=MINE){
			OpenAdjacentBlocks(i,j+1);
		}
		
		
		if(i-1>=0 && j+1<n && buttons[i-1][j+1].isEnabled() && info[i-1][j+1]==0){//up-right
			//buttons[i-1][j+1].setText(""+info[i-1][j+1]);
			buttons[i-1][j+1].setEnabled(false);
			OpenAllZeroes(i-1,j+1);
		}
		if(i-1>=0 && j+1<n && buttons[i-1][j+1].isEnabled() && info[i-1][j+1]!=MINE){
			OpenAdjacentBlocks(i-1,j+1);
		}
			
	}

	
	public void GameWon() {
		boolean checkWon=true;
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				if(info[i][j]!=MINE && buttons[i][j].isEnabled()){
					checkWon=false;
				}
			}
		}
		if(checkWon==true){
			//pop up a window or dialogue box saying you win.
			JOptionPane.showMessageDialog(frame,"YOU WIN\nNo. of Moves:"+noOfMoves);
		}
	}
	

	public void GameLost(){
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				if(info[i][j]==MINE){
					buttons[i][j].setText("MINE");
				}
				buttons[i][j].setEnabled(false);
			}
		}
		//pop up a dialogue box or a window saying you lost.
		JOptionPane.showMessageDialog(frame,"YOU LOST\nNo. of Moves:"+noOfMoves);
	}
	
	
	
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		
		if(event.getSource().equals(reset)){
			//Re-enable,set the buttons and call createMines.
			noOfMoves=0;
			for(int i=0;i<n;i++){
				for(int j=0;j<n;j++){
					if(buttons[i][j].getBackground()==Color.red){
						buttons[i][j].setBackground(null);
					}
					buttons[i][j].setEnabled(true);
					buttons[i][j].setText("");
				}
			}
			
			createMines();
			
		}
		else{
			noOfMoves++;
			for(int i=0;i<n;i++){
				for(int j=0;j<n;j++){
					if(event.getSource().equals(buttons[i][j])){
						//System.out.println(""+info[i][j]);
						if(info[i][j]==MINE){
							//buttons[i][j].setEnabled(false);
							buttons[i][j].setBackground(Color.red);
							//Icon bomb=new ImageIcon(getClass().getResource("bomb.png"));
							
							GameLost();
						}
						else if(info[i][j]==0){//open all zeros connected to it using recursion.
							//buttons[i][j].setText(""+info[i][j]);
							buttons[i][j].setEnabled(false);
							
							OpenAllZeroes(i,j);
						}
						else{
							buttons[i][j].setText(""+info[i][j]);
						    buttons[i][j].setEnabled(false);
						    
						    GameWon();//check every time whether all the undiscovered blocks are mines or not.
						}
						
					}
				}
			}
		}
	}

	
}
