package actions;

import javafx.scene.control.*;
import javafx.stage.*;

public class TodoToolTip extends Tooltip
{
	private Stage window;

	public TodoToolTip(Stage window)
	{
		setAutoHide(true);
		this.window = window;
	}

	public void showTip(String message)
	{
		setText(message);
		super.show(window);
	}
}
