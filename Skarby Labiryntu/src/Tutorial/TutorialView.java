package Tutorial;

import application.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class TutorialView
{
	private Pane root = new Pane();
	Stage primaryStage;
	int WIDTH;
	int HEIGHT;
	Scene oldScene;
	
	public TutorialView(int width, int height)
	{
		this.primaryStage = Main.getPrimaryStage();
		this.WIDTH = width;
		this.HEIGHT = height;
		this.oldScene = Main.getScene();
		
		Scene scene = new Scene(root,WIDTH,HEIGHT);
		//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm()); //stworzyć nowy jeśli potrzeba
		scene.getStylesheets().add(this.getClass() .getResource("/Tutorial/tutorial.css").toExternalForm());
		
		addBackground();
		addTutorial();
		this.primaryStage.setScene(scene);
	}
	
	private void addBackground() {
        ImageView imageView = new ImageView(new Image(getClass().getResource("/resource/Civ6_bg.png").toExternalForm()));
        imageView.setFitWidth(WIDTH);
        imageView.setFitHeight(HEIGHT);

        root.getChildren().add(imageView);
    }
	
	private void addTutorial()
	{
		TextArea textArea = new TextArea("Przykładowy napis");
		
		//do przeniesienia w inne miejsce
		Button btn = new Button("Wstecz");
		btn.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) 
		    {
		        Main.restart(primaryStage, oldScene);
		    }
		});
		root.getChildren().add(btn);
		
		
		//przykładoww textArea
		textArea.layoutXProperty().bind(root.widthProperty().subtract(textArea.widthProperty()).divide(2));
		textArea.layoutYProperty().bind(root.heightProperty().subtract(textArea.heightProperty()).divide(3f));
		textArea.setPrefHeight(HEIGHT/1.5f);
		textArea.setPrefWidth(WIDTH/1.5f);
		textArea.setEditable(false);
		
		root.getChildren().add(textArea);
	}
}
