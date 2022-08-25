import java.util.Optional;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Graphics extends Application{
	
	private Game game = new Game();
	private Letters letters = game.getLets();
	private Board board = game.getBoard();
	
	private VBox window;						//vbox that contains everything
	private GridPane grid = new GridPane();		//grid for the board
	private boolean initClick = false;			//false when no letter is selected, true when a letter is selected
	private Tile currTile;						//current selected tile
	private HBox hand;							//players hand
	private int buttonSize = 37;				//sets the size of all the buttons (board, hand, and functions)
	private Button peel, end, dump, del;		//buttons for all the functions
	private Text lettersRemaining = new Text("Letters remaining: " + letters.getPool().size());
	
	private Parent setGame() {		//sets all the elements for the game
		window = new VBox(20);
		window.setPrefSize(800,600);
		window.setPadding(new Insets(15, 15, 15, 15));
		
		window.getChildren().add(lettersRemaining);
		lettersRemaining.setTextAlignment(TextAlignment.CENTER);
		
		GridPane grid = getGridPane();		//grid
		window.getChildren().add(grid);
		grid.setAlignment(Pos.CENTER);
		
		HBox buttons = getButtons();			//buttons (peel, dump, del, end game)
		window.getChildren().add(buttons);
		buttons.setAlignment(Pos.CENTER);
		
		hand = getHBox();						//player's hand
		window.getChildren().add(hand);
		hand.setAlignment(Pos.CENTER);
		
		return window;
	}
	
	public GridPane getGridPane() {		//sets the board
		grid.setPrefSize(300, 300);

        for (int i = 0; i < board.getDimensions(); i++) {
            for (int j = 0; j < board.getDimensions(); j++) {
                setNewButton(j,i);
            }
        }
        return grid;
	}
	
	private void setNewButton(int j, int i) {		//sets all the buttons on the board
		Tile tile = new Tile(j,i);
		tile.setMinWidth(buttonSize);
        tile.setMaxWidth(buttonSize);
        tile.setMinHeight(buttonSize);
        tile.setMaxHeight(buttonSize);
		tile.setOnAction(e -> {
			if(initClick) {						
				if(currTile.getPlayed()) {					
					movePiece(tile.getX(),tile.getY());		//if a letter is selected that has been played, move it
				}
				else {
					currTile.setPlayed(true);
					placePiece(tile.getX(),tile.getY());	//if a letter is selected that has not been played, play it
				}
			}
			noTileSelected();
		});
		grid.add(new StackPane(tile),j,i);		//add the changes to the board
	}
	
	public HBox getButtons() {		//sets the buttons for the game
		HBox box = new HBox(5);
		
		peel = new Button("Peel");		//peel
		peel.setDisable(true);
		peel.setMinWidth(buttonSize);
        peel.setMaxWidth(buttonSize);
        peel.setMinHeight(buttonSize);
        peel.setMaxHeight(buttonSize);
		peel.setOnAction(e -> {
			if(letters.availablePeel()) {
				peel();								//peels when button is clicked
				peel.setDisable(true);
			}
			noTileSelected();
		});
		
		dump = new Button("Dmp");			//dump
		dump.setMinWidth(buttonSize);
        dump.setMaxWidth(buttonSize);
        dump.setMinHeight(buttonSize);
        dump.setMaxHeight(buttonSize);
        dump.setDisable(true);
        if(letters.getPool().size()<=2) {
			dump.setDisable(true);
		}
		dump.setOnAction(e -> {
			if(initClick) {
				int index = letters.getIndex((char)currTile.getLetter());
				dump(index);
				letters.delete((char)currTile.getLetter());			//deletes the letter from the actual hand
			}
			noTileSelected();
		});
		
		del = new Button("Del");			//delete
		del.setMinWidth(buttonSize);
        del.setMaxWidth(buttonSize);
        del.setMinHeight(buttonSize);
        del.setMaxHeight(buttonSize);
        del.setDisable(true);
		del.setOnAction(e -> {
			if(initClick) {
				if(currTile.getPlayed()) {
					removePiece(currTile.getX(),currTile.getY());	
					currTile.setPlayed(false);
			        hand.getChildren().add(currTile);
			        letters.add(currTile.getLetter());		//moves tile from the board back to the players hand
				}
				noTileSelected();
			}
		});
		
		end = new Button("End");				//game over
		end.setDisable(true);
		end.setMinWidth(buttonSize);
        end.setMaxWidth(buttonSize);
        end.setMinHeight(buttonSize);
        end.setMaxHeight(buttonSize);
		end.setOnAction(e -> {
			String str;
			if(game.gameOver()) {			//checks all words on the board and gives a win if all words are valid
				str = "You won";
			}
			else {
				str = "You lost";
			}
			ButtonType playAgain = new ButtonType("Play Again [doesn't work]");
			ButtonType exit = new ButtonType("Exit");
			Alert a = new Alert(AlertType.NONE, str, playAgain, exit);		//gives an alert when the game ends to exit or play again
			Optional<ButtonType> result = a.showAndWait();
			if(result.get() == exit) {
				System.out.println("close");
				System.exit(0);
			}
			if(result.get() == playAgain) {
				System.out.println("play again");
			}
			System.out.println("test");
		});
		
		box.getChildren().addAll(peel,dump,del,end);		//adds the buttons to the window
		return box;
	}
	
	public HBox getHBox() {					//sets the players hand with starting letters
		HBox box = new HBox(5);
		for(int i=0;i<letters.getCurrLets().size();i++) {	
			char c = letters.getCurrLets().get(i);
			Tile tile = makeNewTile(c);
			box.getChildren().add(new StackPane(tile));
		}
		return box;
	}
	
	private void tileSelected(Tile tile) {		//called when a tile is selected to enable the appropriate buttons
		initClick=true;
		currTile = tile;
		if(letters.getPool().size()>=3 && !currTile.getPlayed()) {
			dump.setDisable(false);
		}
		if(tile.getPlayed()) {
			del.setDisable(false);
		}
	}
	
	private void noTileSelected() {			//called when a tile is no longer selected to disable the appropriate buttons
		initClick=false;
		currTile = null;
		dump.setDisable(true);
		del.setDisable(true);
		if(letters.getCurrLets().size()==0) {
			if(letters.getPool().size()>0) {
				peel.setDisable(false);
			}
			else {
				end.setDisable(false);
			}
		}
	}

	private Tile makeNewTile(char c) {			//makes a new tile for the player's hand
		Tile tile = new Tile(c);
		tile.setText(c+"");
		tile.setMinWidth(buttonSize);
        tile.setMaxWidth(buttonSize);
        tile.setMinHeight(buttonSize);
        tile.setMaxHeight(buttonSize);
        tile.setOnAction(e -> {
        	tileSelected(tile);
		});
        return tile;
	}
	
	
	
	//============================================================================================================================================
	
	
	private void removePiece(int x, int y) {		//removes a letter from the board
		board.remove(x,y);
		setNewButton(x,y);
	}
	
	private void movePiece(int x, int y) {			//moves currTile from its previous coordinates to the new ones provided
		board.move(currTile.getX(),currTile.getY(),x,y);
		setNewButton(currTile.getX(),currTile.getY());
		grid.add(new StackPane(currTile),x,y);
		currTile.setCoords(x,y);
	}
	
	private void placePiece(int x, int y) {			//places currTile at the provided coordinates
		grid.add(new StackPane(currTile),x,y);
		currTile.setCoords(x,y);
		letters.play((char)currTile.getLetter());
		board.play(x,y,(char)currTile.getLetter());
	}
	
	private void peel() {							//gets a letter from the pool and puts it in the players hand
		char c = letters.peel();
		Tile tile = makeNewTile(c);
        hand.getChildren().add(new StackPane(tile));
        lettersRemaining.setText("Letters remaining :" + letters.getPool().size());
	}
	
	private void dump(int index) {		//gives the player 3 new letters while returning the selected one to the letter pool
		peel();
		peel();
		peel();
		remove(index);
	}
	
	private void remove(int index) {		//removes the letter at the provided index in the players hand
		hand.getChildren().remove(index);
		if(letters.getPool().size()>=3) {
			dump.setDisable(false);
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Stage stage = primaryStage;
		Scene scene = new Scene(setGame());
		stage.setTitle("Bananagrams");
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}