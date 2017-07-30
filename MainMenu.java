package Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;


public class MainMenu extends JFrame{
	private static final long serialVersionUID = 1L;
	
	int screenWidth=300;
	int screenHeight=200;
	
	int buttonWidth=100;
	int buttonHeight=60;
	
	JButton Play,Quit;
	
	public MainMenu(){
		
		addButtons();
		addActions();
		
		getContentPane().setLayout(null);//getContentPane() is used when an object of JFrame class has not been created.
		
		Play.setBounds((screenWidth-buttonWidth)/2,20,buttonWidth,buttonHeight);
		Quit.setBounds((screenWidth-buttonWidth)/2,90,buttonWidth,buttonHeight);
		
		//add buttons to the frame
		getContentPane().add(Play);
		getContentPane().add(Quit);
		
		//jframe stuff
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
		setSize(screenWidth,screenHeight);
		setTitle("MINESWEEPER");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
	}
	
	
	private void addButtons(){
		
		Play=new JButton("PLAY");
		Quit=new JButton("QUIT");
	}
	
	
	private void addActions(){
		
		Play.addActionListener(new ActionListener(){//adding action listener to the Play button.

			public void actionPerformed(ActionEvent e) {
				
				dispose();
				
				new SecondMenu();
	
				
			}
			
		} );//Play Button
		
		
		Quit.addActionListener(new ActionListener(){//adding action listener to the Quit button.

			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
				
			}
			
		} );//Quit Button
		
	}
	
	
}

