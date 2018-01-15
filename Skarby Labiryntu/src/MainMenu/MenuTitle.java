package MainMenu;

import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class MenuTitle extends Pane{
private Text text;
	
	public MenuTitle(String name) 
	{
		String spread = "";
		for(char c : name.toCharArray())
		{
			spread += c+" ";
		}
		text = new Text(spread);
		Font myFont = 
	            Font.loadFont(getClass()
	                .getResourceAsStream("/resource/Penumbra-HalfSerif-Std_35114.ttf"), 48);
		text.setFont(myFont);
		text.setFill(Color.WHITE);
        text.setEffect(new DropShadow(30, Color.BLACK));
        
        getChildren().addAll(text);
	}
	
	public double getTitleWidth() 
	{
        return text.getLayoutBounds().getWidth();
    }

    public double getTitleHeight() 
    {
        return text.getLayoutBounds().getHeight();
    }
}
