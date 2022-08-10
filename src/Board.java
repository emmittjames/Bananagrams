import java.util.*;

public class Board {
	
	private char[][] board;
	
	public Board() {
		board = new char[13][13];	//Board is 10x10 with an extra space for edges and numbers
		fillWithBlanks();
		fillEdges();
	}
	
	public boolean play(int xCord, int yCord, char c) {
		c = Character.toUpperCase(c);
		yCord = yCord+2;
		xCord = xCord+2;
		if(board[yCord][xCord] == ' ') {
			board[yCord][xCord] = c;
			return true;
		}
		return false;
	}
	
	public boolean checkValidWords() {
		System.out.println("test");
		for(int i=2;i<board.length-2;i++) {
			for(int j=2;j<board[0].length-2;j++) {
				if(board[i][j]!=' ') {
					if(!checkWord(j,i)) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	public boolean checkWord(int xCord, int yCord) {	//checks if the letter makes words with surrounding letters
		Dictionary dic = new Dictionary();
		
		String down = getDown(xCord,yCord);
		String right = getRight(xCord,yCord);
		
		if(dic.isWord(down) && dic.isWord(right)) {
			return true;
		}
		return false;
	}
	
	public String getDown(int xCord, int yCord) {
		String str = "";
		while(board[yCord-1][xCord]>= 65 && board[yCord-1][xCord]<=90){		//goes to the first letter (all the way up) of the word to check the entire word
			yCord--;
		}
		while(board[yCord][xCord]>= 65 && board[yCord][xCord]<=90){
			str += board[yCord][xCord];
			yCord++;
		}
		return str;
	}
	
	public String getRight(int xCord, int yCord) {
		String str = "";
		while(board[yCord][xCord-1]>= 65 && board[yCord][xCord-1]<=90){		//goes to the first letter (all the way left) in the word to check the entire word
			xCord--;
		}
		while(board[yCord][xCord]>= 65 && board[yCord][xCord]<=90){
			str += board[yCord][xCord];
			xCord++;
		}
		return str;
	}
	
	
	//=============================================================================================================
	
	
	public void fillWithBlanks() {		//fills entire board with blanks chars
		for(int i=0;i<board.length;i++) {
			for(int j=0;j<board[0].length;j++) {
				board[i][j] = ' ';
			}
		}
	}
	
	public void fillEdges() {
		int yVal = 1;
		for(int i=0;i<2;i++) {		//puts north and south side border
			for(int j=1;j<board[0].length;j++) {
				if(j==1||j==board[0].length-1) {
					board[yVal][j] = '+';
				}
				else {
					board[yVal][j] = '-';
				}
			}
			yVal = 12;
		}

		int xVal = 1;
		for(int i=0;i<2;i++) {		//puts west and east side border
			for(int j=2;j<board.length-1;j++) {
					board[j][xVal] = '|';
			}
			xVal = 12;
		}
		
		for(int i=2;i<board[0].length-1;i++) {	//puts numbers on north side
			board[0][i] = (char)((i-2)%10+'0');
		}
		
		for(int i=2;i<board[0].length-1;i++) {	//fills numbers on west side
			board[i][0] = (char)((i-2)%10+'0');
		}
	}
	
	public void printBoard(Letters lets) {
		for(int i=0;i<board.length;i++) {
			for(int j=0;j<board[0].length;j++) {
				System.out.print(board[i][j]);
			}
			System.out.print('\n');
		}
		System.out.print("Current letters: ");
		if(lets.getLets().size()>=1) {
			for(int i=0;i<lets.getLets().size()-1;i++) {
				System.out.print(lets.getLets().get(i)+ ", ");					//prints the current letters
			}
			System.out.println(lets.getLets().get(lets.getLets().size()-1));
		}
	}
}
