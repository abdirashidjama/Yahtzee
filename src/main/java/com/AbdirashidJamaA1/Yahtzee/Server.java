package com.AbdirashidJamaA1.Yahtzee;
import java.net.*;
import java.util.concurrent.atomic.AtomicInteger;

import javax.print.DocFlavor.STRING;

import java.io.*;

public class Server {
	private Socket socket=null;
	private ServerSocket server=null;
	private DataInputStream input =null;
	private final AtomicInteger numPlayers = new AtomicInteger(0);
	private final AtomicInteger names = new AtomicInteger(0); //increment by one each time name is given
	private final AtomicInteger turn = new AtomicInteger(1);
	private ServerConnection p1;
	private ServerConnection p2;
	private ServerConnection p3;
	Game game = new Game();
	//private final AtomicInteger counter= new AtomicInteger(0);
	
	public Server(int port) {
		game.start();
		System.out.println("Game server started!");
		try {
			server = new ServerSocket(port);
			
		}
		catch(IOException i){
			System.out.println(i);
		}
	}
	
	
	public void connectPlayers() {
		System.out.println("Waiting for players ...");
		try {
			while(numPlayers.get() < 3) {
				int num =  numPlayers.incrementAndGet();
				socket = server.accept();
				//System.out.println("player " + this.numPlayers + " joined");
				ServerConnection sc= new ServerConnection(socket, num);
				switch(num){
					case 1:
						p1=sc;
						break;
					case 2:
						p2=sc;
						break;
					case 3:
						p3=sc;
						break;
				}
				Thread t = new Thread(sc);
				t.start();
				
			}
		}
		catch(IOException i){
			System.out.println(i);
		}
		
	}
	
	public class ServerConnection implements Runnable{
		private Socket socket;
		private DataInputStream in;
		private DataOutputStream out;
		private int pID;
		private String name =null;
		
