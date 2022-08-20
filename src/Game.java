import java.util.*;

public class Game {
	
	private Board board;
	private Letters lets;
	private Scanner sc;
	
	public Game() {
		sc = new Scanner(System.in);
		board = new Board();
		lets = new Letters();
	}
	
	public void playGame() {		//Gets input from the user for desired course of action
		boolean gameOver = false;	//true when user wants to end the game
		board.printBoard(lets);
		String print = "Enter a letter to play it";
		if(lets.getPool().size()>=3) {
			print += ", type 0 to dump";
		}
		if(!board.empty()) {
			print += ", type 1 to move a piece, type 2 to remove a piece from the board";
		}
		if(lets.checkGameOver()) {	
			print += ", type 3 to end the game";
		}
		else if(lets.checkPeel()) {	
			print += ", type 3 to peel";
		}
		print+=": ";
		System.out.print(print);
		String in = sc.next().toUpperCase();
		while(in.length()!=1) {
			System.out.println("Please enter a single character");
			in = sc.next().toUpperCase();
		}
		char c = in.charAt(0);
		if(c=='0') {	//dump
			if(lets.getPool().size()>=3) {
				dump();
			}
			else {
				System.out.println("Not enough letters to dump");
			}
		}
		else if(c=='1') {	//move piece
			movePiece();
		}
		else if(c=='2') {
			removePiece();
		}
		else if(c=='3') {
			if(gameOver) {					//game over
				gameOver();
				return;
			}
			lets.peel();					//peel
			System.out.println("PEEL!");
		}
		else if(lets.hasLetter(c)) {
			playPiece(c);
		}
		else {
			System.out.println("Invalid entry");
		}
		playGame();		//makes method repeat itself over and over until game over
	}
	
	private void removePiece() {
		System.out.println("Which letter would you like to remove?");
		int[] coords = getCoords();
		if(coords[0]==-1)		////if user types -1 allows user to re-pick move
			return;
		while(!board.checkOld(coords[0],coords[1])) {
			coords = getCoords();
			if(coords[0]==-1)		////if user types -1 allows user to re-pick move
				return;
		}
		char c = board.remove(coords[0], coords[1]);
		lets.remove(c);
	}
	
	private void dump() {
		System.out.print("Type a letter to dump it or type -1 to select a different move");
		String in = sc.next().toUpperCase();
		while(in.length()!=1 && !in.equals("-1")) {
			System.out.println("Please enter a single character");
			in = sc.next().toUpperCase();
		}
		char c = in.charAt(0);
		if(in.equals("-1")) {
			return;
		}
		else if(lets.hasLetter(c)) {
			lets.dump(c);
		}
		else {
			System.out.println("Invalid entry");
		}
	}
	
	private void movePiece() {	//under construction
		System.out.println("Which letter would you like to move?");
		int[] coordsOld = getCoords();
		if(coordsOld[0]==-1)		////if user types -1 allows user to re-pick move
			return;
		while(!board.checkOld(coordsOld[0],coordsOld[1])) {
			coordsOld = getCoords();
			if(coordsOld[0]==-1)		////if user types -1 allows user to re-pick move
				return;
		}
		
		System.out.println("Where would you like to move it?");
		int[] coordsNew = getCoords();
		if(coordsNew[0]==-1)		////if user types -1 allows user to re-pick move
			return;
		while(!board.checkNew(coordsNew[0],coordsNew[1])) {
			coordsNew = getCoords();
			if(coordsNew[0]==-1)		////if user types -1 allows user to re-pick move
				return;
		}
		board.move(coordsOld[0], coordsOld[1], coordsNew[0], coordsNew[1]);
	}
	
	private void playPiece(char c) {		//plays a piece on the board
		int[] coords = getCoords();
		if(coords[0]==-1)
			return;
		while(!board.play(coords[0],coords[1],c)) {
			coords = getCoords();
			if(coords[0]==-1)		////if user types -1 allows user to re-pick move
				return;
		}
		lets.play(c);
	}
	
	private int[] getCoords() {	//index 0 is the x coordinate index 1 is the y coordinate
		int[] ret = new int[2];
		System.out.print("Enter x coordinate or type -1 to select a different move: ");		//x coordinate
		while(!sc.hasNextInt()) {
			sc.next();
			System.out.println("Please enter an integer");
		}
		ret[0] = sc.nextInt();
		if(ret[0]==-1) {
			return ret;			//if user types -1 allows user to re-pick move
		}
		System.out.print("Enter y coordinate or type -1 to select a different move: ");		//y coordinate
		while(!sc.hasNextInt()) {
			sc.next();
			System.out.println("Please enter an integer");
		}
		ret[1] = sc.nextInt();
		if(ret[1]==-1) {
			ret[0]=-1;
			return ret;			//if user types -1 allows user to re-pick move
		}
		return ret;
	}
	
	
	public boolean gameOver() {		//game over function
		board.printBoard(lets);
		if(board.checkConnected() && board.checkValidWords()) {		//checks if all letters are connected and if every word formed is a real word
			System.out.println("\nYou won!");	
			return true;
		}
		else {
			System.out.println("\nYou lost");
			return false;
		}
	}
	
	public Letters getLets() {
		return lets;
	}
	
	public Board getBoard() {
		return board;
	}
}