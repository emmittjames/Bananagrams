public class Board {
	
	private char[][] board;
	
	public Board() {
		board = new char[18][18];	//Board is 10x10 with an extra space for edges and numbers
		fillWithBlanks();
		fillEdges();
	}
	
	public boolean play(int xCord, int yCord, char c) {		//plays a piece on the board
		if(xCord>board.length-3 || yCord>board[0].length-3 || xCord<0 || yCord<0) {		//ensures coordinates are on the board
			return false;
		}
		c = Character.toUpperCase(c);
		yCord = yCord+2;
		xCord = xCord+2;
		if(board[yCord][xCord] == ' ') {		//makes sure move is on an unoccupied space
			if(checkAdjacent(xCord,yCord)) {	//makes sure move is connected to other pieces (except for the first move)
				board[yCord][xCord] = c;
				return true;
			}
		}
		return false;
	}
	
	private boolean checkAdjacent(int xCord, int yCord) {		//checks adjacent letters or if the board is empty (first move)
		if(empty()){
			return true;
		}
		if((board[yCord+1][xCord]>=65 && board[yCord+1][xCord]<=90) || (board[yCord-1][xCord]>=65 && board[yCord-1][xCord]<=90) || (board[yCord][xCord+1]>=65 && board[yCord][xCord+1]<=90) || (board[yCord][xCord-1]>=65 && board[yCord][xCord-1]<=90)) {
			return true;
		}
		return false;
	}
	
	private boolean empty() {				//returns true if board is empty
		for(int i=2;i<board.length-2;i++) {
			for(int j=2;j<board[0].length-2;j++) {
				if(board[i][j]!=' ') {
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean checkValidWords() {		//checks if all words on the board are valid
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
	
	private boolean checkWord(int xCord, int yCord) {	//checks if the letter makes words with surrounding letters
		Dictionary dic = new Dictionary();
		String down = getVert(xCord,yCord);
		String right = getHoriz(xCord,yCord);
		if(dic.isWord(down) && dic.isWord(right)) {
			return true;
		}
		return false;
	}
	
	private String getVert(int xCord, int yCord) {		//get any vertical word given the coordinates of any letter in the word
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
	
	private String getHoriz(int xCord, int yCord) {		//gets any horizontal word given the coordinates of any letter in the word
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
	
	private void fillWithBlanks() {		//fills entire board with blanks chars
		for(int i=0;i<board.length;i++) {
			for(int j=0;j<board[0].length;j++) {
				board[i][j] = ' ';
			}
		}
	}
	
	private void fillEdges() {		//puts the edges on the borders of the board
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
			yVal = board.length-1;
		}

		int xVal = 1;
		for(int i=0;i<2;i++) {		//puts west and east side border
			for(int j=2;j<board.length-1;j++) {
					board[j][xVal] = '|';
			}
			xVal = board.length-1;
		}
		
		for(int i=2;i<board[0].length-1;i++) {	//puts numbers on north side
			board[0][i] = (char)((i-2)%10+'0');
		}
		
		for(int i=2;i<board[0].length-1;i++) {	//fills numbers on west side
			board[i][0] = (char)((i-2)%10+'0');
		}
	}
	
	public int getDimensions() {
		return board.length-3;
	}
	
	public void printBoard(Letters lets) {		//prints the board along with the current letters underneath
		for(int i=0;i<board.length;i++) {
			for(int j=0;j<board[0].length;j++) {
				System.out.print(board[i][j]);		//prints the board
			}
			System.out.print('\n');
		}
		System.out.print("Current letters: ");
		if(lets.getLets().size()>=1) {
			for(int i=0;i<lets.getLets().size()-1;i++) {
				System.out.print(lets.getLets().get(i)+ ", ");			//prints the current letters
			}
			System.out.println(lets.getLets().get(lets.getLets().size()-1));
		}
	}
}
