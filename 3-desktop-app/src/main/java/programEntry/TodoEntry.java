package programEntry;

import actions.*;
import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import modules.*;
import screens.*;

public class TodoEntry extends Application
{
	public static ToDoContainer root = new ToDoContainer();
	public static TodoToolTip tooltip;
	//TODO list
	/*TODO
	 *  This application need 2 screens
	 *  Register screen
	 *  Login screen
	 *  Task add / edit (save) actions
	 *  Tasks list (add - remove - edit) actions
	 *  Logout scenario
	 * */

	@Override
	public void start(Stage primaryStage) throws Exception
	{
		tooltip = new TodoToolTip(primaryStage);
		CoreLogic.loadConfiguration();
		root.replaceChildren(new LoginScreen());
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setOnCloseRequest(e -> System.exit(0));
	}

	public static void main(String[] args)
	{
		Application.launch(args);
	}
}