		public ServerConnection(Socket s, int id) {
			socket = s;
			pID=id;
			try {
				in = new DataInputStream(socket.getInputStream());
				out = new DataOutputStream(socket.getOutputStream());
			}
			catch(IOException e){
				System.out.print(e);
			}
		}
		public void run() {
			try {
				
				out.writeInt(pID);
				out.flush();
				
				name = in.readUTF(); 
				System.out.println( name + " has joined the game");
				names.getAndIncrement();
				while(names.get()<=2) {
					//loop till all names are given
				}

				
				
				
				

				//out.writeUTF(scoreboard);
				//out.flush();
				String clear="";
				for(int i=0; i<30; i++) {
					clear=clear + '\n';
				}
				
				String enter = "Press <<ENTER>> to roll the dice ...";
				while(true) {
					String scoreboard= "--------------------------- "+ "\n"
							+ "Name: " + p1.name + "     "
							+ game.getPlayers()[0].printScore()
							+ "Name: " + p2.name + "     "
							+ game.getPlayers()[1].printScore()
							+ "Name: " + p3.name + "     "
							+ game.getPlayers()[2].printScore();
					int enterpressed=0;
					
					if(turn.get()==1)
					{
						if(pID==1) {

							out.writeUTF(scoreboard + "\n" + enter);
							out.flush();
							
							while(true) {
								enterpressed = in.readInt();
								if(enterpressed==100) {
									break;
								}
							}
						
							String choice="What action would you like to perform next?"+"\n"
								+ "(1) Select dice to hold, and then re-roll the other dice" +"\n"
								+ "(2) Re-roll all the dice?" +"\n"
								+ "(3) Score this round?" +"\n";
							game.getPlayers()[pID-1].rollDice();
							String dice= game.getPlayers()[pID-1].printDice();
							out.writeUTF(dice + "\n" + choice);
							out.flush();
							int choicenumber =0;
							while(true) {
								choicenumber = in.readInt();
								if(choicenumber==3) {
									break;
								}
							}
							if(choicenumber==3) {	
								out.writeUTF("What category do you want to score this round against? (Please enter the category number) ");
								game.score(in.readInt());
								game.endTurn();
								System.out.println("Player 1 has completed their turn");
								turn.incrementAndGet();	
							}
							else if(choicenumber==2) {
								game.getPlayers()[pID-1].rollDice();
								dice= game.getPlayers()[pID-1].printDice();
								out.writeUTF(dice + "\n" + choice);
								out.flush();
							}
							
						}
						else {
							//out.writeUTF("");
							//out.flush();
							//out.writeUTF(scoreboard + "\n");
							//out.flush();
							//out.writeUTF("");
							//out.flush();
						}	
					}
					else if(turn.get()==2) {
						if(pID==2) {
							out.writeUTF(scoreboard + "\n" + enter);
							out.flush();
							
							while(true) {
								enterpressed = in.readInt();
								if(enterpressed==100) {
									break;
								}
							}
						
							String choice="What action would you like to perform next?"+"\n"
								+ "(1) Select dice to hold, and then re-roll the other dice" +"\n"
								+ "(2) Re-roll all the dice?" +"\n"
								+ "(3) Score this round?" +"\n";
							game.getPlayers()[pID-1].rollDice();
							String dice= game.getPlayers()[pID-1].printDice();
							out.writeUTF(dice + "\n" + choice);
							out.flush();
							int choicenumber =0;
							while(true) {
								choicenumber = in.readInt();
								if(choicenumber==3) {
									break;
								}
							}
							if(choicenumber==3) {	
								out.writeUTF("What category do you want to score this round against? (Please enter the category number) ");
								game.score(in.readInt());
								game.endTurn();
								System.out.println("Player 2 has completed their turn");
								turn.incrementAndGet();	
							}
							
						}
						else {
							
						}	
					}
					else if(turn.get()==3) {
						if(pID==3) {
							out.writeUTF(scoreboard + "\n" + enter);
							out.flush();
							
							while(true) {
								enterpressed = in.readInt();
								if(enterpressed==100) {
									break;
								}
							}
						
							String choice="What action would you like to perform next?"+"\n"
								+ "(1) Select dice to hold, and then re-roll the other dice" +"\n"
								+ "(2) Re-roll all the dice?" +"\n"
								+ "(3) Score this round?" +"\n";
							game.getPlayers()[pID-1].rollDice();
							String dice= game.getPlayers()[pID-1].printDice();
							out.writeUTF(dice + "\n" + choice);
							out.flush();
							int choicenumber =0;
							while(true) {
								choicenumber = in.readInt();
								if(choicenumber==3||choicenumber==2) {
									break;
								}
							}
							if(choicenumber==3) {	
								out.writeUTF("What category do you want to score this round against? (Please enter the category number) ");
								game.score(in.readInt());
								game.endTurn();
<<<<<<< HEAD
								if(game.getPlayers()[2].getRound()==13) {
=======
								if(game.getPlayers()[2].getRound()==14) {
>>>>>>> Networking: added error checking
									Player winner=null;
									String winnername=null;
									if(game.getPlayers()[0].getPoints()>game.getPlayers()[1].getPoints()&&game.getPlayers()[0].getPoints()>game.getPlayers()[2].getPoints()) {
										winner=game.getPlayers()[0];
										name=p1.name;
									}
									else if(game.getPlayers()[1].getPoints()>game.getPlayers()[2].getPoints()&&game.getPlayers()[1].getPoints()>game.getPlayers()[0].getPoints()) {
										winner=game.getPlayers()[1];
										name=p2.name;
									}
									else {
										winner=game.getPlayers()[2];
										name=p3.name;
									}
									System.out.print("game over player " + name + " wins with "+ winner.getPoints());   //make a game.getwinner();
								}
								else {
									System.out.println("Player 3 has completed their turn");
									System.out.println("Round "+(game.getPlayers()[2].getRound()-1) + " complete");
									System.out.println("");
									turn.decrementAndGet();
									turn.decrementAndGet();
								}
							}
							else if(choicenumber ==2) {
								
							}
							
						}
						else {
							
						}	
					}
					
				}

			}
			catch(IOException e) {
				System.out.println(e);
				}
		}
	}
	
	
	public static void main(String args[]) {
		int port = Integer.parseInt(args[0]);
		Server server = new Server(port);
		server.connectPlayers();
	}

}
