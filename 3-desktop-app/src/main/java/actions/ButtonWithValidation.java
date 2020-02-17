package actions;

import screens.*;

public abstract class ButtonWithValidation extends BaseToDoButton
{
	private boolean validationNotPass;

	public ButtonWithValidation(BaseToDoGuiScreen owner)
	{
		super(owner);
	}

	public ButtonWithValidation(String text, BaseToDoGuiScreen owner)
	{
		super(text, owner);
	}

	public boolean isValidationNotPass()
	{
		return validationNotPass;
	}

	public void setValidationNotPass(boolean validationNotPass)
	{
		this.validationNotPass = validationNotPass;
	}

	public abstract String getUiId();
}
