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
	
	private Parent setGame() {
		VBox pane = new VBox(50);
		pane.setPrefSize(800,600);
		pane.setPadding(new Insets(25, 25, 25, 25));
		
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

        for (int i = 0; i < board.getDimensions()-3; i++) {
            for (int j = 0; j < board.getDimensions()-3; j++) {
                Tile tile = new Tile(j,i);
    			tile.setText(j+""+i);
    			tile.setMinWidth(40);
    	        tile.setMaxWidth(40);
    	        tile.setMinHeight(40);
    	        tile.setMaxHeight(40);
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
        }
        return grid;
	}
	
	public HBox getButtons() {
		HBox box = new HBox(5);
		
		peel = new Button("Peel");
		peel.setDisable(true);
		peel.setMinWidth(40);
        peel.setMaxWidth(40);
        peel.setMinHeight(40);
        peel.setMaxHeight(40);
		peel.setOnAction(e -> {
			if(currLetters.availablePeel()) {
				System.out.println("PEEL!");
				peel();
				peel.setDisable(true);
			}
		});
		
		Button dump = new Button("Dmp");
		dump.setMinWidth(40);
        dump.setMaxWidth(40);
        dump.setMinHeight(40);
        dump.setMaxHeight(40);
		dump.setOnAction(e -> {
			System.out.println("DUMP!");
		});
		
		Button del = new Button("Del");
		del.setMinWidth(40);
        del.setMaxWidth(40);
        del.setMinHeight(40);
        del.setMaxHeight(40);
		del.setOnAction(e -> {
			System.out.println("DELETE!");
		});
		
		end = new Button("END");
		end.setDisable(true);
		end.setMinWidth(40);
        end.setMaxWidth(40);
        end.setMinHeight(40);
        end.setMaxHeight(40);
		end.setOnAction(e -> {
			System.out.println("GAME OVER!");
		});
		
		box.getChildren().addAll(peel,dump,del,end);
		return box;
	}
	
	public HBox getHBox() {
		HBox box = new HBox(5);
		for(int i=0;i<currLetters.getCurrLets().size();i++) {
			char c = currLetters.getCurrLets().get(i);
			Tile tile = new Tile(c);
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
		grid.add(new StackPane(currTile),x,y);
	}
	
	public void placePiece(int x, int y) {
		grid.add(new StackPane(currTile),x,y);
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
		tile.setMinWidth(40);
        tile.setMaxWidth(40);
        tile.setMinHeight(40);
        tile.setMaxHeight(40);
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