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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Graphics extends Application{
	
	private Parent setGame() {
		BorderPane pane = new BorderPane();
		pane.setPrefSize(800,600);
		pane.setPadding(new Insets(25, 25, 25, 25));
		
		Game game = new Game();
		Letters lets = game.getLets();
		Board board = game.getBoard();
		
		GridPane grid = board.getGridPane();
		pane.setTop(grid);
		grid.setAlignment(Pos.CENTER);
		
		VBox padding = new VBox(20,new Text(""),new Text(""));
		pane.setCenter(padding);
		
		HBox letters = lets.getHBox();
		pane.setBottom(letters);
		letters.setAlignment(Pos.CENTER);
		
		return pane;
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