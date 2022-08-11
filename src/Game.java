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
	
	public void playGame() {
		while(true) {
			board.printBoard(lets);
			System.out.print("Enter letter of a piece to play it or type 0 to move a piece: ");
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
				if(lets.check()) {	//checks for gameover or peel
					gameOver();
					break;
				}
			}
			else {
				System.out.println("Invalid entry");
			}
		}
	}
	
	public void movePiece() {	//under construction
		System.out.print("move piece");
	}
	
	public void playPiece(char c) {
		System.out.print("Enter x coordinate: ");		//x coordinate
		while(!sc.hasNextInt()) {
			sc.next();
			System.out.println("Please enter an integer");
		}
		int xCord = sc.nextInt();
		System.out.print("Enter y coordinate: ");		//y coordinate
		while(!sc.hasNextInt()) {
			sc.next();
			System.out.println("Please enter an integer");
		}
		int yCord = sc.nextInt();
		if(board.play(xCord,yCord,c)) {
			lets.play(c);
		}
		else {
			System.out.println("Invalid Move");
		}
	}
	
	public void gameOver() {
		board.printBoard(lets);
		if(board.checkValidWords()) {
			System.out.println("You won!");		//wins if all words are valid loss if any invalid words
		}
		else {
			System.out.println("You lost");
		}
	}
}