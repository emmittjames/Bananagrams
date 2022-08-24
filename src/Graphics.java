import java.util.Optional;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Graphics extends Application{
	
	private Game game = new Game();
	private Letters currLetters = game.getLets();
	private Board board = game.getBoard();
	VBox window;
	private GridPane grid = new GridPane();
	private boolean initClick = false;
	private Tile currTile;
	private HBox letters;
	private Button peel;
	private Button end;
	private int buttonSize = 37;
	private Stage stage;
	private Button dump;
	private Button del;
	
	private Parent setGame() {
		window = new VBox(50);
		window.setPrefSize(800,600);
		window.setPadding(new Insets(15, 15, 15, 15));
		
		GridPane grid = getGridPane();
		window.getChildren().add(grid);
		grid.setAlignment(Pos.CENTER);
		
		HBox buttons = getButtons();
		window.getChildren().add(buttons);
		buttons.setAlignment(Pos.CENTER);
		
		letters = getHBox();
		window.getChildren().add(letters);
		letters.setAlignment(Pos.CENTER);
		
		grid.setAlignment(Pos.CENTER);
		return window;
	}
	
	public GridPane getGridPane() {
		grid.setPrefSize(300, 300);

        for (int i = 0; i < board.getDimensions(); i++) {
            for (int j = 0; j < board.getDimensions(); j++) {
                setNewButton(i,j);
            }
        }
        return grid;
	}
	
	private void setNewButton(int i, int j) {
		Tile tile = new Tile(j,i);
		tile.setMinWidth(buttonSize);
        tile.setMaxWidth(buttonSize);
        tile.setMinHeight(buttonSize);
        tile.setMaxHeight(buttonSize);
		tile.setOnAction(e -> {
			System.out.println("board press");
			if(initClick) {
				if(currTile.getPlayed()) {
					movePiece(tile.getX(),tile.getY());
				}
				else {
					currTile.setPlayed();
					placePiece(tile.getX(),tile.getY());
				}
			}
			initClick=false;
		});
		grid.add(new StackPane(tile),j,i);
	}
	
	public HBox getButtons() {
		HBox box = new HBox(5);
		
		peel = new Button("Peel");
		peel.setDisable(true);
		peel.setMinWidth(buttonSize);
        peel.setMaxWidth(buttonSize);
        peel.setMinHeight(buttonSize);
        peel.setMaxHeight(buttonSize);
		peel.setOnAction(e -> {
			if(currLetters.availablePeel()) {
				System.out.println("PEEL!");
				peel();
				peel.setDisable(true);
				if(currLetters.getPool().size()<=2) {
					dump.setDisable(true);
				}
			}
		});
		
		dump = new Button("Dmp");
		dump.setMinWidth(buttonSize);
        dump.setMaxWidth(buttonSize);
        dump.setMinHeight(buttonSize);
        dump.setMaxHeight(buttonSize);
        if(currLetters.getPool().size()<=2) {
			dump.setDisable(true);
		}
		dump.setOnAction(e -> {
			System.out.println("DUMP!");
			int index = currLetters.getIndex((char)currTile.getLetter());
			dump(index);
			currLetters.delete((char)currTile.getLetter());
			if(currLetters.getPool().size()<=2) {
				dump.setDisable(true);
			}
		});
		
		del = new Button("Del");
		del.setMinWidth(buttonSize);
        del.setMaxWidth(buttonSize);
        del.setMinHeight(buttonSize);
        del.setMaxHeight(buttonSize);
		del.setOnAction(e -> {
			System.out.println("DELETE!");
			int index = currLetters.delete((char)currTile.getLetter());
			delete(index);
		});
		
		end = new Button("End");
		end.setDisable(true);
		end.setMinWidth(buttonSize);
        end.setMaxWidth(buttonSize);
        end.setMinHeight(buttonSize);
        end.setMaxHeight(buttonSize);
		end.setOnAction(e -> {
			System.out.println("GAME OVER!");
			String str;
			if(game.gameOver()) {
				str = "You won";
			}
			else {
				str = "You lost";
			}
			ButtonType playAgain = new ButtonType("Play Again [doesn't work]");
			ButtonType exit = new ButtonType("Exit");
			Alert a = new Alert(AlertType.NONE, str, playAgain, exit);
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
		
		box.getChildren().addAll(peel,dump,del,end);
		return box;
	}
	
	public HBox getHBox() {
		HBox box = new HBox(5);
		for(int i=0;i<currLetters.getCurrLets().size();i++) {
			char c = currLetters.getCurrLets().get(i);
			Tile tile = new Tile(c);
			tile.setMinWidth(buttonSize);
	        tile.setMaxWidth(buttonSize);
	        tile.setMinHeight(buttonSize);
	        tile.setMaxHeight(buttonSize);
			tile.setOnAction(e -> {
				System.out.println("letter press");
				currTile = tile;
				initClick=true;
			});
			box.getChildren().add(new StackPane(tile));
		}
		return box;
	}
	
	
	
	//============================================================================================================================================
	
	
	
	public void movePiece(int x, int y) {
		board.move(currTile.getX(),currTile.getY(),x,y);
		setNewButton(currTile.getY(),currTile.getX());
		grid.add(new StackPane(currTile),x,y);
		currTile.setCoords(x,y);
	}
	
	public void placePiece(int x, int y) {
		grid.add(new StackPane(currTile),x,y);
		currTile.setCoords(x,y);
		currLetters.play((char)currTile.getLetter());
		board.play(x,y,(char)currTile.getLetter());
		if(currLetters.getCurrLets().size()==0) {
			if(currLetters.getPool().size()>0) {
				peel.setDisable(false);
			}
			else {
				end.setDisable(false);
			}
		}
	}
	
	private void peel() {
		System.out.println(currLetters.getPool() + " peel");
		char c = currLetters.peel();
		Tile tile = new Tile(c);
		tile.setText(c+"");
		tile.setMinWidth(buttonSize);
        tile.setMaxWidth(buttonSize);
        tile.setMinHeight(buttonSize);
        tile.setMaxHeight(buttonSize);
        tile.setOnAction(e -> {
			System.out.println("letter press");
			currTile = tile;
			initClick=true;
		});
        letters.getChildren().add(new StackPane(tile));
	}
	
	private void dump(int index) {
		peel();
		peel();
		peel();
		delete(index);
	}
	
	private void delete(int index) {
		letters.getChildren().remove(index);
		if(currLetters.getPool().size()>=3) {
			dump.setDisable(false);
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
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