package com.AbdirashidJamaA1.Yahtzee;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;

public class Player 
{
	int points;
	Die [] dice;
	int round;
	SortedMap<Integer, Integer> scoreSheet;
	private Client clientplayer;
	private int pID;
	private int otherID;
	private int otherOtherID;
	private String name;
	
	Player(){
		this.scoreSheet = new TreeMap<Integer, Integer>();
		this.points=0;
		this.round=1;
	}
	public Die [] getDice() {
		return this.dice;
	}
	public SortedMap<Integer, Integer> getScoreSheet(){
		return scoreSheet;
	}
	public void setScoreSheet(SortedMap<Integer, Integer> riggedScoreSheet) {
		this.scoreSheet=riggedScoreSheet;
	}
	public void giveDice(){
		this.dice=null; 
	}
	public void receiveDice(Die [] receivedDies) {
		for(int i=0; i<=4;i++ ){
			receivedDies[i].setValue(0);
		}
		this.dice = receivedDies;
	}
	public void rollDice() {
		for(int i=0; i<=4;i++ ){
			this.dice[i].roll();
		}
	}
	public void rollDie(int i) {
		dice[i].roll();
	}
	public int getRound() {return round;}
	public int getPoints() {return points;}
	public void setPoints(int i) {this.points=i;}
	
	public void score(int i) {
		this.round=this.round+1;
		switch(i) {
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
			scoreUpperSection(i);
			checkUpperBonus();
			break;
		case 7:
			scoreLargeStraight();
			break;
		case 8:
			scoreSmallStraight();
			break;
		case 9:
			scoreFullHouse();
			break;
		case 10:
			scoreThreeOfAKind();
			break;
		case 11:
			scoreFourOfAKind();
			break;
		case 12:
			scoreChance();
			break;
		case 13:
			scoreYahtzee();
			break;
		}
	}
	public void scoreUpperSection(int n) {
		int p=0;
		for(int i=0; i<5; i++) {
			int dieValue = this.dice[i].getValue();
			if(dieValue ==n) {
				p=p+dieValue;
			}
		}
		this.scoreSheet.put(n, p);
		this.points=points+p;
	}
	public void scoreThreeOfAKind() {
		//array hold 5 spots each represent the amount of die of that value exist in dice
		int p=0;
		boolean found= false;
		int[] dieCount = {0,0,0,0,0,0};
		for(int i=0; i<5; i++) {
			dieCount[this.dice[i].getValue()-1]++;
		}
		for(int i=0; i<6; i++) {
			if(dieCount[i]>=3) {
				p=addDice();
				this.points=this.points+p;
				found=true;
				break;
			}
		}
		if (!found) {
			this.points=this.points+p;
		}
		this.scoreSheet.put(10, p);
	}
	public void scoreChance(){
		int p=0;
		for(int i=0; i<5; i++) {
			p=p+this.dice[i].getValue();
		}
		this.scoreSheet.put(12, p);
		this.points=points +p;
		
	}
	public int addDice() {
		int p=0;
		for(int i=0; i<5; i++) {
			p=p+this.dice[i].getValue();
		}
		return p;
	}
	public void scoreFourOfAKind() {
		//array hold 6 spots each represent the amount of die of that value exist in dice
		int p=0;
		boolean found= false;
		int[] dieCount = {0,0,0,0,0,0};
		for(int i=0; i<5; i++) {
			dieCount[this.dice[i].getValue()-1]++;
		}
		for(int i=0; i<6; i++) {
			if(dieCount[i]>=4) {
				p = addDice();
				this.points=this.points+p;
				found=true;
				break;
			}
		}
		if (!found) {
			this.points=this.points+p;
		}
		this.scoreSheet.put(11, p);
	}
	public void scoreSmallStraight() {
		int p=0;
		int count=0;
		int[] dieCount = {0,0,0,0,0,0}; //each number represent if the die face is present
		for(int i=0; i<5; i++) {
			dieCount[this.dice[i].getValue()-1]=1;
		}
		for(int i=0; i<6; i++) {
			if(dieCount[i]==1) {
				count++;
				if(count ==4) {
					p=30;
					break;
				}
			}
			else {
				count=0;
			}
		}
		this.points = this.points +p;
		this.scoreSheet.put(8, p);
	}
	public void scoreLargeStraight() {
		int p=0;
		int count=0;
		int[] dieCount = {0,0,0,0,0,0}; //each number represent if the die face is present
		for(int i=0; i<5; i++) {
			dieCount[this.dice[i].getValue()-1]=1;
		}
		for(int i=0; i<6; i++) {
			if(dieCount[i]==1) {
				count++;
				if(count ==5) {
					p=40;
					break;
				}
			}
			else {
				count=0;
			}
		}
		this.points = this.points +p;
		this.scoreSheet.put(7, p);
	}
	public void scoreFullHouse() {
		int p=0;
		boolean threeOfAKind=false;
		boolean twoOfAKind=false;
		int[] dieCount = {0,0,0,0,0,0}; //each number represent if the die face is present
		for(int i=0; i<5; i++) {
			dieCount[this.dice[i].getValue()-1]++;
		}
		for(int i=0; i<6; i++) {
			if(dieCount[i]==3) {
				threeOfAKind=true;
			}
			else if(dieCount[i]==2) {
				twoOfAKind=true;
			}
		}
		if(threeOfAKind&&twoOfAKind) {
			p=25;
		}
		this.points = this.points + p;
		this.scoreSheet.put(9, p);
	}
	public void scoreYahtzee() {
		int value= this.dice[0].getValue(); //value of 
		int p=50;
		for(int i=1; i<5; i++) {
			if (this.dice[i].getValue()!= value) {
				p=0;
				break;
			}
		}
        if(this.scoreSheet.get(13)!=null&&p!=0) {
            p=p+50;
            this.points = this.points + p;
            this.scoreSheet.put(15, p);
        }
        else {
            this.points = this.points + p;
            this.scoreSheet.put(13, p);
        }
	}
	public void checkUpperBonus() {
		int p=0;
		for(int i=1;i<=6;i++) {
			if(this.scoreSheet.get(i)!=null) {
				p=p+this.scoreSheet.get(i);
			}
		}
		if(p>=63&&this.scoreSheet.get(14)==null){
			this.points= this.points + 35;
			this.scoreSheet.put(14, 35);
		}
	}
	
