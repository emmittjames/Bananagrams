import java.util.*;

public class Game {
	
	private Board board;
	private Scanner sc;
	
	public Game() {
		board = new Board();
		sc = new Scanner(System.in);
	}
	
	public void playGame() {
		while(true) {
			board.printBoard();
			System.out.print("Enter letter or type 0 to move a piece: ");
			char c = sc.next().charAt(0);
			if(c=='0') {
				movePiece();
			}
			else {
				playPiece(c);
			}
		}
	}
	
	public void movePiece() {
		System.out.print("Enter x coordinate to move a piece or type 0 to play a piece: ");
	}
	
	public void playPiece(char c) {
		System.out.print("Enter x coordinate: ");
		int xCord = sc.nextInt();
		System.out.print("Enter y coordinate: ");
		int yCord = sc.nextInt();
		if(!board.play(xCord,yCord,c)) {
			System.out.println("Invalid Move");
		}
	}
}