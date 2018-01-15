package Credits;

import application.Main;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CreditsView 
{
	private Pane root = new Pane();
	Stage primaryStage;
	int WIDTH;
	int HEIGHT;
	Scene oldScene;
	
	public CreditsView(int width, int height)
	{
		//parametry początkowe
		this.WIDTH = width;
		this.HEIGHT = height;
		this.primaryStage = (Main.getPrimaryStage());
		
		Scene scene = new Scene(root,WIDTH,HEIGHT);
		scene.getStylesheets().add(this.getClass() .getResource("/Tutorial/tutorial.css").toExternalForm());
		
		addBackground();
		
		this.primaryStage.setScene(scene);
		this.primaryStage.setOpacity(1);
	}

	private void addBackground() 
	{
        ImageView imageView = new ImageView(new Image(getClass().getResource("/resource/Civ6_bg.png").toExternalForm()));
        imageView.setFitWidth(WIDTH);
        imageView.setFitHeight(HEIGHT);

        root.getChildren().add(imageView);
    }
}
