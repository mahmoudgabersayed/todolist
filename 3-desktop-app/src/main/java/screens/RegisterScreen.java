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

public class RegisterScreen extends BaseToDoGuiScreen
{
	private static String SAVE_BUTTON = "@register_screen_save_button@";
	private static String SAVE_AND_LOGIN_BUTTON = "@register_screen_save_and_login_button@";

	private TextField codeController;
	private TextField name1Controller;
	private TextField name2Controller;
	private PasswordField passwordController;
	private PasswordField confirmPasswordController;
	private SaveButton save;
	private SaveButton saveAndLogin;

	@Override
	public void init()
	{
		codeController = new TextField();
		name1Controller = new TextField();
		name2Controller = new TextField();
		passwordController = new PasswordField();
		confirmPasswordController = new PasswordField();
		save = new SaveButton(SAVE_BUTTON, "Save", this);
		saveAndLogin = new SaveButton(SAVE_AND_LOGIN_BUTTON, "Save and Login", this);
	}

	@Override
	public void createView()
	{
		GridPane infoGrid = new GridPane();
		getChildren().add(infoGrid);
		infoGrid.setHgap(20);
		infoGrid.setVgap(10);
		int rowIndex = 0;
		infoGrid.add(new Label("Code"), 0, rowIndex);
		infoGrid.add(codeController, 1, rowIndex++);
		infoGrid.add(new Label("Arabic Name"), 0, rowIndex);
		infoGrid.add(name1Controller, 1, rowIndex++);
		infoGrid.add(new Label("English Name"), 0, rowIndex);
		infoGrid.add(name2Controller, 1, rowIndex++);
		infoGrid.add(new Label("Password"), 0, rowIndex);
		infoGrid.add(passwordController, 1, rowIndex++);
		infoGrid.add(new Label("Confirm Password"), 0, rowIndex);
		infoGrid.add(confirmPasswordController, 1, rowIndex++);
		infoGrid.add(save, 0, rowIndex);
		GridPane.setHalignment(saveAndLogin, HPos.RIGHT);
		infoGrid.add(saveAndLogin, 1, rowIndex);
	}

	@Override
	public void onSave(SaveButton button)
	{
		if (Checker.areEqual(SAVE_AND_LOGIN_BUTTON, button.getUiId()))
			TodoEntry.root.replaceChildren(new DashboardScreen());
	}

	@Override
	public void beforeSaveAction(ButtonWithValidation button, TODOResponse response)
	{
		TaskUser user = new TaskUser();
		user.setCode(codeController.getText());
		user.setName1(Checker.getFirstNotEmptyOrNull(name1Controller.getText(), name2Controller.getText()));
		user.setName2(Checker.getFirstNotEmptyOrNull(name2Controller.getText(), name1Controller.getText()));
		user.setPassword(passwordController.getText());
		CommonValidation.checkRequired("Code", user.getCode(), response);
		CommonValidation.checkRequired("Arabic Name", user.getName1(), response);
		CommonValidation.checkRequired("English Name", user.getName2(), response);
		CommonValidation.checkRequired("Password", user.getPassword(), response);
		CommonValidation.checkRequired("Confirm password", confirmPasswordController.getText(), response);
		if (Checker.areNotEqual(user.getPassword(), confirmPasswordController.getText()))
			TODOResponse.createFailureResponse("Passwords do not match").addToResponse(response);
		if (response.success())
			CoreLogic.getInstance().register(user).addToResponse(response);
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
