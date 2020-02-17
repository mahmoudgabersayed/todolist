package screens;

import actions.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import programEntry.*;
import utils.*;
import validations.*;

import java.util.*;

public abstract class BaseToDoGuiScreen extends Pane
{
	public BaseToDoGuiScreen()
	{
		setStyle("-fx-background-color: green");
		init();
		createView();
	}

	public abstract void init();

	public abstract void createView();

	public abstract void onSave(SaveButton saveButton);

	public abstract <T extends ButtonWithValidation> void beforeSaveAction(T button, TODOResponse response);

	public void error(String message, IToDoButton button)
	{
		showError(message, button);
	}

	public void error(String message)
	{
		showError(message, null);
	}

	private void showError(String message, IToDoButton button)
	{
		if (Checker.isEmptyOrNull(message))
			return;
		if (button != null)
			button.setValidationNotPass(true);
		System.out.println(message);
		TodoEntry.tooltip.showTip(message);
	}

	public abstract List<Menu> buildMenus();
}
