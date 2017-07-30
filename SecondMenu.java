package Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;


public class SecondMenu extends JFrame{
	private static final long serialVersionUID = 1L;
	
	public static int m,Mines;
	
	int screenWidth=300;
	int screenHeight=200;
	
	int buttonWidth=80;
	int buttonHeight=40;
	
	JButton Easy,Medium,Hard;
	
	public SecondMenu(){
		
		addButtons();
		addActions();
		
		getContentPane().setLayout(null);//getContentPane() is used when an object of JFrame class has not been created.
		
		Easy.setBounds((screenWidth-buttonWidth)/2,20,buttonWidth,buttonHeight);
		Medium.setBounds((screenWidth-buttonWidth)/2,65,buttonWidth,buttonHeight);
		Hard.setBounds((screenWidth-buttonWidth)/2,110,buttonWidth,buttonHeight);
		
		//add buttons to the frame
		getContentPane().add(Easy);
		getContentPane().add(Medium);
		getContentPane().add(Hard);
		
		//jframe stuff
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
		setSize(screenWidth,screenHeight);
		setTitle("Choose level of difficulty");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
	}
	
	
	private void addButtons(){
		
		Easy=new JButton("EASY");
		Medium=new JButton("MEDIUM");
		Hard=new JButton("HARD");
	}
	
	
	private void addActions(){
		
		Easy.addActionListener(new ActionListener(){//adding action listener to the Play button.

			
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				
				m=10;
				Mines=15;
				//setValue(m,Mines);
				new Minesweeper(m,Mines);
							
			}
			
		} );//Easy Button
		
		
		Medium.addActionListener(new ActionListener(){//adding action listener to the Play button.

			
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				
				m=15;
				Mines=30;
				//setValue(m,Mines);
				new Minesweeper(m,Mines);
	
			}
			
		} );//Medium Button
		
		
		Hard.addActionListener(new ActionListener(){//adding action listener to the Play button.

			
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				
				m=20;
				Mines=50;
				//setValue(m,Mines);
				new Minesweeper(m,Mines);
				
			}
			
		} );//Hard Button
		
		
	}
	
	/*private void setValue(int m,int Mines){
		
		Minesweeper ms=new Minesweeper();
		ms.n=m;
		ms.noOfMines=Mines;
		
	}*/

}
