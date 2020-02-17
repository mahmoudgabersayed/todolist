package screens;

import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import programEntry.*;
import utils.*;

import java.util.*;

public class ToDoContainer extends VBox
{
	private MenuBar menuBar;

	public ToDoContainer()
	{
		menuBar = new MenuBar();
		init();
		createView();
	}

	public void replaceChildren(BaseToDoGuiScreen screen)
	{
		getChildren().clear();
		menuBar.getMenus().clear();
		menuBar.getMenus().addAll(screen.buildMenus());
		getChildren().addAll(menuBar, screen);
	}

	public void init()
	{
		menuBar.getMenus().addAll(getDefaultMenuItems());
	}

	public List<Menu> getDefaultMenuItems()
	{
		Menu operationMenu = new Menu("Operation");
		MenuItem login = new MenuItem("Login");
		setUpAction(login);
		MenuItem register = new MenuItem("Register");
		setUpAction(register);
		operationMenu.getItems().addAll(login, register);

		Menu exitMenu = new Menu("Exit");
		MenuItem close = new MenuItem("Close");
		setUpAction(close);
		exitMenu.getItems().addAll(close);
		return Arrays.asList(operationMenu, exitMenu);
	}

	private void setUpAction(MenuItem item)
	{
		setUpItemAction(item, "Login", LoginScreen.class);
		setUpItemAction(item, "Register", RegisterScreen.class);
		if (Checker.areNotEqual(item.getText(), "Close"))
			return;
		item.setOnAction(e -> System.exit(0));
	}

	private void setUpItemAction(MenuItem item, String forItem, Class<?> klass)
	{
		if (Checker.areNotEqual(item.getText(), forItem))
			return;
		item.setOnAction(e -> {
			try
			{
				Node node = Checker.getListFirstElement(TodoEntry.root.getChildren());
				if (node != null && node.getClass().isInstance(klass))
					return;
				replaceChildren((BaseToDoGuiScreen) klass.newInstance());
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
			}
		});
	}

	public void createView()
	{
		getChildren().clear();
		getChildren().addAll(menuBar);
	}
}
