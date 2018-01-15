package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.animation.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.Pair;

import java.util.Arrays;
import java.util.List;

import Credits.CreditsView;
import MainMenu.MenuItem;
import MainMenu.MenuTitle;
import Tutorial.TutorialView;

public class Main extends Application 
{
	//dane do przekazania kolejnym scenom
	private static final int WIDTH = 1280;	//warto pobrać rozmiar z systemu
    private static final int HEIGHT = 720;	//aktualne dane wynikają z obrazka
    static Stage primaryStage;
    static Scene scene;
	
    //akcje menu, do poprawy ilość i nazwy
    private List<Pair<String, Runnable>> menuData = Arrays.asList(
            new Pair<String, Runnable>("Single Player", () -> {
            		//nowa scena
            }),
            new Pair<String, Runnable>("Multiplayer", () -> {}),
            new Pair<String, Runnable>("Game Options", () -> {}),
            new Pair<String, Runnable>("Additional Content", () -> {}),
            new Pair<String, Runnable>("Tutorial", () -> {new TutorialView(WIDTH, HEIGHT);}),
            new Pair<String, Runnable>("Benchmark", () -> {}),
            new Pair<String, Runnable>("Credits", () -> {
            	new CreditsView(WIDTH, HEIGHT);}),
            new Pair<String, Runnable>("Exit to Desktop", Platform::exit)
            
    );
    
    //potrzebne do animacji
    private Pane root = new Pane();
    private VBox menuBox = new VBox(-5);
    private Line line;
    
    //ustawia elementy 
    private Parent createContent() {
        addBackground();
        addTitle();

        //pozycja menu
        double lineX = WIDTH / 2 - 100;
        double lineY = HEIGHT / 3 + 50;

        addLine(lineX, lineY);
        addMenu(lineX + 5, lineY + 5);

        startAnimation();

        return root;
    }
    
    private void addBackground() {
        ImageView imageView = new ImageView(new Image(getClass().getResource("/resource/Civ6_bg.png").toExternalForm()));
        imageView.setFitWidth(WIDTH);
        imageView.setFitHeight(HEIGHT);

        root.getChildren().add(imageView);
    }
    
    private void addTitle() {
    		MenuTitle title = new MenuTitle("Skarby Labiryntu");
        title.setTranslateX(WIDTH / 2 - title.getTitleWidth() / 2);
        title.setTranslateY(HEIGHT / 3);

        root.getChildren().add(title);
    }
    
    private void addLine(double x, double y) {
        line = new Line(x, y, x, y + 300);
        line.setStrokeWidth(3);
        line.setStroke(Color.color(1, 1, 1, 0.75));
        line.setEffect(new DropShadow(5, Color.BLACK));
        line.setScaleY(0);

        root.getChildren().add(line);
    }
    
    private void startAnimation() {
        ScaleTransition st = new ScaleTransition(Duration.seconds(1), line);
        st.setToY(1);
        st.setOnFinished(e -> {

            for (int i = 0; i < menuBox.getChildren().size(); i++) {
                Node n = menuBox.getChildren().get(i);

                TranslateTransition tt = new TranslateTransition(Duration.seconds(1 + i * 0.15), n);
                tt.setToX(0);
                tt.setOnFinished(e2 -> n.setClip(null));
                tt.play();
            }
        });
        st.play();
    }
    
    private void addMenu(double x, double y) {
        menuBox.setTranslateX(x);
        menuBox.setTranslateY(y);
        menuData.forEach(data -> {
            MenuItem item = new MenuItem(data.getKey());
            item.setOnAction(data.getValue());
            item.setTranslateX(-300);

            Rectangle clip = new Rectangle(300, 30);
            clip.translateXProperty().bind(item.translateXProperty().negate());

            item.setClip(clip);

            menuBox.getChildren().addAll(item);
        });

        root.getChildren().add(menuBox);
    }
    
    //gdy wracamy do pierwszego menu
    public static void restart(Stage primaryStage, Scene scene) {
        primaryStage.setTitle("Civilization VI Menu");
        primaryStage.setScene(scene);
    }
    
    //uruchomienie okienka
	@Override
	public void start(Stage primaryStage) 
	{
		Main.primaryStage = primaryStage;
		scene = new Scene(createContent());
        primaryStage.setTitle("Civilization VI Menu");
        primaryStage.setScene(scene);
        
        primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
	//GET & SET #########################################################
	
	public static Stage getPrimaryStage()
	{
		return primaryStage;
	}
	
	public static Scene getScene()
	{
		return scene;
	}
}
