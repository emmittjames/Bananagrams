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
	private Letters currentLetters = game.getLets();
	private Board board = game.getBoard();
	GridPane grid = new GridPane();
	boolean initClick = false;
	Tile currentTile;
	HBox letters;
	boolean noLetters=false;
	
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
    					placePiece(tile.getX(),tile.getY());
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
		
		Button peel = new Button("Peel");
		peel.setMinWidth(40);
        peel.setMaxWidth(40);
        peel.setMinHeight(40);
        peel.setMaxHeight(40);
		peel.setOnAction(e -> {
			System.out.println("PEEL!");
			peel();
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
		
		Button end = new Button("END");
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
		for(int i=0;i<currentLetters.getCurrLets().size();i++) {
			char c = currentLetters.getCurrLets().get(i);
			Tile tile = new Tile(i,c);
			tile.setText(c+"");
			tile.setMinWidth(40);
	        tile.setMaxWidth(40);
	        tile.setMinHeight(40);
	        tile.setMaxHeight(40);
			tile.setOnAction(e -> {
				System.out.println("letter press");
				currentTile = tile;
				initClick=true;
			});
			box.getChildren().add(new StackPane(tile));
		}
		return box;
	}
	
	public void movePiece(int x, int y) {
		grid.add(new StackPane(currentTile),x,y);
	}
	
	public void placePiece(int x, int y) {
		grid.add(new StackPane(currentTile),x,y);
		if(currentLetters.getCurrLets().size()!=0) {
			currentLetters.play(currentTile.getLetter());
		}
		else {
			noLetters=true;
		}
	}
	
	private void peel() {
		char c = currentLetters.peel();
		Tile tile = new Tile(c);
		tile.setText(c+"");
		tile.setMinWidth(40);
        tile.setMaxWidth(40);
        tile.setMinHeight(40);
        tile.setMaxHeight(40);
        tile.setOnAction(e -> {
			System.out.println("letter press");
			currentTile = tile;
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