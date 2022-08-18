import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Graphics extends Application{
	
	private Parent setGame() {
		BorderPane pane = new BorderPane();
		pane.setPrefSize(800,600);
		pane.setPadding(new Insets(25, 25, 25, 25));
		
		Game game = new Game();
		Letters lets = game.getLets();
		Board board = game.getBoard();
		
		HBox letters = lets.getHBox();
		pane.setBottom(letters);
		
		GridPane grid = board.getGridPane();
		pane.setCenter(grid);
		
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