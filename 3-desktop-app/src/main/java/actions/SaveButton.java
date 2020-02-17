package actions;

import screens.*;
import validations.*;

public class SaveButton extends ButtonWithValidation
{
	private boolean moveToAnotherScreen;
	private String uiId;

	public SaveButton(BaseToDoGuiScreen owner)
	{
		super(owner);
	}

	public SaveButton(String uiId, BaseToDoGuiScreen owner)
	{
		super(owner);
		this.uiId = uiId;
	}

	public SaveButton(String uiId, String text, BaseToDoGuiScreen owner)
	{
		super(text, owner);
		this.uiId = uiId;
	}

	public String getUiId()
	{
		return uiId;
	}

	public boolean isMoveToAnotherScreen()
	{
		return moveToAnotherScreen;
	}

	public void setMoveToAnotherScreen(boolean moveToAnotherScreen)
	{
		this.moveToAnotherScreen = moveToAnotherScreen;
	}

	@Override
	public void setUpActions()
	{
		setOnMouseClicked(e -> {
			TODOResponse response = TODOResponse.createSuccessResponse();
			setValidationNotPass(false);
			getOwner().beforeSaveAction(this, response);
			if (isValidationNotPass())
				return;
			getOwner().onSave(this);
		});
	}
}
