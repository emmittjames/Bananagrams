import java.util.*;

public class Game {
	
	private Board board;
	private Scanner sc;
	private Letters lets;
	
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
			char c = in.charAt(0);
			if(c=='0') {
				movePiece();
			}
			else if(c>=65 && c<=90) {
				playPiece(c);
				if(lets.check()) {	//checks for gameover or peel
					endGame();
				}
			}
			else {
				System.out.println("Invalid entry");
			}
		}
	}
	
	public void movePiece() {	//under construction
		
	}
	
	public void playPiece(char c) {
		System.out.print("Enter x coordinate: ");
		int xCord = sc.nextInt();
		System.out.print("Enter y coordinate: ");
		int yCord = sc.nextInt();
		if(lets.valid(c) && board.play(xCord,yCord,c)) {
			lets.play(c);
		}
		else {
			System.out.println("Invalid Move");
		}
	}
	
	public void endGame() {	//under construction
		//check every piece on board and call get right/down
		System.out.println("game over");
	}
}