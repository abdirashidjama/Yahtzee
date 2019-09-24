package com.AbdirashidJamaA1.Yahtzee;
import java.net.*;

import javax.print.DocFlavor.STRING;

import java.io.*;

public class Server {
	
	private Socket socket=null;
	private ServerSocket server=null;
	private DataInputStream input =null;
	private int numPlayers=0;
	private ServerConnection p1;
	private ServerConnection p2;
	private ServerConnection p3;
	
	public Server(int port) {
		// initialize game
		System.out.println("Game server started!");
		//Game game= new Game();
		//game.start();
		
		//start server
		try {
			server = new ServerSocket(port);
			
			
			/*
			//player input
			input = new DataInputStream(
					new BufferedInputStream(
							socket.getInputStream()));
			
			String line = "";
			
			while(!line.equals("exit")) {
				try {
					line = input.readUTF();
					
					System.out.println(line);
				}
				catch(IOException i){
					System.out.println(i);
				}
			}
			System.out.println("Game over");
			
			socket.close();
			input.close();
			*/
			
		}
		catch(IOException i){
			System.out.println(i);
		}
	}
	
	public void connectPlayers() {
		System.out.println("Waiting for players ...");
		try {
			while(this.numPlayers < 3) {
				this.numPlayers++;
				socket = server.accept();
				System.out.println("player " + this.numPlayers + " joined");
				ServerConnection sc= new ServerConnection(socket, this.numPlayers);
				switch(numPlayers){
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
				while(true) {
					
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
