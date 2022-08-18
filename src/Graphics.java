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
	private Letters lets = game.getLets();
	private Board board = game.getBoard();
	GridPane grid = new GridPane();
	boolean initClick = false;
	Tile currentTile;
	
	private Parent setGame() {
		BorderPane pane = new BorderPane();
		pane.setPrefSize(800,600);
		pane.setPadding(new Insets(25, 25, 25, 25));
		
		GridPane grid = getGridPane();
		pane.setTop(grid);
		grid.setAlignment(Pos.CENTER);
		
		VBox padding = new VBox(20,new Text(""),new Text(""));
		pane.setCenter(padding);
		
		HBox letters = getHBox();
		pane.setBottom(letters);
		letters.setAlignment(Pos.CENTER);
		
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
    					placePiece(new Tile(0,'X'),tile.getX(),tile.getY());
    				}
    				initClick=false;
    			});
    			grid.add(new StackPane(tile),j,i);
            }
        }
        return grid;
	}
	
	public void placePiece(Tile tile, int x, int y) {
		grid.add(new StackPane(currentTile),x,y);
	}
	
	public HBox getHBox() {
		HBox box = new HBox(5);
		for(int i=0;i<lets.getCurrLets().size();i++) {
			char c = lets.getCurrLets().get(i);
			Tile tile = new Tile(i,true);
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