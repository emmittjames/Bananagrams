public class Board {
	
	private char[][] board;
	
	public Board() {
		board = new char[18][18];	//Board is 15x15 with an extra space for edges and numbers
		fillWithBlanks();
		fillEdges();
	}
	
	public boolean play(int xCord, int yCord, char c) {		//plays a piece on the board
		if(xCord>=board.length-3 || yCord>=board[0].length-3 || xCord<0 || yCord<0) {		//ensures coordinates are on the board
			System.out.println("Invalid move - coordinates are not on the board");
			return false;
		}
		c = Character.toUpperCase(c);
		yCord+=2;		//Dilutes coords so they can be used with the board
		xCord+=2;
		if(board[yCord][xCord] == ' ') {		//makes sure move is on an unoccupied space
			board[yCord][xCord] = c;
			return true;
		}
		System.out.println("Invalid move - space already taken");
		return false;
	}
	
	public char remove(int xCord, int yCord) {
		xCord+=2;	//Dilutes coords so they can be used with the board
		yCord+=2;
		char c = board[yCord][xCord];
		board[yCord][xCord] = ' ';
		return c;
	}
	
	public void move(int xCordOld, int yCordOld, int xCordNew, int yCordNew) {		//moves letter from old coords to new coords
		xCordOld+=2;	//Dilutes coords so they can be used with the board
		yCordOld+=2;
		xCordNew+=2;
		yCordNew+=2;
		char c = board[yCordOld][xCordOld];
		board[yCordOld][xCordOld] = ' ';
		board[yCordNew][xCordNew] = c;
	}
	
	public boolean checkOld(int xCord, int yCord) {		//checks if the oldCoords in move are valid   //0=valid 1=invalid 2=change move
		if(xCord>=board.length-3 || yCord>=board[0].length-3 || xCord<0 || yCord<0) {		//ensures coordinates are on the board
			System.out.println("Invalid move - old coordinates are not on the board");
			return false;
		}
		yCord+=2;		//Dilutes coords so they can be used with the board
		xCord+=2;
		if(board[yCord][xCord] != ' ') {		//makes sure move is on a space with a letter
		}
		else {
			System.out.println("Invalid move - space is empty");
			return false;
		}
		return true;
	}
	
	public boolean checkNew(int xCord, int yCord) {		//checks if the newCoords in move are valid
		if(xCord>=board.length-3 || yCord>=board[0].length-3 || xCord<0 || yCord<0) {		//ensures coordinates are on the board
			System.out.println("Invalid move - new coordinates are not on the board");
			return false;
		}
		yCord+=2;		//Dilutes coords so they can be used with the board
		xCord+=2;
		if(board[yCord][xCord] == ' ') {		//makes sure move is on an empty space
		}
		else {
			System.out.println("Invalid move - space is taken");
			return false;
		}
		return true;
	}
	
	public boolean checkConnected() {	//checks if letter is connected to any other letters
		for(int i=2;i<board.length-2;i++) {
			for(int j=2;j<board[0].length-2;j++) {
				if(board[i][j]!=' ') {
					if(!checkAdjacent(j,i)) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	private boolean checkAdjacent(int xCord, int yCord) {		//checks for any adjacent letter
		if((board[yCord+1][xCord]>=65 && board[yCord+1][xCord]<=90) || (board[yCord-1][xCord]>=65 && board[yCord-1][xCord]<=90) || (board[yCord][xCord+1]>=65 && board[yCord][xCord+1]<=90) || (board[yCord][xCord-1]>=65 && board[yCord][xCord-1]<=90)) {
			return true;
		}
		return false;
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
				board[j][i] = ' ';
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
	
	public boolean empty() {	//returns true if the board is empty
		for(int i=2;i<board.length-2;i++) {
			for(int j=2;j<board[0].length-2;j++) {
				if(board[i][j]!=' ') {
					return false;
				}
			}
		}
		return true;
	}
	
	public void printBoard(Letters lets) {		//prints the board along with the current letters underneath
		for(int i=0;i<board.length;i++) {
			for(int j=0;j<board[0].length;j++) {
				System.out.print(board[i][j]);		//prints the board
			}
			System.out.print('\n');
		}
		System.out.println("Letters remaining: " + lets.getPool().size());
		System.out.print("Current letters: ");
		if(lets.getCurrLets().size()>=1) {
			for(int i=0;i<lets.getCurrLets().size()-1;i++) {
				System.out.print(lets.getCurrLets().get(i)+ ", ");			//prints the current letters
			}
			System.out.println(lets.getCurrLets().get(lets.getCurrLets().size()-1));
		}
	}
}