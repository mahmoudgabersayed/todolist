package screens;

import actions.*;
import entities.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import modules.*;
import programEntry.*;
import utils.*;
import validations.*;

import java.util.*;

public class LoginScreen extends BaseToDoGuiScreen
{
	private static String LOGIN = "@login_screen_login_button@";
	private static String REGISTER = "@login_screen_register_button@";

	private TextField userNameController;
	private TextField passwordController;
	private SaveButton login;
	private SaveButton register;

	@Override
	public void init()
	{
		userNameController = new TextField();
		passwordController = new TextField();
		login = new SaveButton(LOGIN, "Login", this);
		register = new SaveButton(REGISTER, "Register", this);
	}

	@Override
	public void createView()
	{
		GridPane infoGrid = new GridPane();
		getChildren().add(infoGrid);
		infoGrid.setHgap(20);
		infoGrid.setVgap(10);
		infoGrid.setPadding(new Insets(20));
		int rowIndex = 0;
		infoGrid.add(new Label("User Name"), 0, rowIndex);
		infoGrid.add(userNameController, 1, rowIndex++);
		infoGrid.add(new Label("Password"), 0, rowIndex);
		infoGrid.add(passwordController, 1, rowIndex++);
		infoGrid.add(register, 0, rowIndex);
		GridPane.setHalignment(login, HPos.RIGHT);
		infoGrid.add(login, 1, rowIndex);
	}

	@Override
	public void onSave(SaveButton button)
	{
		if (Checker.areEqual(REGISTER, button.getUiId()))
			TodoEntry.root.replaceChildren(new RegisterScreen());
		if (Checker.areEqual(LOGIN, button.getUiId()))
			TodoEntry.root.replaceChildren(new DashboardScreen());
	}

	@Override
	public void beforeSaveAction(ButtonWithValidation button, TODOResponse response)
	{
		if (Checker.areEqual(button.getUiId(), REGISTER))
			return;
		String userName = userNameController.getText();
		CommonValidation.checkRequired("User Name", userName, response);
		String password = passwordController.getText();
		CommonValidation.checkRequired("Password", password, response);
		TaskUser user = null;
		if (response.success())
			user = CoreLogic.getInstance().login(userName, password);
		if (response.success() && user == null)
			TODOResponse.createFailureResponse("User Name and Password do not match").addToResponse(response);
		error(response.getMessagesSeparatedInLines(), button);
		if (response.success())
			DashboardScreen.user = user;
	}

	@Override
	public List<Menu> buildMenus()
	{
		return TodoEntry.root.getDefaultMenuItems();
	}
}
