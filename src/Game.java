import java.util.*;

public class Game {
	
	private Board board;
	private Letters lets;
	private Scanner sc;
	
	public Game() {
		board = new Board();
		sc = new Scanner(System.in);
		lets = new Letters();
	}
	
	public void playGame() {		//Gets input from the user for desired course of action
		board.printBoard(lets);
		System.out.print("Enter a letter to play it, type 0 to move a piece, or type 1 to dump: ");
		String in = sc.next().toUpperCase();
		while(in.length()!=1) {
			System.out.println("Please enter a single character");
			in = sc.next().toUpperCase();
		}
		char c = in.charAt(0);
		if(c=='0') {
			movePiece();
		}
		else if(lets.hasLetter(c)) {
			playPiece(c);
			if(lets.check()) {	//checks for game over or peel
				gameOver();
				return;
			}
		}
		else {
			System.out.println("Invalid entry");
		}
		playGame();		//makes method repeat itself over and over until game over
	}
	
	private void movePiece() {	//under construction
		/*int[] coords = getCoords();
		if(!board.move(coords[0],coords[1])) {
			System.out.println("Invalid Move");
		}*/
	}
	
	private void playPiece(char c) {		//plays a piece on the board
		int[] coords = getCoords();
		if(coords[0]==-1)
			return;
		while(!board.play(coords[0],coords[1],c)) {
			System.out.println("Invalid Move");
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
	
	
	private void gameOver() {		//game over function
		board.printBoard(lets);
		if(board.checkValidWords()) {
			System.out.println("You won!");		//wins if all words are valid loss if any invalid words
		}
		else {
			System.out.println("You lost");
		}
	}
}