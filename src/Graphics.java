import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
	private GridPane grid = new GridPane();
	private boolean initClick = false;
	private Tile currTile;
	private HBox letters;
	private Button peel;
	private Button end;
	private int buttonSize = 37;
	
	private Parent setGame() {
		VBox pane = new VBox(50);
		pane.setPrefSize(800,600);
		pane.setPadding(new Insets(15, 15, 15, 15));
		
		GridPane grid = getGridPane();
		pane.getChildren().add(grid);
		grid.setAlignment(Pos.CENTER);
		
		HBox buttons = getButtons();
		pane.getChildren().add(buttons);
		buttons.setAlignment(Pos.CENTER);
		
		letters = getHBox();
		pane.getChildren().add(letters);
		letters.setAlignment(Pos.CENTER);
		
		grid.setAlignment(Pos.CENTER);
		return pane;
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
		tile.setText(j+""+i);
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
			}
		});
		
		Button dump = new Button("Dmp");
		dump.setMinWidth(buttonSize);
        dump.setMaxWidth(buttonSize);
        dump.setMinHeight(buttonSize);
        dump.setMaxHeight(buttonSize);
		dump.setOnAction(e -> {
			System.out.println("DUMP!");
		});
		
		Button del = new Button("Del");
		del.setMinWidth(buttonSize);
        del.setMaxWidth(buttonSize);
        del.setMinHeight(buttonSize);
        del.setMaxHeight(buttonSize);
		del.setOnAction(e -> {
			System.out.println("DELETE!");
		});
		
		end = new Button("END");
		end.setDisable(true);
		end.setMinWidth(buttonSize);
        end.setMaxWidth(buttonSize);
        end.setMinHeight(buttonSize);
        end.setMaxHeight(buttonSize);
		end.setOnAction(e -> {
			System.out.println("GAME OVER!");
			System.exit(0);
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
		setNewButton(currTile.getY(),currTile.getX());
		grid.add(new StackPane(currTile),x,y);
		currTile.setCoords(x,y);
	}
	
	public void placePiece(int x, int y) {
		grid.add(new StackPane(currTile),x,y);
		currTile.setCoords(x,y);
		currLetters.play((char)currTile.getLetter());
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

	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene scene = new Scene(setGame());
		primaryStage.setTitle("Bananagrams");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}