	public String printDice() {
		return "Your rolled: | " + this.dice[0].getValue() + " | " + this.dice[1].getValue() + " | " + this.dice[2].getValue() + " | " + this.dice[3].getValue() + " | " + this.dice[4].getValue() + " | ";
	}
	
	public String printScore() {
		String[] scores = new String[15];
		for(int i = 1; i < 15; i++) {
			if(this.scoreSheet.containsKey(i)) {
				scores[i] = String.valueOf(scoreSheet.get(i));
			}
			else {
				scores[i] = "";
			}
		}
		return "          | current Score: " + this.getPoints()+ "   | Current Round: "+ this.getRound() + "\n"
				+ "(1) Ones: " + scores[1] + " (2) Twos: " + scores[2] + "(3) Threes: "  + scores[3] + " (4) Fours: " + scores[4] + " (5) fives: " + scores[5] + " (6) Sixes: " + scores[6] + "  | Bonus: " + scores[14] + "\n"
				+ "(7) Large Straight: " + scores[7] + "(8) Small Striaght: " + scores[8] + "(9) Full House: " + scores[9] + "(10) Three of a Kind: " + scores[10] + "\n"
				+ "(11) Four of a Kind: " + scores[11] + " (12) Chance: " + scores[12] + " (13) Yahtzee: "  + scores[13] + "\n"
				+ "--------------------------- " +"\n";
	}
	//client and networking code
	public void connectToServer(int port) {
		clientplayer = new Client("localhost", port);
	}
	
	private class Client {
		private Socket socket;
		private DataInputStream input;
		private DataOutputStream out;
		private Scanner scanner;
		
		public Client(String address, int port) {
			System.out.println("attempting to connect to port " + port);
			try {
				socket = new Socket(address, port);
				input = new DataInputStream(socket.getInputStream());
				out = new DataOutputStream(socket.getOutputStream());
				scanner = new Scanner(System.in);
				pID = input.readInt();
				
				//Get names send them to server
				System.out.println("Welcome player " + pID + " Please enter your name" );
				name = scanner.nextLine();
				out.writeUTF(name);
				out.flush();
				
				//System.out.flush();
				while(true) {
					//System.out.flush();
					//System.out.println(input.readUTF()); //scoreboard
					while(true) {
						try {  //check if other player if it is restart loop
							//boolean other=false;
							String scoreboard=input.readUTF();
							//if(scoreboard.equals("")) {
							//	other=true;
							//}
							System.out.println(scoreboard);    //enter and scoreboard
							//if(other) {
							//	input.readUTF();
							//	continue;
							//}
							String enter= "sdfjnksdf";
							while(true) {
								//System.out.println("");
								//System.out.print(enter);
								enter = scanner.nextLine();   ///enter
								if(enter.equals(" ")) {
									out.writeInt(100);
									out.flush();
									break;
								}
							}
							
							if(enter.equals(" ")) {
								//out.writeInt(100);
								//out.flush();
								System.out.println(input.readUTF()); //rolled die and choices
								try {
									int choice = scanner.nextInt();
									while(true){   //only breaks at choice
										if(choice==3) {
											out.writeInt(choice);
											out.flush();
											System.out.println(input.readUTF()); 
											int category = scanner.nextInt();
											out.writeInt(category);
											out.flush();
											break;
										}
										else if(choice==2) {
											System.out.println(input.readUTF()); 
										}
										else {
										
										}
									}
									
								}
								catch(EOFException e) {
							
								}
							}
						}
						catch(EOFException e) {
							System.out.println(e);
						}
					}	
				}
			}
			catch(IOException i) {
				System.out.println(i);
			}
		}
		
	}
	
	public static void main(String [] args) {
		int port = Integer.parseInt(args[0]);
		Player p = new Player();
		p.connectToServer(port);
	}
	

}
